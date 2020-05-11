package dti.g25.projet_s.présentation.présenteur;

import android.app.Activity;

import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.présentation.IContratVuePrésenteurVoirUnEleve;
import dti.g25.projet_s.présentation.modèle.dao.Modèle;

public class PresenteurVoirUnEleve implements IContratVuePrésenteurVoirUnEleve.IPrésenteurVoirUnEleve {

    private IContratVuePrésenteurVoirUnEleve.IVueVoirUnEleve vue;
    private Modèle modèle;
    private Activity activity;

    public PresenteurVoirUnEleve(Activity activity, IContratVuePrésenteurVoirUnEleve.IVueVoirUnEleve vue, Modèle modèle) {
        this.activity=activity;
        this.vue=vue;
        this.modèle=modèle;
    }

    public String getNomUtilisateur(){
        Utilisateur user = modèle.getUtilisateurParIndex(0);
        return user.getUsername();
    }
}
