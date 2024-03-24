
package Modelo;

public class EstadoProducto {
    private int ID_EstadoProducto;
    private String EstadoProducto;

    // Constructor
    public EstadoProducto(int ID_EstadoProducto, String EstadoProducto) {
        this.ID_EstadoProducto = ID_EstadoProducto;
        this.EstadoProducto = EstadoProducto;
    }

    // Getters y Setters
    public int getID_EstadoProducto() {
        return ID_EstadoProducto;
    }

    public void setID_EstadoProducto(int ID_EstadoProducto) {
        this.ID_EstadoProducto = ID_EstadoProducto;
    }

    public String getEstadoProducto() {
        return EstadoProducto;
    }

    public void setEstadoProducto(String EstadoProducto) {
        this.EstadoProducto = EstadoProducto;
    }
}

