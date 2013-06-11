package gui.distancia_minima;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import core.DistanciaMinima;


public class DistanciaMinimaController {
	
	
	public void listenerDistanciaMinima(final DistanciaMinimaContainer distanciaMinimaGui){
	
		distanciaMinimaGui.btnEjecutar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				DistanciaMinima  distanciaMinima = new DistanciaMinima();
				distanciaMinima.generarClasificacion(distanciaMinimaGui.data);
				distanciaMinimaGui.ventanaResultados();
			}
			
		});

	}

}
