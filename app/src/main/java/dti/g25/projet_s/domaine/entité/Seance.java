package dti.g25.projet_s.domaine.entité;

import java.time.LocalDateTime;
import java.util.Map;

public class Seance {
    private final CoursGroupe _coursGroupe;
    private Map<Utilisateur, Absence> utilisateurAbsenceMap;
    private EtatSeance _etat;
    private final LocalDateTime _dateTime;

    public Seance(CoursGroupe coursGroupe, LocalDateTime localDateTime ){
        _coursGroupe=coursGroupe;
        _dateTime=localDateTime;
        for (Utilisateur user : _coursGroupe.getParticipants()){
            utilisateurAbsenceMap.put(user,Absence.EST_PRESENT);
        }
        _etat=EtatSeance.PREVUE;
    }



    public CoursGroupe get_coursGroupe() {
        return _coursGroupe;
    }

    public LocalDateTime get_dateTime() {
        return _dateTime;
    }



    public EtatSeance get_etat() {
        return _etat;
    }

    public void set_etat(EtatSeance _etat) {
        this._etat = _etat;
    }

    public Map<Utilisateur, Absence> getUtilisateurAbsenceMap() {
        return utilisateurAbsenceMap;
    }

    public void setUtilisateurAbsenceMap(Map<Utilisateur, Absence> utilisateurAbsenceMap) {
        this.utilisateurAbsenceMap = utilisateurAbsenceMap;
    }

}
