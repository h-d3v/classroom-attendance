package dti.g25.projet_s.présentation.modèle;

import android.content.Context;
import android.test.mock.MockContext;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import dti.g25.projet_s.dao.DAOFactoryRESTAPI;
import dti.g25.projet_s.dao.DAOUtilisateurRESTAPI;
import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.LibelleCours;
import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.présentation.modèle.dao.DAO;

public class TestDAOFactoryRESTAPI {
    private DAOFactoryRESTAPI daoFactoryRESTAPI = new DAOFactoryRESTAPI( new MockContext());
    private String tkn="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTA0NjcwOTYsIm5iZiI6MTU5MDQ2NzA5NiwianRpIjoiODMxOTkxMzMtNTk2ZS00MTAwLWE3YTUtMjMxMTQ0NTI3N2E2IiwiaWRlbnRpdHkiOjEsImZyZXNoIjpmYWxzZSwidHlwZSI6ImFjY2VzcyJ9.jP0XCtb5gC9N8e4u8MrwUeZPPQbbG3ghoGBzJTWBkjU";
    private Utilisateur utilisateur= new Utilisateur("mthibault", Role.ÉLÈVE,"password");
    private CoursGroupe coursGroupe= new CoursGroupe(new LibelleCours("Introduction aux bases de donn\u00e9es", "420F04RO"), 1);
    @Test
    public void testGetCoursGourpeParUtilisateurAPI(){

        DAOUtilisateurRESTAPI daoUtilisateurRESTAPI = new DAOUtilisateurRESTAPI(1, tkn ,utilisateur, new MockContext());
        List<DAO<CoursGroupe>> list=daoFactoryRESTAPI.chargerListeCoursGroupeParUtilisateur(daoUtilisateurRESTAPI);
        list.size();

    }


}
