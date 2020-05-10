package dti.g25.projet_s.ui.activité;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentTransaction;
import dti.g25.projet_s.R;
import dti.g25.projet_s.dao.MockDAOFactory;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.présenteur.PresenteurVoirCoursGroupe;
import dti.g25.projet_s.présentation.vue.VueVoirCoursGroupe;

public class VoirCoursGroupeActivity extends AppCompatActivity {
    private static final String EXTRA_CLÉ_CONNEXION = "dti.g25.projet_s.position";

    PresenteurVoirCoursGroupe presenteurVoirCoursGroupe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_cours_groupe);
        MockDAOFactory mockDAOFactory= new MockDAOFactory();
        //A ajuster TODO
        Modèle modèle = new Modèle();
        modèle.chargerCoursGroupeUtilisateur();
        VueVoirCoursGroupe vueVoirCoursGroupe= new VueVoirCoursGroupe();
        presenteurVoirCoursGroupe=new PresenteurVoirCoursGroupe(vueVoirCoursGroupe, modèle,this);
        vueVoirCoursGroupe.set_presenteur(presenteurVoirCoursGroupe);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.layout_VoirCoursGroupes,vueVoirCoursGroupe);
        fragmentTransaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("uneActivité", "Terminer");
        try {
            presenteurVoirCoursGroupe.onActivityResult(requestCode, resultCode, data);
        } catch (Exception e) {
            Log.e("Erreur", String.valueOf(e));
        }
    }
}
