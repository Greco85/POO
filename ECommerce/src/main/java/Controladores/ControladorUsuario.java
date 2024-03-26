package Controladores;

import Modelo.Direccion;
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
   
    public int obtenerIdUsuario(String correo, String contraseña) throws SQLException {
        int ID_Usuario = -1;
        
        String query = "SELECT ID_Usuario FROM Usuario WHERE Correo_Electronico = ? AND Contraseña = ?";
        
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, correo);
            statement.setString(2, contraseña);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ID_Usuario = resultSet.getInt("ID_Usuario");
                }
            }
        }
        
        return ID_Usuario;
    }
    
    
public Usuario obtenerUsuarioPorId(int id) {
    Usuario usuario = null;
    String consulta = "SELECT * FROM Usuario WHERE ID_Usuario = ?";
    try {
        Connection conexion = Conexion.getInstance().getConexion();
        PreparedStatement statement = conexion.prepareStatement(consulta);
        statement.setInt(1, id);
        ResultSet resultado = statement.executeQuery();

        if (resultado.next()) {
            usuario = new Usuario();
            usuario.setID_Usuario(resultado.getInt("ID_Usuario"));
            usuario.setNombre(resultado.getString("Nombre"));
            usuario.setApellido(resultado.getString("Apellido"));
            usuario.setCorreo_Electronico(resultado.getString("Correo_Electronico"));
            usuario.setContraseña(resultado.getString("Contraseña"));
            
            // Obtener la dirección como una cadenaaaa 
            String direccionString = resultado.getString("Direccion");
            
            if (direccionString != null && !direccionString.isEmpty()) {
            

// Luego la dividimos, no tengo ni idea si esto es peor que solo poner los datos separados
// Pero es parte de aprender hacer cosas medio curiosas :p

    String[] direccionParts = direccionString.split(",");
    if (direccionParts.length >= 6) { 
        Direccion direccion = new Direccion();
        direccion.setCalle(direccionParts[0].trim());
        direccion.setNumeroCasa(direccionParts[1].trim());
        direccion.setColonia(direccionParts[2].trim());
        direccion.setCodigoPostal(direccionParts[3].trim());
        direccion.setCiudad(direccionParts[4].trim());
        direccion.setPais(direccionParts[5].trim());
        
       
        usuario.setDireccion(direccion); 
        
        // IMPRIMO para ver si si salen los valores puestos
        System.out.println("Calle: " + direccion.getCalle());
        System.out.println("Número de Casa: " + direccion.getNumeroCasa());
        System.out.println("Colonia: " + direccion.getColonia());
        System.out.println("Código Postal: " + direccion.getCodigoPostal());
        System.out.println("Ciudad: " + direccion.getCiudad());
        System.out.println("País: " + direccion.getPais());
    } else {
        System.err.println("La cadena de dirección no tiene suficientes componentes");
    }
} else {
                //Como acepta valores nulos pongo esto
    System.err.println("La cadena de dirección está vacía");
}
            usuario.setTelefono(resultado.getString("Telefono"));
            usuario.setFecha_Registro(resultado.getDate("Fecha_Registro"));
            usuario.setFecha_Nacimiento(resultado.getDate("Fecha_Nacimiento"));
            usuario.setImagenURL(resultado.getString("ImagenURL"));
            
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener usuario por ID: " + e.getMessage());
    }
    return usuario;
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
public void actualizarUsuario(Usuario usuario) {
    String consulta = "UPDATE Usuario SET Nombre = ?, Apellido = ?, Correo_Electronico = ?, Contraseña = ?, Direccion = ?, Telefono = ?, Fecha_Registro = ?, Fecha_Nacimiento = ?, ImagenURL = ? WHERE ID_Usuario = ?";
    
    try {
        Connection conexion = Conexion.getInstance().getConexion();
        PreparedStatement statement = conexion.prepareStatement(consulta);
        
        // Establecer los valores para los parámetros de la consulta
        statement.setString(1, usuario.getNombre());
        statement.setString(2, usuario.getApellido());
        statement.setString(3, usuario.getCorreo_Electronico());
        statement.setString(4, usuario.getContraseña());
        
        // Concatenar los componentes de la dirección en una sola cadena
        String direccionString = usuario.getDireccion().getCalle() + "," +
                                 usuario.getDireccion().getNumeroCasa() + "," +
                                 usuario.getDireccion().getColonia() + "," +
                                 usuario.getDireccion().getCodigoPostal() + "," +
                                 usuario.getDireccion().getCiudad() + "," +
                                 usuario.getDireccion().getPais();
        
        statement.setString(5, direccionString);
        
        statement.setString(6, usuario.getTelefono());
        statement.setDate(7, new java.sql.Date(usuario.getFecha_Registro().getTime()));
        statement.setDate(8, new java.sql.Date(usuario.getFecha_Nacimiento().getTime()));
        statement.setString(9, usuario.getImagenURL());
        statement.setInt(10, usuario.getID_Usuario());
        
        
        int filasActualizadas = statement.executeUpdate();
        
        if (filasActualizadas > 0) {
            System.out.println("Usuario actualizado correctamente.");
        } else {
            System.err.println("No se pudo actualizar el usuario.");
        }
    } catch (SQLException e) {
        System.err.println("Error al actualizar usuario: " + e.getMessage());
    }
}

    
    //Eliminar Usuario

//Este solo es eliminar facil, mañana lo hago
    
    //Cerrar Sesión de Usuario 

//Mañana tambien
    
    
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
                conn.close(); 
                System.out.println("Conexión cerrada correctamente");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
    
    return credencialesCorrectas;
}




    
}






