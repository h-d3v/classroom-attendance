package dti.g25.projet_s.ui.activité;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import dti.g25.projet_s.R;
import dti.g25.projet_s.présentation.IContratVuePrésenteurVoirUnEleve;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.présenteur.PresenteurVoirUnEleve;
import dti.g25.projet_s.présentation.vue.VueVoirUnEleve;

public class VoirUnEleve extends AppCompatActivity {

    PresenteurVoirUnEleve présenteur;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_eleve);

        Modèle modèle= new Modèle();
        VueVoirUnEleve vue = new VueVoirUnEleve();

        présenteur = new PresenteurVoirUnEleve(this,  vue, modèle);
        vue.setPresenteur(présenteur);

        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_eleve, vue);
        ft.commit();
    }
}
