package dti.g25.projet_s.ui.activité;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import dti.g25.projet_s.R;
import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.LibelleCours;
import dti.g25.projet_s.domaine.interacteurs.GestionCoursGroupe;
import dti.g25.projet_s.présentation.modèle.dao.Modèle;
import dti.g25.projet_s.présentation.présenteur.PresenteurVoirUnCourGroupe;
import dti.g25.projet_s.présentation.vue.VueVoirUnCourGroupe;

public class VoirUnCourGroupeActivity extends AppCompatActivity {
    PresenteurVoirUnCourGroupe _presenteur;
    // je simule un bouton avec un 'faux' coursGroupe
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_un_courgroupe);
        Modèle modele=new Modèle(this);
        GestionCoursGroupe gcg= new GestionCoursGroupe();
        CoursGroupe coursGroupe=gcg.creerCoursGroupe(new LibelleCours("APP WEB 3", "430-876HY"),76);
        modele.addCoursGroupe(coursGroupe);
        VueVoirUnCourGroupe vue=new VueVoirUnCourGroupe();
        _presenteur= new PresenteurVoirUnCourGroupe(this, vue, modele,0);
        vue.setPresenteur(_presenteur);
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_voir_un_courgroupe, vue);
        ft.commit();
    }
}
