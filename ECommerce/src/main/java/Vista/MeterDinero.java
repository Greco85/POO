
package Vista;

import Controladores.ControladorProducto;
import Controladores.ControladorUsuario;
import Modelo.SesionActiva;
import Modelo.Usuario;
import java.sql.Connection;
    import javax.swing.JButton;
    import javax.swing.JComboBox;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.JOptionPane;
    import javax.swing.JTextField;


public class MeterDinero extends javax.swing.JFrame {
    
    
    private Connection conexion;
    
    private JLabel instruccionesLabel;
    private JLabel tipoTarjetaLabel;
    private JComboBox<String> tipoTarjetaComboBox;
    private JLabel numeroLabel;
    private JTextField numeroTextField;
    private JLabel fechaVencimientoLabel;
    private JTextField fechaVencimientoTextField;
    private JLabel cvvLabel;
    private JTextField cvvTextField;
    private JButton confirmarButton;
    
    private Usuario usuario; 
        
    public MeterDinero(Usuario usuario) {
        
        this.usuario = usuario; 
        initmyComponents();
    }
    
    
    private void initmyComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        instruccionesLabel = new JLabel();
        tipoTarjetaLabel = new JLabel();
        tipoTarjetaComboBox = new JComboBox<>();
        numeroLabel = new JLabel();
        numeroTextField = new JTextField();
        fechaVencimientoLabel = new JLabel();
        fechaVencimientoTextField = new JTextField();
        cvvLabel = new JLabel();
        cvvTextField = new JTextField();
        confirmarButton = new JButton();

        instruccionesLabel.setText("Seleccione el tipo de tarjeta y complete los datos:");

        tipoTarjetaLabel.setText("Tipo de tarjeta:");

        tipoTarjetaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Crédito", "Débito" }));

        numeroLabel.setText("Número de tarjeta:");

        fechaVencimientoLabel.setText("Fecha de vencimiento:");

        cvvLabel.setText("CVV:");

        confirmarButton.setText("Confirmar");
        confirmarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(instruccionesLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(numeroLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tipoTarjetaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fechaVencimientoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cvvLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tipoTarjetaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numeroTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaVencimientoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cvvTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(confirmarButton)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(instruccionesLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipoTarjetaLabel)
                    .addComponent(tipoTarjetaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeroLabel)
                    .addComponent(numeroTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fechaVencimientoLabel)
                    .addComponent(fechaVencimientoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cvvLabel)
                    .addComponent(cvvTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(confirmarButton)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }                      

    private void confirmarButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        String tipoTarjeta = (String) tipoTarjetaComboBox.getSelectedItem();
        String numeroTarjeta = numeroTextField.getText();
        String fechaVencimiento = fechaVencimientoTextField.getText();
        String cvv = cvvTextField.getText();
        
        if (tipoTarjeta.isEmpty() || numeroTarjeta.isEmpty() || fechaVencimiento.isEmpty() || cvv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Datos incompletos", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
       
        
        String cantidadDineroString = JOptionPane.showInputDialog(this, "Introduce la cantidad de dinero que deseas meter:");
        if (cantidadDineroString != null && !cantidadDineroString.isEmpty()) {
            try {
                double cantidadDinero = Double.parseDouble(cantidadDineroString);
                int ID_Usuario = SesionActiva.getID_Usuario();
                
                ControladorUsuario controladorUsuario = new ControladorUsuario(conexion);

                controladorUsuario.verificarYActualizarDineroFalso(usuario, ID_Usuario, cantidadDinero);
                
                JOptionPane.showMessageDialog(this, "Se ha ingresado $" + cantidadDinero + " a su cuenta.", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, introduce una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Operación cancelada.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
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
            java.util.logging.Logger.getLogger(MeterDinero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MeterDinero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MeterDinero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MeterDinero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
