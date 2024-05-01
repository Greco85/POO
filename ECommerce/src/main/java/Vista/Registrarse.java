
package Vista;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.awt.event.*;
import Modelo.Usuario;
import java.util.Date;
import java.text.ParseException;
import Controladores.ControladorUsuario;
import SQL.Conexion;
import java.sql.Connection;


public class Registrarse extends javax.swing.JFrame {
    private JDateChooser dateChooser;

    private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblCorreoElectronico;
    private JLabel lblContraseña;
    private JLabel lblNumeroTelefono;
    private JLabel lblFechaNacimiento;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCorreoElectronico;
    private JTextField txtContraseña;
    private JTextField txtNumeroTelefono;
    private JButton btnRegistrarse;
    private JButton btnRegresar;

    public Registrarse() {
        initComponents();
        initMyComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    private void initMyComponents() {
        setTitle("Registrarse");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblNombre = new JLabel("Nombre:");
        lblApellido = new JLabel("Apellido:");
        lblCorreoElectronico = new JLabel("Correo Electrónico:");
        lblContraseña = new JLabel("Contraseña:");
        lblNumeroTelefono = new JLabel("Teléfono:");
        lblFechaNacimiento = new JLabel("Fecha de Nacimiento:");

        txtNombre = new JTextField(20);
        txtApellido = new JTextField(20);
        txtCorreoElectronico = new JTextField(20);
        txtContraseña = new JTextField(20);
        txtNumeroTelefono = new JTextField(20);
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");

        btnRegistrarse = new JButton("Registrarse");
        btnRegresar = new JButton("Regresar");

        btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (todosCamposCompletos()) {
                    Usuario nuevoUsuario = obtenerDatosUsuario();
                    mostrarMensaje("Registro exitoso");
                    abrirNuevoFrame();
                } else {
                    mostrarMensaje("Error: Por favor complete todos los campos");
                }
            }
        });

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GUI_MENU_P menuFrame = new GUI_MENU_P();
                menuFrame.setVisible(true);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre)
                    .addComponent(lblApellido)
                    .addComponent(lblCorreoElectronico)
                    .addComponent(lblContraseña)
                    .addComponent(lblNumeroTelefono)
                    .addComponent(lblFechaNacimiento))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre)
                    .addComponent(txtApellido)
                    .addComponent(txtCorreoElectronico)
                    .addComponent(txtContraseña)
                    .addComponent(txtNumeroTelefono)
                    .addComponent(dateChooser)
                    .addComponent(btnRegistrarse, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegresar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido)
                    .addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorreoElectronico)
                    .addComponent(txtCorreoElectronico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContraseña)
                    .addComponent(txtContraseña, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroTelefono)
                    .addComponent(txtNumeroTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaNacimiento)
                    .addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegistrarse)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegresar)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }
 
    private boolean todosCamposCompletos() {
    return !txtNombre.getText().isEmpty()
            && !txtApellido.getText().isEmpty()
            && !txtCorreoElectronico.getText().isEmpty()
            && !txtContraseña.getText().isEmpty()
            && !txtNumeroTelefono.getText().isEmpty()
            && dateChooser.getDate() != null;
}

private Usuario obtenerDatosUsuario() {
    String nombre = txtNombre.getText();
    String apellido = txtApellido.getText();
    String correo = txtCorreoElectronico.getText();
    String contraseña = txtContraseña.getText();
    String telefono = txtNumeroTelefono.getText();
    java.util.Date fechaNacimiento = dateChooser.getDate();
    
    // Obtener la fecha actual y formatearla como "yyyy-MM-dd"
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String fechaRegistroFormateada = dateFormat.format(new java.util.Date());

    // Formatear la fecha de nacimiento si está presente
    String fechaNacimientoFormateada = null;
    if (fechaNacimiento != null) {
        fechaNacimientoFormateada = dateFormat.format(fechaNacimiento);
    }

    // Establecer las fechas en los atributos de Usuario
    Date fechaNacimientoDate = null;
    Date fechaRegistroDate = null;

    try {
        fechaNacimientoDate = dateFormat.parse(fechaNacimientoFormateada);
        fechaRegistroDate = dateFormat.parse(fechaRegistroFormateada);
    } catch (ParseException e) {
        e.printStackTrace(); // Manejo de errores de análisis de fechas
    }

    // Establecer la URL de la imagen por defecto
    String imagenURL = "img/ejemplo"; //Luego veo q pongo

    Connection conexion = Conexion.getInstance().getConexion();
    ControladorUsuario controladorUsuario = new ControladorUsuario(conexion);

    
    // Crear un nuevo usuario
    Usuario nuevoUsuario = new Usuario(correo, contraseña, nombre);
    nuevoUsuario.setApellido(apellido);
    nuevoUsuario.setTelefono(telefono);
    nuevoUsuario.setFecha_Nacimiento(fechaNacimientoDate);
    nuevoUsuario.setFecha_Registro(fechaRegistroDate);
    nuevoUsuario.setImagenURL(imagenURL);

    // Guardar el usuario en la base de datos usando el controlador
    controladorUsuario.RegistrarUsuario(nuevoUsuario);

    // Imprimir los datos obtenidos
    System.out.println("Datos del usuario:");
    System.out.println("Nombre: " + nombre);
    System.out.println("Apellido: " + apellido);
    System.out.println("Correo electrónico: " + correo);
    System.out.println("Contraseña: " + contraseña);
    System.out.println("Teléfono: " + telefono);
    System.out.println("Fecha de nacimiento: " + fechaNacimientoFormateada);
    System.out.println("Fecha de registro: " + fechaRegistroFormateada);
    System.out.println("URL de imagen: " + imagenURL);

    return nuevoUsuario;
}



private void mostrarMensaje(String mensaje) {
    JOptionPane.showMessageDialog(this, mensaje);
}

    
    private void abrirNuevoFrame() {
        dispose();
        GUI_MENU_P menuFrame = new GUI_MENU_P();
        menuFrame.setVisible(true);
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
            java.util.logging.Logger.getLogger(Registrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registrarse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
