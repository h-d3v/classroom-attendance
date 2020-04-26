package dti.g25.projet_s.domaine.entites;

import java.util.List;

public class CoursGroupe {

    private final LibelleCours libelleCours;
    private final int numeroGroupe;
    private List<Utilisateur> participants;

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

    public void setParticipants(List<Utilisateur> participants) {
        this.participants = participants;
    }

    public LibelleCours getLibelleCours() {
        return libelleCours;

    }
}
