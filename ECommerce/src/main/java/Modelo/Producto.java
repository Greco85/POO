package Modelo;

public class Producto {
    private String nombre;  //SOLO PARA PROBAR COSAS LUEGO MODIFICO
    private String imagen;
    private double precio;

    public Producto(String nombre, String imagen, double precio) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public double getPrecio() {
        return precio;
    }
}




