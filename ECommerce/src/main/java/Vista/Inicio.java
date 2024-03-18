package Vista;

import Modelo.Usuario;
import Modelo.Categoria;
import Modelo.Producto;
import Controladores.ControladorProducto;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Inicio extends javax.swing.JFrame {
    
    private Usuario usuario;
    private ControladorProducto controladorProducto;
    private String categoriaSeleccionada = "1"; //INICIAMOS CON EL ID 1 PERO QUIZA LO CAMBIE LUEGO

    public Inicio(Usuario usuario) {
        initComponents();
        initNavbar();
        this.usuario = usuario;
        jLabel1.setText("Bienvenido, " + usuario.getCorreo());
        controladorProducto = new ControladorProducto();
        mostrarProductosEnLabels();
    }
        
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        popupMenu1 = new java.awt.PopupMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        popupMenu2 = new java.awt.PopupMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenu5 = new javax.swing.JMenu();
        jLabel1 = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        jMenu1.setText("jMenu1");

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        popupMenu1.setLabel("popupMenu1");

        jMenu2.setText("jMenu2");

        jMenuItem2.setText("jMenuItem2");

        popupMenu2.setLabel("popupMenu2");

        jMenu5.setText("jMenu5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("jLabelBienvenida");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(jLabel1)
                .addContainerGap(180, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel1)
                .addContainerGap(342, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
 
    
 private void initNavbar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Amazon");

        // Barra de navegación
        JComboBox<Categoria> navBar = new JComboBox<>();
        navBar.addItem(new Categoria("Hogar", 1));
        navBar.addItem(new Categoria("Limpieza", 2));
        navBar.addItem(new Categoria("Videojuegos", 3)); //SOLO PARA PROBAR QUE FUNCUIONE

        // Barra de búsqueda
        JTextField searchBar = new JTextField();
        searchBar.setToolTipText("Buscar productos");

        // Botón de búsqueda
        JButton searchButton = new JButton("Buscar");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String busqueda = searchBar.getText();
                System.out.println("Búsqueda realizada: " + busqueda);
                System.out.println("Categoría seleccionada: " + categoriaSeleccionada);

              
                Busqueda busquedaVentana = new Busqueda(busqueda, categoriaSeleccionada);
                dispose();
                busquedaVentana.setVisible(true);
            }
        });

        // Botón de ir a la cuenta del usuario
        JButton accountButton = new JButton("Cuenta");
        accountButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            
        
        CuentaUsuario cuentaUsuario = new CuentaUsuario();
        cuentaUsuario.setVisible(true);
        dispose();
    }
});

        // Pop-up con una categoría
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem popupItem = new JMenuItem("Categoría");
        popupMenu.add(popupItem);

        // Botón del carrito
        JButton botonCarrito = new JButton("Carrito");
        botonCarrito.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        
        Carrito carrito = new Carrito();
        carrito.setVisible(true);
        dispose();
    }
    });

        // Agregar elementos a la barra de menú
        menuBar.add(menu);
        menuBar.add(navBar);
        menuBar.add(searchBar);
        menuBar.add(searchButton);
        menuBar.add(accountButton);
        menuBar.add(popupMenu);
        menuBar.add(botonCarrito);

        // ActionListener para la selección de la categoría
        navBar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Categoria categoriaSeleccionada = (Categoria) navBar.getSelectedItem();
                Inicio.this.categoriaSeleccionada = String.valueOf(categoriaSeleccionada.getId());
                System.out.println("Categoría seleccionada: " + Inicio.this.categoriaSeleccionada);
            }
        });

        // Establecer la barra de menú como la barra de menú de la ventana
        setJMenuBar(menuBar);
    }

private void mostrarProductosEnLabels() {
        Producto[] productos = controladorProducto.getProductos();
        int yPosition = 130; // Posición vertical inicial para el primer producto

        for (int i = 0; i < productos.length; i++) {
            Producto producto = productos[i];
            JLabel productoLabel = new JLabel();
            productoLabel.setText(producto.getNombre() + " - Precio: $" + producto.getPrecio());
            productoLabel.setBounds(50, yPosition, 300, 20); // Establece posicion y tamaño del JLabel
            getContentPane().add(productoLabel); // Agrega el JLabel al contenedor principal
            yPosition += 30; // Incrementa la posición vertical para el próximo producto
        }
    }

    public void mostrarPerfil() {
        //luego veo
    }
    public void limpiarVista() {
       //luego veo
    }
    
    public void mostrarVentanaInicioSesion() {
        //luego veo
    }
    
    public static void main(String[] args) {
        //luego veo
    }



        
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.MenuBar menuBar1;
    private java.awt.PopupMenu popupMenu1;
    private java.awt.PopupMenu popupMenu2;
    // End of variables declaration//GEN-END:variables
}
