package cl.paablo.superlista.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cl.paablo.superlista.R;

public class ItemViewHolder extends RecyclerView.ViewHolder{

    public TextView nombre;
    public TextView cantidad;
    public TextView precio;

    public ItemViewHolder(View itemView){
        super(itemView);

        nombre = itemView.findViewById(R.id.txtnombreProducto);
        cantidad = itemView.findViewById(R.id.txtcantidadProducto);
        precio = itemView.findViewById(R.id.txtprecioProducto);

    }

}
