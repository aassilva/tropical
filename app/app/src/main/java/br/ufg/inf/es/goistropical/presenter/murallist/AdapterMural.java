package br.ufg.inf.es.goistropical.presenter.murallist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.ufg.inf.es.goistropical.model.Mural;

public class AdapterMural extends RecyclerView.Adapter<AdapterMural.MuralViewHolder>{

    private List<Mural> murals;
    private Context context;

    public AdapterMural(List<Mural> tasks, Context context){
        this.murals = tasks;
        this.context = context;
    }

    @Override
    public AdapterMural.MuralViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_task, viewGroup, false);
        AdapterMural.MuralViewHolder viewHolder = new AdapterMural.MuralViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        final Task task = tasks.get(position);
        holder.nameView.setText(task.getName());
        holder.descriptionView.setText(task.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(task);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public static class TodoViewHolder
            extends RecyclerView.ViewHolder {
        TextView nameView;
        TextView descriptionView;

        TodoViewHolder(View itemView) {
            super(itemView);
            nameView = (TextView)itemView.findViewById(R.id.label_task_title);
            descriptionView = (TextView)itemView.findViewById(R.id.label_task_desc);
        }
    }
}
