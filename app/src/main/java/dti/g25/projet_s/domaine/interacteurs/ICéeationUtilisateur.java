package dti.g25.projet_s.domaine.interacteurs;

import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Utilisateur;

public interface ICéeationUtilisateur {

    Utilisateur CréerUtilisateur(String unNom, String mdp, Role unRôle) throws Exception;

}
