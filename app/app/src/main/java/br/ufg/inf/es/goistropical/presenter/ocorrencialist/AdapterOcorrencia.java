package br.ufg.inf.es.goistropical.presenter.ocorrencialist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import br.ufg.inf.es.goistropical.R;
import br.ufg.inf.es.goistropical.model.Ocorrencia;

public class AdapterOcorrencia extends RecyclerView.Adapter<AdapterOcorrencia.OcorrenciaViewHolder>{

    private List<Ocorrencia> ocorrencias;
    private Context context;

    public AdapterOcorrencia(List<Ocorrencia> ocorrencias, Context context){
        this.ocorrencias = ocorrencias;
        this.context = context;
    }

    @Override
    public AdapterOcorrencia.OcorrenciaViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_row_task, viewGroup, false);
        AdapterOcorrencia.OcorrenciaViewHolder viewHolder = new AdapterOcorrencia.OcorrenciaViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OcorrenciaViewHolder holder, int position) {
        final Ocorrencia ocorrencia = ocorrencias.get(position);
        holder.idView.setText(ocorrencia.getId());
        holder.titleView.setText(ocorrencia.getTitulo());
        holder.statusView.setText(ocorrencia.getStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(ocorrencia);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ocorrencias.size();
    }

    public List<Ocorrencia> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(List<Ocorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    public static class OcorrenciaViewHolder
            extends RecyclerView.ViewHolder {
        TextView idView;
        TextView titleView;
        TextView statusView;

        OcorrenciaViewHolder(View itemView) {
            super(itemView);
            idView = (TextView)itemView.findViewById(R.id.label_task_id);
            titleView = (TextView)itemView.findViewById(R.id.label_task_title);
            statusView = (TextView)itemView.findViewById(R.id.label_task_status);
        }
    }
}
