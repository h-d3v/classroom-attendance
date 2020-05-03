package dti.g25.projet_s.domaine.interacteurs;

import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.LibelleCours;
import dti.g25.projet_s.domaine.entité.Utilisateur;

import java.util.List;

public interface IGestionCoursGroupe  {

    CoursGroupe creerCoursGroupe(LibelleCours libelleCours, int numeroGroupe);
    void modifierParticipants(List<Utilisateur> participant, CoursGroupe coursGroupe) throws GestionCoursGroupeException;
    void ajouterParticipant(Utilisateur utilisateur, CoursGroupe coursGroupe);
}
