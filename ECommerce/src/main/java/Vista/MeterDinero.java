
package Vista;

import Controladores.ControladorUsuario;
import Modelo.SesionActiva;
import Modelo.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


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
    private JButton regresarButton;

    
    private Usuario usuario; 
        
    public MeterDinero(Usuario usuario) {
        this.usuario = usuario; 
        initMyComponents();
    }
    
    
    private void initMyComponents() {
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
    regresarButton = new JButton();


    instruccionesLabel.setText("Seleccione el tipo de tarjeta y complete los datos:");
    instruccionesLabel.setFont(new Font("Arial", Font.BOLD, 18));
    instruccionesLabel.setHorizontalAlignment(SwingConstants.CENTER);
    instruccionesLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
    
    tipoTarjetaLabel.setText("Tipo de tarjeta:");
    tipoTarjetaLabel.setFont(new Font("Arial", Font.BOLD, 14));
    tipoTarjetaLabel.setForeground(new Color(51, 102, 255)); 
    
    // Crear un modelo de ComboBox personalizado
    DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(new String[]{"Crédito", "Débito"});

    tipoTarjetaComboBox.setModel(comboBoxModel);

    tipoTarjetaComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    tipoTarjetaComboBox.setBackground(Color.WHITE);
    tipoTarjetaComboBox.setForeground(Color.BLACK);
    tipoTarjetaComboBox.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

    numeroLabel.setText("Número de tarjeta:");
    numeroLabel.setFont(new Font("Arial", Font.BOLD, 14));
    numeroLabel.setForeground(new Color(51, 102, 255)); 
    
    fechaVencimientoLabel.setText("Fecha de vencimiento:");
    fechaVencimientoLabel.setFont(new Font("Arial", Font.BOLD, 14));
    fechaVencimientoLabel.setForeground(new Color(51, 102, 255)); 

    cvvLabel.setText("CVV:");
    cvvLabel.setFont(new Font("Arial", Font.BOLD, 14));
    cvvLabel.setForeground(new Color(51, 102, 255)); 

    confirmarButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
    confirmarButton.setText("Confirmar");
    confirmarButton.setBackground(new Color(51, 102, 255));
    confirmarButton.setForeground(Color.WHITE);
    confirmarButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)),
            BorderFactory.createEmptyBorder(15, 50, 15, 50)
    ));
    confirmarButton.setFocusPainted(false);
    
    regresarButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
    regresarButton.setText("Regresar");
    regresarButton.setBackground(new Color(102, 102, 102));
    regresarButton.setForeground(Color.WHITE);
    regresarButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)),
            BorderFactory.createEmptyBorder(15, 50, 15, 50)
    ));
    regresarButton.setFocusPainted(false);
    
    confirmarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarButtonActionPerformed(evt);
            }
        });

regresarButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }
});
    
    confirmarButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            confirmarButton.setBackground(new Color(41, 81, 255));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            confirmarButton.setBackground(new Color(51, 102, 255)); 
        }
    });

    regresarButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            regresarButton.setBackground(new Color(82, 82, 82)); 
        }

        @Override
        public void mouseExited(MouseEvent e) {
            regresarButton.setBackground(new Color(102, 102, 102)); 
        }
    });
    
    fechaVencimientoTextField.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            fechaVencimientoTextField.setBackground(new Color(240, 240, 240)); 
        }

        @Override
        public void mouseExited(MouseEvent e) {
            fechaVencimientoTextField.setBackground(Color.WHITE); 
        }
    });

    cvvTextField.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            cvvTextField.setBackground(new Color(240, 240, 240)); 
        }

        @Override
        public void mouseExited(MouseEvent e) {
            cvvTextField.setBackground(Color.WHITE); 
        }
    });

    numeroTextField.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            numeroTextField.setBackground(new Color(240, 240, 240));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            numeroTextField.setBackground(Color.WHITE); 
        }
    });

    setSize(600, 600);

    JPanel mainPanel = new JPanel(new BorderLayout());

    JPanel datosPanel = new JPanel();
    datosPanel.setLayout(new GridLayout(4, 2, 10, 10));
    datosPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // Añadir los componentes al panel de datos
    datosPanel.add(tipoTarjetaLabel);
    datosPanel.add(tipoTarjetaComboBox);
    datosPanel.add(numeroLabel);
    datosPanel.add(numeroTextField);
    datosPanel.add(fechaVencimientoLabel);
    datosPanel.add(fechaVencimientoTextField);
    datosPanel.add(cvvLabel);
    datosPanel.add(cvvTextField);

    // Panel para los botones
    JPanel botonesPanel = new JPanel();
    botonesPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    botonesPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
    botonesPanel.add(confirmarButton);
    botonesPanel.add(regresarButton);


    // Establecer el diseño del panel principal
    mainPanel.add(instruccionesLabel, BorderLayout.NORTH);
    mainPanel.add(datosPanel, BorderLayout.CENTER);
    mainPanel.add(botonesPanel, BorderLayout.SOUTH);

    getContentPane().add(mainPanel);
    setLocationRelativeTo(null);
    setVisible(true);
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
