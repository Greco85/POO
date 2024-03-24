
package Modelo;

import java.util.Date;

public class Conversacion {
    private int ID_Conversacion;
    private int ID_Vendedor;
    private int ID_Comprador;
    private Date Fecha_Ultimo_Mensaje;

    // Constructor
    public Conversacion(int ID_Conversacion, int ID_Vendedor, int ID_Comprador, Date Fecha_Ultimo_Mensaje) {
        this.ID_Conversacion = ID_Conversacion;
        this.ID_Vendedor = ID_Vendedor;
        this.ID_Comprador = ID_Comprador;
        this.Fecha_Ultimo_Mensaje = Fecha_Ultimo_Mensaje;
    }

    // Getters y Setters
    public int getID_Conversacion() {
        return ID_Conversacion;
    }

    public void setID_Conversacion(int ID_Conversacion) {
        this.ID_Conversacion = ID_Conversacion;
    }

    public int getID_Vendedor() {
        return ID_Vendedor;
    }

    public void setID_Vendedor(int ID_Vendedor) {
        this.ID_Vendedor = ID_Vendedor;
    }

    public int getID_Comprador() {
        return ID_Comprador;
    }

    public void setID_Comprador(int ID_Comprador) {
        this.ID_Comprador = ID_Comprador;
    }

    public Date getFecha_Ultimo_Mensaje() {
        return Fecha_Ultimo_Mensaje;
    }

    public void setFecha_Ultimo_Mensaje(Date Fecha_Ultimo_Mensaje) {
        this.Fecha_Ultimo_Mensaje = Fecha_Ultimo_Mensaje;
    }
}

