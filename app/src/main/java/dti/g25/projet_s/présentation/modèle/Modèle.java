package dti.g25.projet_s.présentation.modèle;

import android.content.Context;

import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.présentation.modèle.dao.DAOFactory;
import java.util.List;

public class Modèle {

    private Utilisateur utilisateur;
    private List<CoursGroupe> coursGroupes;
    private DAOFactory daoFactory;
    private Context context;

    List<Utilisateur> utilisateurs;
    Utilisateur utilisateurActuelle;

    /**
     * constructeur vide
     */
    public Modèle() {};

    public Modèle(DAOFactory daoFactory, Utilisateur utilisateur) {
        this.utilisateur=utilisateur;
        this.daoFactory = daoFactory;
        chargerCoursGroupeUtilisateur();
    }



    public List<CoursGroupe> getCoursGroupes() {
        return coursGroupes;
    }

    public void chargerCoursGroupeUtilisateur(){
        coursGroupes= daoFactory.creerListeCoursGroupeParUtilisateur(this.utilisateur);
    }

    /**
     * permet de chercher un utlisateur par son nom utlisateur et de comparer son mdp et le place en tant qu'utilisateur actuelle
     * @param nomUtlisateur
     * @param motDePasse
     * @return connexion Boolean
     */
    public Boolean connecterUtilisateur(String nomUtlisateur, String motDePasse) {
        Boolean connexion = false;


        for (Utilisateur utilisateur : utilisateurs) {
            if (nomUtlisateur.equals(motDePasse) && utilisateur.getUsername().equals(nomUtlisateur)) {
                connexion = true;
                utilisateurActuelle = utilisateur;
            }
        }

        return connexion;
    }
}
