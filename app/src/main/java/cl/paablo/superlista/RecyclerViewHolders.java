package cl.paablo.superlista;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewHolders extends RecyclerView.ViewHolder {

    private static final String TAG = RecyclerViewHolders.class.getSimpleName();

    public ImageButton btnEliminar;
    public TextView txtNombre;
    public TextView txtCantidad;
    public TextView txtPrecio;
    private List<Task> taskObject;

    public RecyclerViewHolders(final View itemView, final List<Task> taskObject){
        super(itemView);
        this.taskObject = taskObject;
        txtNombre = itemView.findViewById(R.id.txtnombreProducto);
        txtCantidad = itemView.findViewById(R.id.txtcantidadProducto);
        txtPrecio = itemView.findViewById(R.id.txtprecioProducto);
        btnEliminar = itemView.findViewById(R.id.btnEliminar);



    }

}
