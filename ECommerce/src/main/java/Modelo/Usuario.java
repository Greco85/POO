
package Modelo;

public class Usuario {
    
    private String correo;
    private String contraseña;

    public Usuario(String correo, String contraseña) {
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

    // Método para validar credenciales
    public static boolean validarCredenciales(String correo, String contraseña) {
        // Aquí va la lógica para validar las credenciales
        // Esto luego lo usaree para la db
        return correo.equals("123") && contraseña.equals("123");
    }

}

    
   

   
