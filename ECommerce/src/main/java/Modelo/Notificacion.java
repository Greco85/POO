
package Modelo;

import java.util.Date;

public class Notificacion {
    private int ID_Notificacion;
    private int ID_Usuario;
    private int ID_TipoNoti;
    private String Mensaje;
    private Date Fecha;
    private boolean Leido;

    // Constructor
    public Notificacion(int ID_Notificacion, int ID_Usuario, int ID_TipoNoti, String Mensaje, Date Fecha, boolean Leido) {
        this.ID_Notificacion = ID_Notificacion;
        this.ID_Usuario = ID_Usuario;
        this.ID_TipoNoti = ID_TipoNoti;
        this.Mensaje = Mensaje;
        this.Fecha = Fecha;
        this.Leido = Leido;
    }

    // Getters y Setters
    public int getID_Notificacion() {
        return ID_Notificacion;
    }

    public void setID_Notificacion(int ID_Notificacion) {
        this.ID_Notificacion = ID_Notificacion;
    }

    public int getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(int ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

    public int getID_TipoNoti() {
        return ID_TipoNoti;
    }

    public void setID_TipoNoti(int ID_TipoNoti) {
        this.ID_TipoNoti = ID_TipoNoti;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public boolean isLeido() {
        return Leido;
    }

    public void setLeido(boolean Leido) {
        this.Leido = Leido;
    }
}

