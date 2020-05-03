package dti.g25.projet_s.dao;

import dti.g25.projet_s.domaine.entité.*;
import dti.g25.projet_s.domaine.interacteurs.GestionCoursGroupe;
import dti.g25.projet_s.domaine.interacteurs.GestionCoursGroupeException;
import dti.g25.projet_s.présentation.modèle.dao.DAOFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
            listeUtilisateur.add(new Utilisateur("professeur#"+i, "pwd"+i, Role.PROFESSEUR ));
            utilisateurListHashMap.put(listeUtilisateur.get(i), new LinkedList<CoursGroupe>());
            utilisateurSeancesHashMap.put(listeUtilisateur.get(i), new LinkedList<Seance>());
        }
        //Ajouter eleves
        for(int i=2; i<=100; i++){
            listeUtilisateur.add(new Utilisateur("utilisateur#"+i, "pwd"+i, Role.ÉLÈVE ));
            utilisateurListHashMap.put(listeUtilisateur.get(i), new LinkedList<CoursGroupe>());
            utilisateurSeancesHashMap.put(listeUtilisateur.get(i), new LinkedList<Seance>());

        }

        //Ajouter cours et groupes et seances
        for(int i=0;i<=15;i++){
            listeCoursGroupe.add(new CoursGroupe(new LibelleCours("Titre du 420-"+i, "420-"+i  ), 1));



            if(i%2!=0){ //Prof 0 a tous les cours impairs;
                utilisateurListHashMap.get(listeUtilisateur.get(0)).add(listeCoursGroupe.get(i));
                CoursGroupe coursGroupe= utilisateurListHashMap.get(listeUtilisateur.get(0)).get(utilisateurListHashMap.get(listeUtilisateur.get(0)).size()-1);


                    List<Utilisateur> utilisateurs= new LinkedList<>();
                    utilisateurs.add(listeUtilisateur.get(0));
                    coursGroupe.setParticipants(utilisateurs);


                for(int j=0;j<=15;j++){
                    utilisateurSeancesHashMap.get(listeUtilisateur.get(0)).add(new Seance(i+":00", i+":30", "lundi",j,coursGroupe));

                    System.out.println( utilisateurSeancesHashMap.get(listeUtilisateur.get(0)).get(j).get_heureDebut());

                }
            }
            else { //Prof 1 a tous les cours pairs
                utilisateurListHashMap.get(listeUtilisateur.get(1)).add(listeCoursGroupe.get(i));
                /**
                 *     for(int j=0;j<=15;j++){
                 *                     utilisateurSeancesHashMap.get(listeUtilisateur.get(1)).add(new Seance(i+":00", i+":30", "mercerdi",j, listeCoursGroupe.get(i)));
                 *                 }
                  */


            }

        }
        //Cours 420-1 a deux groupes
        listeCoursGroupe.add(new CoursGroupe(new LibelleCours("Titre du 420-"+1, "420-"+1  ), 2));
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

}
