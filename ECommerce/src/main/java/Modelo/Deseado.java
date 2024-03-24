
package Modelo;

import java.util.Date;

public class Deseado {
    private int ID_Usuario;
    private int ID_Producto;
    private Date Fecha_Agregado;

    // Constructor
    public Deseado(int ID_Usuario, int ID_Producto, Date Fecha_Agregado) {
        this.ID_Usuario = ID_Usuario;
        this.ID_Producto = ID_Producto;
        this.Fecha_Agregado = Fecha_Agregado;
    }

    // Getters y Setters
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

    public Date getFecha_Agregado() {
        return Fecha_Agregado;
    }

    public void setFecha_Agregado(Date Fecha_Agregado) {
        this.Fecha_Agregado = Fecha_Agregado;
    }
}

