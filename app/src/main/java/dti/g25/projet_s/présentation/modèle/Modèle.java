package dti.g25.projet_s.présentation.modèle;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import dti.g25.projet_s.domaine.entité.Utilisateur;

public class Modèle {

    List<Utilisateur> utilisateurs;
    Utilisateur utilisateurActuelle;

    /**
     * permet de chercher un utlisateur par son nom utlisateur et de comparer son mdp et le place en tant qu'utilisateur actuelle
     * @param nomUtlisateur
     * @param motDePasse
     * @return connexion Boolean
     */
    public Boolean connecterUtilisateur(String nomUtlisateur, String motDePasse) throws NoSuchAlgorithmException {
        Boolean connexion = false;


        for (Utilisateur utilisateur: utilisateurs) {
            if(obtenirEmpreinteEnString(obtenirEmprunteSha(motDePasse)).equals(motDePasse) && utilisateur.getUsername().equals(nomUtlisateur)) {
                connexion = true;
                utilisateurActuelle = utilisateur;
            }
        }

        return connexion;
    }



    public static String obtenirEmpreinteEnString(byte[] hash)
    {
        BigInteger number = new BigInteger(1, hash);

        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    /**
     * Retourne en byte un string hashe en sha256
     * @param mdp
     * @return
     */
    public static byte[] obtenirEmprunteSha(String mdp) throws NoSuchAlgorithmException {

        //choisi le type a utilisé
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        //rentourne en BYTE le hash a utilisé
        return md.digest(mdp.getBytes(StandardCharsets.UTF_8));
    }

}