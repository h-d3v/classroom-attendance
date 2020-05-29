package dti.g25.projet_s.ui.activité;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.Response;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import dti.g25.projet_s.R;
import dti.g25.projet_s.dao.DAOFactoryRESTAPI;
import dti.g25.projet_s.dao.Singleton;

import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.entité.Utilisateur;

import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.présenteur.PrésenteurPrendrePrésence;
import dti.g25.projet_s.présentation.vue.VuePrendrePrésence;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class PrendrePrésenceActivité extends AppCompatActivity {
    private static final String EXTRA_CLÉ_CONNEXION = "dti.g25.projet_s.cléConnexion";
    private static final String EXTRA_POSITION_GROUPE = "dti.g25.projet_s.positionCourGroupe";
    private static final String EXTRA_POSITION_SEANCE = "dti.g25.projet_s.positionSeance";
    SharedPreferences sharedPreferences;

    PrésenteurPrendrePrésence présenteur;
    Modèle modèle;
    /**
     * Initialise la vue pour l'activité prendre présence ansi que le présenteur
     * pour la vue
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int positionCoursGroupe = getIntent().getIntExtra( EXTRA_POSITION_GROUPE, -1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prendre_presence);
        VuePrendrePrésence vue = new VuePrendrePrésence();
        modèle = new Modèle();
        modèle= new Modèle(this);
        présenteur = new PrésenteurPrendrePrésence(this, vue, modèle);
        vue.setPrésenteur(présenteur);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.layout_prendre_présence,vue);
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        int positionGroupe = getIntent().getIntExtra( EXTRA_POSITION_GROUPE, -1);
        int positionSéance = getIntent().getIntExtra( EXTRA_POSITION_SEANCE, -1);
        String cléUtilisateur = getIntent().getStringExtra(EXTRA_CLÉ_CONNEXION);
        modèle.setCléConnexion(cléUtilisateur);
        chargerDonnée(positionGroupe, positionSéance);
    }

    protected void chargerDonnée(final int idGroupe, final int idSéance){
        final DAOFactoryRESTAPI daoFactoryRESTAPI = new DAOFactoryRESTAPI(this);
        daoFactoryRESTAPI.setCle(modèle.getCléConnexion());

        Response.Listener<JSONObject> réponse = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject response) {
                final Response.Listener<JSONObject> réponse = new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject résultat) {
                        try {
                            modèle.setJsonUtilisateurs(résultat);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            modèle.setJSONSeances(résultat, modèle.getCoursGroupeActuelle());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(idSéance > -1) {
                            final Response.Listener<JSONObject> réponse = new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject résultat) {
                                    try {
                                        modèle.setJsonPrésenceSeance(résultat, idSéance);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        présenteur.commencerPrendrePrésence(idGroupe, idSéance, modèle.getCléConnexion());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            daoFactoryRESTAPI.obtenirPrésence(réponse, idSéance);

                        } else {
                            try {
                                présenteur.commencerPrendrePrésence(idGroupe, idSéance, modèle.getCléConnexion());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };

                try {
                    modèle.setJsonGroupeActuelle(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                daoFactoryRESTAPI.getSeancesParCourGroupe(réponse, modèle.getCoursGroupeActuelle());
            }
        };

        daoFactoryRESTAPI.chargerUnCourGroupeParId(réponse, idGroupe);
    }

}
