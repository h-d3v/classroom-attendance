package dti.g25.projet_s.présentation.modèle;

import dti.g25.projet_s.domaine.entité.*;
import dti.g25.projet_s.présentation.modèle.dao.DAO;
import dti.g25.projet_s.présentation.modèle.dao.DAOFactoryV1;
import dti.g25.projet_s.présentation.modèle.dao.ModèleDAO;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
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
    DAO<Utilisateur> mockEleve10=mock(DAO.class);
    DAO<Utilisateur> mockEleve11=mock(DAO.class);
    DAO<Utilisateur> mockEleve12=mock(DAO.class);
    DAO<Utilisateur> mockEleve13=mock(DAO.class);
    DAO<Utilisateur> mockEleve15=mock(DAO.class);
    DAO<Utilisateur> mockEleve16=mock(DAO.class);
    DAO<Utilisateur> mockEleve14=mock(DAO.class);
    DAO<Utilisateur> mockEleve17=mock(DAO.class);
    DAO<Utilisateur> mockEleve18=mock(DAO.class);





    DAO<CoursGroupe> mockCoursGroupe1 = mock(DAO.class);
    DAO<CoursGroupe> mockCoursGroupe2 = mock(DAO.class);

    Utilisateur prof0=new Utilisateur("prof0", Role.PROFESSEUR,"prof0");

    Utilisateur eleve0=new Utilisateur("eleve0", Role.ÉLÈVE, "eleve0");
    Utilisateur eleve1=new Utilisateur("eleve1", Role.ÉLÈVE, "eleve1");
    Utilisateur eleve3=new Utilisateur("eleve3", Role.ÉLÈVE, "eleve3");
    Utilisateur eleve2=new Utilisateur("eleve2", Role.ÉLÈVE, "eleve2");
    Utilisateur eleve4=new Utilisateur("eleve4", Role.ÉLÈVE, "eleve4");
    Utilisateur eleve5=new Utilisateur("eleve5", Role.ÉLÈVE, "eleve5");
    Utilisateur eleve6=new Utilisateur("eleve6", Role.ÉLÈVE, "eleve6");
    Utilisateur eleve7=new Utilisateur("eleve7", Role.ÉLÈVE, "eleve7");
    Utilisateur eleve8=new Utilisateur("eleve8", Role.ÉLÈVE, "eleve8");

    Utilisateur prof1=new Utilisateur("prof1", Role.PROFESSEUR, "prof1");
    Utilisateur eleve10=new Utilisateur("eleve10", Role.ÉLÈVE, "eleve10");
    Utilisateur eleve11=new Utilisateur("eleve11", Role.ÉLÈVE, "eleve11");
    Utilisateur eleve13=new Utilisateur("eleve13", Role.ÉLÈVE, "eleve13");
    Utilisateur eleve12=new Utilisateur("eleve12", Role.ÉLÈVE, "eleve12");
    Utilisateur eleve14=new Utilisateur("eleve14", Role.ÉLÈVE, "eleve14");
    Utilisateur eleve15=new Utilisateur("eleve15", Role.ÉLÈVE, "eleve15");
    Utilisateur eleve16=new Utilisateur("eleve16", Role.ÉLÈVE, "eleve16");
    Utilisateur eleve17=new Utilisateur("eleve17", Role.ÉLÈVE, "eleve17");
    Utilisateur eleve18=new Utilisateur("eleve18", Role.ÉLÈVE, "eleve18");

    ArrayList<DAO<Utilisateur>> listeUtilisateur1 =new ArrayList<>();
    ArrayList<DAO<Utilisateur>> listeUtilisateur = new ArrayList<>();
    ArrayList<DAO<Seance>> seances= new ArrayList<>();
    Seance seance0;
    Seance seance1;
    Seance seance3;
    Seance seance2;
    Seance seance4;
    Seance seance5;
    Seance seance6;
    Seance seance7;

    Horaire horaire0;
    Horaire horaire1;
    Horaire horaire3;
    Horaire horaire2;
    Horaire horaire4;
    Horaire horaire5;
    Horaire horaire6;
    Horaire horaire7;
    
    DAO<Seance> mockSeance0=mock(DAO.class);
    DAO<Seance> mockSeance1=mock(DAO.class);
    DAO<Seance> mockSeance3=mock(DAO.class);
    DAO<Seance> mockSeance2=mock(DAO.class);
    DAO<Seance> mockSeance4=mock(DAO.class);
    DAO<Seance> mockSeance5=mock(DAO.class);
    DAO<Seance> mockSeance6=mock(DAO.class);
    DAO<Seance> mockSeance7=mock(DAO.class);



    @Before
    public void initialiser() {
        //when(mockProf0.lire()).thenReturn(prof0);
        //when(mockProf1.lire()).thenReturn(prof1);
        when(mockEleve2.lire()).thenReturn(eleve1);
        when(mockEleve3.lire()).thenReturn(eleve3);
        when(mockEleve1.lire()).thenReturn(eleve0);
        when(mockEleve4.lire()).thenReturn(eleve2);
        when(mockEleve5.lire()).thenReturn(eleve4);
        when(mockEleve0.lire()).thenReturn(eleve5);
        when(mockEleve6.lire()).thenReturn(eleve6);
        when(mockEleve7.lire()).thenReturn(eleve7);
        when(mockEleve8.lire()).thenReturn(eleve8);
        //les coursGroupes factices
        CoursGroupe coursGroupe1= new CoursGroupe(new LibelleCours("INFO", "420"), 1);
        CoursGroupe coursGroupe2= new CoursGroupe(new LibelleCours("INFO", "420"), 1);

        when(mockCoursGroupe1.lire()).thenReturn(coursGroupe1);
        when(mockCoursGroupe2.lire()).thenReturn(coursGroupe2);





        //Peuple la liste d'un cours groupe
        listeUtilisateur.add(mockProf0);
        listeUtilisateur.add(mockEleve1);
        listeUtilisateur.add(mockEleve3);
        listeUtilisateur.add(mockEleve0);
        listeUtilisateur.add(mockEleve2);
        listeUtilisateur.add(mockEleve4);
        listeUtilisateur.add(mockEleve5);
        listeUtilisateur.add(mockEleve6);
        listeUtilisateur.add(mockEleve7);
        listeUtilisateur.add(mockEleve8);
        listeUtilisateur1.add(mockProf0);
        listeUtilisateur1.add(mockEleve11);
        listeUtilisateur1.add(mockEleve13);
        listeUtilisateur1.add(mockEleve10);
        listeUtilisateur1.add(mockEleve12);
        listeUtilisateur1.add(mockEleve14);
        listeUtilisateur1.add(mockEleve15);
        listeUtilisateur1.add(mockEleve16);
        listeUtilisateur1.add(mockEleve17);
        listeUtilisateur1.add(mockEleve18);

        horaire0= new Horaire(11.0f, 12.0f, "Lundi semaine #0");
        horaire1= new Horaire(11.0f, 12.0f, "Lundi semaine #1");
        horaire3= new Horaire(11.0f, 12.0f, "Lundi semaine #3");
        horaire2= new Horaire(11.0f, 12.0f, "Lundi semaine #2");
        horaire4= new Horaire(11.0f, 12.0f, "Lundi semaine #4");
        horaire5= new Horaire(11.0f, 12.0f, "Lundi semaine #5");
        horaire6= new Horaire(11.0f, 12.0f, "Lundi semaine #6");
        horaire7= new Horaire(11.0f, 12.0f, "Lundi semaine #7");



        ArrayList<Utilisateur> participants1= new ArrayList<>();
        for(DAO<Utilisateur> utilisateurDAO : listeUtilisateur){
            participants1.add(utilisateurDAO.lire());
        }
        ArrayList<Utilisateur> participants2= new ArrayList<>();
        for(DAO<Utilisateur> utilisateurDAO : listeUtilisateur1){
            participants2.add(utilisateurDAO.lire());
        }

        coursGroupe1.setParticipants(participants1);
        coursGroupe2.setParticipants(participants2);

        seance0=new Seance(mockCoursGroupe1.lire(), horaire0);
        seance1=new Seance(mockCoursGroupe1.lire(), horaire1);
        seance3=new Seance(mockCoursGroupe1.lire(), horaire3);
        seance2=new Seance(mockCoursGroupe1.lire(), horaire2);
        seance4=new Seance(mockCoursGroupe1.lire(), horaire4);
        seance5=new Seance(mockCoursGroupe1.lire(), horaire5);
        seance6=new Seance(mockCoursGroupe1.lire(), horaire6);
        seance7=new Seance(mockCoursGroupe1.lire(), horaire7);
        seances.add(mockSeance0);
        seances.add(mockSeance1);
        seances.add(mockSeance3);
        seances.add(mockSeance2);
        seances.add(mockSeance4);
        seances.add(mockSeance5);
        seances.add(mockSeance6);
        seances.add(mockSeance7);
      
        when(mockSeance0.lire()).thenReturn(seance0);
        when(mockSeance1.lire()).thenReturn(seance1);
        when(mockSeance3.lire()).thenReturn(seance3);
        when(mockSeance2.lire()).thenReturn(seance2);
        when(mockSeance4.lire()).thenReturn(seance4);
        when(mockSeance5.lire()).thenReturn(seance5);
        when(mockSeance6.lire()).thenReturn(seance6);
        when(mockSeance7.lire()).thenReturn(seance7);
        
        




    }



    @Test
    public void testGetCoursGroupes() {
        LinkedList<DAO<CoursGroupe>> daoLinkedList = new LinkedList<>();
        daoLinkedList.add(mockCoursGroupe1);
        daoLinkedList.add(mockCoursGroupe2);
        when(mockDAOFactory.chargerListeCoursGroupeParUtilisateur(mockProf0)).thenReturn(daoLinkedList);
        ModèleDAO modèleDAO = new ModèleDAO(mockDAOFactory, mockProf0);
        verify(mockDAOFactory).chargerListeCoursGroupeParUtilisateur(mockProf0);

        assertEquals(2, modèleDAO.getCoursGroupes().size());
    }
    @Test
    public void testSetUtilisateur() {
        ModèleDAO modèleDAO = new ModèleDAO();
        modèleDAO.setUtilisateur(mockEleve0);
        assertEquals(mockEleve0, modèleDAO.getUtilisateurConnecte());
        assertNotSame(mockEleve1,modèleDAO.getUtilisateurConnecte());
        verify(mockEleve0, atLeastOnce()).lire();

    }
    @Test
    public void testChargerCoursGroupeUtilisateur() {
        LinkedList<DAO<CoursGroupe>> daoLinkedList = new LinkedList<>();
        daoLinkedList.add(mockCoursGroupe1);
        daoLinkedList.add(mockCoursGroupe2);
        when(mockDAOFactory.chargerListeCoursGroupeParUtilisateur(mockProf0)).thenReturn(daoLinkedList);
        when(mockCoursGroupe1.lire()).thenReturn(new CoursGroupe(new LibelleCours("INFO", "420"), 1));
        when(mockCoursGroupe2.lire()).thenReturn(new CoursGroupe(new LibelleCours("INFO", "420"), 2));
        ModèleDAO modèle= new ModèleDAO(mockDAOFactory, mockProf0);
        verify(mockDAOFactory).chargerListeCoursGroupeParUtilisateur(mockProf0);
        assertEquals(2, modèle.chargerCoursGroupeUtilisateur().size());
        assertEquals(mockCoursGroupe1.lire(),modèle.chargerCoursGroupeUtilisateur().get(0).lire());
        assertEquals(mockCoursGroupe2.lire(),modèle.chargerCoursGroupeUtilisateur().get(1).lire());
        verify(mockCoursGroupe1, atLeastOnce()).lire();
        verify(mockCoursGroupe2,atLeastOnce()).lire();

    }
    @Test
    public void testChangerEtatSeance() {
    }
    @Test
    public void testGetCourGroupeParPos() {
        LinkedList<DAO<CoursGroupe>> daoLinkedList = new LinkedList<>();
        daoLinkedList.add(mockCoursGroupe1);
        daoLinkedList.add(mockCoursGroupe2);
        when(mockDAOFactory.chargerListeCoursGroupeParUtilisateur(mockEleve0)).thenReturn(daoLinkedList);
        ModèleDAO modèle= new ModèleDAO(mockDAOFactory, mockEleve0);
        assertEquals(mockCoursGroupe1, modèle.getCourGroupeParPos(0));
        assertEquals(mockCoursGroupe2, modèle.getCourGroupeParPos(1));
        assertNull(modèle.getCourGroupeParPos(3));
        verify(mockDAOFactory).chargerListeCoursGroupeParUtilisateur(mockEleve0);
    }


    @Test
    public void testGetSeanceParPos() {
        ModèleDAO modèleDAO= new ModèleDAO(mockDAOFactory,mockProf0);
        when(mockDAOFactory.chargerListeSeanceParUtilisateur(mockProf0)).thenReturn(seances);
        modèleDAO.chargerSeanceUtilisateur();
        for(int i= 0;i<seances.size();i++){
            assertEquals(seances.get(i).lire(), modèleDAO.getSeanceParPos(i).lire());
            verify(seances.get(i), atLeast(1)).lire();
        }
        verify(mockDAOFactory).chargerListeSeanceParUtilisateur(mockProf0);

    }

    @Test
    public void testGetListDAOUtlisateurParCourGroupe() {

        LinkedList<DAO<CoursGroupe>> daoLinkedList = new LinkedList<>();
        daoLinkedList.add(mockCoursGroupe1);
        daoLinkedList.add(mockCoursGroupe2);
        when(mockDAOFactory.chargerListeUtilisateurParCoursGroupe(mockCoursGroupe1)).thenReturn(listeUtilisateur);
        when(mockDAOFactory.chargerListeUtilisateurParCoursGroupe(mockCoursGroupe2)).thenReturn(listeUtilisateur1);
        when(mockDAOFactory.chargerListeCoursGroupeParUtilisateur(mockProf0)).thenReturn(daoLinkedList);
        ModèleDAO modèleDAO= new ModèleDAO(mockDAOFactory, mockProf0);
        when(mockDAOFactory.chargerListeCoursGroupeParUtilisateur(mockProf0)).thenReturn(daoLinkedList);
        modèleDAO.chargerCoursGroupeUtilisateur();
        assertNotNull(listeUtilisateur.get(0));
        assertNotNull(modèleDAO.getListDAOUtlisateurParCourGroupe(0));
        assertEquals(modèleDAO.chargerCoursGroupeUtilisateur().size(),2);
        assertEquals(listeUtilisateur, modèleDAO.getListDAOUtlisateurParCourGroupe(0));
        for(int i=0; i<listeUtilisateur.size();i++){
            assertEquals(listeUtilisateur.get(i), modèleDAO.getListDAOUtlisateurParCourGroupe(0).get(i));
            verify(listeUtilisateur.get(i),atLeastOnce()).lire();
        }
        assertEquals(listeUtilisateur1, modèleDAO.getListDAOUtlisateurParCourGroupe(1));
        for(int i=0; i<listeUtilisateur1.size();i++){
            assertEquals(listeUtilisateur1.get(i), modèleDAO.getListDAOUtlisateurParCourGroupe(1).get(i));
            verify(listeUtilisateur1.get(i),atLeastOnce()).lire();
        }
        verify(mockDAOFactory,atLeastOnce()).chargerListeCoursGroupeParUtilisateur(mockProf0);
        verify(mockDAOFactory, atLeastOnce()).chargerListeUtilisateurParCoursGroupe(mockCoursGroupe1);
        verify(mockDAOFactory, atLeastOnce()).chargerListeUtilisateurParCoursGroupe(mockCoursGroupe2);
    }
    @Test
    public void testGetListeEtudiantsParCoursGroupe(){
        LinkedList<DAO<CoursGroupe>> daoLinkedList = new LinkedList<>();
        daoLinkedList.add(mockCoursGroupe1);
        daoLinkedList.add(mockCoursGroupe2);
        when(mockDAOFactory.chargerListeUtilisateurParCoursGroupe(mockCoursGroupe1)).thenReturn(listeUtilisateur);
        when(mockDAOFactory.chargerListeCoursGroupeParUtilisateur(mockProf0)).thenReturn(daoLinkedList);
        ModèleDAO modèleDAO= new ModèleDAO(mockDAOFactory, mockProf0);
        when(mockDAOFactory.chargerListeCoursGroupeParUtilisateur(mockProf0)).thenReturn(daoLinkedList);
        modèleDAO.chargerCoursGroupeUtilisateur();
        assertNotNull(listeUtilisateur);
        assertNotNull(modèleDAO.getListDAOUtlisateurParCourGroupe(0));
        assertEquals(modèleDAO.chargerCoursGroupeUtilisateur().size(),2);
        listeUtilisateur.remove(mockProf0);
        assertEquals(listeUtilisateur, modèleDAO.getListeEtudiantsParCoursGroupe(0));
        //Tous les utilisateurs sont bien des elevesuniquement?
        for(int i=0; i<listeUtilisateur.size();i++){
            assertEquals(listeUtilisateur.get(i).lire().getRôle(), Role.ÉLÈVE);
            assertEquals(modèleDAO.getListDAOUtlisateurParCourGroupe(0).get(i).lire().getRôle(), Role.ÉLÈVE);
            assertTrue(modèleDAO.getListeEtudiantsParCoursGroupe(0).contains(listeUtilisateur.get(i)));
            verify(listeUtilisateur.get(i), atLeastOnce()).lire();
        }
        verify(mockDAOFactory,atLeastOnce()).chargerListeCoursGroupeParUtilisateur(mockProf0);
        verify(mockDAOFactory, atLeastOnce()).chargerListeUtilisateurParCoursGroupe(mockCoursGroupe1);


    }
    @Test
    public void testGetUtilisateurConnectePasDUtilisateur() {

        ModèleDAO modèleDAO= new ModèleDAO(mockDAOFactory, null);
        assertNull(modèleDAO.getUtilisateurConnecte());
    }
    @Test
    public void testGetUtilisateurConnecteParConstructeur(){
        ModèleDAO modèleDAO = new ModèleDAO(mockDAOFactory, mockEleve3);
        assertEquals(mockEleve3, modèleDAO.getUtilisateurConnecte());
    }
    @Test
    public void testSetEtatSeance() {
        ModèleDAO modèleDAO= new ModèleDAO(mockDAOFactory,mockProf0);
        when(mockDAOFactory.chargerListeSeanceParUtilisateur(mockProf0)).thenReturn(seances);
        modèleDAO.chargerSeanceUtilisateur();
        modèleDAO.setEtatSeance(0, EtatSeance.ANULLEE);
        assertEquals(modèleDAO.getSeanceParPos(0).lire().get_etat(), EtatSeance.ANULLEE );
        verify(mockDAOFactory).chargerListeSeanceParUtilisateur(mockProf0);
        verify(seances.get(0), atLeastOnce()).modifier(mockSeance0.lire());

    }

    @Test
    public void testChargerSeanceUtilisateur() {
        when(mockDAOFactory.chargerListeSeanceParUtilisateur(mockProf0)).thenReturn(seances);
        ModèleDAO modèleDAO= new ModèleDAO(mockDAOFactory, mockProf0);
        modèleDAO.chargerSeanceUtilisateur();
        assertNotNull(modèleDAO.getListeSeance());
        assertEquals(seances, modèleDAO.getListeSeance());
        verify(mockDAOFactory).chargerListeSeanceParUtilisateur(mockProf0);

    }



}