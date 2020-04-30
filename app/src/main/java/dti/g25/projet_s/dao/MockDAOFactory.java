package dti.g25.projet_s.dao;

import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.LibelleCours;
import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.présentation.modèle.dao.DAOFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MockDAOFactory extends DAOFactory {

    List<Utilisateur> listeUtilisateur;
    HashMap<Utilisateur, List<CoursGroupe>> utilisateurListHashMap;
    List<CoursGroupe> listeCoursGroupe;





    public MockDAOFactory()  {
        listeUtilisateur=new LinkedList<Utilisateur>();
        utilisateurListHashMap= new HashMap<Utilisateur, List<CoursGroupe>>();
        listeCoursGroupe=new LinkedList<CoursGroupe>();

        /***
         * Peupler la base de donneee Mock
         */
        //Ajouter prof
        for(int i=0; i<2; i++){
            listeUtilisateur.add(new Utilisateur("professeur#"+i, "pwd"+i, Role.PROFESSEUR ));
            utilisateurListHashMap.put(listeUtilisateur.get(i), new LinkedList<CoursGroupe>());
        }
        //Ajouter eleves
        for(int i=2; i<=100; i++){
            listeUtilisateur.add(new Utilisateur("utilisateur#"+i, "pwd"+i, Role.ÉLÈVE ));
            utilisateurListHashMap.put(listeUtilisateur.get(i), new LinkedList<CoursGroupe>());
        }

        //Ajouter cours et groupes
        for(int i=0;i<=15;i++){
            listeCoursGroupe.add(new CoursGroupe(new LibelleCours("Titre du 420-"+i, "420-"+i  ), 1));

            if(i%2!=0){ //Prof 0 a tous les cours impairs
                utilisateurListHashMap.get(listeUtilisateur.get(0)).add(listeCoursGroupe.get(i));
            }
            else { //Prof 1 a tous les cours pairs
                utilisateurListHashMap.get(listeUtilisateur.get(1)).add(listeCoursGroupe.get(i));
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

    public List<CoursGroupe> creerListeCoursGroupeParUtilisateur(Utilisateur utilisateur) {
        return utilisateurListHashMap.get(utilisateur);
    }

    @Override
    public Utilisateur getUtilisateur(int i) {
        return listeUtilisateur.get(i);
    }

}
