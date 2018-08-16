package cl.paablo.superlista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class AgregarProducto extends AppCompatActivity {

    String nombreProducto;
    int cantidadProducto;
    int precioProducto;
    List<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);
        final EditText editNombre = findViewById(R.id.editNombre);
        final EditText editCantidad = findViewById(R.id.editCantidad);
        final EditText editPrecio = findViewById(R.id.editPrecio);



        Button btnAgregar = findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreProducto = editNombre.getText().toString();
                int cantidadProducto = Integer.parseInt(editCantidad.getText().toString());
                int precioProducto = Integer.parseInt(editPrecio.getText().toString());
                productos.add(new Producto(nombreProducto,cantidadProducto,precioProducto));
                
            }
        });
    }
}
