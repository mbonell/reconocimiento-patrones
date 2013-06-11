package main;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


import gui.DesktopContainer;
import gui.DesktopController;


/**
 * Clase principal del proyecto.
 * 
 * @author Marcela Bonell Manjarrez
 * @creation 09/07/13
 * @modification 09/07/13
 */
public class Main {

	private static final long serialVersionUID = 1L;
	private static final String appName = "Reconocimiento de Patrones";
	
	public static void main(String mbm[]) {

		try {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty("com.apple.mrj.application.apple.menu.about.name", appName);
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			
			if (System.getProperty("os.name").startsWith("Mac OS X")) {			    
				com.apple.eawt.Application.getApplication().setDockIconImage(
			            new ImageIcon("imagenes/icon.png").getImage() );
			}else{
				System.err.print("=( no OS X");
			}
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: " + e.getMessage());
		} catch (InstantiationException e) {
			System.out.println("InstantiationException: " + e.getMessage());
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException: " + e.getMessage());
		} catch (UnsupportedLookAndFeelException e) {
			System.out.println("UnsupportedLookAndFeelException: "
					+ e.getMessage());
		}

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				new DesktopController(
						new DesktopContainer(appName)
					);

			}
		});

	}
}
