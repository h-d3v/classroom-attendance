package dti.g25.projet_s.dao;

public class UtlisateurFactice {

    public static Boolean tenterConnexion(String nomUtilisateur, String MotDePasse){
        Boolean connexion = false;

        if(nomUtilisateur.equals("joe") && MotDePasse.equals("1234"))
            connexion = true;

        return connexion;
    }

}
