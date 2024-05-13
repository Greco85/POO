
package Vista;


import Controladores.ControladorUsuario;
import Modelo.SesionActiva;
import Modelo.Usuario;
import SQL.Conexion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.sql.Connection;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class CuentaUsuario extends javax.swing.JFrame {
    private Usuario usuario;
    private String busqueda;
    private int categoriaId;
    private Connection conexion;

    public CuentaUsuario(Usuario usuario) {
        this.usuario = usuario;
        initmyComponents();
        Menubar menubar = new Menubar();
        menubar.initMenuBar(this, usuario, busqueda, categoriaId);        mostrarNombre(); 
        configurarEventos();
    }
    
    private void configurarEventos() {
        EditarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirEditarDatosUsuario();
                dispose();
            }
        });
        
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Publicaciones publicacionesFrame = new Publicaciones(usuario);
                publicacionesFrame.setVisible(true);
                dispose();
            }
        });
        
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MeterDinero meterDinero = new MeterDinero(usuario);
                meterDinero.setVisible(true);
            }
        });
        
        eliminarCuentaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SesionActiva sesionActiva = new SesionActiva();
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar tu cuenta?", "Confirmar eliminación de cuenta", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    Conexion conexion = Conexion.getInstance();
                    ControladorUsuario controladorUsuario = new ControladorUsuario(conexion.getConexion());
                    if (controladorUsuario.eliminarUsuario(sesionActiva.getID_Usuario())) {
                        JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
                        GUI_MENU_P inicioFrame = new GUI_MENU_P();
                        inicioFrame.setVisible(true);
                        dispose(); 
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al eliminar usuario");
                    }
                }
            }
        });
        
        // Productos vendidos
        jButtonProductosVendidos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaVendedor vistaVendedor = new VistaVendedor(usuario);
                vistaVendedor.setVisible(true);
                dispose();
            }
        });

        // Pedidos Hechos
        jButtonPedidosHechos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaComprador vistaComprador = new VistaComprador(usuario);
                vistaComprador.setVisible(true);
                dispose();
            }
        });
        
        // Pedidos Hechos
        jButtonConversaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                int ID_Vendedor = 0;
                int ID_Comprador = 0;
                ConversacionesVista conversacionVista = new ConversacionesVista(usuario, ID_Vendedor, ID_Comprador);
                conversacionVista.setVisible(true);
                dispose();
            }
        });


    }
     
    private void abrirEditarDatosUsuario() {
        EditarDatosUsuario editarDatosUsuario = new EditarDatosUsuario(usuario, conexion);
        editarDatosUsuario.setVisible(true);
        dispose();
    }

   private void initmyComponents() {
    // Crear componentes
    jLabel1 = new javax.swing.JLabel();
    EditarCuenta = new javax.swing.JButton();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    eliminarCuentaButton = new javax.swing.JButton();
    jButtonProductosVendidos = new javax.swing.JButton();
    jButtonPedidosHechos = new javax.swing.JButton();
    jButtonConversaciones = new javax.swing.JButton(); 

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    // Texto de los botones
    EditarCuenta.setText("EDITAR CUENTA");
    jButton1.setText("PUBLICACIONES");
    jButton2.setText("METER DINERO");
    eliminarCuentaButton.setText("Eliminar Cuenta");
    jButtonProductosVendidos.setText("Productos Vendidos");
    jButtonPedidosHechos.setText("Pedidos Hechos");
    jButtonConversaciones.setText("Conversaciones");

    // Estilo de los botones
    estilizarBoton(EditarCuenta);
    estilizarBoton(jButton1);
    estilizarBoton(jButton2);
    estilizarBoton(jButtonProductosVendidos);
    estilizarBoton(jButtonPedidosHechos);
    estilizarBoton(jButtonConversaciones);
        
    eliminarCuentaButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
    eliminarCuentaButton.setBackground(Color.RED); 
    eliminarCuentaButton.setForeground(Color.WHITE);
    eliminarCuentaButton.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)), // Borde blanco
        BorderFactory.createEmptyBorder(15, 50, 15, 50) 
    ));
    
    // Crear panel y agregar botones al panel
    JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10)); // GridLayout de 7 filas y 1 columna
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espacio alrededor del panel
    panel.add(EditarCuenta);
    panel.add(jButton1);
    panel.add(jButton2);
    panel.add(jButtonProductosVendidos);
    panel.add(jButtonPedidosHechos);
    panel.add(jButtonConversaciones);
    panel.add(eliminarCuentaButton);


    // Agregar panel al centro del frame
    getContentPane().add(panel, BorderLayout.CENTER);

    pack();
    setSize(800, 650);
    setLocationRelativeTo(null);
}

// Método para aplicar estilos a los botones
private void estilizarBoton(JButton boton) {
    boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
    boton.setBackground(new Color(41, 81, 204)); // Azul
    boton.setForeground(Color.WHITE);
    boton.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)), // Borde blanco
        BorderFactory.createEmptyBorder(15, 50, 15, 50) // Espacio interno
    ));
}



    private void mostrarNombre() {
        jLabel1.setText(usuario.getNombre());
    }
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        EditarCuenta = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Greco Joseth Rodriguez Gonzalez");

        EditarCuenta.setText("EDITAR CUENTA");
        EditarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarCuentaActionPerformed(evt);
            }
        });

        jButton1.setText("PUBLICACIONES");

        jButton2.setText("GUARDADOS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(EditarCuenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EditarCuenta)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EditarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarCuentaActionPerformed
        
    }//GEN-LAST:event_EditarCuentaActionPerformed
    
        private javax.swing.JButton jButtonConversaciones;

    private javax.swing.JButton jButtonPedidosHechos;
    private javax.swing.JButton jButtonProductosVendidos;
    private javax.swing.JButton eliminarCuentaButton;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EditarCuenta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
