package dti.g25.projet_s.présentation.vue;

public interface IContatVuePresenteurVoirCoursGroupe {

    interface IVueVoirCoursGroupe{
         void rafraichir();

    }

    interface IPresenteurVoirCoursGroupe{
      int getNombresItems();
      void requeteVoirCoursGroupe(int position);
      String getTitreCoursGroupe(int position);

    }

}
