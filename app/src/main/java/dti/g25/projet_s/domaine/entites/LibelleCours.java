package dti.g25.projet_s.domaine.entites;


public class LibelleCours {

    private final String TITRE;
    private final String CODE;


    public LibelleCours(String TITRE, String CODE) {
        this.TITRE = TITRE;
        this.CODE = CODE;
    }


    public String getCODE() {
        return CODE;
    }

    public String getTITRE() {
        return TITRE;
    }
}
