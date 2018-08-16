package cl.paablo.superlista;

import java.util.HashMap;
import java.util.Map;

class Producto {

    private String nombre;
    private int cantidad;
    private int precio;

    public Producto(String nombre, int cantidad, int precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("nombre",nombre);
        result.put("cantidad",cantidad);
        result.put("precio",precio);

        return result;
    }
}
