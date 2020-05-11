package dti.g25.projet_s.domaine.entité;


public class LibelleCours {

    private final String TITRE;
    private final String CODE;
    private final String SIGLE;


    public LibelleCours(String TITRE, String CODE, String sigle) {
        this.TITRE = TITRE;
        this.CODE = CODE;
        this.SIGLE = sigle;
    }

    public String getTITRE() {
        return TITRE;
    }

    public String getSIGLE() { return SIGLE; }

    public String getCODE() {
        return CODE;
    }

    public String getTitreAbrégé() { return SIGLE + "-" + CODE; }
    @Override
    public String toString() {
        return TITRE + " : " + CODE;
    }
}
