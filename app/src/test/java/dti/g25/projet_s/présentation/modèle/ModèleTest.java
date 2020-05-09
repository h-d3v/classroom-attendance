package dti.g25.projet_s.présentation.modèle;

import android.webkit.URLUtil;
import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.LibelleCours;
import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.présentation.modèle.dao.DAO;
import dti.g25.projet_s.présentation.modèle.dao.DAOFactoryV1;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;


public class ModèleTest extends TestCase {

        DAOFactoryV1 mockDAOFactory=mock(DAOFactoryV1.class);
        DAO<Utilisateur> mockProf0=mock(DAO.class);
        DAO<Utilisateur> mockProf1=mock(DAO.class);
        DAO<Utilisateur> mockEleve0=mock(DAO.class);
        DAO<Utilisateur> mockEleve1=mock(DAO.class);
        DAO<Utilisateur> mockEleve2=mock(DAO.class);
        DAO<Utilisateur> mockEleve3=mock(DAO.class);
        DAO<Utilisateur> mockEleve5=mock(DAO.class);
        DAO<Utilisateur> mockEleve6=mock(DAO.class);
        DAO<Utilisateur> mockEleve4=mock(DAO.class);
        DAO<Utilisateur> mockEleve7=mock(DAO.class);
        DAO<Utilisateur> mockEleve8=mock(DAO.class);



        DAO<CoursGroupe> mockCoursGroupe1 = mock(DAO.class);
        DAO<CoursGroupe> mockCoursGroupe2 = mock(DAO.class);

        Utilisateur prof0=new Utilisateur("prof0", Role.PROFESSEUR,"prof0");
        Utilisateur prof1=new Utilisateur("prof1", Role.PROFESSEUR, "prof1");
        Utilisateur eleve0=new Utilisateur("eleve0", Role.ÉLÈVE, "eleve0");
        Utilisateur eleve1=new Utilisateur("eleve1", Role.ÉLÈVE, "eleve1");
        Utilisateur eleve3=new Utilisateur("eleve3", Role.ÉLÈVE, "eleve3");
        Utilisateur eleve2=new Utilisateur("eleve2", Role.ÉLÈVE, "eleve2");
        Utilisateur eleve4=new Utilisateur("eleve4", Role.ÉLÈVE, "eleve4");
        Utilisateur eleve5=new Utilisateur("eleve5", Role.ÉLÈVE, "eleve5");
        Utilisateur eleve6=new Utilisateur("eleve6", Role.ÉLÈVE, "eleve6");
        Utilisateur eleve7=new Utilisateur("eleve7", Role.ÉLÈVE, "eleve7");
        Utilisateur eleve8=new Utilisateur("eleve8", Role.ÉLÈVE, "eleve8");
        //Les utilisateurs factices
        @Before
        public void initialiser() {
            when(mockProf0.lire()).thenReturn(prof0);
            when(mockProf1.lire()).thenReturn(prof1);
            when(mockEleve1.lire()).thenReturn(eleve0);
            when(mockEleve2.lire()).thenReturn(eleve1);
            when(mockEleve3.lire()).thenReturn(eleve3);
            when(mockEleve4.lire()).thenReturn(eleve2);
            when(mockEleve5.lire()).thenReturn(eleve4);
            when(mockEleve0.lire()).thenReturn(eleve5);
            when(mockEleve6.lire()).thenReturn(eleve6);
            when(mockEleve7.lire()).thenReturn(eleve7);
            when(mockEleve8.lire()).thenReturn(eleve8);
            //les coursGroupes factices
            when(mockCoursGroupe1.lire()).thenReturn(new CoursGroupe(new LibelleCours("INFO", "420"), 1));
            when(mockCoursGroupe2.lire()).thenReturn(new CoursGroupe(new LibelleCours("INFO", "420"), 2));

            // when(mockDAOFactory.chargerListeUtilisateurParCoursGroupe())
            LinkedList<DAO<CoursGroupe>> daoLinkedList = new LinkedList<>();
            daoLinkedList.add(mockCoursGroupe1);
            daoLinkedList.add(mockCoursGroupe2);
            when(mockDAOFactory.chargerListeCoursGroupeParUtilisateur(mockProf0)).thenReturn(daoLinkedList);
        }



    @Test
    public void testGetCoursGroupes() {
    }

    public void testSetUtilisateur() {
    }
    @Test
    public void testChargerCoursGroupeUtilisateur() {
        ModèleDAO modèle= new ModèleDAO(mockDAOFactory, mockProf0);
        assertEquals(2, modèle.chargerCoursGroupeUtilisateur().size());
    }

    public void testChangerEtatSeance() {
    }

    public void testGetCourGroupeParPos() {
    }

    public void testAjouterAbsence() {
    }

    public void testCréerSéance() {
    }

    public void testGetSeanceParPos() {
    }

    public void testGetPostionSeance() {
    }

    public void testGetListDAOUtlisateurParCourGroupe() {
    }

    public void testGetListeEtudiantsParCoursGroupe() {
    }

    public void testGetUtilisateurConnecte() {
    }

    public void testSetEtatSeance() {
    }

    public void testGetListeSeance() {
    }

    public void testChargerSeanceUtilisateur() {
    }

    public void testGetListeUtilisateur() {
    }

    public void testGetUtilisateurParIndex() {
    }
}