
package Vista;

import Controladores.ControladorEnvio;
import Controladores.ControladorUsuario;
import Modelo.Direccion;
import Modelo.SesionActiva;
import Modelo.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class PantallaDeCompra extends javax.swing.JFrame {
    
    
     private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblCorreoElectronico;
    private JLabel lblTelefono;
    private JLabel lblCalle;
    private JLabel lblNumeroCasa;
    private JLabel lblColonia;
    private JLabel lblCodigoPostal;
    private JLabel lblCiudad;
    private JLabel lblPais;
    private JLabel lblMetodoPago;
    private JLabel lblTipoEnvio;
    private JLabel lblTotal;
    private JLabel lblNumeroTarjeta;
    private JLabel lblFechaVencimiento;
    private JLabel lblCVV;

    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCorreoElectronico;
    private JTextField txtTelefono;
    private JTextField txtCalle;
    private JTextField txtNumeroCasa;
    private JTextField txtColonia;
    private JTextField txtCodigoPostal;
    private JTextField txtCiudad;
    private JTextField txtPais;
    private JTextField txtTotal;
    private JTextField txtNumeroTarjeta;
    private JTextField txtFechaVencimiento;
    private JTextField txtCVV;

    private JComboBox<String> cbMetodoPago;
    private JComboBox<String> cbTipoEnvio;

    private JButton btnRealizarCompra;
    
    private Usuario usuario;
    private Connection conexion;
        private ControladorUsuario controladorUsuario;

  
    public PantallaDeCompra(Usuario usuario, Connection conexion) {
        this.usuario = usuario;
        this.conexion = conexion;
        this.controladorUsuario = new ControladorUsuario(conexion);
         cargarDatosUsuario();
        initmyComponents();
        setTitle("Realizar Compra");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
    }
    
private void initmyComponents() {
    lblNombre = new JLabel("Nombre:");
    lblApellido = new JLabel("Apellido:");
    lblCorreoElectronico = new JLabel("Correo Electrónico:");
    lblTelefono = new JLabel("Teléfono:");
    lblCalle = new JLabel("Calle:");
    lblNumeroCasa = new JLabel("Número de Casa:");
    lblColonia = new JLabel("Colonia:");
    lblCodigoPostal = new JLabel("Código Postal:");
    lblCiudad = new JLabel("Ciudad:");
    lblPais = new JLabel("País:");
    lblMetodoPago = new JLabel("Método de Pago:");
    lblTipoEnvio = new JLabel("Tipo de Envío:");
    lblTotal = new JLabel("Total:");
    lblNumeroTarjeta = new JLabel("Número de Tarjeta:");
    lblFechaVencimiento = new JLabel("Fecha de Vencimiento:");
    lblCVV = new JLabel("CVV:");

    txtNombre = new JTextField(20);
    txtNombre.setText(usuario.getNombre());
    txtNombre.setEditable(false);

    txtApellido = new JTextField(20);
    txtApellido.setText(usuario.getApellido());
    txtApellido.setEditable(false);

    txtCorreoElectronico = new JTextField(20);
    txtCorreoElectronico.setText(usuario.getCorreo_Electronico());
    txtCorreoElectronico.setEditable(false);

    txtTelefono = new JTextField(20);
    txtTelefono.setText(usuario.getTelefono());
    txtTelefono.setEditable(false);

    Direccion direccion = usuario.getDireccion();

    txtCalle = new JTextField(20);
    txtCalle.setText(direccion.getCalle());
    txtCalle.setEditable(false);

    txtNumeroCasa = new JTextField(20);
    txtNumeroCasa.setText(direccion.getNumeroCasa());
    txtNumeroCasa.setEditable(false);

    txtColonia = new JTextField(20);
    txtColonia.setText(direccion.getColonia());
    txtColonia.setEditable(false);

    txtCodigoPostal = new JTextField(20);
    txtCodigoPostal.setText(direccion.getCodigoPostal());
    txtCodigoPostal.setEditable(false);

    txtCiudad = new JTextField(20);
    txtCiudad.setText(direccion.getCiudad());
    txtCiudad.setEditable(false);

    txtPais = new JTextField(20);
    txtPais.setText(direccion.getPais());
    txtPais.setEditable(false);

    txtTotal = new JTextField(20);
    txtTotal.setText(String.valueOf(SesionActiva.getTotalAPagar())); 
    txtTotal.setEditable(false);

    txtNumeroTarjeta = new JTextField(20);
    txtFechaVencimiento = new JTextField(20);
    txtCVV = new JTextField(20);

    lblNumeroTarjeta.setVisible(false);
    txtNumeroTarjeta.setVisible(false);
    lblFechaVencimiento.setVisible(false);
    txtFechaVencimiento.setVisible(false);
    lblCVV.setVisible(false);
    txtCVV.setVisible(false);

    cbMetodoPago = new JComboBox<>(new String[]{"Efectivo", "Tarjeta de Crédito", "Tarjeta de Débito"});
    cbMetodoPago.setEditable(false);
    
    ControladorEnvio controladorEnvio = new ControladorEnvio();
    List<String> tiposEnvioList = controladorEnvio.obtenerTiposEnvio();


    String[] tiposEnvioArray = tiposEnvioList.toArray(new String[0]);

    cbTipoEnvio = new JComboBox<>(tiposEnvioArray);
    cbTipoEnvio.setEditable(false);
    
    
    btnRealizarCompra = new JButton("Realizar Compra");

    btnRealizarCompra.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            realizarCompra();
        }
    });
    
    cbMetodoPago.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String metodoPagoSeleccionado = (String) cbMetodoPago.getSelectedItem();
            if (metodoPagoSeleccionado.equals("Tarjeta de Crédito") || metodoPagoSeleccionado.equals("Tarjeta de Débito")) {
                lblNumeroTarjeta.setVisible(true);
                txtNumeroTarjeta.setVisible(true);
                lblFechaVencimiento.setVisible(true);
                txtFechaVencimiento.setVisible(true);
                lblCVV.setVisible(true);
                txtCVV.setVisible(true);
            } else {
                lblNumeroTarjeta.setVisible(false);
                txtNumeroTarjeta.setVisible(false);
                lblFechaVencimiento.setVisible(false);
                txtFechaVencimiento.setVisible(false);
                lblCVV.setVisible(false);
                txtCVV.setVisible(false);
            }
        }
    });

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);

    layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre)
                    .addComponent(lblApellido)
                    .addComponent(lblCorreoElectronico)
                    .addComponent(lblTelefono)
                    .addComponent(lblCalle)
                    .addComponent(lblNumeroCasa)
                    .addComponent(lblColonia)
                    .addComponent(lblCodigoPostal)
                    .addComponent(lblCiudad)
                    .addComponent(lblPais)
                    .addComponent(lblMetodoPago)
                    .addComponent(lblTipoEnvio)
                    .addComponent(lblTotal)
                    .addComponent(lblNumeroTarjeta)
                    .addComponent(lblFechaVencimiento)
                    .addComponent(lblCVV))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre)
                    .addComponent(txtApellido)
                    .addComponent(txtCorreoElectronico)
                    .addComponent(txtTelefono)
                    .addComponent(txtCalle)
                    .addComponent(txtNumeroCasa)
                    .addComponent(txtColonia)
                    .addComponent(txtCodigoPostal)
                    .addComponent(txtCiudad)
                    .addComponent(txtPais)
                    .addComponent(cbMetodoPago)
                    .addComponent(cbTipoEnvio)
                    .addComponent(txtTotal)
                    .addComponent(txtNumeroTarjeta)
                    .addComponent(txtFechaVencimiento)
                    .addComponent(txtCVV)
                    .addComponent(btnRealizarCompra))
    );

    layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido)
                    .addComponent(txtApellido))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorreoElectronico)
                    .addComponent(txtCorreoElectronico))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono)
                    .addComponent(txtTelefono))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCalle)
                    .addComponent(txtCalle))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroCasa)
                    .addComponent(txtNumeroCasa))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblColonia)
                    .addComponent(txtColonia))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigoPostal)
                    .addComponent(txtCodigoPostal))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCiudad)
                    .addComponent(txtCiudad))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPais)
                    .addComponent(txtPais))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMetodoPago)
                    .addComponent(cbMetodoPago))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoEnvio)
                    .addComponent(cbTipoEnvio))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(txtTotal))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroTarjeta)
                    .addComponent(txtNumeroTarjeta))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaVencimiento)
                    .addComponent(txtFechaVencimiento))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCVV)
                    .addComponent(txtCVV))
            .addComponent(btnRealizarCompra)
    );
}


    private void realizarCompra() {
        JOptionPane.showMessageDialog(this, "Compra realizada con éxito", "Compra Realizada", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void cargarDatosUsuario() {
        int ID_Usuario = SesionActiva.getID_Usuario();
        usuario = controladorUsuario.obtenerUsuarioPorId(ID_Usuario);
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
            java.util.logging.Logger.getLogger(PantallaDeCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaDeCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaDeCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaDeCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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