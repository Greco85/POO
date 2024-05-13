package Vista;

import Controladores.ControladorCarrito;
import Controladores.ControladorProducto;
import Modelo.Carrito;
import Modelo.Producto;
import Modelo.SesionActiva;
import Modelo.Usuario; //EL CARRITO DESPUES DE HACER FUNCIONAR LO DE LOS PRODUCTOS
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class CarritoPantalla extends javax.swing.JFrame {
    private Usuario usuario;
    private String busqueda;
    private int categoriaId;
    private double totalCarrito = 0;
    private Connection conexion;

    
    private ControladorCarrito controladorCarrito;
    private ControladorProducto controladorProducto;
    
public CarritoPantalla(Usuario usuario) {
    this.usuario = usuario;
        initmyComponents();
        Menubar menubar = new Menubar();
        menubar.initMenuBar(this, usuario, busqueda, categoriaId);  
        setExtendedState(JFrame.MAXIMIZED_BOTH);
}


       
    private JPanel crearPanelProductos() {
    int alturaPanel = 270; 
    int espacioLateral = 20; 
        
    JPanel panelProductos = new JPanel();
    panelProductos.setLayout(new BoxLayout(panelProductos, BoxLayout.Y_AXIS));
    panelProductos.setBorder(BorderFactory.createEmptyBorder(10, espacioLateral, 10, espacioLateral)); // Borde con espaciado

    List<Carrito> productosEnCarrito = controladorCarrito.obtenerTodoElCarrito(SesionActiva.getID_Usuario());
    
    
    String ImagenRuta = System.getProperty("user.dir") + "\\src\\main\\java\\Imagenes\\bmo.jpg";
    
    double anchoPantalla = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

    // Restar 10 (espacio lateral izquierdo), 10 (espacio lateral derecho), 260 (ancho del panel de imagen), 10 (espacio entre la imagen y el texto)
    double anchoRestado = anchoPantalla - 70 - 270;
    
    for (Carrito carrito : productosEnCarrito) {
        
        JPanel panelProducto = new JPanel();
        panelProducto.setLayout(null);
        panelProducto.setPreferredSize(new Dimension(getContentPane().getWidth() - (2 * espacioLateral), alturaPanel));
        panelProducto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        totalCarrito = totalCarrito + carrito.getTotal();
        
        int ID_Producto = carrito.getID_Producto();
        Producto producto = controladorProducto.obtenerProductosporID(ID_Producto);

        
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

        panelNombre.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); 
        
        
        JTextArea descripcionTextArea = new JTextArea("Descripción: " + producto.getDescripcion());
        descripcionTextArea.setBounds(270, 50, (int)anchoRestado, 140); 
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
        JLabel precioLabel = new JLabel("Total: $" + carrito.getTotal());
        JLabel cantidadLabel = new JLabel("Cantidad En El Carrito: " + carrito.getCantidad());
        JLabel categoriaLabel = new JLabel("Categoría: " + producto.getID_CategoriaProducto());

        Font font = new Font("Arial", Font.PLAIN, 14);
        Color textColor = Color.BLACK;
        Color backgroundColor = new Color(240, 240, 240);
        Border border = BorderFactory.createLineBorder(Color.GRAY); 

        JLabel[] labels = {precioLabel, cantidadLabel, categoriaLabel};
        for (JLabel label : labels) {
            label.setFont(font);
            label.setForeground(textColor);
            label.setBounds(posicionHorizontal, 200, (int)espacioDisponible, 20);
            label.setHorizontalAlignment(SwingConstants.CENTER);

            label.setBorder(border);
            label.setOpaque(true);
            label.setBackground(backgroundColor);

            panelProducto.add(label);

            posicionHorizontal += espacioDisponible;
        }

        
        
        // Botón Eliminar
        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setBounds(270, 230, (int)anchoRestado, 30);
        eliminarButton.setBackground(Color.RED);
        eliminarButton.setForeground(Color.BLACK);
        eliminarButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)), // Borde blanco
            BorderFactory.createEmptyBorder(10, 20, 10, 20) // Espacio interno
        ));
        
        eliminarButton.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            eliminarButton.setBackground(new Color(128, 0, 0)); // Rojo oscuro
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            eliminarButton.setBackground(new Color(255, 0, 0)); // Rojo normal
        }
    });
        
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar este producto del carrito?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    int ID_Producto = producto.getID_Producto();
                    int ID_Usuario = SesionActiva.getID_Usuario();

                    ControladorCarrito controladorCarrito = new ControladorCarrito();

                    boolean eliminado = controladorCarrito.eliminarDelCarrito(ID_Usuario, ID_Producto);

                    if (eliminado) {
                        System.out.println("El producto fue eliminado del carrito correctamente.");
                    } else {
                        System.out.println("Error al eliminar el producto del carrito.");
                    }
                }
            }
        });

        panelProducto.add(eliminarButton);



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
    
    
     



        


    return panelProductos;
}
    
    
    
    
        private void initmyComponents() {
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            JLabel bienvenidaLabel = new JLabel("Carrito de Compras");
            bienvenidaLabel.setFont(new Font("Arial", Font.BOLD, 18)); 
            bienvenidaLabel.setForeground(Color.WHITE); 
            bienvenidaLabel.setBorder(BorderFactory.createEmptyBorder(2, 20, 2, 20)); 
            bienvenidaLabel.setHorizontalAlignment(SwingConstants.CENTER); 

            JPanel bienvenidaPanel = new JPanel(new BorderLayout());
            bienvenidaPanel.setBackground(Color.DARK_GRAY); 
            bienvenidaPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
            bienvenidaPanel.add(bienvenidaLabel, BorderLayout.CENTER); 

            controladorCarrito = new ControladorCarrito();
            controladorProducto = new ControladorProducto();
            JPanel panelProductos = crearPanelProductos();

            JScrollPane scrollPane = new JScrollPane(panelProductos);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         
            // Crear el panel de compra
            JPanel panelCompra = new JPanel();
            panelCompra.setBackground(Color.WHITE);
            panelCompra.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel comprarLabel = new JLabel("El total de este carrito es: $" + totalCarrito);
            SesionActiva.setTotalAPagar(totalCarrito); 

            comprarLabel.setFont(new Font("Arial", Font.BOLD, 16)); 
            comprarLabel.setForeground(Color.BLACK); 
            comprarLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20)); 
            comprarLabel.setHorizontalAlignment(SwingConstants.CENTER); 

            JButton comprarButton = new JButton("Comprar");
            comprarButton.setFont(new Font("Arial", Font.PLAIN, 14));
            comprarButton.setForeground(Color.BLACK); 
            comprarButton.setBackground(Color.GREEN); 
            comprarButton.setFocusPainted(false); 
            comprarButton.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20)); 
            
            comprarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    
                    
                    PantallaDeCompra pantallaDeCompra = new PantallaDeCompra(usuario, conexion);
                    pantallaDeCompra.setVisible(true);
                }
            });
            
            comprarButton.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
        comprarButton.setBackground(new Color(0, 128, 0)); // Verde oscuro
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            comprarButton.setBackground(Color.GREEN); 
        }
    });

            // Agregar el JLabel y el JButton al panel de compra
            panelCompra.setLayout(new GridLayout(2, 1, 0, 0));
            panelCompra.add(comprarLabel);
            panelCompra.add(comprarButton);


            JPanel contenidoPanel = new JPanel(new BorderLayout());
            contenidoPanel.add(scrollPane, BorderLayout.CENTER);
            contenidoPanel.add(panelCompra, BorderLayout.SOUTH);

            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(bienvenidaPanel, BorderLayout.NORTH);
            getContentPane().add(contenidoPanel, BorderLayout.CENTER);

            pack();
        }


    @SuppressWarnings("unchecked")
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
