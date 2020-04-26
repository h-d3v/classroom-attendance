package dti.g25.projet_s.domaine.interacteurs;

import dti.g25.projet_s.domaine.entites.CoursGroupe;
import dti.g25.projet_s.domaine.entites.LibelleCours;
import dti.g25.projet_s.domaine.entites.Utilisateur;

import java.util.List;

public interface IGestionCoursGroupe  {

    CoursGroupe creerCoursGroupe(LibelleCours libelleCours, int numeroGroupe);
    void modifierParticipants(List<Utilisateur> participant, CoursGroupe coursGroupe) throws GestionCoursGroupeException;
}
