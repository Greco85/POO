package Vista;

import Modelo.Categoria;
import Modelo.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menubar { //TAMBIEN ANDO HACIENDO TODO SIN PENSARLO TANTO (MUY MAL) ASI Q PUEDE QUE LO MODIFIQUE DESPUES

    public static void initMenuBar(JFrame frame, Usuario usuario, String busqueda, int categoriaId) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Amazon");

        JMenuItem amazonMenuItem = new JMenuItem("Ir a Inicio");
        amazonMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Inicio inicioFrame = new Inicio(usuario);
                inicioFrame.setVisible(true);
                frame.dispose();
            }
        });

        menu.add(amazonMenuItem);

        JComboBox<Categoria> navBar = new JComboBox<>();
        navBar.addItem(new Categoria("Hogar", 1));
        navBar.addItem(new Categoria("Limpieza", 2));
        navBar.addItem(new Categoria("Videojuegos", 3)); //SOLO PARA PROBAR QUE FUNCIONE

        JTextField searchBar = new JTextField();
        searchBar.setToolTipText("Buscar productos");
        searchBar.setText(busqueda);

        JButton searchButton = new JButton("Buscar");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String busqueda = searchBar.getText();
                System.out.println("Búsqueda realizada: " + busqueda);
                Categoria categoriaSeleccionada = (Categoria) navBar.getSelectedItem();
                try {
                    int categoriaId = categoriaSeleccionada.getId();
                    Busqueda busquedaVentana = new Busqueda(busqueda, categoriaId, usuario);
                    frame.dispose();
                    busquedaVentana.setVisible(true);
                } catch (NumberFormatException ex) {
                    System.out.println("Error: El ID de la categoría no es un número válido.");
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
                Carrito carrito = new Carrito(usuario);
                carrito.setVisible(true);
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

        navBar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Categoria categoriaSeleccionada = (Categoria) navBar.getSelectedItem();
                // Aquí deberías manejar la categoría seleccionada
                // Puedes acceder a categoriaSeleccionada.getId() para obtener el ID de la categoría seleccionada
            }
        });

        frame.setJMenuBar(menuBar);
    }
}
