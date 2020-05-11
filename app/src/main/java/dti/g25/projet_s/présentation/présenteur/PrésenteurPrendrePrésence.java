package dti.g25.projet_s.présentation.présenteur;

import android.app.Activity;
import android.util.Log;

import dti.g25.projet_s.présentation.ContratVuePrésenteurPrendrePrésence;
import dti.g25.projet_s.présentation.modèle.Modèle;

public class PrésenteurPrendrePrésence implements ContratVuePrésenteurPrendrePrésence.IPrésenteurPrendrePrésence {

    private static final String EXTRA_POSITION_SEANCE= "dti.g25.projet_s.positionSécance";
    private int positionGroupe;
    private int itérateur = 0;
    private int positionSéeance;
    private String cléUtilisateur;

    Modèle modèle;
    Activity activité;
    ContratVuePrésenteurPrendrePrésence.IVuePrendrePrésence vue;


    /**
     * Constructeur du presenteur pour la prise de présemce
     * @param activité Activité dans laquelle le présenteur de la prise de présence sera placé
     * @param vue La vue qui est relié au présenteur de la prise de présence
     * @param modèle le modele du MVP
     */
    public PrésenteurPrendrePrésence(Activity activité, ContratVuePrésenteurPrendrePrésence.IVuePrendrePrésence vue, Modèle modèle) {
        this.activité=activité;
        this.vue=vue;
        this.modèle=modèle;
    }


    @Override
    public String getNomUtilisteur() {
        return modèle.getListeEtudiantsParCoursGroupe(positionGroupe).get(itérateur).getUsername();
    }

    @Override
    public void commencerPrendrePrésence(int positionSéeance, int positionGroupe, String cléUtilisateur) throws Exception {
        this.positionSéeance = positionSéeance;
        this.positionGroupe = positionGroupe;
        this.cléUtilisateur = cléUtilisateur;

        modèle.setCléUtilisateur(cléUtilisateur);
        modèle.rafraîchir();
        Log.d("nomUtilisateur", modèle.getListeEtudiantsParCoursGroupe(positionGroupe).get(itérateur).getUsername());
        vue.setTxtNomÉtudiant(modèle.getListeEtudiantsParCoursGroupe(positionGroupe).get(itérateur).getUsername());
    }

    @Override
    public void ajouterAbsence(boolean présence) {

        modèle.ajouterAbsenceParCourGroupe(présence, modèle.getListeEtudiantsParCoursGroupe(positionGroupe).get(itérateur), positionSéeance, positionGroupe);
        itérateur +=1;
        if(modèle.getListUtlisateurParCourGroupe(positionGroupe).size() -1 == itérateur)
            activité.finish();
        else
            vue.setTxtNomÉtudiant(modèle.getListeEtudiantsParCoursGroupe(positionGroupe).get(itérateur).getUsername());

    }

}
