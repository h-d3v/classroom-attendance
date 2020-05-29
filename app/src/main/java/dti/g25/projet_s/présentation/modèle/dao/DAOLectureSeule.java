package dti.g25.projet_s.présentation.modèle.dao;


public interface DAOLectureSeule<T> {


    /***
     *
     * @return t l'objet tel qui l'est dans la base de donnee, null si non existant dans la base de donne
     */
    T lire();



}
