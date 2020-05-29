package dti.g25.projet_s.présentation.modèle;

import android.content.Context;

import com.android.volley.Response;

import org.json.JSONObject;

import dti.g25.projet_s.dao.DAOFactoryRESTAPI;
import dti.g25.projet_s.dao.ServeurFactice;
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
import java.util.List;

public class Modèle {

    private String cléUtilisateur;
    private Utilisateur utilisateur;
    private List<CoursGroupe> coursGroupes;
    private List<Seance> seances;
    private DAOFactory daoFactory;
    private Context context;
    private List<Seance> listeSeance;
    private List<Seance> listeSeanceCourGroupe;
    private Utilisateur utilisateurActuelle;
    private List<Utilisateur> listeUtilisateur;

    /**
     * constructeur vide
     */
    public Modèle() {};

    public Modèle(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        coursGroupes = chargerCoursGroupeUtilisateur();
        chargerSeanceUtilisateur();
    }

    /**
     * Crée un modèle a partir d'une clé d'utilisateur
     * @param uneClé
     */
    public Modèle(String uneClé) {
        cléUtilisateur = uneClé;
        coursGroupes = chargerCoursGroupeUtilisateur();
        chargerSeanceUtilisateur();
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
        coursGroupes=new ArrayList<>();
    }

    public void addCoursGroupe(CoursGroupe courGroupe){
        coursGroupes.add(courGroupe);
    }

    public void setUtilisateur(Utilisateur utilisateur){
        this.utilisateur=utilisateur;
    }

    public List<CoursGroupe> chargerCoursGroupeUtilisateur(){
            return new ServeurFactice().ObtenirCourGroupe(cléUtilisateur);
       // return daoFactory.creerListeCoursGroupeParUtilisateur(this.utilisateur);
    }

    /**
     * charge les info de l'utilasateur
     */
    public void chargerInfoUtilisateur() throws Exception {
        utilisateurActuelle =  new ServeurFactice().chargerInfoUtilisateur(cléUtilisateur);
    }

    public void changerEtatSeance(int pos,EtatSeance etatSeance){
        Seance seance=new GestionSeance().changerSatutSeance(etatSeance,seances.get(pos));
        seances.set(pos,seance);
    }

    public CoursGroupe getCourGroupeParPos(int position){
        return coursGroupes.get(position);
    }

    /**
     * Ajoute un absence selon un élèves
     * @param présence
     * @param unUtilisateur
     * @param positionSeance
     */
    public void ajouterAbsence(boolean présence, Utilisateur unUtilisateur, int positionSeance) {
        listeSeance.set(positionSeance, (new GestionSeance().ajouterAbsence(unUtilisateur, getSeanceParPos(positionSeance), présence)));
    }


    public void ajouterAbsenceParCourGroupe(boolean présence, Utilisateur unUtilisateur, int positionSeance, int postionCourGroupe) {
        getListeSeanceParCourGroupe(postionCourGroupe).set(positionSeance, (new GestionSeance().ajouterAbsence(unUtilisateur, getSeanceParPos(positionSeance), présence)));
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

    /**
     * retourn la lsite des ETUDIANTS d'un cour
     * @param positionGroupe
     * @return
     */
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

    public List<Seance> getListeSeance(){
        return listeSeance;
    }

    public List<Seance> getListeSeanceParCourGroupe(int position) {
        return getCourGroupeParPos(position).getListeSeances();
    }


    /**
     * charge les utilsiateur de une seance
     */
    public void chargerSeanceUtilisateur() {
        listeSeance = new ArrayList<>();
        if(coursGroupes != null) {
            for (CoursGroupe unCour : coursGroupes) {
                for (Seance uneSeance : unCour.getListeSeances()) {
                    listeSeance.add(uneSeance);
                }
            }
        }
    }

    public Role getRoleUtilsaiteurConnecté() {
        if (utilisateurActuelle != null)
            return utilisateurActuelle.getRôle();
        return Role.ÉLÈVE;
    }


    public List<Utilisateur> getListeUtilisateur(){
        return listeUtilisateur;
    }

    /**
     * retourne un utilsaiteur
     * @param index
     * @return
     */
    public Utilisateur getUtilisateurParIndex(int index){
        List<Utilisateur> tousLesUsers = getListeUtilisateur();
        return tousLesUsers.get(index);
    }

    /**
     * rafrachi les donné du modèle
     */
    public void rafraîchir() throws Exception {
        coursGroupes = chargerCoursGroupeUtilisateur();
        chargerSeanceUtilisateur();
        chargerInfoUtilisateur();
    }

    /**
     * permet de mettre la clé d'utliateur
     * @param uneClé
     */
    public void setCléUtilisateur(String uneClé) {
        cléUtilisateur = uneClé;
    }

    /**
     * Retourne un seance selon sa postion dans un cour groupe
     * @param positionCoursGroupe
     * @param position
     */
    public Seance getSeanceParCourGroupe(int positionCoursGroupe, int position) {
        return getListeSeanceParCourGroupe(positionCoursGroupe).get(position);
    }

    //Requête Http
    public getUtilisateursHttp(int positionGroupe, Response.Listener<JSONObject> réponse) {
        new DAOFactoryRESTAPI()
    }
}
