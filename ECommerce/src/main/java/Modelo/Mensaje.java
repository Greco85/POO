
package Modelo;

import java.util.Date;

public class Mensaje {
    private int ID_Mensaje;
    private int ID_Usuario_Emisor;
    private String Mensaje;
    private Date Fecha_Envio;
    private boolean Leido;
    private int ID_Conversacion;

    // Constructor
    public Mensaje(int ID_Mensaje, int ID_Usuario_Emisor, String Mensaje, Date Fecha_Envio, boolean Leido, int ID_Conversacion) {
        this.ID_Mensaje = ID_Mensaje;
        this.ID_Usuario_Emisor = ID_Usuario_Emisor;
        this.Mensaje = Mensaje;
        this.Fecha_Envio = Fecha_Envio;
        this.Leido = Leido;
        this.ID_Conversacion = ID_Conversacion;
    }

    // Getters y Setters
    public int getID_Mensaje() {
        return ID_Mensaje;
    }

    public void setID_Mensaje(int ID_Mensaje) {
        this.ID_Mensaje = ID_Mensaje;
    }

    public int getID_Usuario_Emisor() {
        return ID_Usuario_Emisor;
    }

    public void setID_Usuario_Emisor(int ID_Usuario_Emisor) {
        this.ID_Usuario_Emisor = ID_Usuario_Emisor;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public Date getFecha_Envio() {
        return Fecha_Envio;
    }

    public void setFecha_Envio(Date Fecha_Envio) {
        this.Fecha_Envio = Fecha_Envio;
    }

    public boolean isLeido() {
        return Leido;
    }

    public void setLeido(boolean Leido) {
        this.Leido = Leido;
    }

    public int getID_Conversacion() {
        return ID_Conversacion;
    }

    public void setID_Conversacion(int ID_Conversacion) {
        this.ID_Conversacion = ID_Conversacion;
    }
}

