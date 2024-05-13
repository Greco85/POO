package Vista;

import Modelo.Usuario;
import Modelo.Producto;
import Controladores.ControladorProducto;
import Modelo.SesionActiva;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class Inicio extends javax.swing.JFrame {
    
    private Usuario usuario;
    private ControladorProducto controladorProducto;
    private String busqueda;
    private int categoriaId;

    public Inicio(Usuario usuario) {
    initComponents();
    Menubar menubar = new Menubar();
    menubar.initMenuBar(this, usuario, busqueda, categoriaId);
    this.usuario = usuario;
    controladorProducto = new ControladorProducto();
    
    JLabel bienvenidaLabel = new JLabel("Bienvenido, " + usuario.getCorreo_Electronico());
    bienvenidaLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); 
    bienvenidaLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el texto
    
    // Obtener el ID del usuario desde SesionActiva
    int ID_Usuario = SesionActiva.getID_Usuario();
    System.out.println("Hola ya está en el inicio y el ID del usuario aquí es: " + ID_Usuario);
    
    // Crear un panel para contener el label de bienvenida
    JPanel bienvenidaPanel = new JPanel(new BorderLayout());
    bienvenidaPanel.add(bienvenidaLabel, BorderLayout.CENTER);
    
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(bienvenidaPanel, BorderLayout.NORTH);
    getContentPane().add(mostrarProductosEnLabels(), BorderLayout.CENTER);
    
    setExtendedState(JFrame.MAXIMIZED_BOTH);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 427, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
  private JScrollPane mostrarProductosEnLabels() {
    List<Producto> productos = controladorProducto.obtenerTodosLosProductos();
    int alturaPanel = 200; 
    int espacioPanel = 10;
    int espacioLateral = 20; 
    
    // Creamos el panel que contendrá todos los paneles de productos
    JPanel panelProductos = new JPanel();
    panelProductos.setLayout(new BoxLayout(panelProductos, BoxLayout.Y_AXIS));
    panelProductos.setBorder(BorderFactory.createEmptyBorder(10, espacioLateral, 10, espacioLateral)); // Borde con espaciado

    JPanel panelSuperior = new JPanel();
    panelSuperior.setPreferredSize(new Dimension(1, espacioPanel));
    panelProductos.add(panelSuperior);

    for (Producto producto : productos) {
        JPanel panelProducto = new JPanel();
        panelProducto.setLayout(null);
        panelProducto.setPreferredSize(new Dimension(getContentPane().getWidth() - (2 * espacioLateral), alturaPanel));
        panelProducto.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel idLabel = new JLabel("ID: " + producto.getID_Producto());
        idLabel.setBounds(10, 10, 100, 20); 
        panelProducto.add(idLabel);
        
        JLabel nombreLabel = new JLabel("Nombre: " + producto.getNombre());
        nombreLabel.setBounds(120, 10, 400, 20);
        panelProducto.add(nombreLabel);

        JLabel descripcionLabel = new JLabel("Descripción: " + producto.getDescripcion());
        descripcionLabel.setBounds(10, 40, 400, 20);
        panelProducto.add(descripcionLabel);

        JLabel precioLabel = new JLabel("Precio: $" + producto.getPrecio());
        precioLabel.setBounds(10, 70, 100, 20);
        panelProducto.add(precioLabel);

        JLabel cantidadLabel = new JLabel("Cantidad Disponible: " + producto.getCantidad_Disponible());
        cantidadLabel.setBounds(10, 100, 200, 20);
        panelProducto.add(cantidadLabel);

        JLabel categoriaLabel = new JLabel("Categoría: " + producto.getID_CategoriaProducto());
        categoriaLabel.setBounds(10, 130, 200, 20);
        panelProducto.add(categoriaLabel);

        JLabel fechaCreacionLabel = new JLabel("Fecha de Creación: " + producto.getFecha_Creacion());
        fechaCreacionLabel.setBounds(10, 160, 200, 20);
        panelProducto.add(fechaCreacionLabel);

        panelProducto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Guardar el ID del producto en la sesión activa
                SesionActiva.setID_Producto(producto.getID_Producto());
                System.out.println("El id es: " + SesionActiva.getID_Producto());
                
                // Abrir la vista "VerProducto"
                VerProducto verProducto = new VerProducto(usuario);
                verProducto.setVisible(true);
                dispose();
            }
        });

        panelProductos.add(panelProducto);
    }

    JScrollPane scrollPane = new JScrollPane(panelProductos);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    return scrollPane;
}



    
    public static void main(String[] args) {
        //luego veo
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
