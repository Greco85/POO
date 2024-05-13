
package Vista;

import Controladores.ControladorCompra;
import Controladores.ControladorNotificacion;
import Controladores.ControladorPedido;
import Controladores.ControladorProducto;
import Modelo.Pedido;
import Modelo.Producto;
import Modelo.SesionActiva;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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


public class VistaSeguimientoPedido extends javax.swing.JFrame {

    private ControladorProducto controladorProducto;
    private ControladorPedido controladorPedido;
    private ControladorCompra controladorCompra;
        private ControladorNotificacion controladorNotificacion;

    
    public VistaSeguimientoPedido() {
        
        controladorProducto = new ControladorProducto(); 
        controladorPedido = new ControladorPedido();
        controladorCompra = new ControladorCompra();
        controladorNotificacion = new ControladorNotificacion();

        initmyComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }
    
    private void initmyComponents() {
        
    int alturaPanel = 270; 
    int espacioLateral = 20; 

    JPanel bienvenidaPanel = new JPanel(new BorderLayout());
    bienvenidaPanel.setBackground(new Color(240, 240, 240)); 
    JLabel bienvenidaLabel = new JLabel("VISTA TODOS LOS PEDIDOS ENTREGAOS EN PAQUETERIA");
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

   

    List<Pedido> pedidos = controladorPedido.obtenertodosPedidos();
    
    String ImagenRuta = System.getProperty("user.dir") + "\\src\\main\\java\\Imagenes\\bmo.jpg";
    
    double anchoPantalla = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

    // Restar 10 (espacio lateral izquierdo), 10 (espacio lateral derecho), 260 (ancho del panel de imagen), 10 (espacio entre la imagen y el texto)
    double anchoRestado = anchoPantalla - 70 - 270;
    
    for (Pedido pedido : pedidos) {
        if (pedido.getID_EstadoPedido() == 2 || pedido.getID_EstadoPedido() == 3) {
            
           Producto productoPedido = controladorProducto.obtenerProductosporID(pedido.getID_Producto());
            
            
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
        String EstadoPedido = controladorProducto.ObtenerEstadoPedidoporID(pedido.getID_EstadoPedido());
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
            
            JButton entregadoButton = new JButton();
            entregadoButton.setBounds(270, 230, (int)anchoRestado, 30);
            entregadoButton.setForeground(Color.BLACK);
            entregadoButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)), // Borde blanco
            BorderFactory.createEmptyBorder(10, 20, 10, 20) // Espacio interno
                ));
                if (pedido.getID_EstadoPedido() == 2) {
                    panelPedido.setBackground(Color.WHITE);
                    entregadoButton.setBackground(Color.PINK); 
                                
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

                    entregadoButton.setText("¿Ya va en tránsito el paquete?");
                } else if (pedido.getID_EstadoPedido() == 3) {
                                entregadoButton.setBackground(Color.YELLOW); 
                                
                                  entregadoButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    entregadoButton.setBackground(new Color(150, 150, 0)); 

                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                entregadoButton.setBackground(Color.YELLOW); 

                }
            });

                    panelPedido.setBackground(Color.WHITE);
                    entregadoButton.setText("Ya se entregó el paquete al usuario");
                }

            entregadoButton.addActionListener(e -> {
                
                int confirmacion = JOptionPane.showConfirmDialog(null, entregadoButton.getText(), "Confirmación", JOptionPane.YES_NO_OPTION);

                if (pedido.getID_EstadoPedido() == 2) {
                    
                    if (confirmacion == JOptionPane.YES_OPTION) {
                String estadoEnvio = "En tránsito";
                int nuevoID_EstadoPedido = controladorPedido.obtenerIdEstadoPedido(estadoEnvio);

                boolean actualizacionExitosa = controladorPedido.actualizarEstadoPedido(pedido.getID_Pedido(), nuevoID_EstadoPedido);
                
                LocalDateTime fechaHoraActual = LocalDateTime.now();
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                
                String fechaentregado = fechaHoraActual.format(formatter);
                boolean actualizacionExitosaDetalleCompra = controladorCompra.insertarDetalleCompra( pedido.getID_Usuario(), pedido.getID_Producto(), pedido.getTotal(),fechaentregado, pedido.getCantidad() );

                if (actualizacionExitosa && actualizacionExitosaDetalleCompra) {
                    dispose(); 
                    VistaSeguimientoPedido vistaSeguimientoPedido = new VistaSeguimientoPedido();
                    vistaSeguimientoPedido.setVisible(true);
                    
                    
                    int ID_TipoNoti = 6; //LUEGO BUSCARLA CON UNA CONSULTA "Producto en paqueteria"

                String mensajee = " El producto con ID: " + pedido.getID_Producto() + " ha sido puesto en camino";
                
                // Obtener la fecha y hora actual
                Date fechaActual = new Date();

                Timestamp fechaYHoraActual = new Timestamp(fechaActual.getTime());
                
                int ID_UsuarioNoti = pedido.getID_Usuario();
               
                // Llamada a la función crearNotificacion
                controladorNotificacion.crearNotificacion(ID_UsuarioNoti, ID_TipoNoti, mensajee, fechaYHoraActual);

                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el estado de envío del pedido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
                    
                } else if (pedido.getID_EstadoPedido() == 3) {
                    
                    if (confirmacion == JOptionPane.YES_OPTION) {
                String estadoEnvio = "Entregado";
                int nuevoID_EstadoPedido = controladorPedido.obtenerIdEstadoPedido(estadoEnvio);

                boolean actualizacionExitosa = controladorPedido.actualizarEstadoPedido(pedido.getID_Pedido(), nuevoID_EstadoPedido);

                if (actualizacionExitosa) {
                    dispose(); 
                    VistaSeguimientoPedido vistaSeguimientoPedido = new VistaSeguimientoPedido();
                    vistaSeguimientoPedido.setVisible(true);
                    
                    
                   int ID_TipoNoti = 7; //LUEGO BUSCARLA CON UNA CONSULTA "Producto en paqueteria"

                String mensajee = " El producto con ID: " + pedido.getID_Producto() + " ha sido entregado";
                
                
                // Obtener la fecha y hora actual
                Date fechaActual = new Date();

                Timestamp fechaYHoraActual = new Timestamp(fechaActual.getTime());
                
                int ID_UsuarioNoti = pedido.getID_Usuario();
                
                int ID_UsuarioNotiVendedor = controladorProducto.buscarID_UsuarioporID_Producto(pedido.getID_Producto());

               
                // Llamada a la función crearNotificacion
                controladorNotificacion.crearNotificacion(ID_UsuarioNoti, ID_TipoNoti, mensajee, fechaYHoraActual);

                controladorNotificacion.crearNotificacion(ID_UsuarioNotiVendedor, ID_TipoNoti, mensajee, fechaYHoraActual);

                
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el estado de envío del pedido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
                    
                }
              
        });
              panelPedido.add(entregadoButton);

              contenidoPanel.add(panelPedido);



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
            java.util.logging.Logger.getLogger(VistaSeguimientoPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaSeguimientoPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaSeguimientoPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaSeguimientoPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaSeguimientoPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
