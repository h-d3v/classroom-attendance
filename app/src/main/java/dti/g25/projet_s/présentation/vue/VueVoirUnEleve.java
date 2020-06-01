package dti.g25.projet_s.présentation.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import dti.g25.projet_s.R;
import dti.g25.projet_s.présentation.IContratVuePrésenteurVoirUnEleve;

public class VueVoirUnEleve extends Fragment implements IContratVuePrésenteurVoirUnEleve.IVueVoirUnEleve, SingleChoiceDialogHeuresAbsenceFragment.SingleChoiceListener {

    private IContratVuePrésenteurVoirUnEleve.IPrésenteurVoirUnEleve présenteur;
    private TextView tvNomEleve, tvDetailsEleve;
    private ImageView imgEleve;
    private Button btnConfirmer, btnAbsence;
    private int nbHeures=0;
    private int position;


    public void setPresenteur(IContratVuePrésenteurVoirUnEleve.IPrésenteurVoirUnEleve présenteur) {
        this.présenteur = présenteur;
    }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        View racine = inflater.inflate(R.layout.frag_voir_eleve_absent, container, false);

        Intent intent = getActivity().getIntent();
        position = intent.getIntExtra("EXTRA_POSITION_ÉLÈVES",0);

        tvNomEleve = racine.findViewById(R.id.tvNomEleve);
        tvDetailsEleve = racine.findViewById(R.id.tvDetailsEleve);
        imgEleve = racine.findViewById(R.id.imgEleve);
        btnConfirmer = racine.findViewById(R.id.btnConfirmer);
        btnAbsence = racine.findViewById(R.id.btnAbsence);
        tvDetailsEleve.setText("la desc de l'eleve");

        //TODO Photo des étudiants à implementer
        btnAbsence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    public void setVisibilitéPrésence(boolean b) {
        btnConfirmer.setEnabled(b);
        btnAbsence.setEnabled(b);
        if(!b){
            btnAbsence.setVisibility(View.INVISIBLE);
            btnConfirmer.setVisibility(View.INVISIBLE);
        }
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

    @Override
    public void setUsername(String username){
        tvNomEleve.setText(username);
    }

}
