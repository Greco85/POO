
package Controladores; //hace cosas para las entidades Envio, EstadoEnvio, MetodoEnvio

import SQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class ControladorEnvio {
    
    
    private Connection conexion;

    public ControladorEnvio() {
        this.conexion = Conexion.getInstance().getConexion();
        
    }
    
    
    //MetodoEnvio
    public int obtenerIdMetodoEnvioPorNombre(String nombreMetodoEnvio) {
            int ID_MetodoEnvio = -1;
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                String consulta = "SELECT ID_MetodoEnvio FROM MetodoEnvio WHERE MetodoEnvio = ?";
                statement = conexion.prepareStatement(consulta);
                statement.setString(1, nombreMetodoEnvio);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    ID_MetodoEnvio = resultSet.getInt("ID_MetodoEnvio");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return ID_MetodoEnvio;
        }

    
    //Traer Metodos de envio
    
    public List<String> obtenerTiposEnvio() {
            List<String> tiposEnvio = new ArrayList<>();
            Connection conexion = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                conexion = Conexion.getInstance().getConexion();
                String query = "SELECT ID_MetodoEnvio, MetodoEnvio FROM MetodoEnvio";
                statement = conexion.prepareStatement(query);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    int idTipoEnvio = resultSet.getInt("ID_MetodoEnvio");
                    String nombreTipoEnvio = resultSet.getString("MetodoEnvio");
                    tiposEnvio.add(nombreTipoEnvio);
                }
            } catch (SQLException e) {
                System.err.println("Error al obtener tipos de envío: " + e.getMessage());
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
            return tiposEnvio;
        }
    
    
    
    //Crear Metodo de Envio
    //Actualizar Metodo de Envio
    //Eliminar Metodo de Envio
    
}
