package dti.g25.projet_s.présentation;

import android.content.Intent;

import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Utilisateur;

public interface ContratVuePrésenteurVoirListeÉlèves {

    public interface IPésenteurVoirListeÉlèves {

        int getNombresItems();

        Utilisateur getUtilisateurParPosition(int position);

        String getPrésenceUtilisateurParPos(int position);

        void requeteVoirÉlèves(int position);

        void commencerListeÉlèvesPrésence(int positionSeance, int positionCoursGroupe, String cléUtilisateur) throws Exception;

        void onActivityResult(int requestCode, int resultCode, Intent data) throws Exception;

        Boolean getpeutPrendrePrésence();
    }

    public interface IVueVoirListeÉlèves {

        void rafraichir();

        void setBoutonPrésence(boolean b);
    }
}
