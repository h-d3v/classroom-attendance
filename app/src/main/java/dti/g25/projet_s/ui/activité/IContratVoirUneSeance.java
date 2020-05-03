package dti.g25.projet_s.ui.activité;

import dti.g25.projet_s.domaine.entité.Seance;

public interface IContratVoirUneSeance {
    interface IVueVoirUneseance{

        void rafraichir();

        void setPresenteur(IPresenteurVoirUneSeance presenteurVoirUneSeance);
    }

    interface IPresenteurVoirUneSeance{
        void requeteModifierSatatutSeance();
        boolean estAutoriseAModifierStatutSeance();
        Seance getSeance();

    }

}
