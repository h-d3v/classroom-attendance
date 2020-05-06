package dti.g25.projet_s.présentation.présenteur;

import android.app.Activity;
import dti.g25.projet_s.présentation.ContratVuePrésenteurPrendrePrésence;
import dti.g25.projet_s.présentation.modèle.Modèle;

public class PrésenteurPrendrePrésence implements ContratVuePrésenteurPrendrePrésence.IPrésenteurPrendrePrésence {

    private static final String EXTRA_POSITION_SEANCE= "dti.g25.projet_s.positionSécance";
    private final int positionGroupe;
    private final int nombreEtudiant;
    private int itérateur;
    private int positionSéeance;

    Modèle modèle;
    Activity activité;
    ContratVuePrésenteurPrendrePrésence.IVuePrendrePrésence vue;


    /**
     * Constructeur du presenteur pour la prise de présemce
     * @param activité Activité dans laquelle le présenteur de la prise de présence sera placé
     * @param vue La vue qui est relié au présenteur de la prise de présence
     * @param modèle le modele du MVP
     */
    public PrésenteurPrendrePrésence(Activity activité, ContratVuePrésenteurPrendrePrésence.IVuePrendrePrésence vue, Modèle modèle, int positionGroupe, int positionSéeance) {
        this.activité=activité;
        this.vue=vue;
        this.modèle=modèle;
        this.positionGroupe=positionGroupe;
        nombreEtudiant=modèle.getListeEtudiantsParCoursGroupe(positionGroupe).size();
        itérateur = 0;
        this.positionSéeance = positionSéeance;
    }


    @Override
    public String getNomUtilisteur() {
        return modèle.getListUtlisateurParCourGroupe(positionGroupe).get(itérateur).getUsername();
    }

    @Override
    public void ajouterAbsence(boolean présence) {

        modèle.ajouterAbsence(présence, modèle.getListeEtudiantsParCoursGroupe(positionGroupe).get(itérateur), positionSéeance);
        itérateur +=1;
        if(modèle.getListUtlisateurParCourGroupe(positionGroupe).size() -1 == itérateur)
            activité.finish();
        else
            vue.setTxtNomÉtudiant(modèle.getListeEtudiantsParCoursGroupe(positionGroupe).get(itérateur).getUsername());

    }

}
