
package Modelo;

import java.util.Date;

public class DetalleCarrito {
    private int ID_DetalleCarrito;
    private int ID_Carrito;
    private int ID_Producto;
    private int Cantidad;
    private double Total;
    private Date FechaAgregado;

    // Constructor
    public DetalleCarrito(int ID_DetalleCarrito, int ID_Carrito, int ID_Producto, int Cantidad, double Total, Date FechaAgregado) {
        this.ID_DetalleCarrito = ID_DetalleCarrito;
        this.ID_Carrito = ID_Carrito;
        this.ID_Producto = ID_Producto;
        this.Cantidad = Cantidad;
        this.Total = Total;
        this.FechaAgregado = FechaAgregado;
    }

    // Getters y Setters
    public int getID_DetalleCarrito() {
        return ID_DetalleCarrito;
    }

    public void setID_DetalleCarrito(int ID_DetalleCarrito) {
        this.ID_DetalleCarrito = ID_DetalleCarrito;
    }

    public int getID_Carrito() {
        return ID_Carrito;
    }

    public void setID_Carrito(int ID_Carrito) {
        this.ID_Carrito = ID_Carrito;
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

    public Date getFechaAgregado() {
        return FechaAgregado;
    }

    public void setFechaAgregado(Date FechaAgregado) {
        this.FechaAgregado = FechaAgregado;
    }
}

