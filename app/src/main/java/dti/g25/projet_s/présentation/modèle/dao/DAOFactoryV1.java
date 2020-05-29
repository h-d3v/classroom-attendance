package dti.g25.projet_s.présentation.modèle.dao;

import dti.g25.projet_s.domaine.entité.CoursGroupe;

import dti.g25.projet_s.domaine.entité.Horaire;

import dti.g25.projet_s.domaine.entité.Seance;

import dti.g25.projet_s.domaine.entité.Utilisateur;



import java.util.List;

public  abstract class DAOFactoryV1 {

    public abstract List<DAO<CoursGroupe>> chargerListeCoursGroupeParUtilisateur(DAO<Utilisateur> utilisateurDAO);

    public abstract List<DAO<Utilisateur>> chargerListeUtilisateursParCoursGroupe(DAO<CoursGroupe>coursGroupeDAO);

    public abstract List<DAO<Seance>> chargerListeSeanceParCoursGroupe(DAO<CoursGroupe> coursGroupeDAO);

    public abstract DAO<CoursGroupe> chargerCoursGroupeParSeance(DAO<Seance> seanceDAO);

    public abstract List<DAO<Seance>> chargerListeSeanceParUtilisateur(DAO<Utilisateur> utilisateurDAO);

    public abstract List<DAO<Utilisateur>> chargerListeUtilisateurParCoursGroupe(DAO<CoursGroupe> coursGroupeDAO);

    public abstract List<DAO<Utilisateur>> chargerListeUtilisateurParSeance(DAO<Seance> seanceDAO);

    public abstract List<DAO<Horaire>> chargerHoraireParCoursGroupe(DAO<CoursGroupe> coursGroupeDAO);

    public abstract List<DAO<CoursGroupe>> chargerCoursGroupeParHoaire(DAO<Horaire> horaireDAO);
    public abstract String tenterConnection(String nomUtilisateur, String motDePasse);

    public abstract String getCle();

    //To be concluded !! WIP

}
