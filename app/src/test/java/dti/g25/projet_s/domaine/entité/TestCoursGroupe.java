package dti.g25.projet_s.domaine.entité;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class TestCoursGroupe {
    private LibelleCours libelleCobaye= new LibelleCours("Appli native 54","HE-DI-00");
    private CoursGroupe cobayeCoursGroupe = new CoursGroupe(libelleCobaye, 33);

    private Utilisateur cobayeUser1= new Utilisateur("Hedi", Role.PROFESSEUR, "nd4y99d483hgu4ifj8oi");
    private Utilisateur cobayeUser2= new Utilisateur("Hedi", Role.ÉLÈVE, "nd4y99di");
    private List<Utilisateur> userListCobaye= new LinkedList<>();

    private Horaire cobayeHoraire= new Horaire(14.0,18.0,"Vendredi");
    private Horaire cobayeHoraire2= new Horaire(13.0,18.0,"Dimanche");


    @Test
    public void testToString(){
        Assert.assertEquals("Appli native 54 : HE-DI-00 Groupe: 33", cobayeCoursGroupe.toString());
    }

    @Test
    public void testSetGetParticipants(){
        userListCobaye.add(cobayeUser1);
        userListCobaye.add(cobayeUser2);
        cobayeCoursGroupe.setParticipants(userListCobaye);
        Assert.assertEquals(userListCobaye, cobayeCoursGroupe.getParticipants());
    }

    @Test
    public void testSetGetHoraire(){
        List<Horaire> horaireList= new LinkedList<>();
        horaireList.add(cobayeHoraire);
        horaireList.add(cobayeHoraire2);
        cobayeCoursGroupe.setListeHoraire(horaireList);
        Assert.assertEquals(horaireList, cobayeCoursGroupe.getListeHoraire());
    }

}
