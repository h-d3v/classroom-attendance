package dti.g25.projet_s.présentation.vue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dti.g25.projet_s.R;
import dti.g25.projet_s.présentation.ContratVpVoirListeSeances;
import dti.g25.projet_s.présentation.présenteur.PresenteurVoirListeSeance;
import dti.g25.projet_s.présentation.vue.adapter.SeanceAdapter;

public class VueVoirListeSeance extends Fragment implements ContratVpVoirListeSeances.IVueVoirListeSeance {
    private PresenteurVoirListeSeance _presenteur;
    private RecyclerView _rvSeance;
    private SeanceAdapter _seanceAdapter;

    public void set_presenteur(PresenteurVoirListeSeance presenteur) {
        this._presenteur = presenteur;
    }

    public PresenteurVoirListeSeance get_presenteur() {
        return _presenteur;
    }

    /**
     * Cree la vue
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return la vue
     */
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View vue = inflater.inflate(R.layout.frag_voir_liste_seances, container, false);
        _rvSeance = vue.findViewById(R.id.rvSeances);
        _seanceAdapter = new SeanceAdapter(_presenteur);
        _rvSeance.setAdapter(_seanceAdapter);
        _rvSeance.setLayoutManager(new LinearLayoutManager(getContext()));
        return vue;

    }

    @Override
    public void rafraichir() {
        //TODO
    }
}
