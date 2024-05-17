
package Controladores; //INICIO DE SESION

import Modelo.SesionActiva;
import javax.swing.JOptionPane;
import Vista.GUI_MENU_P;
import Vista.Inicio;
import Modelo.Usuario;
import SQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControladorInicioSesion { //Actualizacion para obtener el ID del usuario y mandarlo al modelo SesionActiva
    
    private static String obtenerNombreConCorreoYContraseña(String correoElectronico, String contraseña) {
    try {
        Connection conexion = Conexion.getInstance().getConexion();
        String sql = "SELECT [Nombre] FROM [EcommerceDB].[dbo].[Usuario] WHERE [Correo_Electronico] = ? AND [Contraseña] = ?";
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, correoElectronico);
        statement.setString(2, contraseña);
        ResultSet resultado = statement.executeQuery();
        
        if (resultado.next()) {
            return resultado.getString("Nombre");
        } else {
            return null; 
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        return null; 
    }
}
    
     public int obtenerIDconCorreoUsuario(String correo) {
    int ID_Usuario = -1;
    
    String query = "SELECT ID_Usuario FROM Usuario WHERE Correo_Electronico = ?";
    Connection conexion = Conexion.getInstance().getConexion();

    try {
        PreparedStatement statement = conexion.prepareStatement(query);
        statement.setString(1, correo);
        
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            ID_Usuario = resultSet.getInt("ID_Usuario");
        }
        
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        System.err.println("Error al obtener ID de usuario por correo: " + e.getMessage());
    }
    
    return ID_Usuario;
}
    
    
     public void actualizarIMGURL(int id) {
    try {
        Connection conexion = Conexion.getInstance().getConexion();
        String imgURL = "Usuario_" + id + ".jpg";
        String consulta = "UPDATE Usuario SET ImagenURL = ? WHERE ID_Usuario = ?";
        PreparedStatement statement = conexion.prepareStatement(consulta);
        statement.setString(1, imgURL);
        statement.setInt(2, id);
        int filasActualizadas = statement.executeUpdate();

        if (filasActualizadas > 0) {
            System.out.println("La IMGURL se actualizó correctamente.");
        } else {
            System.out.println("No se pudo actualizar la IMGURL.");
        }
    } catch (SQLException e) {
        System.err.println("Error al actualizar la IMGURL por ID_Usuario: " + e.getMessage());
    }
}

    
    
public static void validarCredenciales(GUI_MENU_P frameInicioSesion, String Correo, String Contraseña) {
    if (Correo.isEmpty() || Contraseña.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos",
                "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    String nombre = obtenerNombreConCorreoYContraseña(Correo,Contraseña );
    
    Usuario usuario = new Usuario(Correo, Contraseña, nombre);

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

  


    

