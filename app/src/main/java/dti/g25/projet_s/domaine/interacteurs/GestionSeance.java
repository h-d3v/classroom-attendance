package dti.g25.projet_s.domaine.interacteurs;

import java.time.LocalDateTime;


import android.os.Build;
import androidx.annotation.RequiresApi;

import dti.g25.projet_s.domaine.entité.Absence;
import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.EtatSeance;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.entité.Utilisateur;

public class GestionSeance implements IGestionSeance {

    @Override
    public Seance creerSeance(CoursGroupe coursGroupe, LocalDateTime dateTime){
      return new Seance(coursGroupe,dateTime);
    }


    @Override
    public Seance changerSatutSeance(EtatSeance etatSeance, Seance seance) {
        seance.set_etat(etatSeance);
        return seance;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void ajouterAbsence(Utilisateur utilisateur, Seance seance) {
       seance.getUtilisateurAbsenceMap().replace(utilisateur, Absence.EST_ABSENT);

    }


}
