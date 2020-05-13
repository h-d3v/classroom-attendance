package dti.g25.projet_s.ui.activité;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentTransaction;

import androidx.appcompat.app.AppCompatActivity;

import dti.g25.projet_s.R;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.présenteur.PrésenteurPrendrePrésence;
import dti.g25.projet_s.présentation.vue.VuePrendrePrésence;

public class PrendrePrésenceActivité extends AppCompatActivity {
    private static final String EXTRA_CLÉ_CONNEXION = "dti.g25.projet_s.cléConnexion";
    private static final String EXTRA_POSITION_GROUPE = "dti.g25.projet_s.positionCourGroupe";
    private static final String EXTRA_POSITION_SEANCE = "dti.g25.projet_s.positionSeance";

    PrésenteurPrendrePrésence présenteur;

    /**
     * Initialise la vue pour l'activité prendre présence ansi que le présenteur
     * pour la vue
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prendre_presence);

        Modèle  modèle = new Modèle();

        VuePrendrePrésence vue=new VuePrendrePrésence();

        présenteur=new PrésenteurPrendrePrésence(this, vue, modèle);
        vue.setPrésenteur(présenteur);

        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_prendre_présence, vue);
        ft.commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        int positionProjet = getIntent().getIntExtra( EXTRA_POSITION_GROUPE, -1);
        int positionSéance = getIntent().getIntExtra( EXTRA_POSITION_SEANCE, -1);
        String cléUtilisateur = getIntent().getStringExtra(EXTRA_CLÉ_CONNEXION);
        try {
            présenteur.commencerPrendrePrésence(positionProjet, positionSéance, cléUtilisateur);
        } catch (Exception e) {
            Log.e("ErreurPrendrePrésence", String.valueOf(e));
        }
    }

}
