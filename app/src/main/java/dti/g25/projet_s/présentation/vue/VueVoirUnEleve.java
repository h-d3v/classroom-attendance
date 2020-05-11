package dti.g25.projet_s.présentation.vue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import dti.g25.projet_s.R;
import dti.g25.projet_s.dao.MockDAOFactory;
import dti.g25.projet_s.domaine.entité.Absence;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.présentation.IContratVuePrésenteurVoirUnEleve;
import dti.g25.projet_s.présentation.modèle.Modèle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class VueVoirUnEleve extends Fragment implements IContratVuePrésenteurVoirUnEleve.IVueVoirUnEleve, SingleChoiceDialogHeuresAbsenceFragment.SingleChoiceListener {

    private IContratVuePrésenteurVoirUnEleve.IPrésenteurVoirUnEleve présenteur;
    private TextView tvNomEleve, tvDetailsEleve;
    private ImageView imgEleve;
    private Button btnConfirmer, btnAbsence;
    private int nbHeures=0;


    public void setPresenteur(IContratVuePrésenteurVoirUnEleve.IPrésenteurVoirUnEleve présenteur) {
        this.présenteur = présenteur;
    }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        View racine = inflater.inflate(R.layout.frag_voir_eleve_absent, container, false);

        MockDAOFactory mockDAOFactory = new MockDAOFactory();
        Modèle modèle = new Modèle(mockDAOFactory);
        final Utilisateur leUser = mockDAOFactory.getUtilisateur(0);

        tvNomEleve = racine.findViewById(R.id.tvNomEleve);
        tvDetailsEleve = racine.findViewById(R.id.tvDetailsEleve);
        imgEleve = racine.findViewById(R.id.imgEleve);
        btnConfirmer = racine.findViewById(R.id.btnConfirmer);
        btnAbsence = racine.findViewById(R.id.btnAbsence);
        tvNomEleve.setText(leUser.getUsername());
        //imgEleve.setImageResource(); -- TODO Photo des étudiants à implementer
        btnAbsence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DialogFragment singleChoiceDialog = new SingleChoiceDialogHeuresAbsenceFragment();
//                singleChoiceDialog.setCancelable(false);
//                singleChoiceDialog.show(getFragmentManager(), "Faire un choix");
                try {
                    présenteur.requêteAjouterAbsence(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btnConfirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment singleChoiceDialog = new SingleChoiceDialogHeuresAbsenceFragment();
                singleChoiceDialog.setCancelable(false);
                try {
                    présenteur.requêteAjouterAbsence(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return racine;
    }

    @Override
    public String getNomUtilisateur() {
        return null;
    }

    @Override
    public void setNomUtilisateur(String unString) {
        tvNomEleve.setText(unString);
    }
    @Override
    public void onClickPositif(String[] list, int position) {
        nbHeures = Integer.parseInt(list[position]);
    }

    @Override
    public void onClickNegatif() {
        Toast.makeText(getActivity(), "Action annulée",
                Toast.LENGTH_LONG).show();
    }

}
