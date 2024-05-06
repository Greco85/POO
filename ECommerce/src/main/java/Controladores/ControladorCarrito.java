
package Controladores; //hace cosas para las entidades Carrito

import Modelo.Carrito;
import java.sql.PreparedStatement;
import SQL.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControladorCarrito {
    
    
    private Connection conexion;
    

    public ControladorCarrito() {
        this.conexion = Conexion.getInstance().getConexion();
    }
    
    //Agregar Producto al Carrito
    public boolean agregarAlCarrito(int ID_Usuario, int ID_Producto, int Cantidad, double total) {
    PreparedStatement statement = null;
    
    try {
        String query = "INSERT INTO Carrito (ID_Usuario, ID_Producto, FechaAgregado, Cantidad, Total) VALUES (?, ?, GETDATE(), ?, ?)";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, ID_Usuario);
        statement.setInt(2, ID_Producto);
        statement.setInt(3, Cantidad);
        statement.setDouble(4, total); 
        
        statement.executeUpdate();
        return true;
    } catch (SQLException e) {
        System.err.println("Error al agregar producto al carrito: " + e.getMessage());
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
    
    //Eliminar producto del carrito
     public boolean eliminarDelCarrito(int ID_Usuario, int ID_Producto) {
        PreparedStatement statement = null;

        try {
            String query = "DELETE FROM Carrito WHERE ID_Usuario = ? AND ID_Producto = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, ID_Usuario);
            statement.setInt(2, ID_Producto);

            int filasEliminadas = statement.executeUpdate();
            return filasEliminadas > 0; 
        } catch (SQLException e) {
            System.err.println("Error al eliminar producto del carrito: " + e.getMessage());
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
    
    
    public List<Carrito> obtenerTodoElCarrito(int ID_Usuario) {
    List<Carrito> productosCarrito = new ArrayList<>();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        
        String query = "SELECT * FROM Carrito WHERE ID_Usuario = ?";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, ID_Usuario);
        resultSet = statement.executeQuery();

       while (resultSet.next()) {
            int ID_Producto = resultSet.getInt("ID_Producto");
            int cantidad = resultSet.getInt("Cantidad");
            double total = resultSet.getDouble("Total");
            Date fechaAgregado = resultSet.getDate("FechaAgregado");
            Carrito productoCarrito = new Carrito(ID_Usuario, ID_Producto, fechaAgregado, cantidad, total);
            productosCarrito.add(productoCarrito);
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener los productos del carrito: " + e.getMessage());
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

    return productosCarrito;
}

    
    
    
    
    public boolean productoEnCarrito(int ID_Usuario, int ID_Producto) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT COUNT(*) FROM Carrito WHERE ID_Usuario = ? AND ID_Producto = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, ID_Usuario);
            statement.setInt(2, ID_Producto);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.err.println("Error al verificar si el producto está en el carrito: " + ex.getMessage());
            return false;
        } finally {
        }
    }
    
    // Método para agregar o actualizar la cantidad de un producto en el carrito
    public boolean agregarOActualizarCarrito(int ID_Usuario, int ID_Producto, int cantidad, double total) {
        if (productoEnCarrito(ID_Usuario, ID_Producto)) {
            return actualizarCantidadCarrito(ID_Usuario, ID_Producto, cantidad, total);
        } else {
            return agregarAlCarrito(ID_Usuario, ID_Producto, cantidad, total);
        }
    }

    
    public boolean actualizarCantidadCarrito(int ID_Usuario, int ID_Producto, int nuevaCantidad, double nuevoTotal) {
    PreparedStatement statement = null;
    try {
        String query = "UPDATE Carrito SET Cantidad = ?, Total = ? WHERE ID_Usuario = ? AND ID_Producto = ?";
        statement = conexion.prepareStatement(query);
        
        statement.setInt(1, nuevaCantidad);
        statement.setDouble(2, nuevoTotal);
        statement.setInt(3, ID_Usuario);
        statement.setInt(4, ID_Producto);
        
        int filasActualizadas = statement.executeUpdate();
        
        return filasActualizadas > 0;
    } catch (SQLException ex) {
        System.err.println("Error al actualizar la cantidad en el carrito: " + ex.getMessage());
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
    
    
    


    //Eliminar Producto del Carrito
    
    //Vaciar Carrito
    
    //Calcular Total del Carrito
    
}
