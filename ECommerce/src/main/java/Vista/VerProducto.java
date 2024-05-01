
package Vista;

import Controladores.ControladorProducto;
import Modelo.Producto;
import Modelo.SesionActiva;
import Modelo.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.sql.Connection;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class VerProducto extends javax.swing.JFrame {

    private Usuario usuario;
    private String busqueda;
    private int categoriaId;
    private Connection conexion;
    
    public VerProducto(Usuario usuario) {
        this.usuario = usuario;
        obtenerDatosProducto();
        initmyComponents();
        menubar.initMenuBar(this, usuario, busqueda, categoriaId);
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
   private void obtenerDatosProducto() {
    
    ControladorProducto controlador = new ControladorProducto(); 
     Producto producto = controlador.obtenerProductosporID(SesionActiva.getID_Producto());

    if (producto != null) {
        System.out.println("Detalles del Producto:");
        System.out.println("ID: " + producto.getID_Producto());
        System.out.println("Nombre: " + producto.getNombre());
        System.out.println("Descripción: " + producto.getDescripcion());
        System.out.println("Precio: " + producto.getPrecio());
        System.out.println("Cantidad Disponible: " + producto.getCantidad_Disponible());
        System.out.println("ID de Categoría: " + producto.getID_CategoriaProducto());
        
    } else {
        System.out.println("El producto con ID " + SesionActiva.getID_Producto() + " no fue encontrado.");
    }
}
    private void initmyComponents() {
        
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int) screenSize.getWidth();
    int screenHeight = (int) screenSize.getHeight();

    JPanel detallesProductoPanel = new JPanel(new GridBagLayout()); 
    detallesProductoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 

    ControladorProducto controlador = new ControladorProducto();
    Producto producto = controlador.obtenerProductosporID(SesionActiva.getID_Producto());

    if (producto != null) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); 

        JLabel idLabel = new JLabel("ID:");
        JLabel idValorLabel = new JLabel(Integer.toString(SesionActiva.getID_Producto()));
        detallesProductoPanel.add(idLabel, gbc);
        detallesProductoPanel.add(idValorLabel, gbc);

        JLabel nombreLabel = new JLabel("Nombre:");
        JLabel nombreValorLabel = new JLabel(producto.getNombre());
        detallesProductoPanel.add(nombreLabel, gbc);
        detallesProductoPanel.add(nombreValorLabel, gbc);
        
        JLabel descripcionLabel = new JLabel("Descripción:");
        JLabel descripcionValorLabel = new JLabel(producto.getDescripcion());
        detallesProductoPanel.add(descripcionLabel, gbc);
        detallesProductoPanel.add(descripcionValorLabel, gbc);

        JLabel precioLabel = new JLabel("Precio:");
        JLabel precioValorLabel = new JLabel(Double.toString(producto.getPrecio()));
        detallesProductoPanel.add(precioLabel, gbc);
        detallesProductoPanel.add(precioValorLabel, gbc);

        JLabel cantidadLabel = new JLabel("Cantidad Disponible:");
        JLabel cantidadValorLabel = new JLabel(Integer.toString(producto.getCantidad_Disponible()));
        detallesProductoPanel.add(cantidadLabel, gbc);
        detallesProductoPanel.add(cantidadValorLabel, gbc);

        JLabel categoriaLabel = new JLabel("ID de Categoría:");
        JLabel categoriaValorLabel = new JLabel(Integer.toString(producto.getID_CategoriaProducto()));
        detallesProductoPanel.add(categoriaLabel, gbc);
        detallesProductoPanel.add(categoriaValorLabel, gbc);

        gbc.gridwidth = 2; 
        gbc.gridy = 6;
        gbc.insets = new Insets(20, 5, 5, 5); 
        ImageIcon imagenIcono = new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Imagenes\\bmo.jpg");
        Image imagenOriginal = imagenIcono.getImage();
        Image imagenEscalada = imagenOriginal.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon imagenEscaladaIcono = new ImageIcon(imagenEscalada);
        JLabel imagenLabel = new JLabel();
        imagenLabel.setIcon(imagenEscaladaIcono);
        detallesProductoPanel.add(imagenLabel, gbc);
    } else {
        JLabel errorLabel = new JLabel("El producto no fue encontrado.");
        detallesProductoPanel.add(errorLabel);
    }

    JScrollPane scrollPane = new JScrollPane(detallesProductoPanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(scrollPane, BorderLayout.EAST);

    int margin = 0;
    int panelWidth = screenWidth - (2 * margin);
    int panelHeight = screenHeight - (2 * margin);
    detallesProductoPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));

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

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(VerProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Usuario usuario = new Usuario();
                new VerProducto(usuario).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
