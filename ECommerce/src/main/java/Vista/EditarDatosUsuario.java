
package Vista;

import Modelo.Usuario;
import Modelo.Direccion;
import Controladores.ControladorUsuario;
import Modelo.SesionActiva;
import SQL.Conexion;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
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
    lblNombre.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
    lblNombre.setForeground(Color.BLACK);
    lblNombre.setOpaque(true);
    
    lblApellido = new JLabel("Apellido:");
    lblApellido.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
    lblApellido.setForeground(Color.BLACK);
    lblApellido.setOpaque(true);

    lblCorreoElectronico = new JLabel("Correo Electrónico:");
    lblCorreoElectronico.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
    lblCorreoElectronico.setForeground(Color.BLACK);
    lblCorreoElectronico.setOpaque(true);

    lblContraseña = new JLabel("Contraseña:");
    lblContraseña.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
    lblContraseña.setForeground(Color.BLACK);
    lblContraseña.setOpaque(true);

    lblTelefono = new JLabel("Teléfono:");
    lblTelefono.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
    lblTelefono.setForeground(Color.BLACK);
    lblTelefono.setOpaque(true);

    lblFechaNacimiento = new JLabel("Fecha de Nacimiento:");
    lblFechaNacimiento.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
    lblFechaNacimiento.setForeground(Color.BLACK);
    lblFechaNacimiento.setOpaque(true);

    lblCalle = new JLabel("Calle:");
    lblCalle.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
    lblCalle.setForeground(Color.BLACK);
    lblCalle.setOpaque(true);

    lblNumeroCasa = new JLabel("Número de Casa:");
    lblNumeroCasa.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
    lblNumeroCasa.setForeground(Color.BLACK);
    lblNumeroCasa.setOpaque(true);

    lblColonia = new JLabel("Colonia:");
    lblColonia.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
    lblColonia.setForeground(Color.BLACK);
    lblColonia.setOpaque(true);

    lblCodigoPostal = new JLabel("Código Postal:");
    lblCodigoPostal.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
    lblCodigoPostal.setForeground(Color.BLACK);
    lblCodigoPostal.setOpaque(true);

    lblCiudad = new JLabel("Ciudad:");
    lblCiudad.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
    lblCiudad.setForeground(Color.BLACK);
    lblCiudad.setOpaque(true);

    lblPais = new JLabel("País:");
    lblPais.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
    lblPais.setForeground(Color.BLACK);
    lblPais.setOpaque(true);


    txtNombre = new JTextField(20);
    txtNombre.setText(usuario.getNombre());

    txtNombre.setPreferredSize(new Dimension(300, 40));
    txtNombre.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY),
        BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));

    txtApellido = new JTextField(20);
    txtApellido.setText(usuario.getApellido());

    txtApellido.setPreferredSize(new Dimension(300, 40));
    txtApellido.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY),
        BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));

    txtCorreoElectronico = new JTextField(20);
    txtCorreoElectronico.setText(usuario.getCorreo_Electronico());
    txtCorreoElectronico.setPreferredSize(new Dimension(300, 40));
    txtCorreoElectronico.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY),
        BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));

    txtContraseña = new JTextField(20);
    txtContraseña.setText(usuario.getContraseña());
    txtContraseña.setPreferredSize(new Dimension(300, 40));
    txtContraseña.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY),
        BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));

    txtTelefono = new JTextField(20);
    txtTelefono.setText(usuario.getTelefono());
    txtTelefono.setPreferredSize(new Dimension(300, 40));
    txtTelefono.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY),
        BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));
    
    Direccion direccion = usuario.getDireccion();
    
    txtCalle = new JTextField(20);
    txtCalle.setText(direccion.getCalle());
    txtCalle.setPreferredSize(new Dimension(300, 40));
    txtCalle.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY),
        BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));

    txtNumeroCasa = new JTextField(20);
    txtNumeroCasa.setText(direccion.getNumeroCasa());
    txtNumeroCasa.setPreferredSize(new Dimension(300, 40));
    txtNumeroCasa.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY),
        BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));

    txtColonia = new JTextField(20);
    txtColonia.setText(direccion.getColonia());
    txtColonia.setPreferredSize(new Dimension(300, 40));
    txtColonia.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY),
        BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));

    txtCodigoPostal = new JTextField(20);        
    txtCodigoPostal.setText(direccion.getCodigoPostal());
    txtCodigoPostal.setPreferredSize(new Dimension(300, 40));
    txtCodigoPostal.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY),
        BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));

    txtCiudad = new JTextField(20);       
    txtCiudad.setText(direccion.getCiudad());
    txtCiudad.setPreferredSize(new Dimension(300, 40));
    txtCiudad.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY),
        BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));

    txtPais = new JTextField(20);
    txtPais.setText(direccion.getPais());

    txtPais.setPreferredSize(new Dimension(300, 40));
    txtPais.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY),
        BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));

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
    
    btnActualizar.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        btnActualizar.setBackground(new Color(41, 81, 204));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)),
                BorderFactory.createEmptyBorder(15, 50, 15, 50)
        ));
        
    btnCancelar.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        btnCancelar.setBackground(new Color(102, 102, 102));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)),
                BorderFactory.createEmptyBorder(15, 50, 15, 50)
        ));    

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
    
    
     btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnActualizar.setBackground(new Color(41, 81, 204));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnActualizar.setBackground(new Color(51, 102, 255)); 
            }
        });

        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelar.setBackground(new Color(82, 82, 82));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelar.setBackground(new Color(102, 102, 102));
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
        setSize(600, 650);
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
