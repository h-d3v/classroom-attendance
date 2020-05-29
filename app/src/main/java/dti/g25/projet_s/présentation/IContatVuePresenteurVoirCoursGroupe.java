package dti.g25.projet_s.présentation;

public interface IContatVuePresenteurVoirCoursGroupe {

    interface IVueVoirCoursGroupe{
         void rafraichir();

    }

    interface IPresenteurVoirCoursGroupe{

        void rafraîchir();

        int getNombresItems();

        void commencerVoirCourGroupe(String cléConnexion);

        void requeteVoirCoursGroupe(int position);
      String getTitreCoursGroupe(int position);

    }

}
