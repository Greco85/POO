
package Controladores; //hace cosas para las entidades Producto, CategoriaProducto, EstadoProducto.

import Modelo.Producto;
import java.util.Date;


public class ControladorProducto {
    
    private Producto[] productos; // Arreglo de productos

    public ControladorProducto() {
        // Inicializar el arreglo de productos con algunos productos de ejemplo
        productos = new Producto[]{
            new Producto(1, "Prueba 1", "imagen1.jpg", 10.99, 10, 1, new Date(), 1, "imagen1.jpg"),
            new Producto(2, "Pan", "imagen2.jpg", 20.49, 20, 2, new Date(), 1, "imagen2.jpg"),
            new Producto(3, "Gato", "imagen3.jpg", 15.79, 15, 3, new Date(), 1, "imagen3.jpg"),
            new Producto(4, "POR FINNN PAN", "imagen3.jpg", 14.2, 12, 2, new Date(), 1, "imagen3.jpg")
        };
    }

    public Producto[] getProductos() {
        return productos;
    }
    
    //Producto
    
    //Crear Producto 
    //Actualizar Producto 
    //Eliminar Producto 
    //Obtener Productos
    
    //CategoriaProducto
    
    //Crear CategoriaProducto
    //Actualizar CategoriaProducto
    //Eliminar CategoriaProducto
    //Obtener CategoriaProducto
    
    //EstadoProducto
    
    //Crear EstadoProducto
    //Actualizar EstadoProducto
    //Eliminar EstadoProducto
    //Obtener EstadoProducto
    
    
    
    
    
    
    
}

