package dti.g25.projet_s.domaine.entité;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Seance {
    private final CoursGroupe _coursGroupe;
    private List<Absence> listeAbsence;
    private EtatSeance _etat;

    public Seance(CoursGroupe coursGroupe){
        _coursGroupe=coursGroupe;
        for (Utilisateur user : _coursGroupe.getParticipants()){
            listeAbsence.add(new Absence(user, true));
        }
        _etat=EtatSeance.PREVUE;
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
        return listeAbsence;
    }

    public void setListeAbsence(List<Absence> listeAbsence) {
        this.listeAbsence = listeAbsence;
    }

}
