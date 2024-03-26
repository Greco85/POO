
package Modelo;


import java.util.Date;

public class Usuario {
    private int ID_Usuario;
    private String Nombre;
    private String Apellido;
    private String Correo_Electronico;
    private String Contraseña;
    private Direccion direccion;
    private String Telefono;
    private Date Fecha_Registro;
    private Date Fecha_Nacimiento;
    private String ImagenURL;
   
    // Constructor
    public Usuario(int ID_Usuario, String Nombre, String Apellido, String Correo_Electronico, String Contraseña, Direccion direccion, String Telefono, Date Fecha_Registro, Date Fecha_Nacimiento, String ImagenURL) {
        this.ID_Usuario = ID_Usuario;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Correo_Electronico = Correo_Electronico;
        this.Contraseña = Contraseña;
        this.direccion = direccion;
        this.Telefono = Telefono;
        this.Fecha_Registro = Fecha_Registro;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
        this.ImagenURL = ImagenURL;
    }
  
    public Usuario(String Correo_Electronico, String Contraseña, String Nombre, String Apellido, String Telefono, Date Fecha_Registro, Date Fecha_Nacimiento, String ImagenURL) {
        this.Correo_Electronico = Correo_Electronico;
        this.Contraseña = Contraseña;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Telefono = Telefono;
        this.Fecha_Registro = Fecha_Registro;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
        this.ImagenURL = ImagenURL;
    }
    
    // Constructor para utilizar en el controlador de inicio de sesión
    public Usuario(String Correo_Electronico, String Contraseña, String Nombre) {
        this.Correo_Electronico = Correo_Electronico;
        this.Contraseña = Contraseña;
        this.Nombre = Nombre;
    }
    
    // Constructor por defecto
    public Usuario() { 
        this.direccion = null;
    }

    // Getters y Setters
    public int getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(int ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getCorreo_Electronico() {
        return Correo_Electronico;
    }

    public void setCorreo_Electronico(String Correo_Electronico) {
        this.Correo_Electronico = Correo_Electronico;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public Date getFecha_Registro() {
        return Fecha_Registro;
    }

    public void setFecha_Registro(Date Fecha_Registro) {
        this.Fecha_Registro = Fecha_Registro;
    }

    public Date getFecha_Nacimiento() {
        return Fecha_Nacimiento;
    }

    public void setFecha_Nacimiento(Date Fecha_Nacimiento) {
        this.Fecha_Nacimiento = Fecha_Nacimiento;
    }

    public String getImagenURL() {
        return ImagenURL;
    }

    public void setImagenURL(String ImagenURL) {
        this.ImagenURL = ImagenURL;
    }
}
