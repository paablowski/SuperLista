package cl.paablo.superlista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgregarProducto extends AppCompatActivity {

    String nombreProducto;
    int cantidadProducto;
    int precioProducto;
    private DatabaseReference mDatabase;
    List<Producto> productos;
    Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);
        final EditText editNombre = findViewById(R.id.editNombre);
        final EditText editCantidad = findViewById(R.id.editCantidad);
        final EditText editPrecio = findViewById(R.id.editPrecio);
        btnCancelar = findViewById(R.id.btnCancelar);
        mDatabase = FirebaseDatabase.getInstance().getReference("listado");


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgregarProducto.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Button btnAgregar = findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreProducto = editNombre.getText().toString();
                cantidadProducto = Integer.parseInt(editCantidad.getText().toString());
                precioProducto = Integer.parseInt(editPrecio.getText().toString());
                Producto producto = new Producto(nombreProducto,cantidadProducto,precioProducto);
                Map<String, Object> listadoValores = producto.toMap();
                Map<String, Object> childUpdates = new HashMap<>();

                String key = mDatabase.child(nombreProducto).getKey();
                childUpdates.put("/productos/"+key,listadoValores);
                mDatabase.updateChildren(childUpdates);

            }
        });
    }
}
