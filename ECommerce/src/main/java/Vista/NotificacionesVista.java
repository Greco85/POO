
package Vista;

import Controladores.ControladorNotificacion;
import Modelo.Notificacion;
import Modelo.SesionActiva;
import Modelo.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


public class NotificacionesVista extends javax.swing.JFrame {

    
    private Usuario usuario;
     private String busqueda;
     private int categoriaId;
     private JPanel detallesProductoPanel;
     private Connection conexion;
     private ControladorNotificacion controladorNotificacion;

    
    public NotificacionesVista(Usuario usuario) {
        
        this.usuario = usuario;        
        this.controladorNotificacion = new ControladorNotificacion();
        initmyComponents();
        mostrarNotificaciones();
        Menubar menubar = new Menubar();
        menubar.initMenuBar(this, usuario, busqueda, categoriaId);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        añadirBotonBorrar();
    }
    
    private void añadirBotonBorrar() {
        JButton btnBorrar = new JButton("Borrar Notificaciones");
        btnBorrar.setBackground(Color.RED);
        btnBorrar.setForeground(Color.BLACK);
        btnBorrar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)), // Borde blanco
            BorderFactory.createEmptyBorder(10, 20, 10, 20) // Espacio interno
        ));
        
        btnBorrar.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            btnBorrar.setBackground(new Color(230, 0, 0)); // Rojo oscuro
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            btnBorrar.setBackground(new Color(255, 0, 0)); // Rojo normal
        }
    });
        
        
        btnBorrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controladorNotificacion.borrarNotificaciones(SesionActiva.getID_Usuario());
                
                NotificacionesVista notificacionesVista = new NotificacionesVista(usuario);
                notificacionesVista.setVisible(true);
                dispose();
                
                
            }
        });

        jPanel1.add(btnBorrar, BorderLayout.PAGE_END);
    }
    
    public void mostrarNotificaciones() {
        
        int alturaPanel = 100; 
        int espacioLateral = 20; 
    
        int ID_Usuario = SesionActiva.getID_Usuario();
        List<Notificacion> notificaciones = controladorNotificacion.obtenerTodasLasNotificacionesByIDUsuario(ID_Usuario);

        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.Y_AXIS));
        panelContainer.setBorder(BorderFactory.createEmptyBorder(10, espacioLateral, 10, espacioLateral)); // Borde con espaciado
        
        String ImagenRuta = System.getProperty("user.dir") + "\\src\\main\\java\\Imagenes\\bmo.jpg";
    
        double anchoPantalla = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        // Restar 10 (espacio lateral izquierdo), 10 (espacio lateral derecho), 260 (ancho del panel de imagen), 10 (espacio entre la imagen y el texto)
        double anchoRestado = anchoPantalla - 70 - 270;
    
        for (Notificacion notificacion : notificaciones) {
        
            JPanel panel = new javax.swing.JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(getContentPane().getWidth() - (2 * espacioLateral), alturaPanel));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            // Crear el panel rojo
        JPanel panelImagen = new JPanel();
        panelImagen.setBackground(Color.RED);
        panelImagen.setBounds(10, 10, 80, 80);
        panel.add(panelImagen);
        
        JLabel labelImagen = new JLabel();
        ImageIcon icono = new ImageIcon(ImagenRuta);
        Image imagen = icono.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagen);
        labelImagen.setIcon(iconoEscalado);
        labelImagen.setBounds(0, 0, 80, 80);
        panelImagen.add(labelImagen);
        
        
        JTextArea descripcionTextArea = new JTextArea(notificacion.getMensaje());
        descripcionTextArea.setBounds(100, 10, (int)anchoRestado, 80); 
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

        panel.add(descripcionTextArea);
        
        
        // Crear JPanel para la fecha
        JPanel panelfecha = new JPanel();
        panelfecha.setBackground(Color.BLUE);
        panelfecha.setBounds(650 , 20, (int)anchoRestado, 50);
        panelfecha.setLayout(new BorderLayout()); 
        panel.add(panelfecha);

        // Crear JLabel para la fecha
        Date fechaNotificacion = notificacion.getFecha();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
        String fechaFormateada = sdf.format(fechaNotificacion); 

        JLabel fecha = new JLabel(fechaFormateada);
        fecha.setForeground(Color.WHITE);
        fecha.setFont(new Font("Arial", Font.BOLD, 14)); 
        fecha.setHorizontalAlignment(JLabel.CENTER);
        panelfecha.add(fecha, BorderLayout.CENTER); 

        panelfecha.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Establecer un relleno en los bordes del JPanel

        panel.setMaximumSize(new Dimension(Short.MAX_VALUE, 100));

        
        

        
        panel.setBackground(Color.BLUE);

         
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }
            });

            panelContainer.add(panel);
        }

        JScrollPane scrollPane = new JScrollPane(panelContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(scrollPane, BorderLayout.CENTER);

        jPanel1.revalidate();
        jPanel1.repaint();
    }
    
    private void initmyComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 268, Short.MAX_VALUE)
        );
        
        // Crear el JLabel
        jLabel1 = new JLabel("NOTIFICACIONES");

        jLabel1.setFont(new Font("Arial", Font.BOLD, 20));
        jLabel1.setForeground(Color.BLACK); 
        jLabel1.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); 

        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setVerticalAlignment(SwingConstants.CENTER);
        jLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(jLabel1)
                .addContainerGap(154, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 268, Short.MAX_VALUE)
        );

        jLabel1.setText("NOTIFICACIONES");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(jLabel1)
                .addContainerGap(154, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
            java.util.logging.Logger.getLogger(NotificacionesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NotificacionesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NotificacionesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NotificacionesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
