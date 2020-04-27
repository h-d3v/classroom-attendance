package dti.g25.projet_s.domaine.entité;

public class Utilisateur {

    private String nom;
    private String mdp;
    private Role rôle;

    /**
     * contructeur vide
     */
    public Utilisateur() {
    }

    /**
     * Contructeur complet
     * @param nom
     * @param mdp
     * @param rôle
     */
    public Utilisateur(String nom, String mdp, Role rôle) {
        this.nom = nom;
        this.mdp = mdp;
        this.rôle = rôle;
    }

    public String getUsername() {
        return nom;
    }

    public String getMdp() {
        return mdp;
    }

    public Role getRôle() {
        return rôle;
    }

    public void setNom (String nom) {
        this.nom = nom;
    }

    public void setMdp (String mdp) {
        this.mdp = mdp;
    }

    public void setRôle (Role rôle) {
        this.rôle = rôle;
    }

}
