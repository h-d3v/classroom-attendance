package dti.g25.projet_s.présentation;

public interface ContratVuePrésenteurConnexion {

    /**
     * contrat de la de connexion
     */
    public interface IVueConnexion{
        public String getNomUtilisateur();
        public String getMotDePasseUtilisateur();

    }

    /**
     * contrat du présenteur de connexion
     */
    public interface IPrésenteurConnexion {
        public Boolean tenterConnexion(String nomUtilisateur, String motDePasse) throws Exception;

        public void tenterConnectionAutomatique();

        void sauvegarderIdentifiants(String nomUtilisateur, String motDePasseUtilisateur);

        String getNomUtilisateurSauvegarde();

        String  getMotPasseUtilisateurSauvegarde();

        void supprimerIdentifiants();
    }

}
