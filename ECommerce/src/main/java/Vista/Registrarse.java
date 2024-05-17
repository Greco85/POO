
package Vista;

import Controladores.ControladorInicioSesion;
import Controladores.ControladorProducto;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.awt.event.*;
import Modelo.Usuario;
import java.util.Date;
import java.text.ParseException;
import Controladores.ControladorUsuario;
import SQL.Conexion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
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
    private ControladorInicioSesion controladorInicioSesion;


    public Registrarse() {
        controladorInicioSesion = new ControladorInicioSesion(); 

        initMyComponents();
    }
    
    private void initMyComponents() {
        setTitle("Registrarse");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel iconLabel = new JLabel();
        ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Imagenes\\Perfil.png");
        Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);

        iconLabel.setIcon(resizedIcon);
        
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.WHITE); 
        imagePanel.add(iconLabel);

        lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
        lblNombre.setForeground(Color.BLACK);
        lblNombre.setBackground(Color.WHITE); 

        lblNombre.setOpaque(true);

        lblApellido = new JLabel("Apellido:");
        lblApellido.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
        lblApellido.setForeground(Color.BLACK);
        lblApellido.setBackground(Color.WHITE); 

        lblApellido.setOpaque(true);

        lblCorreoElectronico = new JLabel("Correo Electrónico:");
        lblCorreoElectronico.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
        lblCorreoElectronico.setForeground(Color.BLACK);
        lblCorreoElectronico.setBackground(Color.WHITE); 

        lblCorreoElectronico.setOpaque(true);

        lblContraseña = new JLabel("Contraseña:");
        lblContraseña.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
        lblContraseña.setForeground(Color.BLACK);
        lblContraseña.setBackground(Color.WHITE); 

        lblContraseña.setOpaque(true);

        lblNumeroTelefono = new JLabel("Teléfono:");
        lblNumeroTelefono.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
        lblNumeroTelefono.setForeground(Color.BLACK);
        lblNumeroTelefono.setBackground(Color.WHITE); 

        lblNumeroTelefono.setOpaque(true);

        lblFechaNacimiento = new JLabel("Fecha de Nacimiento:");
        lblFechaNacimiento.setFont(new Font("Quicksand Medium", Font.BOLD, 14));
        lblFechaNacimiento.setForeground(Color.BLACK);
        lblFechaNacimiento.setBackground(Color.WHITE); 

        lblFechaNacimiento.setOpaque(true);


        txtNombre = new JTextField(20);
        txtNombre.setPreferredSize(new Dimension(300, 40)); // Ajustar el tamaño del JTextField
        txtNombre.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY), // Borde externo
                BorderFactory.createEmptyBorder(5, 10, 5, 10) // Espacio interno
        ));

        txtApellido = new JTextField(20);
        txtApellido.setPreferredSize(new Dimension(300, 40));
        txtApellido.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        txtCorreoElectronico = new JTextField(20);
        txtCorreoElectronico.setPreferredSize(new Dimension(300, 40));
        txtCorreoElectronico.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        txtContraseña = new JPasswordField(20); 
        txtContraseña.setPreferredSize(new Dimension(300, 40));
        txtContraseña.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        txtNumeroTelefono = new JTextField(20);
        txtNumeroTelefono.setPreferredSize(new Dimension(300, 40));
        txtNumeroTelefono.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        // Para el campo de texto txtNombre
        txtNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtNombre.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY), // Cambiar a gris claro
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtNombre.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY), // Cambiar al color gris original
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }
        });

        // Para el campo de texto txtApellido
        txtApellido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtApellido.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY), // Cambiar a gris claro
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtApellido.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY), // Cambiar al color gris original
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }
        });

        // Para el campo de texto txtCorreoElectronico
        txtCorreoElectronico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtCorreoElectronico.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY), // Cambiar a gris claro
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtCorreoElectronico.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY), // Cambiar al color gris original
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }
        });

        // Para el campo de texto txtContraseña
        txtContraseña.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtContraseña.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY), // Cambiar a gris claro
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtContraseña.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY), // Cambiar al color gris original
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }
        });

        // Para el campo de texto txtNumeroTelefono
        txtNumeroTelefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtNumeroTelefono.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY), // Cambiar a gris claro
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtNumeroTelefono.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY), // Cambiar al color gris original
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }
        });

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setPreferredSize(new Dimension(300, 40));
        dateChooser.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        btnRegistrarse = new JButton("Registrarse");
        btnRegresar = new JButton("Regresar");
        
        btnRegistrarse.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        btnRegistrarse.setBackground(new Color(41, 81, 204));
        btnRegistrarse.setForeground(Color.WHITE);
        btnRegistrarse.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)),
                BorderFactory.createEmptyBorder(15, 50, 15, 50)
        ));
        
        btnRegresar.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        btnRegresar.setBackground(new Color(102, 102, 102));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)),
                BorderFactory.createEmptyBorder(15, 50, 15, 50)
        ));

        btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (todosCamposCompletos()) {
                    Usuario nuevoUsuario = obtenerDatosUsuario();
                    mostrarMensaje("Registro exitoso");
                    
                    int ID_Usuario = controladorInicioSesion.obtenerIDconCorreoUsuario(nuevoUsuario.getCorreo_Electronico());
                    
                    controladorInicioSesion.actualizarIMGURL(ID_Usuario);
                    
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
        
         btnRegistrarse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistrarse.setBackground(new Color(41, 81, 204));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrarse.setBackground(new Color(51, 102, 255)); 
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

        JPanel contentPanel = new JPanel();
    contentPanel.setBackground(Color.WHITE); 
    GroupLayout layout = new GroupLayout(contentPanel);
    contentPanel.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(imagePanel) 
                    .addGroup(layout.createSequentialGroup()
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
                            .addComponent(dateChooser)))
                    .addComponent(btnRegistrarse, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegresar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagePanel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
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
     getContentPane().add(contentPanel);
        
     pack();
        setSize(600, 600);

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
    String imagenURL = ""; 

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

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registrarse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
