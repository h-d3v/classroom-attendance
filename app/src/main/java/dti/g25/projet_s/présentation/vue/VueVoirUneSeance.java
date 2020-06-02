package dti.g25.projet_s.présentation.vue;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import com.beardedhen.androidbootstrap.BootstrapButton;

import dti.g25.projet_s.R;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.présentation.IContratVoirUneSeance;

public class VueVoirUneSeance extends Fragment implements IContratVoirUneSeance.IVueVoirUneseance {
   IContratVoirUneSeance.IPresenteurVoirUneSeance _presenteur;

   private TextView _tvHoraire;
   private TextView _tvCours;
   private TextView _tvLibelle;
   private TextView _tvEstPrevue;
   private BootstrapButton _btnModifierStatut ;
   private AlertDialog.Builder builder;



    @Override
    public void setPresenteur(IContratVoirUneSeance.IPresenteurVoirUneSeance presenteurVoirUneSeance) {
        _presenteur=presenteurVoirUneSeance;
    }
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState){
        View racine=inflater.inflate(R.layout.frag_voir_une_seance, container, false);
        _tvLibelle = racine.findViewById(R.id.tv_detail_cours);
        _tvEstPrevue = racine.findViewById(R.id.tv_est_prevu);
        _tvHoraire = racine.findViewById(R.id.tv_horaire_cours);
        _btnModifierStatut =  racine.findViewById(R.id.btnModifierStatut);
        builder = new AlertDialog.Builder(_presenteur.get_activite());

        _btnModifierStatut.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    builder.setMessage("Souhaitez-vous vraiment modifier l'etat de la seance?")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    try {
                                        _presenteur.requeteModifierSatatutSeance();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        _tvEstPrevue.setText(_presenteur.getSeance().get_etat().toString());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    Toast.makeText(_presenteur.get_activite(), "Statut de la seance modifiee",
                                            Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    Toast.makeText(_presenteur.get_activite(), "Statut de la seance non modifiee",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });

                    AlertDialog alert = builder.create();

                    alert.setTitle("Modification du statut de la seance");
                    alert.show();
                }
            });

        return racine;

    }

    @Override
    public void afficherHoraire(String unString){
        _tvHoraire.setText(unString);
    }

    @Override
    public void afficherLibelle(String unString) {
        _tvLibelle.setText(unString);
    }

    @Override
    public void afficherEstPrévue(String unString) {
        _tvEstPrevue.setText(unString);
    }

    @Override
    public void autoriserProf(boolean b) {
        _btnModifierStatut.setEnabled(b);
        if(b)
            _btnModifierStatut.setVisibility(View.VISIBLE);
        else
            _btnModifierStatut.setVisibility(View.INVISIBLE);
    }

}
