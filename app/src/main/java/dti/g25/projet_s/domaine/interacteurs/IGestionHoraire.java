package dti.g25.projet_s.domaine.interacteurs;

import dti.g25.projet_s.domaine.entité.Horaire;

public interface IGestionHoraire {

    Horaire créerHoraire(float heureDébut, float heureFin, String date);
}
