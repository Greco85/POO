package Controladores;

import Modelo.Usuario;

public class ControladorUsuario {

    //Registrar Usuario
    
    //Actualizar Usuario
    
    //Eliminar Usuario
    
    //Cerrar Sesión de Usuario 
    
    
    public static boolean validarCredenciales(Usuario usuario) {
        // Obtener el correo y la contraseña del objeto Usuario
        String correo = usuario.getCorreo_Electronico();
        String contraseña = usuario.getContraseña();
        
        // Lógica para validar las credenciales
        // Por simplicidad, asumimos que el usuario es válido si el correo es "123" y la contraseña es "123"
        return correo.equals("123") && contraseña.equals("123");
    }
}






