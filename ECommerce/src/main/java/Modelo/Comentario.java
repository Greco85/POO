
package Modelo;

import java.util.Date;

public class Comentario {
    private int ID_Comentario;
    private int ID_Usuario;
    private int ID_Producto;
    private String Comentario;
    private Date Fecha_Comentario;

    // Constructor
    public Comentario(int ID_Comentario, int ID_Usuario, int ID_Producto, String Comentario, Date Fecha_Comentario) {
        this.ID_Comentario = ID_Comentario;
        this.ID_Usuario = ID_Usuario;
        this.ID_Producto = ID_Producto;
        this.Comentario = Comentario;
        this.Fecha_Comentario = Fecha_Comentario;
    }

    // Getters y Setters
    public int getID_Comentario() {
        return ID_Comentario;
    }

    public void setID_Comentario(int ID_Comentario) {
        this.ID_Comentario = ID_Comentario;
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

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String Comentario) {
        this.Comentario = Comentario;
    }

    public Date getFecha_Comentario() {
        return Fecha_Comentario;
    }

    public void setFecha_Comentario(Date Fecha_Comentario) {
        this.Fecha_Comentario = Fecha_Comentario;
    }
}

