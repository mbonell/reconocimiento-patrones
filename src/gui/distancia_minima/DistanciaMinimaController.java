package gui.distancia_minima;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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
				distanciaMinima.generarClasificacion( getData( distanciaMinimaGui.tablaNuevosPatrones.getModel() ) );
				distanciaMinimaGui.ventanaResultados();
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
