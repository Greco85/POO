
package Modelo;

public class MetodoEnvio {
    private int ID_MetodoEnvio;
    private String MetodoEnvio;

    // Constructor
    public MetodoEnvio(int ID_MetodoEnvio, String MetodoEnvio) {
        this.ID_MetodoEnvio = ID_MetodoEnvio;
        this.MetodoEnvio = MetodoEnvio;
    }

    // Getters y Setters
    public int getID_MetodoEnvio() {
        return ID_MetodoEnvio;
    }

    public void setID_MetodoEnvio(int ID_MetodoEnvio) {
        this.ID_MetodoEnvio = ID_MetodoEnvio;
    }

    public String getMetodoEnvio() {
        return MetodoEnvio;
    }

    public void setMetodoEnvio(String MetodoEnvio) {
        this.MetodoEnvio = MetodoEnvio;
    }
}

