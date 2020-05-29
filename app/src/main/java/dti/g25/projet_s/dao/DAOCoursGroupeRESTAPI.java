package dti.g25.projet_s.dao;

import android.content.Context;

import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.présentation.modèle.dao.DAO;

public class DAOCoursGroupeRESTAPI implements DAO<CoursGroupe> {
    int id;
    String cle;
    Context context;
    CoursGroupe coursGroupe;

    public DAOCoursGroupeRESTAPI(int id, String cle, CoursGroupe cg,  Context context){
        this.id=id;
        this.cle=cle;
        this.coursGroupe=cg;
        this.context=context;
    }

    @Override
    public CoursGroupe creer(CoursGroupe coursGroupe) {
        return null;
    }

    @Override
    public CoursGroupe lire() {
        return coursGroupe;
    }

    @Override
    public CoursGroupe modifier(CoursGroupe coursGroupe) {
        return null;
    }

    @Override
    public boolean supprimer() {
        return false;
    }
}
