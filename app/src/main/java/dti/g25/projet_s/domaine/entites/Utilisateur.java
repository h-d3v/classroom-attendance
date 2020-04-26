package dti.g25.projet_s.domaine.entites;

public class Utilisateur {

    private String nom;
    private  String motDePasse;
    private final Role ROLE;


    public Utilisateur(Role role) {
        this.ROLE=role;
    }

    public Utilisateur(String nom, String motDePasse, Role ROLE) {
        this.nom = nom;
        this.motDePasse = motDePasse;
        this.ROLE = ROLE;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Role getROLE() {
        return ROLE;
    }
}
