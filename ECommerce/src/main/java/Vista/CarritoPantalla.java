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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
        menubar.initMenuBar(this, usuario, busqueda, categoriaId);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
}


       
    private JPanel crearPanelProductos() {
    JPanel panelProductos = new JPanel();
    panelProductos.setLayout(new BoxLayout(panelProductos, BoxLayout.Y_AXIS));
    panelProductos.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Borde con espaciado

    List<Carrito> productosEnCarrito = controladorCarrito.obtenerTodoElCarrito(SesionActiva.getID_Usuario());

    for (Carrito carrito : productosEnCarrito) {
        JPanel panelProducto = new JPanel();
        panelProducto.setLayout(null);
        panelProducto.setPreferredSize(new Dimension(getWidth() - 40, 200));
        panelProducto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        totalCarrito = totalCarrito + carrito.getTotal();
        
        int ID_Producto = carrito.getID_Producto();
        Producto producto = controladorProducto.obtenerProductosporID(ID_Producto);

        JLabel nombreLabel = new JLabel("Nombre: " + producto.getNombre());
        nombreLabel.setBounds(10, 10, 400, 20);
        panelProducto.add(nombreLabel);

        JLabel precioLabel = new JLabel("Precio: $" + producto.getPrecio());
        precioLabel.setBounds(10, 40, 100, 20);
        panelProducto.add(precioLabel);

        JLabel cantidadLabel = new JLabel("Cantidad: " + carrito.getCantidad());
        cantidadLabel.setBounds(10, 70, 200, 20);
        panelProducto.add(cantidadLabel);

        JLabel totalLabel = new JLabel("Total: $" + carrito.getTotal());
        totalLabel.setBounds(10, 100, 100, 20);
        panelProducto.add(totalLabel);

        JLabel imagenLabel = new JLabel("Imagen: " + producto.getImagenURL());
        imagenLabel.setBounds(10, 130, 400, 20);
        panelProducto.add(imagenLabel);
        
        
        // Botón Eliminar
        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setBounds(10, 160, 80, 30);
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



        
        panelProducto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                SesionActiva.setID_Producto(producto.getID_Producto());
                System.out.println("El id es: " + SesionActiva.getID_Producto());

                VerProducto verProducto = new VerProducto(usuario);
                verProducto.setVisible(true);
                dispose();
            }
        });

        panelProductos.add(panelProducto);
    }

    return panelProductos;
}
    
    
    
    
        private void initmyComponents() {
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            JLabel bienvenidaLabel = new JLabel("Carrito de Compras");

            JPanel bienvenidaPanel = new JPanel(new BorderLayout());
            bienvenidaPanel.add(bienvenidaLabel, BorderLayout.CENTER);

            controladorCarrito = new ControladorCarrito();
            controladorProducto = new ControladorProducto();
            JPanel panelProductos = crearPanelProductos();

            JScrollPane scrollPane = new JScrollPane(panelProductos);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            JPanel panelCompra = new JPanel();
          
            
            JLabel comprarLabel = new JLabel("El total de este carrito es: " + totalCarrito);
            SesionActiva.setTotalAPagar(totalCarrito); 

            JButton comprarButton = new JButton("Comprar");
            comprarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    PantallaDeCompra pantallaDeCompra = new PantallaDeCompra(usuario, conexion);
                    pantallaDeCompra.setVisible(true);
                    
                }
            });
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
