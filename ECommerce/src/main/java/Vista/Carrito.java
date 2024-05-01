package Vista;

import Modelo.SesionActiva;
import Modelo.Usuario; //EL CARRITO DESPUES DE HACER FUNCIONAR LO DE LOS PRODUCTOS
import javax.swing.JFrame;

public class Carrito extends javax.swing.JFrame {
    private Usuario usuario;
    private String busqueda;
    private int categoriaId;
    
    public Carrito(Usuario usuario) {
        this.usuario = usuario;
        initComponents();
        menubar.initMenuBar(this, usuario, busqueda, categoriaId);
        // Para ver si funciona en cualquier frame
    int ID_Usuario = SesionActiva.getID_Usuario();
    System.out.println("Hola ya esta en el inicio y el ID del usuario aquí es: " + ID_Usuario);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ENTRASTE AL CARRITO :P");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(jLabel1)
                .addContainerGap(136, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addContainerGap(248, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
