package cl.paablo.superlista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class AgregarProducto extends AppCompatActivity {

    String nombreProducto;
    int cantidadProducto;
    int precioProducto;
    private DatabaseReference mDatabase;
    List<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);
        final EditText editNombre = findViewById(R.id.editNombre);
        final EditText editCantidad = findViewById(R.id.editCantidad);
        final EditText editPrecio = findViewById(R.id.editPrecio);
        Button btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgregarProducto.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        Button btnAgregar = findViewById(R.id.btnGuardar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreProducto = editNombre.getText().toString();
                int cantidadProducto = Integer.parseInt(editCantidad.getText().toString());
                int precioProducto = Integer.parseInt(editPrecio.getText().toString());
                //mDatabase.child("listado").child()

            }
        });
    }
}
