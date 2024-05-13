
package Vista;

import Controladores.ControladorConversacion;
import Controladores.ControladorNotificacion;
import Modelo.Conversacion;
import Modelo.Mensaje;
import Modelo.SesionActiva;
import Modelo.Usuario;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;


public class ConversacionesVista extends javax.swing.JFrame {

    private Usuario usuario;
    private String busqueda;
    private int categoriaId;
    private Connection conexion;
    int ID_Vendedor;
    int ID_Comprador;
    
     private ControladorNotificacion controladorNotificacion;

    
    private ControladorConversacion controladorConversacion;
    
    public ConversacionesVista(Usuario usuario, int ID_Vendedor ,int ID_Comprador) {
        this.usuario = usuario;
        this.ID_Vendedor = ID_Vendedor;
        this.ID_Comprador = ID_Comprador;
        controladorConversacion = new ControladorConversacion(); 
        this.controladorNotificacion = new ControladorNotificacion();

        
        Menubar menubar = new Menubar();
        menubar.initMenuBar(this, usuario, busqueda, categoriaId);        
        initmyComponents();
        
        List<Conversacion> conversaciones = controladorConversacion.obtenerTodasLasConversaciones();
        
        mostrarConversaciones(conversaciones);
        
        System.out.println("El vendedor es " + ID_Vendedor + " y el comprador es: "+ ID_Comprador);
        
        int ID_Conversacion = controladorConversacion.obtenerIDConversacion(ID_Vendedor, ID_Comprador);

        List<Mensaje> Mensajes = controladorConversacion.obtenerMensajesdeConversacion(ID_Conversacion);

        mostrarMensajes(Mensajes);

        
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarMensajeActionPerformed(evt);
            }
        });
        
                setExtendedState(JFrame.MAXIMIZED_BOTH);

    }
    
       public void mostrarConversaciones(List<Conversacion> conversaciones) {

        Dimension panelSize = jPanel1.getSize();

        int posY = 0;

        int i = 0; 
        for (Conversacion conversacion : conversaciones) {
            // Creamos un nuevo panel para esta conversación
            JPanel panel = new javax.swing.JPanel();
            JLabel labelNombre = new javax.swing.JLabel();
            JLabel labelMensaje = new javax.swing.JLabel();

            labelNombre.setText("Nombre: ");
            labelMensaje.setText("Último mensaje: " );

            panel.add(labelNombre);
            panel.add(labelMensaje);

            // Configuramos el color del panel
            
            System.out.println(conversacion.getID_Conversacion());
            
            Mensaje ultimoMensaje = controladorConversacion.obtenerUltimoMensaje(conversacion.getID_Conversacion());
            
            if (ultimoMensaje != null) {
                
                if (SesionActiva.getID_Usuario() == ultimoMensaje.getID_Usuario_Emisor()){
                                    panel.setBackground(Color.GREEN);
                } else { 
                
                 if (!ultimoMensaje.isLeido()) {
                
                    panel.setBackground(Color.RED);

                    } else {

                    panel.setBackground(Color.BLUE);


                    }
                
                }
            
        } else {
            panel.setBackground(Color.GRAY); // Por ejemplo, puedes establecer el color del panel como gris
        }

            panel.setPreferredSize(new Dimension(panelSize.width, 70)); // 70 es una altura arbitraria, puedes ajustarla

            panel.setBounds(0, posY, panelSize.width, 70);

            posY += 70; // 70 es la altura del panel

           panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                if (ultimoMensaje != null && SesionActiva.getID_Usuario() != ultimoMensaje.getID_Usuario_Emisor()) {
                    controladorConversacion.marcarComoVisto(ultimoMensaje.getID_Mensaje());
                }

                int ID_Comprador = conversacion.getID_Comprador();
                int ID_Vendedor = conversacion.getID_Vendedor();

                ConversacionesVista conversacionesVista = new ConversacionesVista(usuario, ID_Vendedor , ID_Comprador);
                conversacionesVista.setVisible(true);
                dispose();
            }
        });


            jPanel1.add(panel);

            i++;
        }

        int panelHeight = conversaciones.size() * 70; // 70 es la altura de cada panel de conversación
        jPanel1.setPreferredSize(new Dimension(panelSize.width, panelHeight));

        jPanel1.revalidate();
        jPanel1.repaint();
    }

    
   public void mostrarMensajes(List<Mensaje> mensajes) {
    // Obtener el tamaño de la pantalla
    int ID_Usuario_Activo = SesionActiva.getID_Usuario(); 

    Dimension panelSize = jPanel2.getSize();
    Color[] colores = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE};
    int posY = 0;
    int i = 0;
    
    jPanel2.removeAll();
    
    for (Mensaje mensaje : mensajes) {
        JPanel panel = new javax.swing.JPanel();
        JLabel labelContenido = new javax.swing.JLabel();
        JLabel labelFechaEnvio = new javax.swing.JLabel();
        
        labelContenido.setText("Contenido: " + mensaje.getMensaje());
        labelFechaEnvio.setText("Fecha de Envío: " + mensaje.getFecha_Envio().toString());
        
        panel.add(labelContenido);
        panel.add(labelFechaEnvio);
        
        if (mensaje.getID_Usuario_Emisor() == ID_Usuario_Activo) {
            panel.setBounds(500, posY, panelSize.width, 70);
        } else {
            panel.setBounds(0, posY, panelSize.width, 70);
        }
        
        panel.setBackground(colores[i % colores.length]);
        panel.setPreferredSize(new Dimension(panelSize.width, 70)); 
        
        posY += 70; 
        
        jPanel2.add(panel);
        i++;
    }
    
    int panelHeight = mensajes.size() * 70; 
    jPanel2.setPreferredSize(new Dimension(panelSize.width, panelHeight));

    jPanel2.revalidate();
    jPanel2.repaint();
    
}


    
private void initmyComponents() {
    jPanel1 = new javax.swing.JPanel();
    jPanel2 = new javax.swing.JPanel();
    jLabel2 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    jTextArea1 = new javax.swing.JTextArea();
    jButton1 = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jPanel1.setBackground(new java.awt.Color(204, 255, 0));
    jPanel1.setForeground(new java.awt.Color(255, 255, 0));

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 264, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
    );

    jPanel2.setBackground(new java.awt.Color(0, 204, 204));

    jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
    jLabel2.setText("AQUI VAN A IR LOS MENSAJES DE CIERTA CONVERSACION");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel2)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(162, Short.MAX_VALUE))
    );
    

    jTextArea1.setColumns(20);
    jTextArea1.setRows(5);
    jScrollPane1.setViewportView(jTextArea1);

    jButton1.setText("Enviar");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    // Agregamos jPanel1 a un JScrollPane y configuramos el scroll vertical
    JScrollPane jScrollPane = new JScrollPane(jPanel1);
    jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    // Agregamos jPanel2 a un JScrollPane y configuramos el scroll vertical
    JScrollPane jScrollPane2 = new JScrollPane(jPanel2);
    jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE) // Modificado
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE) // Modificado
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)))
                .addContainerGap())
    );

    jButton1.getAccessibleContext().setAccessibleName("Enviar");

    pack();
}


     private void enviarMensajeActionPerformed(java.awt.event.ActionEvent evt) {
         
        Date fechaActual = new Date();
        
        Timestamp fecha = new Timestamp(fechaActual.getTime());
        
        String mensaje = jTextArea1.getText();
        
        // Verificar si ya existe una conversación entre el vendedor y el comprador
        if (!controladorConversacion.verificarExistenciaConversacion(ID_Vendedor, ID_Comprador)) {
            // Si no existe, crear la conversación
            controladorConversacion.crearConversacion(ID_Vendedor, ID_Comprador, fecha);

        }
        
        // Obtener el ID de la conversación recién creada
        int ID_Conversacion = controladorConversacion.obtenerIDConversacion(ID_Vendedor, ID_Comprador);

            // Verificar si el vendedor ya está en la conversación
        if (!controladorConversacion.verificarConversacionUsuario(ID_Vendedor, ID_Conversacion)) {
            // Si el vendedor no está en la conversación, agregarlo
            controladorConversacion.agregarConversacionUsuario(ID_Conversacion, ID_Vendedor);
        }

        // Verificar si el comprador ya está en la conversación
        if (!controladorConversacion.verificarConversacionUsuario(ID_Comprador, ID_Conversacion)) {
            // Si el comprador no está en la conversación, agregarlo
            controladorConversacion.agregarConversacionUsuario(ID_Conversacion, ID_Comprador);
        }
        
        controladorConversacion.crearMensaje(SesionActiva.getID_Usuario(), mensaje, fecha, false, ID_Conversacion);

                // Mostrar el mensaje en la consola
                System.out.println("Mensaje enviado: " + mensaje);
                System.out.println("ID DEL VENDEDOR: " + ID_Vendedor);
                System.out.println("ID DEL COMPRADOR: " + ID_Comprador);
                System.out.println("FECHA DEL ULTIMO MENSAJE: " + fecha);
                
                
         int ID_TipoNoti = 1; //LUEGO BUSCARLA CON UNA CONSULTA "Mensaje"

                String mensajee = "El usuario con ID : " + SesionActiva.getID_Usuario() + " te ha enviado un mensaje";
                
                int UsuarioIDNoti;

                if (SesionActiva.getID_Usuario() == ID_Vendedor){
                    UsuarioIDNoti = ID_Comprador;
                } else {
                    UsuarioIDNoti = ID_Vendedor;
                }
                
                
                // Llamada a la función crearNotificacion
                controladorNotificacion.crearNotificacion(UsuarioIDNoti, ID_TipoNoti, mensajee, fecha);


                
        ConversacionesVista conversacionesVista = new ConversacionesVista(usuario, ID_Vendedor , ID_Comprador);
                    conversacionesVista.setVisible(true);
                    dispose();
                
    }
     
     
     
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 0));
        jPanel1.setForeground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel2.setText("AQUI VAN A IR LOS MENSAJES DE CIERTA CONVERSACION");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(189, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(137, 137, 137))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );

        jButton1.getAccessibleContext().setAccessibleName("Enviar");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    
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
            java.util.logging.Logger.getLogger(ConversacionesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConversacionesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConversacionesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConversacionesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
