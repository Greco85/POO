
package Modelo;

public class TipoNotificacion {
    private int ID_TipoNoti;
    private String TipoNotificacion;

    // Constructor
    public TipoNotificacion(int ID_TipoNoti, String TipoNotificacion) {
        this.ID_TipoNoti = ID_TipoNoti;
        this.TipoNotificacion = TipoNotificacion;
    }

    // Getters y Setters
    public int getID_TipoNoti() {
        return ID_TipoNoti;
    }

    public void setID_TipoNoti(int ID_TipoNoti) {
        this.ID_TipoNoti = ID_TipoNoti;
    }

    public String getTipoNotificacion() {
        return TipoNotificacion;
    }

    public void setTipoNotificacion(String TipoNotificacion) {
        this.TipoNotificacion = TipoNotificacion;
    }
}

