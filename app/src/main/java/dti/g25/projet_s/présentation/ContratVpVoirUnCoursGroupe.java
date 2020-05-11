package dti.g25.projet_s.présentation;

import dti.g25.projet_s.domaine.entité.Seance;

public interface ContratVpVoirUnCoursGroupe {
    interface IVueVoirCoursGroupe{

        void afficherNomCour(String titre);

        void afficherNombreÉlèvesInscrit(int size);

        void afficherSigleCour(String sigle);

        void rafraichir();

    }

    interface IPrensenteurVoirCourGroupe{
        void requeteVoirListeEleves();
        void commencerVoirCourGroupe(int position, String cléUtilisateur) throws Exception;

        int getNbSeancesModele();

        Seance getSeanceParPos(int position);

        boolean getUtilisateurUilisateurBouton();

        void requeteVoirSeance();

        void requetePrendrePrésence();

        void requeteModifierPrésence();

        int getVisibilteBouton();
    }


}

