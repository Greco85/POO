
package Modelo;

public class Envio {
    private int ID_Envio;
    private int ID_EstadoEnvio;
    private int ID_MetodoEnvio;
    private String Direccion;

    // Constructor
    public Envio(int ID_Envio,int ID_EstadoEnvio, int ID_MetodoEnvio, String Direccion) {
        this.ID_Envio = ID_Envio;
        this.ID_EstadoEnvio = ID_EstadoEnvio;
        this.ID_MetodoEnvio = ID_MetodoEnvio;
        this.Direccion = Direccion;
    }

    // Getters y Setters
    public int getID_Envio() {
        return ID_Envio;
    }

    public void setID_Envio(int ID_Envio) {
        this.ID_Envio = ID_Envio;
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

    
}
