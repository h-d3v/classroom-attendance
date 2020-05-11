package dti.g25.projet_s.domaine.entité;

import org.junit.Assert;
import org.junit.Test;

public class AbsenceTest {
    private Utilisateur cobayeUser= new Utilisateur("Hedi", Role.ÉLÈVE, "nd4y99di");

    private Absence absenceCobaye = new Absence(cobayeUser, true);

    @Test
    public void getUtilisateur(){
        Assert.assertEquals(cobayeUser, absenceCobaye.getUtilisateur());
    }

    @Test
    public void setGetRetard(){
        absenceCobaye.setRetard(1);
        Assert.assertSame(1.0, absenceCobaye.getRetard());
    }




}
