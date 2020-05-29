package dti.g25.projet_s.domaine.interacteurs;

import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.LibelleCours;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.domaine.interacteurs.GestionCoursGroupeException;

import java.util.List;

public interface IGestionCoursGroupe  {

    CoursGroupe creerCoursGroupe(LibelleCours libelleCours, int numeroGroupe);
    CoursGroupe creerCoursGroupe(LibelleCours libelleCours, int numeroGroupe, int id);
    void modifierParticipants(List<Utilisateur> participant, CoursGroupe coursGroupe) throws GestionCoursGroupeException;
    void ajouterParticipant(Utilisateur utilisateur, CoursGroupe coursGroupe);
}
