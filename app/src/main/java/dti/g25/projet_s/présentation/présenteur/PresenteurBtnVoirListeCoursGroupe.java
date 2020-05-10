package dti.g25.projet_s.présentation.présenteur;

import android.app.Activity;
import android.content.Intent;

import dti.g25.projet_s.présentation.modèle.dao.Modèle;
import dti.g25.projet_s.ui.activité.VoirUnCourGroupeActivity;
import dti.g25.projet_s.présentation.vue.VueVoirBtnListeCoursGroupes;

public class PresenteurBtnVoirListeCoursGroupe {
    private Modèle _modele;
    private VueVoirBtnListeCoursGroupes _vue;
    private Activity _activite;

    /**
     * Constructeur du presenteur
     * @param activite Activité dans laquelle le présenteur agis qui sera l'activité pricipale
     * @param vue La vue qui est relié au présenteur, qui sera la vue principale
     * @param modele le modele du MVP
     */
    public PresenteurBtnVoirListeCoursGroupe(Activity activite, VueVoirBtnListeCoursGroupes vue, Modèle modele) {
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
