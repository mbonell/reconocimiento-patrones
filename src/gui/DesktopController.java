package gui;
import gui.adaline.AdalineContainer;
import gui.adaline.AdalineController;
import gui.backpropagation.BackPropagationContainer;
import gui.backpropagation.BackPropagationController;
import gui.diabetes_perceptron.DiabetesPerceptronContainer;
import gui.diabetes_perceptron.DiabetesPerceptronController;
import gui.distancia_minima.DistanciaMinimaContainer;
import gui.distancia_minima.DistanciaMinimaController;
import gui.perceptron.PerceptronContainer;
import gui.perceptron.PerceptronController;
import gui.quickpropagation.QuickPropagationContainer;
import gui.quickpropagation.QuickPropagationController;

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
		
		desk.itemPerceptronDiabetes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				DiabetesPerceptronContainer diabetesPerceptronGui = new DiabetesPerceptronContainer();
				DiabetesPerceptronController diabetesPerceptronController = new DiabetesPerceptronController();
				
				diabetesPerceptronGui.ventanaPrincipal(desk.desk);
				diabetesPerceptronController.listenerPrincipal(diabetesPerceptronGui);
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
		
		desk.itemBackPropagation.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				BackPropagationContainer BPGui = new BackPropagationContainer();
				BackPropagationController BPController = new BackPropagationController();
				
				BPGui.ventanaPrincipal(desk.desk);
				BPController.listenerPrincipal(BPGui);
			}			
		});
		
		desk.itemQuickPropagation.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				QuickPropagationContainer QPGui = new QuickPropagationContainer();
				QuickPropagationController QPController = new QuickPropagationController();
				
				QPGui.ventanaPrincipal(desk.desk);
				QPController.listenerPrincipal(QPGui);
			}			
		});
		
		desk.itemAcerca.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				desk.iframeAcerca();
			}			
		});
		
	}
}
