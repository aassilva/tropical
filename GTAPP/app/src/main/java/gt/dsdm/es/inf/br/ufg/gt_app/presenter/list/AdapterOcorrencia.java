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
import gt.dsdm.es.inf.br.ufg.gt_app.model.Ocorrencia;

public class AdapterOcorrencia extends RecyclerView.Adapter<AdapterOcorrencia.OcorrenciaViewHolder> {

    private List<Ocorrencia> ocorrenciaList;
    private Context context;

    public AdapterOcorrencia(List<Ocorrencia> ocorrenciaList, Context context) {
        this.ocorrenciaList = ocorrenciaList;
        this.context = context;
    }

    @Override
    public AdapterOcorrencia.OcorrenciaViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_ocorrencia_item, viewGroup, false);
        AdapterOcorrencia.OcorrenciaViewHolder viewHolder = new AdapterOcorrencia.OcorrenciaViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OcorrenciaViewHolder holder, int position) {
        final Ocorrencia ocorrencia = ocorrenciaList.get(position);

        holder.nameView.setText("ID: " + ocorrencia.getId());
        holder.tituloView.setText("TITULO: " + ocorrencia.getTitulo());
        holder.statusView.setText("STATUS: " + ocorrencia.getStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(ocorrencia);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ocorrenciaList.size();
    }

    public List<Ocorrencia> getTasks() {
        return ocorrenciaList;
    }

    public void setOcorrencia(List<Ocorrencia> ocorrencias) {
        this.ocorrenciaList = ocorrencias;
    }

    public static class OcorrenciaViewHolder
            extends RecyclerView.ViewHolder {
        TextView nameView;
        TextView tituloView;
        TextView statusView;

        OcorrenciaViewHolder(View itemView) {
            super(itemView);
            nameView = (TextView)itemView.findViewById(R.id.label_task_id_ocorrencia);
            tituloView = (TextView)itemView.findViewById(R.id.label_task_title_ocorrencia);
            statusView = (TextView) itemView.findViewById(R.id.label_task_status_ocorrencia);
        }
    }

}
