package dti.g25.projet_s.domaine.entité;


import androidx.annotation.NonNull;

import java.util.Objects;

import static java.lang.String.valueOf;

public class Horaire {

    private float heureDebut;
    private float heureFin;
    private String date;
    private String journee;

    public Horaire(float heureDébut, float heureFin, String date) {
        this.heureDebut = heureDébut;
        this.heureFin = heureFin;
        this.date = date;
    }

    public float getHeureDebut() {
        return heureDebut;
    }


    public String getHeureDebutString() {
        return convertirDoubleEnHeure(heureDebut);
    }

    public String getHeureFinString() {
        return convertirDoubleEnHeure(heureFin);
    }

    public float getHeureFin() {
        return heureFin;
    }

    public String getJournee() {
        return journee;
    }

    public String getDate() { return date;}

    public String convertirDoubleEnHeure(float unDouble){
        String unString = "";
        int heure = 0;
        while(unDouble >= 1){
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

        if (!Objects.equals(heureDebut, horaire.heureDebut)) return false;
        if (!Objects.equals(heureFin, horaire.heureFin)) return false;
        return journee != null ? journee.equals(horaire.journee) : horaire.journee == null;
    }


}
