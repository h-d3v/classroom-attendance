package dti.g25.projet_s.domaine.entité;

public class Absence{

    public Utilisateur utilisateur;
    public Boolean présence;
    public float retard;

    public Absence(Utilisateur utilisateur, Boolean présence) {
        this.utilisateur = utilisateur;
        this.présence = présence;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Boolean getPrésence() {
        return présence;
    }

    public void setPrésence(Boolean présence) {
        this.présence = présence;
    }

    public float getRetard() {
        return retard;
    }

    public void setRetard(float retard) {
        this.retard = retard;
    }
}
