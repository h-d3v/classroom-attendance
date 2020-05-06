package dti.g25.projet_s.domaine.entité;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import dti.g25.projet_s.domaine.entité.Horaire;

public class Seance {
    private CoursGroupe _coursGroupe;
    private List<Absence> _listeAbsence;
    private EtatSeance _etat;
    private Horaire _horaires;

    public Seance(CoursGroupe coursGroupe, Horaire horaires){
        _coursGroupe=coursGroupe;
        _listeAbsence = new ArrayList<Absence>();
        _horaires = horaires;

        for (Utilisateur user : _coursGroupe.getParticipants()){
            _listeAbsence.add(new Absence(user, true));
        }
        _etat=EtatSeance.PREVUE;
    }

    public List<Absence> get_listeAbsence() {
        return _listeAbsence;
    }

    public void set_listeAbsence(List<Absence> _listeAbsence) {
        this._listeAbsence = _listeAbsence;
    }

    public Horaire get_horaires() {
        return _horaires;
    }

    public void set_horaires(Horaire _horaires) {
        this._horaires = _horaires;
    }

    public void set_coursGroupe(CoursGroupe _coursGroupe) {
        this._coursGroupe = _coursGroupe;
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

    public List<Absence> getListeAbsence() {
        return _listeAbsence;
    }

    public void setListeAbsence(List<Absence> listeAbsence) {
        this._listeAbsence = listeAbsence;
    }

}
