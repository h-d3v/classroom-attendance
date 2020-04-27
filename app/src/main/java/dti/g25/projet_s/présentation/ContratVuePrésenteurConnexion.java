package dti.g25.projet_s.présentation;

public interface ContratVuePrésenteurConnexion {

    /**
     * contrat de la de connexion
     */
    public interface IVueConnexion{
        public String getNomUtilisateur();
        public String getMdpUtilisateur();
    }

    /**
     * contrat du présenteur de connexion
     */
    public interface IPrésenteurConnexion {
        public void tenterConnexion();
    }

}
