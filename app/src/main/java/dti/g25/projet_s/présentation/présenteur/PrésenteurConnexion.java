package dti.g25.projet_s.présentation.présenteur;

import android.app.Activity;

import dti.g25.projet_s.dao.UtlisateurFactice;
import dti.g25.projet_s.présentation.ContratVuePrésenteurConnexion;

public class PrésenteurConnexion implements ContratVuePrésenteurConnexion.IPrésenteurConnexion {

    Modèle modèle;
    Activity activité;
    ContratVuePrésenteurConnexion.IVueConnexion vue;

    /**
     * Constructeur du presenteur pour la connexion
     * @param activité Activité dans laquelle le présenteur de connexion sera placé
     * @param vue La vue qui est relié au présenteur de la connexion
     * @param modèle le modele du MVP
     */
    public PrésenteurConnexion(Activity activité, ContratVuePrésenteurConnexion.IVueConnexion vue, Modèle modèle) {
        this.activité=activité;
        this.vue=vue;
        this.modèle=modèle;
    }

    @Override
    public Boolean tenterConnexion(String nomUtilasiteur, String motDePasse) {

        return new UtlisateurFactice().tenterConnexion(nomUtilasiteur, motDePasse);

    }

}
