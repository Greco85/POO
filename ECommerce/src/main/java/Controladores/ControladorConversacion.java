
package Controladores; //hace cosas para las entidades Conversacion, Mensaje, Usuario_Conversacion

import Modelo.Conversacion;
import Modelo.Mensaje;
import SQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class ControladorConversacion {
    
    private Connection conexion;

    public ControladorConversacion() {
        this.conexion = Conexion.getInstance().getConexion();
        
    }
    
    
    
    public void crearConversacion(int ID_Vendedor, int ID_Comprador, Timestamp fechaUltimoMensaje) {
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = Conexion.getInstance().getConexion();
            String query = "INSERT INTO Conversacion (ID_Vendedor, ID_Comprador, Fecha_Ultimo_Mensaje) " +
                           "VALUES (?, ?, ?)";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, ID_Vendedor);
            statement.setInt(2, ID_Comprador);
            statement.setTimestamp(3, fechaUltimoMensaje);
            statement.executeUpdate();
            System.out.println("Conversación creada correctamente");
        } catch (SQLException e) {
            System.err.println("Error al crear conversación: " + e.getMessage());
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
    
    
    public Mensaje obtenerUltimoMensaje(int ID_Conversacion) {
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    Mensaje ultimoMensaje = null;

    try {
        conexion = Conexion.getInstance().getConexion();
        String query = "SELECT TOP 1 * FROM Mensaje WHERE ID_Conversacion = ? ORDER BY Fecha_Envio DESC";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, ID_Conversacion);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int ID_Mensaje = resultSet.getInt("ID_Mensaje");
            int ID_Usuario_Emisor = resultSet.getInt("ID_Usuario_Emisor");
            String mensaje = resultSet.getString("Mensaje");
            Timestamp fechaEnvio = resultSet.getTimestamp("Fecha_Envio");
            boolean leido = resultSet.getBoolean("Leido");

            ultimoMensaje = new Mensaje(ID_Mensaje, ID_Usuario_Emisor, mensaje, fechaEnvio, leido, ID_Conversacion);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener el último mensaje de la conversación: " + e.getMessage());
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

    return ultimoMensaje;
}

    
    
    // Función para verificar si ya existe una conversación entre un vendedor y un comprador
    public boolean verificarExistenciaConversacion(int ID_Vendedor, int ID_Comprador) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = Conexion.getInstance().getConexion();
            String query = "SELECT * FROM Conversacion WHERE ID_Vendedor = ? AND ID_Comprador = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, ID_Vendedor);
            statement.setInt(2, ID_Comprador);
            resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            System.err.println("Error al verificar la existencia de la conversación: " + e.getMessage());
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
        }
    }
    
     // Función para agregar un usuario a una conversación
    public void agregarConversacionUsuario(int ID_Conversacion, int ID_Usuario) {
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = Conexion.getInstance().getConexion();
            String query = "INSERT INTO Usuario_Conversacion (ID_Conversacion, ID_Usuario) " +
                           "VALUES (?, ?)";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, ID_Conversacion);
            statement.setInt(2, ID_Usuario);
            statement.executeUpdate();
            System.out.println("Usuario agregado a la conversación correctamente");
        } catch (SQLException e) {
            System.err.println("Error al agregar usuario a la conversación: " + e.getMessage());
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

    // Función para verificar si un usuario está en una conversación
    public boolean verificarConversacionUsuario(int ID_Usuario, int ID_Conversacion) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = Conexion.getInstance().getConexion();
            String query = "SELECT * FROM Usuario_Conversacion WHERE ID_Usuario = ? AND ID_Conversacion = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, ID_Usuario);
            statement.setInt(2, ID_Conversacion);
            resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            System.err.println("Error al verificar la existencia del usuario en la conversación: " + e.getMessage());
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
        }
    }
    
    public int obtenerIDConversacion(int ID_Vendedor, int ID_Comprador) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int ID_Conversacion = -1; 

        try {
            conexion = Conexion.getInstance().getConexion();
            String query = "SELECT ID_Conversacion FROM Conversacion " +
                           "WHERE ID_Vendedor = ? AND ID_Comprador = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, ID_Vendedor);
            statement.setInt(2, ID_Comprador);
            resultSet = statement.executeQuery();

            // Si hay resultados, se obtiene el ID de la conversación
            if (resultSet.next()) {
                ID_Conversacion = resultSet.getInt("ID_Conversacion");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el ID de la conversación: " + e.getMessage());
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

        return ID_Conversacion;
    }
    
    public void crearMensaje(int ID_Usuario_Emisor, String mensaje, Timestamp fechaEnvio, boolean leido, int ID_Conversacion) {
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = Conexion.getInstance().getConexion();
            String query = "INSERT INTO Mensaje (ID_Usuario_Emisor, Mensaje, Fecha_Envio, Leido, ID_Conversacion) " +
                           "VALUES (?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, ID_Usuario_Emisor);
            statement.setString(2, mensaje);
            statement.setTimestamp(3, fechaEnvio);
            statement.setBoolean(4, leido);
            statement.setInt(5, ID_Conversacion);
            statement.executeUpdate();
            System.out.println("Mensaje creado correctamente");
        } catch (SQLException e) {
            System.err.println("Error al crear el mensaje: " + e.getMessage());
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
    
    
    public List<Conversacion> obtenerTodasLasConversaciones() {
        List<Conversacion> conversaciones = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM Conversacion";
            statement = conexion.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int ID_Conversacion = resultSet.getInt("ID_Conversacion");
                int ID_Vendedor = resultSet.getInt("ID_Vendedor");
                int ID_Comprador = resultSet.getInt("ID_Comprador");
                Timestamp fechaUltimoMensaje = resultSet.getTimestamp("Fecha_Ultimo_Mensaje");

                Conversacion conversacion = new Conversacion(ID_Conversacion, ID_Vendedor, ID_Comprador, fechaUltimoMensaje);
                conversaciones.add(conversacion);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todas las conversaciones: " + e.getMessage());
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

        return conversaciones;
    }
    
    public List<Mensaje> obtenerMensajesdeConversacion(int ID_Conversacion) {
    List<Mensaje> mensajes = new ArrayList<>();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        String query = "SELECT * FROM Mensaje WHERE ID_Conversacion = ?";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, ID_Conversacion);
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int ID_Mensaje = resultSet.getInt("ID_Mensaje");
            int ID_Usuario_Emisor = resultSet.getInt("ID_Usuario_Emisor");
            String contenido = resultSet.getString("Mensaje");
            Timestamp fechaEnvio = resultSet.getTimestamp("Fecha_Envio");
            boolean leido = resultSet.getBoolean("Leido");

            Mensaje mensaje = new Mensaje(ID_Mensaje, ID_Usuario_Emisor, contenido, fechaEnvio, leido, ID_Conversacion);
            mensajes.add(mensaje);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener los mensajes: " + e.getMessage());
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

    return mensajes;
}

    
    
    public void marcarComoVisto(int ID_Mensaje) {
    Connection conexion = null;
    PreparedStatement statement = null;

    try {
        conexion = Conexion.getInstance().getConexion();
        String query = "UPDATE Mensaje SET Leido = ? WHERE ID_Mensaje = ?";
        statement = conexion.prepareStatement(query);
        statement.setBoolean(1, true); // Establecer Leido como true
        statement.setInt(2, ID_Mensaje);
        statement.executeUpdate();
        System.out.println("Mensaje marcado como visto correctamente.");
    } catch (SQLException e) {
        System.err.println("Error al marcar el mensaje como visto: " + e.getMessage());
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



    
    
    
    //Conversacion
    
    //Crear Conversación
    
    //Eliminar Conversación
    
    //Obtener Mensajes de una Conversación
    
    
    //Mensaje
    
    //Marcar Mensaje como Leído
    
    //Enviar Mensaje
    
    //Eliminar Mensaje
    
    
    
    //Usuario_Conversacion
    
    //Obtener Conversaciones por Usuario
}
