package dti.g25.projet_s.domaine.entité;


import androidx.annotation.NonNull;

import static java.lang.String.valueOf;

public class Horaire {

    private Double heureDebut;
    private Double heureFin;
    private String journee;

    public Horaire(Double heureDebut, Double heureFin, String journee) {
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.journee = journee;
    }

    public Horaire(double heureDébut, double heureFin) {
        this.heureDebut = heureDébut;
        this.heureFin = heureFin;
    }

    public Double getHeureDebut() {
        return heureDebut;
    }


    public String getHeureDebutString() {
        return convertirDoubleEnHeure(heureDebut);
    }

    public String getHeureFinString() {
        return convertirDoubleEnHeure(heureFin);
    }

    public Double getHeureFin() {
        return heureFin;
    }

    public String getJournee() {
        return journee;
    }

    public String convertirDoubleEnHeure(Double unDouble){
        String unString = null;
        int heure = 0;
        while(unDouble > 1){
            unDouble -= 1;
            heure +=1;
        }
        if(heure < 10) {
            if (unDouble == 0) {
                unString = "0" + heure + ":00";
            } else if (unDouble == 0.25) {
                unString = "0" + heure + ":15";
            } else if (unDouble == 0.5) {
                unString ="0" + heure + ":30";
            } else if (unDouble == 0.75) {
                unString ="0" + heure + ":45";
            }
        } else {
            if (unDouble == 0) {
                unString = heure + ":00";
            } else if (unDouble == 0.25) {
                unString = heure + ":15";
            } else if (unDouble == 0.5) {
                unString = heure + ":30";
            } else if (unDouble == 0.75) {
                unString = heure + ":45";
            }
        }

        return unString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Horaire horaire = (Horaire) o;

        if (heureDebut != null ? !heureDebut.equals(horaire.heureDebut) : horaire.heureDebut != null) return false;
        if (heureFin != null ? !heureFin.equals(horaire.heureFin) : horaire.heureFin != null) return false;
        return journee != null ? journee.equals(horaire.journee) : horaire.journee == null;
    }


}
