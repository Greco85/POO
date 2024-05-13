
package Vista;

import Controladores.ControladorCarrito;
import Controladores.ControladorComentario;
import Controladores.ControladorProducto;
import Modelo.Comentario;
import Modelo.Producto;
import Modelo.SesionActiva;
import Modelo.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


public class VerProducto extends javax.swing.JFrame {

    private Usuario usuario;
    private String busqueda;
    private int categoriaId;
    private JPanel detallesProductoPanel;
    private Connection conexion;

    
    public VerProducto(Usuario usuario) {
        this.usuario = usuario;
        obtenerDatosProducto();
        initmyComponents();
        Menubar menubar = new Menubar();
        menubar.initMenuBar(this, usuario, busqueda, categoriaId);        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
   private void obtenerDatosProducto() {
    
    ControladorProducto controlador = new ControladorProducto(); 
     Producto producto = controlador.obtenerProductosporID(SesionActiva.getID_Producto());

    if (producto != null) {
        System.out.println("Detalles del Producto:");
        System.out.println("ID: " + producto.getID_Producto());
        System.out.println("Nombre: " + producto.getNombre());
        System.out.println("Descripción: " + producto.getDescripcion());
        System.out.println("Precio: " + producto.getPrecio());
        System.out.println("Cantidad Disponible: " + producto.getCantidad_Disponible());
        System.out.println("ID de Categoría: " + producto.getID_CategoriaProducto());
        
    } else {
        System.out.println("El producto con ID " + SesionActiva.getID_Producto() + " no fue encontrado.");
    }
}
   
    private void initmyComponents() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int) screenSize.getWidth();
    int screenHeight = (int) screenSize.getHeight();

    JPanel detallesProductoPanel = new JPanel(new GridBagLayout());
    detallesProductoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    JPanel comentariosPanel = new JPanel(new GridBagLayout());
    comentariosPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    ControladorProducto controlador = new ControladorProducto();
    Producto producto = controlador.obtenerProductosporID(SesionActiva.getID_Producto());
    
    int ID_Vendedor = producto.getID_Usuario();
    
    if (producto != null) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); 

        JLabel idLabel = new JLabel("ID:");
        JLabel idValorLabel = new JLabel(Integer.toString(SesionActiva.getID_Producto()));
        detallesProductoPanel.add(idLabel, gbc);
        detallesProductoPanel.add(idValorLabel, gbc);

        JLabel nombreLabel = new JLabel("Nombre:");
        JLabel nombreValorLabel = new JLabel(producto.getNombre());
        detallesProductoPanel.add(nombreLabel, gbc);
        detallesProductoPanel.add(nombreValorLabel, gbc);
        
        JLabel descripcionLabel = new JLabel("Descripción:");
        JLabel descripcionValorLabel = new JLabel(producto.getDescripcion());
        detallesProductoPanel.add(descripcionLabel, gbc);
        detallesProductoPanel.add(descripcionValorLabel, gbc);

        JLabel precioLabel = new JLabel("Precio:");
        JLabel precioValorLabel = new JLabel(Double.toString(producto.getPrecio()));
        detallesProductoPanel.add(precioLabel, gbc);
        detallesProductoPanel.add(precioValorLabel, gbc);

        JLabel cantidadLabel = new JLabel("Cantidad Disponible:");
        JLabel cantidadValorLabel = new JLabel(Integer.toString(producto.getCantidad_Disponible()));
        detallesProductoPanel.add(cantidadLabel, gbc);
        detallesProductoPanel.add(cantidadValorLabel, gbc);

        JLabel categoriaLabel = new JLabel("ID de Categoría:");
        JLabel categoriaValorLabel = new JLabel(Integer.toString(producto.getID_CategoriaProducto()));
        detallesProductoPanel.add(categoriaLabel, gbc);
        detallesProductoPanel.add(categoriaValorLabel, gbc);

        ImageIcon imagenIcono = new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Imagenes\\bmo.jpg");
        Image imagenOriginal = imagenIcono.getImage();
        Image imagenEscalada = imagenOriginal.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon imagenEscaladaIcono = new ImageIcon(imagenEscalada);

        JLabel imagenLabel = new JLabel();
        imagenLabel.setIcon(imagenEscaladaIcono);

        gbc.gridwidth = 2;
        gbc.gridy = 0; 
        gbc.insets = new Insets(20, 5, 5, 5);
        detallesProductoPanel.add(imagenLabel, gbc);

        gbc.gridy++;

        gbc.insets = new Insets(10, 5, 5, 5);


        
        JButton comprarButton = new JButton("Comprar Producto");
        comprarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                String cantidadStr = JOptionPane.showInputDialog(null, "Ingrese la cantidad de productos que desea comprar:");
                int cantidad = Integer.parseInt(cantidadStr);

                if (producto != null && cantidad > 0 && cantidad <= producto.getCantidad_Disponible()) {
                    
                    double TotalApagar = producto.getPrecio() * cantidad; 
                    
                    SesionActiva.setTotalAPagar(TotalApagar); 

                    PantallaCompraProducto pantallaDeCompra = new PantallaCompraProducto(usuario, conexion, SesionActiva.getID_Producto(), TotalApagar, cantidad);
                    pantallaDeCompra.setVisible(true);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "La cantidad solicitada no está disponible.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                   
            }
        });
        
        detallesProductoPanel.add(comprarButton, gbc);
        
        JButton mandarMensajeButton = new JButton("Mandar Mensaje");
            mandarMensajeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                    int ID_Comprador = SesionActiva.getID_Usuario();
                            
                    ConversacionesVista conversacionesVista = new ConversacionesVista(usuario, ID_Vendedor , ID_Comprador);
                    conversacionesVista.setVisible(true);
                    dispose();
                    
                }
            });
            detallesProductoPanel.add(mandarMensajeButton, gbc);


        // Botón para Agregar al Carrito
        JButton agregarCarritoButton = new JButton("Agregar al Carrito");
        agregarCarritoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int ID_Producto = producto.getID_Producto();
                String cantidad = JOptionPane.showInputDialog(null, "Ingrese la cantidad que desea agregar al carrito:", "Cantidad", JOptionPane.QUESTION_MESSAGE);

                try {
                    int cantidadParaElCarro = Integer.parseInt(cantidad);

                    if (cantidadParaElCarro <= 0) {
                        JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor que cero.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int cantidadDisponible = producto.getCantidad_Disponible();

                    if (cantidadParaElCarro > cantidadDisponible) {
                        JOptionPane.showMessageDialog(null, "No hay suficiente cantidad disponible en el inventario.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    double total = cantidadParaElCarro * producto.getPrecio();

                    ControladorCarrito controladorCarrito = new ControladorCarrito();

                    // Verificar si el producto ya está en el carrito y actuar en consecuencia
                    boolean agregado = controladorCarrito.agregarOActualizarCarrito(SesionActiva.getID_Usuario(), ID_Producto, cantidadParaElCarro, total);

                    if (agregado) {
                        JOptionPane.showMessageDialog(null, "Producto agregado al carrito exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al agregar el producto al carrito.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido para la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        detallesProductoPanel.add(agregarCarritoButton, gbc);

       // Botón para Hacer Comentario
       JButton hacerComentarioButton = new JButton("Hacer Comentario");
        hacerComentarioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HacerComentario hacerComentario = new HacerComentario();
                hacerComentario.setVisible(true);
            }
        });
        detallesProductoPanel.add(hacerComentarioButton, gbc);

    } else {
        JLabel errorLabel = new JLabel("El producto no fue encontrado.");
        detallesProductoPanel.add(errorLabel);
    }
    
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(detallesProductoPanel, BorderLayout.NORTH); 

    GridBagConstraints gbcComentarios = new GridBagConstraints();
    gbcComentarios.anchor = GridBagConstraints.WEST;
    gbcComentarios.insets = new Insets(5, 5, 5, 5);

    mostrarComentarios(comentariosPanel);
    JScrollPane comentariosScrollPane = new JScrollPane(comentariosPanel);
    comentariosScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    getContentPane().add(comentariosScrollPane, BorderLayout.CENTER); 

    int margin = 0;
    int panelWidth = screenWidth - (2 * margin);
    int panelHeight = 400;
    detallesProductoPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));

    pack();
}
    
private void mostrarComentarios(JPanel comentariosPanel) {
    ControladorComentario controladorComentario = new ControladorComentario(); 
    
    List<Comentario> comentarios = controladorComentario.obtenerComentariosPorIDProducto(SesionActiva.getID_Producto());
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 5, 5, 5);
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int comentarioWidth = (int) screenSize.getWidth();
    
    int gridy = 0;
    
    for (Comentario comentario : comentarios) {
        JPanel comentarioPanel = new JPanel(new GridBagLayout());
        comentarioPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        JLabel idComentarioLabel = new JLabel("ID Comentario: " + comentario.getID_Comentario());
        JLabel idUsuarioLabel = new JLabel("ID Usuario: " + comentario.getID_Usuario());
        
        JLabel comentarioTextLabel = new JLabel("Comentario:");
        comentarioTextLabel.setForeground(Color.WHITE);
        
        JTextArea contenidoTextArea = new JTextArea(comentario.getComentario());
        contenidoTextArea.setLineWrap(true); 
        contenidoTextArea.setWrapStyleWord(true); 
        contenidoTextArea.setEditable(false);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        
        JLabel fechaLabel = new JLabel("Fecha Comentario: " + comentario.getFecha_Comentario().toString()); // Puedes darle el formato que desees
        
        // Si el ID de usuario del comentario coincide con el ID de usuario activo pasa:
        if (comentario.getID_Usuario() == SesionActiva.getID_Usuario()) {
            comentarioPanel.setBackground(Color.BLUE);
            contenidoTextArea.setBackground(Color.BLUE);
            idComentarioLabel.setForeground(Color.WHITE);
            idUsuarioLabel.setForeground(Color.WHITE);
            fechaLabel.setForeground(Color.WHITE);
            contenidoTextArea.setForeground(Color.WHITE);
            
            JButton editarButton = new JButton("Editar");
            JButton borrarButton = new JButton("Borrar");
            
            editarButton.addActionListener(e -> {
            int ID_Comentario = comentario.getID_Comentario();

            // Llamar al método ObtenerComentarioPorID en la instancia de ControladorComentario
            Comentario comentarioEditar = controladorComentario.ObtenerComentarioPorID(ID_Comentario);

            // Verificar si se pudo obtener el comentario
            if (comentarioEditar != null) {
                EditarComentario editarComentario = new EditarComentario(comentarioEditar);
                editarComentario.setVisible(true);
                System.out.println(comentarioEditar);
            } else {
                // Manejar el caso en el que no se pueda obtener el comentario
                JOptionPane.showMessageDialog(null, "No se pudo obtener el comentario para editar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

            
           borrarButton.addActionListener(e -> {
            int ID_Comentario = comentario.getID_Comentario();

            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres borrar este comentario?", "Confirmar borrado", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean borrado = controladorComentario.borrarComentario(ID_Comentario);

                if (borrado) {
                    // Elimina el panel de comentario de la interfaz de usuario
                    comentariosPanel.remove(comentarioPanel);
                    comentariosPanel.revalidate();
                    comentariosPanel.repaint();
                }
            }
        });


            
            // Agregar los botones al panel
            gbc.gridy = gridy;
            gbc.gridx = 0;
            comentarioPanel.add(idComentarioLabel, gbc);
            gbc.gridy++;
            comentarioPanel.add(idUsuarioLabel, gbc);
            gbc.gridy++;
            comentarioPanel.add(comentarioTextLabel, gbc);
            gbc.gridy++;
            comentarioPanel.add(contenidoTextArea, gbc);
            gbc.gridy++;
            comentarioPanel.add(fechaLabel, gbc);
            gbc.gridy++;
            comentarioPanel.add(editarButton, gbc);
            gbc.gridy++;
            comentarioPanel.add(borrarButton, gbc);
        } else {
            gbc.gridy = gridy;
            gbc.gridx = 0;
            comentarioPanel.add(idComentarioLabel, gbc);
            gbc.gridy++;
            comentarioPanel.add(idUsuarioLabel, gbc);
            gbc.gridy++;
            comentarioPanel.add(comentarioTextLabel, gbc);
            gbc.gridy++;
            comentarioPanel.add(contenidoTextArea, gbc);
            gbc.gridy++;
            comentarioPanel.add(fechaLabel, gbc);
        }
        
        gridy++; 
        
        comentariosPanel.add(comentarioPanel, gbc);
    }
    
    comentariosPanel.revalidate();
    comentariosPanel.repaint();
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
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(VerProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Usuario usuario = new Usuario();
                new VerProducto(usuario).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
