package dti.g25.projet_s.présentation.vue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import dti.g25.projet_s.R;
import dti.g25.projet_s.présentation.ContratVuePrésenteurPrendrePrésence;

public class VuePrendrePrésence extends Fragment implements ContratVuePrésenteurPrendrePrésence.IVuePrendrePrésence {

    private ContratVuePrésenteurPrendrePrésence.IPrésenteurPrendrePrésence présenteur;

    private TextView txtNomÉtudiant;
    private Button btnPrésent;
    private Button btnAbsent;

    /**
     * Permet d'associer le présenteur a la vue
     * @param présenteur
     */
    public void setPrésenteur(ContratVuePrésenteurPrendrePrésence.IPrésenteurPrendrePrésence présenteur) {
        this.présenteur=présenteur;
    }

    /**
     * Créer la vue lorsqu'on lane l'app et la retourn en paramêtre
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        View racine = inflater.inflate(R.layout.fragement_connexion, container, false);

        txtNomÉtudiant=racine.findViewById(R.id.txtUtilisateur);
        btnAbsent = racine.findViewById(R.id.btnAbsent);
        btnPrésent = racine.findViewById(R.id.btnPrésent);
        btnPrésent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    présenteur.ajouterAbsence(true);

            }
        });
        btnAbsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                présenteur.ajouterAbsence(false);

            }
        });

        return racine;
    }

    @Override
    public void setTxtNomÉtudiant(String nomÉtudiant){
        txtNomÉtudiant.setText(nomÉtudiant);
    }

}
