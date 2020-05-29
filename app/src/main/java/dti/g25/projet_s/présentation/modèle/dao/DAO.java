package dti.g25.projet_s.présentation.modèle.dao;

public interface DAO<T> {


        /***
         *
         * @param t l'objet a creer dans la base de donnee
         * @return t l'objet tel qui l'a ete cree dans la base de donnee, null si non cree
         */
        T creer(T t);

        /***
         *
         * @return t l'objet tel qui l'est dans la base de donnee, null si non existant dans la base de donne
         */
        T lire();

        /***
         *
         * @param t l'objet tel qui doit etre modifie
         * @return t l'objet tel qui l'a ete modifie dans la base de donnee, null si l'objet est inexistant dans la BD
         */
        T modifier(T t);

        /***
         *
         * @return boolean true si supprime, false sinon
         */
        boolean supprimer();



}
