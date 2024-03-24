
package Modelo;

public class DetalleCompra {
    private int ID_DetalleCompra;
    private int ID_Compra;
    private int ID_Producto;
    private int Cantidad;
    private double Total;

    // Constructor
    public DetalleCompra(int ID_DetalleCompra, int ID_Compra, int ID_Producto, int Cantidad, double Total) {
        this.ID_DetalleCompra = ID_DetalleCompra;
        this.ID_Compra = ID_Compra;
        this.ID_Producto = ID_Producto;
        this.Cantidad = Cantidad;
        this.Total = Total;
    }

    // Getters y Setters
    public int getID_DetalleCompra() {
        return ID_DetalleCompra;
    }

    public void setID_DetalleCompra(int ID_DetalleCompra) {
        this.ID_DetalleCompra = ID_DetalleCompra;
    }

    public int getID_Compra() {
        return ID_Compra;
    }

    public void setID_Compra(int ID_Compra) {
        this.ID_Compra = ID_Compra;
    }

    public int getID_Producto() {
        return ID_Producto;
    }

    public void setID_Producto(int ID_Producto) {
        this.ID_Producto = ID_Producto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }
}

