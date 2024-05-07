package Modelo;

import java.util.Date;

public class DetalleCompra {
    private int ID_DetalleCompra;
    private int ID_Usuario;
    private int ID_Producto;
    private double Total;
    private Date Fecha_Entregado;
    private int Cantidad;

    // Constructor
    public DetalleCompra(int ID_DetalleCompra, int ID_Usuario, int ID_Producto, double Total, Date Fecha_Entregado, int Cantidad) {
        this.ID_DetalleCompra = ID_DetalleCompra;
        this.ID_Usuario = ID_Usuario;
        this.ID_Producto = ID_Producto;
        this.Total = Total;
        this.Fecha_Entregado = Fecha_Entregado;
        this.Cantidad = Cantidad;
    }

    // Getters y Setters
    public int getID_DetalleCompra() {
        return ID_DetalleCompra;
    }

    public void setID_DetalleCompra(int ID_DetalleCompra) {
        this.ID_DetalleCompra = ID_DetalleCompra;
    }

    public int getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(int ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

    public int getID_Producto() {
        return ID_Producto;
    }

    public void setID_Producto(int ID_Producto) {
        this.ID_Producto = ID_Producto;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public Date getFecha_Entregado() {
        return Fecha_Entregado;
    }

    public void setFecha_Entregado(Date Fecha_Entregado) {
        this.Fecha_Entregado = Fecha_Entregado;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }
}
