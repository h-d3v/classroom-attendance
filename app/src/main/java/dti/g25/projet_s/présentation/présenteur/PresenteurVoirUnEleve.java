package dti.g25.projet_s.présentation.présenteur;

import android.app.Activity;

import java.util.List;

import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.vue.IContratVuePrésenteurVoirUnEleve;

public class PresenteurVoirUnEleve implements IContratVuePrésenteurVoirUnEleve.IPrésenteurVoirUnEleve {

    private IContratVuePrésenteurVoirUnEleve.IPrésenteurVoirUnEleve vue;
    private Modèle modèle;
    private Activity activity;

    public PresenteurVoirUnEleve(Activity activity, IContratVuePrésenteurVoirUnEleve.IPrésenteurVoirUnEleve vue, Modèle modèle) {
        this.activity=activity;
        this.vue=vue;
        this.modèle=modèle;
    }

    public String getNomUtilisateur(){
        Utilisateur user = modèle.getUtilisateurParIndex(0);
        return user.getUsername();
    }
}
