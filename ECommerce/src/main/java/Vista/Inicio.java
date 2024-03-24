package Vista;

import Modelo.Usuario;
import Modelo.Producto;
import Controladores.ControladorProducto;
import javax.swing.JLabel;

public class Inicio extends javax.swing.JFrame {
    
    private Usuario usuario;
    private ControladorProducto controladorProducto;
    private String busqueda;
    private int categoriaId;

    public Inicio(Usuario usuario) {
        initComponents();
        menubar.initMenuBar(this, usuario, busqueda, categoriaId);
        this.usuario = usuario;
        jLabel1.setText("Bienvenido, " + usuario.getCorreo_Electronico());
        controladorProducto = new ControladorProducto();
        mostrarProductosEnLabels();
    }
        
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        popupMenu1 = new java.awt.PopupMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        popupMenu2 = new java.awt.PopupMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenu5 = new javax.swing.JMenu();
        jLabel1 = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        jMenu1.setText("jMenu1");

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        popupMenu1.setLabel("popupMenu1");

        jMenu2.setText("jMenu2");

        jMenuItem2.setText("jMenuItem2");

        popupMenu2.setLabel("popupMenu2");

        jMenu5.setText("jMenu5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("jLabelBienvenida");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(jLabel1)
                .addContainerGap(180, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel1)
                .addContainerGap(342, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
private void mostrarProductosEnLabels() { //tambien solo ver que se pasen valores
        Producto[] productos = controladorProducto.getProductos();
        int yPosition = 130; // Posición vertical inicial para el primer producto

        for (int i = 0; i < productos.length; i++) {
            Producto producto = productos[i];
            JLabel productoLabel = new JLabel();
            productoLabel.setText(producto.getNombre() + " - Precio: $" + producto.getPrecio());
            productoLabel.setBounds(50, yPosition, 300, 20); // Establece posicion y tamaño del JLabel
            getContentPane().add(productoLabel); // Agrega el JLabel al contenedor principal
            yPosition += 30; // Incrementa la posición vertical para el próximo producto
        }
    }
    
    public static void main(String[] args) {
        //luego veo
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.MenuBar menuBar1;
    private java.awt.PopupMenu popupMenu1;
    private java.awt.PopupMenu popupMenu2;
    // End of variables declaration//GEN-END:variables
}
