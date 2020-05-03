package dti.g25.projet_s.domaine.interacteurs;

import java.time.LocalDateTime;

import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.EtatSeance;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.entité.Utilisateur;

public interface IGestionSeance {
 Seance creerSeance(String dateDebut, String dateFin, String journee, int semaine, CoursGroupe coursGroupe) ;
 Seance changerSatutSeance(EtatSeance etatSeance, Seance seance);
 void ajouterAbsence(Utilisateur utilisateur, Seance seance);
}
