package dti.g25.projet_s.dao;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.Horaire;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.présentation.modèle.dao.DAO;
import dti.g25.projet_s.présentation.modèle.dao.DAOFactoryV1;


public class DAOFactoryRESTAPI extends DAOFactoryV1 {

    private static final String URL_ACCEUIL = "https://projet-s.dti.crosemont.quebec/api/v1/";
    private static final String TAG = "DAOFactoryRESTAPI" ;
    private final static String URL__GET_GROUPE = "https://projet-s.dti.crosemont.quebec/api/v1/groupe/";
    private static final String CNX_GET_POINT_ENTREE = "https://projet-s.dti.crosemont.quebec/api/v1/" ;
    private  Context context;
    private  String cle;
    private Response.Listener<JSONObject> response;
    private static List<DAO<CoursGroupe>> coursGroupes;


    public DAOFactoryRESTAPI(Context context) {
        this.context = context;
    }

    @Override
    public void chargerListeCoursGroupeParUtilisateur(Utilisateur utilisateur, Response.Listener onResponse) {
        System.out.println("Chargement des cours groupes....");
        RequestQueue queue= Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, CNX_GET_POINT_ENTREE+"utilisateur/"+utilisateur.getId()+"/groupes", null, onResponse, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+cle);
                return params;
            }
        };

        Singleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void chargerUtilisateurActuel(Response.Listener onResponse) {
        System.out.println("Chargement de l'utiliasteur....");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_ACCEUIL, null, onResponse, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+cle);
                return params;
            }
        };

        Singleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void chargerUnCourGroupeParId(Response.Listener onResponse, int idCourGroupe){
        String url = URL__GET_GROUPE + idCourGroupe + "?embed=true";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, onResponse, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+cle);
                return params;
            }
        };

        Singleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public List<DAO<Utilisateur>> chargerListeUtilisateursParCoursGroupe(DAO<CoursGroupe> coursGroupeDAO) {
        return null;
    }

    @Override
    public  List<DAO<Seance>> chargerListeSeanceParCoursGroupe(DAO<CoursGroupe> coursGroupeDAO) {
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


    @Override
    public String tenterConnection(final String nomUtilisateur, final String motDePasse, Response.ErrorListener errorResponse) {
        final String CNX_GET="https://projet-s.dti.crosemont.quebec/api/v1/auth_token";

        Log.d("utlisateur: ", nomUtilisateur);
        Log.d("mot de passe: ", motDePasse);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, CNX_GET, null, response, errorResponse) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization",
                        String.format("Basic %s", Base64.encodeToString(
                                String.format("%s:%s", nomUtilisateur, motDePasse).getBytes(), Base64.DEFAULT)));
                return params;
            }
        };

        Singleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
        return cle;
    }

    @Override
    public void getSeancesParCourGroupe(Response.Listener<JSONObject> response, CoursGroupe courGroupe){
        List<Seance> listSeance = new ArrayList<>();
        String url = URL__GET_GROUPE + courGroupe.getId() + "?embed=true";

        JSONObject jsonn = new JSONObject();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url , jsonn, response
            , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Authorization", "Bearer "+cle);
                return headers;
            }
        };
        Singleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void getListeÉlèvesCour(Response.Listener<JSONObject> response, CoursGroupe courGroupe){
        String url = "https://projet-s.dti.crosemont.quebec/api/v1/groupe/1?embed=true";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url , null, response
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            public Map<String, String> getHeaders() {
                HashMap headers = new HashMap();
                headers.put("Authorization", "Bearer "+cle);
                return headers;
            }
        };
    }

    @Override
    public void prendrePrésence() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,"https://projet-s.dti.crosemont.quebec/api/v1/groupe/1?embed=true" , null, response
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            public Map<String, String> getHeaders() {
                HashMap headers = new HashMap();
                headers.put("Authorization:", "Bearer "+cle);
                return headers;
            }
        };
        Singleton.getInstance(context).addToRequestQueue(jsonObjectRequest);

    }

    @Override
    public void obtenirPrésence() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,"https://projet-s.dti.crosemont.quebec/api/v1/groupe/1?embed=true" , null, response
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        Singleton.getInstance(context).addToRequestQueue(jsonObjectRequest);

    }


    @Override
    public String getCle() {
        return cle;
    }

    @Override
    public void setCle(String cle) {
        this.cle = cle;
    }



    public void setResponse(Response.Listener<JSONObject> response) {
        this.response = response;
    }
}
