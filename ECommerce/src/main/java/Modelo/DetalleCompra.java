package Modelo;

import java.util.Date;

public class DetalleCompra {
    private int ID_DetalleCompra;
    private int ID_Producto;
    private int Cantidad;
    private double Total;
    private int ID_Pedido;
    private Date Fecha_Compra;

    // Constructor
    public DetalleCompra(int ID_DetalleCompra, int ID_Producto, int Cantidad, double Total, int ID_Pedido, Date Fecha_Compra) {
        this.ID_DetalleCompra = ID_DetalleCompra;
        this.ID_Producto = ID_Producto;
        this.Cantidad = Cantidad;
        this.Total = Total;
        this.ID_Pedido = ID_Pedido;
        this.Fecha_Compra = Fecha_Compra;
    }

    // Getters y Setters
    public int getID_DetalleCompra() {
        return ID_DetalleCompra;
    }

    public void setID_DetalleCompra(int ID_DetalleCompra) {
        this.ID_DetalleCompra = ID_DetalleCompra;
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

    public int getID_Pedido() {
        return ID_Pedido;
    }

    public void setID_Pedido(int ID_Pedido) {
        this.ID_Pedido = ID_Pedido;
    }

    public Date getFecha_Compra() {
        return Fecha_Compra;
    }

    public void setFecha_Compra(Date Fecha_Compra) {
        this.Fecha_Compra = Fecha_Compra;
    }
}
