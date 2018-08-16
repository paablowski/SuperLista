package cl.paablo.superlista;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders>{

    private List<Task> task;
    protected Context context;

    public RecyclerViewAdapter(Context context, List<Task> task){
        this.task = task;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType){
        View productView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_lista, parent, false);
        RecyclerViewHolders pvh = new RecyclerViewHolders(productView, task);

        return pvh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position){
        holder.txtNombre.setText(task.get(position).getTask());
    }

    @Override
    public int getItemCount(){
        return this.task.size();
    }
}

