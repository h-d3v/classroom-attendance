package dti.g25.projet_s.présentation;

import dti.g25.projet_s.domaine.entité.Utilisateur;

public interface ContratVuePrésenteurPrendrePrésence {

    /**
     * contrat de la vue de la Prise de présence
     */
    public interface IVuePrendrePrésence {

        void setTxtNomÉtudiant(String username);
    }

    /**
     * contrat du présenteur de la Prise de présence
     */
    public interface IPrésenteurPrendrePrésence {

        public String getNomUtilisteur();

        void commencerPrendrePrésence(int positionSéeance, int positionGroupe, String cléUtilisateur) throws Exception;

        public void ajouterAbsence(boolean b) throws Exception;
    }
}
