package dti.g25.projet_s.ui.activité;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import dti.g25.projet_s.R;
import dti.g25.projet_s.dao.MockDAOFactory;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.présenteur.PresenteurVoirListeSeance;
import dti.g25.projet_s.présentation.vue.VueVoirListeSeance;

public class VoirListeSeancesActivity extends AppCompatActivity {

    PresenteurVoirListeSeance _presenteur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
