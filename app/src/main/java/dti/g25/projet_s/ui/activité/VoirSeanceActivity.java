package dti.g25.projet_s.ui.activité;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import dti.g25.projet_s.R;
import dti.g25.projet_s.dao.MockDAOFactory;
import dti.g25.projet_s.présentation.IContratVoirUneSeance;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.présenteur.PresenteurVoirUneSeance;
import dti.g25.projet_s.présentation.vue.VueVoirUneSeance;

public class VoirSeanceActivity extends AppCompatActivity {
    private static final String EXTRA_CLÉ_CONNEXION = "dti.g25.projet_s.cléConnexion";
    private static final String EXTRA_POSITION_GROUPE = "dti.g25.projet_s.positionCourGroupe";
    private static final String EXTRA_POSITION_SEANCE = "dti.g25.projet_s.positionSeance";

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

    protected void onStart() {
        super.onStart();

        int positionGroupe = getIntent().getIntExtra( EXTRA_POSITION_GROUPE, -1);
        int positionSéance = getIntent().getIntExtra( EXTRA_POSITION_SEANCE, -1);
        String cléUtilisateur = getIntent().getStringExtra(EXTRA_CLÉ_CONNEXION);
        try {
            presenteurVoirUneSeance.commencerVoirSéance(positionGroupe, positionSéance, cléUtilisateur);
        } catch (Exception e) {
            Log.e("ErreurPrendrePrésence", String.valueOf(e));
        }
    }



}
