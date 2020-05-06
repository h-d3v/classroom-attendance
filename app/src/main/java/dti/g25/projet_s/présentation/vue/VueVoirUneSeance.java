package dti.g25.projet_s.présentation.vue;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import dti.g25.projet_s.R;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.ui.activité.IContratVoirUneSeance;

public class VueVoirUneSeance extends Fragment implements IContratVoirUneSeance.IVueVoirUneseance {
   IContratVoirUneSeance.IPresenteurVoirUneSeance _presenteur;

   private TextView _tvHoraire;
   private TextView _tvCours;
   private AlertDialog.Builder builder;




    @Override
    public void rafraichir() {

    }

    @Override
    public void setPresenteur(IContratVoirUneSeance.IPresenteurVoirUneSeance presenteurVoirUneSeance) {
        _presenteur=presenteurVoirUneSeance;
    }
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState){
        View racine=inflater.inflate(R.layout.frag_voir_une_seance, container, false);
        TextView tvHoraire = racine.findViewById( R.id.tv_horaire_cours);
        TextView tvLibelle = racine.findViewById(R.id.tv_detail_cours);
        final TextView tvEstPrevue = racine.findViewById(R.id.tv_est_prevu);
        Seance seance=_presenteur.getSeance();
        tvLibelle.setText(seance.get_coursGroupe().toString());
        tvHoraire.setText("Debut:"+seance.get_horaires().getHeureDebut()+" Fin:"+seance.get_horaires().getHeureDebut()+" Journee:"+seance.get_horaires().getJournee());
        tvEstPrevue.setText("Statut: "+ seance.get_etat().toString());
        if(!_presenteur.estAutoriseAModifierStatutSeance()) {
            racine.findViewById(R.id.btnModifierStatut).setEnabled(false);
        }
        else {
            builder = new AlertDialog.Builder(_presenteur.get_activite());

            racine.findViewById(R.id.btnModifierStatut).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    builder.setMessage("Souhaitez-vous vraiment modifier l'etat de la seance?")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    _presenteur.requeteModifierSatatutSeance();
                                    tvEstPrevue.setText(_presenteur.getSeance().get_etat().toString());
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

        }


        return racine;

    }



}
