package dti.g25.projet_s.presentation.presenteur;

import android.app.Activity;
import android.content.Intent;

import dti.g25.projet_s.activities.VoirUnCourGroupeActivity;
import dti.g25.projet_s.domaine.entites.LibelleCours;
import dti.g25.projet_s.presentation.contratVuePresenteur.ContratVpVoirUnCoursGroupe;
import dti.g25.projet_s.presentation.modele.Modele;
import dti.g25.projet_s.presentation.vue.VueVoirBtnListeCoursGroupes;

public class PresenteurBtnVoirListeCoursGroupe {
    private Modele _modele;
    private VueVoirBtnListeCoursGroupes _vue;
    private Activity _activite;

    /**
     * Constructeur du presenteur
     * @param activite Activité dans laquelle le présenteur agis qui sera l'activité pricipale
     * @param vue La vue qui est relié au présenteur, qui sera la vue principale
     * @param modele le modele du MVP
     */
    public PresenteurBtnVoirListeCoursGroupe(Activity activite, VueVoirBtnListeCoursGroupes vue, Modele modele) {
        _activite=activite;
        _modele=modele;
        _vue=vue;
    }

    public void requeteVoirUnCourGroupe(){
        Intent intentModif=new Intent(_activite, VoirUnCourGroupeActivity.class);
        intentModif.putExtra("EXTRA_POSITION_PROJET", 0);
        _activite.startActivityForResult(intentModif, 20);
    }


}
