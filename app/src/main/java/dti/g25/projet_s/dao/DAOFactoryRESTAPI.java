package dti.g25.projet_s.dao;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.Horaire;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.présentation.modèle.dao.DAO;
import dti.g25.projet_s.présentation.modèle.dao.DAOFactoryV1;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


    public class DAOFactoryRESTAPI extends DAOFactoryV1 {
        private static final String TOKEN = "";
        private static final String NOM_UTILISATEUR = "username";
        private static final String MOT_PASSE = "password";
        private static final String URL = "https://projet-s.dti.crosemont.quebec/api/v0/utilisateurs";
        private static final String URL_CONNECT = "https://projet-s.dti.crosemont.quebec/";
        private static final String POINT_ENTREE_UTILISATEURS = "utilisateurs";
        private final static String seanceparGroupeGet = "https://projet-s.dti.crosemont.quebec/api/v0/groupe/";
        private  Context context;
        private  String cle;
        private Response.Listener<JSONObject> response;


        public DAOFactoryRESTAPI(Context context) {
            this.context = context;

        }

        public DAOFactoryRESTAPI() {

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

        @Override
        public String tenterConnection(final String nomUtilisateur, final String motDePasse) {
            final String CNX_GET="https://projet-s.dti.crosemont.quebec/api/v1/auth_token";
            ;
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, CNX_GET, null, response, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            }) {
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

        public void getSeancesParCourGroupe(Response.Listener<JSONObject> response, CoursGroupe courGroupe){
            List<Seance> listSeance = new ArrayList<>();
            //seanceparGroupeGet + courGroupe.getId() + "?embed=true"

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
        public String getCle() {
            return cle;
        }

        public void setCle(String clé) { cle = clé;}
        public void setResponse(Response.Listener<JSONObject> response) {
            this.response = response;
        }

    }

