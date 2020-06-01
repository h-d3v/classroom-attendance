package dti.g25.projet_s.présentation.présenteur;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.présentation.IContratVuePrésenteurVoirUnEleve;
import dti.g25.projet_s.présentation.modèle.Modèle;

public class PresenteurVoirUnEleve implements IContratVuePrésenteurVoirUnEleve.IPrésenteurVoirUnEleve {
    private static final String EXTRA_CLÉ_CONNEXION = "dti.g25.projet_s.cléConnexion";
    private static final String EXTRA_POSITION_PRÉSENCE = "dti.g25.projet_s.présence";
    private static final String EXTRA_POSITION_ÉLÈVES = "dti.g25.projet_s.positionÉlèves";

    private IContratVuePrésenteurVoirUnEleve.IVueVoirUnEleve vue;
    private Modèle modèle;
    private Activity activity;
    private int positionSeance;
    private int positionGroupe;
    private int positionÉlèves;
    private String cléUtilisateur;

    public PresenteurVoirUnEleve(Activity activity, IContratVuePrésenteurVoirUnEleve.IVueVoirUnEleve vue, Modèle modèle) {
        this.activity=activity;
        this.vue=vue;
        this.modèle=modèle;
    }

    @Override
    public void requêteAjouterAbsence(boolean b) {
        Intent donnéesRetour=new Intent();
        donnéesRetour.putExtra(EXTRA_POSITION_PRÉSENCE, b);
        donnéesRetour.putExtra(EXTRA_POSITION_ÉLÈVES, positionÉlèves);
        activity.setResult(activity.RESULT_OK, donnéesRetour);
        activity.finish();
    }

    @Override
    public void commencerVoirUnÉlèves(int positionSeance, int positionGroupe, int positionÉlèves, String cléUtilisateur) throws Exception {
        this.positionSeance =positionSeance;
        this.positionGroupe = positionGroupe;
        this.positionÉlèves = positionÉlèves;
        this.cléUtilisateur = cléUtilisateur;
        if(positionSeance == -1) {
            vue.setVisibilitéPrésence(false);
        }
        modèle.setCléConnexion(this.cléUtilisateur);
        vue.setNomUtilisateur(modèle.getListeEtudiantsParCoursGroupe().get(positionÉlèves).getUsername());
    }

    @Override
    public String getNomUtilisateur(int position){
        Utilisateur leUser = modèle.getListeEtudiantsParCoursGroupe().get(position);
        return leUser.getUsername();
    }

    @Override
    public void rafraichir(){
        vue.setUsername(modèle.getListeEtudiantsParCoursGroupe().get(this.positionÉlèves).getUsername());
    }

}
