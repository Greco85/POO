package Controladores;

import Modelo.Usuario;

public class ControladorUsuario {

    public static void registrarUsuario(String correo, String contraseña) {
        // Lógica para registrar un nuevo usuario
    }

    public static void actualizarUsuario(String correo, String nuevaContraseña) {
        // Lógica para actualizar la contraseña de un usuario
    }

    public static void eliminarUsuario(String correo) {
        // Lógica para eliminar un usuario
    }
    
    public static boolean validarCredenciales(Usuario usuario) {
        // Extraer el correo y la contraseña del objeto Usuario
        String correo = usuario.getCorreo();
        String contraseña = usuario.getContraseña();
        
        // Lógica para validar las credenciales
        // Por simplicidad, asumimos que el usuario es válido si el correo es "123" y la contraseña es "123"
        return correo.equals("123") && contraseña.equals("123");
    }
}




