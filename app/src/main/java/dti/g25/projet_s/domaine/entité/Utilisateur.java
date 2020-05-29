package dti.g25.projet_s.domaine.entité;

public class Utilisateur {

    private int id;
    private String nom;
    private String mdp;
    private Role rôle;
    private int id;

    /**
     * contructeur vide
     */
    public Utilisateur() {
    }

    /**
     * Contructeur complet sans mot de passe
     * @param nom
     * @param rôle
     */
    public Utilisateur(String nom , Role rôle) {
        this.nom = nom;
        this.rôle = rôle;
    }
    /**
     * Contructeur complet avec mot de passe
     * @param nom
     * @param rôle
     */
    public Utilisateur(String nom , Role rôle, String mdp) {
        this.nom = nom;
        this.rôle = rôle;
        this.mdp=mdp;
    }

    public Utilisateur(int id, String nom , Role rôle) {
        this.nom = nom;
        this.rôle = rôle;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Utilisateur{" +
                "nom='" + nom + '\'' +
                ", rôle=" + rôle +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Utilisateur that = (Utilisateur) o;

        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (mdp != null ? !mdp.equals(that.mdp) : that.mdp != null) return false;
        return rôle == that.rôle;
    }


}
