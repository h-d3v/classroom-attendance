package dti.g25.projet_s.dao;

import dti.g25.projet_s.domaine.entité.CoursGroupe;
import java.util.Map;

public class MockDAOCoursGroupe implements DAO<CoursGroupe> {


    public MockDAOCoursGroupe(int position){

    }
    /***
     *
     * @param coursGroupe l'objet a creer dans la base de donnee
     * @return t l'objet tel qui l'a ete cree dans la base de donnee, null si non cree
     */
    @Override
    public CoursGroupe creer(CoursGroupe coursGroupe) {
        throw new  UnsupportedOperationException();
    }

    /***
     *
     * @return t l'objet tel qui l'est dans la base de donnee, null si non existant dans la base de donne
     */
    @Override
    public CoursGroupe lire() {
        throw new  UnsupportedOperationException();
    }

    /***
     *
     * @param coursGroupe l'objet tel qui doit etre modifie
     * @return t l'objet tel qui l'a ete modifie dans la base de donnee, null si l'objet est inexistant dans la BD
     */
    @Override
    public CoursGroupe modifier(CoursGroupe coursGroupe) {
        throw new  UnsupportedOperationException();
    }

    /***
     *
     * @return boolean true si supprime, false sinon
     */
    @Override
    public boolean supprimer() {
        throw new  UnsupportedOperationException();
    }

    /***
     *
     * @return la cle primaire de l'objet dans la base de donnee
     */
    @Override
    public long getPK() {
        throw new  UnsupportedOperationException();
    }
}
