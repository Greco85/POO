
package Vista;

import Controladores.ControladorProducto;
import Modelo.SesionActiva;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class HacerPublicacion extends javax.swing.JFrame {

    
    public HacerPublicacion() {
        initmyComponents();
    }
    
     private void initmyComponents() {
    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Hacer Publicación");
    setSize(400, 300); // Reducimos un poco el tamaño para acomodar los cambios
    setLocationRelativeTo(null);

    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout()); // Usamos GridBagLayout para mayor flexibilidad

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 5, 5, 5);
    
    // Nombre
    JLabel lblNombre = new JLabel("Nombre:");
    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(lblNombre, gbc);
    JTextField txtNombre = new JTextField(20);
    gbc.gridx = 1;
    panel.add(txtNombre, gbc);

    // Descripción
    JLabel lblDescripcion = new JLabel("Descripción:");
    gbc.gridx = 0;
    gbc.gridy = 1;
    panel.add(lblDescripcion, gbc);
    JTextField txtDescripcion = new JTextField(20);
    gbc.gridx = 1;
    panel.add(txtDescripcion, gbc);

    // Precio
    JLabel lblPrecio = new JLabel("Precio:");
    gbc.gridx = 0;
    gbc.gridy = 2;
    panel.add(lblPrecio, gbc);
    JTextField txtPrecio = new JTextField(20);
    gbc.gridx = 1;
    panel.add(txtPrecio, gbc);

    // Cantidad Disponible
    JLabel lblCantidad = new JLabel("Cantidad Disponible:");
    gbc.gridx = 0;
    gbc.gridy = 3;
    panel.add(lblCantidad, gbc);
    JTextField txtCantidad = new JTextField(20);
    gbc.gridx = 1;
    panel.add(txtCantidad, gbc);

    // Categoría
    JLabel lblCategoria = new JLabel("Categoría:");
    gbc.gridx = 0;
    gbc.gridy = 4;
    panel.add(lblCategoria, gbc);

    JComboBox<String> cmbCategoria = new JComboBox<>();
    gbc.gridx = 1;
    panel.add(cmbCategoria, gbc);

    ControladorProducto controladorCategoria = new ControladorProducto();
    List<String> categorias = controladorCategoria.obtenerCategorias();

    for (String categoria : categorias) {
        cmbCategoria.addItem(categoria);
    }

    // URL de la Imagen
    JLabel lblImagenURL = new JLabel("URL de la Imagen:");
    gbc.gridx = 0;
    gbc.gridy = 5;
    panel.add(lblImagenURL, gbc);
    JTextField txtImagenURL = new JTextField(20);
    gbc.gridx = 1;
    panel.add(txtImagenURL, gbc);

    // Botón Publicar
    JButton btnPublicar = new JButton("Publicar");
    btnPublicar.addActionListener(evt -> btnPublicarActionPerformed(txtNombre.getText(), txtDescripcion.getText(),
            txtPrecio.getText(), txtCantidad.getText(), cmbCategoria.getSelectedIndex() + 1, txtImagenURL.getText()));
    gbc.gridx = 1;
    gbc.gridy = 6;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridwidth = 2;
    panel.add(btnPublicar, gbc);

    getContentPane().add(panel);
}


    private void btnPublicarActionPerformed(String nombre, String descripcion,
                                         String precio, String cantidad, int categoria, String imagenURL) {
    int ID_Usuario = SesionActiva.getID_Usuario(); 
    System.out.println("ID_Usuario: " + ID_Usuario);
    System.out.println("Nombre: " + nombre);
    System.out.println("Descripción: " + descripcion);
    System.out.println("Precio: " + precio);
    System.out.println("Cantidad_Disponible: " + cantidad);
    System.out.println("ID_CategoriaProducto: " + categoria);
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String fechaCreacion = dateFormat.format(new Date());
    System.out.println("Fecha_Creacion: " + fechaCreacion);
    
    System.out.println("ID_EstadoProducto: " + 1);
    System.out.println("URL de la Imagen: " + imagenURL);

    ControladorProducto controladorProducto = new ControladorProducto();
    controladorProducto.agregarProducto(ID_Usuario, nombre, descripcion, precio, cantidad,categoria, fechaCreacion, imagenURL);
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
            java.util.logging.Logger.getLogger(HacerPublicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HacerPublicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HacerPublicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HacerPublicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HacerPublicacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
