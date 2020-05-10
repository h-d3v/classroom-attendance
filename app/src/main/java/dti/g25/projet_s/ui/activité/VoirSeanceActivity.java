package dti.g25.projet_s.ui.activité;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import dti.g25.projet_s.R;
import dti.g25.projet_s.dao.MockDAOFactory;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.présenteur.PresenteurVoirUneSeance;
import dti.g25.projet_s.présentation.vue.VueVoirUneSeance;

public class VoirSeanceActivity extends AppCompatActivity {
IContratVoirUneSeance.IPresenteurVoirUneSeance presenteurVoirUneSeance;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_une_seance);
        MockDAOFactory mockDAOFactory= new MockDAOFactory();
        Modèle modèle = new Modèle(mockDAOFactory);
        modèle.chargerCoursGroupeUtilisateur();
        VueVoirUneSeance vueVoirUneSeance=new VueVoirUneSeance();
        presenteurVoirUneSeance=new PresenteurVoirUneSeance(modèle,vueVoirUneSeance, this, 0);
        vueVoirUneSeance.setPresenteur(presenteurVoirUneSeance);
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_voir_une_seance, vueVoirUneSeance);
        ft.commit();



    }



}
