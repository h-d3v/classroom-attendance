package dti.g25.projet_s.domaine.interacteurs;

import dti.g25.projet_s.domaine.entité.Horaire;

public class GestionHoraire implements IGestionHoraire {

    public Horaire créerHoraire(double heureDébut, double heureFin) {

        return new Horaire(heureDébut, heureFin);
    }
}
