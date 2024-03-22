package Controladores;

import javax.swing.JOptionPane;
import Vista.GUI_MENU_P;
import Vista.Inicio;
import Modelo.Usuario;

public class ControladorInicioSesion {

    public static void validarCredenciales(GUI_MENU_P frameInicioSesion, String correo, String contraseña) {
        if (correo.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos",
                    "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Supongamos que inicialmente el usuario tiene 2.5 estrellas
        double numEstrellas = 2.5; 
        String nombre = "Greco Joseth"; 
       
    
Usuario usuario = new Usuario(correo, contraseña, numEstrellas, nombre);

        
        if (ControladorUsuario.validarCredenciales(usuario)) { // Pasar el objeto Usuario
            frameInicioSesion.setVisible(false);
            Inicio ventanaInicio = new Inicio(usuario);
            ventanaInicio.setVisible(true);
            frameInicioSesion.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Correo electrónico o contraseña incorrectos",
                    "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
        }
    }
}

  


    

