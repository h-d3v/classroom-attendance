package dti.g25.projet_s.domaine.entité;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Seance {
    private CoursGroupe _coursGroupe;
    private Map<Utilisateur, Absence> utilisateurAbsenceMap;
    private EtatSeance _etat;
    private final String _heureDebut;
    private final String _heureFin;
    private final String _journee;
    private int _semaine;

    public Seance(String dateDebut, String dateFin, String journee, int semaine, CoursGroupe coursGroupe ){
        _coursGroupe=coursGroupe;
        _heureDebut=dateDebut;
        _heureFin=dateFin;
        _journee=journee;
        _semaine=semaine;
        utilisateurAbsenceMap=new HashMap<>();
        for (Utilisateur user : _coursGroupe.getParticipants()){
            utilisateurAbsenceMap.put(user,Absence.EST_PRESENT);
        }
        _etat=EtatSeance.PREVUE;
    }

    public void set_coursGroupe(CoursGroupe _coursGroupe) {
        this._coursGroupe = _coursGroupe;
    }

    public String get_heureDebut() {
        return _heureDebut;
    }

    public String get_heureFin() {
        return _heureFin;
    }

    public String get_journee() {
        return _journee;
    }

    public int get_semaine() {
        return _semaine;
    }

    public void set_semaine(int _semaine) {
        this._semaine = _semaine;
    }

    public CoursGroupe get_coursGroupe() {
        return _coursGroupe;
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
