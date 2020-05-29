package dti.g25.projet_s.présentation.présenteur;

import android.app.Activity;
import android.content.Intent;

import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.présentation.ContratVuePrésenteurVoirListeÉlèves;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.ui.activité.VoirUnEleve;

public class PrésenteurVoirListeÉlèves implements ContratVuePrésenteurVoirListeÉlèves.IPrésenteurVoirListeÉlèves {
    private static final String EXTRA_CLÉ_CONNEXION = "dti.g25.projet_s.cléConnexion";
    private static final String EXTRA_POSITION_GROUPE = "dti.g25.projet_s.positionCourGroupe";
    private static final String EXTRA_POSITION_SEANCE = "dti.g25.projet_s.positionSeance";
    private static final String EXTRA_POSITION_ÉLÈVES = "dti.g25.projet_s.positionÉlèves";
    private static final String EXTRA_POSITION_PRÉSENCE = "dti.g25.projet_s.présence";
    private static final int RESQUEST_CODE_VOIR_ELEVES = 33;


    private Modèle modèle;
    private ContratVuePrésenteurVoirListeÉlèves.IVueVoirListeÉlèves vue;
    private Activity activité;
    private int positionCoursGroupe;
    private int positionSeance;
    private String cléUtilisateur;
    private boolean peutPrendrePrésence = true;

    public PrésenteurVoirListeÉlèves(Activity activité, ContratVuePrésenteurVoirListeÉlèves.IVueVoirListeÉlèves vue, Modèle modèle) {
        this.activité=activité;
        this.modèle=modèle;
        this.vue=vue;
    }

    @Override
    public int getNombresItems() {
        return modèle.getListeEtudiantsParCoursGroupe(positionCoursGroupe).size();
    }

    @Override
    public Utilisateur getUtilisateurParPosition(int position) {
        return modèle.getListeEtudiantsParCoursGroupe(positionCoursGroupe).get(position);
    }

    @Override
    public String getPrésenceUtilisateurParPos(int position) throws Exception {
        if(modèle.getSeanceParCourGroupe(positionCoursGroupe, positionSeance).getListeAbsence().get(position).getPrésence())
            return "présent";
        return "absent";
    }

    @Override
    public void requeteVoirÉlèves(int position) {
        Intent intentVoirSéance = new Intent(activité, VoirUnEleve.class);
        intentVoirSéance.putExtra(EXTRA_CLÉ_CONNEXION, cléUtilisateur);
        intentVoirSéance.putExtra(EXTRA_POSITION_GROUPE, positionCoursGroupe);
        intentVoirSéance.putExtra(EXTRA_POSITION_SEANCE, positionSeance);
        intentVoirSéance.putExtra(EXTRA_POSITION_ÉLÈVES, position);
        activité.startActivityForResult(intentVoirSéance, RESQUEST_CODE_VOIR_ELEVES);
    }

    @Override
    public void commencerListeÉlèvesPrésence(int positionSeance, int positionCoursGroupe, String cléUtilisateur) throws Exception {
        this.positionSeance = positionSeance;
        this.positionCoursGroupe = positionCoursGroupe;
        this.cléUtilisateur = cléUtilisateur;
        modèle.setCléUtilisateur(this.cléUtilisateur);
        modèle.rafraîchir();
        if (positionSeance == -1) {
            peutPrendrePrésence = false;
        }
        vue.rafraichir();
    }

    @Override
    public  void onActivityResult(int requestCode, int resultCode, Intent data) throws Exception {
        if (requestCode == RESQUEST_CODE_VOIR_ELEVES && resultCode == Activity.RESULT_OK) {
           boolean présence =  data.getBooleanExtra(EXTRA_POSITION_PRÉSENCE, true);
           int positionÉlèves = data.getIntExtra(EXTRA_POSITION_ÉLÈVES, -1);

           if(positionÉlèves > -1)
                modèle.ajouterAbsenceParCourGroupe(présence, getUtilisateurParPosition(positionÉlèves), positionSeance, positionCoursGroupe);

           vue.rafraichir();
        }

    }

    @Override
    public Boolean getpeutPrendrePrésence() {
        return peutPrendrePrésence;
    }
    
    @Override
    public void rafraîchir() {
        vue.rafraichir();
    }

}
