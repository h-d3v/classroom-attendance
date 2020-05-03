package dti.g25.projet_s.dao;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.LibelleCours;
import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Utilisateur;

public class CourGroupeFactice {

    public List<CoursGroupe> ObtenirCourGroupe(){
        List<CoursGroupe> listeCourGroupe = new ArrayList<>();
        List<Utilisateur> listeParticipant = new ArrayList<>();
        listeParticipant.add(new Utilisateur("Jacke", Role.ÉLÈVE));
        listeParticipant.add(new Utilisateur("Bob", Role.ÉLÈVE));
        listeParticipant.add(new Utilisateur("Gustave", Role.ÉLÈVE));
        listeParticipant.add(new Utilisateur("René", Role.PROFESSEUR));

        for(int i =0; i < 3; i++) {
            listeCourGroupe.add(new CoursGroupe(new LibelleCours("ART", "BC" + i), i));
            listeCourGroupe.get(i).setParticipants(listeParticipant);
        }

        return listeCourGroupe;
    }

}
