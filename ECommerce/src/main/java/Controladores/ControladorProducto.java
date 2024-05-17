
package Controladores; //hace cosas para las entidades Producto, CategoriaProducto, EstadoProducto.

import Modelo.Producto;
import SQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ControladorProducto {
    
    private Connection conexion;
    
    private ControladorNotificacion controladorNotificacion;


    public ControladorProducto() {
        this.conexion = Conexion.getInstance().getConexion();
        this.controladorNotificacion = new ControladorNotificacion();

    }
    
     public String obtenerNombrePorIDUsuario(int ID_Usuario) {
    String nombreUsuario = null;
    
    String query = "SELECT Nombre FROM Usuario WHERE ID_Usuario = ?";
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setInt(1, ID_Usuario);
        
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                nombreUsuario = resultSet.getString("Nombre");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); 
    }
    
    return nombreUsuario;
}
     
     public String ObtenerEstadoPedidoporID(int ID_EstadoPedido) {
    String estadoPedido = null;
    
    String query = "SELECT EstadoPedido FROM EstadoPedido WHERE ID_EstadoPedido = ?";
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setInt(1, ID_EstadoPedido);
        
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                estadoPedido = resultSet.getString("EstadoPedido");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); 
    }
    
    return estadoPedido;
}
     
     public String obtenerMetodoEnvioPorID(int ID_MetodoEnvio) {
    String metodoEnvio = null;
    
    String query = "SELECT MetodoEnvio FROM MetodoEnvio WHERE ID_MetodoEnvio = ?";
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setInt(1, ID_MetodoEnvio);
        
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                metodoEnvio = resultSet.getString("MetodoEnvio");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return metodoEnvio;
}


    
    public List<Producto> obtenerTodosLosProductos() {
    List<Producto> productos = new ArrayList<>();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        String query = "SELECT * FROM Producto";
        statement = conexion.prepareStatement(query);
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int ID_Producto = resultSet.getInt("ID_Producto");
            int ID_Usuario = resultSet.getInt("ID_Usuario");
            String nombre = resultSet.getString("Nombre");
            String descripcion = resultSet.getString("Descripcion");
            double precio = resultSet.getDouble("Precio");
            int cantidad = resultSet.getInt("Cantidad_Disponible");
            int categoria = resultSet.getInt("ID_CategoriaProducto");
            Date fechaCreacion = resultSet.getDate("Fecha_Creacion");
            int estadoProducto = resultSet.getInt("ID_EstadoProducto");
            String imagenURL = resultSet.getString("ImagenURL");

            Producto producto = new Producto(ID_Producto, ID_Usuario, nombre, descripcion, precio, cantidad,
                    categoria, fechaCreacion, estadoProducto, imagenURL);
            productos.add(producto);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener los productos: " + e.getMessage());
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
    return productos;
}
    
    
    
    public int obtenerID_UsuarioPorID_Producto(int ID_Producto) {
    int ID_Usuario = -1; 

    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        String query = "SELECT ID_Usuario FROM Producto WHERE ID_Producto = ?";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, ID_Producto);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            ID_Usuario = resultSet.getInt("ID_Usuario");
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener el ID del usuario por ID_Producto: " + e.getMessage());
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

    return ID_Usuario;
}
    
    
    public String obtenerNombreProductoporID(int ID_Producto) {
    String nombreProducto = null;
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        conexion = Conexion.getInstance().getConexion();
        String query = "SELECT Nombre FROM Producto WHERE ID_Producto = ?";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, ID_Producto);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            nombreProducto = resultSet.getString("Nombre");
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener el nombre del producto por ID: " + e.getMessage());
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

    return nombreProducto;
}
    
    
    public String obtenerCategoriaPorID_Categoria(int ID_CategoriaProducto) {
    String categoria = null;

    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        String query = "SELECT Categoria FROM CategoriaProducto WHERE ID_CategoriaProducto = ?";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, ID_CategoriaProducto);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            categoria = resultSet.getString("Categoria");
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener la categoría por ID_CategoriaProducto: " + e.getMessage());
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

    return categoria;
}


    //Producto 
    
   public List<Producto> obtenerProductosPorNombreYCategoria(String nombre, int categoriaId) {
    List<Producto> productos = new ArrayList<>();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        String query = "SELECT * FROM Producto WHERE Nombre = ? AND ID_CategoriaProducto = ?";
        statement = conexion.prepareStatement(query);
        statement.setString(1, nombre); 
        statement.setInt(2, categoriaId); 
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int ID_Producto = resultSet.getInt("ID_Producto");
            int ID_Usuario = resultSet.getInt("ID_Usuario");
            String nombreProducto = resultSet.getString("Nombre");
            String descripcion = resultSet.getString("Descripcion");
            double precio = resultSet.getDouble("Precio");
            int cantidad = resultSet.getInt("Cantidad_Disponible");
            int categoria = resultSet.getInt("ID_CategoriaProducto");
            Date fechaCreacion = resultSet.getDate("Fecha_Creacion");
            int estadoProducto = resultSet.getInt("ID_EstadoProducto");
            String imagenURL = resultSet.getString("ImagenURL");

            Producto producto = new Producto(ID_Producto, ID_Usuario, nombreProducto, descripcion, precio, cantidad,
                    categoria, fechaCreacion, estadoProducto, imagenURL);
            productos.add(producto);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener los productos por nombre y categoría: " + e.getMessage());
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
    
    
    // Imprimir los productos encontrados
    System.out.println("Productos encontrados:");
    for (Producto producto : productos) {
        System.out.println(producto);
    }
    
    return productos;
}
   
   
   public void ActualizarCantidad(String NombreProducto , int ID_Producto, int Cantidad) {
        PreparedStatement statement = null;

        try {
            String consultaActualizarCantidad = "UPDATE Producto SET Cantidad_Disponible = Cantidad_Disponible - ? WHERE ID_Producto = ?";
            statement = conexion.prepareStatement(consultaActualizarCantidad);
            statement.setInt(1, Cantidad);
            statement.setInt(2, ID_Producto);
            statement.executeUpdate();

            // Verificar si la cantidad disponible es igual a 0
            if (CantidadDisponible(ID_Producto) == 0) {
                String consultaCambiarEstado = "UPDATE Producto SET ID_EstadoProducto = ? WHERE ID_Producto = ?";
                statement = conexion.prepareStatement(consultaCambiarEstado);
                statement.setInt(1, 2); // LUEGO CAMBIAR AUN NO LO TENEMOS "Agotado"
                statement.setInt(2, ID_Producto);
                statement.executeUpdate();
                
                int ID_TipoNoti = 3; //LUEGO BUSCARLA CON UNA CONSULTA "Producto Agotado"

                String mensaje = "Se te agotaron los productos disponibles de tu producto: " + NombreProducto ;

                // Obtener la fecha y hora actual
                Date fechaActual = new Date();

                // Convertir la fecha actual a un objeto Timestamp (necesario para almacenar en la base de datos)
                Timestamp fechaYHoraActual = new Timestamp(fechaActual.getTime());
                int ID_Usuario = buscarID_UsuarioporID_Producto(ID_Producto);
               
                // Llamada a la función crearNotificacion
                controladorNotificacion.crearNotificacion(ID_Usuario, ID_TipoNoti, mensaje, fechaYHoraActual);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
   
   public int buscarID_UsuarioporID_Producto(int ID_Producto) {
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    int ID_Usuario = -1; // Valor predeterminado si no se encuentra ningún usuario

    try {
        conexion = Conexion.getInstance().getConexion();
        String query = "SELECT ID_Usuario FROM Producto WHERE ID_Producto = ?";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, ID_Producto);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            ID_Usuario = resultSet.getInt("ID_Usuario");
        }
    } catch (SQLException e) {
        System.err.println("Error al buscar el ID del usuario por ID del producto: " + e.getMessage());
    } finally {
        // Cerrar recursos
    }

    return ID_Usuario;
}

    private int CantidadDisponible(int ID_Producto) throws SQLException {
        String consultaCantidadDisponible = "SELECT Cantidad_Disponible FROM Producto WHERE ID_Producto = ?";
        try (PreparedStatement statement = conexion.prepareStatement(consultaCantidadDisponible)) {
            statement.setInt(1, ID_Producto);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("Cantidad_Disponible");
                }
            }
        }
        return 0; 
    }
   
   
   public List<Producto> obtenerProductosPorNombre(String nombre) {
    List<Producto> productos = new ArrayList<>();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        String query = "SELECT * FROM Producto WHERE Nombre = ?";
        statement = conexion.prepareStatement(query);
        statement.setString(1, nombre); 
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int ID_Producto = resultSet.getInt("ID_Producto");
            int ID_Usuario = resultSet.getInt("ID_Usuario");
            String nombreProducto = resultSet.getString("Nombre");
            String descripcion = resultSet.getString("Descripcion");
            double precio = resultSet.getDouble("Precio");
            int cantidad = resultSet.getInt("Cantidad_Disponible");
            int categoria = resultSet.getInt("ID_CategoriaProducto");
            Date fechaCreacion = resultSet.getDate("Fecha_Creacion");
            int estadoProducto = resultSet.getInt("ID_EstadoProducto");
            String imagenURL = resultSet.getString("ImagenURL");

            Producto producto = new Producto(ID_Producto, ID_Usuario, nombreProducto, descripcion, precio, cantidad,
                    categoria, fechaCreacion, estadoProducto, imagenURL);
            productos.add(producto);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener los productos por nombre: " + e.getMessage());
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

    return productos;
}

   
   public List<Producto> obtenerProductosPorIdCategoria(int categoriaId) {
    List<Producto> productos = new ArrayList<>();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        String query = "SELECT * FROM Producto WHERE ID_CategoriaProducto = ?";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, categoriaId); 
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int ID_Producto = resultSet.getInt("ID_Producto");
            int ID_Usuario = resultSet.getInt("ID_Usuario");
            String nombreProducto = resultSet.getString("Nombre");
            String descripcion = resultSet.getString("Descripcion");
            double precio = resultSet.getDouble("Precio");
            int cantidad = resultSet.getInt("Cantidad_Disponible");
            int categoria = resultSet.getInt("ID_CategoriaProducto");
            Date fechaCreacion = resultSet.getDate("Fecha_Creacion");
            int estadoProducto = resultSet.getInt("ID_EstadoProducto");
            String imagenURL = resultSet.getString("ImagenURL");

            Producto producto = new Producto(ID_Producto, ID_Usuario, nombreProducto, descripcion, precio, cantidad,
                    categoria, fechaCreacion, estadoProducto, imagenURL);
            productos.add(producto);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener los productos por ID de categoría: " + e.getMessage());
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

    return productos;
}


    
    //Crear Producto 
    public void agregarProducto(int ID_Usuario, String nombre, String descripcion,
                                 String precio, String cantidad, int categoria, String fechaCreacion, int idEstadoproducto , String imagenURL) {
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = Conexion.getInstance().getConexion();
            String query = "INSERT INTO Producto (ID_Usuario, Nombre, Descripcion, Precio, Cantidad_Disponible, " +
                           "ID_CategoriaProducto, Fecha_Creacion, ID_EstadoProducto, ImagenURL) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, ID_Usuario);
            statement.setString(2, nombre);
            statement.setString(3, descripcion);
            statement.setString(4, precio);
            statement.setString(5, cantidad);
            statement.setInt(6, categoria);
            statement.setString(7, fechaCreacion);
            statement.setInt(8, idEstadoproducto); // ID_EstadoProducto fijo en 1 por ahora
            statement.setString(9, imagenURL);
            statement.executeUpdate();
            System.out.println("Producto agregado correctamente");
        } catch (SQLException e) {
            System.err.println("Error al agregar producto: " + e.getMessage());
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
    
    //Actualizar Producto 
    
     public void actualizarProducto(int ID_Producto, String nombre, String descripcion, double precio, int cantidad, int categoria, int  IDestadoproducto,String imagenURL) {
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = Conexion.getInstance().getConexion();
            String query = "UPDATE Producto SET Nombre = ?, Descripcion = ?, Precio = ?, Cantidad_Disponible = ?, ID_CategoriaProducto = ?,ID_EstadoProducto = ?, ImagenURL = ? WHERE ID_Producto = ?";
            statement = conexion.prepareStatement(query);
            statement.setString(1, nombre);
            statement.setString(2, descripcion);
            statement.setDouble(3, precio);
            statement.setInt(4, cantidad);
            statement.setInt(5, categoria);
            statement.setInt(6, IDestadoproducto);
            statement.setString(7, imagenURL);
            statement.setInt(8, ID_Producto);

            int filasActualizadas = statement.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Producto actualizado correctamente.");
            } else {
                System.out.println("No se pudo actualizar el producto.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el producto: " + e.getMessage());
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
     
    //Eliminar Producto 
     
     public void eliminarProducto(int ID_Producto) {
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = Conexion.getInstance().getConexion();
            String query = "DELETE FROM Producto WHERE ID_Producto = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, ID_Producto);

            int filasEliminadas = statement.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("Producto eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el producto.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el producto: " + e.getMessage());
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
    
        //Obtener datos de un producto por su ID
    
    public Producto obtenerProductosporID(int ID_Producto) {
    Producto producto = null;
    String consulta = "SELECT ID_Usuario, Nombre, Descripcion, Precio, Cantidad_Disponible, ID_CategoriaProducto, ID_EstadoProducto, ImagenURL " +
                      "FROM Producto WHERE ID_Producto = ?";

        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setInt(1, ID_Producto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int ID_Usaurio = rs.getInt("ID_Usuario");
                String nombre = rs.getString("Nombre");
                String descripcion = rs.getString("Descripcion");
                double precio = rs.getDouble("Precio");
                int cantidadDisponible = rs.getInt("Cantidad_Disponible");
                int idCategoria = rs.getInt("ID_CategoriaProducto");
                int idEstado = rs.getInt("ID_EstadoProducto");
                String imagenURL = rs.getString("ImagenURL");

                producto = new Producto(ID_Producto, ID_Usaurio, nombre, descripcion, precio, cantidadDisponible, idCategoria, null, idEstado, imagenURL);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el producto por ID: " + e.getMessage());
        }

        return producto;
    }

    //Obtener Productos POR ID_Usuario
    
    public List<Producto> obtenerTodosLosProductos(int idUsuario) {
    List<Producto> productos = new ArrayList<>();
    String consulta = "SELECT * FROM Producto WHERE ID_Usuario = ?";
    
    Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    try {
        conexion = Conexion.getInstance().getConexion();
        ps = conexion.prepareStatement(consulta);
        ps.setInt(1, idUsuario); 
        rs = ps.executeQuery();
        
        while (rs.next()) {
            int idProducto = rs.getInt("ID_Producto");
            String nombre = rs.getString("Nombre");
            String descripcion = rs.getString("Descripcion");
            double precio = rs.getDouble("Precio");
            int cantidadDisponible = rs.getInt("Cantidad_Disponible");
            int idCategoria = rs.getInt("ID_CategoriaProducto");
            Date fechaCreacion = rs.getDate("Fecha_Creacion");
            int idEstado = rs.getInt("ID_EstadoProducto");
            String imagenURL = rs.getString("ImagenURL");
            int idUsuarioProducto = rs.getInt("ID_Usuario");
            
            // Crear el objeto Producto y agregarlo a la lista
            Producto producto = new Producto(idProducto, idUsuario, nombre, descripcion, precio, cantidadDisponible, idCategoria, fechaCreacion, idEstado, imagenURL);
            productos.add(producto);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener los productos: " + e.getMessage());
    } finally {
        // Cerrar los recursos
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conexion != null) {
        }
    }
    
    return productos;
}

    
 public List<Integer> obtenerID_ProductosPorID_Usuario(int ID_Usuario) {
    List<Integer> idsProductos = new ArrayList<>();
    Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    try {
        conexion = Conexion.getInstance().getConexion();
        String consulta = "SELECT ID_Producto FROM Producto WHERE ID_Usuario = ?";
        ps = conexion.prepareStatement(consulta);
        ps.setInt(1, ID_Usuario);
        rs = ps.executeQuery();
        
        while (rs.next()) {
            int idProducto = rs.getInt("ID_Producto");
            idsProductos.add(idProducto);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener los IDs de productos por ID_Usuario: " + e.getMessage());
    } finally {
        // No cerrar la conexión aquí
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    return idsProductos;
}

    
    
   
    //CategoriaProducto
    
    //Crear CategoriaProducto
    //Actualizar CategoriaProducto
    //Eliminar CategoriaProducto
    
    //Obtener CategoriaProducto
        public List<String> obtenerCategorias() {
        List<String> categorias = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = Conexion.getInstance().getConexion();
            String query = "SELECT ID_CategoriaProducto, Categoria FROM CategoriaProducto";
            statement = conexion.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idCategoriaProducto = resultSet.getInt("ID_CategoriaProducto");
                String nombreCategoria = resultSet.getString("Categoria");
                categorias.add(nombreCategoria);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener categorías: " + e.getMessage());
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
        return categorias;
    }
        
        
    //Obtener la categoria por ID
        
    public String obtenerCategoriaPorID(int ID_CategoriaProducto) {
        
    String nombreCategoria = null;
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        conexion = Conexion.getInstance().getConexion();
        String query = "SELECT Categoria FROM CategoriaProducto WHERE ID_CategoriaProducto = ?";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, ID_CategoriaProducto);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            nombreCategoria = resultSet.getString("Categoria");
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener categoría por ID: " + e.getMessage());
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
    return nombreCategoria;
}
    
    
    public int obtenerIDporNombreCategoria(String nombreCategoria) {
    int idCategoria = -1;
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        conexion = Conexion.getInstance().getConexion();
        String query = "SELECT ID_CategoriaProducto FROM CategoriaProducto WHERE Categoria = ?";
        statement = conexion.prepareStatement(query);
        statement.setString(1, nombreCategoria);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            idCategoria = resultSet.getInt("ID_CategoriaProducto");
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener ID por nombre de categoría: " + e.getMessage());
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
    return idCategoria;
}
    
    public int obtenerIDporNombreEstadoProducto(String nombreEstadoProducto) {
    int idEstadoProducto = -1;
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        conexion = Conexion.getInstance().getConexion();
        String query = "SELECT ID_EstadoProducto FROM EstadoProducto WHERE EstadoProducto = ?";
        statement = conexion.prepareStatement(query);
        statement.setString(1, nombreEstadoProducto);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            idEstadoProducto = resultSet.getInt("ID_EstadoProducto");
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener ID por nombre de estado del producto: " + e.getMessage());
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
    return idEstadoProducto;
}

    
    public List<String> obtenerEstadosProducto() {
    List<String> estadosProducto = new ArrayList<>();
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        conexion = Conexion.getInstance().getConexion();
        String query = "SELECT EstadoProducto FROM EstadoProducto";
        statement = conexion.prepareStatement(query);
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String estadoProducto = resultSet.getString("EstadoProducto");
            estadosProducto.add(estadoProducto);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener estados de producto: " + e.getMessage());
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
    return estadosProducto;
}


    public String ObtenerEstadoProductoporID(int ID_EstadoProducto) {
    String estadoProducto = null;
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        conexion = Conexion.getInstance().getConexion();
        String query = "SELECT EstadoProducto FROM EstadoProducto WHERE ID_EstadoProducto = ?";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, ID_EstadoProducto);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            estadoProducto = resultSet.getString("EstadoProducto");
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener estado de producto por ID: " + e.getMessage());
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
    return estadoProducto;
}


    
    //EstadoProducto
    
    //Crear EstadoProducto
    //Actualizar EstadoProducto
    //Eliminar EstadoProducto
    //Obtener EstadoProducto
    
    
    
    public String obtenerNombreUsuarioporID(int idUsuario) {
    String nombreUsuario = null;
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        conexion = Conexion.getInstance().getConexion();
        String query = "SELECT Nombre FROM Usuario WHERE ID_Usuario = ?";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, idUsuario);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            nombreUsuario = resultSet.getString("Nombre");
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener el nombre de usuario por ID: " + e.getMessage());
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
    return nombreUsuario;
}

    
    
    
}

