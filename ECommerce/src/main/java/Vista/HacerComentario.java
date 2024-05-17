
package Vista;

import Controladores.ControladorComentario;
import Controladores.ControladorNotificacion;
import Controladores.ControladorProducto;
import Controladores.ControladorUsuario;
import Modelo.Producto;
import Modelo.SesionActiva;
import Modelo.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class HacerComentario extends javax.swing.JFrame {

    private JTextArea comentarioTextArea;
    private Connection conexion;

    private ControladorNotificacion controladorNotificacion;
    private ControladorProducto controladorProducto;
    private ControladorUsuario controladorUsuario;



    public HacerComentario() {
        this.controladorNotificacion = new ControladorNotificacion();
        this.controladorProducto = new ControladorProducto();
        this.controladorUsuario = new ControladorUsuario(conexion);
        initmyComponents();
    }
    
    private void initmyComponents() {
    setTitle("Hacer Comentario");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
    setLayout(new BorderLayout());

    // Panel para el área de comentario
    JPanel comentarioPanel = new JPanel(new BorderLayout());
    
    // Panel para mostrar el ID del usuario y del producto
    JPanel idPanel = new JPanel(new GridLayout(2, 1));
    
    int ID_Usuario = SesionActiva.getID_Usuario();
    
    Usuario usuarioP = controladorUsuario.obtenerUsuarioPorId(ID_Usuario) ;
    
    int ID_Producto = SesionActiva.getID_Producto();
    
    Producto productoP = controladorProducto.obtenerProductosporID(ID_Producto) ;

    
    JLabel idUsuarioLabel = new JLabel("Nombre de Usuario: " + usuarioP.getNombre());
    JLabel idProductoLabel = new JLabel("Producto: " + productoP.getNombre());
    
    idUsuarioLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16)); 
    idUsuarioLabel.setForeground(new Color(0, 0,0)); 
    idUsuarioLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    idProductoLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16)); 
    idProductoLabel.setForeground(new Color(0, 0,0));
    idProductoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    
    idPanel.add(idUsuarioLabel);
    idPanel.add(idProductoLabel);
    
    comentarioPanel.add(idPanel, BorderLayout.NORTH);
    
    comentarioTextArea = new JTextArea();
    comentarioTextArea.setLineWrap(true);
    comentarioTextArea.setWrapStyleWord(true);
    comentarioTextArea.setDocument(new JTextFieldLimit(1000)); 
    comentarioPanel.add(comentarioTextArea, BorderLayout.CENTER);
    add(comentarioPanel, BorderLayout.CENTER);

    // Panel para los botones
    JPanel botonesPanel = new JPanel(new FlowLayout());
    JButton hacerComentarioButton = new JButton("Hacer Comentario");
    hacerComentarioButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16)); 
    hacerComentarioButton.setText("Hacer Comentario");
    hacerComentarioButton.setBackground(new Color(51, 102, 255)); 
    hacerComentarioButton.setForeground(Color.WHITE); 
    hacerComentarioButton.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)), 
        BorderFactory.createEmptyBorder(15, 50, 15, 50)
    ));
    hacerComentarioButton.setFocusPainted(false);
    
    hacerComentarioButton.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            hacerComentarioButton.setBackground(new Color(41, 81, 204)); 
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            hacerComentarioButton.setBackground(new Color(51, 102, 255)); 
        }
    });
    
    hacerComentarioButton.addActionListener(new ActionListener() {
    
        public void actionPerformed(ActionEvent e) {
        // Lógica para hacer un comentario
        String comentario = comentarioTextArea.getText();
        int ID_Usuario = SesionActiva.getID_Usuario();
        int ID_Producto = SesionActiva.getID_Producto();
        Date fechaComentario = new Date(); 
        
        ControladorComentario controladorComentario = new ControladorComentario();
        boolean comentarioHecho = controladorComentario.hacerComentario(ID_Usuario, ID_Producto, comentario, fechaComentario);
        
        if(comentarioHecho) {
            System.out.println("Comentario hecho correctamente");
            
                 
                
            String nombreUsuario = controladorUsuario.ObtenerNombreporID(SesionActiva.getID_Usuario());
            String NombreProducto = controladorProducto.obtenerNombreProductoporID(ID_Producto);

            int ID_TipoNoti = 4; //LUEGO BUSCARLA CON UNA CONSULTA "Comentario"

                String mensajee = "El usuario: " + nombreUsuario + " ha puesto un mensaje en el producto: " + NombreProducto;
                // Obtener la fecha y hora actual
                Date fechaActual = new Date();

                Timestamp fechaYHoraActual = new Timestamp(fechaActual.getTime());
                
                int ID_UsuarioNoti = controladorProducto.buscarID_UsuarioporID_Producto(ID_Producto);
               
                // Llamada a la función crearNotificacion
                controladorNotificacion.crearNotificacion(ID_UsuarioNoti, ID_TipoNoti, mensajee, fechaYHoraActual);

            
        } else {
            System.out.println("Error al hacer el comentario");
        }
        dispose();
    }
});
    
    JButton regresarButton = new JButton("Regresar");
    regresarButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16)); 
    regresarButton.setText("Regresar");
    regresarButton.setBackground(new Color(102, 102, 102)); 
    regresarButton.setForeground(Color.WHITE); 
    regresarButton.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)), 
        BorderFactory.createEmptyBorder(15, 50, 15, 50)
    ));
    regresarButton.setFocusPainted(false);
    
    regresarButton.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        regresarButton.setBackground(new Color(82, 82, 82));
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
        regresarButton.setBackground(new Color(102, 102, 102));
    }
});

    regresarButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    });
    
    botonesPanel.add(hacerComentarioButton);
    botonesPanel.add(regresarButton);
    add(botonesPanel, BorderLayout.SOUTH);

    setSize(600, 600); 
    setLocationRelativeTo(null); 
    setVisible(true);
}
    

    class JTextFieldLimit extends PlainDocument {
        private int limit;
        
        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }
        
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null)
                return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
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
            java.util.logging.Logger.getLogger(HacerComentario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HacerComentario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HacerComentario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HacerComentario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HacerComentario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
