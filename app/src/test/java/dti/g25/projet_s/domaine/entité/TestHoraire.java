package dti.g25.projet_s.domaine.entité;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHoraire {
    private Horaire cobaye= new Horaire(14.0,18.0,"Vendredi");

    @Test
    public void testGetJournee(){
        assertEquals(cobaye.getJournee(), "Vendredi");
    }

    @Test
    public void testGetHeureDebut(){
      Assert.assertSame(cobaye.getHeureDebut(), 14.0);
    }

    @Test
    public void testGetHeureFin(){
        Assert.assertSame(cobaye.getHeureFin(), 18.0);
    }

    @Test
    public void testGetStringHeureFin(){
        assertEquals(cobaye.getHeureFinString(),"18:00");
    }

}
