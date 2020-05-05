package dti.g25.projet_s.domaine.entité;


import java.util.Map;

public class Seance {
    private final CoursGroupe _coursGroupe;
    private Map<Utilisateur, Absence> utilisateurAbsenceMap;
    private EtatSeance _etat;
    private Horaire _horaire;

    public Seance(CoursGroupe coursGroupe ){
        _coursGroupe=coursGroupe;
        /*for (Utilisateur user : _coursGroupe.getParticipants()){
            utilisateurAbsenceMap.put(user,Absence.EST_PRESENT);
        }*/
        _etat=EtatSeance.PREVUE;
    }

    public CoursGroupe get_coursGroupe() {
        return _coursGroupe;
    }

    public Horaire get_horaire() {
        return _horaire;
    }

    public void set_horaire(Horaire _horaire) {
        this._horaire = _horaire;
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
