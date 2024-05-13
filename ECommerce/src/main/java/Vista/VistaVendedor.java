
package Vista;

import Controladores.ControladorPedido;
import Controladores.ControladorProducto;
import Controladores.ControladorNotificacion;
import Controladores.ControladorUsuario;

import Modelo.Pedido;
import Modelo.Producto;
import java.util.List;
import Modelo.SesionActiva;
import Modelo.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
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

public class VistaVendedor extends javax.swing.JFrame {
    
    
    private ControladorProducto controladorProducto;
    private ControladorPedido controladorPedido;
    private ControladorNotificacion controladorNotificacion;
    private ControladorUsuario controladorUsuario;



    private Usuario usuario;
    private String busqueda;
    private int categoriaId;
    private Connection conexion;
    
    public VistaVendedor(Usuario usuario) {
        this.usuario = usuario;

        Menubar menubar = new Menubar();
        menubar.initMenuBar(this, usuario, busqueda, categoriaId);
        controladorProducto = new ControladorProducto(); 
        controladorPedido = new ControladorPedido();
        controladorNotificacion = new ControladorNotificacion();

        initmyComponents();
    
        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }
    

 private void initmyComponents() {
    int alturaPanel = 270; 
    int espacioLateral = 20; 
        
    
     
     int ID_Usuario = SesionActiva.getID_Usuario();
    List<Integer> idsProductos = controladorProducto.obtenerID_ProductosPorID_Usuario(ID_Usuario);

    System.out.println("Productos del Usuario:");

    JPanel bienvenidaPanel = new JPanel(new BorderLayout());
    bienvenidaPanel.setBackground(new Color(240, 240, 240)); 
    JLabel bienvenidaLabel = new JLabel("Vista de tus productos vendidos");
    bienvenidaLabel.setFont(new Font("Arial", Font.BOLD, 24)); 
    bienvenidaLabel.setForeground(new Color(50, 50, 50));
    bienvenidaPanel.add(bienvenidaLabel, BorderLayout.CENTER);
    bienvenidaPanel.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0)); 
    bienvenidaPanel.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 2)); // Borde delgado y gris
    bienvenidaLabel.setHorizontalAlignment(SwingConstants.CENTER);
    bienvenidaPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    bienvenidaPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

    JPanel contenidoPanel = new JPanel();
    contenidoPanel.setLayout(new BoxLayout(contenidoPanel, BoxLayout.Y_AXIS)); 
    contenidoPanel.setBorder(BorderFactory.createEmptyBorder(10, espacioLateral, 10, espacioLateral)); // Borde con espaciado

   for (Integer idProducto : idsProductos) {
    System.out.println("ID de Producto: " + idProducto);

    List<Pedido> pedidos = controladorPedido.obtenerPedidosPorIDProducto(idProducto);
    
    
    String ImagenRuta = System.getProperty("user.dir") + "\\src\\main\\java\\Imagenes\\bmo.jpg";
    
    double anchoPantalla = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

    // Restar 10 (espacio lateral izquierdo), 10 (espacio lateral derecho), 260 (ancho del panel de imagen), 10 (espacio entre la imagen y el texto)
    double anchoRestado = anchoPantalla - 70 - 270;

    for (Pedido pedido : pedidos) {
        if (pedido.getID_EstadoPedido() == 1) {
            
            Producto productoPedido = controladorProducto.obtenerProductosporID(idProducto);
            
            
            JPanel panelPedido = new JPanel();
            panelPedido.setLayout(null);
            panelPedido.setPreferredSize(new Dimension(getContentPane().getWidth() - (2 * espacioLateral), alturaPanel));
            panelPedido.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            
        // Crear el panel rojo
        JPanel panelImagen = new JPanel();
        panelImagen.setBackground(Color.WHITE);
        panelImagen.setBounds(10, 10, 250, 250);
        panelPedido.add(panelImagen);
        
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
        panelPedido.add(panelNombre);

        // Crear JLabel para el nombre
        JLabel nombreLabel = new JLabel(productoPedido.getNombre());
        nombreLabel.setForeground(Color.WHITE);
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 14)); 
        nombreLabel.setHorizontalAlignment(JLabel.CENTER);
        panelNombre.add(nombreLabel, BorderLayout.CENTER); 

        panelNombre.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Establecer un relleno en los bordes del JPanel
        int ID_UsuarioPEDIDO = pedido.getID_Usuario();
        String NombreUsuarioPedido = controladorProducto.obtenerNombrePorIDUsuario(ID_UsuarioPEDIDO);
        String EstadoPedido = controladorProducto.ObtenerEstadoPedidoporID(pedido.getID_EstadoPedido() );
        String MetododeEnvio = controladorProducto.obtenerMetodoEnvioPorID(pedido.getID_MetodoEnvio() );

        JTextArea descripcionTextArea = new JTextArea(
            "ID del Pedido: " + pedido.getID_Pedido() + "\n" +
            "Nombre del Usuario del Pedido: " + NombreUsuarioPedido + "\n" +
            "Estado del Pedido: " + EstadoPedido +  "\n" +
            "Método de Envío: " + MetododeEnvio + "\n" +
            "Dirección: " + pedido.getDireccion() + "\n" +
            "Fecha del Pedido: " + pedido.getFechaPedido()
        );
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

        panelPedido.add(descripcionTextArea);

        int cantidadLabels = 3; 
        double espacioDisponible = anchoRestado / cantidadLabels;
        int posicionHorizontal = 270;

        // Crear JLabels para precio, cantidad y categoría
        JLabel precioLabel = new JLabel("Total: $" + pedido.getTotal());
        JLabel cantidadLabel = new JLabel("Cantidad En El Pedido: " + pedido.getCantidad());
        JLabel categoriaLabel = new JLabel("Categoría: " + productoPedido.getID_CategoriaProducto());

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

            panelPedido.add(label);

            posicionHorizontal += espacioDisponible;
        }
        
        
            JButton entregadoButton = new JButton("Entregado a Paqueteria");
              // Botón Eliminar
        entregadoButton.setBounds(270, 230, (int)anchoRestado, 30);
        entregadoButton.setBackground(Color.PINK); 
        entregadoButton.setForeground(Color.BLACK);
        entregadoButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)), // Borde blanco
            BorderFactory.createEmptyBorder(10, 20, 10, 20) // Espacio interno
        ));
            
            entregadoButton.addActionListener(e -> {
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Ya ha sido entregado el pedido a la paquetería?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    String estadoEnvio = "En proceso";
                    int nuevoID_EstadoPedido = controladorPedido.obtenerIdEstadoPedido(estadoEnvio);

                    boolean actualizacionExitosa = controladorPedido.actualizarEstadoPedido(pedido.getID_Pedido(), nuevoID_EstadoPedido);

                    if (actualizacionExitosa) {
                        
                int ID_TipoNoti = 5; //LUEGO BUSCARLA CON UNA CONSULTA "Producto en paqueteria"

                String mensajee = "El usuario con ID : " + SesionActiva.getID_Usuario() + " ha entregado el producto con ID: " + pedido.getID_Producto() + "a la paqueteria";
                
                // Obtener la fecha y hora actual
                Date fechaActual = new Date();

                Timestamp fechaYHoraActual = new Timestamp(fechaActual.getTime());
                
                int ID_UsuarioNoti = pedido.getID_Usuario();
               
                // Llamada a la función crearNotificacion
                controladorNotificacion.crearNotificacion(ID_UsuarioNoti, ID_TipoNoti, mensajee, fechaYHoraActual);
                        dispose(); 
                        VistaVendedor vistaVendedor = new VistaVendedor(usuario);
                        vistaVendedor.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar el estado de envío del pedido.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            
            panelPedido.add(entregadoButton);
            
            
       descripcionTextArea.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            panelPedido.setBackground(Color.LIGHT_GRAY); 
        }

        @Override
        public void mouseExited(MouseEvent e) {
            panelPedido.setBackground(Color.WHITE);
        }
    });
       
        descripcionTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Guardar el ID del producto en la sesión activa
                SesionActiva.setID_Producto(pedido.getID_Producto());
                System.out.println("El id es: " + SesionActiva.getID_Producto());

                // Abrir la vista "VerProducto"
                VerProducto verProducto = new VerProducto(usuario);
                verProducto.setVisible(true);
                dispose();
            }
        });

    panelPedido.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            panelPedido.setBackground(Color.LIGHT_GRAY); 
        }

        @Override
        public void mouseExited(MouseEvent e) {
            panelPedido.setBackground(Color.WHITE); 
        }

        @Override
       
        public void mouseClicked(MouseEvent e) {
            // Guardar el ID del producto en la sesión activa
            SesionActiva.setID_Producto(pedido.getID_Producto());
            System.out.println("El id es: " + SesionActiva.getID_Producto());

            // Abrir la vista "VerProducto"
            VerProducto verProducto = new VerProducto(usuario);
            verProducto.setVisible(true);
            dispose();
        }
    });

          
        
        entregadoButton.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            entregadoButton.setBackground(new Color(128, 0, 64)); 
            entregadoButton.setForeground(Color.WHITE);

        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
        entregadoButton.setBackground(Color.PINK); 
        entregadoButton.setForeground(Color.BLACK);

        }
    });
            
            panelPedido.setBackground(Color.WHITE);

            contenidoPanel.add(panelPedido);
        }
    }
}


    JScrollPane scrollPane = new JScrollPane(contenidoPanel); 
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    getContentPane().add(bienvenidaPanel, BorderLayout.NORTH);
    getContentPane().add(scrollPane, BorderLayout.CENTER);

    pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
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

    
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
