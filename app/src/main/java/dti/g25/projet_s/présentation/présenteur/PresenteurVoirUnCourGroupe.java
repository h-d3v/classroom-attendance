package dti.g25.projet_s.présentation.présenteur;

import android.app.Activity;
import android.content.Intent;
import android.os.Debug;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.List;

import dti.g25.projet_s.domaine.entité.LibelleCours;
import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.présentation.ContratVpVoirUnCoursGroupe;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.ui.activité.PrendrePrésenceActivité;
import dti.g25.projet_s.ui.activité.VoirListeÉlevesActivité;
import dti.g25.projet_s.ui.activité.VoirSeanceActivity;

public class PresenteurVoirUnCourGroupe implements ContratVpVoirUnCoursGroupe.IPrensenteurVoirCourGroupe {
    private static final String EXTRA_CLÉ_CONNEXION = "dti.g25.projet_s.cléConnexion";
    private static final String EXTRA_POSITION_GROUPE = "dti.g25.projet_s.positionCourGroupe";
    private static final String EXTRA_POSITION_SEANCE = "dti.g25.projet_s.positionSeance";
    private static final int RESQUEST_CODE_VOIR_ELEVES = 33;
    private static final int RESQUEST_CODE_VOIR_SEANCE = 22;

    private Modèle _modele;
    private ContratVpVoirUnCoursGroupe.IVueVoirCoursGroupe _vue;
    private Activity _activite;
    private int _positionCoursGroupe;
    private String _cléUtilisateur;


    /**
     * Constructeur du presenteur
     * @param activite Activité dans laquelle le présenteur agis qui sera l'activité pricipale
     * @param vue La vue qui est relié au présenteur, qui sera la vue principale
     * @param modele le modele du MVP
     */
    public PresenteurVoirUnCourGroupe(Activity activite, ContratVpVoirUnCoursGroupe.IVueVoirCoursGroupe vue, Modèle modele) {
        _activite=activite;
        _modele=modele;
        _vue=vue;
    }

    public LibelleCours getLibelleCours(){
        return _modele.getCourGroupeParPos(_positionCoursGroupe).getLibelleCours();
    }

    public int getNumeroCours(){
        return _modele.getCourGroupeParPos(_positionCoursGroupe).getNumeroGroupe();
    }

    @Override
    public void requeteVoirListeEleves() {
        Intent intentVoirSéance = new Intent(_activite, VoirListeÉlevesActivité.class);
        intentVoirSéance.putExtra(EXTRA_CLÉ_CONNEXION, _cléUtilisateur);
        intentVoirSéance.putExtra(EXTRA_POSITION_GROUPE, _positionCoursGroupe);
        _activite.startActivityForResult(intentVoirSéance, RESQUEST_CODE_VOIR_SEANCE);
    }

    @Override
    public void commencerVoirCourGroupe(int position, String cléUtilisateur) throws Exception {
        _modele.getListeSeanceParCourGroupe();
        _positionCoursGroupe = position;
        _cléUtilisateur = cléUtilisateur;
        _modele.setCléConnexion(_cléUtilisateur);
        _modele.rafraîchir();
        _vue.afficherNomCour(_modele.getCourGroupeParPos(_positionCoursGroupe).getLibelleCours().getTITRE());
        _vue.afficherSigleCour(_modele.getCourGroupeParPos(_positionCoursGroupe).getLibelleCours().getSigle());
        _vue.afficherNombreÉlèvesInscrit(_modele.getListeEtudiantsParCoursGroupe().size());
        _vue.rafraichir();
    }

    @Override
    public int getNbSeancesModele() throws Exception {
        if(_modele.getListeSeanceParCourGroupe() == null)
            return 0;
        return _modele.getListeSeanceParCourGroupe().size();
    }

    @Override
    public Seance getSeanceParPos(int position) throws Exception {
        return _modele.getSeanceParCourGroupe( position);
    }

    @Override
    public boolean getUtilisateurUilisateurBouton() {
        return _modele.getRoleUtilsaiteurConnecté().equals(Role.PROFESSEUR);
    }

    @Override
    public void requeteVoirSeance(int position) {
        Intent intentVoirSéance = new Intent(_activite, VoirSeanceActivity.class);
        intentVoirSéance.putExtra(EXTRA_CLÉ_CONNEXION, _cléUtilisateur);
        intentVoirSéance.putExtra(EXTRA_POSITION_GROUPE, _positionCoursGroupe);
        intentVoirSéance.putExtra(EXTRA_POSITION_SEANCE, position);
        _activite.startActivityForResult(intentVoirSéance, RESQUEST_CODE_VOIR_SEANCE);
    }

    @Override
    public void requetePrendrePrésence(int positionSeance) {
        Intent intentVoirSéance = new Intent(_activite, PrendrePrésenceActivité.class);
        intentVoirSéance.putExtra(EXTRA_CLÉ_CONNEXION, _modele.getCléConnexion());
        intentVoirSéance.putExtra(EXTRA_POSITION_GROUPE, _positionCoursGroupe);
        intentVoirSéance.putExtra(EXTRA_POSITION_SEANCE, _modele.getListeSeanceParCourGroupe().get(positionSeance).getId());
        _activite.startActivity(intentVoirSéance);
    }

    @Override
    public void requeteModifierPrésence(int position) {
        Intent intentVoirSéance = new Intent(_activite, VoirListeÉlevesActivité.class);
        intentVoirSéance.putExtra(EXTRA_CLÉ_CONNEXION, _modele.getCléConnexion());
        intentVoirSéance.putExtra(EXTRA_POSITION_GROUPE, _positionCoursGroupe);
        intentVoirSéance.putExtra(EXTRA_POSITION_SEANCE, _modele.getListeSeanceParCourGroupe().get(position).getId());
        _activite.startActivityForResult(intentVoirSéance, RESQUEST_CODE_VOIR_ELEVES);
    }

    /**
     * permet de savoir si les bouton doivent être vue ou non
     * @return
     */
    @Override
    public int getVisibilteBouton() {
        if(getUtilisateurUilisateurBouton())
            return View.VISIBLE;
        return View.INVISIBLE;
    }

    @Override
    public void rafraîchir() {
        _vue.afficherNomCour(_modele.getCoursGroupeActuelle().getLibelleCours().getTITRE());
        _vue.afficherSigleCour(_modele.getCoursGroupeActuelle().getLibelleCours().getSigle());
        _vue.afficherNombreÉlèvesInscrit(_modele.getListeEtudiantsParCoursGroupe().size());
        _vue.rafraichir();
    }


}
