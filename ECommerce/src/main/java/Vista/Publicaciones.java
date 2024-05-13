
package Vista;

import Controladores.ControladorProducto;
import Modelo.Producto;
import Modelo.SesionActiva;
import Modelo.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.border.Border;


public class Publicaciones extends javax.swing.JFrame {
    
    
    private JButton jButtonNuevaPublicacion;
    private ControladorProducto controladorProducto;
    private List<Producto> productos;
    private Usuario usuario;
    int ID_Usuario = SesionActiva.getID_Usuario();



    public Publicaciones(Usuario usuario) {
        this.usuario = usuario;
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
    jLabel1.setFont(new Font("Arial", Font.BOLD, 20));
    jLabel1.setForeground(Color.WHITE); 
    jLabel1.setBackground(Color.DARK_GRAY); 
    jLabel1.setOpaque(true);
    jLabel1.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));

    panelPrincipal.add(jLabel1, BorderLayout.NORTH);
    
    // Panel para mostrar productos
    jPanel1 = new JPanel();
    jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
    JScrollPane scrollPane = new JScrollPane(jPanel1);
    panelPrincipal.add(scrollPane, BorderLayout.CENTER);

    JPanel panelBotones = new JPanel(new GridLayout(2, 1));

    // Botón para nueva publicación
    jButtonNuevaPublicacion = new JButton("Nueva Publicación");
    jButtonNuevaPublicacion.setFont(new Font("Arial", Font.PLAIN, 18));
    jButtonNuevaPublicacion.setBackground(new Color(41, 81, 204)); 
        jButtonNuevaPublicacion.setForeground(Color.WHITE);
        jButtonNuevaPublicacion.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)), // Borde blanco
            BorderFactory.createEmptyBorder(10, 20, 10, 20) 
        ));
        
    jButtonNuevaPublicacion.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            jButtonNuevaPublicacionActionPerformed(evt);
            dispose();
        }
    });
    panelBotones.add(jButtonNuevaPublicacion);

    // Botón para regresar
    JButton jButtonRegresar = new JButton("Regresar");
    jButtonRegresar.setFont(new Font("Arial", Font.PLAIN, 18));
    jButtonRegresar.setBackground(new Color(102, 102, 102)); 
        jButtonRegresar.setForeground(Color.WHITE);
        jButtonRegresar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)), // Borde blanco
            BorderFactory.createEmptyBorder(10, 20, 10, 20) 
        ));
        
    jButtonRegresar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            jButtonRegresarActionPerformed(evt);
        }
    });
    panelBotones.add(jButtonRegresar);
    
    jButtonNuevaPublicacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonNuevaPublicacion.setBackground(new Color(41, 81, 204)); 
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonNuevaPublicacion.setBackground(new Color(51, 102, 255)); 
            }
        });

        jButtonRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonRegresar.setBackground(new Color(82, 82, 82)); 
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonRegresar.setBackground(new Color(102, 102, 102)); 
            }
        });

    panelPrincipal.add(panelBotones, BorderLayout.WEST);
}

    private void jButtonRegresarActionPerformed(ActionEvent evt) {
        CuentaUsuario cuentaUsuario = new CuentaUsuario(usuario);
        cuentaUsuario.setVisible(true);
        dispose();
    }

    

    private void jButtonNuevaPublicacionActionPerformed(ActionEvent evt) {
        HacerPublicacion hacerPublicacionFrame = new HacerPublicacion(usuario);
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
    int alturaPanel = 270; 
    int espacioPanel = 10;
    int espacioLateral = 20; 
    
    
    String ImagenRuta = System.getProperty("user.dir") + "\\src\\main\\java\\Imagenes\\bmo.jpg";
    
    double anchoPantalla = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

    // Restar 10 (espacio lateral izquierdo), 10 (espacio lateral derecho), 260 (ancho del panel de imagen), 10 (espacio entre la imagen y el texto)
    double anchoRestado = anchoPantalla - 70 - 270 - 70 - 45;
    
    JPanel panel = new JPanel();
    panel.setPreferredSize(new Dimension(600, 270));
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    panel.setLayout(new BorderLayout());
    
   
    // Panel para la información
    JPanel panelProducto = new JPanel();
    panelProducto.setLayout(null);
    panelProducto.setPreferredSize(new Dimension(getContentPane().getWidth() - (2 * espacioLateral), alturaPanel));
    panelProducto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
       
    
    // Crear el panel rojo
        JPanel panelImagen = new JPanel();
        panelImagen.setBackground(Color.WHITE);
        panelImagen.setBounds(10, 10, 200, 200);
        panelProducto.add(panelImagen);
        
        JLabel labelImagen = new JLabel();
        ImageIcon icono = new ImageIcon(ImagenRuta);
        Image imagen = icono.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagen);
        labelImagen.setIcon(iconoEscalado);
        labelImagen.setBounds(0, 0, 200, 200);
        panelImagen.add(labelImagen);

       
        // Crear JPanel para el nombre
        JPanel panelNombre = new JPanel();
        panelNombre.setBackground(Color.BLUE);
        panelNombre.setBounds(230, 10, (int)anchoRestado, 30);
        panelNombre.setLayout(new BorderLayout()); 
        panelProducto.add(panelNombre);

        // Crear JLabel para el nombre
        JLabel nombreLabel = new JLabel(producto.getNombre());
        nombreLabel.setForeground(Color.WHITE);
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 14)); 
        nombreLabel.setHorizontalAlignment(JLabel.CENTER);
        panelNombre.add(nombreLabel, BorderLayout.CENTER); 

        panelNombre.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); 


        JTextArea descripcionTextArea = new JTextArea("Descripción: " + producto.getDescripcion());
        descripcionTextArea.setBounds(230, 50, (int)anchoRestado, 140); 
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

        panelProducto.add(descripcionTextArea);

        int cantidadLabels = 3; 
        double espacioDisponible = anchoRestado / cantidadLabels;
        int posicionHorizontal = 230;

        // Crear JLabels para precio, cantidad y categoría
        JLabel precioLabel = new JLabel("Precio: $" + producto.getPrecio());
        JLabel cantidadLabel = new JLabel("Cantidad Disponible: " + producto.getCantidad_Disponible());
        JLabel categoriaLabel = new JLabel("Categoría: " + producto.getID_CategoriaProducto());

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

            panelProducto.add(label);

            posicionHorizontal += espacioDisponible;
        }
    
    
    
    

    JPanel panelBotones = new JPanel(new GridLayout(1, 2));
    JButton botonEditar = new JButton("EDITAR");
    botonEditar.setBackground(Color.YELLOW); 
    botonEditar.setForeground(Color.BLACK);
    botonEditar.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)), // Borde blanco
        BorderFactory.createEmptyBorder(10, 20, 10, 20) // Espacio interno
    ));
    JButton botonBorrar = new JButton("BORRAR");
    botonBorrar.setBackground(Color.RED);
    botonBorrar.setForeground(Color.BLACK);
    botonBorrar.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)), // Borde blanco
        BorderFactory.createEmptyBorder(10, 20, 10, 20) 
    ));
    
   botonEditar.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            botonEditar.setBackground(new Color(255, 204, 0)); 
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            botonEditar.setBackground(new Color(255, 255, 0));   
        }
    });

    botonBorrar.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            botonBorrar.setBackground(new Color(128, 0, 0)); 
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            botonBorrar.setBackground(new Color(255, 0, 0)); 
        }
    });
    
    panelBotones.add(botonEditar);
    panelBotones.add(botonBorrar);
    panel.add(panelProducto, BorderLayout.CENTER);
    panel.add(panelBotones, BorderLayout.SOUTH);

    botonEditar.addActionListener(evt -> {
        int ID_Producto = producto.getID_Producto();
        System.out.println("ID del Producto: " + ID_Producto);
        EditarDatosPublicacion editarDatosPublicacion = new EditarDatosPublicacion(ID_Producto, usuario);
        editarDatosPublicacion.setVisible(true);
        dispose(); 
    });

    botonBorrar.addActionListener(evt -> {
        int ID_Producto = producto.getID_Producto();
        System.out.println("ID del Producto: " + ID_Producto);

        int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de borrar este producto?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            controladorProducto = new ControladorProducto();
            controladorProducto.eliminarProducto(ID_Producto);
            dispose();
            Publicaciones publicaciones = new Publicaciones(usuario);
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
                Usuario usuario = new Usuario();
                new Publicaciones(usuario).setVisible(true);
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
