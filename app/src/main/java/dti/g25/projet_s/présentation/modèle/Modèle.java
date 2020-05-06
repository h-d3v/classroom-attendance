package dti.g25.projet_s.présentation.modèle;

import android.content.Context;

import dti.g25.projet_s.dao.CourGroupeFactice;
import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.EtatSeance;
import dti.g25.projet_s.domaine.entité.Horaire;
import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.domaine.interacteurs.CréeationUtilisateur;
import dti.g25.projet_s.domaine.interacteurs.GestionSeance;
import dti.g25.projet_s.présentation.modèle.dao.DAOFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Modèle {

    private Utilisateur utilisateur;
    private List<CoursGroupe> coursGroupes;
    private DAOFactory daoFactory;
    private Context context;
    private List<Seance> listeSeance;
    private Utilisateur utilisateurActuelle;

    /**
     * constructeur vide
     */
    public Modèle() {};

    public Modèle(DAOFactory daoFactory, Utilisateur utilisateur) {
        this.utilisateurActuelle = utilisateur;
        this.daoFactory = daoFactory;
        coursGroupes = chargerCoursGroupeUtilisateur();
        chargerSeanceUtilisateur();
    }

    /**
     * Constructeur du modele
     */
    public Modèle(Context context, DAOFactory daoFactory){
        this.context=context;
        this.daoFactory=daoFactory;
    }


    public Utilisateur créerUtilsiateur(String nomUtilisateur, Role role) throws Exception {
        return new CréeationUtilisateur().CréerUtilisateur(nomUtilisateur, role);
    }

    /**
     *
     * @return
     */
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
        return new CourGroupeFactice().ObtenirCourGroupe();
       // return daoFactory.creerListeCoursGroupeParUtilisateur(this.utilisateur);
    }

    public CoursGroupe getCourGroupeParPos(int position){
        return coursGroupes.get(position);
    }

    public void ajouterAbsence(boolean présence, Utilisateur unUtilisateur, int positionSeance) {
        listeSeance.set(positionSeance, (new GestionSeance().ajouterAbsence(unUtilisateur, getSeanceParPos(positionSeance), présence)));
    }

    public Seance créerSéance(int indexGroupe, Horaire horaire) {
        Seance uneSeance = new GestionSeance().creerSeance(getCourGroupeParPos(indexGroupe), horaire);
        listeSeance.add(uneSeance);
        return uneSeance;
    }

    public Seance getSeanceParPos(int positionSeance){
        return listeSeance.get(positionSeance);
    }

    public int getPostionSeance(Seance seance){
        return listeSeance.indexOf(seance);
    }

    public List<Utilisateur> getListUtlisateurParCourGroupe(int positionGroupe){
        return getCourGroupeParPos(positionGroupe).getParticipants();
    }

    public List<Utilisateur> getListeEtudiantsParCoursGroupe(int positionGroupe) {
        List<Utilisateur> listeEtudiants = new ArrayList<>();
        List<Utilisateur> listeParticipant = getCourGroupeParPos(positionGroupe).getParticipants();
        for (Utilisateur unUtilisateur : listeParticipant) {
            if (unUtilisateur.getRôle() == Role.ÉLÈVE)
                listeEtudiants.add(unUtilisateur);
        }

        return listeEtudiants;
    }

    public Utilisateur getUtilisateurConnecte() {
        return utilisateurActuelle;
    }

    public void setEtatSeance(int positionSeance, EtatSeance etatSeance) {
        listeSeance.get(positionSeance).set_etat(etatSeance);
    }

    public void chargerSeanceUtilisateur(){
        listeSeance = new ArrayList<>();
        for(CoursGroupe unCour: coursGroupes ){
            for(Seance uneSeance :  unCour.getListeSeances()){
                listeSeance.add(uneSeance);
            }
        }
    }
}
