package dti.g25.projet_s.domaine.interacteurs;

import java.time.LocalDateTime;

import android.os.Build;
import androidx.annotation.RequiresApi;
import dti.g25.projet_s.domaine.entité.*;

public class GestionSeance implements IGestionSeance {


    @Override
    public Seance creerSeance(String dateDebut, String dateFin, String journee, int semaine, CoursGroupe coursGroupe){
      return new Seance(dateDebut, dateFin,journee, semaine,coursGroupe);
    }


    @Override
    public Seance changerSatutSeance(EtatSeance etatSeance, Seance seance) {
        seance.set_etat(etatSeance);
        return seance;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void ajouterAbsence(Utilisateur utilisateur, Seance seance) {
        if(seance.getUtilisateurAbsenceMap() !=null && seance.getUtilisateurAbsenceMap().get(utilisateur)!=null)
       seance.getUtilisateurAbsenceMap().replace(utilisateur, Absence.EST_ABSENT);

    }


}
