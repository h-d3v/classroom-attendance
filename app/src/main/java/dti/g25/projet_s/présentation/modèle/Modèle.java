package dti.g25.projet_s.présentation.modèle;

import android.content.Context;

import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.présentation.modèle.dao.DAOFactory;

import java.util.LinkedList;
import java.util.List;

public class Modèle {

    private Utilisateur utilisateur;
    private List<CoursGroupe> coursGroupes;
    private DAOFactory daoFactory;
    private Context context;
    private List<Seance> seances;

    List<Utilisateur> utilisateurs;
    Utilisateur utilisateurActuelle;

    /**
     * constructeur vide
     */
    public Modèle() {};

    public Modèle(DAOFactory daoFactory, Utilisateur utilisateur) {
        this.utilisateurActuelle = utilisateur;
        this.daoFactory = daoFactory;
        coursGroupes = chargerCoursGroupeUtilisateur();

    }

    /**
     * Constructeur du modele
     */
    public Modèle(Context context, DAOFactory daoFactory){
        this.context=context;
        this.daoFactory=daoFactory;
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

    public List<CoursGroupe> getCoursGroupes() {
        return coursGroupes;
    }

    public Modèle(Context context){
        this.context=context;
        coursGroupes=new LinkedList<>();
    }

    public void addCoursGroupe(CoursGroupe courGroupe){
        coursGroupes.add(courGroupe);
    }

    public void setUtilisateur(Utilisateur utilisateur){
        this.utilisateur=utilisateur;
    }

    public List<CoursGroupe> chargerCoursGroupeUtilisateur(){
        return daoFactory.creerListeCoursGroupeParUtilisateur(this.utilisateur);
    }

    public CoursGroupe getCourGroupeParPos(int position){
        return coursGroupes.get(position);
    }

    public Utilisateur getUtilisateurConnecte() {
        return utilisateurActuelle;
    }

    public List<Seance> chargerSeancesPaerUtilisateur(int cle){
        seances=daoFactory.creerListeSeanceParUtilisateur(cle);
        return daoFactory.creerListeSeanceParUtilisateur(cle);
    }

    public List<Seance> getSeances(){
        return seances;
    }



    public Seance getSeance(int positionSeance) {
        if(seances!=null){
            return seances.get(positionSeance);
        }else return null;
    }
}
