package dti.g25.projet_s.présentation.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import dti.g25.projet_s.R;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.ui.activité.IContratVoirUneSeance;
import dti.g25.projet_s.ui.activité.VoirSeanceActivity;

public class VueVoirUneSeance extends Fragment implements IContratVoirUneSeance.IVueVoirUneseance {
   IContratVoirUneSeance.IPresenteurVoirUneSeance _presenteur;

   private TextView _tvHoraire;
   private TextView _tvCours;


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
        TextView tvEstPrevue = racine.findViewById(R.id.tv_est_prevu);
        Seance seance=_presenteur.getSeance();
        tvLibelle.setText(seance.get_coursGroupe().toString());
        tvHoraire.setText("Debut:"+seance.get_heureDebut()+" Fin:"+seance.get_heureFin()+" Journee:"+seance.get_journee()
                          +" Semaine:"+seance.get_semaine());
        tvEstPrevue.setText("Statut: "+ seance.get_etat().toString());
        if(!_presenteur.estAutoriseAModifierStatutSeance()) {
            racine.findViewById(R.id.btnModifierStatut).setEnabled(false);
        }
        else{
            racine.findViewById(R.id.btnModifierStatut).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    _presenteur.requeteModifierSatatutSeance();
                    tvEstPrevue.setText("Statut: "+ seance.get_etat().toString());
                }
            });
        }


        return racine;

    }

}
