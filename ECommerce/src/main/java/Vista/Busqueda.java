
package Vista;

import javax.swing.JLabel;
import Controladores.ControladorProducto;
import Modelo.Usuario;
import javax.swing.JFrame;


public class Busqueda extends javax.swing.JFrame {
    
    private JLabel busquedaLabel;
    private JLabel categoriaLabel;
    private String categoriaSeleccionada = "1"; //INICIAMOS CON EL ID 1 PERO QUIZA LO CAMBIE LUEGO
    private ControladorProducto controladorProducto;
    private Usuario usuario;
    private String busqueda;
    private int categoriaId;
    

     public Busqueda(String busqueda, int categoriaId, Usuario usuario) {
       
        super("Búsqueda de Productos");
        initComponents();
        menubar.initMenuBar(this, usuario, busqueda, categoriaId);
        controladorProducto = new ControladorProducto();
        this.usuario = usuario;
        this.busqueda = busqueda;
        this.categoriaId = categoriaId;
        mostrarObjetos(busqueda, categoriaId);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    private void mostrarObjetos(String busqueda, int categoriaId) { //Esto solo es para comprobar que se pasan los valores
        busquedaLabel = new JLabel("Búsqueda: " + busqueda);
        categoriaLabel = new JLabel("Categoría seleccionada: " + categoriaId);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(busquedaLabel)
                    .addComponent(categoriaLabel))
                .addContainerGap(253, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(busquedaLabel)
                .addGap(18, 18, 18)
                .addComponent(categoriaLabel)
                .addContainerGap(243, Short.MAX_VALUE))
        );

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
