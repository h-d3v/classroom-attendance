package dti.g25.projet_s.domaine.interacteurs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import android.os.Build;

import androidx.annotation.RequiresApi;

import dti.g25.projet_s.domaine.entité.Absence;
import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.EtatSeance;
import dti.g25.projet_s.domaine.entité.Horaire;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.entité.Utilisateur;

public class GestionSeance implements IGestionSeance {


    @Override
    public Seance creerSeance(CoursGroupe coursGroupe, Horaire horaires){
      return new Seance(coursGroupe, horaires);
    }

    @Override
    public Seance creerSeance(CoursGroupe coursGroupe, Horaire horaires, Date date, int id) {
        return new Seance (coursGroupe, horaires, date, id);
    }


    @Override
    public Seance changerSatutSeance(EtatSeance etatSeance, Seance seance) {
        seance.set_etat(etatSeance);
        return seance;
    }

    @Override
    public Seance ajouterAbsence(Utilisateur utilisateur, Seance seance, Boolean présence) {
        List<Absence> listeAbsence = seance.getListeAbsence();
        for(int i = 0 ; i < listeAbsence.size(); i++){
            if(listeAbsence.get(i).getUtilisateur().getUsername().equals(utilisateur.getUsername())){
                listeAbsence.set(i, new Absence(utilisateur, présence));
            }  else {
                seance.getListeAbsence().add(new Absence(utilisateur, présence));
            }
        }
        seance.setListeAbsence(listeAbsence);
        return seance;
    }

}
