package dti.g25.projet_s.dao;

import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.Horaire;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.présentation.modèle.dao.DAO;
import dti.g25.projet_s.présentation.modèle.dao.DAOFactoryV1;

import java.util.List;

public class DAOFactoryRESTAPI extends DAOFactoryV1 {
    private final String url="https://projet-s.dti.crosemont.quebec/api/v0/";

    @Override
    public List<DAO<CoursGroupe>> chargerListeCoursGroupeParUtilisateur(DAO<Utilisateur> utilisateurDAO) {
        return null;
    }

    @Override
    public List<DAO<Utilisateur>> chargerListeUtilisateursParCoursGroupe(DAO<CoursGroupe> coursGroupeDAO) {
        return null;
    }

    @Override
    public List<DAO<Seance>> chargerListeSeanceParCoursGroupe(DAO<CoursGroupe> coursGroupeDAO) {
        return null;
    }

    @Override
    public DAO<CoursGroupe> chargerCoursGroupeParSeance(DAO<Seance> seanceDAO) {
        return null;
    }

    @Override
    public List<DAO<Seance>> chargerListeSeanceParUtilisateur(DAO<Utilisateur> utilisateurDAO) {
        return null;
    }

    @Override
    public List<DAO<Utilisateur>> chargerListeUtilisateurParCoursGroupe(DAO<CoursGroupe> coursGroupeDAO) {
        return null;
    }

    @Override
    public List<DAO<Utilisateur>> chargerListeUtilisateurParSeance(DAO<Seance> seanceDAO) {
        return null;
    }

    @Override
    public List<DAO<Horaire>> chargerHoraireParCoursGroupe(DAO<CoursGroupe> coursGroupeDAO) {
        return null;
    }

    @Override
    public List<DAO<CoursGroupe>> chargerCoursGroupeParHoaire(DAO<Horaire> horaireDAO) {
        return null;
    }

    @Override
    public DAO<Utilisateur> tenterConnection(String nomUtilisateur, String motDePasse) {

        return new DAOUtilisateurRESTAPI();
    }
}
