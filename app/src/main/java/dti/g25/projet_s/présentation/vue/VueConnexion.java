package dti.g25.projet_s.présentation.vue;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.fragment.app.Fragment;

import dti.g25.projet_s.R;
import dti.g25.projet_s.présentation.ContratVuePrésenteurConnexion;

public class VueConnexion extends Fragment implements ContratVuePrésenteurConnexion.IVueConnexion {

    private ContratVuePrésenteurConnexion.IPrésenteurConnexion présenteur;

    private TextView txtUtilisateur;
    private TextView txtMotDePasse;
    private TextView txtMessageErreur;
    private Button btnConnexion;
    private CheckBox cbSeSouvenir;




    /**
     * Permet d'associer le présenteur a la vue
     * @param présenteur
     */
    public void setPrésenteur(ContratVuePrésenteurConnexion.IPrésenteurConnexion présenteur) {
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
        présenteur.tenterConnectionAutomatique();
        View racine = inflater.inflate(R.layout.fragement_connexion, container, false);

        txtUtilisateur=racine.findViewById(R.id.txtUtilisateur);
        txtMotDePasse=racine.findViewById(R.id.txtMotDePasse);
        btnConnexion = racine.findViewById(R.id.btnConnexion);
        txtMessageErreur = racine.findViewById(R.id.txtMessageErreur);
        cbSeSouvenir = racine.findViewById(R.id.checkBoxSeSouvenir);
        if(!"".equals(présenteur.getNomUtilisateurSauvegarde()) && !"".equals(présenteur.getMotPasseUtilisateurSauvegarde()) ) {
            cbSeSouvenir.setChecked(true);
        }
        txtUtilisateur.setText(présenteur.getNomUtilisateurSauvegarde());
        txtMotDePasse.setText(présenteur.getMotPasseUtilisateurSauvegarde());

        txtUtilisateur.addTextChangedListener(connexionTextWatcher);
        txtMotDePasse.addTextChangedListener(connexionTextWatcher);
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(présenteur.tenterConnexion(getNomUtilisateur(), getMotDePasseUtilisateur())) {

                        if (cbSeSouvenir.isChecked()){
                            présenteur.sauvegarderIdentifiants(getNomUtilisateur(), getMotDePasseUtilisateur());
                        } else {
                            présenteur.supprimerIdentifiants();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return racine;
    }

    private TextWatcher connexionTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            if(getMotDePasseUtilisateur().trim().isEmpty())
                btnConnexion.setEnabled(false);
            else if (getNomUtilisateur().trim().isEmpty())
                btnConnexion.setEnabled(false);
            else
                btnConnexion.setEnabled(true);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(getMotDePasseUtilisateur().trim().isEmpty())
                btnConnexion.setEnabled(false);
            else if (getNomUtilisateur().trim().isEmpty())
                btnConnexion.setEnabled(false);
            else
                btnConnexion.setEnabled(true);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public String getNomUtilisateur() {
        return txtUtilisateur.getText().toString();
    }

    @Override
    public String getMotDePasseUtilisateur() {
        return txtMotDePasse.getText().toString();
    }

    @Override
    public void setMessageErreur(String s) {
        txtMessageErreur.setText(s);
    }

    @Override
    public boolean getCbSeSouvenir() {
        return cbSeSouvenir.isChecked();

    }


}
