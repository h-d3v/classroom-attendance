package dti.g25.projet_s.domaine.interacteurs;

import java.time.LocalDateTime;
import java.util.LinkedList;

import dti.g25.projet_s.domaine.entites.Absence;
import dti.g25.projet_s.domaine.entites.CoursGroupe;
import dti.g25.projet_s.domaine.entites.Seance;

public class GestionSeance implements IGestionSeance {

    @Override
    public Seance creerSeance(String etat, CoursGroupe coursGroupe, LinkedList<Absence> listeAbsence, LocalDateTime dateTime){
        Seance sceance=new Seance();
        sceance.set_etat(etat);
        sceance.set_coursGroupe(coursGroupe);
        sceance.set_absenceListe(listeAbsence);
        sceance.set_dateTime(dateTime);
        return sceance;
    }

    @Override
    public Seance changerSatutSeance() {
        return null;
    }
}
