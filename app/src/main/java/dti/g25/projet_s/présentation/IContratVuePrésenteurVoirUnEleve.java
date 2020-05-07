package dti.g25.projet_s.présentation;

public interface IContratVuePrésenteurVoirUnEleve {

    /**
     * contrat du présenteur de connexion
     */
    interface IPrésenteurVoirUnEleve {

        String getNomUtilisateur();
    }

    interface  IVueVoirUnEleve {
      void onClickPositif(String[] list, int position);
      void onClickNegatif();
      String getNomUtilisateur();
    }
}
