
package Vista;

import Controladores.ControladorComentario;
import Controladores.ControladorNotificacion;
import Controladores.ControladorProducto;
import Controladores.ControladorUsuario;
import Modelo.Comentario;
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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class EditarComentario extends javax.swing.JFrame {

    private JTextArea comentarioTextArea;
    private ControladorNotificacion controladorNotificacion;
    private ControladorProducto controladorProducto;
    private ControladorUsuario controladorUsuario;
    
    private Connection conexion;

    
    public EditarComentario(Comentario comentarioEditar) {
        this.controladorNotificacion = new ControladorNotificacion();
        this.controladorProducto = new ControladorProducto();
        this.controladorUsuario = new ControladorUsuario(conexion);
        initmyComponents(comentarioEditar);
    }

        private void initmyComponents(Comentario comentarioEditar) {
        setTitle("Editar Comentario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLayout(new BorderLayout());

        // Panel para mostrar el ID del usuario y del producto
        JPanel idPanel = new JPanel(new GridLayout(2, 1));
        
        int ID_Usuario = comentarioEditar.getID_Usuario();
    
        Usuario usuarioP = controladorUsuario.obtenerUsuarioPorId(ID_Usuario) ;

        int ID_Producto = comentarioEditar.getID_Producto();
    
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
        
        add(idPanel, BorderLayout.NORTH);

        JPanel comentarioPanel = new JPanel(new BorderLayout());

        comentarioTextArea = new JTextArea();
        comentarioTextArea.setLineWrap(true);
        comentarioTextArea.setWrapStyleWord(true);
        comentarioTextArea.setDocument(new JTextFieldLimit(1000));
        comentarioTextArea.setText(comentarioEditar.getComentario());

        comentarioPanel.add(comentarioTextArea, BorderLayout.CENTER);
        add(comentarioPanel, BorderLayout.CENTER);
        
        
        JPanel botonesPanel = new JPanel(new FlowLayout());

        // Botón "Guardar Cambios"
        JButton guardarCambiosButton = new JButton("Guardar Cambios");
        guardarCambiosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nuevoComentario = comentarioTextArea.getText();
                ControladorComentario controlador = new ControladorComentario();
                boolean actualizado = controlador.actualizarComentario(comentarioEditar.getID_Comentario(), nuevoComentario);
                if (actualizado) {
                    System.out.println("Comentario actualizado correctamente");
                } else {
                    System.out.println("Error al actualizar el comentario");
                }
                dispose();
            }
        });
        guardarCambiosButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16)); 
        guardarCambiosButton.setBackground(new Color(51, 102, 255)); 
        guardarCambiosButton.setForeground(Color.WHITE); 
        guardarCambiosButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)), 
            BorderFactory.createEmptyBorder(15, 50, 15, 50)
        ));
        guardarCambiosButton.setFocusPainted(false); 

        // Agregar efecto de cambio de color al pasar el mouse sobre el botón "Guardar Cambios"
        guardarCambiosButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                guardarCambiosButton.setBackground(new Color(41, 81, 204)); 
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                guardarCambiosButton.setBackground(new Color(51, 102, 255)); 
            }
        });

        // Botón "Cancelar"
        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancelarButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16)); 
        cancelarButton.setBackground(new Color(102, 102, 102)); 
        cancelarButton.setForeground(Color.WHITE); 
        cancelarButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)), 
            BorderFactory.createEmptyBorder(15, 50, 15, 50)
        ));
        cancelarButton.setFocusPainted(false); 

        // Agregar efecto de cambio de color al pasar el mouse sobre el botón "Cancelar"
        cancelarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelarButton.setBackground(new Color(82, 82, 82)); 
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelarButton.setBackground(new Color(102, 102, 102)); 
            }
        });


        botonesPanel.add(guardarCambiosButton);
        botonesPanel.add(cancelarButton);
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Comentario comentario = obtenerComentarioParaEditar();
                // new EditarComentario(comentario).setVisible(true);
            }
        });

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
        }
     }
