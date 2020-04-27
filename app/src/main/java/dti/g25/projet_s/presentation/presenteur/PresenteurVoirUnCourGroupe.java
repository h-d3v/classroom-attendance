package dti.g25.projet_s.presentation.presenteur;

import android.app.Activity;

import dti.g25.projet_s.domaine.entites.LibelleCours;
import dti.g25.projet_s.presentation.contratVuePresenteur.ContratVpVoirUnCoursGroupe;
import dti.g25.projet_s.presentation.modele.Modele;

public class PresenteurVoirUnCourGroupe implements ContratVpVoirUnCoursGroupe.IPrensenteurVoirCourGroupe {

    private Modele _modele;
    private ContratVpVoirUnCoursGroupe.IVueVoirCoursGroupe _vue;
    private Activity _activite;
    private int _positionCoursGroupe;


    /**
     * Constructeur du presenteur
     * @param activite Activité dans laquelle le présenteur agis qui sera l'activité pricipale
     * @param vue La vue qui est relié au présenteur, qui sera la vue principale
     * @param modele le modele du MVP
     */
    public PresenteurVoirUnCourGroupe(Activity activite, ContratVpVoirUnCoursGroupe.IVueVoirCoursGroupe vue, Modele modele, int positionCoursGroupe) {
        _activite=activite;
        _modele=modele;
        _vue=vue;
        _positionCoursGroupe=positionCoursGroupe;
    }



    public LibelleCours getLibelleCours(){
        return _modele.getCourGroupeParPos(_positionCoursGroupe).getLibelleCours();
    }

    public int getNumeroCours(){
        return _modele.getCourGroupeParPos(_positionCoursGroupe).getNumeroGroupe();
    }


    @Override
    public void requeteVoirUnSeance() {
        //TODO
    }

    @Override
    public void requeteVoirListeEleves() {
        //TODO
    }


}
