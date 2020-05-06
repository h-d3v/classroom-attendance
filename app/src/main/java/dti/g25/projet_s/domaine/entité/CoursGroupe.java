package dti.g25.projet_s.domaine.entité;

import java.util.List;

import dti.g25.projet_s.domaine.entité.LibelleCours;

public class CoursGroupe {

    private final LibelleCours libelleCours;
    private final int numeroGroupe;
    private List<Utilisateur> participants;
    private List<Seance> listeSeances;
    private List<Horaire> listeHoraire;

    public List<Seance> getListeSeances() {
        return listeSeances;
    }

    public void setListeSeances(List<Seance> listeSeances) {
        this.listeSeances = listeSeances;
    }

    public CoursGroupe(LibelleCours libelleCours, int numeroGroupe) {
        this.libelleCours = libelleCours;
        this.numeroGroupe = numeroGroupe;
    }

    public int getNumeroGroupe() {
        return numeroGroupe;
    }

    public List<Utilisateur> getParticipants() {
        return participants;
    }

    public List<Horaire> getListeHoraire() {
        return listeHoraire;
    }

    public void setListeHoraire(List<Horaire> listeHoraire) {
        this.listeHoraire = listeHoraire;
    }

    public void setParticipants(List<Utilisateur> participants) {
        this.participants = participants;
    }

    public LibelleCours getLibelleCours() {
        return libelleCours;
    }

    @Override
    public String toString() {
        return libelleCours +
                " Groupe: " + numeroGroupe;
    }
}
