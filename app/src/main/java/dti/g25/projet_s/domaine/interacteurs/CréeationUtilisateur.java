package dti.g25.projet_s.domaine.interacteurs;

import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Utilisateur;

public class CréeationUtilisateur {

    public static Utilisateur CréerUtilisateur(String unNom, String mdp, Role unRôle) throws Exception {

        if(mdp == "" || mdp.isEmpty())
            throw new Exception("le mdp est vide");
        if(unNom == "" || unNom.isEmpty())
            throw new Exception("l'utilisateur n'a pas de nom est vide");
        if(unRôle == null)
            throw new Exception("l'utilisateur n'a pas de rôle");

        return new Utilisateur(unNom, mdp, unRôle);
    }

}
