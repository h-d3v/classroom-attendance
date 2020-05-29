package dti.g25.projet_s.présentation.présenteur;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import dti.g25.projet_s.domaine.entité.EtatSeance;
import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.IContratVoirUneSeance;

public class PresenteurVoirUneSeance implements IContratVoirUneSeance.IPresenteurVoirUneSeance {
    private static final int REQUEST_CODE_MODIFIER_ETAT=284;
    private static  String  EXTRA_ETAT_SEANCE="dti.g25.projet_s.etat_seance";
    private Modèle _modele;
    private IContratVoirUneSeance.IVueVoirUneseance _vue;
    private Activity _activite;
    private int _positionSeance;
    private int _positionGroupe;
    private String _cléUtilisateur;

    /**
     * Constructeur du presenteur
     * @param activite Activité dans laquelle le présenteur agis qui sera l'activité pricipale
     * @param vue La vue qui est relié au présenteur, qui sera la vue principale
     * @param modele le modele du MVP
     * @param positionSeance  la position d'une seance dans l'acitvite VoirLesSeances
     */

    public PresenteurVoirUneSeance(Modèle modele, IContratVoirUneSeance.IVueVoirUneseance vue, Activity activite, int positionSeance) {
        this._modele = modele;
        this._vue = vue;
        this._activite = activite;
        this._positionSeance=positionSeance;
    }


    public boolean estAutoriseAModifierStatutSeance(){
        if(_modele.getUtilisateurConnecte()!=null && _modele.getUtilisateurConnecte().getRôle()== Role.PROFESSEUR){

            return true;
        }else
        return false;
    }

    @Override
    public Seance getSeance() throws Exception {
        if(_positionGroupe == -1)
            return _modele.getSeanceParPos(_positionSeance);
        return _modele.getSeanceParCourGroupe(_positionSeance);
    }

    @Override
    public Activity get_activite(){
        return _activite;
    }

    @Override
    public void commencerVoirSéance(int positionGroupe, int positionSéance, String cléUtilisateur) throws Exception {
        _positionSeance = positionSéance;
        _positionGroupe = positionGroupe;
        _cléUtilisateur = cléUtilisateur;

        _modele.setCléConnexion(_cléUtilisateur);
        _modele.rafraîchir();

        _vue.afficherEstPrévue("Statut: "+ getSeance().get_etat().toString());
        _vue.afficherHoraire("Debut:"+getSeance().get_horaires().getHeureDebutString()+" Fin:"+ getSeance().get_horaires().getHeureFinString()+" Journee:"+getSeance().get_horaires().getJournee());
        _vue.afficherLibelle(getSeance().get_coursGroupe().toString());
        _vue.autoriserProf(_modele.getRoleUtilsaiteurConnecté().equals(Role.PROFESSEUR));
    }


    @Override
    public void requeteModifierSatatutSeance() throws Exception {
        /**
         *         Intent intent= new Intent(_activite, popUp.class);
         *         _activite.startActivityForResult(intent, REQUEST_CODE_MODIFIER_ETAT);
         */
        if(getSeance().get_etat()==EtatSeance.PREVUE){
            _modele.setEtatSeance(_positionSeance,EtatSeance.ANULLEE);
        }
        else {
            _modele.setEtatSeance(_positionSeance, EtatSeance.PREVUE);
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) throws  Exception{
        if(requestCode==REQUEST_CODE_MODIFIER_ETAT && resultCode==Activity.RESULT_OK){
            _modele.getSeanceParPos(_positionSeance).set_etat(EtatSeance.valueOf(data.getStringExtra(EXTRA_ETAT_SEANCE)));
        }
    }

}
