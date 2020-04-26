package dti.g25.projet_s.domaine.entité;

public class Utilisateur {

    private String username;
    private String mdp;
    private Role rôle;

    /**
     * contructeur vide
     */
    public Utilisateur() {
    }

    /**
     * Contructeur complet
     * @param username
     * @param mdp
     * @param rôle
     */
    public Utilisateur(String username, String mdp, Role rôle) {
        this.username = username;
        this.mdp = mdp;
        this.rôle = rôle;
    }

    public String getUsername() {
        return username;
    }

    public String getMdp() {
        return mdp;
    }

    public Role getRôle() {
        return rôle;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setRôle(Role rôle) {
        this.rôle = rôle;
    }

}
