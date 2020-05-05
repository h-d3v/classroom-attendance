package dti.g25.projet_s.présentation.modèle.dao;

import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.entité.Utilisateur;

import java.util.List;

public abstract class DAOFactory {

    public abstract List<CoursGroupe> creerListeCoursGroupeParUtilisateur(Utilisateur utilisateur);

    public abstract Utilisateur getUtilisateur(int i);

    public abstract List<Seance> getListeSeanceParUtilisateur(Utilisateur utilisateur);
}
