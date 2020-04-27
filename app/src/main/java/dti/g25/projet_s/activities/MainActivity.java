package dti.g25.projet_s.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import dti.g25.projet_s.R;
import dti.g25.projet_s.dao.MockDAOFactory;
import dti.g25.projet_s.presentation.modele.Modele;
import dti.g25.projet_s.presentation.presenteur.PresenteurBtnVoirListeCoursGroupe;
import dti.g25.projet_s.presentation.vue.VueVoirBtnListeCoursGroupes;

public class MainActivity extends AppCompatActivity {

    PresenteurBtnVoirListeCoursGroupe _presenteur;
    //simule un bouton avec un 'faux' coursGroupe
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Modele modele=new Modele(this, new MockDAOFactory());
        VueVoirBtnListeCoursGroupes vue=new VueVoirBtnListeCoursGroupes();
        _presenteur= new PresenteurBtnVoirListeCoursGroupe(this, vue, modele);
        vue.setPresenteur(_presenteur);
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_voir_btn, vue);
        ft.commit();
    }
}
