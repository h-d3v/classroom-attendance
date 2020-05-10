package dti.g25.projet_s.ui.activité;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import dti.g25.projet_s.R;
import dti.g25.projet_s.dao.MockDAOFactory;
import dti.g25.projet_s.présentation.modèle.dao.Modèle;
import dti.g25.projet_s.présentation.présenteur.PresenteurVoirCoursGroupe;
import dti.g25.projet_s.présentation.vue.VueVoirCoursGroupe;

public class VoirCoursGroupeActivity extends AppCompatActivity {
    PresenteurVoirCoursGroupe presenteurVoirCoursGroupe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_cours_groupe);
        MockDAOFactory mockDAOFactory= new MockDAOFactory();
        //A ajuster TODO
        Modèle modèle = new Modèle(mockDAOFactory,mockDAOFactory.getUtilisateur(0));
        modèle.chargerCoursGroupeUtilisateur();
        VueVoirCoursGroupe vueVoirCoursGroupe= new VueVoirCoursGroupe();
        presenteurVoirCoursGroupe=new PresenteurVoirCoursGroupe(vueVoirCoursGroupe, modèle,this);
        vueVoirCoursGroupe.set_presenteur(presenteurVoirCoursGroupe);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.layout_principal,vueVoirCoursGroupe);
        fragmentTransaction.commit();


    }
}
