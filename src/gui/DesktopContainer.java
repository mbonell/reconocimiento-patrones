package gui;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.io.File;


public class DesktopContainer extends JFrame{

	private static final long serialVersionUID = 1L;

	Image img;
	JDesktopPane desk;
	JInternalFrame iframeG,iframeA;
	JFrame frame;
	
	JMenuBar menubar = new JMenuBar();
	JMenu menuAdmon = new JMenu("Clasificadores");
	JMenu menuAcerca = new JMenu("Acerca de");
	
	JMenuItem itemDistanciaMinima = new JMenuItem("Distancia Mínima");
	JMenuItem itemSalir = new JMenuItem("Salir");
	JMenuItem itemAcerca = new JMenuItem("Prácticas");
	
	JSeparator separador = new JSeparator();
	public File imagen = new File ("imagenes/adn.jpg"); 
	JFileChooser fc = new JFileChooser();



	/* Agregar componentes a la aplicaci�n*/
	public DesktopContainer(String a){
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		desk = new JDesktopPane();
		cargarImagen(desk, imagen);
		
		menuAdmon.add(itemDistanciaMinima);
		menuAdmon.add(separador);
		menuAdmon.add(itemSalir);
		menuAcerca.add(itemAcerca);
		menubar.add(menuAdmon);
		menubar.add(menuAcerca);
		
		frame.setJMenuBar(menubar);
		frame.add(desk);
		frame.setSize(800,600);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}
	
	/**
	 * Carga la imagen de fondo del contenedor de practicas.
	 * @param jDeskp
	 * @param fileImagen
	 */
    public  void cargarImagen(javax.swing.JDesktopPane jDeskp, File fileImagen){
        try{
            BufferedImage image = ImageIO.read(fileImagen);
              jDeskp.setBorder(new PintaImagen(image)); }
        catch (Exception e){  
        	System.out.println("Error al cargar Imagen");   
        }
    }
    

    
    /* Interfaz de la gr�fica del AGS*/
    public void iframeGrafica(){
    	iframeG = new JInternalFrame("Gr�fica", true, true, true, true);
		iframeG.setBounds(700, 50, 580, 370);
		iframeG.setResizable(false);
		iframeG.setMaximizable(false);
		iframeG.setVisible(true);
		desk.add(iframeG);
		iframeG.setToolTipText("Gr�fica");
		iframeG.setLayout(null);	
		
		JLabel lb = new JLabel();
		lb.setBounds(1, 1, 100, 100);
		lb.setIcon(new ImageIcon("imagenes/grafica.png"));
		iframeG.add(lb);
    }
    
    /**
     * Crea la ventana para mostrar la información sobre
     * las prácticas.
     */
    public void iframeAcerca(){
    	iframeA = new JInternalFrame("Acerca de..", true, true, true, true);
		iframeA.setBounds(200, 50, 320, 200);
		iframeA.setResizable(false);
		iframeA.setMaximizable(false);
		iframeA.setVisible(true);
		desk.add(iframeA);
		iframeA.setToolTipText("Acerca de..");
		iframeA.setLayout(null);	
		
		JLabel lb = new JLabel("<html><b><center><font color='blue'> Reconocimento de Patrones</font></center></b><br>"+
		"<b>Desarrollador:</b> Marcela Bonell Manjarrez.<br>"+  
		"<b>Universidad Autónoma de Guadalajara.</b><br>"+  
		"<b>Profesora:</b> Dra. Nancy Arana Daniel.<br>"+   
		"<b>Maestría en Ciencias Computacionales</b><br>"+  
		"<b>Periodo:</b> Mayo-Agosto del 2013.</html>");

		lb.setBounds(10, 2, 500, 150);
		iframeA.add(lb);
    }
}