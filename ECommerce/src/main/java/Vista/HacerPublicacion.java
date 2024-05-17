
package Vista;

import Controladores.ControladorProducto;
import Modelo.SesionActiva;
import Modelo.Usuario;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class HacerPublicacion extends javax.swing.JFrame {

      private Usuario usuario;
      
    int ID_Producto;
    JTextField txtNombre;
    JTextField txtDescripcion;
    JTextField txtPrecio;
    JTextField txtCantidad;
    JComboBox<String> cmbCategoria;
    JTextField txtImagenURL;
      
    public HacerPublicacion(Usuario usuario) {
        this.usuario = usuario;
        initmyComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
     private void initmyComponents() {
    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Hacer Publicación");
    setSize(600, 600); // Reducimos un poco el tamaño para acomodar los cambios
    setLocationRelativeTo(null);

    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout()); // Usamos GridBagLayout para mayor flexibilidad

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 5, 5, 5);
    
    // Nombre
    JLabel lblNombre = new JLabel("Nombre:");
    lblNombre.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
    lblNombre.setForeground(Color.BLACK);
    lblNombre.setOpaque(true);
    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(lblNombre, gbc);
    txtNombre = new JTextField(20);
    txtNombre.setPreferredSize(new Dimension(300, 40)); // Ajustar el tamaño del JTextField
    txtNombre.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY), // Borde externo
                BorderFactory.createEmptyBorder(5, 10, 5, 10) // Espacio interno
        ));
    gbc.gridx = 1;
    panel.add(txtNombre, gbc);

    // Descripción
    JLabel lblDescripcion = new JLabel("Descripción:");
    lblDescripcion.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
    lblDescripcion.setForeground(Color.BLACK);
    lblNombre.setOpaque(true);
    gbc.gridx = 0;
    gbc.gridy = 1;
    panel.add(lblDescripcion, gbc);
    txtDescripcion = new JTextField(20);
    txtDescripcion.setPreferredSize(new Dimension(300, 40)); // Ajustar el tamaño del JTextField
    txtDescripcion.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY), // Borde externo
                BorderFactory.createEmptyBorder(5, 10, 5, 10) // Espacio interno
        ));
    gbc.gridx = 1;
    panel.add(txtDescripcion, gbc);

    // Precio
    JLabel lblPrecio = new JLabel("Precio:");
    lblPrecio.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
    lblPrecio.setForeground(Color.BLACK);
    lblPrecio.setOpaque(true);
    gbc.gridx = 0;
    gbc.gridy = 2;
    panel.add(lblPrecio, gbc);
    txtPrecio = new JTextField(20);
    txtPrecio.setPreferredSize(new Dimension(300, 40)); // Ajustar el tamaño del JTextField
    txtPrecio.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY), // Borde externo
                BorderFactory.createEmptyBorder(5, 10, 5, 10) // Espacio interno
        ));
    gbc.gridx = 1;
    panel.add(txtPrecio, gbc);

    // Cantidad Disponible
    JLabel lblCantidad = new JLabel("Cantidad Disponible:");
    lblCantidad.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
    lblCantidad.setForeground(Color.BLACK);
    lblCantidad.setOpaque(true);
    
    gbc.gridx = 0;
    gbc.gridy = 3;
    panel.add(lblCantidad, gbc);
    txtCantidad = new JTextField(20);
    txtCantidad.setPreferredSize(new Dimension(300, 40)); // Ajustar el tamaño del JTextField
    txtCantidad.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY), // Borde externo
                BorderFactory.createEmptyBorder(5, 10, 5, 10) // Espacio interno
        ));
    gbc.gridx = 1;
    panel.add(txtCantidad, gbc);

    // Categoría
    JLabel lblCategoria = new JLabel("Categoría:");
    lblCategoria.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
    lblCategoria.setForeground(Color.BLACK);
    lblCategoria.setOpaque(true);
    gbc.gridx = 0;
    gbc.gridy = 4;
    panel.add(lblCategoria, gbc);

    cmbCategoria = new JComboBox<>();
    cmbCategoria.setPreferredSize(new Dimension(245, 40)); // Establecer el ancho y alto del JComboBox

    gbc.gridx = 1;
    panel.add(cmbCategoria, gbc);

    ControladorProducto controladorCategoria = new ControladorProducto();
    List<String> categorias = controladorCategoria.obtenerCategorias();

    for (String categoria : categorias) {
        cmbCategoria.addItem(categoria);
    }

    // Estado del Producto
    JLabel lblEstadoProducto = new JLabel("Estado del Producto:");
    lblEstadoProducto.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
    lblEstadoProducto.setForeground(Color.BLACK);
    lblEstadoProducto.setOpaque(true);
    gbc.gridx = 0;
    gbc.gridy = 5; // Cambia el índice según la disposición de tu panel
    panel.add(lblEstadoProducto, gbc);

    JComboBox cmbEstadoProducto = new JComboBox<>();
    cmbEstadoProducto.setPreferredSize(new Dimension(245, 40)); // Establecer el ancho y alto del JComboBox

    gbc.gridx = 1;
    panel.add(cmbEstadoProducto, gbc);

    // Agregar los estados del producto al JComboBox
    List<String> estados = controladorCategoria.obtenerEstadosProducto();
    for (String estado : estados) {
        cmbEstadoProducto.addItem(estado);
    }


    // Botón Publicar
    JButton btnPublicar = new JButton("Publicar");
    btnPublicar.setFont(new Font("Arial", Font.PLAIN, 18));
    btnPublicar.setBackground(new Color(41, 81, 204)); 
        btnPublicar.setForeground(Color.WHITE);
        btnPublicar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)), // Borde blanco
            BorderFactory.createEmptyBorder(10, 20, 10, 20) 
        ));
        
    btnPublicar.addActionListener(evt -> {
        btnPublicarActionPerformed(txtNombre.getText(), txtDescripcion.getText(),
  txtPrecio.getText(), txtCantidad.getText(), cmbCategoria.getSelectedIndex() + 1, cmbEstadoProducto.getSelectedIndex() + 1,  txtNombre.getText() + "_" + SesionActiva.getID_Usuario() + ".jpg");
        Publicaciones publicaciones = new Publicaciones(usuario);
        publicaciones.setVisible(true);
        dispose();
    });
    
    gbc.gridx = 1;
    gbc.gridy = 6;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridwidth = 2;
    panel.add(btnPublicar, gbc);
    
    
    // Botón Regresar
    JButton btnRegresar = new JButton("Regresar");
    btnRegresar.setFont(new Font("Arial", Font.PLAIN, 18));
    btnRegresar.setBackground(new Color(102, 102, 102)); 
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)), // Borde blanco
            BorderFactory.createEmptyBorder(10, 20, 10, 20) 
        ));
        
    btnRegresar.addActionListener(evt -> {
        dispose();
        Publicaciones publicaciones = new Publicaciones(usuario);
        publicaciones.setVisible(true);
    });
    gbc.gridy = 7;
    panel.add(btnRegresar, gbc);
    
     btnPublicar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPublicar.setBackground(new Color(41, 81, 204));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPublicar.setBackground(new Color(51, 102, 255)); 
            }
        });

        btnRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegresar.setBackground(new Color(82, 82, 82));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegresar.setBackground(new Color(102, 102, 102));
            }
        });
    
    
    
    getContentPane().add(panel);
}


    private void btnPublicarActionPerformed(String nombre, String descripcion,
                                         String precio, String cantidad, int categoria, int IDestadoproducto, String imagenURL) {
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
    
    System.out.println("ID_EstadoProducto: " + IDestadoproducto);

    ControladorProducto controladorProducto = new ControladorProducto();
    controladorProducto.agregarProducto(ID_Usuario, nombre, descripcion, precio, cantidad,categoria, fechaCreacion, IDestadoproducto, imagenURL);
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
               Usuario usuario = new Usuario();
               new HacerPublicacion(usuario).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
