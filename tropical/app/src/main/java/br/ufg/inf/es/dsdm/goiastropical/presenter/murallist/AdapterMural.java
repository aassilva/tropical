package br.ufg.inf.es.dsdm.goiastropical.presenter.murallist;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import br.ufg.inf.es.dsdm.goiastropical.R;
import br.ufg.inf.es.dsdm.goiastropical.model.Mural;

public class AdapterMural extends RecyclerView.Adapter<AdapterMural.MuralViewHolder> {

    private List<Mural> murals;
    private Context context;

    public AdapterMural(List<Mural> murals, Context context){
        this.murals = murals;
        this.context = context;
    }

    @Override
    public AdapterMural.MuralViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_task_mural, viewGroup, false);
        AdapterMural.MuralViewHolder viewHolder = new AdapterMural.MuralViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MuralViewHolder holder, int position) {
        final Mural mural = murals.get(position);

        holder.nameView.setText(mural.getTitulo());
        holder.descriptionView.setText(mural.getDescricao());
        holder.imageView.setImageURI(Uri.parse(mural.getImagem()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(mural);
            }
        });
    }

    @Override
    public int getItemCount() {
        return murals.size();
    }

    public List<Mural> getTasks() {
        return murals;
    }

    public void setMural(List<Mural> murals) {
        this.murals = murals;
    }

    public static class MuralViewHolder
            extends RecyclerView.ViewHolder {
        TextView nameView;
        TextView descriptionView;
        AppCompatImageView imageView;

        MuralViewHolder(View itemView) {
            super(itemView);
            nameView = (TextView)itemView.findViewById(R.id.mural_task_title);
            descriptionView = (TextView)itemView.findViewById(R.id.mural_task_desc);
            imageView = (AppCompatImageView)itemView.findViewById(R.id.mural_task_img);
        }
    }

}
