
package Vista;

import Controladores.ControladorProducto;
import Modelo.SesionActiva;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import Modelo.Producto;




public class EditarDatosPublicacion extends javax.swing.JFrame {

    int ID_Producto;
    JTextField txtNombre;
    JTextField txtDescripcion;
    JTextField txtPrecio;
    JTextField txtCantidad;
    JComboBox<String> cmbCategoria;
    JTextField txtImagenURL;
    
    public EditarDatosPublicacion(int ID_Producto) {
        this.ID_Producto = ID_Producto;
         initmyComponents();
         obtenerDatosProducto();
       
        

    }
    
    private void initmyComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Publicación");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblNombre, gbc);
        txtNombre = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtNombre, gbc);

        // Descripción
        JLabel lblDescripcion = new JLabel("Descripción:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblDescripcion, gbc);
        txtDescripcion = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtDescripcion, gbc);

        // Precio
        JLabel lblPrecio = new JLabel("Precio:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblPrecio, gbc);
        txtPrecio = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtPrecio, gbc);

        // Cantidad Disponible
        JLabel lblCantidad = new JLabel("Cantidad Disponible:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblCantidad, gbc);
        txtCantidad = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtCantidad, gbc);

        // Categoría
        JLabel lblCategoria = new JLabel("Categoría:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lblCategoria, gbc);

        cmbCategoria = new JComboBox<>();
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
        txtImagenURL = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtImagenURL, gbc);

        // Botón Actualizar
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(evt -> {
            btnActualizarActionPerformed(txtNombre.getText(), txtDescripcion.getText(),
                    txtPrecio.getText(), txtCantidad.getText(), cmbCategoria.getSelectedIndex() + 1, txtImagenURL.getText());
            dispose();
            Publicaciones publicaciones = new Publicaciones();
            publicaciones.setVisible(true);
            
        });
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        panel.add(btnActualizar, gbc);

        getContentPane().add(panel);
    }
    
        private void obtenerDatosProducto() {
        ControladorProducto controladorProducto = new ControladorProducto();
        Producto producto = controladorProducto.obtenerProductosporID(ID_Producto);

        txtNombre.setText(producto.getNombre());
        txtDescripcion.setText(producto.getDescripcion());
        txtPrecio.setText(String.valueOf(producto.getPrecio()));
        txtCantidad.setText(String.valueOf(producto.getCantidad_Disponible()));
        txtImagenURL.setText(producto.getImagenURL());

        int ID_CategoriaProducto = producto.getID_CategoriaProducto();
        String nombreCategoria = controladorProducto.obtenerCategoriaPorID(ID_CategoriaProducto);
        if (nombreCategoria != null) {
            cmbCategoria.setSelectedItem(nombreCategoria);
        }
    }


 private void btnActualizarActionPerformed(String nombre, String descripcion,
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
    controladorProducto.actualizarProducto(ID_Producto, nombre, descripcion,
                                            Double.parseDouble(precio), Integer.parseInt(cantidad),
                                            categoria, imagenURL);
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
            java.util.logging.Logger.getLogger(EditarDatosPublicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarDatosPublicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarDatosPublicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarDatosPublicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
