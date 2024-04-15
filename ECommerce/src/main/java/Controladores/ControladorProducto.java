
package Controladores; //hace cosas para las entidades Producto, CategoriaProducto, EstadoProducto.

import Modelo.Producto;
import SQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ControladorProducto {
    
    private Connection conexion;

    public ControladorProducto() {
        this.conexion = Conexion.getInstance().getConexion();
        productos = new Producto[]{
            new Producto(1,1 , "Prueba 1", "imagen1.jpg", 10.99, 10, 1, new Date(), 1, "imagen1.jpg"),
            new Producto(2,1 , "Pan", "imagen2.jpg", 20.49, 20, 2, new Date(), 1, "imagen2.jpg"),
            new Producto(3,1 , "Gato", "imagen3.jpg", 15.79, 15, 3, new Date(), 1, "imagen3.jpg"),
            new Producto(4,1 , "POR FINNN PAN", "imagen3.jpg", 14.2, 12, 2, new Date(), 1, "imagen3.jpg")
        };
    }
    
    private Producto[] productos; // Arreglo de productos

    public Producto[] getProductos() {
        return productos;
    }
    
    //Producto
    
    //Crear Producto 
    public void agregarProducto(int ID_Usuario, String nombre, String descripcion,
                                 String precio, String cantidad, int categoria, String fechaCreacion, String imagenURL) {
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
            statement.setInt(8, 1); // ID_EstadoProducto fijo en 1 por ahora
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
    
     public void actualizarProducto(int ID_Producto, String nombre, String descripcion, double precio, int cantidad, int categoria, String imagenURL) {
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = Conexion.getInstance().getConexion();
            String query = "UPDATE Producto SET Nombre = ?, Descripcion = ?, Precio = ?, Cantidad_Disponible = ?, ID_CategoriaProducto = ?, ImagenURL = ? WHERE ID_Producto = ?";
            statement = conexion.prepareStatement(query);
            statement.setString(1, nombre);
            statement.setString(2, descripcion);
            statement.setDouble(3, precio);
            statement.setInt(4, cantidad);
            statement.setInt(5, categoria);
            statement.setString(6, imagenURL);
            statement.setInt(7, ID_Producto);

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
    String consulta = "SELECT Nombre, Descripcion, Precio, Cantidad_Disponible, ID_CategoriaProducto, ID_EstadoProducto, ImagenURL " +
                      "FROM Producto WHERE ID_Producto = ?";

        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setInt(1, ID_Producto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("Nombre");
                String descripcion = rs.getString("Descripcion");
                double precio = rs.getDouble("Precio");
                int cantidadDisponible = rs.getInt("Cantidad_Disponible");
                int idCategoria = rs.getInt("ID_CategoriaProducto");
                int idEstado = rs.getInt("ID_EstadoProducto");
                String imagenURL = rs.getString("ImagenURL");

                producto = new Producto(ID_Producto, 0, nombre, descripcion, precio, cantidadDisponible, idCategoria, null, idEstado, imagenURL);
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

    try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
        ps.setInt(1, idUsuario); 
        ResultSet rs = ps.executeQuery();
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
            int idUsuarioProducto = rs.getInt("ID_Usuario"); // Obtener ID_Usuario

            Producto producto = new Producto(idProducto,idUsuario, nombre, descripcion, precio, cantidadDisponible, idCategoria, fechaCreacion, idEstado, imagenURL);
            productos.add(producto);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener los productos: " + e.getMessage());
    }

    return productos;
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

    
    //EstadoProducto
    
    //Crear EstadoProducto
    //Actualizar EstadoProducto
    //Eliminar EstadoProducto
    //Obtener EstadoProducto
    
    
    
    
    
    
    
}

