package dti.g25.projet_s.dao;
import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.Horaire;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.présentation.modèle.dao.DAO;
import dti.g25.projet_s.présentation.modèle.dao.DAOFactoryV1;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;


public class DAOFactoryRESTAPI extends DAOFactoryV1 {
    private static final String TOKEN = "";
    private static final String NOM_UTILISATEUR = "username";
    private static final String MOT_PASSE = "password";
    private static final String URL = "https://projet-s.dti.crosemont.quebec/api/v0/utilisateurs";
    private static final String URL_CONNECT = "https://projet-s.dti.crosemont.quebec/";
    private static final String POINT_ENTREE_UTILISATEURS = "utilisateurs";
    private static Context context;
    private static String cle;


    public DAOFactoryRESTAPI(Context context) {
        this.context = context;

    }

    @Override
    public List<DAO<CoursGroupe>> chargerListeCoursGroupeParUtilisateur(DAO<Utilisateur> utilisateurDAO) {
        return null;
    }

    @Override
    public List<DAO<Utilisateur>> chargerListeUtilisateursParCoursGroupe(DAO<CoursGroupe> coursGroupeDAO) {
        return null;
    }

    @Override
    public List<DAO<Seance>> chargerListeSeanceParCoursGroupe(DAO<CoursGroupe> coursGroupeDAO) {
        return null;
    }

    @Override
    public DAO<CoursGroupe> chargerCoursGroupeParSeance(DAO<Seance> seanceDAO) {
        return null;
    }

    @Override
    public List<DAO<Seance>> chargerListeSeanceParUtilisateur(DAO<Utilisateur> utilisateurDAO) {
        return null;
    }

    @Override
    public List<DAO<Utilisateur>> chargerListeUtilisateurParCoursGroupe(DAO<CoursGroupe> coursGroupeDAO) {
        return null;
    }

    @Override
    public List<DAO<Utilisateur>> chargerListeUtilisateurParSeance(DAO<Seance> seanceDAO) {
        return null;
    }

    @Override
    public List<DAO<Horaire>> chargerHoraireParCoursGroupe(DAO<CoursGroupe> coursGroupeDAO) {
        return null;
    }

    @Override
    public List<DAO<CoursGroupe>> chargerCoursGroupeParHoaire(DAO<Horaire> horaireDAO) {
        return null;
    }

    @Override //Tentative de cnx pour la v0 de l'api se connecte que si 'lutilisateur est dans la liste des utilisateurs a l'adresse
    // https://projet-s.dti.crosemont.quebec/api/v0/utilisateurs
    public String tenterConnection(final String nomUtilisateur, String motDePasse) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("utilisateurs");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject utilisateur = jsonArray.getJSONObject(i);
                        //Log.i("id", String.valueOf(utilisateur.getInt("id")));
                        //Log.i("nom", utilisateur.getString("nom"));
                        //Log.i("role", String.valueOf(utilisateur.getInt("rôle")));
                        if (nomUtilisateur.equals(utilisateur.getString("nom"))) {
                            setCle("42");

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
            }
        });
        Singleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
        return cle;
    }

    @Override
    public String getCle() {
        return cle;
    }

    public static void setCle(String cle) {
        DAOFactoryRESTAPI.cle = cle;
    }

    /** Ebauche de connexion pour la version 1 de l'api non teste
        JSONObject postJSON = new JSONObject();
        try {
            postJSON.put(NOM_UTILISATEUR, nomUtilisateur);
            postJSON.put(MOT_PASSE, motDePasse);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL_CONNECT, postJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    cle=response.getString(TOKEN);

                } catch (JSONException e) {
                   e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        Singleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
            return cle;
        }
         */



}
