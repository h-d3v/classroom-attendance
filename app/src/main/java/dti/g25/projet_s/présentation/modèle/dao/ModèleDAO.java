package dti.g25.projet_s.présentation.modèle.dao;

import android.content.Context;

import android.util.Log;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import dti.g25.projet_s.dao.Singleton;
import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.EtatSeance;
import dti.g25.projet_s.domaine.entité.Horaire;
import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.domaine.interacteurs.CréeationUtilisateur;
import dti.g25.projet_s.domaine.interacteurs.GestionSeance;
import dti.g25.projet_s.présentation.modèle.dao.DAO;
import dti.g25.projet_s.présentation.modèle.dao.DAOFactory;
import dti.g25.projet_s.présentation.modèle.dao.DAOFactoryV1;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ModèleDAO {

    private List<DAO<CoursGroupe>> coursGroupes;
    private DAOFactoryV1 daoFactory;
    private Context context;
    private List<DAO<Seance>> listeSeance;
    private DAO<Utilisateur> utilisateurActuel;
    private List<DAO<Utilisateur>> listeUtilisateur;
    private String cle;
    private final String URL="https://projet-s.dti.crosemont.quebec/api/v0/utilisateurs";

    /**
     * constructeur vide
     */
    public ModèleDAO() {
    }


    public ModèleDAO(Context context, DAOFactoryV1 daoFactoryV1) {
        this.context = context;
        this.daoFactory=daoFactoryV1;

    }

    /**
     * @param daoFactory  : la factory dao source d'acces aux donnees
     * @param utilisateur : l'utilisateur connecte de l'application
     */
    public ModèleDAO(DAOFactoryV1 daoFactory, DAO<Utilisateur> utilisateur) {
        this.utilisateurActuel = utilisateur;
        this.daoFactory = daoFactory;
        this.coursGroupes = daoFactory.chargerListeCoursGroupeParUtilisateur(utilisateur);
    }


    /**
     * @return coursGroupes la liste des coursGroupes
     */
    public List<DAO<CoursGroupe>> getCoursGroupes() {
        return coursGroupes;
    }


/** L'app ne cree pas de coursGroupe ?
 public void addCoursGroupe(CoursGroupe courGroupe){
 coursGroupes.add(courGroupe);
 }
 */
    /**
     * @param utilisateur l'utilisateur connecte
     */
    public void setUtilisateur(DAO<Utilisateur> utilisateur) {
        this.utilisateurActuel = utilisateur;
    }

    public List<DAO<CoursGroupe>> chargerCoursGroupeUtilisateur() {
        coursGroupes = daoFactory.chargerListeCoursGroupeParUtilisateur(this.utilisateurActuel);
        return coursGroupes;
    }



    /**
     * @param position: la position du coursGroupe dans la liste coursGroupe du modele
     * @return null ou le coursGroupe a la position entree en parametre
     */
    public DAO<CoursGroupe> getCourGroupeParPos(int position) {
        if (coursGroupes == null || coursGroupes.size() == 0 || coursGroupes.size() < position) {
            return null;
        }
        return coursGroupes.get(position);
    }

    public void ajouterAbsence(boolean présence, DAO<Utilisateur> unUtilisateur, int positionSeance) {
        Seance seanceModifiee = new GestionSeance().ajouterAbsence(unUtilisateur.lire(), listeSeance.get(positionSeance).lire(), présence);
        listeSeance.get(positionSeance).modifier(seanceModifiee);
    }



    public DAO<Seance> getSeanceParPos(int positionSeance) {
        return listeSeance.get(positionSeance);
    }





    public List<DAO<Utilisateur>> getListDAOUtlisateurParCourGroupe(int positionGroupe) {

        List<DAO<Utilisateur>> listeUtilisateurParCoursGroupe = daoFactory.chargerListeUtilisateurParCoursGroupe(coursGroupes.get(positionGroupe));

        return listeUtilisateurParCoursGroupe;

    }

    /**
     * @param positionGroupe : la position du coursGroupe dans la liste coursGroupe du modele
     * @return List<DAO < Utilisateur>> : la liste des Etudiants associes au coursGroupe entre en parametre
     * null si aucun etudiant ou aucun coursGroupe trouve
     */
    public List<DAO<Utilisateur>> getListeEtudiantsParCoursGroupe(int positionGroupe) {
        List<DAO<Utilisateur>> listeParticipants = getListDAOUtlisateurParCourGroupe(positionGroupe);
        if (listeParticipants == null) {
            return null;
        }
        List<DAO<Utilisateur>> listeEtudiants = listeParticipants;
        for (DAO<Utilisateur> unUtilisateur : listeParticipants) {
            if (unUtilisateur.lire().getRôle() != Role.ÉLÈVE)
                listeEtudiants.remove(unUtilisateur);
        }
        if (listeEtudiants.size() == 0) {
            return null;
        }

        return listeEtudiants;
    }

    public DAO<Utilisateur> getUtilisateurConnecte() {
        return utilisateurActuel;
    }

    public void setEtatSeance(int positionSeance, EtatSeance etatSeance) {
        Seance seanceDAOModifiee = new GestionSeance().changerSatutSeance(etatSeance, listeSeance.get(positionSeance).lire());
        listeSeance.get(positionSeance).modifier(seanceDAOModifiee);
    }

    public void changerEtatSeance(int posSeance, EtatSeance etatSeance) {
        // TODO ? Pas utilise listeSeance.get(pos).modifier(new Seance(null, null)); REDONDANT voir ci dessus
    }

    public List<DAO<Seance>> getListeSeance() {
        return listeSeance;
    }

    public void chargerSeanceUtilisateur() {
        listeSeance=daoFactory.chargerListeSeanceParUtilisateur(utilisateurActuel);

    }




    //TODO ? non utilisee
    public int getPostionSeance(Seance seance) {
        return listeSeance.indexOf(seance);
    }


    //TODO ??
    public DAO<Utilisateur> getUtilisateurParIndex(int index){

        if(listeUtilisateur==null||listeUtilisateur.size()==0|| listeUtilisateur.size()<index){
            return null;
        }

        return listeUtilisateur.get(index);
    }

    //TODO ??? Jamais utilisee
    public Seance créerSéance(int indexGroupe, Horaire horaire) {
        /**Seance uneSeance = new GestionSeance().creerSeance(getCourGroupeParPos(indexGroupe), horaire);
         listeSeance.add(uneSeance);
         return uneSeance;*/
        throw new UnsupportedOperationException();

    }

    public void setCléUtilisateur(String cléConnexion) {
        cle=cléConnexion;
    }

/** //TODO? supprimer fonction puisque l'app ne cree pas d'utilisateur
 *
 * @param
 * @param
 * @return
 * @throws Exception

public Utilisateur créerUtilsiateur(String nomUtilisateur, Role role) throws Exception {
return new CréeationUtilisateur().CréerUtilisateur(nomUtilisateur, role);
}


*/


}
