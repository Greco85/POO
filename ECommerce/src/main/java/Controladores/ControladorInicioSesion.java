package Controladores;

import javax.swing.JOptionPane;
import Modelo.Usuario;
import Vista.Inicio;
import Vista.GUI_MENU_P;

public class ControladorInicioSesion {

    public static void validarCredenciales(GUI_MENU_P frame, String correo, String contraseña) {
        if (correo.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese el correo electrónico y la contraseña",
                    "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (Usuario.validarCredenciales(correo, contraseña)) {
            // Si las credenciales son válidas, mostramos la ventana de inicio
            Usuario usuario = new Usuario(correo, contraseña);
            Inicio inicio = new Inicio(usuario);
            inicio.setVisible(true);
            frame.dispose();
        } else {
            // Si las credenciales son inválidas, mostramos un mensaje de error
            JOptionPane.showMessageDialog(null, "Correo electrónico o contraseña incorrectos",
                    "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
        }
    }
}


  


    

