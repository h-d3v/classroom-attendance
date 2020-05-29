package dti.g25.projet_s.présentation.vue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dti.g25.projet_s.R;
import dti.g25.projet_s.présentation.ContratVuePrésenteurVoirListeÉlèves;
import dti.g25.projet_s.présentation.vue.adapter.ÉlèvesPrésenceAdapter;

public class VueVoirListeÉlèves extends Fragment implements ContratVuePrésenteurVoirListeÉlèves.IVueVoirListeÉlèves {

    private ContratVuePrésenteurVoirListeÉlèves.IPrésenteurVoirListeÉlèves présenteur;
    private RecyclerView rvVoirÉlèves;
    private ÉlèvesPrésenceAdapter élèvesPrésenceAdapter;

    public void set_presenteur(ContratVuePrésenteurVoirListeÉlèves.IPrésenteurVoirListeÉlèves presenteur) {
        this.présenteur = presenteur;
    }

    public ContratVuePrésenteurVoirListeÉlèves.IPrésenteurVoirListeÉlèves get_presenteur() {
        return présenteur;
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

        View vue = inflater.inflate(R.layout.frag_voir_liste_eleves, container, false);
        rvVoirÉlèves = vue.findViewById(R.id.rvVoirÉlèves);
        élèvesPrésenceAdapter = new ÉlèvesPrésenceAdapter(présenteur);
        rvVoirÉlèves.setAdapter(élèvesPrésenceAdapter);
        rvVoirÉlèves.setLayoutManager(new LinearLayoutManager(getContext()));
        return vue;

    }


    @Override
    public void rafraichir() {
        if(élèvesPrésenceAdapter!=null)
            élèvesPrésenceAdapter.notifyDataSetChanged();
    }

    @Override
    public void setBoutonPrésence(boolean b) {

    }
}
