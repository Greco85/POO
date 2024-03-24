
package Modelo;

public class EstadoEnvio {
    private int ID_EstadoEnvio;
    private String EstadoEnvio;

    // Constructor
    public EstadoEnvio(int ID_EstadoEnvio, String EstadoEnvio) {
        this.ID_EstadoEnvio = ID_EstadoEnvio;
        this.EstadoEnvio = EstadoEnvio;
    }

    // Getters y Setters
    public int getID_EstadoEnvio() {
        return ID_EstadoEnvio;
    }

    public void setID_EstadoEnvio(int ID_EstadoEnvio) {
        this.ID_EstadoEnvio = ID_EstadoEnvio;
    }

    public String getEstadoEnvio() {
        return EstadoEnvio;
    }

    public void setEstadoEnvio(String EstadoEnvio) {
        this.EstadoEnvio = EstadoEnvio;
    }
}

