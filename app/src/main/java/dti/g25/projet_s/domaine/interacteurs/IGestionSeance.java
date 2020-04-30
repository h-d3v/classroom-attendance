package dti.g25.projet_s.domaine.interacteurs;

import java.time.LocalDateTime;
import java.util.LinkedList;

import dti.g25.projet_s.domaine.entites.Absence;
import dti.g25.projet_s.domaine.entites.CoursGroupe;
import dti.g25.projet_s.domaine.entites.Seance;

public interface IGestionSeance {
 Seance creerSeance(String statut, CoursGroupe coursGroupe, LinkedList<Absence> listeAbsence, LocalDateTime dateTime) ;
 Seance changerSatutSeance();
}
