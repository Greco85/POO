
package Controladores; //hace cosas para la entidad Comentario

import Modelo.Comentario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import SQL.Conexion;
import java.sql.Connection;
import java.util.Date;

public class ControladorComentario {
    
    
    private Connection conexion;
    

    public ControladorComentario() {
        this.conexion = Conexion.getInstance().getConexion();
    }
    
    // Ver los Comentarios ??
    
    //Crear Comentario
     public boolean hacerComentario(int ID_Usuario, int ID_Producto, String Comentario, Date fechaComentario) {
        PreparedStatement statement = null;
        
        try {
            String query = "INSERT INTO Comentario (ID_Usuario, ID_Producto, Comentario, Fecha_Comentario) VALUES (?, ?, ?, ?)";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, ID_Usuario);
            statement.setInt(2, ID_Producto);
            statement.setString(3, Comentario);
            statement.setTimestamp(4, new java.sql.Timestamp(fechaComentario.getTime())); // Convierte Date a Timestamp
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al hacer el comentario: " + e.getMessage());
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
     
    //Eliminar Comentario
        public boolean borrarComentario(int ID_Comentario) {
        // Lógica para borrar el comentario en la base de datos
        PreparedStatement statement = null;
        try {
            String query = "DELETE FROM Comentario WHERE ID_Comentario = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, ID_Comentario);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.err.println("Error al borrar el comentario: " + ex.getMessage());
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    System.err.println("Error al cerrar la declaración: " + ex.getMessage());
                }
            }
        }
    }
    
    //Obtener Comentario Por ID   
    public Comentario ObtenerComentarioPorID(int ID_Comentario) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM Comentario WHERE ID_Comentario = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, ID_Comentario);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Comentario comentario = new Comentario(
                    resultSet.getInt("ID_Comentario"),
                    resultSet.getInt("ID_Usuario"),
                    resultSet.getInt("ID_Producto"),
                    resultSet.getString("Comentario"),
                    resultSet.getDate("Fecha_Comentario")
                );
                return comentario;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener el comentario: " + ex.getMessage());
            return null;
        } finally {
        }
    }
        
    //Actualizar Comentario
public boolean actualizarComentario(int ID_Comentario, String nuevoComentario) {
    PreparedStatement statement = null;
    try {
        String query = "UPDATE Comentario SET Comentario = ? WHERE ID_Comentario = ?";
        statement = conexion.prepareStatement(query);
        statement.setString(1, nuevoComentario);
        statement.setInt(2, ID_Comentario);
        int rowsUpdated = statement.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException ex) {
        System.err.println("Error al actualizar el comentario: " + ex.getMessage());
        return false;
    } finally {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar el statement: " + ex.getMessage());
            }
        }
    }
}

    
    //Obtener Comentarios por Producto
      public List<Comentario> obtenerComentariosPorIDProducto(int ID_Producto) {
        List<Comentario> comentarios = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM Comentario WHERE ID_Producto = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, ID_Producto);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int ID_Comentario = resultSet.getInt("ID_Comentario");
                int ID_Usuario = resultSet.getInt("ID_Usuario");
                String Comentario = resultSet.getString("Comentario");
                Date Fecha_Comentario = resultSet.getDate("Fecha_Comentario");

                Comentario comentario = new Comentario(ID_Comentario, ID_Usuario, ID_Producto, Comentario, Fecha_Comentario);
                comentarios.add(comentario);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los comentarios: " + e.getMessage());
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
        }
        return comentarios;
    }
}
    
    //Validar Existencia de un comentario por parte de un Usuario a un Producto
    
    //Contabilizar Comentarios
    
    
