package dti.g25.projet_s.domaine.entité;

import java.util.List;

public class CoursGroupe {

    private final LibelleCours libelleCours;
    private final int numeroGroupe;
    private int id;
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

    public CoursGroupe(LibelleCours libelleCours, int numeroGroupe, int id) {
        this.libelleCours = libelleCours;
        this.numeroGroupe = numeroGroupe;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoursGroupe that = (CoursGroupe) o;

        if (numeroGroupe != that.numeroGroupe) return false;
        return libelleCours != null ? libelleCours.equals(that.libelleCours) : that.libelleCours == null;
    }


}
