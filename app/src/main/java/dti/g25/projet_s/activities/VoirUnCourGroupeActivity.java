package dti.g25.projet_s.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import dti.g25.projet_s.R;
import dti.g25.projet_s.dao.MockDAOFactory;
import dti.g25.projet_s.presentation.modele.Modele;
import dti.g25.projet_s.presentation.presenteur.PresenteurVoirUnCourGroupe;
import dti.g25.projet_s.presentation.vue.VueVoirUnCourGroupe;

public class VoirUnCourGroupeActivity extends AppCompatActivity {
    PresenteurVoirUnCourGroupe _presenteur;
    // je simule un bouton avec un 'faux' coursGroupe
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_un_courgroupe);
        Modele modele=new Modele(this, new MockDAOFactory());
        modele.chargerCoursGroupeUtilisateur();
        VueVoirUnCourGroupe vue=new VueVoirUnCourGroupe();
        _presenteur= new PresenteurVoirUnCourGroupe(this, vue, modele,0);
        vue.setPresenteur(_presenteur);
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_voir_un_courgroupe, vue);
        ft.commit();
    }
}
