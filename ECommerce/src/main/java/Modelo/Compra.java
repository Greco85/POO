
package Modelo;

import java.util.Date;

public class Compra {
    private int ID_Compra;
    private int ID_Usuario;
    private int ID_Producto;
    private int Cantidad;
    private Date Fecha_Compra;

    // Constructor
    public Compra(int ID_Compra, int ID_Usuario, int ID_Producto, int Cantidad, Date Fecha_Compra) {
        this.ID_Compra = ID_Compra;
        this.ID_Usuario = ID_Usuario;
        this.ID_Producto = ID_Producto;
        this.Cantidad = Cantidad;
        this.Fecha_Compra = Fecha_Compra;
    }

    // Getters y Setters
    public int getID_Compra() {
        return ID_Compra;
    }

    public void setID_Compra(int ID_Compra) {
        this.ID_Compra = ID_Compra;
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

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public Date getFecha_Compra() {
        return Fecha_Compra;
    }

    public void setFecha_Compra(Date Fecha_Compra) {
        this.Fecha_Compra = Fecha_Compra;
    }
}

