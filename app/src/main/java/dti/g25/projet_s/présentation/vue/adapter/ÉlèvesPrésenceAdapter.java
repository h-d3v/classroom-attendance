package dti.g25.projet_s.présentation.vue.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dti.g25.projet_s.R;
import dti.g25.projet_s.présentation.ContratVuePrésenteurVoirListeÉlèves;

public class ÉlèvesPrésenceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ContratVuePrésenteurVoirListeÉlèves.IPésenteurVoirListeÉlèves  présenteur;

    public ÉlèvesPrésenceAdapter(ContratVuePrésenteurVoirListeÉlèves.IPésenteurVoirListeÉlèves présenteur){
        this.présenteur=présenteur;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        LinearLayout racine = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.eleves_view, parent, false);

        return new RecyclerView.ViewHolder(racine) {
        };
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((TextView)holder.itemView.findViewById(R.id.txtNomÉlèves)).setText(présenteur.getUtilisateurParPosition(position).getUsername());
        ((TextView)holder.itemView.findViewById(R.id.txtPrésence)).setText(présenteur.getPrésenceUtilisateurParPos(position));
            ((Button) holder.itemView.findViewById(R.id.btnAbsence)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View bouton) {
                    présenteur.requeteVoirÉlèves(position);
                }
            });
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        if(présenteur==null) return 0;

        return présenteur.getNombresItems();
    }
}
