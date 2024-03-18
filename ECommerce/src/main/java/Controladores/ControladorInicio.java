package controladores;

import Vista.Inicio;

/**
 * Controlador para la ventana de inicio.
 */
public class ControladorInicio {
    
    private Inicio vistaInicio;

    /**
     * Constructor que recibe la vista de inicio.
     * @param vistaInicio La vista de inicio a controlar.
     */
    public ControladorInicio(Inicio vistaInicio) {
        this.vistaInicio = vistaInicio;
    }
   
    /**
     * Muestra el perfil del usuario.
     */
    public void mostrarPerfil() {
        vistaInicio.mostrarPerfil();
    }
    
    /**
     * Cierra la sesión del usuario y muestra la ventana de inicio de sesión.
     */
    public void cerrarSesion() {
        vistaInicio.limpiarVista();
        vistaInicio.mostrarVentanaInicioSesion();
    }
}



