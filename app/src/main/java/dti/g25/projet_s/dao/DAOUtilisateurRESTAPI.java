package dti.g25.projet_s.dao;

import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.présentation.modèle.dao.DAO;


public class DAOUtilisateurRESTAPI implements DAO<Utilisateur> {


    /***
     *
     * @param utilisateur l'objet a creer dans la base de donnee
     * @return t l'objet tel qui l'a ete cree dans la base de donnee, null si non cree
     */
    @Override
    public Utilisateur creer(Utilisateur utilisateur) {
        return null;
    }

    /***
     *
     * @return t l'objet tel qui l'est dans la base de donnee, null si non existant dans la base de donne
     */
    @Override
    public Utilisateur lire() {
        return null;
    }

    /***
     *
     * @param utilisateur l'objet tel qui doit etre modifie
     * @return t l'objet tel qui l'a ete modifie dans la base de donnee, null si l'objet est inexistant dans la BD
     */
    @Override
    public Utilisateur modifier(Utilisateur utilisateur) {
        return null;
    }

    /***
     *
     * @return boolean true si supprime, false sinon
     */
    @Override
    public boolean supprimer() {
        return false;
    }
}
