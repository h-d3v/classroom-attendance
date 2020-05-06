package dti.g25.projet_s.ui.activité;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import dti.g25.projet_s.R;
import dti.g25.projet_s.dao.MockDAOFactory;
import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.présentation.ContratVuePrésenteurPrendrePrésence;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.présenteur.PrésenteurConnexion;
import dti.g25.projet_s.présentation.présenteur.PrésenteurPrendrePrésence;
import dti.g25.projet_s.présentation.vue.VueConnexion;
import dti.g25.projet_s.présentation.vue.VuePrendrePrésence;

public class PrendrePrésenceActivité extends AppCompatActivity {

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

        Modèle modèle= null;
        try {
            modèle = new Modèle(new MockDAOFactory(), new Modèle().créerUtilsiateur("Jaque", Role.PROFESSEUR));
        } catch (Exception e) {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, e.toString(), duration);
            toast.show();
        }

        VuePrendrePrésence vue=new VuePrendrePrésence();

        int positionProjet = getIntent().getIntExtra( "EXTRA_POSITION_GROUPE", 1);
        int positionSéance = getIntent().getIntExtra( "EXTRA_POSITION_SÉANCE", 1);
        présenteur=new PrésenteurPrendrePrésence(this, vue, modèle, positionProjet, positionSéance);
        vue.setPrésenteur(présenteur);

        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_prendre_présence, vue);
        ft.commit();

    }

}
