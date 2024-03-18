
package Modelo;

public class Usuario {
    
    private String correo;
    private String contraseña;

    public Usuario(String correo, String contraseña) {  //SOLO PARA PROBAR COSAS LUEGO MODIFICO
        this.correo = correo;
        this.contraseña = contraseña;
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
}

    
   

   
