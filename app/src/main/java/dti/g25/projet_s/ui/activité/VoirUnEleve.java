package dti.g25.projet_s.ui.activité;

import android.os.Bundle;
import android.util.Log;
import java.lang.Exception;
import java.security.spec.ECField;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import dti.g25.projet_s.R;
import dti.g25.projet_s.dao.DAOFactoryRESTAPI;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.présenteur.PresenteurVoirUnEleve;
import dti.g25.projet_s.présentation.vue.VueVoirUnEleve;

import static java.lang.String.valueOf;

public class VoirUnEleve extends AppCompatActivity {
    private static final String EXTRA_CLÉ_CONNEXION = "dti.g25.projet_s.cléConnexion";
    private static final String EXTRA_POSITION_GROUPE = "dti.g25.projet_s.positionCourGroupe";
    private static final String EXTRA_POSITION_SEANCE = "dti.g25.projet_s.positionSeance";
    private static final String EXTRA_POSITION_ÉLÈVES = "dti.g25.projet_s.positionÉlèves";

    PresenteurVoirUnEleve présenteur;
    private Modèle modèle;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_eleve);

        modèle= new Modèle();
        VueVoirUnEleve vue = new VueVoirUnEleve();

        présenteur = new PresenteurVoirUnEleve(this,  vue, modèle);
        vue.setPresenteur(présenteur);

        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_eleve, vue);
        ft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        int positionSeance =getIntent().getIntExtra(EXTRA_POSITION_SEANCE, -1);
        int positionGroupe = getIntent().getIntExtra(EXTRA_POSITION_GROUPE, -1);
        int positionÉlèves = getIntent().getIntExtra(EXTRA_POSITION_ÉLÈVES, -1);
        String cléUtilisateur = getIntent().getStringExtra(EXTRA_CLÉ_CONNEXION);

        modèle.setCléConnexion(cléUtilisateur);

        importerDonné(positionGroupe);

        try {
            présenteur.commencerVoirUnÉlèves(positionSeance, positionGroupe, positionÉlèves, cléUtilisateur);
        } catch (Exception e) {
            Log.e("ErreurVoirÉlève", valueOf(e));
        }
    }

    private void importerDonné(final int idGroupe) {
        final DAOFactoryRESTAPI daoFactoryRESTAPI = new DAOFactoryRESTAPI(this);
        daoFactoryRESTAPI.setCle(modèle.getCléConnexion());

        Response.Listener<JSONObject> réponse = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject response) {
                try{
                    modèle.setJsonGroupeActuelle(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Response.Listener<JSONObject> réponse2 = new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject reponse){
                        Log.d("test: ","passed?");
                        try {
                            modèle.setJsonUtilisateurs(response);
                            présenteur.rafraichir();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                daoFactoryRESTAPI.getListeÉlèvesCour(réponse2, modèle.getCoursGroupeActuelle());
            }
        };
        daoFactoryRESTAPI.chargerUnCourGroupeParId(réponse, idGroupe);
    }
}