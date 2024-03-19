
package Vista;

import javax.swing.*;
import java.awt.event.*;

public class Registrarse extends javax.swing.JFrame { //SOLO PARA VER EL DISEÑOO LUEGO LE MUEVO COSILLAS
    
    private JLabel lblNombreCompleto;
    private JLabel lblCorreoElectronico;
    private JLabel lblContraseña;
    private JLabel lblDireccionEnvio;
    private JLabel lblNumeroTelefono;
    private JLabel lblFechaNacimiento;
    private JTextField txtNombreCompleto;
    private JTextField txtCorreoElectronico;
    private JTextField txtContraseña;
    private JTextField txtDireccionEnvio;
    private JTextField txtNumeroTelefono;
    private JTextField txtFechaNacimiento;
    private JButton btnRegistrarse;
    private JButton btnRegresar;

    
    public Registrarse() {
        initComponents();
        initMyComponents();
    }
    
  private void initMyComponents() {
        setTitle("Registrarse");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblNombreCompleto = new JLabel("Nombre Completo:");
        lblCorreoElectronico = new JLabel("Correo Electrónico:");
        lblContraseña = new JLabel("Contraseña:");
        lblDireccionEnvio = new JLabel("Dirección de Envío:");
        lblNumeroTelefono = new JLabel("Número de Teléfono:");
        lblFechaNacimiento = new JLabel("Fecha de Nacimiento:");

        txtNombreCompleto = new JTextField(20);
        txtCorreoElectronico = new JTextField(20);
        txtContraseña = new JTextField(20);
        txtDireccionEnvio = new JTextField(20);
        txtNumeroTelefono = new JTextField(20);
        txtFechaNacimiento = new JTextField(20);

        btnRegistrarse = new JButton("Registrarse");
        btnRegresar = new JButton("Regresar");

        btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (todosCamposCompletos()) {
                    mostrarMensaje("Registro exitoso");
                    // Redirigir al nuevo frame
                    abrirNuevoFrame();
                } else {
                    mostrarMensaje("Error: Por favor complete todos los campos");
                }
            }
        });

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana actual
                dispose();
                // Crear e inicializar el nuevo frame
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
                    .addComponent(lblNombreCompleto)
                    .addComponent(lblCorreoElectronico)
                    .addComponent(lblContraseña)
                    .addComponent(lblDireccionEnvio)
                    .addComponent(lblNumeroTelefono)
                    .addComponent(lblFechaNacimiento))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreCompleto)
                    .addComponent(txtCorreoElectronico)
                    .addComponent(txtContraseña)
                    .addComponent(txtDireccionEnvio)
                    .addComponent(txtNumeroTelefono)
                    .addComponent(txtFechaNacimiento)
                    .addComponent(btnRegistrarse, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegresar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreCompleto)
                    .addComponent(txtNombreCompleto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(lblDireccionEnvio)
                    .addComponent(txtDireccionEnvio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroTelefono)
                    .addComponent(txtNumeroTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaNacimiento)
                    .addComponent(txtFechaNacimiento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
        return !txtNombreCompleto.getText().isEmpty()
                && !txtCorreoElectronico.getText().isEmpty()
                && !txtContraseña.getText().isEmpty()
                && !txtDireccionEnvio.getText().isEmpty()
                && !txtNumeroTelefono.getText().isEmpty()
                && !txtFechaNacimiento.getText().isEmpty();
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    
    private void abrirNuevoFrame() {
        // Cerrar la ventana actual
        dispose();
        // Crear e inicializar el nuevo frame
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
