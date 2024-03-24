
package Controladores; //INICIO DE SESION

import javax.swing.JOptionPane;
import Vista.GUI_MENU_P;
import Vista.Inicio;
import Modelo.Usuario;

public class ControladorInicioSesion {
    
    //Cerrar Sesión de Usuario
    
    public static void validarCredenciales(GUI_MENU_P frameInicioSesion, String Correo, String Contraseña) {
        if (Correo.isEmpty() || Contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos",
                    "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        String Nombre = "Greco Joseth"; 
       
    
Usuario usuario = new Usuario(Correo, Contraseña, Nombre);

        
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

  


    

