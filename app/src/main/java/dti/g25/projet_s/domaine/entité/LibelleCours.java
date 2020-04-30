package dti.g25.projet_s.domaine.entité;


public class LibelleCours {

    private final String TITRE;
    private final String CODE;


    public LibelleCours(String TITRE, String CODE) {
        this.TITRE = TITRE;
        this.CODE = CODE;
    }

    public String getTITRE() {
        return TITRE;
    }

    public String getCODE() {
        return CODE;
    }

    @Override
    public String toString() {
        return TITRE + " : " + CODE;
    }
}
