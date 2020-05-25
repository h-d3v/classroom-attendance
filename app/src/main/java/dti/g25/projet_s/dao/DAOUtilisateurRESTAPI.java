package dti.g25.projet_s.dao;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.domaine.interacteurs.CréeationUtilisateur;
import dti.g25.projet_s.présentation.modèle.dao.DAO;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class DAOUtilisateurRESTAPI implements DAO<Utilisateur> {

    private static final String CNX_GET_POINT_ENTREE ="https://projet-s.dti.crosemont.quebec/api/v1/" ;
    private static final String TAG = "DAOUtilisateurRESTAPI";
    private static Role[] roles={Role.ADMINISTRATEUR, Role.ÉLÈVE, Role.PROFESSEUR };
    int id;
     String cle;
     Context context;
     Utilisateur utilisateur;

    public DAOUtilisateurRESTAPI (int id, Context context){
        this.id=id;
        this.context=context;
    }
    public DAOUtilisateurRESTAPI(String cle,  Context context){
        this.cle=cle;
        this.context=context;
    }
    public DAOUtilisateurRESTAPI(int id, String cle, Utilisateur utilisateur,  Context context){
        this.id=id;
        this.cle=cle;
        this.utilisateur= utilisateur;
        this.context=context;

    }



    /***
     *
     */
    public DAO<Utilisateur> chargerParCleConnexion(final String cle){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, CNX_GET_POINT_ENTREE, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    id=response.getInt("id");
                    String nom = response.getString("nom");
                    int role = response.getInt("rôle");
                    utilisateur =new CréeationUtilisateur().CréerUtilisateur(nom,roles[role]);
                    Log.i(TAG, utilisateur.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) { //TODO exception avec creationUtilisateur
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
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
        return null;

    }

    /***
     *
     * @param utilisateur l'objet a creer dans la base de donnee
     * @return t l'objet tel qui l'a ete cree dans la base de donnee, null si non cree
     */
    @Override
    public Utilisateur creer(Utilisateur utilisateur) {
        return null;
    }

    /***
     *
     * @return t l'objet tel qui l'est dans la base de donnee, null si non existant dans la base de donne
     */
    @Override
    public Utilisateur lire() {
        return null;
    }

    /***
     *
     * @param utilisateur l'objet tel qui doit etre modifie
     * @return t l'objet tel qui l'a ete modifie dans la base de donnee, null si l'objet est inexistant dans la BD
     */
    @Override
    public Utilisateur modifier(Utilisateur utilisateur) {
        return null;
    }

    /***
     *
     * @return boolean true si supprime, false sinon
     */
    @Override
    public boolean supprimer() {
        return false;
    }

    public String getCle() {
        return cle;
    }

    public void setCle(String cle) {
        this.cle = cle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}

