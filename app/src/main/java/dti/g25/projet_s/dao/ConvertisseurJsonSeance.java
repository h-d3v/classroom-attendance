package dti.g25.projet_s.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dti.g25.projet_s.domaine.entité.Absence;
import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.Horaire;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.interacteurs.GestionHoraire;
import dti.g25.projet_s.domaine.interacteurs.GestionSeance;

public class ConvertisseurJsonSeance {

    /**
     * Décode une ligne json d'une liste de séeance et la retourne
     * @param résultat
     * @param coursGroupe
     * @return
     * @throws JSONException
     */
    public List<Seance> décoderJsonSéeances(JSONObject résultat, CoursGroupe coursGroupe) throws JSONException {
        List<Seance> seances = new ArrayList<>();

        JSONObject résultatZoomé;
        résultatZoomé = (JSONObject) résultat.get("_embedded");
        résultatZoomé = (JSONObject) résultatZoomé.get("seances");
        JSONArray listeSeance = résultatZoomé.names();

        for(int i = 0; i < listeSeance.length(); i++) {
            JSONObject objectAcuel = (JSONObject) résultatZoomé.get(listeSeance.getString(i));
            float heureDébut = obtenirHeureEnFloat(objectAcuel.getString("début"));
            float heureFin = obtenirHeureEnFloat(objectAcuel.getString("fin"));
            int id = objectAcuel.getInt("id");
            Horaire horaire = new GestionHoraire().créerHoraire(heureDébut, heureFin, objectAcuel.getString("date"));
            Seance unSeance = new GestionSeance().creerSeance(coursGroupe, horaire, id);
            seances.add(unSeance);
        }

        return seances;
    }

    /**.
     * Retourn une seance avec en elle les présence des élèves
     * @param seance
     * @param résultat
     * @return
     * @throws JSONException
     */
    public Seance présenceSeance(Seance seance, JSONObject résultat) throws JSONException {
        JSONArray listeUtilisateur = résultat.names();
        if(listeUtilisateur != null) {
            for (int i = 0; i < listeUtilisateur.length(); i++) {
                JSONObject objectAcuel = (JSONObject) résultat.get(listeUtilisateur.getString(i));
                for (Absence absence : seance.getListeAbsence()) {
                    if (objectAcuel.getString("nom").equals(absence.getUtilisateur().getUsername())) {
                        absence.setPrésence(true);
                    }
                }

            }
        }

        return seance;
    }


    /**
     * tranforme l'heure en String vers un float
     * @param heureString
     * @return
     */
    private float obtenirHeureEnFloat(String heureString) {
        float temp;
        String[] partie = heureString.split(":");

        temp = Float.parseFloat(partie[0]);

        if (Double.parseDouble(partie[1]) > 30) {
            temp += 0.75;
        } else if (Double.parseDouble(partie[1]) > 15) {
            temp += 0.5;
        } else if(Double.parseDouble(partie[1]) > 0) {
            temp += 0.25;
        }

           return temp;
    }

}
