package Modelo;

public class Usuario {
    private String correo;
    private String contraseña;
    private double numEstrellas;
    private String nombre; 

    public Usuario(String correo, String contraseña, double numEstrellas, String nombre) {
        this.correo = correo;
        this.contraseña = contraseña;
        this.numEstrellas = numEstrellas;
        this.nombre = nombre; 
    }

    // Getters y setters
    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public double getNumEstrellas() {
        return numEstrellas;
    }

    public void setNumEstrellas(double numEstrellas) {
        this.numEstrellas = numEstrellas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}


   
