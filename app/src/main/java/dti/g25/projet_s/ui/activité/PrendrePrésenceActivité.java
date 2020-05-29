package dti.g25.projet_s.ui.activité;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.Response;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import dti.g25.projet_s.R;
import dti.g25.projet_s.dao.Singleton;

import dti.g25.projet_s.domaine.entité.Utilisateur;

import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.présenteur.PrésenteurPrendrePrésence;
import dti.g25.projet_s.présentation.vue.VuePrendrePrésence;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class PrendrePrésenceActivité extends AppCompatActivity {
    private static final String EXTRA_CLÉ_CONNEXION = "dti.g25.projet_s.cléConnexion";
    private static final String EXTRA_POSITION_GROUPE = "dti.g25.projet_s.positionCourGroupe";
    private static final String EXTRA_POSITION_SEANCE = "dti.g25.projet_s.positionSeance";
    SharedPreferences sharedPreferences;

    PrésenteurPrendrePrésence présenteur;

    /**
     * Initialise la vue pour l'activité prendre présence ansi que le présenteur
     * pour la vue
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int positionCoursGroupe = getIntent().getIntExtra( EXTRA_POSITION_GROUPE, -1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prendre_presence);
        final Activity activity =this;

        final Modèle  modèle = new Modèle();
        modèle.setCléUtilisateur(this.getSharedPreferences("infosLogin", Context.MODE_PRIVATE).getString("auth_token",""));
        //Pour test a supprimmer
        modèle.setCléUtilisateur("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTA3MDU1NDQsIm5iZiI6MTU5MDcwNTU0NCwianRpIjoiYzc3ZGI2YTYtZGJhNS00ZGJjLWI4MzAtY2Q2MjJjZTE5NGM1IiwiaWRlbnRpdHkiOjIsImZyZXNoIjpmYWxzZSwidHlwZSI6ImFjY2VzcyJ9.ewilzSDadEDmYqWc6jsZhhGre7eUecy45jkJKD38S10"
);          //Pour testremplacer le 1 par l'id du coursGroupr groupe/1/membres"
        JsonObjectRequest request =  new JsonObjectRequest(Request.Method.GET,"https://projet-s.dti.crosemont.quebec/api/v1/groupe/1/membres", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int i=0;


                while (true){
                    try {
                        if(!response.has(String.valueOf(i))){
                            break;
                        }
                        JSONObject jsonObject= response.getJSONObject(String.valueOf(i));
                        int id = jsonObject.getInt("id");
                        String nom = jsonObject.getString("nom");
                        Utilisateur utilisateur = new Utilisateur();
                        utilisateur.setNom(nom);
                        utilisateur.setId(id);
                        modèle.addUtilisateurCoursGroupe(utilisateur);
                        Log.i("cc", utilisateur.getUsername());
                        i++;

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    VuePrendrePrésence vue=new VuePrendrePrésence();

                    présenteur=new PrésenteurPrendrePrésence(activity, vue, modèle);
                    vue.setPrésenteur(présenteur);

                    FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                    ft.add(R.id.layout_prendre_présence, vue);
                    ft.commit();
                }
            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){

            }

        }

        ) {
            @Override
            public Map<String,String > getHeaders()  {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer "+ modèle.getCléUtilisateur());
                return headers;
            }
        };
        Singleton.getInstance(this).addToRequestQueue(request);


    }

    @Override
    protected void onStart() {
        super.onStart();
        int positionProjet = getIntent().getIntExtra( EXTRA_POSITION_GROUPE, -1);
        int positionSéance = getIntent().getIntExtra( EXTRA_POSITION_SEANCE, -1);
        String cléUtilisateur = getIntent().getStringExtra(EXTRA_CLÉ_CONNEXION);
        try {

            présenteur.commencerPrendrePrésence(positionProjet, positionSéance, cléUtilisateur);
        } catch (Exception e) {
            Log.e("ErreurPrendrePrésence", String.valueOf(e));
        }
    }

}
