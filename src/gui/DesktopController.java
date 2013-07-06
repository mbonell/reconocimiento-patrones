package gui;
import gui.adaline.AdalineContainer;
import gui.adaline.AdalineController;
import gui.distancia_minima.DistanciaMinimaContainer;
import gui.distancia_minima.DistanciaMinimaController;
import gui.perceptron.PerceptronContainer;
import gui.perceptron.PerceptronController;
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
				distanciaMinimaController.listenerDistanciaMinima(distanciaMinimaGui);
			}			
		});

		desk.itemPerceptron.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				PerceptronContainer perceptronGui = new PerceptronContainer();
				PerceptronController perceptronController = new PerceptronController();
				
				perceptronGui.ventanaPerceptron(desk.desk);
				perceptronController.listenerPerceptron(perceptronGui);
			}			
		});
		
		desk.itemAdaline.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				AdalineContainer adalineGui = new AdalineContainer();
				AdalineController adalineController = new AdalineController();
				
				adalineGui.ventanaAdaline(desk.desk);
				adalineController.listenerAdaline(adalineGui);
			}			
		});
		
		desk.itemAcerca.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				desk.iframeAcerca();
			}			
		});
		
	}
}
