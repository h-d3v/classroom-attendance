package dti.g25.projet_s.présentation;

public interface IContratVuePrésenteurVoirUnEleve {

    /**
     * contrat du présenteur de connexion
     */
    interface IPrésenteurVoirUnEleve {

        void requêteAjouterAbsence(boolean b);

        void commencerVoirUnÉlèves(int positionSeance, int positionGroupe, int positionÉlèves, String cléUtilisateur) throws Exception;
    }

    interface  IVueVoirUnEleve {
        void setNomUtilisateur(String unString);
        void onClickPositif(String[] list, int position);
        void onClickNegatif();
        String getNomUtilisateur();

        void setVisibilitéPrésence(boolean b);
    }
}
