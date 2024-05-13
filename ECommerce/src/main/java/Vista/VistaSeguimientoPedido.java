
package Vista;

import Controladores.ControladorCompra;
import Controladores.ControladorNotificacion;
import Controladores.ControladorPedido;
import Controladores.ControladorProducto;
import Modelo.Pedido;
import Modelo.SesionActiva;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


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
        

    JPanel bienvenidaPanel = new JPanel(new BorderLayout());
    JLabel bienvenidaLabel = new JLabel("VISTA TODOS LOS PEDIDOS ENTREGAOS EN PAQUETERIA");
    bienvenidaPanel.add(bienvenidaLabel, BorderLayout.CENTER);

    JPanel contenidoPanel = new JPanel();
    contenidoPanel.setLayout(new BoxLayout(contenidoPanel, BoxLayout.Y_AXIS)); 

   

    List<Pedido> pedidos = controladorPedido.obtenertodosPedidos();

    for (Pedido pedido : pedidos) {
        if (pedido.getID_EstadoPedido() == 2 || pedido.getID_EstadoPedido() == 3) {
            JPanel panelPedido = new JPanel();
            panelPedido.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panelPedido.setPreferredSize(new Dimension(400, 250)); 

            JLabel idPedidoLabel = new JLabel("ID del Pedido: " + pedido.getID_Pedido());
            panelPedido.add(idPedidoLabel);

            JLabel idUsuarioLabel = new JLabel("ID del Usuario: " + pedido.getID_Usuario());
            panelPedido.add(idUsuarioLabel);

            JLabel idEstadoPedidoLabel = new JLabel("ID del Estado del Pedido: " + pedido.getID_EstadoPedido());
            panelPedido.add(idEstadoPedidoLabel);

            JLabel idMetodoEnvioLabel = new JLabel("ID del Método de Envío: " + pedido.getID_MetodoEnvio());
            panelPedido.add(idMetodoEnvioLabel);

            JLabel direccionLabel = new JLabel("Dirección: " + pedido.getDireccion());
            panelPedido.add(direccionLabel);

            JLabel idProductoLabel = new JLabel("ID del Producto: " + pedido.getID_Producto());
            panelPedido.add(idProductoLabel);

            JLabel cantidadLabel = new JLabel("Cantidad: " + pedido.getCantidad());
            panelPedido.add(cantidadLabel);

            JLabel totalLabel = new JLabel("Total: " + pedido.getTotal());
            panelPedido.add(totalLabel);

            JLabel fechaPedidoLabel = new JLabel("Fecha del Pedido: " + pedido.getFechaPedido());
            panelPedido.add(fechaPedidoLabel);
            
            JButton entregadoButton = new JButton();
                if (pedido.getID_EstadoPedido() == 2) {
                    panelPedido.setBackground(new Color(255, 192, 203)); // Rosa
                    entregadoButton.setText("¿Ya va en tránsito el paquete?");
                } else if (pedido.getID_EstadoPedido() == 3) {
                    panelPedido.setBackground(new Color(255, 160, 122)); // Naranja pastel
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
