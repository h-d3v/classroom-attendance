package dti.g25.projet_s.domaine.interacteurs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import android.os.Build;

import androidx.annotation.RequiresApi;

import dti.g25.projet_s.domaine.entité.*;

public class GestionSeance implements IGestionSeance {


    @Override
    public Seance creerSeance(CoursGroupe coursGroupe, Horaire horaires){
      return new Seance(coursGroupe, horaires);
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
            if(listeAbsence.get(i).getUtilisateur().equals(utilisateur)){
                listeAbsence.set(i, new Absence(utilisateur, présence));
            }  else {
                seance.getListeAbsence().add(new Absence(utilisateur, présence));
            }
        }
        seance.setListeAbsence(listeAbsence);
        return seance;
    }


}
