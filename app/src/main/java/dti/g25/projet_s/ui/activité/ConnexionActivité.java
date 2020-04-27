package dti.g25.projet_s.ui.activité;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import dti.g25.projet_s.R;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.présenteur.PrésenteurConnexion;
import dti.g25.projet_s.présentation.vue.VueConnexion;

public class ConnexionActivité extends AppCompatActivity {

    private PrésenteurConnexion présenteur;

    /**
     * Initialise la vue pour l'activité Créer billet ansi que le présenteur
     * pour la vue
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion_activite);

        VueConnexion vue=new VueConnexion();
        Modèle modèle=new Modèle();

        présenteur=new PrésenteurConnexion(this, vue, modèle);
        vue.setPrésenteur(présenteur);

        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.commit();

    }
}
