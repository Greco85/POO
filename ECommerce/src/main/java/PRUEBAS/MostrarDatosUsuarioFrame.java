
package PRUEBAS;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import Modelo.Usuario;
import SQL.Conexion;
import Controladores.ControladorUsuario;


public class MostrarDatosUsuarioFrame extends javax.swing.JFrame {
    
    private JButton btnMostrarDatos;
    private Usuario usuario;
    private Connection conexion;

    public MostrarDatosUsuarioFrame(Usuario usuario, Connection conexion) {
        this.usuario = usuario;
        this.conexion = conexion; // Asigna la conexión recibida al miembro de la clase
        initComponents();
        initmyComponents();
    }

private void initmyComponents() {
        setTitle("Mostrar Datos de Usuario");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnMostrarDatos = new JButton("Mostrar Datos");
        btnMostrarDatos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarDatosUsuario();
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addGap(100)
                .addComponent(btnMostrarDatos)
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addGap(80)
                .addComponent(btnMostrarDatos)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

private void mostrarDatosUsuario() {
        String idUsuarioStr = JOptionPane.showInputDialog(this, "Ingrese el ID del usuario:", "ID de Usuario", JOptionPane.QUESTION_MESSAGE);
        
        try {
            int idUsuario = Integer.parseInt(idUsuarioStr);
            ControladorUsuario controladorUsuario = new ControladorUsuario(conexion);
            Usuario usuario = controladorUsuario.obtenerUsuarioPorId(idUsuario);
            if (usuario != null) {
                mostrarMensajeUsuario(usuario);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un usuario con el ID especificado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID del usuario debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } 

    private void mostrarMensajeUsuario(Usuario usuario) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Nombre: ").append(usuario.getNombre()).append("\n");
        mensaje.append("Apellido: ").append(usuario.getApellido()).append("\n");
        mensaje.append("Correo Electrónico: ").append(usuario.getCorreo_Electronico()).append("\n");
        mensaje.append("Contraseña: ").append(usuario.getContraseña()).append("\n");
        mensaje.append("Dirección: ").append(usuario.getDireccion()).append("\n"); //YA AQUI CAMBIE ESTO Y AGREGUE UN MODELO DIRECCION PERO SI FUNCIONA
        mensaje.append("Teléfono: ").append(usuario.getTelefono()).append("\n");
        mensaje.append("Fecha de Registro: ").append(usuario.getFecha_Registro()).append("\n");
        mensaje.append("Fecha de Nacimiento: ").append(usuario.getFecha_Nacimiento()).append("\n");
        mensaje.append("URL de Imagen: ").append(usuario.getImagenURL()).append("\n");

        JOptionPane.showMessageDialog(this, mensaje.toString(), "Datos del Usuario", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
    
    Conexion conexion = Conexion.getInstance();
    Usuario usuario = new Usuario();
    MostrarDatosUsuarioFrame frame = new MostrarDatosUsuarioFrame(usuario, conexion.getConexion());
    frame.setVisible(true);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
