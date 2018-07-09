/*
 * Copyright (c) 2018 Marcelo Quinta
 * Copyright (c) 2018 Antonio Silva
 * Copyright (c) 2018 Keslley Lima
 * MIT License.
 */
package gt.dsdm.es.inf.br.ufg.gt_app.presenter.list;

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

import gt.dsdm.es.inf.br.ufg.gt_app.R;
import gt.dsdm.es.inf.br.ufg.gt_app.model.Mural;

public class AdapterMural extends RecyclerView.Adapter<AdapterMural.MuralViewHolder> {

    private List<Mural> murallist;
    private Context context;

    public AdapterMural(List<Mural> murallist, Context context) {
        this.murallist = murallist;
        this.context = context;
    }

    @Override
    public AdapterMural.MuralViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_mural_item, viewGroup, false);
        AdapterMural.MuralViewHolder viewHolder = new AdapterMural.MuralViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MuralViewHolder holder, int position) {
        final Mural mural = murallist.get(position);

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
        return murallist.size();
    }

    public List<Mural> getTasks() {
        return murallist;
    }

    public void setMural(List<Mural> murals) {
        this.murallist = murals;
    }

    public static class MuralViewHolder
            extends RecyclerView.ViewHolder {
        TextView nameView;
        TextView descriptionView;
        AppCompatImageView imageView;

        MuralViewHolder(View itemView) {
            super(itemView);
            nameView = (TextView)itemView.findViewById(R.id.label_task_title);
            descriptionView = (TextView)itemView.findViewById(R.id.label_task_desc);
            imageView = (AppCompatImageView)itemView.findViewById(R.id.label_taskimg_mural);
        }
    }
}
