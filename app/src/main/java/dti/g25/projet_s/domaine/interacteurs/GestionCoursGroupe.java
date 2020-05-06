package dti.g25.projet_s.domaine.interacteurs;

import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.LibelleCours;
import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Utilisateur;

import java.util.List;

public class GestionCoursGroupe implements IGestionCoursGroupe {


    @Override
    public CoursGroupe creerCoursGroupe(LibelleCours libelleCours, int numeroGroupe) {
        return new CoursGroupe(libelleCours, numeroGroupe);
    }

    @Override
    public void modifierParticipants(List<Utilisateur> participants, CoursGroupe coursGroupe) throws GestionCoursGroupeException {
        List<Utilisateur> copieParticipants= participants;

        boolean verifierSiProf=false;
        boolean verifierParticipantUnique=true; //TODO
        for(Utilisateur utilisateur : copieParticipants){
            if (utilisateur.getRôle()== Role.PROFESSEUR){
                verifierSiProf=true;
                copieParticipants.add(utilisateur);
            }

        }
        if(!verifierSiProf){
            throw new GestionCoursGroupeException("Le cours doit posseder au moins un enseignant");
        }
        else if(!verifierParticipantUnique){
            throw new GestionCoursGroupeException("Le cours doit posseder une liste de participant sans duplicate");
        }

        else{
            coursGroupe.setParticipants(copieParticipants);
        }


    }

    public void ajouterParticipant(Utilisateur utilisateur, CoursGroupe coursGroupe){
        coursGroupe.getParticipants().add(utilisateur); // Nullpointer TODO
    }

}
