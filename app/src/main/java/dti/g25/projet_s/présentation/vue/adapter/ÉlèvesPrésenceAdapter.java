package dti.g25.projet_s.présentation.vue.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import dti.g25.projet_s.R;
import dti.g25.projet_s.présentation.ContratVuePrésenteurVoirListeÉlèves;

public class ÉlèvesPrésenceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private BootstrapButton btnVoirProfil;
    private ContratVuePrésenteurVoirListeÉlèves.IPrésenteurVoirListeÉlèves présenteur;

    public ÉlèvesPrésenceAdapter(ContratVuePrésenteurVoirListeÉlèves.IPrésenteurVoirListeÉlèves présenteur){
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
        try {
            ((TextView)holder.itemView.findViewById(R.id.txtPrésence)).setText(présenteur.getPrésenceUtilisateurParPos(position));
        } catch (Exception e) {
            e.printStackTrace();
        }
        btnVoirProfil=holder.itemView.findViewById(R.id.btnVoirProfil);
        btnVoirProfil.setText("Modifier présence");
        if(présenteur.getpeutPrendrePrésence()) {
           btnVoirProfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View bouton) {
                    présenteur.requeteVoirÉlèves(position);
                }
            });
            ((TextView)holder.itemView.findViewById(R.id.txtPrésence)).setVisibility(View.VISIBLE);
            try {
                ((TextView)holder.itemView.findViewById(R.id.txtPrésence)).setText(présenteur.getPrésenceUtilisateurParPos(position));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ((TextView)holder.itemView.findViewById(R.id.txtPrésence)).setVisibility(View.INVISIBLE);

            holder.itemView.findViewById(R.id.btnVoirProfil).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View bouton) {
                    présenteur.requeteVoirÉlèves(position);
                }
            });
        }
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
