package dti.g25.projet_s.presentation.modele.daos;

import dti.g25.projet_s.dao.DAO;
import dti.g25.projet_s.domaine.entites.CoursGroupe;
import dti.g25.projet_s.domaine.entites.Utilisateur;

import java.util.List;

public abstract class DAOFactory {
    public abstract List<CoursGroupe> creerListeCoursGroupeParUtilisateur(Utilisateur utilisateur);
}
