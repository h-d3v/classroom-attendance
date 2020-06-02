package dti.g25.projet_s.présentation.modèle.dao;

import com.android.volley.Response;

import org.json.JSONObject;

import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.Utilisateur;

public  abstract class DAOFactoryV1 {

    public abstract void chargerUtilisateurActuel(Response.Listener onResponse);

    public abstract void chargerUnCourGroupeParId(Response.Listener onResponse, int idCourGroupe);

    public abstract void getListeÉlèvesCour(Response.Listener<JSONObject> response, CoursGroupe courGroupe);

    public abstract void obtenirPrésence(Response.Listener<JSONObject> response, int idPrésence);

    public abstract String tenterConnection(String nomUtilisateur, String motDePasse, Response.ErrorListener errorResponse);

    public abstract void getSeancesParCourGroupe(Response.Listener<JSONObject> response, CoursGroupe courGroupe);

    public abstract void setCle(String cle);

    public abstract void chargerListeCoursGroupeParUtilisateur(Utilisateur utilisateurConnecte, Response.Listener onResponse);
}
