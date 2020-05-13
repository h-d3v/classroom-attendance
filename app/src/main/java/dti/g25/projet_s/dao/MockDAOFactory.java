package dti.g25.projet_s.dao;

import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.EtatSeance;
import dti.g25.projet_s.domaine.entité.Horaire;
import dti.g25.projet_s.domaine.entité.LibelleCours;
import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.présentation.modèle.dao.DAOFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MockDAOFactory extends  DAOFactory{

    List<Utilisateur> listeUtilisateur= new LinkedList<Utilisateur>();
    HashMap<Utilisateur, List<CoursGroupe>> utilisateurListHashMap;
    List<CoursGroupe> listeCoursGroupe;
    HashMap<Utilisateur, List<Seance>> utilisateurSeancesHashMap;





    public MockDAOFactory() {
        listeUtilisateur=new LinkedList<Utilisateur>();
        utilisateurListHashMap= new HashMap<Utilisateur, List<CoursGroupe>>();
        listeCoursGroupe=new LinkedList<CoursGroupe>();
        utilisateurSeancesHashMap= new HashMap<>();



        /***
         * Peupler la base de donneee Mock
         */
        //Ajouter prof
        for(int i=0; i<2; i++){
            listeUtilisateur.add(new Utilisateur("professeur#"+i, Role.PROFESSEUR ));
            utilisateurListHashMap.put(listeUtilisateur.get(i), new LinkedList<CoursGroupe>());
            utilisateurSeancesHashMap.put(listeUtilisateur.get(i), new LinkedList<Seance>());
        }
        //Ajouter eleves
        for(int i=2; i<=100; i++){
            listeUtilisateur.add(new Utilisateur("utilisateur#"+i, Role.ÉLÈVE ));
            utilisateurListHashMap.put(listeUtilisateur.get(i), new LinkedList<CoursGroupe>());
            utilisateurSeancesHashMap.put(listeUtilisateur.get(i), new LinkedList<Seance>());

        }

        //Ajouter cours et groupes et seances
        for(int i=0;i<=15;i++){
            listeCoursGroupe.add(new CoursGroupe(new LibelleCours("Titre du 420-"+i, "420-"+i, "aa"  ), 1));



            if(i%2!=0){ //Prof 0 a tous les cours impairs;
                utilisateurListHashMap.get(listeUtilisateur.get(0)).add(listeCoursGroupe.get(i));
                CoursGroupe coursGroupe= utilisateurListHashMap.get(listeUtilisateur.get(0)).get(utilisateurListHashMap.get(listeUtilisateur.get(0)).size()-1);


                    List<Utilisateur> utilisateurs= new LinkedList<>();
                    utilisateurs.add(listeUtilisateur.get(0));
                    coursGroupe.setParticipants(utilisateurs);


                for(int j=0;j<=15;j++){
                    utilisateurSeancesHashMap.get(listeUtilisateur.get(0)).add(new Seance(coursGroupe, new Horaire( Double.valueOf(i), Double.valueOf(j), "lundi")));

                    System.out.println( utilisateurSeancesHashMap.get(listeUtilisateur.get(0)).get(j).get_horaires().getHeureDebut());

                }
            }
            else { //Prof 1 a tous les cours pairs
                utilisateurListHashMap.get(listeUtilisateur.get(1)).add(listeCoursGroupe.get(i));
            }

        }
        //Cours 420-i a deux groupes
        listeCoursGroupe.add(new CoursGroupe(new LibelleCours("Titre du 420-"+1, "420-"+1 , "aa" ), 2));
        utilisateurListHashMap.get(listeUtilisateur.get(0)).add(listeCoursGroupe.get(listeCoursGroupe.size()-1));

        for(int i=2;i<50;i++){
            utilisateurListHashMap.get(listeUtilisateur.get(i)).add(listeCoursGroupe.get(0));
            utilisateurListHashMap.get(listeUtilisateur.get(i)).add(listeCoursGroupe.get(7));
        }
        for(int i=50; i<80; i++){
            utilisateurListHashMap.get(listeUtilisateur.get(i)).add(listeCoursGroupe.get(listeCoursGroupe.size()-1));
        }

    }

    /**
     *
     * @param utilisateur
     * @return
     */


    @Override
    public List<CoursGroupe> creerListeCoursGroupeParUtilisateur(Utilisateur utilisateur) {
        return utilisateurListHashMap.get(utilisateur);
    }

    @Override
    public List<Seance> creerListeSeanceParUtilisateur(int cle){
        return utilisateurSeancesHashMap.get(listeUtilisateur.get(cle));
    }

    @Override
    public Seance getSeanceUtilisateur(int utilisateurId, int key) {
        return utilisateurSeancesHashMap.get(listeUtilisateur.get(utilisateurId)).get(key);
    }

    @Override
    public Utilisateur getUtilisateur(int i) {
        return listeUtilisateur.get(i);
    }

    public boolean changerStatutSeance(int pos, EtatSeance etatSeance){
        //TODO
        return false;
    }

}
