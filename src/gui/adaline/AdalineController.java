package gui.adaline;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import core.Adaline;

public class AdalineController {
	
	Adaline adaline = new Adaline();


	public void listenerAdaline(final AdalineContainer adalineGui){

		adalineGui.btnAgregarSetAprendizaje.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) adalineGui.tablaSetAprendizaje.getModel();
				Object nuevo[]= {"0","0", "0", "0", "0", "0", "0", "0", "0"};
				temp.addRow(nuevo);
		    }
		});

		adalineGui.btnEliminarSetAprendizaje.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) adalineGui.tablaSetAprendizaje.getModel();
		    	if(temp.getRowCount() > 0){
		    		temp.removeRow(temp.getRowCount()-1);
		    	}
		    }
		});

		adalineGui.btnAgregarSetPrueba.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) adalineGui.tablaSetPruebas.getModel();
				Object nuevo[]= {"0","0", "0", "0", "-"};
				temp.addRow(nuevo);
		    }
		});

		adalineGui.btnEliminarSetPrueba.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) adalineGui.tablaSetPruebas.getModel();
		    	if(temp.getRowCount() > 0){
		    		temp.removeRow(temp.getRowCount()-1);
		    	}
		    }
		});
		adalineGui.btnEntrenar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				adalineGui.visibleSalida(false);
				adalineGui.lblNoEntrenada.setVisible(true);
				adalineGui.lblNoEntrenada.setText("<html><b>Entrenando...</b></html>");
									
				
				adaline.setUmbralInicial(Double.valueOf(adalineGui.txtUmbralInicial.getText()));
				adaline.setUmbralFinal(Double.valueOf(adalineGui.txtUmbralInicial.getText()));
				adaline.setRazonAprendizaje(Double.valueOf(adalineGui.txtRazonAprendizaje.getText()));
				adaline.setLimiteEpocas(Integer.valueOf(adalineGui.txtLimiteEpocasInicial.getText()));
				adaline.setErrorDeseado(Double.valueOf(adalineGui.txtErrorDeseado.getText()));

				
				adaline.pesos.put(adaline.EMBARAZOS, Double.valueOf(adalineGui.txtPesoEmbarazo.getText()));
				adaline.pesos.put(adaline.CONCENTRACION_GLUCOSA, Double.valueOf(adalineGui.txtPesoConcentracionGlucosa.getText()));
				adaline.pesos.put(adaline.PRESION_ARTERIAL, Double.valueOf(adalineGui.txtPesoPresionArterial.getText()));
				adaline.pesos.put(adaline.GROSOR_TRICEPS, Double.valueOf(adalineGui.txtPesoGrosorTriceps.getText()));
				adaline.pesos.put(adaline.INSULINA, Double.valueOf(adalineGui.txtPesoInsulina.getText()));
				adaline.pesos.put(adaline.MASA_CORPORAL, Double.valueOf(adalineGui.txtPesoMasaCorporal.getText()));
				adaline.pesos.put(adaline.FUNCION, Double.valueOf(adalineGui.txtPesoFuncion.getText()));
				adaline.pesos.put(adaline.EDAD, Double.valueOf(adalineGui.txtPesoEdad.getText()));
				
				adaline.entrenar(getData(adalineGui.tablaSetAprendizaje.getModel()));
				

				adalineGui.lblNoEntrenada.setVisible(false);
				adalineGui.visibleSalida(true);
				
				//Valores finales
				DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
				simbolos.setDecimalSeparator('.');
				DecimalFormat formato = new DecimalFormat("#####.##",simbolos);
				
				//adalineGui.lblPesoLongitudSepaloValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.LONGITUD_SEPALO))+"</b></html>");
				//adalineGui.lblPesoAnchoSepaloValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.ANCHO_SEPALO))+"</b></html>");
				//adalineGui.lblPesoLongitudPetaloValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.LONGITUD_PETALO))+"</b></html>");
				//adalineGui.lblPesoAnchoPetaloValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.ANCHO_PETALO))+"</b></html>");

				adalineGui.lblUmbralValor.setText("<html><b>"+formato.format(perceptron.getUmbralFinal())+"</b></html>");
				adalineGui.lblEpocasValor.setText("<html><b>"+formato.format(perceptron.getNumeroEpocasFinal())+"</b></html>");


			}
		});
		
		adalineGui.btnClasificar.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	adalineGui.ventanaClasificaciones();
		    
		    }
		});
		
		adalineGui.btnClasificarPrueba.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	String[][] clasificados = perceptron.clasificar(getData(adalineGui.tablaSetPruebas.getModel()));
				DefaultTableModel resultModel =  (DefaultTableModel) adalineGui.tablaSetPruebas.getModel();
				
				
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
