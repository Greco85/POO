
package Controladores; //INICIO DE SESION

import Modelo.SesionActiva;
import javax.swing.JOptionPane;
import Vista.GUI_MENU_P;
import Vista.Inicio;
import Modelo.Usuario;
import SQL.Conexion;
import java.sql.Connection;
import java.sql.SQLException;

public class ControladorInicioSesion { //Actualizacion para obtener el ID del usuario y mandarlo al modelo SesionActiva
    
public static void validarCredenciales(GUI_MENU_P frameInicioSesion, String Correo, String Contraseña) {
    if (Correo.isEmpty() || Contraseña.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos",
                "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
        return;
    }
    Usuario usuario = new Usuario(Correo, Contraseña, null);

    int ID_Usuario = obtenerIdUsuario(usuario);

    if (ID_Usuario != -1) {
        SesionActiva.setID_Usuario(ID_Usuario);
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
      private static int obtenerIdUsuario(Usuario usuario) {
        try {
            Connection conexion = Conexion.getInstance().getConexion();
            ControladorUsuario controladorUsuario = new ControladorUsuario(conexion);
            return controladorUsuario.obtenerIdUsuario(usuario.getCorreo_Electronico(), usuario.getContraseña());
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1; 
        }
    }
    
}

  


    

