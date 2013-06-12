package gui.distancia_minima;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import plot.Cartesian;

import core.DistanciaMinima;


public class DistanciaMinimaController {
	

	public void listenerDistanciaMinima(final DistanciaMinimaContainer distanciaMinimaGui){

		distanciaMinimaGui.btnAgregarPatron.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) distanciaMinimaGui.tablaNuevosPatrones.getModel();
				Object nuevo[]= {"-","-"};
				temp.addRow(nuevo);
		    }
		});

		distanciaMinimaGui.btnEliminarPatron.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) distanciaMinimaGui.tablaNuevosPatrones.getModel();
		    	if(temp.getRowCount() > 0){
		    		temp.removeRow(temp.getRowCount()-1);
		    	}
		    }
		});

		distanciaMinimaGui.btnEjecutar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				DistanciaMinima  distanciaMinima = new DistanciaMinima();
				
				String[][] data = distanciaMinima.generarClasificacion( getData( distanciaMinimaGui.tablaNuevosPatrones.getModel() ) ); 
				
				Cartesian grafica = new Cartesian();
				
				HashMap<String, Color> colors = new HashMap<String, Color>();
				colors.put( distanciaMinima.BEISBOL , Color.green);
				colors.put( distanciaMinima.FUTBOL , Color.blue);
				
				for(String[] row : data){
					grafica.addPoint(colors.get( row[ distanciaMinima.DISTANCIA_EUCLIDIANA_CLASE ]  ) , Float.parseFloat(row[ distanciaMinima.PESO ]), Float.parseFloat(row[ distanciaMinima.DIAMETRO ]) );
				}
				
				JInternalFrame  window = distanciaMinimaGui.ventanaResultados();
				
				window.setContentPane( grafica.plot() );
			}
		});
	}
	
	private String[][] getData( TableModel model ){
		String[][] result = new String[ model.getRowCount() ][ model.getColumnCount() ];
		for( int i=0; i < model.getRowCount(); i++ ){
			String [] row = new String[ model.getColumnCount() ];
			for( int j=0; j < model.getColumnCount(); j++ ){
				row[j] = (String) model.getValueAt(i, j);
			}
			result[i] = row;
		}

		return result;
	}
}
