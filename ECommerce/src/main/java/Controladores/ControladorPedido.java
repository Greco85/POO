
package Controladores; //hace cosas para las entidades Pedido, EstadoPedido

import Modelo.Pedido;
import SQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


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
     
     
    public boolean actualizarEstadoPedido(int ID_Pedido, int nuevoID_EstadoPedido) {
    Connection conexion = null;
    PreparedStatement statement = null;
    boolean actualizacionExitosa = false;

    try {
        conexion = Conexion.getInstance().getConexion();
        String query = "UPDATE Pedido SET ID_EstadoPedido = ? WHERE ID_Pedido = ?";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, nuevoID_EstadoPedido);
        statement.setInt(2, ID_Pedido);
        int filasAfectadas = statement.executeUpdate();

        if (filasAfectadas > 0) {
            actualizacionExitosa = true;
        }
    } catch (SQLException e) {
        System.err.println("Error al actualizar el estado de envío del pedido: " + e.getMessage());
    } finally {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    return actualizacionExitosa;
}
    
    
    public boolean borrarPedido(int ID_Pedido) {
    Connection conexion = null;
    PreparedStatement statement = null;
    boolean borradoExitoso = false;

    try {
        conexion = Conexion.getInstance().getConexion();
        String query = "DELETE FROM Pedido WHERE ID_Pedido = ?";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, ID_Pedido);
        int filasAfectadas = statement.executeUpdate();

        if (filasAfectadas > 0) {
            borradoExitoso = true;
        }
    } catch (SQLException e) {
        System.err.println("Error al borrar el pedido: " + e.getMessage());
    } finally {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return borradoExitoso;
}

     
     
    public List<Pedido> obtenerPedidosPorIDProducto(int ID_Producto) {
        List<Pedido> pedidos = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = Conexion.getInstance().getConexion();
            String consulta = "SELECT * FROM Pedido WHERE ID_Producto = ?";
            statement = conexion.prepareStatement(consulta);
            statement.setInt(1, ID_Producto);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            int idPedido = resultSet.getInt("ID_Pedido");
                int idUsuario = resultSet.getInt("ID_Usuario");
                int idEstadoPedido = resultSet.getInt("ID_EstadoPedido");
                int idMetodoEnvio = resultSet.getInt("ID_MetodoEnvio");
                String direccion = resultSet.getString("Direccion");
                int cantidad = resultSet.getInt("Cantidad");
                double total = resultSet.getDouble("Total");
                Timestamp fechaPedidoTimestamp = resultSet.getTimestamp("FechaPedido");

                Pedido pedido = new Pedido(idPedido, idUsuario, idEstadoPedido, idMetodoEnvio, direccion, ID_Producto, cantidad, total, fechaPedidoTimestamp);
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener pedidos por ID de producto: " + e.getMessage());
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
        

        return pedidos;
    }
    
    
     public List<Pedido> obtenerPedidosPorIDUsuario(int ID_Usuario) {
        List<Pedido> pedidos = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = Conexion.getInstance().getConexion();
            String consulta = "SELECT * FROM Pedido WHERE ID_Usuario = ?";
            statement = conexion.prepareStatement(consulta);
            statement.setInt(1, ID_Usuario);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            int idPedido = resultSet.getInt("ID_Pedido");
                int idEstadoPedido = resultSet.getInt("ID_EstadoPedido");
                int idMetodoEnvio = resultSet.getInt("ID_MetodoEnvio");
                String direccion = resultSet.getString("Direccion");
                int idProducto = resultSet.getInt("ID_Producto");

                int cantidad = resultSet.getInt("Cantidad");
                double total = resultSet.getDouble("Total");
                Timestamp fechaPedidoTimestamp = resultSet.getTimestamp("FechaPedido");

                Pedido pedido = new Pedido(idPedido, ID_Usuario, idEstadoPedido, idMetodoEnvio, direccion, idProducto, cantidad, total, fechaPedidoTimestamp);
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener pedidos por ID de producto: " + e.getMessage());
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
        

        return pedidos;
    }
    
    
    public List<Pedido> obtenertodosPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = Conexion.getInstance().getConexion();
            String consulta = "SELECT * FROM Pedido";
            statement = conexion.prepareStatement(consulta);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            int idPedido = resultSet.getInt("ID_Pedido");
                int idUsuario = resultSet.getInt("ID_Usuario");
                int idEstadoPedido = resultSet.getInt("ID_EstadoPedido");
                int idMetodoEnvio = resultSet.getInt("ID_MetodoEnvio");
                String direccion = resultSet.getString("Direccion");
                int ID_Producto = resultSet.getInt("ID_Producto");

                int cantidad = resultSet.getInt("Cantidad");
                double total = resultSet.getDouble("Total");
                Timestamp fechaPedidoTimestamp = resultSet.getTimestamp("FechaPedido");

                Pedido pedido = new Pedido(idPedido, idUsuario, idEstadoPedido, idMetodoEnvio, direccion, ID_Producto, cantidad, total, fechaPedidoTimestamp);
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener pedidos por ID de producto: " + e.getMessage());
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
        

        return pedidos;
    }

}