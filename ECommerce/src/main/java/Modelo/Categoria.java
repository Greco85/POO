
package Modelo;

public class Categoria {
    private int ID_CategoriaProducto;
    private String nombreCategoria;

    // Constructor
    public Categoria(int ID_CategoriaProducto, String nombreCategoria) {
        this.ID_CategoriaProducto = ID_CategoriaProducto;
        this.nombreCategoria = nombreCategoria;
    }

    // Getters y Setters
    public int getID_CategoriaProducto() {
        return ID_CategoriaProducto;
    }

    public void setID_CategoriaProducto(int ID_CategoriaProducto) {
        this.ID_CategoriaProducto = ID_CategoriaProducto;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
    
    @Override
    public String toString() {
        return nombreCategoria;
    }
}
