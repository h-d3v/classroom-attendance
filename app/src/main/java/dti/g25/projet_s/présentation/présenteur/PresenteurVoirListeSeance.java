package dti.g25.projet_s.présentation.présenteur;

import android.app.Activity;
import android.content.Intent;

import dti.g25.projet_s.domaine.entité.EtatSeance;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.présentation.ContratVpVoirListeSeances;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.ui.activité.ConnexionActivité;
import dti.g25.projet_s.ui.activité.VoirListeSeancesActivity;

public class PresenteurVoirListeSeance implements ContratVpVoirListeSeances.IPresenteurVoirListeSeances {

    private Modèle _modele;
    private ContratVpVoirListeSeances.IVueVoirListeSeance _vue;
    private Activity _activite;
    private int _positionSeance;


    /**
     * Constructeur du presenteur
     * @param activite Activité dans laquelle le présenteur agis
     * @param vue La vue qui est relié au présenteur
     * @param modele le modele du MVP de l'appi
     */
    public PresenteurVoirListeSeance(Activity activite, ContratVpVoirListeSeances.IVueVoirListeSeance vue, Modèle modele) {
        _activite=activite;
        _modele=modele;
        _vue=vue;
    }

    public void commencerVoirListeSeance(String cléUtilisateur, int position){

    }

    @Override
    public void requeteConsulterCoursGroupe(int position) {

    }

    @Override
    public void changerEtat(int position, EtatSeance etatSeance) {
        //_modele.changerEtatSeance();
        //TODO
    }

    @Override
    public void requeteModifierPresence(int position) {

    }

    public Seance getSeanceParPos(int pos){
        if(_modele.getSeanceParPos(pos)!= null) return _modele.getSeanceParPos(pos);
        return null;
    }

    @Override
    public int getNbSeancesModele() {
        if(_modele.getListeSeance()!=null){
            return _modele.getListeSeance().size();
        }
        return 0;
    }



}
