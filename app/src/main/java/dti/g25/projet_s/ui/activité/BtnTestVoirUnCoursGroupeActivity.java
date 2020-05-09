package dti.g25.projet_s.ui.activité;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import dti.g25.projet_s.R;
import dti.g25.projet_s.présentation.présenteur.PresenteurBtnVoirListeCoursGroupe;
import dti.g25.projet_s.présentation.vue.VueVoirBtnListeCoursGroupes;

public class BtnTestVoirUnCoursGroupeActivity extends AppCompatActivity {

    PresenteurBtnVoirListeCoursGroupe _presenteur;
    //simule un bouton avec un 'faux' coursGroupe
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Modèle modele= new Modèle(this);
        VueVoirBtnListeCoursGroupes vue=new VueVoirBtnListeCoursGroupes();
        _presenteur= new PresenteurBtnVoirListeCoursGroupe(this, vue, modele);
        vue.setPresenteur(_presenteur);
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_voir_btn, vue);
        ft.commit();
    }
}
