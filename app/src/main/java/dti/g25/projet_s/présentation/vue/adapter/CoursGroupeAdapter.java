package dti.g25.projet_s.présentation.vue.adapter;
import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import dti.g25.projet_s.R;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import dti.g25.projet_s.présentation.IContatVuePresenteurVoirCoursGroupe;

public class CoursGroupeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    BootstrapButton b;

    private IContatVuePresenteurVoirCoursGroupe.IPresenteurVoirCoursGroupe presenteurVoirCoursGroupe;

    public CoursGroupeAdapter(IContatVuePresenteurVoirCoursGroupe.IPresenteurVoirCoursGroupe presenteurVoirCoursGroupe){
        this.presenteurVoirCoursGroupe=presenteurVoirCoursGroupe;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        LinearLayout racine = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cours_groupe_view, parent, false);

        return new RecyclerView.ViewHolder(racine) {

        };
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.findViewById(R.id.btnVoirCoursGroupe).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View bouton){
                presenteurVoirCoursGroupe.requeteVoirCoursGroupe(position);
            }
        });
        System.out.println("Ou rien");
        b=holder.itemView.findViewById(R.id.btnVoirCoursGroupe);
        b.setText(presenteurVoirCoursGroupe.getTitreCoursGroupe(position));
        b.setTextColor(b.getResources().getColor(R.color.textDark, null));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        if(presenteurVoirCoursGroupe==null) return 0;
        return presenteurVoirCoursGroupe.getNombresItems();
    }
}
