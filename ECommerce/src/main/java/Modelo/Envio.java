
package Modelo;

import java.util.Date;

public class Envio {
    private int ID_Envio;
    private int ID_Pedido;
    private int ID_Usuario;
    private Date Fecha_Envio;
    private int ID_EstadoEnvio;
    private int ID_MetodoEnvio;
    private String Direccion;
    private String Ciudad;
    private String Codigo_Postal;

    // Constructor
    public Envio(int ID_Envio, int ID_Pedido, int ID_Usuario, Date Fecha_Envio, int ID_EstadoEnvio, int ID_MetodoEnvio, String Direccion, String Ciudad, String Codigo_Postal) {
        this.ID_Envio = ID_Envio;
        this.ID_Pedido = ID_Pedido;
        this.ID_Usuario = ID_Usuario;
        this.Fecha_Envio = Fecha_Envio;
        this.ID_EstadoEnvio = ID_EstadoEnvio;
        this.ID_MetodoEnvio = ID_MetodoEnvio;
        this.Direccion = Direccion;
        this.Ciudad = Ciudad;
        this.Codigo_Postal = Codigo_Postal;
    }

    // Getters y Setters
    public int getID_Envio() {
        return ID_Envio;
    }

    public void setID_Envio(int ID_Envio) {
        this.ID_Envio = ID_Envio;
    }

    public int getID_Pedido() {
        return ID_Pedido;
    }

    public void setID_Pedido(int ID_Pedido) {
        this.ID_Pedido = ID_Pedido;
    }

    public int getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(int ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

    public Date getFecha_Envio() {
        return Fecha_Envio;
    }

    public void setFecha_Envio(Date Fecha_Envio) {
        this.Fecha_Envio = Fecha_Envio;
    }

    public int getID_EstadoEnvio() {
        return ID_EstadoEnvio;
    }

    public void setID_EstadoEnvio(int ID_EstadoEnvio) {
        this.ID_EstadoEnvio = ID_EstadoEnvio;
    }

    public int getID_MetodoEnvio() {
        return ID_MetodoEnvio;
    }

    public void setID_MetodoEnvio(int ID_MetodoEnvio) {
        this.ID_MetodoEnvio = ID_MetodoEnvio;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public String getCodigo_Postal() {
        return Codigo_Postal;
    }

    public void setCodigo_Postal(String Codigo_Postal) {
        this.Codigo_Postal = Codigo_Postal;
    }
}
