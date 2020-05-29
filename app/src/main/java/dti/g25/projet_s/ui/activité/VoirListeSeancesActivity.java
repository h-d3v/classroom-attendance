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

        //TODO finir les classe des vues et le presenteur pour cette activite
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_voir_liste_seances);
        MockDAOFactory mockDAOFactory= new MockDAOFactory();
        // TODO peupler le row mapper a l'aide du data mock

        Modèle modèle = new Modèle(mockDAOFactory);
        modèle.setUtilisateur(mockDAOFactory.getUtilisateur(0));
        modèle.chargerSeanceUtilisateur();
        modèle.chargerCoursGroupeUtilisateur();
        VueVoirListeSeance _vue= new VueVoirListeSeance();
       _presenteur=new PresenteurVoirListeSeance(this,_vue,modèle);
        _vue.set_presenteur(_presenteur);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.layout_voir_liste_seances,_vue);
        fragmentTransaction.commit();

    }
}
