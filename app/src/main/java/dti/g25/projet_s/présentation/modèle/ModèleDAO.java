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
import dti.g25.projet_s.présentation.modèle.dao.DAO;
import dti.g25.projet_s.présentation.modèle.dao.DAOFactory;
import dti.g25.projet_s.présentation.modèle.dao.DAOFactoryV1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ModèleDAO {


    private DAO<Utilisateur> utilisateur;
    private List<DAO<CoursGroupe>> coursGroupes;
    private DAOFactoryV1 daoFactory;
    private Context context;
    private List<DAO<Seance>> listeSeance;
    private DAO<Utilisateur> utilisateurActuel;
    private List<DAO<Utilisateur>> listeUtilisateur;

    /**
     * constructeur vide
     */
    public ModèleDAO() {};

    public ModèleDAO(DAOFactoryV1 daoFactory, DAO<Utilisateur> utilisateur) {
        this.utilisateurActuel = utilisateur;
        this.daoFactory = daoFactory;
        this.coursGroupes= daoFactory.chargerListeCoursGroupeParUtilisateur(utilisateur);
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


    /**
     *
     * @return
     */
    public List<DAO<CoursGroupe>> getCoursGroupes() {
        return coursGroupes;
    }


    public ModèleDAO(Context context){
        this.context=context;
        coursGroupes=new LinkedList<>();
    }
/**
    public void addCoursGroupe(CoursGroupe courGroupe){
        coursGroupes.add(courGroupe);
    }
 */

    public void setUtilisateur(DAO<Utilisateur> utilisateur){
        this.utilisateur=utilisateur;
    }

    public List<DAO<CoursGroupe>> chargerCoursGroupeUtilisateur(){

        return daoFactory.chargerListeCoursGroupeParUtilisateur(this.utilisateur);
    }

    public void changerEtatSeance(int pos,EtatSeance etatSeance){
        // TODO ? Pas utilise listeSeance.get(pos).modifier(new Seance(null, null));
    }

    public DAO<CoursGroupe> getCourGroupeParPos(int position){
        return coursGroupes.get(position);
    }

    public void ajouterAbsence(boolean présence, DAO<Utilisateur> unUtilisateur, int positionSeance) {
        Seance seanceModifiee= new GestionSeance().ajouterAbsence(unUtilisateur.lire(),listeSeance.get(positionSeance).lire(), présence);
        listeSeance.get(positionSeance).modifier(seanceModifiee);
    }
 //TODO ??? Jamais utilisee
    public Seance créerSéance(int indexGroupe, Horaire horaire) {
        /**Seance uneSeance = new GestionSeance().creerSeance(getCourGroupeParPos(indexGroupe), horaire);
        listeSeance.add(uneSeance);
        return uneSeance;*/
        throw new UnsupportedOperationException();

    }

    public DAO<Seance> getSeanceParPos(int positionSeance){
        return listeSeance.get(positionSeance);
    }
    //TODO ? non utilisee
    public int getPostionSeance(Seance seance){
        return listeSeance.indexOf(seance);
    }

    public List<DAO<Utilisateur>> getListDAOUtlisateurParCourGroupe(int positionGroupe){
        //TODO ?? Jamais utilisee return getCourGroupeParPos(positionGroupe).getParticipants();
        throw new UnsupportedOperationException();
    }

    public List<DAO<Utilisateur>> getListeEtudiantsParCoursGroupe(int positionGroupe) {
        List<DAO<Utilisateur>> listeUtilisateurParCoursGroupe= daoFactory.chargerListeUtilisateurParCoursGroupe(coursGroupes.get(positionGroupe));
        if(listeUtilisateurParCoursGroupe.size()==0||listeUtilisateurParCoursGroupe==null){
            return null;
        }
        return listeUtilisateurParCoursGroupe;
    }

    public DAO<Utilisateur> getUtilisateurConnecte() {
        return utilisateurActuel;
    }

    public void setEtatSeance(int positionSeance, EtatSeance etatSeance) {
        Seance seanceDAOModifiee = new GestionSeance().changerSatutSeance(etatSeance, listeSeance.get(positionSeance).lire());
        listeSeance.get(positionSeance).modifier(seanceDAOModifiee);
    }

    public List<DAO<Seance>> getListeSeance(){
        return listeSeance;
    }

    public void chargerSeanceUtilisateur() {

        daoFactory.chargerListeSeanceParUtilisateur(utilisateurActuel);
    }

    public List<DAO<Utilisateur>> getListeUtilisateur(){
        return listeUtilisateur;
    }

    public DAO<Utilisateur> getUtilisateurParIndex(int index){
        List<DAO<Utilisateur>> tousLesUsers = getListeUtilisateur();
        if(tousLesUsers.size()==0||tousLesUsers==null){
            return null;
        }

        return tousLesUsers.get(index);
    }
}
