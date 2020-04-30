package dti.g25.projet_s.domaine.entites;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Sceance {
    private CoursGroupe _coursGroupe;
    private LinkedList<Absence> _absenceListe;
    private String _etat;
    private LocalDateTime _dateTime;

    public Sceance(){
        _coursGroupe=null;
        _absenceListe=null;
        _etat=null;
        _dateTime=null;
    }

    public CoursGroupe get_coursGroupe() {
        return _coursGroupe;
    }

    public LocalDateTime get_dateTime() {
        return _dateTime;
    }

    public void set_dateTime(LocalDateTime _dateTime) {
        this._dateTime = _dateTime;
    }

    public String get_etat() {
        return _etat;
    }

    public void set_etat(String _etat) {
        this._etat = _etat;
    }

    public LinkedList<Absence> get_absenceListe() {
        return _absenceListe;
    }

    public void set_absenceListe(LinkedList<Absence> _absenceListe) {
        this._absenceListe = _absenceListe;
    }

    public void set_coursGroupe(CoursGroupe _coursGroupe) {
        this._coursGroupe = _coursGroupe;
    }
}
