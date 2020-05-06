package dti.g25.projet_s.domaine.interacteurs;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dti.g25.projet_s.domaine.entité.*;

public interface IGestionSeance {

 Seance creerSeance(CoursGroupe coursGroupe) ;
 Seance changerSatutSeance(EtatSeance etatSeance, Seance seance);
 Seance ajouterAbsence(Utilisateur utilisateur, Seance seance, Boolean présence);

}
