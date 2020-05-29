package dti.g25.projet_s.ui.activité;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import dti.g25.projet_s.R;
import dti.g25.projet_s.dao.DAOFactoryRESTAPI;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.modèle.dao.ModèleDAO;
import dti.g25.projet_s.présentation.présenteur.PrésenteurConnexion;
import dti.g25.projet_s.présentation.vue.VueConnexion;

public class ConnexionActivité extends AppCompatActivity {

    PrésenteurConnexion présenteur;

    /**
     * Initialise la vue pour l'activité Connexion ansi que le présenteur
     * pour la vue
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion_activite);

        Modèle modèle= new Modèle(this);
        VueConnexion vue=new VueConnexion();

        présenteur=new PrésenteurConnexion(this, vue, modèle);
        vue.setPrésenteur(présenteur);

        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_connexion, vue);
        ft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        daoFactoryRESTAPI.setResponse(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        });
    }

}
