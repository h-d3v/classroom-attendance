package dti.g25.projet_s.domaine.interacteurs;

import java.time.LocalDateTime;
import java.util.LinkedList;

import dti.g25.projet_s.domaine.entites.Absence;
import dti.g25.projet_s.domaine.entites.CoursGroupe;
import dti.g25.projet_s.domaine.entites.Sceance;

public class GestionSceance implements IGestionSceance {

    @Override
    public Sceance creerSceance(String etat, CoursGroupe coursGroupe, LinkedList<Absence> listeAbsence, LocalDateTime dateTime){
        Sceance sceance=new Sceance();
        sceance.set_etat(etat);
        sceance.set_coursGroupe(coursGroupe);
        sceance.set_absenceListe(listeAbsence);
        sceance.set_dateTime(dateTime);
        return sceance;
    }

    @Override
    public Sceance changerSatutSceance() {
        return null;
    }
}
