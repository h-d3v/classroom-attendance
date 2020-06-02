package dti.g25.projet_s.présentation.modèle;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dti.g25.projet_s.dao.ConvertisseurJsonGroupe;
import dti.g25.projet_s.dao.ConvertisseurJsonSeance;
import dti.g25.projet_s.dao.ConvertisseurJsonUtilisateur;
import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.EtatSeance;
import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.entité.Utilisateur;

public class Modèle{

    private String cléConnexion;

    private List<CoursGroupe> coursGroupes;
    private List<Seance> listeSeance;
    private List<Seance> listeSeanceCourGroupe;
    private List<Utilisateur> listeUtilisateurs;

    private Utilisateur utilisateurActuelle;
    private CoursGroupe coursGroupeActuelle;

    /**
     * constructeur vide
     */
    public Modèle() {
        listeUtilisateurs = new ArrayList<>();
    };

    /**
     * Crée un modèle a partir d'une clé de connexion
     * @param uneClé
     */
    public Modèle(String uneClé) {
        cléConnexion = uneClé;
    }

    //Getter

    /**
     *permet d'obtenir tout les cour Groupe
     * @return
     */
    public List<CoursGroupe> getCoursGroupes() {
        return coursGroupes;
    }

    /**
     * permte d'obtenir un cour par sa position
     * @param position
     * @return
     */
    public CoursGroupe getCourGroupeParPos(int position){
        return coursGroupes.get(position);
    }

    /**
     * Permet d'obtenir une seance selon sa position
     * @param positionSeance
     * @return
     */
    public Seance getSeanceParPos(int positionSeance){
        return listeSeance.get(positionSeance);
    }

    /**
     * retourn la lsite des ETUDIANTS d'un cour
     * @return
     */
    public List<Utilisateur> getListeEtudiantsParCoursGroupe() {
        List<Utilisateur> listeEtudiants = new ArrayList<>();
        if(coursGroupeActuelle == null) {
            return null;
        }
        List<Utilisateur> listeParticipant = coursGroupeActuelle.getParticipants();
        for (Utilisateur unUtilisateur : listeParticipant) {
            if (unUtilisateur.getRôle() == Role.ÉLÈVE)
                listeEtudiants.add(unUtilisateur);
        }

        return listeEtudiants;
    }

    /**
     * pertmet d'obtenir l'utilsiateur connecté
     * @return
     */
    public Utilisateur getUtilisateurConnecte() {
        return utilisateurActuelle;
    }

    /**
     * Permet d'obtenir le cour groupe actuelle
     * @return
     */
    public CoursGroupe getCoursGroupeActuelle() { return coursGroupeActuelle; }

    /**
     * Permet d'obtenir la liste des seance d'un Groupe
     * @return
     */
    public List<Seance> getListeSeanceParCourGroupe() {
        return listeSeanceCourGroupe;
    }

    /**
     * permet d'obtenir le rôle d'un utilsaiteur connecté
     * @return
     */
    public Role getRoleUtilsaiteurConnecté() {
        if (utilisateurActuelle != null)
            return utilisateurActuelle.getRôle();
        return Role.ÉLÈVE;
    }


    /**
     * retorune la lsite de sutilsaiteur visible acutelement
     * @return
     */
    public List<Utilisateur> getListeUtilisateurs(){
        return listeUtilisateurs;
    }

    /**
     * Retourne un seance selon sa postion dans un cour groupe
     * @param position
     */
    public Seance getSeanceParCourGroupe( int position) {
        return getListeSeanceParCourGroupe().get(position);
    }

    /**
     * permet d'obtenir la cléde connxion de l'utilisateur
     * @return
     */
    public String getCléConnexion() {
        return cléConnexion;
    }

    /**
     * permet d'obtenir un séeance selon son id
     * @param id
     * @return
     */
    public Seance getSeanceParId(int id) {
        Seance uneSeance = null;
        for (Seance seance: listeSeanceCourGroupe
             ) {
            if(seance.getId() == id);
            uneSeance = seance;
        }

        return uneSeance;
    }

    //setter

    /**
     * permet de mettre la clé d'utliateur
     * @param uneClé
     */
    public void setCléConnexion(String uneClé) {
        cléConnexion = uneClé;
    }

    public void setSeanceParId(Seance seance) {
        for (int i = 0; listeSeanceCourGroupe.size() >  i; i++) {
            if (seance.getId() == listeSeanceCourGroupe.get(i).getId()) {
                listeSeanceCourGroupe.set(i, seance);
            }
        }
    }

    /**
     * Permet de chagner l'êtat d'une séeance
     * @param positionSeance
     * @param etatSeance
     */
    public void setEtatSeance(int positionSeance, EtatSeance etatSeance) {
        listeSeance.get(positionSeance).set_etat(etatSeance);
    }

    //Traitement des Requêtes JSON

    /**
     * Permet de mettre le cour groupe actuelle selon un objet JSON
     * @param réponse
     * @param unCourGroupe
     * @throws JSONException
     */
    public void setJSONSeances(JSONObject réponse, CoursGroupe unCourGroupe) throws JSONException {
        listeSeanceCourGroupe = new ConvertisseurJsonSeance().décoderJsonSéeances(réponse, unCourGroupe);
    }

    /**
     * Met les courgroupe dans un variable selon un json mis en paramêtre
     * @param réponse
     */
    public void setJsonGroupes(JSONObject réponse) {
        coursGroupes = new ConvertisseurJsonGroupe().décoderJsonListeGroupes(réponse);
    }

    /**
     * Permet de mettre le groupe acteulle dans une varaible grace a un objet json mis en parmêtre
     * @param réponse
     * @throws Exception
     */
    public void setJsonGroupeActuelle(JSONObject réponse) throws Exception {
        coursGroupeActuelle = new ConvertisseurJsonGroupe().décoderJsonGroupe(réponse);
    }

    /**
     * permet de mettre l'utilisateur
     * @param réponse
     * @throws Exception
     */
    public void setJsonUtilisateurs(JSONObject réponse) throws Exception {
        listeUtilisateurs = new ConvertisseurJsonUtilisateur().obtenirListeUtilisateurs(réponse);
    }

    /**
     * Permet d'obtenir la liste de présence dans une varaible grace a un objet json mis en parmêtre
     * @param résultat
     * @param idSeance
     * @throws JSONException
     */
    public void setJsonPrésenceSeance(JSONObject résultat, int idSeance) throws JSONException {
        setSeanceParId(new ConvertisseurJsonSeance().présenceSeance(getSeanceParId(idSeance), résultat));
    }

    /**
     *Permet d'obtenir l'utilsiteur actuel dans une varaible grace a un objet json mis en parmêtre
     * @param réponse
     * @throws Exception
     */
    public void setJsonUtilisateurActuelle(JSONObject réponse) throws Exception {
        this.utilisateurActuelle= new ConvertisseurJsonUtilisateur().décoderUtilisateur(réponse);
    }

}
