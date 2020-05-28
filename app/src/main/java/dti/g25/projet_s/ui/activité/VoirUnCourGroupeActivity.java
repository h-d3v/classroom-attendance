package dti.g25.projet_s.ui.activité;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import dti.g25.projet_s.R;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.présenteur.PresenteurVoirUnCourGroupe;
import dti.g25.projet_s.présentation.vue.VueVoirUnCourGroupe;

import static java.lang.String.valueOf;

public class VoirUnCourGroupeActivity extends AppCompatActivity {
    private static final String EXTRA_CLÉ_CONNEXION = "dti.g25.projet_s.cléConnexion";
    private static final String EXTRA_POSITION_GROUPE = "dti.g25.projet_s.positionCourGroupe";

    PresenteurVoirUnCourGroupe _presenteur;
    Modèle _modele;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_un_courgroupe);
        _modele=new Modèle(this);
        VueVoirUnCourGroupe vue=new VueVoirUnCourGroupe();
        _presenteur= new PresenteurVoirUnCourGroupe(this, vue, _modele);
        vue.setPresenteur(_presenteur);
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_voir_un_courgroupe, vue);
        ft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        int position = getIntent().getIntExtra(EXTRA_POSITION_GROUPE, -1);
        String cléUtilisateur = getIntent().getStringExtra(EXTRA_CLÉ_CONNEXION);
        Response.Listener<JSONObject> réponse = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject réponse) {
                try {
                    Log.d("Schéma", "ca passe ici");
                    _modele.setJSONSeances(réponse, 1, 1);
                     _presenteur.rafraîchir();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        _modele.requeteHttpSeanceCourGroupe(position, réponse);
        try {
            _presenteur.commencerVoirCourGroupe(position, cléUtilisateur);
        } catch (Exception e) {
            Log.e("ErreurVoirCourGroupe", valueOf(e));
        }
    }

}
