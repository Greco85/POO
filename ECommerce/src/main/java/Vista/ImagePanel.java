
package Vista;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

// Subclase de JPanel para mostrar una imagen de fondo
class ImagePanel extends JPanel {

    private Image backgroundImage;

    public ImagePanel() {
        // Cargar la imagen desde el archivo
        ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Imagenes\\FondoInicioSesion.jpg");
        backgroundImage = icon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar la imagen de fondo
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Obtener el tamaño de la pantalla
        int screenWidth = (int) screenSize.getWidth(); // Obtener el ancho de la pantalla
        int screenHeight = (int) screenSize.getHeight(); // Obtener el alto de la pantalla
        int width = screenWidth / 2; // Ancho deseado como la mitad del ancho de la pantalla
        int height = screenHeight; // Alto deseado igual al alto de la pantalla
        g.drawImage(backgroundImage, 0, 0, width, height, this);
    }

    @Override
    public Dimension getPreferredSize() {
        // Establecer el tamaño preferido del panel para que coincida con el tamaño de la imagen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Obtener el tamaño de la pantalla
        int screenWidth = (int) screenSize.getWidth(); // Obtener el ancho de la pantalla
        int screenHeight = (int) screenSize.getHeight(); // Obtener el alto de la pantalla
        int width = screenWidth / 2; // Ancho deseado como la mitad del ancho de la pantalla
        int height = screenHeight; // Alto deseado igual al alto de la pantalla
        return new Dimension(width, height);
    }
}


