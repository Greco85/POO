
package Controladores; //INICIO DE SESION

import javax.swing.JOptionPane;
import Vista.GUI_MENU_P;
import Vista.Inicio;
import Modelo.Usuario;

public class ControladorInicioSesion {
    
public static void validarCredenciales(GUI_MENU_P frameInicioSesion, String Correo, String Contraseña) {
        if (Correo.isEmpty() || Contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos",
                    "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Crea un objeto Usuario con el correo y la contraseña proporcionados
        Usuario usuario = new Usuario(Correo, Contraseña, null);

        // Verifica las credenciales en la DB
        if (ControladorUsuario.validarCredenciales(usuario)) {
            // Si las credenciales son correctas, accede al otro frame
            frameInicioSesion.setVisible(false);
            Inicio ventanaInicio = new Inicio(usuario);
            ventanaInicio.setVisible(true);
            frameInicioSesion.dispose();
        } else {
            // Si las credenciales son incorrectas, muestra un mensaje de error
            JOptionPane.showMessageDialog(null, "Correo electrónico o contraseña incorrectos",
                    "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
        }
    }
}

  


    

