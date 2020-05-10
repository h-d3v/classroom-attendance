package dti.g25.projet_s.dao;

import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.domaine.interacteurs.CréeationUtilisateur;

public class UtlisateurFactice {

    public static String tenterConnexion(String nomUtilisateur, String MotDePasse) throws Exception {
        String uneClé= null;
        if(nomUtilisateur.equals("ReanuKeeves") && MotDePasse.equals("Matrix4"))
            uneClé = "unCléProf";
        else if(nomUtilisateur.equals("Sakurai") && MotDePasse.equals("Kirby"))
            uneClé = "unCléÉlèves";

        return uneClé;
    }
}
