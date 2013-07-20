package main;

import javax.swing.SwingUtilities;
import gui.DesktopContainer;
import gui.DesktopController;


public class Main {

	private static final long serialVersionUID = 1L;
	private static final String appName = "Reconocimiento de Patrones";
	
	public static void main(String mbm[]) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				new DesktopController(
						new DesktopContainer(appName)
					);

			}
		});

	}
}
