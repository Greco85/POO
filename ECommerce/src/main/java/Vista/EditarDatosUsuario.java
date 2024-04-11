
package Vista;

import Modelo.Usuario;
import Modelo.Direccion;
import Controladores.ControladorUsuario;
import Modelo.SesionActiva;
import SQL.Conexion;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class EditarDatosUsuario extends javax.swing.JFrame {

    private ControladorUsuario controladorUsuario;
    private JDateChooser dateChooser;

    private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblCorreoElectronico;
    private JLabel lblContraseña;
    private JLabel lblTelefono;
    private JLabel lblFechaNacimiento;
    private JLabel lblCalle;
    private JLabel lblNumeroCasa;
    private JLabel lblColonia;
    private JLabel lblCodigoPostal;
    private JLabel lblCiudad;
    private JLabel lblPais;

    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCorreoElectronico;
    private JTextField txtContraseña;
    private JTextField txtTelefono;
    private JTextField txtCalle;
    private JTextField txtNumeroCasa;
    private JTextField txtColonia;
    private JTextField txtCodigoPostal;
    private JTextField txtCiudad;
    private JTextField txtPais;

    private JButton btnActualizar;
    private JButton btnCancelar;

    private Usuario usuario;
    private Connection conexion; 

    public EditarDatosUsuario(Usuario usuario, Connection conexion) {
        this.usuario = usuario;
        this.conexion = conexion;
        this.controladorUsuario = new ControladorUsuario(conexion);
         cargarDatosUsuario();
        initComponents();
        initMyComponents();
    }

    private void cargarDatosUsuario() {
        int ID_Usuario = SesionActiva.getID_Usuario();
        usuario = controladorUsuario.obtenerUsuarioPorId(ID_Usuario);
    }

private void initMyComponents() {
        setTitle("Editar Datos de Usuario");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    lblNombre = new JLabel("Nombre:");
    lblApellido = new JLabel("Apellido:");
    lblCorreoElectronico = new JLabel("Correo Electrónico:");
    lblContraseña = new JLabel("Contraseña:");
    lblTelefono = new JLabel("Teléfono:");
    lblFechaNacimiento = new JLabel("Fecha de Nacimiento:");
    lblCalle = new JLabel("Calle:");
    lblNumeroCasa = new JLabel("Número de Casa:");
    lblColonia = new JLabel("Colonia:");
    lblCodigoPostal = new JLabel("Código Postal:");
    lblCiudad = new JLabel("Ciudad:");
    lblPais = new JLabel("País:");

    txtNombre = new JTextField(20);
    txtNombre.setText(usuario.getNombre());

    txtApellido = new JTextField(20);
    txtApellido.setText(usuario.getApellido());

    txtCorreoElectronico = new JTextField(20);
    txtCorreoElectronico.setText(usuario.getCorreo_Electronico());

    txtContraseña = new JTextField(20);
    txtContraseña.setText(usuario.getContraseña());

    txtTelefono = new JTextField(20);
    txtTelefono.setText(usuario.getTelefono());
    
    Direccion direccion = usuario.getDireccion();
    
        txtCalle = new JTextField(20);
        txtCalle.setText(direccion.getCalle());

        txtNumeroCasa = new JTextField(20);
        txtNumeroCasa.setText(direccion.getNumeroCasa());

        txtColonia = new JTextField(20);
        txtColonia.setText(direccion.getColonia());

        txtCodigoPostal = new JTextField(20);
        txtCodigoPostal.setText(direccion.getCodigoPostal());

        txtCiudad = new JTextField(20);
        txtCiudad.setText(direccion.getCiudad());

        txtPais = new JTextField(20);
        txtPais.setText(direccion.getPais());

    dateChooser = new JDateChooser();
    dateChooser.setDateFormatString("yyyy-MM-dd");

    Date FechaNacimiento = usuario.getFecha_Nacimiento();

    if (FechaNacimiento != null) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada = dateFormat.format(FechaNacimiento);

        try {
            Date fechaParseada = dateFormat.parse(fechaFormateada);
            dateChooser.setDate(fechaParseada);
        } catch (ParseException e) {
            System.err.println("Error al parsear la fecha: " + e.getMessage());
        }
    }

    btnActualizar = new JButton("Actualizar");
    btnCancelar = new JButton("Cancelar");

    btnCancelar.addActionListener(e -> {
        dispose();
        CuentaUsuario cuentaUsuario = new CuentaUsuario(usuario);
        cuentaUsuario.setVisible(true);
    });

    btnActualizar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            btnActualizarActionPerformed(e);
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
                                        .addComponent(lblTelefono)
                                        .addComponent(lblFechaNacimiento)
                                        .addComponent(lblCalle)
                                        .addComponent(lblNumeroCasa)
                                        .addComponent(lblColonia)
                                        .addComponent(lblCodigoPostal)
                                        .addComponent(lblCiudad)
                                        .addComponent(lblPais))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNombre)
                                        .addComponent(txtApellido)
                                        .addComponent(txtCorreoElectronico)
                                        .addComponent(txtContraseña)
                                        .addComponent(txtTelefono)
                                        .addComponent(dateChooser)
                                        .addComponent(txtCalle)
                                        .addComponent(txtNumeroCasa)
                                        .addComponent(txtColonia)
                                        .addComponent(txtCodigoPostal)
                                        .addComponent(txtCiudad)
                                        .addComponent(txtPais)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnActualizar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnCancelar)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblNombre)
                                        .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblApellido)
                                        .addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCorreoElectronico)
                                        .addComponent(txtCorreoElectronico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblContraseña)
                                        .addComponent(txtContraseña, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTelefono)
                                        .addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblFechaNacimiento)
                                        .addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCalle)
                                        .addComponent(txtCalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblNumeroCasa)
                                        .addComponent(txtNumeroCasa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblColonia)
                                        .addComponent(txtColonia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCodigoPostal)
                                        .addComponent(txtCodigoPostal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCiudad)
                                        .addComponent(txtCiudad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblPais)
                                        .addComponent(txtPais, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnActualizar)
                                        .addComponent(btnCancelar))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

private void btnActualizarActionPerformed(ActionEvent evt) {
    
    Conexion conexion = Conexion.getInstance(); //DEBES QUITAR ESA Y SOLO TENER UNA QN SABE COMO LE HACES PERO QUITALA DE AQUI
    if (conexion.getConexion() == null) {
        System.out.println("La conexión a la base de datos no está disponible.");
        return;
    }
    
    System.out.println("Nombre: " + txtNombre.getText());
    System.out.println("Apellido: " + txtApellido.getText());
    System.out.println("Correo Electrónico: " + txtCorreoElectronico.getText());
    System.out.println("Contraseña: " + txtContraseña.getText());
    System.out.println("Teléfono: " + txtTelefono.getText());

    try {
        // Actualizar los datos del usuario
        usuario.setNombre(txtNombre.getText());
        usuario.setApellido(txtApellido.getText());
        usuario.setCorreo_Electronico(txtCorreoElectronico.getText());
        usuario.setContraseña(txtContraseña.getText());
        usuario.setTelefono(txtTelefono.getText());
        usuario.setFecha_Nacimiento(dateChooser.getDate());

        // Crear un objeto de dirección y establecer sus valores
        Direccion direccion = new Direccion();
        direccion.setCalle(txtCalle.getText());
        direccion.setNumeroCasa(txtNumeroCasa.getText());
        direccion.setColonia(txtColonia.getText());
        direccion.setCodigoPostal(txtCodigoPostal.getText());
        direccion.setCiudad(txtCiudad.getText());
        direccion.setPais(txtPais.getText());

        usuario.setDireccion(direccion);
        controladorUsuario.actualizarUsuario(usuario);
        
        System.out.println("Usuario actualizado exitosamente.");
        CuentaUsuario cuentaUsuario = new CuentaUsuario(usuario);
        cuentaUsuario.setVisible(true);
        dispose();
    } catch (Exception e) {
        System.out.println("Error al actualizar los datos del usuario: " + e.getMessage());
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

   

public static void main(String[] args) {
    Conexion conexion = Conexion.getInstance();
    System.out.println("Instancia de Conexion obtenida.");

    if (conexion.getConexion() != null) {
        Usuario usuario = new Usuario();
        EditarDatosUsuario editarDatosUsuario = new EditarDatosUsuario(usuario, conexion.getConexion());
        editarDatosUsuario.setVisible(true);
    } else {
        System.out.println("No se pudo establecer la conexión a la base de datos.");
    }
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
