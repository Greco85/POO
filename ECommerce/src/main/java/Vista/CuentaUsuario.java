
package Vista;


import Controladores.ControladorUsuario;
import Modelo.SesionActiva;
import Modelo.Usuario;
import SQL.Conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.sql.Connection;
import javax.swing.JFrame;


public class CuentaUsuario extends javax.swing.JFrame {
    private Usuario usuario;
    private String busqueda;
    private int categoriaId;
    private Connection conexion;

    public CuentaUsuario(Usuario usuario) {
        this.usuario = usuario;
        initmyComponents();
        menubar.initMenuBar(this, usuario, busqueda, categoriaId);
        mostrarNombre(); 
        configurarEventos();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
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
                Publicaciones publicacionesFrame = new Publicaciones();
                publicacionesFrame.setVisible(true);
                dispose();
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
   
    }
     
    private void abrirEditarDatosUsuario() {
    EditarDatosUsuario editarDatosUsuario = new EditarDatosUsuario(usuario, conexion);
    editarDatosUsuario.setVisible(true);
    dispose();
}

     
     
private void initmyComponents() {

    jLabel1 = new javax.swing.JLabel();
    EditarCuenta = new javax.swing.JButton();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    eliminarCuentaButton = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jLabel1.setText("Nombre del Usuario");

    EditarCuenta.setText("EDITAR CUENTA");

    jButton1.setText("PUBLICACIONES");

    jButton2.setText("GUARDADOS");

    eliminarCuentaButton.setText("Eliminar Cuenta");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(150, 150, 150)
                    .addComponent(jLabel1))
                .addGroup(layout.createSequentialGroup()
                    .addGap(98, 98, 98)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(EditarCuenta)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createSequentialGroup()
                    .addGap(132, 132, 132)
                    .addComponent(eliminarCuentaButton)))
            .addContainerGap(97, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(65, 65, 65)
            .addComponent(jLabel1)
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(EditarCuenta)
                .addComponent(jButton2))
            .addGap(18, 18, 18)
            .addComponent(jButton1)
            .addGap(18, 18, 18)
            .addComponent(eliminarCuentaButton)
            .addContainerGap(73, Short.MAX_VALUE))
    );

    pack();
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

    private javax.swing.JButton eliminarCuentaButton;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EditarCuenta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
