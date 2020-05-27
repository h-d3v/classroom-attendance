package dti.g25.projet_s.présentation;

import android.content.Intent;

import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Utilisateur;

public interface ContratVuePrésenteurVoirListeÉlèves {

    public interface IPésenteurVoirListeÉlèves {

        int getNombresItems();

        Utilisateur getUtilisateurParPosition(int position);

        String getPrésenceUtilisateurParPos(int position) throws Exception;

        void requeteVoirÉlèves(int position);

        void commencerListeÉlèvesPrésence(int positionSeance, int positionCoursGroupe, String cléUtilisateur) throws Exception;

        void onActivityResult(int requestCode, int resultCode, Intent data) throws Exception;
    }

    public interface IVueVoirListeÉlèves {

        void rafraichir();
    }
}
