package dti.g25.projet_s.domaine.entité;


public class LibelleCours {

    private final String TITRE;
    private final String SIGLE;


    public LibelleCours(String TITRE, String sigle) {
        this.TITRE = TITRE;
        this.SIGLE = sigle;
    }

    public String getTITRE() {
        return TITRE;
    }

    public String getSigle() {
        return SIGLE;
    }

    @Override
    public String toString() {
        return TITRE + " : " + SIGLE;
    }
}
