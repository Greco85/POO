package Modelo;

import java.util.Date;

public class Pedido {
    private int ID_Pedido;
    private int ID_Usuario;
    private int ID_EstadoPedido;
    private int ID_MetodoEnvio;
    private String Direccion;
    private int ID_Producto;
    private int Cantidad;
    private double Total;
    private Date FechaPedido;

      public Pedido(int ID_Pedido, int ID_Usuario, int ID_EstadoPedido, int ID_MetodoEnvio, String Direccion, int ID_Producto, int Cantidad, double Total, Date FechaPedido) {
        this.ID_Pedido = ID_Pedido;
        this.ID_Usuario = ID_Usuario;
        this.ID_EstadoPedido = ID_EstadoPedido;
        this.ID_MetodoEnvio = ID_MetodoEnvio;
        this.Direccion = Direccion;
        this.ID_Producto = ID_Producto;
        this.Cantidad = Cantidad;
        this.Total = Total;
        this.FechaPedido = FechaPedido;
    }

    // Getters y Setters para todos los campos
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

    public int getID_MetodoEnvio() {
        return ID_MetodoEnvio;
    }

    public void setID_MetodoEnvio(int ID_MetodoEnvio) {
        this.ID_MetodoEnvio = ID_MetodoEnvio;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public int getID_Producto() {
        return ID_Producto;
    }

    public void setID_Producto(int ID_Producto) {
        this.ID_Producto = ID_Producto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public Date getFechaPedido() {
        return FechaPedido;
    }

    public void setFechaPedido(Date FechaPedido) {
        this.FechaPedido = FechaPedido;
    }
}
