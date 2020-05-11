package dti.g25.projet_s.domaine.interacteur;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.LibelleCours;
import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.domaine.interacteurs.GestionCoursGroupe;
import dti.g25.projet_s.domaine.interacteurs.GestionCoursGroupeException;

public class TestGestionCoursGroupe {
    private LibelleCours libelleCobaye= new LibelleCours("Appli native 54","HE-DI-00");
    private CoursGroupe cobayeCoursGroupe = new CoursGroupe(libelleCobaye, 33);
    private GestionCoursGroupe gestionCoursGroupe= new GestionCoursGroupe();
    private Utilisateur cobayeUser2= new Utilisateur("Hedi", Role.PROFESSEUR, "nd4y99di");
    private Utilisateur cobayeUser1= new Utilisateur("Hedi2", Role.PROFESSEUR, "nd4y99d483hgu4ifj8oi");

    @Test
    public void creerGroupe(){
        Assert.assertEquals(cobayeCoursGroupe, gestionCoursGroupe.creerCoursGroupe(libelleCobaye, 33));
    }

    @Test
    public void ajouterPrtcipant(){
        cobayeCoursGroupe.setParticipants(new LinkedList<Utilisateur>());
        gestionCoursGroupe.ajouterParticipant( cobayeUser2,cobayeCoursGroupe);
        Assert.assertEquals(cobayeUser2,cobayeCoursGroupe.getParticipants().get(0));
    }

    @Test
    public void modifPrticipant() throws GestionCoursGroupeException {
        List linkedList=new LinkedList<Utilisateur>();
        List linkedList2=new LinkedList<Utilisateur>();
        linkedList.add(cobayeUser1);
        linkedList2.add(cobayeUser2);

        cobayeCoursGroupe.setParticipants(linkedList);

        gestionCoursGroupe.modifierParticipants(linkedList2, cobayeCoursGroupe);

        Assert.assertEquals(cobayeCoursGroupe.getParticipants(), linkedList2);
    }

}
