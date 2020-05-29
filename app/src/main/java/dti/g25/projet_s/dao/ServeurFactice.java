package dti.g25.projet_s.dao;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.Horaire;
import dti.g25.projet_s.domaine.entité.LibelleCours;
import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.domaine.interacteurs.CréeationUtilisateur;

public class ServeurFactice {

    public List<CoursGroupe> ObtenirCourGroupe(String uneClé) {
        List<CoursGroupe> listeCourGroupe = new ArrayList<>();
        List<Utilisateur> listeParticipant = new ArrayList<>();
        if(uneClé != null){
            if(uneClé.equals("uneCléProf") || uneClé.equals("uneCléÉlèves")) {

                List<Seance> listeSeance;
                listeParticipant.add(new Utilisateur("Jacke", Role.ÉLÈVE));
                listeParticipant.add(new Utilisateur("Bob", Role.ÉLÈVE));
                listeParticipant.add(new Utilisateur("Gustave", Role.ÉLÈVE));
                listeParticipant.add(new Utilisateur("Sakurai", Role.ÉLÈVE));
                listeParticipant.add(new Utilisateur("ReanuKeeves", Role.PROFESSEUR));


                for (int i = 0; i < 3; i++) {
                    listeSeance = new ArrayList<>();
                    listeCourGroupe.add(new CoursGroupe(new LibelleCours("ART DRAMATIQUE", "BC" + i), i));
                    listeCourGroupe.get(i).setParticipants(listeParticipant);
                    listeSeance.add(new Seance(listeCourGroupe.get(i), new Horaire(00.0f, 5.5f, "Mercredi")));
                    listeCourGroupe.get(i).setListeSeances(listeSeance);
                }
            }
        }

        return listeCourGroupe;
    }

    public Utilisateur chargerInfoUtilisateur (String uneClé) throws Exception {
        Utilisateur unUtilisateur = null;
        if(uneClé != null) {
            if (uneClé.equals("uneCléÉlèves"))
                unUtilisateur = new CréeationUtilisateur().CréerUtilisateur(1, "Sakurai", Role.ÉLÈVE);
            if (uneClé.equals("uneCléProf"))
                unUtilisateur = new CréeationUtilisateur().CréerUtilisateur(2,"ReanuKeeves", Role.PROFESSEUR);
        }
        return unUtilisateur;
    }

    public static String tenterConnexion(String nomUtilisateur, String MotDePasse) {
        String uneClé = null;
        if (nomUtilisateur.equals("ReanuKeeves") && MotDePasse.equals("Matrix4"))
            uneClé = "uneCléProf";
        else if (nomUtilisateur.equals("Sakurai") && MotDePasse.equals("Kirby"))
            uneClé = "uneCléÉlèves";

        return uneClé;
    }

}
