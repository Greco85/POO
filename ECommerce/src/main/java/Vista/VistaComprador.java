
package Vista;

import Controladores.ControladorPedido;
import Controladores.ControladorProducto;
import Modelo.Pedido;
import Modelo.SesionActiva;
import Modelo.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class VistaComprador extends javax.swing.JFrame {
    
    
    private ControladorProducto controladorProducto;
    private ControladorPedido controladorPedido;

    private Usuario usuario;
    private String busqueda;
    private int categoriaId;
    
    public VistaComprador(Usuario usuario) {
        this.usuario = usuario;
        menubar.initMenuBar(this, usuario, busqueda, categoriaId);

        controladorProducto = new ControladorProducto(); 
        controladorPedido = new ControladorPedido();
        initmyComponents();
    
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    
    
    private void initmyComponents() {
        
    int ID_Usuario = SesionActiva.getID_Usuario();

    System.out.println("Productos del Usuario:");

    JPanel bienvenidaPanel = new JPanel(new BorderLayout());
    JLabel bienvenidaLabel = new JLabel("VISTA DE TUS PRODUCTOS COMPRADOS");
    bienvenidaPanel.add(bienvenidaLabel, BorderLayout.CENTER);

    JPanel contenidoPanel = new JPanel();
    contenidoPanel.setLayout(new BoxLayout(contenidoPanel, BoxLayout.Y_AXIS)); 


    List<Pedido> pedidos = controladorPedido.obtenerPedidosPorIDUsuario(ID_Usuario);

    for (Pedido pedido : pedidos) {
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
            
            String estadoEnvio = "Entregado";
            int nuevoID_EstadoPedido = controladorPedido.obtenerIdEstadoPedido(estadoEnvio);

            
            if (nuevoID_EstadoPedido == pedido.getID_EstadoPedido()){
                
                 JButton entregadoButton = new JButton("¿Deseas Borrarlo?");
            entregadoButton.addActionListener(e -> {
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Deseas borrar esta informacion de esta pantalla?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    
                    boolean borradoExitoso = controladorPedido.borrarPedido(pedido.getID_Pedido());

                    if (borradoExitoso) {
                        dispose(); 
                        VistaComprador vistaComprador = new VistaComprador(usuario);
                        vistaComprador.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar el estado de envío del pedido.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            panelPedido.add(entregadoButton);
            
            }
            
            
           

        switch (pedido.getID_EstadoPedido()) {
            case 1:
                panelPedido.setBackground(Color.RED);
                break;
            case 2:
                panelPedido.setBackground(Color.BLUE);
                break;
            case 3:
                panelPedido.setBackground(Color.ORANGE);
                break;
            case 4:
                panelPedido.setBackground(Color.GREEN);
                break;
            default:
                panelPedido.setBackground(Color.WHITE);
                break;
        }

            contenidoPanel.add(panelPedido);
        
    
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
            java.util.logging.Logger.getLogger(VistaComprador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaComprador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaComprador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaComprador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
