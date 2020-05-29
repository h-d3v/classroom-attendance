package dti.g25.projet_s.ui.activité;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Response;

import org.json.JSONObject;

import dti.g25.projet_s.R;
import dti.g25.projet_s.dao.DAOFactoryRESTAPI;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.présenteur.PresenteurVoirCoursGroupe;
import dti.g25.projet_s.présentation.vue.VueVoirCoursGroupe;

public class VoirCoursGroupeActivity extends AppCompatActivity {
    private static final String EXTRA_CLÉ_CONNEXION = "dti.g25.projet_s.cléConnexion";

    PresenteurVoirCoursGroupe presenteurVoirCoursGroupe;
    Modèle modèle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_cours_groupe);
        //A ajuster TODO
        VueVoirCoursGroupe vueVoirCoursGroupe= new VueVoirCoursGroupe();
        modèle= new Modèle(this);
        presenteurVoirCoursGroupe=new PresenteurVoirCoursGroupe(vueVoirCoursGroupe, modèle,this);
        modèle.chargerCoursGroupeUtilisateur();
        vueVoirCoursGroupe.set_presenteur(presenteurVoirCoursGroupe);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.layout_VoirCoursGroupes,vueVoirCoursGroupe);
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final DAOFactoryRESTAPI daoFactoryRESTAPI =  new DAOFactoryRESTAPI(this);

        daoFactoryRESTAPI.setCle(data.getStringExtra(EXTRA_CLÉ_CONNEXION));
        Response.Listener onResponse1 = (new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Response.Listener onResponse = new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        modèle.setJsonGroupes(response);
                        presenteurVoirCoursGroupe.rafraîchir();
                    }
                };
                try {
                    modèle.setJsonUtilisateurActuelle(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                daoFactoryRESTAPI.chargerListeCoursGroupeParUtilisateur(modèle.getUtilisateurConnecte(), onResponse);
            }
        });

        daoFactoryRESTAPI.chargerUtilisateurActuel(onResponse1);

        presenteurVoirCoursGroupe.commencerVoirCourGroupe(data.getStringExtra(EXTRA_CLÉ_CONNEXION));
    }

}
