
package Vista; //probando cosillas

import Modelo.Usuario;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;


public class CuentaUsuario extends JFrame {

    private Usuario usuario;
    private String busqueda;
    private int categoriaId;

    public CuentaUsuario(Usuario usuario) {
        this.usuario = usuario;
        initMyComponents();
        menubar.initMenuBar(this, usuario, busqueda, categoriaId); 
        setVisible(true);
        
    }

    private void initMyComponents() {
      
        JPanel panel = new JPanel(new BorderLayout());

    
        ImageIcon editarIcon = new ImageIcon("bmo.jpg");
        JButton btnEditar = new JButton("Editar", editarIcon);
        JButton btnVender = new JButton("Vender");
        ImageIcon productosIcon = new ImageIcon("bmo.jpg"); 
        JButton btnProductosActivos = new JButton("Productos Activos", productosIcon);
        JButton btnProductosDeseados = new JButton("Productos Deseados");

        JLabel lblProductos = new JLabel();

 
        btnProductosActivos.addActionListener(evt -> lblProductos.setText("Hola, estos son tus productos activos."));
        btnProductosDeseados.addActionListener(evt -> lblProductos.setText("Estos son tus productos deseados."));


        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // 2 filas y 2 columnas
        buttonPanel.add(btnEditar);
        buttonPanel.add(btnVender);
        buttonPanel.add(btnProductosActivos);
        buttonPanel.add(btnProductosDeseados);

        panel.add(buttonPanel, BorderLayout.NORTH); 

        
        getContentPane().add(panel);

        pack();
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

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
