package Vista;

import Modelo.Usuario;
import Modelo.Producto;
import Controladores.ControladorProducto;
import Modelo.SesionActiva;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

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
    
    JLabel bienvenidaLabel = new JLabel("Bienvenido, " + usuario.getNombre());
    bienvenidaLabel.setFont(new Font("Arial", Font.BOLD, 18));
    bienvenidaLabel.setForeground(Color.BLACK); 
    bienvenidaLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); 
    bienvenidaLabel.setHorizontalAlignment(SwingConstants.CENTER); 
    bienvenidaLabel.setVerticalAlignment(SwingConstants.CENTER); 


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
    int alturaPanel = 270; 
    int espacioPanel = 10;
    int espacioLateral = 20; 
    
    // Creamos el panel que contendrá todos los paneles de productos
    JPanel panelProductos = new JPanel();
    panelProductos.setLayout(new BoxLayout(panelProductos, BoxLayout.Y_AXIS));
    panelProductos.setBorder(BorderFactory.createEmptyBorder(10, espacioLateral, 10, espacioLateral)); // Borde con espaciado

    JPanel panelSuperior = new JPanel();
    panelSuperior.setPreferredSize(new Dimension(1, espacioPanel));
    panelProductos.add(panelSuperior);
    
    String ImagenRuta = System.getProperty("user.dir") + "\\src\\main\\java\\Imagenes\\bmo.jpg";
    
    double anchoPantalla = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

    // Restar 10 (espacio lateral izquierdo), 10 (espacio lateral derecho), 260 (ancho del panel de imagen), 10 (espacio entre la imagen y el texto)
    double anchoRestado = anchoPantalla - 70 - 270;

    for (Producto producto : productos) {
        JPanel panelProducto = new JPanel();
        
        panelProducto.setLayout(null);
        panelProducto.setPreferredSize(new Dimension(getContentPane().getWidth() - (2 * espacioLateral), alturaPanel));
        panelProducto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        // Crear el panel rojo
        JPanel panelImagen = new JPanel();
        panelImagen.setBackground(Color.WHITE);
        panelImagen.setBounds(10, 10, 250, 250);
        panelProducto.add(panelImagen);
        
        JLabel labelImagen = new JLabel();
        ImageIcon icono = new ImageIcon(ImagenRuta);
        Image imagen = icono.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagen);
        labelImagen.setIcon(iconoEscalado);
        labelImagen.setBounds(0, 0, 250, 250);
        panelImagen.add(labelImagen);

       
        // Crear JPanel para el nombre
        JPanel panelNombre = new JPanel();
        panelNombre.setBackground(Color.BLUE);
        panelNombre.setBounds(270, 10, (int)anchoRestado, 30);
        panelNombre.setLayout(new BorderLayout()); 
        panelProducto.add(panelNombre);

        // Crear JLabel para el nombre
        JLabel nombreLabel = new JLabel(producto.getNombre());
        nombreLabel.setForeground(Color.WHITE);
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 14)); 
        nombreLabel.setHorizontalAlignment(JLabel.CENTER);
        panelNombre.add(nombreLabel, BorderLayout.CENTER); 

        panelNombre.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Establecer un relleno en los bordes del JPanel


        JTextArea descripcionTextArea = new JTextArea("Descripción: " + producto.getDescripcion());
        descripcionTextArea.setBounds(270, 50, (int)anchoRestado, 170); 
        descripcionTextArea.setLineWrap(true); 
        descripcionTextArea.setWrapStyleWord(true); 
        descripcionTextArea.setEditable(false); 
        descripcionTextArea.setBackground(Color.WHITE);
        descripcionTextArea.setForeground(Color.BLACK); 
        descripcionTextArea.setFont(new Font("Arial", Font.PLAIN, 14)); 

        // Agregar un borde al JTextArea
        descripcionTextArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5) 
        ));

        panelProducto.add(descripcionTextArea);

        int cantidadLabels = 3; 
        double espacioDisponible = anchoRestado / cantidadLabels;
        int posicionHorizontal = 270;

        // Crear JLabels para precio, cantidad y categoría
        JLabel precioLabel = new JLabel("Precio: $" + producto.getPrecio());
        JLabel cantidadLabel = new JLabel("Cantidad Disponible: " + producto.getCantidad_Disponible());
        JLabel categoriaLabel = new JLabel("Categoría: " + producto.getID_CategoriaProducto());

        Font font = new Font("Arial", Font.PLAIN, 14);
        Color textColor = Color.BLACK;
        Color backgroundColor = new Color(240, 240, 240);
        Border border = BorderFactory.createLineBorder(Color.GRAY); 

        JLabel[] labels = {precioLabel, cantidadLabel, categoriaLabel};
        for (JLabel label : labels) {
            label.setFont(font);
            label.setForeground(textColor);
            label.setBounds(posicionHorizontal, 230, (int)espacioDisponible, 20);
            label.setHorizontalAlignment(SwingConstants.CENTER);

            label.setBorder(border);
            label.setOpaque(true);
            label.setBackground(backgroundColor);

            panelProducto.add(label);

            posicionHorizontal += espacioDisponible;
        }



        
        
       descripcionTextArea.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            panelProducto.setBackground(Color.LIGHT_GRAY); 
        }

        @Override
        public void mouseExited(MouseEvent e) {
            panelProducto.setBackground(Color.WHITE);
        }
    });
       
        descripcionTextArea.addMouseListener(new MouseAdapter() {
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

    panelProducto.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            panelProducto.setBackground(Color.LIGHT_GRAY); 
        }

        @Override
        public void mouseExited(MouseEvent e) {
            panelProducto.setBackground(Color.WHITE); 
        }

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
        
        panelProducto.setBackground(Color.WHITE);


        panelProductos.add(panelProducto);
    }



    JScrollPane scrollPane = new JScrollPane(panelProductos);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    return scrollPane;
}



    
    public static void main(String[] args) {
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
