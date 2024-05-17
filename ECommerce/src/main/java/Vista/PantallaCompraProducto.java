
package Vista;

import Controladores.ControladorCarrito;
import Controladores.ControladorEnvio;
import Controladores.ControladorNotificacion;
import Controladores.ControladorPedido;
import Controladores.ControladorProducto;
import Controladores.ControladorUsuario;
import Modelo.Direccion;
import Modelo.SesionActiva;
import Modelo.Usuario;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class PantallaCompraProducto extends javax.swing.JFrame {

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
        private JButton btnRegresar;

    
    private Usuario usuario;
    private Connection conexion;
    private ControladorUsuario controladorUsuario;
    
        private ControladorNotificacion controladorNotificacion;

    
    
    public PantallaCompraProducto(Usuario usuario, Connection conexion, int ID_Producto, double TotalaPagar, int Cantidad) {
        this.usuario = usuario;
        this.conexion = conexion;
        this.controladorUsuario = new ControladorUsuario(conexion);
        this.controladorNotificacion = new ControladorNotificacion();

        cargarDatosUsuario();
        initmyComponents(ID_Producto,TotalaPagar,Cantidad);
        setTitle("Realizar Compra");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);
    }
    
    private void initmyComponents(int ID_Producto, double TotalaPagar, int Cantidad) {
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
    
    Font labelFont = new Font("Quicksand Medium", Font.BOLD, 14);
    Color labelForeground = Color.BLACK;
    
    
    JLabel[] labels = {lblNombre, lblApellido, lblCorreoElectronico, lblTelefono, lblCalle, lblNumeroCasa,
                       lblColonia, lblCodigoPostal, lblCiudad, lblPais, lblMetodoPago, lblTipoEnvio, lblTotal,
                       lblNumeroTarjeta, lblFechaVencimiento, lblCVV};

    for (JLabel label : labels) {
        label.setFont(labelFont);
        label.setForeground(labelForeground);
        label.setOpaque(true);
    }
    
   

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
    
     Font textFieldFont = new Font("Arial", Font.PLAIN, 14);
Color textFieldForeground = Color.BLACK;
Color textFieldBackground = Color.WHITE;
Color disabledColor = Color.LIGHT_GRAY;

JTextField[] textFields = {txtNombre, txtApellido, txtCorreoElectronico, txtTelefono, txtCalle, txtNumeroCasa,
                           txtColonia, txtCodigoPostal, txtCiudad, txtPais, txtTotal, txtNumeroTarjeta,
                           txtFechaVencimiento, txtCVV};

for (JTextField textField : textFields) {
    textField.setFont(textFieldFont);
    textField.setForeground(textFieldForeground);
    textField.setBackground(textFieldBackground);
    textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));
    textField.setDisabledTextColor(disabledColor); 
}
    
    
    

    lblNumeroTarjeta.setVisible(false);
    txtNumeroTarjeta.setVisible(false);
    lblFechaVencimiento.setVisible(false);
    txtFechaVencimiento.setVisible(false);
    lblCVV.setVisible(false);
    txtCVV.setVisible(false);
    
    // Diseño para los JLabels
    labelFont = new Font("Arial", Font.BOLD, 14);
    labelForeground = Color.BLACK;

    lblNumeroTarjeta.setFont(labelFont);
    lblNumeroTarjeta.setForeground(labelForeground);

    lblFechaVencimiento.setFont(labelFont);
    lblFechaVencimiento.setForeground(labelForeground);

    lblCVV.setFont(labelFont);
    lblCVV.setForeground(labelForeground);

    // Diseño para los JTextFields
    textFieldFont = new Font("Arial", Font.PLAIN, 14);
    textFieldForeground = Color.BLACK;
    disabledColor = Color.LIGHT_GRAY;

    txtNumeroTarjeta.setFont(textFieldFont);
    txtNumeroTarjeta.setForeground(textFieldForeground);
    txtNumeroTarjeta.setBackground(textFieldBackground);
    txtNumeroTarjeta.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));
    txtNumeroTarjeta.setDisabledTextColor(disabledColor); 

    txtFechaVencimiento.setFont(textFieldFont);
    txtFechaVencimiento.setForeground(textFieldForeground);
    txtFechaVencimiento.setBackground(textFieldBackground);
    txtFechaVencimiento.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));
    txtFechaVencimiento.setDisabledTextColor(disabledColor);

    txtCVV.setFont(textFieldFont);
    txtCVV.setForeground(textFieldForeground);
    txtCVV.setBackground(textFieldBackground);
    txtCVV.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));
    txtCVV.setDisabledTextColor(disabledColor);

    cbMetodoPago = new JComboBox<>(new String[]{"Efectivo", "Tarjeta de Crédito", "Tarjeta de Débito"});
    cbMetodoPago.setEditable(false);
    
    ControladorEnvio controladorEnvio = new ControladorEnvio();
    List<String> tiposEnvioList = controladorEnvio.obtenerTiposEnvio();


    String[] tiposEnvioArray = tiposEnvioList.toArray(new String[0]);

    cbTipoEnvio = new JComboBox<>(tiposEnvioArray);
    cbTipoEnvio.setEditable(false);
    
    
    Font comboBoxFont = new Font("Arial", Font.PLAIN, 14);
    Color comboBoxForeground = Color.BLACK;
    Color comboBoxBackground = Color.WHITE;

    cbMetodoPago.setFont(comboBoxFont);
    cbMetodoPago.setForeground(comboBoxForeground);
    cbMetodoPago.setBackground(comboBoxBackground);
    cbMetodoPago.setPreferredSize(new Dimension(300, 20)); // Ancho y alto

    cbTipoEnvio.setFont(comboBoxFont);
    cbTipoEnvio.setForeground(comboBoxForeground);
    cbTipoEnvio.setBackground(comboBoxBackground);
    cbTipoEnvio.setPreferredSize(new Dimension(300, 20)); // Ancho y alto
    
    btnRealizarCompra = new JButton("Realizar Compra");
btnRealizarCompra.setFont(new Font("Arial", Font.PLAIN, 18));
    btnRealizarCompra.setBackground(new Color(41, 81, 204)); 
        btnRealizarCompra.setForeground(Color.WHITE);
        btnRealizarCompra.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)), // Borde blanco
            BorderFactory.createEmptyBorder(10, 20, 10, 20) 
        ));
    btnRealizarCompra.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            realizarCompra( ID_Producto,  TotalaPagar,  Cantidad);
        }
    });
    
   btnRegresar = new JButton("Regresar");
        btnRegresar.setFont(new Font("Arial", Font.PLAIN, 18));
        btnRegresar.setBackground(new Color(102, 102, 102)); 
            btnRegresar.setForeground(Color.WHITE);
            btnRegresar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)), // Borde blanco
                BorderFactory.createEmptyBorder(10, 20, 10, 20) 
            ));
    btnRegresar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    });
    
    btnRealizarCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRealizarCompra.setBackground(new Color(41, 81, 204));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRealizarCompra.setBackground(new Color(51, 102, 255)); 
            }
        });

        btnRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegresar.setBackground(new Color(82, 82, 82));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegresar.setBackground(new Color(102, 102, 102));
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
        .addComponent(lblCVV)
        .addComponent(btnRegresar)) // Agregamos el botón btnRegresar al grupo
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
        .addComponent(btnRealizarCompra)
        .addComponent(btnRegresar)
    )
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
        .addComponent(btnRegresar) // Agregamos el botón btnRegresar al layout
);
}

    private void realizarCompra(int ID_Producto, double TotalaPagar, int Cantidad) {
    if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() ||
        txtCorreoElectronico.getText().isEmpty() || txtTelefono.getText().isEmpty() ||
        txtCalle.getText().isEmpty() || txtNumeroCasa.getText().isEmpty() ||
        txtColonia.getText().isEmpty() || txtCodigoPostal.getText().isEmpty() ||
        txtCiudad.getText().isEmpty() || txtPais.getText().isEmpty() ||
        txtTotal.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Campos Incompletos", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    
    String metodoPago = (String) cbMetodoPago.getSelectedItem();
        
    if (metodoPago.equals("Tarjeta de Crédito") || metodoPago.equals("Tarjeta de Débito")) {
            // Validar campos de tarjeta de crédito/debito
            if (txtNumeroTarjeta.getText().isEmpty() || txtFechaVencimiento.getText().isEmpty() || txtCVV.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete los campos de la tarjeta.", "Campos Incompletos", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    
            int ID_Usuario = SesionActiva.getID_Usuario();
        //dinero a pagar por ese carrito
    
        double totalAPagar = SesionActiva.getTotalAPagar();
        double dineroUsuario = controladorUsuario.obtenerDineroFalso(ID_Usuario);
        
        
        if (dineroUsuario < totalAPagar) {
            // Si el usuario no tiene suficiente dinero, mostrar una alerta
            double dineroDisponible = dineroUsuario;
            JOptionPane.showMessageDialog(this, "No tiene suficiente dinero para pagar. Dinero disponible: $" + dineroDisponible, "Alerta", JOptionPane.WARNING_MESSAGE);
        } else {
            
            
            //Solo es para darle dinero de prueba NO ES UNA BUENA PRACTICA PERO SOY ESTUDIANTE
    
        String tipoEnvioSeleccionado = (String) cbTipoEnvio.getSelectedItem(); // Obtener el tipo de envío seleccionado
        
        
        Date fechaActual = new Date();
        Timestamp fechaYHoraActual = new Timestamp(fechaActual.getTime());
        
        String EstadoEnvio = "Pendiente";
        
        ControladorPedido controladorPedido = new ControladorPedido();
        int ID_EstadoPedido = controladorPedido.obtenerIdEstadoPedido(EstadoEnvio);
        
        Direccion direccionUsuario = usuario.getDireccion();

        String calle = txtCalle.getText();
        String numeroCasa = txtNumeroCasa.getText();
        String colonia = txtColonia.getText();
        String codigoPostal = txtCodigoPostal.getText();
        String ciudad = txtCiudad.getText();
        String pais = txtPais.getText();
        
        String direccion = "";
        
        double DineroTotalCompra = 0;

        direccion = calle + ", " + numeroCasa + ", " + colonia + ", " + codigoPostal + ", " + ciudad + ", " + pais;


        ControladorEnvio controladorEnvio = new ControladorEnvio();
        
        ControladorProducto controladorProducto = new ControladorProducto();

        int ID_MetodoEnvio = controladorEnvio.obtenerIdMetodoEnvioPorNombre(tipoEnvioSeleccionado);

        if (ID_MetodoEnvio != -1) {
            System.out.println("ID del método de envío seleccionado: " + ID_MetodoEnvio);
        } else {
            System.out.println("No se pudo obtener el ID del método de envío seleccionado.");
        }
        
       
            DineroTotalCompra = DineroTotalCompra + TotalaPagar;
            // Realizar la inserción del pedido con los valores obtenidos
        boolean insercionExitosa = controladorPedido.insertarPedido(
                ID_Usuario,
                ID_EstadoPedido,
                ID_MetodoEnvio,
                direccion,
                ID_Producto,
                Cantidad,
                TotalaPagar,
                fechaYHoraActual
        );

    if (insercionExitosa) {
        System.out.println("Pedido insertado correctamente.");
        
        String NombreProducto = controladorProducto.obtenerNombreProductoporID(ID_Producto);
        // BAJAR LA CANTIDAD DEL PRODUCTO ORIGINAL CON OTRA CONSULTA A LA DB
        controladorProducto.ActualizarCantidad(NombreProducto,  ID_Producto, Cantidad);
        
        
        int ID_UsuarioVendedor = controladorProducto.obtenerID_UsuarioPorID_Producto(ID_Producto);
        
        controladorUsuario.transferirDinero( ID_Usuario,  ID_UsuarioVendedor, TotalaPagar);
        
        
        System.out.println("hola papu");
        
        
        //NOTIFICACION
        
        
        int ID_TipoNoti = 2; //LUEGO BUSCARLA CON UNA CONSULTA
        
                
        String nombreUsuario = controladorUsuario.ObtenerNombreporID(SesionActiva.getID_Usuario());
                
                
         String mensajee = "El usuario: " + nombreUsuario + " Te ha comprado un producto, prepáralo para la entrega a paquetería";
                
         
        // Llamada a la función crearNotificacion
        controladorNotificacion.crearNotificacion(ID_UsuarioVendedor, ID_TipoNoti, mensajee, fechaYHoraActual);


        
        JOptionPane.showMessageDialog(this, "Compra realizada con éxito", "Compra Realizada", JOptionPane.INFORMATION_MESSAGE);


    } else {
        System.out.println("Error al insertar el pedido.");
    }
        }
            
        
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
            java.util.logging.Logger.getLogger(PantallaCompraProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaCompraProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaCompraProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaCompraProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
