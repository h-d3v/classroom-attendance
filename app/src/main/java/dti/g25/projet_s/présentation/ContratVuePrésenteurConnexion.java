package dti.g25.projet_s.présentation;

public interface ContratVuePrésenteurConnexion {

    /**
     * contrat de la de connexion
     */
    public interface IVueConnexion{
        public String getNomUtilisateur();
        public String getMotDePasseUtilisateur();
        void setMessageErreur(String s);

        boolean getCbSeSouvenir();
    }

    /**
     * contrat du présenteur de connexion
     */
    public interface IPrésenteurConnexion {
        public Boolean tenterConnexion(String nomUtilisateur, String motDePasse) throws Exception;

        public void tenterConnectionAutomatique();

        void sauvegarderIdentifiants();

        String getNomUtilisateurSauvegarde();

        String  getMotPasseUtilisateurSauvegarde();

        void supprimerIdentifiants();

        void terminerConnexion();
    }

}
