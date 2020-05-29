package dti.g25.projet_s.ui.activité;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import dti.g25.projet_s.R;
import dti.g25.projet_s.dao.DAOCoursGroupeRESTAPI;
import dti.g25.projet_s.dao.DAOFactoryRESTAPI;
import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.présenteur.PrésenteurVoirListeÉlèves;
import dti.g25.projet_s.présentation.vue.VueVoirListeÉlèves;

import static java.lang.String.valueOf;

public class VoirListeÉlevesActivité extends AppCompatActivity {

    private static final String EXTRA_CLÉ_CONNEXION = "dti.g25.projet_s.cléConnexion";
    private static final String EXTRA_POSITION_GROUPE = "dti.g25.projet_s.positionCourGroupe";
    private static final String EXTRA_POSITION_SEANCE = "dti.g25.projet_s.positionSeance";

    private PrésenteurVoirListeÉlèves présenteur;
    private Modèle modèle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_un_courgroupe);
        modèle=new Modèle(this);
        VueVoirListeÉlèves vue=new VueVoirListeÉlèves();
        présenteur= new PrésenteurVoirListeÉlèves(this, vue, modèle);
        vue.set_presenteur(présenteur);
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_voir_un_courgroupe, vue);
        ft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        int positionGroupe = getIntent().getIntExtra(EXTRA_POSITION_GROUPE, -1);
        int positionSeance = getIntent().getIntExtra(EXTRA_POSITION_SEANCE, -1);
        String cléUtilisateur = getIntent().getStringExtra(EXTRA_CLÉ_CONNEXION);

        modèle.setCléConnexion(cléUtilisateur);

        importerDonné(positionGroupe, positionSeance);
        try {
            présenteur.commencerListeÉlèvesPrésence(positionSeance, positionGroupe, cléUtilisateur);
        } catch (Exception e) {
            Log.e("ErreurVoirListeÉlèvesP", valueOf(e));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("uneActivité", "Terminer");
        try {
            présenteur.onActivityResult(requestCode, resultCode, data);
        } catch (Exception e) {
            Log.e("Erreur", String.valueOf(e));
        }
    }

    private void importerDonné(final int idGroupe, final int idSeance) {
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
                        if(idSeance > -1) {
                            final Response.Listener<JSONObject> réponse = new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject résultat) {
                                    try {
                                        modèle.setJsonPrésenceSeance(résultat, idSeance);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    présenteur.rafraîchir();
                                }
                            };
                            daoFactoryRESTAPI.obtenirPrésence(réponse, idSeance);

                        } else {
                            présenteur.rafraîchir();
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
