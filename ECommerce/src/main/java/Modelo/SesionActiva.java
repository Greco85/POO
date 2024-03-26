
package Modelo;

public class SesionActiva { //Aqui podemos obtener el ID del usuario activo para facilitarnos cosas
    
    private static int ID_Usuario;

    public static void setID_Usuario(int ID_Usuario) {
        SesionActiva.ID_Usuario = ID_Usuario;
    }

    public static int getID_Usuario() {
        return ID_Usuario;
    }
}

