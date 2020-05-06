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

    public Double getHeureDebut() {
        return heureDebut;
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
}
