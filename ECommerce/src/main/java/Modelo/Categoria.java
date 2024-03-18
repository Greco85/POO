
package Modelo;

public class Categoria {
    private String nombre; //SOLO PARA PROBAR COSAS LUEGO MODIFICO
    private int id;

    public Categoria(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return nombre;
    }
}