package dti.g25.projet_s.dao;

import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class ConvertisseurJsonConnexion {

    public String Authentifier(JSONObject response) throws JSONException {
        String cléConnexion =    response.getString("auth_token");
        if (!cléConnexion.equals(null)) {
            Log.i("Cle de connection", cléConnexion[0]);
        }

        return cléConnexion;
    }

}
