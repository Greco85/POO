package Vista;

import Controladores.ControladorProducto;
import Modelo.Categoria;
import Modelo.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class menubar {

    public static void initMenuBar(JFrame frame, Usuario usuario, String busqueda, int categoriaId) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Fimecommerce");

        JMenuItem amazonMenuItem = new JMenuItem("Ir a Inicio");
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
        botonCarrito.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CarritoPantalla carrito = new CarritoPantalla(usuario);
                carrito.setVisible(true);
                frame.dispose();
            }
        });

        JButton logoutButton = new JButton("Cerrar Sesión");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                GUI_MENU_P inicioFrame = new GUI_MENU_P();
                inicioFrame.setVisible(true);
                frame.dispose();
            }
        });

        menuBar.add(menu);
        // Agregar el panel que contiene el JComboBox al menú
        menuBar.add(navBar);
        menuBar.add(searchBar);
        menuBar.add(searchButton);
        menuBar.add(accountButton);
        menuBar.add(popupMenu);
        menuBar.add(botonCarrito);
        menuBar.add(logoutButton);

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
