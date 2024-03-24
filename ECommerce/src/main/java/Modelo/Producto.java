package Modelo;

import java.util.Date;

public class Producto {
    private int ID_Producto;
    private String Nombre;
    private String Descripcion;
    private double Precio;
    private int Cantidad_Disponible;
    private int ID_CategoriaProducto;
    private Date Fecha_Creacion;
    private int ID_EstadoProducto;
    private String ImagenURL;

    // Constructor
    public Producto(int ID_Producto, String Nombre, String Descripcion, double Precio, int Cantidad_Disponible, int ID_CategoriaProducto, Date Fecha_Creacion, int ID_EstadoProducto, String ImagenURL) {
        this.ID_Producto = ID_Producto;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Precio = Precio;
        this.Cantidad_Disponible = Cantidad_Disponible;
        this.ID_CategoriaProducto = ID_CategoriaProducto;
        this.Fecha_Creacion = Fecha_Creacion;
        this.ID_EstadoProducto = ID_EstadoProducto;
        this.ImagenURL = ImagenURL;
    }

    // Getters y Setters
    public int getID_Producto() {
        return ID_Producto;
    }

    public void setID_Producto(int ID_Producto) {
        this.ID_Producto = ID_Producto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public int getCantidad_Disponible() {
        return Cantidad_Disponible;
    }

    public void setCantidad_Disponible(int Cantidad_Disponible) {
        this.Cantidad_Disponible = Cantidad_Disponible;
    }

    public int getID_CategoriaProducto() {
        return ID_CategoriaProducto;
    }

    public void setID_CategoriaProducto(int ID_CategoriaProducto) {
        this.ID_CategoriaProducto = ID_CategoriaProducto;
    }

    public Date getFecha_Creacion() {
        return Fecha_Creacion;
    }

    public void setFecha_Creacion(Date Fecha_Creacion) {
        this.Fecha_Creacion = Fecha_Creacion;
    }

    public int getID_EstadoProducto() {
        return ID_EstadoProducto;
    }

    public void setID_EstadoProducto(int ID_EstadoProducto) {
        this.ID_EstadoProducto = ID_EstadoProducto;
    }

    public String getImagenURL() {
        return ImagenURL;
    }

    public void setImagenURL(String ImagenURL) {
        this.ImagenURL = ImagenURL;
    }
}




