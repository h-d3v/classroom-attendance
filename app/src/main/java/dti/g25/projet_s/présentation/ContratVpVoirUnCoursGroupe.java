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

        int getNbSeancesModele() throws Exception;

        Seance getSeanceParPos(int position) throws Exception;

        boolean getUtilisateurUilisateurBouton();

        void requeteVoirSeance(int position);

        void requetePrendrePrésence(int positionSeance);

        void requeteModifierPrésence(int position);

        int getVisibilteBouton();

        void rafraîchir();
    }


}

