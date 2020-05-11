package dti.g25.projet_s.présentation;

import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Utilisateur;

public interface ContratVuePrésenteurVoirListeÉlèves {

    public interface IPésenteurVoirListeÉlèves {

        int getNombresItems();

        Utilisateur getUtilisateurParPosition(int position);

        String getPrésenceUtilisateurParPos(int position);

        void requeteVoirÉlèves(int position);

        void commencerListeÉlèvesPrésence(int positionSeance, int positionCoursGroupe, String cléUtilisateur) throws Exception;
    }

    public interface IVueVoirListeÉlèves {

        void rafraichir();
    }
}
