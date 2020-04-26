package dti.g25.projet_s.domaine.interacteurs;

import dti.g25.projet_s.domaine.entites.CoursGroupe;
import dti.g25.projet_s.domaine.entites.LibelleCours;
import dti.g25.projet_s.domaine.entites.Role;
import dti.g25.projet_s.domaine.entites.Utilisateur;

import java.util.List;

public class GestionCoursGroupe implements IGestionCoursGroupe{


    @Override
    public CoursGroupe creerCoursGroupe(LibelleCours libelleCours, int numeroGroupe) {
        return new CoursGroupe(libelleCours, numeroGroupe);
    }

    @Override
    public void modifierParticipants(List<Utilisateur> participants, CoursGroupe coursGroupe) throws GestionCoursGroupeException {
        List<Utilisateur> copieParticipants= coursGroupe.getParticipants();

        boolean verifierSiProf=false;
        boolean verifierParticipantUnique=true; //TODO
        for(Utilisateur utilisateur : copieParticipants){
            if (utilisateur.getROLE()== Role.ENSEIGNANT){
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

}
