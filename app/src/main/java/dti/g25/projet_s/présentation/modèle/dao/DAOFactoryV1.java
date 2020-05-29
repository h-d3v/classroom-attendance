package dti.g25.projet_s.présentation.modèle.dao;

import com.android.volley.Response;

import org.json.JSONObject;

import dti.g25.projet_s.domaine.entité.CoursGroupe;

import dti.g25.projet_s.domaine.entité.Horaire;

import dti.g25.projet_s.domaine.entité.Seance;

import dti.g25.projet_s.domaine.entité.Utilisateur;



import java.util.List;

public  abstract class DAOFactoryV1 {

    public abstract void chargerUtilisateurActuel(Response.Listener onResponse);

    public abstract void chargerUnCourGroupeParId(Response.Listener onResponse, int idCourGroupe);

    public abstract List<DAO<Utilisateur>> chargerListeUtilisateursParCoursGroupe(DAO<CoursGroupe>coursGroupeDAO);

    public abstract List<DAO<Seance>> chargerListeSeanceParCoursGroupe(DAO<CoursGroupe> coursGroupeDAO);

    public abstract DAO<CoursGroupe> chargerCoursGroupeParSeance(DAO<Seance> seanceDAO);

    public abstract List<DAO<Seance>> chargerListeSeanceParUtilisateur(DAO<Utilisateur> utilisateurDAO);

    public abstract List<DAO<Utilisateur>> chargerListeUtilisateurParCoursGroupe(DAO<CoursGroupe> coursGroupeDAO);

    public abstract List<DAO<Utilisateur>> chargerListeUtilisateurParSeance(DAO<Seance> seanceDAO);

    public abstract List<DAO<Horaire>> chargerHoraireParCoursGroupe(DAO<CoursGroupe> coursGroupeDAO);

    public abstract List<DAO<CoursGroupe>> chargerCoursGroupeParHoaire(DAO<Horaire> horaireDAO);

    public abstract String getCle();

    public abstract String tenterConnection(String nomUtilisateur, String motDePasse, Response.ErrorListener errorResponse);

    public abstract void getSeancesParCourGroupe(Response.Listener<JSONObject> response, CoursGroupe courGroupe);

    public abstract void getListeÉlèvesCour(Response.Listener<JSONObject> response, CoursGroupe courGroupe);

    public abstract void prendrePrésence();

    public abstract void obtenirPrésence();

    public abstract void setCle(String cle);

    public abstract void chargerListeCoursGroupeParUtilisateur(Utilisateur utilisateurConnecte, Response.Listener onResponse);
}
