
package Vista;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Busqueda extends javax.swing.JFrame {
    
     private JLabel busquedaLabel;
    private JLabel categoriaLabel;

    
    public Busqueda(String busqueda, String categoriaSeleccionada) {
        initComponents();
        mostrarObjetos(busqueda, categoriaSeleccionada);
    }
    
    private void mostrarObjetos(String busqueda, String categoriaSeleccionada) {

        busquedaLabel = new JLabel("Búsqueda: " + busqueda);
        categoriaLabel = new JLabel("Categoría seleccionada: " + categoriaSeleccionada);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(categoriaLabel)
                    .addComponent(busquedaLabel))
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
