
package Controladores; //hace cosas para las entidades Pedido, EstadoPedido

import SQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;


public class ControladorPedido {
    
     private Connection conexion;

    public ControladorPedido() {
        this.conexion = Conexion.getInstance().getConexion();
        
    }
    
    //Pedido
    
    //Crear Pedido
    //Actualizar Pedido
    //Eliminar Pedido
    //Obtener Pedidos por Usuario
    
    //EstadoPedido
    
    //Crear EstadoPedido
    //Actualizar EstadoPedido
    //Eliminar EstadoPedido
    
    //Obtener EstadoPedido
    
    public int obtenerIdEstadoPedido(String estadoEnvio) {
    int idEstadoPedido = -1;

    String consulta = "SELECT ID_EstadoPedido FROM EstadoPedido WHERE EstadoPedido = ?";

    try {
        PreparedStatement statement = conexion.prepareStatement(consulta);
        statement.setString(1, estadoEnvio);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                idEstadoPedido = resultSet.getInt("ID_EstadoPedido");
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener el ID del estado del pedido: " + e.getMessage());
    }

    return idEstadoPedido;
}

    
    
     public boolean insertarPedido(int ID_Usuario, int ID_EstadoPedido, int ID_MetodoEnvio,
                                  String direccion, int ID_Producto, int Cantidad, double Total,
                                  Timestamp fechaYHoraActual) {
        Connection conexion = null;
        PreparedStatement statement = null;
        boolean insercionExitosa = false;

        try {
            conexion = Conexion.getInstance().getConexion();
            String query = "INSERT INTO Pedido (ID_Usuario, ID_EstadoPedido, ID_MetodoEnvio, Direccion, " +
                           "ID_Producto, Cantidad, Total, FechaPedido) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, ID_Usuario);
            statement.setInt(2, ID_EstadoPedido);
            statement.setInt(3, ID_MetodoEnvio);
            statement.setString(4, direccion);
            statement.setInt(5, ID_Producto);
            statement.setInt(6, Cantidad);
            statement.setDouble(7, Total);
            statement.setTimestamp(8, fechaYHoraActual);
            int filasAfectadas = statement.executeUpdate();
            
            if (filasAfectadas > 0) {
                insercionExitosa = true;
            } else {
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar pedido: " + e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return insercionExitosa;
    }


}