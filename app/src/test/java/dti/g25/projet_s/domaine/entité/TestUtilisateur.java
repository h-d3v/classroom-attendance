package dti.g25.projet_s.domaine.entité;

import org.junit.Assert;
import org.junit.Test;

public class TestUtilisateur {
    Utilisateur cobaye= new Utilisateur("Hedi", Role.ADMINISTRATEUR, "nd4y99d483hgu4ifj8oi");

    @Test
    public void testGetUsername(){
        Assert.assertEquals("Hedi", cobaye.getUsername());
    }

    @Test
    public void testGetRole(){
        Assert.assertEquals(Role.ADMINISTRATEUR, cobaye.getRôle());
    }

    @Test
    public void testGetMdp(){
        Assert.assertEquals("nd4y99d483hgu4ifj8oi", cobaye.getMdp());
    }


}
