package dti.g25.projet_s.util;

import android.app.Activity;
import android.widget.Toast;
import com.android.volley.*;

public class VolleyErrorListener {
    private static Response.ErrorListener errorListener;

    public static Response.ErrorListener fabriquerErrorListnerAfficherToasts(final Activity activity) {
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(activity,
                            "Erreur de connection",
                            Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(activity,
                            "Erreur d'identification",
                            Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(activity,
                            "Erreur serveur",
                            Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(activity,
                            "Erreur Reseau",
                            Toast.LENGTH_SHORT).show();
                }
            }
        };
        return errorListener;
    }

}



