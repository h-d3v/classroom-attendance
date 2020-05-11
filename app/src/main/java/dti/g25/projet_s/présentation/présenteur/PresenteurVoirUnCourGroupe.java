package dti.g25.projet_s.présentation.présenteur;

import android.app.Activity;
import android.view.View;

import dti.g25.projet_s.domaine.entité.LibelleCours;
import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.présentation.ContratVpVoirUnCoursGroupe;
import dti.g25.projet_s.présentation.modèle.Modèle;

public class PresenteurVoirUnCourGroupe implements ContratVpVoirUnCoursGroupe.IPrensenteurVoirCourGroupe {

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
        //TODO
    }

    @Override
    public void commencerVoirCourGroupe(int position, String cléUtilisateur) throws Exception {
        _positionCoursGroupe = position;
        _cléUtilisateur = cléUtilisateur;
        _modele.setCléUtilisateur(_cléUtilisateur);
        _modele.rafraîchir();
        _vue.afficherNomCour(_modele.getCourGroupeParPos(_positionCoursGroupe).getLibelleCours().getTITRE());
        _vue.afficherSigleCour(_modele.getCourGroupeParPos(_positionCoursGroupe).getLibelleCours().getTitreAbrégé());
        _vue.afficherNombreÉlèvesInscrit(_modele.getListeEtudiantsParCoursGroupe(_positionCoursGroupe).size());
        _vue.rafraichir();
        _vue.rafraichir();
    }

    @Override
    public int getNbSeancesModele() {
        if(_modele.getListeSeance() == null)
            return 0;
        return _modele.getListeSeanceParCourGroupe(_positionCoursGroupe).size();
    }

    @Override
    public Seance getSeanceParPos(int position) {
        return _modele.getSeanceParCourGroupe(_positionCoursGroupe, position);
    }

    @Override
    public boolean getUtilisateurUilisateurBouton() {
        return _modele.getRoleUtilsaiteurConnecté().equals(Role.PROFESSEUR);
    }

    @Override
    public void requeteVoirSeance() {

    }

    @Override
    public void requetePrendrePrésence() {

    }

    @Override
    public void requeteModifierPrésence() {

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


}
