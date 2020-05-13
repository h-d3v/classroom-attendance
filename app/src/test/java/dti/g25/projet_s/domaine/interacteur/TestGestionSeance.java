package dti.g25.projet_s.domaine.interacteur;

import org.junit.Assert;
import org.junit.Test;

import dti.g25.projet_s.domaine.entité.Absence;
import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.EtatSeance;
import dti.g25.projet_s.domaine.entité.Horaire;
import dti.g25.projet_s.domaine.entité.LibelleCours;
import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.domaine.interacteurs.GestionCoursGroupe;
import dti.g25.projet_s.domaine.interacteurs.GestionSeance;

public class TestGestionSeance {
    private LibelleCours libelleCobaye= new LibelleCours("Appli native 54","HE-DI-00");
    private CoursGroupe cobayeCoursGroupe = new CoursGroupe(libelleCobaye, 33);
    private Horaire cobayeHoraire= new Horaire(14.0,18.0,"Vendredi");

    private Utilisateur cobayeUser= new Utilisateur("Hedi", Role.PROFESSEUR, "nd4y99d483hgu4ifj8oi");
    private Absence absenceCobaye = new Absence(cobayeUser, true);

    private Seance seanceCobaye= new Seance(cobayeCoursGroupe, cobayeHoraire);

    private GestionSeance gestionSeance= new GestionSeance();

    @Test
    public void creerSeance(){
        Assert.assertEquals(seanceCobaye, gestionSeance.creerSeance(cobayeCoursGroupe,cobayeHoraire));
    }

    @Test
    public void changerStatut(){
        seanceCobaye.set_etat(EtatSeance.ANULLEE);
        gestionSeance.changerSatutSeance(EtatSeance.PREVUE,seanceCobaye);
        Assert.assertEquals(EtatSeance.PREVUE,seanceCobaye.get_etat());
    }

    @Test
    public void ajouterAbsn(){
        gestionSeance.ajouterAbsence(cobayeUser, seanceCobaye, true);
        Assert.assertEquals(absenceCobaye.getUtilisateur(),seanceCobaye.getListeAbsence().get(0).getUtilisateur());
    }
}
