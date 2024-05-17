
package Vista;

import javax.swing.JLabel;
import Controladores.ControladorProducto;
import Modelo.Producto;
import Modelo.SesionActiva;
import Modelo.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;


public class Busqueda extends javax.swing.JFrame {
    
    private JLabel busquedaLabel;
    private JLabel categoriaLabel;
    private String categoriaSeleccionada = "0"; //INICIAMOS CON EL ID 1 PERO QUIZA LO CAMBIE LUEGO
    private ControladorProducto controladorProducto;
    private Usuario usuario;
    private String busqueda;
    private int categoriaId;
    

   public Busqueda(String busqueda, int categoriaId, Usuario usuario) {
        super("Búsqueda de Productos");
        initComponents();
        Menubar menubar = new Menubar();
        menubar.initMenuBar(this, usuario, busqueda, categoriaId);
        controladorProducto = new ControladorProducto();
        this.usuario = usuario;
        this.busqueda = busqueda;
        this.categoriaId = categoriaId;
        
        System.out.println(busqueda);
        System.out.println(categoriaId);
       

        if (categoriaId == -1 && (busqueda == null || busqueda.isEmpty())) {
           List<Producto> productos = controladorProducto.obtenerTodosLosProductos();
            JScrollPane scrollPane = mostrarProductosEnLabels(productos);
            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(scrollPane, BorderLayout.CENTER);
        }
        
        if (categoriaId == -1 && !busqueda.isEmpty()) {
            
            List<Producto> productos = controladorProducto.obtenerProductosPorNombre(busqueda);
            JScrollPane scrollPane = mostrarProductosEnLabels(productos);
            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(scrollPane, BorderLayout.CENTER);
            
        }
        
        if (categoriaId != -1 && (busqueda == null || busqueda.isEmpty())) {
            
            List<Producto> productos = controladorProducto.obtenerProductosPorIdCategoria(categoriaId);
            JScrollPane scrollPane = mostrarProductosEnLabels(productos);
            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(scrollPane, BorderLayout.CENTER);
        }

        if (categoriaId != -1 && !busqueda.isEmpty()) {
            List<Producto> productos = controladorProducto.obtenerProductosPorNombreYCategoria(busqueda ,categoriaId);
            JScrollPane scrollPane = mostrarProductosEnLabels(productos);
            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(scrollPane, BorderLayout.CENTER);
        }

        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }


    
    private JScrollPane mostrarProductosEnLabels(List<Producto> productos) {
    int alturaPanel = 270; 
    int espacioLateral = 20; 
    
   
    // Creamos el panel que contendrá todos los paneles de productos
    JPanel panelProductos = new JPanel();
    panelProductos.setLayout(new BoxLayout(panelProductos, BoxLayout.Y_AXIS));
    panelProductos.setBorder(BorderFactory.createEmptyBorder(10, espacioLateral, 10, espacioLateral)); // Borde con espaciado

    
    
    double anchoPantalla = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

    // Restar 10 (espacio lateral izquierdo), 10 (espacio lateral derecho), 260 (ancho del panel de imagen), 10 (espacio entre la imagen y el texto)
    double anchoRestado = anchoPantalla - 70 - 270;
       
    String ImagenRuta;

    
    for (Producto producto : productos) {
        

        if (producto.getID_EstadoProducto() == 1){
                    
         // Ruta predeterminada de la imagen de respaldo
            String imagenPredeterminada = System.getProperty("user.dir") + "\\src\\main\\java\\Imagenes\\Sinimagen.jpg";

            // Ruta de la imagen proporcionada por el producto
            String imagenRutaProducto = System.getProperty("user.dir") + "\\src\\main\\java\\Imagenes\\" + producto.getImagenURL();

            // Verificar si la imagen del producto existe
            File imagenProducto = new File(imagenRutaProducto);
            if (imagenProducto.exists()) {
                // Si la imagen del producto existe, usar esa ruta
                 ImagenRuta = imagenRutaProducto;
            } else {
                // Si no existe, usar la imagen predeterminada
                ImagenRuta = imagenPredeterminada;
            }

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

        panelNombre.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); 


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
        
        String Categoria = controladorProducto.obtenerCategoriaPorID_Categoria(producto.getID_CategoriaProducto());
        
        categoriaLabel = new JLabel("Categoría: " + Categoria);

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
                
         } //FIN IF


    JScrollPane scrollPane = new JScrollPane(panelProductos);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    
    return scrollPane;
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
