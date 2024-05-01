
package Vista;

import Controladores.ControladorProducto;
import Modelo.Producto;
import Modelo.SesionActiva;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class Publicaciones extends javax.swing.JFrame {
    
    
    private JButton jButtonNuevaPublicacion;
    private ControladorProducto controladorProducto;
    private List<Producto> productos;
    int ID_Usuario = SesionActiva.getID_Usuario();



    public Publicaciones() {
        initmyComponents();
        cargarProductosDesdeBaseDeDatos();
        mostrarProductos();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    private void initmyComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mis Publicaciones");
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        getContentPane().add(panelPrincipal);
        
        // Título
        jLabel1 = new JLabel("MIS PUBLICACIONES");
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setFont(new Font("Arial", Font.BOLD, 24));
        panelPrincipal.add(jLabel1, BorderLayout.NORTH);
        
        // Panel para mostrar productos
        jPanel1 = new JPanel();
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(jPanel1);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Botón para nueva publicación
        jButtonNuevaPublicacion = new JButton("Nueva Publicación");
        jButtonNuevaPublicacion.setFont(new Font("Arial", Font.PLAIN, 18));
        jButtonNuevaPublicacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonNuevaPublicacionActionPerformed(evt);
                dispose();
            }
        });
        panelPrincipal.add(jButtonNuevaPublicacion, BorderLayout.SOUTH);
    }

    private void jButtonNuevaPublicacionActionPerformed(ActionEvent evt) {
        HacerPublicacion hacerPublicacionFrame = new HacerPublicacion();
        hacerPublicacionFrame.setVisible(true);
    }

    private void cargarProductosDesdeBaseDeDatos() {
        controladorProducto = new ControladorProducto();
        productos = controladorProducto.obtenerTodosLosProductos(ID_Usuario);
    }

    private void mostrarProductos() {
        if (productos != null) {
            for (Producto producto : productos) {
                JPanel panelProducto = crearPanelProducto(producto);
                jPanel1.add(panelProducto);
            }
        }
    }

 private JPanel crearPanelProducto(Producto producto) {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    panel.setLayout(new BorderLayout());
    JPanel panelImagen = new JPanel();
    JLabel labelImagen = new JLabel(new ImageIcon(producto.getImagenURL())); // Suponiendo que la URL es para una imagen
    panelImagen.add(labelImagen);

    // Panel para la información
    JPanel panelInfo = new JPanel(new GridLayout(2, 1));
    JLabel labelNombre = new JLabel(producto.getNombre());
    labelNombre.setFont(new Font("Arial", Font.BOLD, 18));
    JLabel labelPrecio = new JLabel("Precio: $" + producto.getPrecio());
    labelPrecio.setFont(new Font("Arial", Font.PLAIN, 16));
    panelInfo.add(labelNombre);
    panelInfo.add(labelPrecio);

    JPanel panelBotones = new JPanel(new GridLayout(1, 2));
    JButton botonEditar = new JButton("EDITAR");
    JButton botonBorrar = new JButton("BORRAR");
    panelBotones.add(botonEditar);
    panelBotones.add(botonBorrar);
    panel.add(panelImagen, BorderLayout.WEST);
    panel.add(panelInfo, BorderLayout.CENTER);
    panel.add(panelBotones, BorderLayout.SOUTH);

  
       
    
    
    botonEditar.addActionListener(evt -> {
    int ID_Producto = producto.getID_Producto();
    System.out.println("ID del Producto: " + ID_Producto);
    EditarDatosPublicacion editarDatosPublicacion = new EditarDatosPublicacion(ID_Producto);
    editarDatosPublicacion.setVisible(true);
    dispose(); 
    });

    
   botonBorrar.addActionListener(evt -> {
        int ID_Producto = producto.getID_Producto();
        System.out.println("ID del Producto: " + ID_Producto);

        int opcion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de borrar este producto?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            controladorProducto = new ControladorProducto();
            controladorProducto.eliminarProducto(ID_Producto);
            dispose();
            Publicaciones publicaciones = new Publicaciones();
            publicaciones.setVisible(true);
        }
    });


    return panel;
} 
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 255, 0));

        jButton1.setText("BORRAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("EDITAR");

        jPanel2.setBackground(new java.awt.Color(255, 153, 0));

        jLabel2.setText("IMAGEN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel2)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jLabel3.setText("NOMBRE DEL PRODUCTO");

        jLabel4.setText("PRECIO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 229, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jLabel4))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setText("MIS PUBLICACIONES");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(296, 296, 296))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(267, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Publicaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Publicaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Publicaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Publicaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Publicaciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
