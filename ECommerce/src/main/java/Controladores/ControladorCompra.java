
package Controladores; //hace cosas para las entidades Compra, DetalleCompra

import SQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ControladorCompra {
    
    //Registrar Compra
    
     private Connection conexion;

    public ControladorCompra() {
        this.conexion = Conexion.getInstance().getConexion();
        
    }
    
    
    public boolean insertarDetalleCompra(int ID_Usuario, int ID_Producto, double total, String fechaEntregado, int cantidad) {
        Connection conexion = null;
        PreparedStatement statement = null;
        boolean insercionExitosa = false;
        
        try {
            conexion = Conexion.getInstance().getConexion();
            
            String query = "INSERT INTO DetalleCompra (ID_Usuario, ID_Producto, Total, Fecha_Entregado, Cantidad) VALUES (?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(query);
            
            statement.setInt(1, ID_Usuario);
            statement.setInt(2, ID_Producto);
            statement.setDouble(3, total);
            statement.setString(4, fechaEntregado);
            statement.setInt(5, cantidad);
            
            int filasInsertadas = statement.executeUpdate();
            
            if (filasInsertadas > 0) {
                insercionExitosa = true;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al insertar el detalle de compra: " + e.getMessage());
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
    
    //Obtener Compras por Usuario
    
    //Obtener Detalles de Compra por Usuario
    
    //Calcular Total de la Compra
    
    //Obtener Compras por Rango de Fechas
    
    //Generar Factura
    
    //Gestionar Estado de la Compra
    
}
