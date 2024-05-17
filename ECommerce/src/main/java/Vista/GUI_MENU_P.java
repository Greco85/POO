
package Vista;

import Controladores.ControladorInicioSesion;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JFrame;
import javax.swing.*;


public class GUI_MENU_P extends javax.swing.JFrame {
    
    public GUI_MENU_P() {
        Componentes();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 383, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void Componentes() {
        jPanel2 = new ImagePanel();
        jPanel1 = new ImagePanel(); 
        jPanel3 = new JPanel();
        jLabel1P = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jTextField1P = new JTextField();
        jTextField2 = new JTextField();
        jButton1P = new JButton();
        jButton2 = new JButton();
        
        
        jPanel3.addComponentListener(new ComponentAdapter() {
                @Override
            public void componentResized(ComponentEvent e) {
                int anchopanel3 = jPanel3.getWidth();
                System.out.println(anchopanel3);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setLayout(new java.awt.BorderLayout());
        jPanel1.setBackground(Color.WHITE);
        jPanel2.add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel3.setBackground(Color.WHITE);
        jPanel3.setLayout(null); 

        
        jLabel1P.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 40));
        jLabel1P.setForeground(Color.BLACK); 
        jLabel1P.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1P.setText("INICIO DE SESIÓN");
        jLabel1P.setOpaque(true); 
        jLabel1P.setBackground(Color.WHITE);
        jLabel1P.setBounds(0, 80, 640, 50); 
        jPanel3.add(jLabel1P);
        

        jLabel2.setFont(new java.awt.Font("Quicksand Medium", java.awt.Font.BOLD, 14)); 
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setText("  Correo Electrónico");
        jLabel2.setOpaque(true); 
        jLabel2.setBackground(new Color(30, 144, 255)); 
        jLabel2.setBounds(20, 150, 600, 40); 
        jPanel3.add(jLabel2);

        
        
        jTextField2.setText("Introduce tu Correo Electrónico");
        jTextField2.setForeground(new Color(64, 64, 64));
        jTextField2.setBackground(new Color(240, 240, 240)); 
        jTextField2.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(200, 200, 200)), // Borde con sombra
                BorderFactory.createEmptyBorder(10, 10, 10, 10) 
        ));
        jTextField2.setBounds(20, 190, 600, 70); 
        
        
        jTextField2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextField2.getText().equals("Introduce tu Correo Electrónico")) {
                    jTextField2.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jTextField2.getText().isEmpty()) {
                    jTextField2.setText("Introduce tu Correo Electrónico");
                }
            }
        });
        
        jPanel3.add(jTextField2);
        
      


        jLabel3.setFont(new java.awt.Font("Quicksand Medium", java.awt.Font.BOLD, 14)); // Aumentar el tamaño y hacerlo en negrita
        jLabel3.setForeground(Color.WHITE); 
        jLabel3.setText("  Contraseña");
        jLabel3.setOpaque(true); 
        jLabel3.setBackground(new Color(30, 144, 255));
        jLabel3.setBounds(20, 280, 600, 40); 
        jPanel3.add(jLabel3);
        
        Color ColorTF = new Color(240, 240, 240);

        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField2.setBackground(ColorTF);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField2.setBackground(Color.WHITE);
            }
        });

        jTextField1P.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField1P.setBackground(ColorTF);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField1P.setBackground(Color.WHITE);
            }
        });
        

        jTextField1P.setText("Introduce tu Contraseña");
        jTextField1P.setForeground(new Color(64, 64, 64)); 
        jTextField1P.setBackground(new Color(240, 240, 240)); 
        jTextField1P.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(200, 200, 200)), 
                BorderFactory.createEmptyBorder(10, 10, 10, 10) 
        ));
        jTextField1P.setBounds(20, 320, 600, 70); 
        jPanel3.add(jTextField1P);

        
        jTextField1P.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextField1P.getText().equals("Introduce tu Contraseña")) {
                    jTextField1P.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jTextField1P.getText().isEmpty()) {
                    jTextField1P.setText("Introduce tu Contraseña");
                }
            }
        });
        jPanel3.add(jTextField1P);
        
        
        
        jButton1P.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16)); 
        jButton1P.setText("INGRESAR");
        jButton1P.setBackground(new Color(51, 102, 255));
        jButton1P.setForeground(Color.WHITE); 
        jButton1P.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)), 
                BorderFactory.createEmptyBorder(15, 50, 15, 50)
        ));
        jButton1P.setBounds(20, 460, 600, 70); 
        jButton1P.setFocusPainted(false); 
        jPanel3.add(jButton1P);


        jButton2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16)); 
        jButton2.setText("REGISTRARSE");
        jButton2.setBackground(new Color(102, 102, 102)); 
        jButton2.setForeground(Color.WHITE); 
        jButton2.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255)), 
                BorderFactory.createEmptyBorder(15, 50, 15, 50)
        ));
        jButton2.setBounds(20, 550, 600, 70); 
        jButton2.setFocusPainted(false); 
        jPanel3.add(jButton2);
        
        jButton1P.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1P.setBackground(new Color(41, 81, 204)); 
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1P.setBackground(new Color(51, 102, 255)); 
            }
        });

        // Ajuste del color al pasar el mouse para el botón REGISTRARSE
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2.setBackground(new Color(82, 82, 82)); 
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2.setBackground(new Color(102, 102, 102)); 
            }
        });

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2);
        
        jButton1P.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1PActionPerformed(evt);
            }
        });

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        pack();
    }
    
    private void jButton1PActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String correo = jTextField2.getText();
        String contraseña = jTextField1P.getText();
        ControladorInicioSesion.validarCredenciales(this, correo, contraseña); // Pasar el objeto Usuario
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
       Registrarse registroFrame = new Registrarse();
       registroFrame.setVisible(true);
       this.dispose();
    }  
    
   
     public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_MENU_P().setVisible(true);
            }
        });
    }
     
    
    private javax.swing.JButton jButton1P;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1P;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1P;
    private javax.swing.JTextField jTextField2;
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    // End of variables declaration//GEN-END:variables
}
