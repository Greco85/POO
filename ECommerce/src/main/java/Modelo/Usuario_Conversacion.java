
package Modelo;

public class Usuario_Conversacion {
    private int ID_Usuario_Conversacion;
    private int ID_Conversacion;
    private int ID_Usuario;

    // Constructor
    public Usuario_Conversacion(int ID_Usuario_Conversacion, int ID_Conversacion, int ID_Usuario) {
        this.ID_Usuario_Conversacion = ID_Usuario_Conversacion;
        this.ID_Conversacion = ID_Conversacion;
        this.ID_Usuario = ID_Usuario;
    }

    // Getters y Setters
    public int getID_Usuario_Conversacion() {
        return ID_Usuario_Conversacion;
    }

    public void setID_Usuario_Conversacion(int ID_Usuario_Conversacion) {
        this.ID_Usuario_Conversacion = ID_Usuario_Conversacion;
    }

    public int getID_Conversacion() {
        return ID_Conversacion;
    }

    public void setID_Conversacion(int ID_Conversacion) {
        this.ID_Conversacion = ID_Conversacion;
    }

    public int getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(int ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }
}

