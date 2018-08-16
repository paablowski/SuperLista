package cl.paablo.superlista.modelo;


import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Producto {

    public String nombre;
    public int cantidad;
    public int precio;

    public Producto() {
    }

    public Producto(String nombre, int cantidad, int precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("nombre",nombre);
        result.put("cantidad",cantidad);
        result.put("precio",precio);

        return result;
    }
}
