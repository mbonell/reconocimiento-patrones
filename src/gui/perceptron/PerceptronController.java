package gui.perceptron;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import core.Perceptron;

public class PerceptronController {
	
	Perceptron perceptron = new Perceptron();


	public void listenerPerceptron(final PerceptronContainer perceptronGui){

		perceptronGui.btnAgregarSetAprendizaje.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) perceptronGui.tablaSetAprendizaje.getModel();
				Object nuevo[]= {"0","0", "0", "0", "0"};
				temp.addRow(nuevo);
		    }
		});

		perceptronGui.btnEliminarSetAprendizaje.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) perceptronGui.tablaSetAprendizaje.getModel();
		    	if(temp.getRowCount() > 0){
		    		temp.removeRow(temp.getRowCount()-1);
		    	}
		    }
		});

		perceptronGui.btnAgregarSetPrueba.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) perceptronGui.tablaSetPruebas.getModel();
				Object nuevo[]= {"0","0", "0", "0", "-"};
				temp.addRow(nuevo);
		    }
		});

		perceptronGui.btnEliminarSetPrueba.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) perceptronGui.tablaSetPruebas.getModel();
		    	if(temp.getRowCount() > 0){
		    		temp.removeRow(temp.getRowCount()-1);
		    	}
		    }
		});
		perceptronGui.btnEntrenar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				perceptronGui.visibleSalida(false);
				perceptronGui.lblNoEntrenada.setVisible(true);
				perceptronGui.lblNoEntrenada.setText("<html><b>Entrenando...</b></html>");
									
				
				perceptron.setUmbralInicial(Double.valueOf(perceptronGui.txtUmbralInicial.getText()));
				perceptron.setUmbralFinal(Double.valueOf(perceptronGui.txtUmbralInicial.getText()));
				perceptron.setRazonAprendizaje(Double.valueOf(perceptronGui.txtRazonAprendizaje.getText()));
				perceptron.setLimiteEpocas(Integer.valueOf(perceptronGui.txtLimiteEpocasInicial.getText()));
				
				perceptron.pesos.put(perceptron.LONGITUD_SEPALO, Double.valueOf(perceptronGui.txtPesoLongitudSepalo.getText()));
				perceptron.pesos.put(perceptron.ANCHO_SEPALO, Double.valueOf(perceptronGui.txtPesoAnchoSepalo.getText()));
				perceptron.pesos.put(perceptron.LONGITUD_PETALO, Double.valueOf(perceptronGui.txtPesoLongitudPetalo.getText()));
				perceptron.pesos.put(perceptron.ANCHO_PETALO, Double.valueOf(perceptronGui.txtPesoAnchoPetalo.getText()));
				
				perceptron.entrenar(getData(perceptronGui.tablaSetAprendizaje.getModel()));
				

				perceptronGui.lblNoEntrenada.setVisible(false);
				perceptronGui.visibleSalida(true);
				
				//Valores finales
				DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
				simbolos.setDecimalSeparator('.');
				DecimalFormat formato = new DecimalFormat("#####.##",simbolos);
				
				perceptronGui.lblPesoLongitudSepaloValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.LONGITUD_SEPALO))+"</b></html>");
				perceptronGui.lblPesoAnchoSepaloValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.ANCHO_SEPALO))+"</b></html>");
				perceptronGui.lblPesoLongitudPetaloValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.LONGITUD_PETALO))+"</b></html>");
				perceptronGui.lblPesoAnchoPetaloValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.ANCHO_PETALO))+"</b></html>");

				perceptronGui.lblUmbralValor.setText("<html><b>"+formato.format(perceptron.getUmbralFinal())+"</b></html>");
				perceptronGui.lblEpocasValor.setText("<html><b>"+formato.format(perceptron.getNumeroEpocasFinal())+"</b></html>");


			}
		});
		
		perceptronGui.btnClasificar.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	perceptronGui.ventanaClasificaciones();
		    
		    }
		});
		
		perceptronGui.btnClasificarPrueba.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	String[][] clasificados = perceptron.clasificar(getData(perceptronGui.tablaSetPruebas.getModel()));
				DefaultTableModel resultModel =  (DefaultTableModel) perceptronGui.tablaSetPruebas.getModel();
				
				
				for(int j=0; j<clasificados.length; j++){
					resultModel.setValueAt(clasificados[j][4], j, 4);
				}

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
