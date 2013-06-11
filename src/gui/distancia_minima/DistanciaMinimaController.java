package gui.distancia_minima;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;



public class DistanciaMinimaController {
	
	
	public void listenerIframe(final DistanciaMinimaContainer distanciaMinimaGui){
	
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
		
	}

}
