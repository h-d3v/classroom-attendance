package dti.g25.projet_s.présentation.modèle;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import dti.g25.projet_s.dao.ConvertisseurJsonGroupe;
import dti.g25.projet_s.dao.ConvertisseurJsonSeance;

import dti.g25.projet_s.dao.ConvertisseurJsonUtilisateur;
import dti.g25.projet_s.dao.DAOFactoryRESTAPI;
import dti.g25.projet_s.dao.ServeurFactice;
import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.EtatSeance;
import dti.g25.projet_s.domaine.entité.Horaire;
import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.domaine.interacteurs.GestionSeance;
import dti.g25.projet_s.présentation.modèle.dao.DAOFactory;

import java.util.ArrayList;
import java.util.List;

public class Modèle {

    private Context context;
    private String cléConnexion;

    private List<CoursGroupe> coursGroupes;
    private List<Seance> listeSeance;
    private List<Seance> listeSeanceCourGroupe;
    private List<Utilisateur> listeUtilisateurs;

    private DAOFactoryRESTAPI daoFactoryRESTAPI;

    private Utilisateur utilisateurActuelle;
    private CoursGroupe coursGroupeActuelle;

    /**
     * constructeur vide
     */
    public Modèle() {
        listeUtilisateurs = new ArrayList<>();
    };


    public Modèle(Context context){
        this.context=context;
        daoFactoryRESTAPI = new DAOFactoryRESTAPI(context);
        coursGroupes=new ArrayList<>();
    }

    /**
     * Crée un modèle a partir d'une clé d'utilisateur
     * @param uneClé
     */
    public Modèle(String uneClé) {
        cléConnexion = uneClé;
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



    public void addCoursGroupe(CoursGroupe courGroupe){
        coursGroupes.add(courGroupe);
    }

    public void setJsonUtilisateurActuelle(JSONObject réponse) throws Exception {
        this.utilisateurActuelle= new ConvertisseurJsonUtilisateur().décoderUtilisateur(réponse);
    }

    public List<CoursGroupe> chargerCoursGroupeUtilisateur(){
            return new ServeurFactice().ObtenirCourGroupe(cléConnexion);
       // return daoFactory.creerListeCoursGroupeParUtilisateur(this.utilisateur);
    }


    public void changerEtatSeance(int pos,EtatSeance etatSeance){
        Seance seance=new GestionSeance().changerSatutSeance(etatSeance,listeSeance.get(pos));
        listeSeance.set(pos,seance);
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


    public void ajouterAbsenceParCourGroupe(boolean présence, Utilisateur unUtilisateur, int positionSeance, int postionCourGroupe) throws Exception {

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

    public CoursGroupe getCoursGroupeActuelle() { return coursGroupeActuelle; }


    public void setEtatSeance(int positionSeance, EtatSeance etatSeance) {
        listeSeance.get(positionSeance).set_etat(etatSeance);
    }

    public List<Seance> getListeSeance(){
        return listeSeance;
    }

    public List<Seance> getListeSeanceParCourGroupe(final int position) {

        return listeSeanceCourGroupe;
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
        return listeUtilisateurs;
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
    }

    /**
     * permet de mettre la clé d'utliateur
     * @param uneClé
     */
    public void setCléConnexion(String uneClé) {
        cléConnexion = uneClé;
    }

    /**
     * Retourne un seance selon sa postion dans un cour groupe
     * @param positionCoursGroupe
     * @param position
     */
    public Seance getSeanceParCourGroupe(int positionCoursGroupe, int position) throws Exception {
        return getListeSeanceParCourGroupe(positionCoursGroupe).get(position);
    }

    public void setJSONSeances(JSONObject réponse, CoursGroupe unCourGroupe) throws JSONException {
        listeSeanceCourGroupe = new ConvertisseurJsonSeance().décoderJsonSéeances(réponse, unCourGroupe);
    }

    //Requête Http
    public void requeteHttpSeanceCourGroupe(int idGroupe, Response.Listener<JSONObject> réponse) {
        daoFactoryRESTAPI.getSeancesParCourGroupe(réponse, getCourGroupeParPos(idGroupe));
    }

    public void setJsonGroupes(JSONObject réponse) {
        coursGroupes = new ConvertisseurJsonGroupe().décoderJsonListeGroupes(réponse);
    }

    public void setJsonGroupeActuelle(JSONObject réponse) throws Exception {
        coursGroupeActuelle = new ConvertisseurJsonGroupe().décoderJsonGroupe(réponse);
        Log.d("id : ", String.valueOf(coursGroupeActuelle.getId()));
    }
    
    public void setJsonUtilsaiteurs(JSONObject réponse) throws Exception {
        listeUtilisateurs = new ConvertisseurJsonUtilisateur().obtenirListeUtilisateurs(réponse);
    }
    public void addUtilisateurCoursGroupe(Utilisateur utilisateur) {
        listeUtilisateurs.add(utilisateur);
    }

    public String getCléConnexion() {
        return cléConnexion;
    }

}
