package dti.g25.projet_s.domaine.interacteurs;

import java.time.LocalDateTime;

import android.os.Build;

import androidx.annotation.RequiresApi;

import dti.g25.projet_s.domaine.entité.*;

public class GestionSeance implements IGestionSeance {

    @Override
    public Seance creerSeance(CoursGroupe coursGroupe){
      return new Seance(coursGroupe);
    }


    @Override
    public Seance changerSatutSeance(EtatSeance etatSeance, Seance seance) {
        seance.set_etat(etatSeance);
        return seance;
    }



    @Override
    public Seance ajouterAbsence(Utilisateur utilisateur, Seance seance, Boolean présence) {
        seance.getListeAbsence().add(new Absence(utilisateur, présence));

       return seance;
    }


}
