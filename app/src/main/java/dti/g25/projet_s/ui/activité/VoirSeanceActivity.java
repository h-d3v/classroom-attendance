package dti.g25.projet_s.ui.activité;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import dti.g25.projet_s.R;
import dti.g25.projet_s.dao.DAOFactoryRESTAPI;
import dti.g25.projet_s.présentation.IContratVoirUneSeance;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.présenteur.PresenteurVoirUneSeance;
import dti.g25.projet_s.présentation.vue.VueVoirUneSeance;

public class VoirSeanceActivity extends AppCompatActivity {
    private static final String EXTRA_CLÉ_CONNEXION = "dti.g25.projet_s.cléConnexion";
    private static final String EXTRA_POSITION_GROUPE = "dti.g25.projet_s.positionCourGroupe";
    private static final String EXTRA_POSITION_SEANCE = "dti.g25.projet_s.positionSeance";

    IContratVoirUneSeance.IPresenteurVoirUneSeance presenteurVoirUneSeance;
    Modèle modèle;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_une_seance);
        modèle = new Modèle();
        VueVoirUneSeance vueVoirUneSeance=new VueVoirUneSeance();
        presenteurVoirUneSeance=new PresenteurVoirUneSeance(modèle,vueVoirUneSeance, this, 0);
        vueVoirUneSeance.setPresenteur(presenteurVoirUneSeance);
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_voir_une_seance, vueVoirUneSeance);
        ft.commit();

    }

    protected void onStart() {
        super.onStart();

        int positionGroupe = getIntent().getIntExtra( EXTRA_POSITION_GROUPE, -1);
        int positionSéance = getIntent().getIntExtra( EXTRA_POSITION_SEANCE, -1);
        String cléUtilisateur = getIntent().getStringExtra(EXTRA_CLÉ_CONNEXION);
        try {
            presenteurVoirUneSeance.commencerVoirSéance(positionGroupe, positionSéance, cléUtilisateur);
        } catch (Exception e) {
            e.printStackTrace();
        }
        chargerDonné(positionGroupe);
    }

    private void chargerDonné(final int idGroupe) {
        final DAOFactoryRESTAPI daoFactoryRESTAPI = new DAOFactoryRESTAPI(this);

        daoFactoryRESTAPI.setCle(modèle.getCléConnexion());

        Response.Listener<JSONObject> onResponse = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject réponse) {

                try {
                    modèle.setJsonUtilisateurActuelle(réponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Response.Listener<JSONObject> onResponse2 = new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            modèle.setJsonGroupeActuelle(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Response.Listener<JSONObject> onResponse3 = new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject réponse) {
                                try {
                                    Log.d("passe", "ici");
                                    modèle.setJSONSeances(réponse, modèle.getCoursGroupeActuelle());
                                    presenteurVoirUneSeance.rafraîchir();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        daoFactoryRESTAPI.getSeancesParCourGroupe(onResponse3, modèle.getCoursGroupeActuelle());
                    }
                };

                daoFactoryRESTAPI.chargerUnCourGroupeParId(onResponse2, idGroupe);
            }
        };

        daoFactoryRESTAPI.chargerUtilisateurActuel(onResponse);
    }



}
