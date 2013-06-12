package gui.distancia_minima;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
				//DistanciaMinima  distanciaMinima = new DistanciaMinima();
				//distanciaMinima.generarClasificacion( getData( distanciaMinimaGui.tablaNuevosPatrones.getModel() ) );
				
				String[][] data = {
						{"160",  "5", "0", "0", "0", "0", "0", "0", "0", "0"},
						{"400", "20", "0", "0", "0", "0", "0", "0", "0", "0"},
						{"100", "15", "0", "0", "0", "0", "0", "0", "0", "0"},
						{"500", "30", "0", "0", "0", "0", "0", "0", "0", "0"},
						{"300", "14", "0", "0", "0", "0", "0", "0", "0", "0"}
						};
				
				Cartesian grafica = new Cartesian();
				for(String[] row : data){
					grafica.addPoint( Float.parseFloat(row[0]), Float.parseFloat(row[1]) );
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
