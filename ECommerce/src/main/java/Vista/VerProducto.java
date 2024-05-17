
package Vista;

import Controladores.ControladorCarrito;
import Controladores.ControladorComentario;
import Controladores.ControladorConversacion;
import Controladores.ControladorProducto;
import Modelo.Comentario;
import Modelo.Producto;
import Modelo.SesionActiva;
import Modelo.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;


public class VerProducto extends javax.swing.JFrame {

    private Usuario usuario;
    private String busqueda;
    private int categoriaId;
    private JPanel detallesProductoPanel;
    private Connection conexion;
    
        private ControladorConversacion controladorConversacion;
        private ControladorProducto controladorProducto;


    
    public VerProducto(Usuario usuario) {
        this.usuario = usuario;
                controladorConversacion = new ControladorConversacion(); 
                controladorProducto = new ControladorProducto(); 

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
       
       
    String ImagenRuta; 

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int) screenSize.getWidth();
    int screenHeight = (int) screenSize.getHeight();

    detallesProductoPanel = new JPanel();
    detallesProductoPanel.setLayout(null);
    detallesProductoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    JPanel comentariosPanel = new JPanel();
    comentariosPanel.setLayout(null);
    comentariosPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    
    

    ControladorProducto controlador = new ControladorProducto();
    Producto producto = controlador.obtenerProductosporID(SesionActiva.getID_Producto());

    int ID_Vendedor = producto.getID_Usuario();
    
    double anchoPantalla = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    


    // Restar 10 (espacio lateral izquierdo), 10 (espacio lateral derecho), 260 (ancho del panel de imagen), 10 (espacio entre la imagen y el texto)
    double anchoRestado = anchoPantalla - 70 - 270;


    if (producto != null) {
        
        
        // Ruta predeterminada de la imagen de respaldo
            String imagenPredeterminada = System.getProperty("user.dir") + "\\src\\main\\java\\Imagenes\\Sinimagen.jpg";

            // Ruta de la imagen proporcionada por el producto
            String imagenRutaProducto = System.getProperty("user.dir") + "\\src\\main\\java\\Imagenes\\" + producto.getImagenURL();

            // Verificar si la imagen del producto existe
            File imagenProducto = new File(imagenRutaProducto);
            if (imagenProducto.exists()) {
                // Si la imagen del producto existe, usar esa ruta
                 ImagenRuta = imagenRutaProducto;
            } else {
                // Si no existe, usar la imagen predeterminada
                ImagenRuta = imagenPredeterminada;
            }
        
        
         // Crear el panel rojo
        JPanel panelImagen = new JPanel();
        panelImagen.setBackground(Color.WHITE);
        panelImagen.setBounds(10, 45, 300, 300);
        detallesProductoPanel.add(panelImagen);
        
        JLabel labelImagen = new JLabel();
        ImageIcon icono = new ImageIcon(ImagenRuta);
        Image imagen = icono.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagen);
        labelImagen.setIcon(iconoEscalado);
        labelImagen.setBounds(0, 0, 300, 300);
        panelImagen.add(labelImagen);
        
        
          // Crear JPanel para el nombre
        JPanel panelNombre = new JPanel();
        panelNombre.setBackground(Color.BLUE);
        panelNombre.setBounds(323, 20, (int)anchoRestado, 30);
        panelNombre.setLayout(new BorderLayout()); 
        detallesProductoPanel.add(panelNombre);

        // Crear JLabel para el nombre
        JLabel nombreLabel = new JLabel(producto.getNombre());
        nombreLabel.setForeground(Color.WHITE);
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 14)); 
        nombreLabel.setHorizontalAlignment(JLabel.CENTER);
        panelNombre.add(nombreLabel, BorderLayout.CENTER); 

        panelNombre.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Establecer un relleno en los bordes del JPanel


        JTextArea descripcionTextArea = new JTextArea("Descripción: " + producto.getDescripcion());
        descripcionTextArea.setBounds(323, 60, (int)anchoRestado, 190); 
        descripcionTextArea.setLineWrap(true); 
        descripcionTextArea.setWrapStyleWord(true); 
        descripcionTextArea.setEditable(false); 
        descripcionTextArea.setBackground(Color.WHITE);
        descripcionTextArea.setForeground(Color.BLACK); 
        descripcionTextArea.setFont(new Font("Arial", Font.PLAIN, 14)); 

        // Agregar un borde al JTextArea
        descripcionTextArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5) 
        ));

        detallesProductoPanel.add(descripcionTextArea);

        int cantidadLabels = 3; 
        double espacioDisponible = anchoRestado / cantidadLabels;
        int posicionHorizontal = 323;

        // Crear JLabels para precio, cantidad y categoría
        JLabel precioLabel = new JLabel("Precio: $" + producto.getPrecio());
        JLabel cantidadLabel = new JLabel("Cantidad Disponible: " + producto.getCantidad_Disponible());
        JLabel categoriaLabel = new JLabel("Categoría: " + producto.getID_CategoriaProducto());

        Font font = new Font("Arial", Font.PLAIN, 14);
        Color textColor = Color.BLACK;
        Color backgroundColor = new Color(240, 240, 240);
        Border border = BorderFactory.createLineBorder(Color.GRAY); 

        JLabel[] labels = {precioLabel, cantidadLabel, categoriaLabel};
        for (JLabel label : labels) {
            label.setFont(font);
            label.setForeground(textColor);
            label.setBounds(posicionHorizontal, 260, (int)espacioDisponible, 20);
            label.setHorizontalAlignment(SwingConstants.CENTER);

            label.setBorder(border);
            label.setOpaque(true);
            label.setBackground(backgroundColor);

            detallesProductoPanel.add(label);

            posicionHorizontal += espacioDisponible;
        }
        
        int cantidadBotones = 4; 
        espacioDisponible = anchoRestado / cantidadBotones;
        posicionHorizontal = 323;

        // Crear botones
        JButton comprarButton = new JButton("Comprar Producto");
        JButton mandarMensajeButton = new JButton("Mandar Mensaje");
        JButton agregarCarritoButton = new JButton("Agregar al Carrito");
       JButton hacerComentarioButton = new JButton("Hacer Comentario");

        JButton[] botones = {comprarButton, mandarMensajeButton, agregarCarritoButton, hacerComentarioButton};

        for (JButton boton : botones) {
            boton.setBounds(posicionHorizontal, 300, (int)espacioDisponible, 70);
            boton.setHorizontalAlignment(SwingConstants.CENTER);

            // Personalizar el aspecto de los botones
            boton.setFont(font);
            boton.setForeground(textColor);
            boton.setBorder(border);
            boton.setOpaque(true);
            boton.setBackground(backgroundColor);

            detallesProductoPanel.add(boton);

            posicionHorizontal += espacioDisponible;
        }


         comprarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener el ID del comprador
                int ID_Comprador = SesionActiva.getID_Usuario();

                // Verificar si el usuario es el propietario del producto
                if (ID_Comprador == producto.getID_Usuario()) {
                    JOptionPane.showMessageDialog(null, "No puedes comprar tu propio producto.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Solicitar la cantidad de productos que desea comprar
                String cantidadStr = JOptionPane.showInputDialog(null, "Ingrese la cantidad de productos que desea comprar:");

                try {
                    // Convertir la cantidad ingresada a un entero
                    int cantidad = Integer.parseInt(cantidadStr);

                    // Verificar si la cantidad ingresada es válida
                    if (producto != null && cantidad > 0 && cantidad <= producto.getCantidad_Disponible()) {
                        double totalAPagar = producto.getPrecio() * cantidad;

                        // Guardar el total a pagar en la sesión activa
                        SesionActiva.setTotalAPagar(totalAPagar);

                        // Abrir la pantalla de compra
                        PantallaCompraProducto pantallaDeCompra = new PantallaCompraProducto(usuario, conexion, SesionActiva.getID_Producto(), totalAPagar, cantidad);
                        pantallaDeCompra.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "La cantidad solicitada no está disponible.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido para la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


           mandarMensajeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int ID_Comprador = SesionActiva.getID_Usuario();

                // Verificar si el usuario es el propietario del producto
                if (ID_Comprador == producto.getID_Usuario()) {
                    JOptionPane.showMessageDialog(null, "No puedes enviarte un mensaje a ti mismo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ConversacionesVista conversacionesVista = new ConversacionesVista(usuario, ID_Vendedor, ID_Comprador);
                conversacionesVista.setVisible(true);
                dispose();
            }
        });

            
            
        agregarCarritoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int ID_Producto = producto.getID_Producto();

                // Verificar si el usuario es el propietario del producto
                if (SesionActiva.getID_Usuario() == producto.getID_Usuario()) {
                    JOptionPane.showMessageDialog(null, "No puedes agregar tus propios productos al carrito.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

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

        
        hacerComentarioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HacerComentario hacerComentario = new HacerComentario();
                hacerComentario.setVisible(true);
            }
        });


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
       
    int alturaPanel = 370; 
    int espacioLateral = 20; 
    comentariosPanel.removeAll();
    comentariosPanel.setLayout(new BoxLayout(comentariosPanel, BoxLayout.Y_AXIS)); // Establecer un diseño de caja vertical
    
    ControladorComentario controladorComentario = new ControladorComentario(); 
    
    List<Comentario> comentarios = controladorComentario.obtenerComentariosPorIDProducto(SesionActiva.getID_Producto());
    
    String ImagenRuta;
    double anchoPantalla = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

    double anchoRestado = anchoPantalla - 150;

        
   for (Comentario comentario : comentarios) {
    JPanel comentarioPanel = new JPanel();        
    comentarioPanel.setPreferredSize(new Dimension(getContentPane().getWidth() - (2 * espacioLateral), alturaPanel));
    comentarioPanel.setLayout(null);
    comentarioPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    comentarioPanel.setBackground(Color.WHITE); 
    
     String ImgURL = controladorConversacion.obtenerImagenURLPorID(comentario.getID_Usuario());
        // Ruta predeterminada de la imagen de respaldo
            String imagenPredeterminada = System.getProperty("user.dir") + "\\src\\main\\java\\Imagenes\\Sinimagen.jpg";

            // Ruta de la imagen proporcionada por el producto
            String imagenRutaProducto = System.getProperty("user.dir") + "\\src\\main\\java\\Imagenes\\" + ImgURL;
            System.out.println(ImgURL);
            // Verificar si la imagen del producto existe
            File imagenProducto = new File(imagenRutaProducto);
            if (imagenProducto.exists()) {
                // Si la imagen del producto existe, usar esa ruta
                 ImagenRuta = imagenRutaProducto;
            } else {
                // Si no existe, usar la imagen predeterminada
                ImagenRuta = imagenPredeterminada;
            }
            
    // Crear el panel rojo
        JPanel panelImagen = new JPanel();
        panelImagen.setBackground(Color.WHITE);
        panelImagen.setBounds(20, 15, 60, 60);
        comentarioPanel.add(panelImagen);
        
        JLabel labelImagen = new JLabel();
        ImageIcon icono = new ImageIcon(ImagenRuta);
        Image imagen = icono.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagen);
        labelImagen.setIcon(iconoEscalado);
        labelImagen.setBounds(0, 0, 60, 60);
        panelImagen.add(labelImagen);
        
        // Crear JPanel para el nombre
        JPanel panelNombre = new JPanel();
        panelNombre.setBackground(Color.BLUE);
        panelNombre.setBounds(100, 25, (int)anchoRestado, 30);
        panelNombre.setLayout(new BorderLayout()); 
        comentarioPanel.add(panelNombre);

        String NombreUsuario = controladorProducto.obtenerNombreUsuarioporID(comentario.getID_Usuario());
        
        // Crear JLabel para el nombre
        JLabel nombreLabel = new JLabel("Usuario: " + NombreUsuario + "                              Fecha de Mensaje: " + comentario.getFecha_Comentario());
        nombreLabel.setForeground(Color.WHITE);
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 14)); 
        nombreLabel.setHorizontalAlignment(JLabel.CENTER);
        panelNombre.add(nombreLabel, BorderLayout.CENTER); 

        panelNombre.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));


   // Crear JPanel para el nombre
        JPanel ComentarioD = new JPanel();
        ComentarioD.setBackground(Color.BLUE);
        ComentarioD.setBounds(15, 85, (int)anchoPantalla - 50, 20);
        ComentarioD.setLayout(new BorderLayout()); 
        comentarioPanel.add(ComentarioD);

        // Crear JLabel para el nombre
        JLabel ComentarioTxt = new JLabel("Comentario:");
        ComentarioTxt.setForeground(Color.WHITE);
        ComentarioTxt.setFont(new Font("Arial", Font.BOLD, 14)); 
        ComentarioTxt.setHorizontalAlignment(JLabel.CENTER);
        ComentarioD.add(ComentarioTxt, BorderLayout.CENTER); 

        ComentarioD.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Establecer un relleno en los bordes del JPanel

     

        JTextArea descripcionTextArea = new JTextArea(comentario.getComentario());
        descripcionTextArea.setBounds(15, 105, (int)anchoPantalla - 50, 120); 
        descripcionTextArea.setLineWrap(true); 
        descripcionTextArea.setWrapStyleWord(true); 
        descripcionTextArea.setEditable(false); 
        descripcionTextArea.setBackground(Color.WHITE);
        descripcionTextArea.setForeground(Color.BLACK); 
        descripcionTextArea.setFont(new Font("Arial", Font.PLAIN, 14)); 

        // Agregar un borde al JTextArea
        descripcionTextArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5) 
        ));
        
        comentarioPanel.add(descripcionTextArea);

        
        JButton editarButton = new JButton("Editar");
        editarButton.setBounds(15, 230, (int)anchoPantalla - 50, 30); // Establecer la posición y el tamaño del botón
        editarButton.setBackground(Color.GREEN); // Establecer el color de fondo del botón
        editarButton.setForeground(Color.WHITE); // Establecer el color del texto del botón
        editarButton.setFocusPainted(false); // Quitar el borde de enfoque
        editarButton.setBorder(BorderFactory.createRaisedBevelBorder()); // Establecer un borde en relieve
        editarButton.setFont(new Font("Arial", Font.BOLD, 12)); // Establecer la fuente y el tamaño del texto

        JButton borrarButton = new JButton("Borrar");
        borrarButton.setBounds(15, 270, (int)anchoPantalla - 50, 30); // Establecer la posición y el tamaño del botón
        borrarButton.setBackground(Color.RED); // Establecer el color de fondo del botón
        borrarButton.setForeground(Color.WHITE); // Establecer el color del texto del botón
        borrarButton.setFocusPainted(false); // Quitar el borde de enfoque
        borrarButton.setBorder(BorderFactory.createRaisedBevelBorder()); // Establecer un borde en relieve
        borrarButton.setFont(new Font("Arial", Font.BOLD, 12)); // Establecer la fuente y el tamaño del texto

            
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
                     comentariosPanel.remove(comentarioPanel); // Quitar el panel del comentario del panel principal
                    comentariosPanel.revalidate(); // Volver a validar el panel para que se actualice la disposición
                    comentariosPanel.repaint(); // Volver a pintar el panel para que se muestren los cambios
                }
            }
        });
           
        comentarioPanel.add(editarButton);

        comentarioPanel.add(borrarButton);


        
    comentariosPanel.add(comentarioPanel); 
}
    
    comentariosPanel.revalidate(); // Volver a validar el panel para que se actualice la disposición
    comentariosPanel.repaint(); // Volver a pintar el panel para que se muestren los cambios
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
