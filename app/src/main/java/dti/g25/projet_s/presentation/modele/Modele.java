package dti.g25.projet_s.presentation.modele;

import android.content.Context;
import dti.g25.projet_s.dao.DAO;
import dti.g25.projet_s.domaine.entites.CoursGroupe;
import dti.g25.projet_s.domaine.entites.Utilisateur;
import dti.g25.projet_s.presentation.modele.daos.DAOFactory;
import java.util.List;

public class Modele {

    private Utilisateur utilisateur;
    private List<CoursGroupe> coursGroupes;
    private DAOFactory daoFactory;
    private Context context;

    /**
     * Constructeur du modele
     */
    public Modele(Context context, DAOFactory daoFactory){
        this.context=context;
        this.daoFactory=daoFactory;
    }



    public void setUtilisateur(Utilisateur utilisateur){
        this.utilisateur=utilisateur;
    }

    public List<CoursGroupe> chargerCoursGroupeUtilisateur(){
        return daoFactory.creerListeCoursGroupeParUtilisateur(this.utilisateur);
    }

    public CoursGroupe getCourGroupeParPos(int position){
        return coursGroupes.get(position);
    }


}
