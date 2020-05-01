package dti.g25.projet_s.domaine.interacteurs;

import java.time.LocalDateTime;

import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.EtatSeance;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.entité.Utilisateur;

public interface IGestionSeance {
 Seance creerSeance(CoursGroupe coursGroupe, LocalDateTime localDateTime) ;
 Seance changerSatutSeance(EtatSeance etatSeance, Seance seance);
 void ajouterAbsence(Utilisateur utilisateur, Seance seance);
}
