package dti.g25.projet_s.domaine.interacteurs;

import java.time.LocalDateTime;

import dti.g25.projet_s.domaine.entité.*;

public interface IGestionSeance {
 Seance creerSeance(CoursGroupe coursGroupe, LocalDateTime localDateTime) ;
 Seance changerSatutSeance(EtatSeance etatSeance, Seance seance);
 void ajouterAbsence(Utilisateur utilisateur, Seance seance);
}
