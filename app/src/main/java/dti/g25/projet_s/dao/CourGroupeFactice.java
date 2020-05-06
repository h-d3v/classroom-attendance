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

public class CourGroupeFactice {

    public List<CoursGroupe> ObtenirCourGroupe(){
        List<CoursGroupe> listeCourGroupe = new ArrayList<>();
        List<Utilisateur> listeParticipant = new ArrayList<>();
        List<Seance> listeSeance;
        listeParticipant.add(new Utilisateur("Jacke", Role.ÉLÈVE));
        listeParticipant.add(new Utilisateur("Bob", Role.ÉLÈVE));
        listeParticipant.add(new Utilisateur("Gustave", Role.ÉLÈVE));
        listeParticipant.add(new Utilisateur("René", Role.PROFESSEUR));


        for(int i =0; i < 3; i++) {
            listeSeance = new ArrayList<>();
            listeCourGroupe.add(new CoursGroupe(new LibelleCours("ART", "BC" + i), i));
            listeCourGroupe.get(i).setParticipants(listeParticipant);
            listeSeance.add(new Seance( listeCourGroupe.get(i), new Horaire(00.0,5.5, "Mercredi")));
            listeCourGroupe.get(i).setListeSeances(listeSeance);
        }

        return listeCourGroupe;
    }

}
