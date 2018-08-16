package cl.paablo.superlista;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Arrays;

import cl.paablo.superlista.modelo.*;
import cl.paablo.superlista.viewholder.ItemViewHolder;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;

    private DatabaseReference mDatabase;
    private ChildEventListener mListener;

    private ArrayList<Producto> listado;
    private FirebaseRecyclerAdapter<Producto, ItemViewHolder> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference("listado");
        listado = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
/*

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        Query query = mDatabase.limitToLast(8);

        mAdapter = new FirebaseRecyclerAdapter<Producto, ItemViewHolder>(
                Producto.class, R.layout.recycler_lista, ItemViewHolder.class, query) {
            @Override
            protected void populateViewHolder(ItemViewHolder viewHolder, Producto producto, int position) {
                viewHolder.nombre.setText(producto.nombre);
                viewHolder.cantidad.setText(producto.cantidad);
                viewHolder.precio.setText(producto.precio);

            }

            @Override
            public void onChildChanged(EventType type, DataSnapshot snapshot, int index, int oldIndex){
                super.onChildChanged(type, snapshot, index, oldIndex);
                recyclerView.scrollToPosition(index);
            }
        };

        recyclerView.setAdapter(mAdapter);



            ChildEventListener childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Producto producto = dataSnapshot.getValue(Producto.class);
                    listado.add(producto);
                    System.out.println("ON CHILD ADDED");
                    System.out.println("Tamaño: "+listado.size());

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Producto producto = dataSnapshot.getValue(Producto.class);
                    System.out.println("ON CHILD CHANGED");
                    System.out.println("Tamaño: "+listado.size());
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    Producto producto = dataSnapshot.getValue(Producto.class);
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Producto producto = dataSnapshot.getValue(Producto.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            mDatabase.addChildEventListener(childEventListener);
            mListener = childEventListener;




            if (mListener != null){
                mDatabase.removeEventListener(mListener);
            }

            for (Producto producto : listado){
                Log.e(TAG, "list: "+producto.nombre);
            }
*/
        FloatingActionButton floatingActionButton = findViewById(R.id.btnAgregarActivity);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, AgregarProducto.class);
                    startActivity(intent);
                }
            });



    }
}
