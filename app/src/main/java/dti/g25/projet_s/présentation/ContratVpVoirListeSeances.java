package dti.g25.projet_s.présentation;

import dti.g25.projet_s.domaine.entité.EtatSeance;

public interface ContratVpVoirListeSeances {
    interface IVueVoirListeSeance{
        void rafraichir();
    }

    interface IPresenteurVoirListeSeances{
        void requeteConsulterCoursGroupe(int position);
        void changerEtat(int position, EtatSeance etatSeance);
        void requeteModifierPresence(int position);
        int getNbSeancesModele();
    }
}
