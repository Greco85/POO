package Controladores;

import Modelo.Producto;

public class ControladorProducto {
    
    private Producto[] productos; // Arreglo de productos

    public ControladorProducto() {
        // Inicializar el arreglo de productos con algunos productos de ejemplo
        productos = new Producto[]{
            new Producto("Prueba 1", "imagen1.jpg", 10.99),
            new Producto("Pan", "imagen2.jpg", 20.49),
            new Producto("Gato", "imagen3.jpg", 15.79),
            new Producto("POR FINNN PAN", "imagen3.jpg", 14.2)
            
        };
    }

    public Producto[] getProductos() {
        return productos;
    }


}

