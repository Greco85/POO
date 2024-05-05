
package Modelo;

import java.util.Date;

public class Carrito {
   
    private int ID_Usuario;
    private int ID_Producto;
    private Date Fecha_Agregado;
    private int Cantidad;
    private double Total;

    // Constructor
    public Carrito(int ID_Usuario, int ID_Producto, Date Fecha_Agregado, int Cantidad, double Total) {
        this.ID_Usuario = ID_Usuario;
        this.ID_Producto = ID_Producto;
        this.Fecha_Agregado = Fecha_Agregado;
        this.Cantidad = Cantidad;
        this.Total = Total;
    }

    // Getters y Setters
    
    public int getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(int ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

    public Date getFecha_Agregado() {
        return Fecha_Agregado;
    }

    public void setFecha_Agregado(Date Fecha_Agregado) {
        this.Fecha_Agregado = Fecha_Agregado;
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
}

