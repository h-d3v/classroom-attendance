package dti.g25.projet_s.domaine.entité;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class TestSeance {
    private LibelleCours libelleCobaye= new LibelleCours("Appli native 54","HE-DI-00");
    private CoursGroupe cobayeCoursGroupe = new CoursGroupe(libelleCobaye, 33);
    private Horaire cobayeHoraire= new Horaire(14.0,18.0,"Vendredi");

    private Utilisateur cobayeUser= new Utilisateur("Hedi", Role.PROFESSEUR, "nd4y99d483hgu4ifj8oi");
    private Absence absenceCobaye = new Absence(cobayeUser, true);

    private Seance seanceCobaye= new Seance(cobayeCoursGroupe, cobayeHoraire);

    @Test
    public void setGetAbsences(){
        List<Absence> absenceList= new LinkedList<>();
        absenceList.add(absenceCobaye);
        seanceCobaye.setListeAbsence(absenceList);
        Assert.assertEquals(absenceList,seanceCobaye.getListeAbsence());
    }

    @Test
    public void getCoursGroupe() {
        Assert.assertEquals(cobayeCoursGroupe, seanceCobaye.get_coursGroupe());
    }

    @Test
    public void getHoraire(){
        Assert.assertEquals(cobayeHoraire, seanceCobaye.get_horaires());
    }

}
