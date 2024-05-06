
package Modelo;

public class SesionActiva { //Aqui podemos obtener el ID del usuario activo para facilitarnos cosas
    
    private static int ID_Usuario;
    private static int ID_Producto;
    private static double totalAPagar;

    public static void setID_Usuario(int ID_Usuario) {
        SesionActiva.ID_Usuario = ID_Usuario;
    }

    public static int getID_Usuario() {
        return ID_Usuario;
    }
    
     public static void setID_Producto(int ID_Producto) {
        SesionActiva.ID_Producto = ID_Producto;
    }

    public static int getID_Producto() {
        return ID_Producto;
    }


    public static void setTotalAPagar(double totalAPagar) {
            SesionActiva.totalAPagar = totalAPagar;
        }

        public static double getTotalAPagar() {
            return totalAPagar;
        }
 }

