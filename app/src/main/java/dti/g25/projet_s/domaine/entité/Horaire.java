package dti.g25.projet_s.domaine.entité;

public class Horaire {
    private int jour;
    private String heureDebut;
    private String heureFin;

    public Horaire(int jour, String heureDebut, String heureFin) {
        this.jour = jour;
        this.heureFin=heureFin;
        this.heureDebut=heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }


    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }
}
