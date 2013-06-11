package gui;
import gui.distancia_minima.DistanciaMinimaContainer;
import gui.distancia_minima.DistanciaMinimaController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DesktopController {
	
	DesktopContainer desk;

	
	public DesktopController(DesktopContainer d) {
		this.desk=d;	
		addListeners();
	}
	

	private void addListeners(){
		
		desk.itemSalir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				desk.frame.dispose();
			}			
		});
		
		
		desk.itemDistanciaMinima.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				DistanciaMinimaContainer distanciaMinimaGui = new DistanciaMinimaContainer();
				DistanciaMinimaController distanciaMinimaController = new DistanciaMinimaController();
				
				distanciaMinimaGui.ventanaDistanciaMinima(desk.desk);
				distanciaMinimaController.listenerIframe(distanciaMinimaGui);
			}			
		});

		
		desk.itemAcerca.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				desk.iframeAcerca();
			}			
		});
		
		/*
		desk.btnEjecutar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

		}
			
		});*/
		

		}
}
