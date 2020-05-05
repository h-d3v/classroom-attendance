package dti.g25.projet_s.dao;

import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.EtatSeance;
import dti.g25.projet_s.domaine.entité.Horaire;
import dti.g25.projet_s.domaine.entité.LibelleCours;
import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.présentation.modèle.dao.DAOFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MockDAOFactory extends  DAOFactory{

    private List<Utilisateur> listeUtilisateur= new LinkedList<Utilisateur>();
    private HashMap<Utilisateur, List<CoursGroupe>> utilisateurListHashMap;
    private List<CoursGroupe> listeCoursGroupe;
    private LinkedList<Seance> listeSeance;


    
    public MockDAOFactory()  {
        listeUtilisateur=new LinkedList<Utilisateur>();
        utilisateurListHashMap= new HashMap<Utilisateur, List<CoursGroupe>>();
        listeCoursGroupe=new LinkedList<CoursGroupe>();
        listeSeance= new LinkedList<Seance>();

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
        //Cours 420-i a deux groupes
        listeCoursGroupe.add(new CoursGroupe(new LibelleCours("Titre du 420-"+1, "420-"+1  ), 2));
        utilisateurListHashMap.get(listeUtilisateur.get(0)).add(listeCoursGroupe.get(listeCoursGroupe.size()-1));

        for(int i=2;i<50;i++){
            utilisateurListHashMap.get(listeUtilisateur.get(i)).add(listeCoursGroupe.get(0));
            utilisateurListHashMap.get(listeUtilisateur.get(i)).add(listeCoursGroupe.get(7));
        }
        for(int i=50; i<80; i++){
            utilisateurListHashMap.get(listeUtilisateur.get(i)).add(listeCoursGroupe.get(listeCoursGroupe.size()-1));
        }

        //Ajouter les séances
        for(int i=0;i<50;i++){
            int randomNumJour = ThreadLocalRandom.current().nextInt(0, 6 + 1);
            int randomNumPosListeCoursGroupe = ThreadLocalRandom.current().nextInt(0, 14 + 1);

            Horaire horaire= new Horaire(randomNumJour, "13:00","16:00");
            //Tout les utilisateurs sont inscrits a toute les seance, pour tester

            CoursGroupe cg=listeCoursGroupe.get(randomNumPosListeCoursGroupe);
            cg.setParticipants(listeUtilisateur);
            Seance seance= new Seance(listeCoursGroupe.get(randomNumPosListeCoursGroupe));

            seance.set_horaire(horaire);
            seance.set_etat(EtatSeance.PREVUE);
            listeSeance.add(seance);
            //TODO simuler une absence à une séance avec le hash map
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

    @Override
    public List<Seance> getListeSeanceParUtilisateur(Utilisateur utilisateur) {
        //on parcours la liste des seances
        List<Seance> seancesDeUtilisateur= new LinkedList<>();
        for (Seance seance: listeSeance ) {

            //on pacours la liste d'utilisateur inscrit a la seance

            for (Utilisateur participantSeance: seance.get_coursGroupe().getParticipants()) {
                //verifie si le nom de l'utilisateur fait partie de la seance
                if(utilisateur.getUsername().equals(participantSeance.getUsername())) {
                    List<CoursGroupe> listeCoursGroupeUtilisateur =this.creerListeCoursGroupeParUtilisateur(utilisateur);
                    seancesDeUtilisateur.add(seance);

                }
            }
        }
        return seancesDeUtilisateur;
    }

    public boolean changerStatutSeance(int pos, EtatSeance etatSeance){
        //TODO
        return false;
    }

}
