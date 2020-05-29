package dti.g25.projet_s.présentation;

import android.app.Activity;
import dti.g25.projet_s.domaine.entité.Seance;

public interface IContratVoirUneSeance {
    interface IVueVoirUneseance{

        void setPresenteur(IPresenteurVoirUneSeance presenteurVoirUneSeance);

        void afficherHoraire(String unString);

        void afficherLibelle(String unString);

        void afficherEstPrévue(String unString);

        void autoriserProf(boolean b);
    }

    interface IPresenteurVoirUneSeance{
        void requeteModifierSatatutSeance() throws Exception;
        boolean estAutoriseAModifierStatutSeance();
        Seance getSeance() throws Exception;
        Activity get_activite();

        void commencerVoirSéance(int positionProjet, int positionSéance, String cléUtilisateur) throws Exception;
    }

}
