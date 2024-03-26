
package Modelo;

public class Direccion {
    private String calle;
    private String numeroCasa;
    private String colonia;
    private String codigoPostal;
    private String ciudad;
    private String pais;

    // Constructor
    public Direccion(String calle, String numeroCasa, String colonia, String codigoPostal, String ciudad, String pais) {
        this.calle = calle;
        this.numeroCasa = numeroCasa;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.pais = pais;
    }
    
    public Direccion() {
        this.calle = "";
        this.numeroCasa = "";
        this.colonia = "";
        this.codigoPostal = "";
        this.ciudad = "";
        this.pais = "";
    }

    // Getters y setters

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
