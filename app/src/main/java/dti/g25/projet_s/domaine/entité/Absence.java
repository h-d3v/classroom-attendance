package dti.g25.projet_s.domaine.entité;

public class Absence {
    Utilisateur étudiantAbsent;
    //Seance seanceManquée;
    int nombreHeures;

    public Absence(int nbHeures, Utilisateur étudiant){
        this.étudiantAbsent=étudiant;
        this.nombreHeures=nbHeures;
    }

    public Utilisateur getÉtudiantAbsent() {
        return étudiantAbsent;
    }
    public void setÉtudiantAbsent(Utilisateur étudiantAbsent) {
        this.étudiantAbsent = étudiantAbsent;
    }
    public double getNombreHeures() {
        return nombreHeures;
    }
    public void setNombreHeures(int nombreHeures) {
        this.nombreHeures = nombreHeures;
    }
    public void ajoutAbsence(int nbHeures){
        nombreHeures += nbHeures;
    }

    @Override
    public String toString() {
        return "Nombre d'heures d'absence: " + nombreHeures + " - Eleve: "+étudiantAbsent;
    }
}
