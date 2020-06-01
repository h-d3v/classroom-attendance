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
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.présenteur.PresenteurVoirUnCourGroupe;
import dti.g25.projet_s.présentation.vue.VueVoirUnCourGroupe;

import static java.lang.String.valueOf;

public class VoirUnCourGroupeActivity extends AppCompatActivity {
    private static final String EXTRA_CLÉ_CONNEXION = "dti.g25.projet_s.cléConnexion";
    private static final String EXTRA_POSITION_GROUPE = "dti.g25.projet_s.positionCourGroupe";

    PresenteurVoirUnCourGroupe _presenteur;
    Modèle _modele;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_un_courgroupe);
        _modele=new Modèle();
        VueVoirUnCourGroupe vue=new VueVoirUnCourGroupe();
        _presenteur= new PresenteurVoirUnCourGroupe(this, vue, _modele);
        vue.setPresenteur(_presenteur);
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_voir_un_courgroupe, vue);
        ft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        int position = getIntent().getIntExtra(EXTRA_POSITION_GROUPE, -1);
        String cléUtilisateur = getIntent().getStringExtra(EXTRA_CLÉ_CONNEXION);

        Log.d("clé", cléUtilisateur);
        _modele.setCléConnexion(cléUtilisateur);
        chargerDonné(position);

        try {
            _presenteur.commencerVoirCourGroupe(position, cléUtilisateur);
        } catch (Exception e) {
            Log.e("ErreurVoirCourGroupe", valueOf(e));
        }
    }


    private void chargerDonné(final int idGroupe) {
        final DAOFactoryRESTAPI daoFactoryRESTAPI = new DAOFactoryRESTAPI(this);

        Log.d("clé", _modele.getCléConnexion());
        daoFactoryRESTAPI.setCle(_modele.getCléConnexion());

        Response.Listener<JSONObject> onResponse = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject réponse) {

                try {
                    _modele.setJsonUtilisateurActuelle(réponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Response.Listener<JSONObject> onResponse2 = new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            _modele.setJsonGroupeActuelle(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Log.d("Schéma", "ca passe ici");
                        Response.Listener<JSONObject> onResponse3 = new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject réponse) {
                                try {
                                    _modele.setJSONSeances(réponse, _modele.getCoursGroupeActuelle());
                                    _presenteur.rafraîchir();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        daoFactoryRESTAPI.getSeancesParCourGroupe(onResponse3, _modele.getCoursGroupeActuelle());
                    }
                };

                daoFactoryRESTAPI.chargerUnCourGroupeParId(onResponse2, idGroupe);
            }
        };

        daoFactoryRESTAPI.chargerUtilisateurActuel(onResponse);
    }
}
