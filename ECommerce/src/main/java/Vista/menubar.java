package Vista;

import Controladores.ControladorNotificacion;
import Controladores.ControladorProducto;
import Modelo.SesionActiva;
import Modelo.Usuario;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Menubar {
    
    
    private ControladorNotificacion controladorNotificacion;


    public void initMenuBar(JFrame frame, Usuario usuario, String busqueda, int categoriaId) {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(0, 128, 0)); // Color verde oscuro

        JMenu menu = new JMenu("Fimecommerce");
        menu.setForeground(Color.WHITE); // Color de fuente blanco
        menu.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente en negrita Arial, tamaño 14

        controladorNotificacion = new ControladorNotificacion();

        JMenuItem amazonMenuItem = new JMenuItem("Ir a Inicio");
        amazonMenuItem.setForeground(Color.WHITE); 
        amazonMenuItem.setFont(new Font("Arial", Font.BOLD, 14)); 

        amazonMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Inicio inicioFrame = new Inicio(usuario);
                inicioFrame.setVisible(true);
                frame.dispose();
            }
        });

        menu.add(amazonMenuItem);

        JComboBox<String> navBar = new JComboBox<>();
        navBar.addItem("Todos");
        ControladorProducto controladorCategoria = new ControladorProducto();
        List<String> categorias = controladorCategoria.obtenerCategorias();

        for (String categoria : categorias) {
            navBar.addItem(categoria);
        }

        JTextField searchBar = new JTextField();
        searchBar.setToolTipText("Buscar productos");
        searchBar.setText(busqueda);

        JButton searchButton = new JButton("Buscar");
        searchButton.setBackground(new Color(41, 81, 204)); // Azul oscuro
        searchButton.setForeground(Color.WHITE); // Texto blanco
        searchButton.setFocusPainted(false); // Evita que se pinte el borde cuando se selecciona
        searchButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Añade un borde vacío para espacio
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String busqueda = searchBar.getText();
                System.out.println("Búsqueda realizada: " + busqueda);

                ControladorProducto controladorProducto = new ControladorProducto();
                String categoriaSeleccionada = (String) navBar.getSelectedItem();

                if (categoriaSeleccionada != null) {
                    try {

                        int categoriaId = controladorProducto.obtenerIDporNombreCategoria(categoriaSeleccionada);
                        System.out.println("Búsqueda realizada: " + categoriaId);
                        Busqueda busquedaVentana = new Busqueda(busqueda, categoriaId, usuario);
                        frame.dispose();
                        busquedaVentana.setVisible(true);
                    } catch (NumberFormatException ex) {
                        System.out.println("Error: El ID de la categoría no es un número válido.");
                    }
                } else {
                    System.out.println("Error: No se ha seleccionado ninguna categoría.");
                }
            }
        });

        JButton accountButton = new JButton("Cuenta");
         accountButton.setBackground(new Color(41, 81, 204)); // Azul oscuro
        accountButton.setForeground(Color.WHITE); // Texto blanco
        accountButton.setFocusPainted(false); // Evita que se pinte el borde cuando se selecciona
        accountButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); 
        accountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CuentaUsuario cuentaUsuario = new CuentaUsuario(usuario);
                cuentaUsuario.setVisible(true);
                frame.dispose();
            }
        });

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem popupItem = new JMenuItem("Categoría");
        popupMenu.add(popupItem);

        JButton botonCarrito = new JButton("Carrito");
        botonCarrito.setBackground(new Color(41, 81, 204)); // Azul oscuro
        botonCarrito.setForeground(Color.WHITE); // Texto blanco
        botonCarrito.setFocusPainted(false); // Evita que se pinte el borde cuando se selecciona
        botonCarrito.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); 
        
        botonCarrito.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CarritoPantalla carrito = new CarritoPantalla(usuario);
                carrito.setVisible(true);
                frame.dispose();
            }
        });

        JButton logoutButton = new JButton("Cerrar Sesión");
        logoutButton.setBackground( Color.RED); 
        logoutButton.setForeground(Color.WHITE); 
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                GUI_MENU_P inicioFrame = new GUI_MENU_P();
                inicioFrame.setVisible(true);
                frame.dispose();
            }
        });
        
        //VERIFICAR SI HAY UNA NOTIFICACION SIN LEER
        
        int ID_Usuario = SesionActiva.getID_Usuario(); 

        boolean hayNotificacionesPendientes = controladorNotificacion.verificarNotificacionPendiente(ID_Usuario);

        ImageIcon scaledIcon = null;

        if (hayNotificacionesPendientes) {
            System.out.println("Hay notificaciones pendientes para el usuario con ID " + ID_Usuario);
            ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "\\img\\NotificacionActiva.png");
            Image image = icon.getImage();
            Image newImage = image.getScaledInstance(20, 17, Image.SCALE_SMOOTH);
            scaledIcon = new ImageIcon(newImage);

        } else {
            System.out.println("No hay notificaciones pendientes para el usuario con ID " + ID_Usuario);
            ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "\\img\\Campana.png");
            Image image = icon.getImage();
            Image newImage = image.getScaledInstance(20, 17, Image.SCALE_SMOOTH);
            scaledIcon = new ImageIcon(newImage);
        }

        JButton notificationButton = new JButton(scaledIcon);
        notificationButton.setToolTipText("Notificaciones");

        notificationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                controladorNotificacion.marcarComoLeidasPorUsuario(ID_Usuario);
                NotificacionesVista notificacionesVista = new NotificacionesVista(usuario);
                notificacionesVista.setVisible(true);
                frame.dispose();

            }
        });

        menuBar.add(menu);
        menuBar.add(navBar);
        menuBar.add(searchBar);
        menuBar.add(searchButton);
        menuBar.add(accountButton);
        menuBar.add(popupMenu);
        menuBar.add(botonCarrito);
        menuBar.add(logoutButton);
        menuBar.add(notificationButton); // Agregar el botón de notificación al menú

        navBar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String categoriaSeleccionada = (String) navBar.getSelectedItem();
                // Aquí puedes realizar cualquier acción que necesites con la categoría seleccionada
                System.out.println("Categoría seleccionada: " + categoriaSeleccionada);
            }
        });
        frame.setJMenuBar(menuBar);
    }
}
