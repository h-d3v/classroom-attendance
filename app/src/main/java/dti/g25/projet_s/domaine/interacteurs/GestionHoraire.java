package dti.g25.projet_s.domaine.interacteurs;

import dti.g25.projet_s.domaine.entité.Horaire;

public class GestionHoraire implements IGestionHoraire {

    public Horaire créerHoraire(float heureDébut, float heureFin, String date) {

        return new Horaire(heureDébut, heureFin, date);
    }
}
