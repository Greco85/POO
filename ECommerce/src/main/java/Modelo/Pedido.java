
package Modelo;

public class Pedido {
    private int ID_Pedido;
    private int ID_Usuario;
    private int ID_EstadoPedido;
    private int ID_Envio;

    // Constructor
    public Pedido(int ID_Pedido, int ID_Usuario, int ID_EstadoPedido,int ID_Envio) {
        this.ID_Pedido = ID_Pedido;
        this.ID_Usuario = ID_Usuario;
        this.ID_EstadoPedido = ID_EstadoPedido;
        this.ID_Envio = ID_Envio;
    }

    // Getters y Setters
    public int getID_Pedido() {
        return ID_Pedido;
    }

    public void setID_Pedido(int ID_Pedido) {
        this.ID_Pedido = ID_Pedido;
    }

    public int getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(int ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

    public int getID_EstadoPedido() {
        return ID_EstadoPedido;
    }

    public void setID_EstadoPedido(int ID_EstadoPedido) {
        this.ID_EstadoPedido = ID_EstadoPedido;
    }


    public int getID_Envio() {
        return ID_Envio;
    }

    public void setID_Envio(int ID_Envio) {
        this.ID_Envio = ID_Envio;
    }
}

