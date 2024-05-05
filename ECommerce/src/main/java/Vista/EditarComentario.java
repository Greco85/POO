
package Vista;

import Controladores.ControladorComentario;
import Modelo.Comentario;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    
    public EditarComentario(Comentario comentarioEditar) {
        initmyComponents(comentarioEditar);
    }

        private void initmyComponents(Comentario comentarioEditar) {
        setTitle("Editar Comentario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLayout(new BorderLayout());

        // Panel para mostrar el ID del usuario y del producto
        JPanel idPanel = new JPanel(new GridLayout(2, 1));

        JLabel idUsuarioLabel = new JLabel("ID del Usuario: " + comentarioEditar.getID_Usuario());
        JLabel idProductoLabel = new JLabel("ID del Producto: " + comentarioEditar.getID_Producto());

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


        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        botonesPanel.add(guardarCambiosButton);
        botonesPanel.add(cancelarButton);
        add(botonesPanel, BorderLayout.SOUTH);

        setSize(300, 300); 
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
