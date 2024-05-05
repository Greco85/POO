
package Vista;

import javax.swing.JLabel;
import Controladores.ControladorProducto;
import Modelo.Producto;
import Modelo.SesionActiva;
import Modelo.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


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
