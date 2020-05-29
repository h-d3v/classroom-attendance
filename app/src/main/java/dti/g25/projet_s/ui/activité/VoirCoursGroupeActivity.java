package dti.g25.projet_s.ui.activité;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentTransaction;
import dti.g25.projet_s.R;
import dti.g25.projet_s.dao.DAOFactoryRESTAPI;
import dti.g25.projet_s.présentation.modèle.dao.ModèleDAO;
import dti.g25.projet_s.présentation.présenteur.PresenteurVoirCoursGroupe;
import dti.g25.projet_s.présentation.vue.VueVoirCoursGroupe;

public class VoirCoursGroupeActivity extends AppCompatActivity {
    private static final String EXTRA_CLÉ_CONNEXION = "dti.g25.projet_s.cléConnexion";

    PresenteurVoirCoursGroupe presenteurVoirCoursGroupe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_cours_groupe);
        //A ajuster TODO
        VueVoirCoursGroupe vueVoirCoursGroupe= new VueVoirCoursGroupe();
        ModèleDAO modèle = new ModèleDAO(this,new  DAOFactoryRESTAPI(this));
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
        try {
         // presenteurVoirCoursGroupe.telechargerCoursGroupeUtilisateur(Intent data);
        } catch (Exception e) {
          Log.e("Erreur", String.valueOf(e));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("uneActivité", "Terminer");
        try {
            presenteurVoirCoursGroupe.telechargerCoursGroupeUtilisateur(data);
        } catch (Exception e) {
            Log.e("Erreur", String.valueOf(e));
        }
    }
}
