package Controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelo.Usuario;
import SQL.Conexion;
import java.sql.Connection;

public class ControladorUsuario {

    private final Connection conexion;
    
    public ControladorUsuario(Connection conexion) {
        this.conexion = conexion;
    }

public void RegistrarUsuario(Usuario usuario) {
        // Definir la consulta SQL para registrar a un usuario nuevo
        String sql = "INSERT INTO Usuario (Nombre, Apellido, Correo_Electronico, Contraseña, Telefono, Fecha_Nacimiento, Fecha_Registro, ImagenURL) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getCorreo_Electronico());
            statement.setString(4, usuario.getContraseña());
            statement.setString(5, usuario.getTelefono());
            statement.setDate(6, new java.sql.Date(usuario.getFecha_Nacimiento().getTime()));
            statement.setDate(7, new java.sql.Date(usuario.getFecha_Registro().getTime()));
            statement.setString(8, usuario.getImagenURL());

            // Ejecutar la consulta SQL para insertar el usuario
            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Usuario insertado correctamente en la base de datos.");
            } else {
                System.out.println("Error al insertar el usuario en la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta SQL: " + e.getMessage());
        }
    }

public static boolean validarCredenciales(Connection conexion, Usuario usuario) {
        boolean credencialesCorrectas = false;

        try {
            String consulta = "SELECT * FROM Usuario WHERE Correo_Electronico = ? AND Contraseña = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, usuario.getCorreo_Electronico());
            statement.setString(2, usuario.getContraseña());
            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {
                credencialesCorrectas = true;
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }

        return credencialesCorrectas;
    }
    
    //Actualizar Usuario
    
    //Eliminar Usuario
    
    //Cerrar Sesión de Usuario 
    
    
public static boolean validarCredenciales(Usuario usuario) {
    boolean credencialesCorrectas = false;
    Conexion conexion = Conexion.getInstance();
    Connection conn = null;

    try {
        conn = conexion.getConexion(); // Obtener la conexión
        String consulta = "SELECT * FROM Usuario WHERE Correo_Electronico = ? AND Contraseña = ?";
        PreparedStatement statement = conn.prepareStatement(consulta);
        statement.setString(1, usuario.getCorreo_Electronico());
        statement.setString(2, usuario.getContraseña());
        ResultSet resultado = statement.executeQuery();

        if (resultado.next()) {
            credencialesCorrectas = true;
        }
    } catch (SQLException e) {
        System.err.println("Error al ejecutar la consulta: " + e.getMessage());
    } finally {
        if (conn != null) {
            try {
                conn.close(); // Cerrar la conexión en el bloque finally para garantizar que siempre se cierre
                System.out.println("Conexión cerrada correctamente");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
    
    return credencialesCorrectas;
}




    
}






