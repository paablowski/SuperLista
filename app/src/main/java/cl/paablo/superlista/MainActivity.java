package cl.paablo.superlista;

import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import cl.paablo.superlista.modelo.*;
import cl.paablo.superlista.viewholder.ItemViewHolder;


public class MainActivity extends AppCompatActivity {

    String nombreProducto;
    int cantidadProducto;
    int precioProducto;
    EditText editNombre;
    EditText editCantidad;
    EditText editPrecio;


    final Context context = this;

    private static final String TAG = "MessageActivity";
    private static final String REQUIRED = "Required";
    private String listado = "";

    private RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter<Producto, ItemViewHolder> mAdapter;

    private DatabaseReference mDatabase;
    private DatabaseReference mProductoReference;
    private ChildEventListener mMessageListener;

    private ArrayList<Producto> listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton btnAgregarProducto = findViewById(R.id.btnAgregarProducto);

        mRecyclerView = findViewById(R.id.recyclerView);

        final TextView txtListado;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mProductoReference = FirebaseDatabase.getInstance().getReference("articulos");

        listaProductos = new ArrayList<>();

        btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context);
                dialog.setTitle("Agrega producto a lista");
                dialog.setContentView(R.layout.activity_agregar_producto);
                Button btnGuardar = dialog.findViewById(R.id.btnGuardar);
                editNombre = dialog.findViewById(R.id.editNombre);
                editCantidad = dialog.findViewById(R.id.editCantidad);
                editPrecio = dialog.findViewById(R.id.editPrecio);
                btnGuardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        nombreProducto = editNombre.getText().toString();
                        cantidadProducto = Integer.parseInt(editCantidad.getText().toString());
                        precioProducto = Integer.parseInt(editPrecio.getText().toString());


                        Producto producto = new Producto(nombreProducto,cantidadProducto,precioProducto);
                        Map<String, Object> listadoValores = producto.toMap();
                        Map<String, Object> childUpdates = new HashMap<>();

                        String key = mDatabase.child("listado").push().getKey();

                        childUpdates.put("/articulos/" + key, listadoValores);
                        mDatabase.updateChildren(childUpdates);

                        Snackbar.make(view, "Se agregó a la lista!",Snackbar.LENGTH_SHORT).show();
                        editNombre.setText("");
                        editCantidad.setText("");
                        editPrecio.setText("");
                    }
                });
                dialog.show();


                //submitMessage();
                //edtSentText.setText("");
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(false);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);

        Query query = mProductoReference.limitToLast(8);
/*
        mAdapter = new FirebaseRecyclerAdapter<Producto, ItemViewHolder>(
                Producto.class, R.layout.recycler_lista, ItemViewHolder.class, query) {
            @Override
            protected void populateViewHolder(ItemViewHolder viewHolder, Producto model, int position) {
                viewHolder.nombre.setText(model.nombre);
                viewHolder.cantidad.setText(model.cantidad);
                viewHolder.precio.setText(model.precio);
            }

            @Override
            public void onChildChanged(EventType type, DataSnapshot snapshot, int index, int oldIndex) {
                super.onChildChanged(type, snapshot, index, oldIndex);

                mRecyclerView.scrollToPosition(index);
            }
        };*/

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final TextView txtListado = findViewById(R.id.idLista);
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Producto producto = dataSnapshot.getValue(Producto.class);
                listado += ("Nombre: " + producto.nombre +" Cantidad: " + producto.cantidad + " Precio: " + producto.precio + "\n");
                txtListado.setText(listado);


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Producto producto = dataSnapshot.getValue(Producto.class);
                listado += ("Nombre: " + producto.nombre +" Cantidad: " + producto.cantidad + " Precio: " + producto.precio + "\n");
                txtListado.setText(listado);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Producto producto = dataSnapshot.getValue(Producto.class);
                listado += ("Nombre: " + producto.nombre +" Cantidad: " + producto.cantidad + " Precio: " + producto.precio + "\n");
                txtListado.setText(listado);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Producto producto = dataSnapshot.getValue(Producto.class);
                listado += ("Nombre: " + producto.nombre +" Cantidad: " + producto.cantidad + " Precio: " + producto.precio + "\n");
                txtListado.setText(listado);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "postMessages:onCancelled", databaseError.toException());
                Toast.makeText(MainActivity.this, "Failed to load Message.", Toast.LENGTH_SHORT).show();
            }
        };

        mProductoReference.addChildEventListener(childEventListener);

        // copy for removing at onStop()
        mMessageListener = childEventListener;
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mMessageListener != null) {
            mProductoReference.removeEventListener(mMessageListener);
        }

        for (Producto producto: listaProductos) {
            Log.e(TAG, "listItem: " + producto.nombre);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mAdapter.cleanup();
    }

}
