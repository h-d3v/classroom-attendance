package dti.g25.projet_s.ui.activité;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import dti.g25.projet_s.R;
import dti.g25.projet_s.présentation.ContratVuePrésenteurVoirListeÉlèves;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.présenteur.PresenteurVoirUnCourGroupe;
import dti.g25.projet_s.présentation.présenteur.PrésenteurVoirListeÉlèvesPrésence;
import dti.g25.projet_s.présentation.vue.VueVoirListeÉlèvesPrésence;
import dti.g25.projet_s.présentation.vue.VueVoirUnCourGroupe;

import static java.lang.String.valueOf;

public class VoirListeÉlevesPrésenceActivité  extends AppCompatActivity {

    private static final String EXTRA_CLÉ_CONNEXION = "dti.g25.projet_s.cléConnexion";
    private static final String EXTRA_POSITION_GROUPE = "dti.g25.projet_s.positionCourGroupe";
    private static final String EXTRA_POSITION_SEANCE = "dti.g25.projet_s.positionSeance";

    PrésenteurVoirListeÉlèvesPrésence présenteur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_un_courgroupe);
        Modèle modèle=new Modèle(this);
        VueVoirListeÉlèvesPrésence vue=new VueVoirListeÉlèvesPrésence();
        présenteur= new PrésenteurVoirListeÉlèvesPrésence(this, vue, modèle);
        vue.set_presenteur(présenteur);
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_voir_un_courgroupe, vue);
        ft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        int positionGroupe = getIntent().getIntExtra(EXTRA_POSITION_GROUPE, -1);
        int positionSeance = getIntent().getIntExtra(EXTRA_POSITION_SEANCE, -1);
        String cléUtilisateur = getIntent().getStringExtra(EXTRA_CLÉ_CONNEXION);
        
        try {
            présenteur.commencerListeÉlèvesPrésence(positionSeance, positionGroupe, cléUtilisateur);
        } catch (Exception e) {
            Log.e("ErreurVoirListeÉlèvesP", valueOf(e));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("uneActivité", "Terminer");
        try {
            présenteur.onActivityResult(requestCode, resultCode, data);
        } catch (Exception e) {
            Log.e("Erreur", String.valueOf(e));
        }
    }
}
