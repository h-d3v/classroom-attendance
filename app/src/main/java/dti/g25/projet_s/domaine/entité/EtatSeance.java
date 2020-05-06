package dti.g25.projet_s.domaine.entité;

public enum EtatSeance {
    PREVUE,
    ANULLEE;

    @Override
    public String toString(){
        if (this == EtatSeance.ANULLEE) {
            return "Seance anullee";
        } else {
            return "Seance prevue";
        }

    }
}
