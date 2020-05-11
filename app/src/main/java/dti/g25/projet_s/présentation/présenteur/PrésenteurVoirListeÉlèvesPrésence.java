package dti.g25.projet_s.présentation.présenteur;

import android.app.Activity;

import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.présentation.ContratVpVoirUnCoursGroupe;
import dti.g25.projet_s.présentation.ContratVuePrésenteurVoirListeÉlèves;
import dti.g25.projet_s.présentation.modèle.Modèle;

public class PrésenteurVoirListeÉlèvesPrésence implements ContratVuePrésenteurVoirListeÉlèves.IPésenteurVoirListeÉlèves {
    private static final String EXTRA_CLÉ_CONNEXION = "dti.g25.projet_s.cléConnexion";
    private static final String EXTRA_POSITION_GROUPE = "dti.g25.projet_s.positionCourGroupe";
    private static final String EXTRA_POSITION_SEANCE = "dti.g25.projet_s.positionSeance";


    private Modèle modèle;
    private ContratVuePrésenteurVoirListeÉlèves.IVueVoirListeÉlèves vue;
    private Activity activité;
    private int positionCoursGroupe;
    private int positionSeance;
    private String cléUtilisateur;

    public PrésenteurVoirListeÉlèvesPrésence(Activity activité, ContratVuePrésenteurVoirListeÉlèves.IVueVoirListeÉlèves vue, Modèle modèle) {
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
    public String getPrésenceUtilisateurParPos(int position) {
        if(modèle.getSeanceParCourGroupe(positionCoursGroupe, positionSeance).getListeAbsence().get(position).getPrésence())
            return "présent";
        return "absent";
    }

    @Override
    public void requeteVoirÉlèves(int position) {

    }

    @Override
    public void commencerListeÉlèvesPrésence(int positionSeance, int positionCoursGroupe, String cléUtilisateur) throws Exception {
        this.positionSeance = positionSeance;
        this.positionCoursGroupe = positionCoursGroupe;
        this.cléUtilisateur = cléUtilisateur;
        modèle.setCléUtilisateur(this.cléUtilisateur);
        modèle.rafraîchir();
        vue.rafraichir();
    }

}
