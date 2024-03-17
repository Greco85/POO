package Controladores;

import Vista.Inicio;

public class ControladorInicio {
    
    private Inicio vistaInicio; // Referencia a la vista de inicio

    public ControladorInicio(Inicio vistaInicio) {
        this.vistaInicio = vistaInicio;
    }
    
    // Método para mostrar el perfil del usuario
    public void mostrarPerfil() {
        vistaInicio.mostrarPerfil(); // Llama al método de la vista para mostrar el perfil
    }
    
    // Método para mostrar los productos disponibles
    public void mostrarProductos() {
        vistaInicio.mostrarProductos(); // Llama al método de la vista para mostrar los productos
    }
    
    // Método para cerrar la sesión del usuario
    public void cerrarSesion() {
        // Cerrar sesión implica limpiar la vista y posiblemente redirigir al inicio de sesión
        vistaInicio.limpiarVista(); // Llama al método de la vista para limpiarla
                vistaInicio.mostrarVentanaInicioSesion();

    }
}


