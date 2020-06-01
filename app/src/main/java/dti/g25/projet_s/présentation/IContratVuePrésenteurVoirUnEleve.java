package dti.g25.projet_s.présentation;

public interface IContratVuePrésenteurVoirUnEleve {

    /**
     * contrat du présenteur de connexion
     */
    interface IPrésenteurVoirUnEleve {

        void requêteAjouterAbsence(boolean b);

        void commencerVoirUnÉlèves(int positionSeance, int positionGroupe, int positionÉlèves, String cléUtilisateur) throws Exception;

        String getNomUtilisateur(int position);

        void rafraichir();

    }

    interface  IVueVoirUnEleve {

        void onClickPositif(String[] list, int position);

        void onClickNegatif();

        void setVisibilitéPrésence(boolean b);

        void setUsername(String username);
    }
}
