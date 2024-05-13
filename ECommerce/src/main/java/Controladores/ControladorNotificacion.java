
package Controladores; //hace cosas para las entidades Notificacion, TipoNotificacion

import Modelo.Notificacion;
import SQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class ControladorNotificacion {
    
    private Connection conexion;

    public ControladorNotificacion() {
        this.conexion = Conexion.getInstance().getConexion();
        
    }
    
    
    //Notificacion
    
    //Crear Notificacion
    public void crearNotificacion(int ID_UsuariodelaNoti, int ID_TipoNoti, String mensaje, Timestamp fecha) {
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = Conexion.getInstance().getConexion();
            String query = "INSERT INTO Notificacion (ID_Usuario, ID_TipoNoti, Mensaje, Fecha, Leido) " +
                           "VALUES (?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, ID_UsuariodelaNoti);
            statement.setInt(2, ID_TipoNoti);
            statement.setString(3, mensaje);
            statement.setTimestamp(4, fecha);
            statement.setBoolean(5, false); // No leído por defecto
            statement.executeUpdate();
            System.out.println("Notificación creada correctamente");
        } catch (SQLException e) {
            System.err.println("Error al crear notificación: " + e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conexion != null) {
            }
        }
    }
    
   public boolean verificarNotificacionPendiente(int ID_Usuario) {
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        conexion = Conexion.getInstance().getConexion();
        String query = "SELECT COUNT(*) FROM Notificacion WHERE ID_Usuario = ? AND Leido = 0";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, ID_Usuario);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int cantidadNotificaciones = resultSet.getInt(1);
            return cantidadNotificaciones > 0; 
        } else {
            return false;
        }
    } catch (SQLException e) {
        System.err.println("Error al verificar notificaciones pendientes: " + e.getMessage());
        return false;
    } finally {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // No cerramos la conexión aquí
    }
   }
   
   public void borrarNotificaciones(int ID_Usuario) {
    Connection conexion = null;
    PreparedStatement statement = null;

    try {
        conexion = Conexion.getInstance().getConexion();
        String query = "DELETE FROM Notificacion WHERE ID_Usuario = ?";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, ID_Usuario);
        statement.executeUpdate();
        System.out.println("Notificaciones borradas correctamente para el usuario con ID " + ID_Usuario);
    } catch (SQLException e) {
        System.err.println("Error al borrar notificaciones: " + e.getMessage());
    } finally {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conexion != null) {
        }
    }
}

    
   
   public void marcarComoLeidasPorUsuario(int ID_Usuario) {
    Connection conexion = null;
    PreparedStatement statement = null;

    try {
        conexion = Conexion.getInstance().getConexion();
        String query = "UPDATE Notificacion SET Leido = 1 WHERE ID_Usuario = ? AND Leido = 0";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, ID_Usuario);
        statement.executeUpdate();
        System.out.println("Notificaciones marcadas como leídas correctamente para el usuario con ID " + ID_Usuario);
    } catch (SQLException e) {
        System.err.println("Error al marcar notificaciones como leídas: " + e.getMessage());
    } finally {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // No cerramos la conexión aquí
    }
}

   
   // Método para obtener todas las notificaciones de un usuario por su ID
public List<Notificacion> obtenerTodasLasNotificacionesByIDUsuario(int ID_Usuario) {
    List<Notificacion> notificaciones = new ArrayList<>();
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        conexion = Conexion.getInstance().getConexion();
        String query = "SELECT * FROM Notificacion WHERE ID_Usuario = ?";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, ID_Usuario);
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int ID_Notificacion = resultSet.getInt("ID_Notificacion");
            int ID_TipoNoti = resultSet.getInt("ID_TipoNoti");
            String mensaje = resultSet.getString("Mensaje");
            Timestamp fecha = resultSet.getTimestamp("Fecha");
            boolean leido = resultSet.getBoolean("Leido");
            
            Notificacion notificacion = new Notificacion(ID_Notificacion, ID_Usuario, ID_TipoNoti, mensaje, fecha, leido);
            notificaciones.add(notificacion);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener notificaciones por ID de usuario: " + e.getMessage());
    } finally {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // No cerramos la conexión aquí
    }
    
    return notificaciones;
}

    
    
    //Actualizar Notificacion
    //Eliminar Notificacion
    //Obtener Notificaciones por Usuario
    //Marcar Notificación como Leída
    
    //TipoNotificacion (solo para probarlo en la app)
    
    //Crear TipoNotificacion
    //Actualizar TipoNotificacion
    //Eliminar TipoNotificacion
    
}
