package dti.g25.projet_s.domaine.interacteur;

import org.junit.Assert;
import org.junit.Test;

import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.domaine.interacteurs.CréeationUtilisateur;

public class TestCreationUtilisateur {
    private Utilisateur cobayeUser1= new Utilisateur("Hedi", Role.PROFESSEUR);
    private CréeationUtilisateur créeationUtilisateur = new CréeationUtilisateur();

    @Test
    public void creerUtilisateur() throws Exception {
        Assert.assertEquals(cobayeUser1, créeationUtilisateur.CréerUtilisateur("Hedi", Role.PROFESSEUR));
    }

}
