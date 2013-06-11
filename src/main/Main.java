package main;
import javax.swing.UIManager;
import gui.DesktopContainer;
import gui.DesktopController;

/**
 * Clase principal del proyecto.
 * @author Marcela Bonell Manjarrez
 * @creation 09/07/13
 * @modification 09/07/13
 */
public class Main{

	private static final long serialVersionUID = 1L;

	public static void main(String mbm[]) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		new DesktopController(new DesktopContainer("Reconocimiento de Patrones"));
	}
}


