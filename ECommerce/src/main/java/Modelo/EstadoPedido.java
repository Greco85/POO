package Modelo;

public class EstadoPedido {
    private int ID_EstadoPedido;
    private String EstadoPedido;

    // Constructor
    public EstadoPedido(int ID_EstadoPedido, String EstadoPedido) {
        this.ID_EstadoPedido = ID_EstadoPedido;
        this.EstadoPedido = EstadoPedido;
    }

    // Getters y Setters
    public int getID_EstadoPedido() {
        return ID_EstadoPedido;
    }

    public void setID_EstadoPedido(int ID_EstadoPedido) {
        this.ID_EstadoPedido = ID_EstadoPedido;
    }

    public String getEstadoPedido() {
        return EstadoPedido;
    }

    public void setEstadoPedido(String EstadoPedido) {
        this.EstadoPedido = EstadoPedido;
    }
}

