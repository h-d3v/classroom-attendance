package dti.g25.projet_s.présentation.présenteur;

import android.app.Activity;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.ui.activité.IContratVoirUneSeance;

public class PresenteurVoirUneSeance implements IContratVoirUneSeance.IPresenteurVoirUneSeance {

    private Modèle _modele;
    private IContratVoirUneSeance.IVueVoirUneseance _vue;
    private Activity _activite;
    private int _positionSeance;

    /**
     * Constructeur du presenteur
     * @param activite Activité dans laquelle le présenteur agis qui sera l'activité pricipale
     * @param vue La vue qui est relié au présenteur, qui sera la vue principale
     * @param modele le modele du MVP
     * @paran la position d'une seance dans l'acitvite VoirLesSeances
     */

    public PresenteurVoirUneSeance(Modèle modele, IContratVoirUneSeance.IVueVoirUneseance vue, Activity activite, int positionSeance) {
        this._modele = modele;
        this._vue = vue;
        this._activite = activite;
        this._positionSeance=positionSeance;
    }




    @Override
    public void requeteModifierSatatutSeance() {

    }
}
