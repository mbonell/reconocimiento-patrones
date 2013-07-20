package gui.diabetes_perceptron;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import core.PerceptronDiabetes;

public class DiabetesPerceptronController {
	
	PerceptronDiabetes perceptron = new PerceptronDiabetes();


	public void listenerPrincipal(final DiabetesPerceptronContainer diabetesPerceptronGui){

		diabetesPerceptronGui.btnAgregarSetAprendizaje.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) diabetesPerceptronGui.tablaSetAprendizaje.getModel();
				Object nuevo[]= {"0","0", "0", "0", "0", "0", "0", "0", "0"};
				temp.addRow(nuevo);
		    }
		});

		diabetesPerceptronGui.btnEliminarSetAprendizaje.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) diabetesPerceptronGui.tablaSetAprendizaje.getModel();
		    	if(temp.getRowCount() > 0){
		    		temp.removeRow(temp.getRowCount()-1);
		    	}
		    }
		});

		diabetesPerceptronGui.btnAgregarSetPrueba.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) diabetesPerceptronGui.tablaSetPruebas.getModel();
				Object nuevo[]= {"0","0", "0", "0", "0", "0", "0", "0", "-"};
				temp.addRow(nuevo);
		    }
		});

		diabetesPerceptronGui.btnEliminarSetPrueba.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) diabetesPerceptronGui.tablaSetPruebas.getModel();
		    	if(temp.getRowCount() > 0){
		    		temp.removeRow(temp.getRowCount()-1);
		    	}
		    }
		});
		
		diabetesPerceptronGui.btnEntrenar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				diabetesPerceptronGui.visibleSalida(false);
				diabetesPerceptronGui.lblNoEntrenada.setVisible(true);
				diabetesPerceptronGui.lblNoEntrenada.setText("<html><b>Entrenando...</b></html>");
													
				perceptron.setRazonAprendizaje(Double.valueOf(diabetesPerceptronGui.txtRazonAprendizaje.getText()));
				perceptron.setLimiteEpocas(Integer.valueOf(diabetesPerceptronGui.txtLimiteEpocasInicial.getText()));
				perceptron.setUmbralInicial(Double.valueOf(diabetesPerceptronGui.txtUmbral.getText()));
				perceptron.setUmbralFinal(Double.valueOf(diabetesPerceptronGui.txtUmbral.getText()));

				
				perceptron.pesos.put(perceptron.EMBARAZOS, Double.valueOf(diabetesPerceptronGui.txtPesoEmbarazo.getText()));
				perceptron.pesos.put(perceptron.CONCENTRACION_GLUCOSA, Double.valueOf(diabetesPerceptronGui.txtPesoConcentracionGlucosa.getText()));
				perceptron.pesos.put(perceptron.PRESION_ARTERIAL, Double.valueOf(diabetesPerceptronGui.txtPesoPresionArterial.getText()));
				perceptron.pesos.put(perceptron.GROSOR_TRICEPS, Double.valueOf(diabetesPerceptronGui.txtPesoGrosorTriceps.getText()));
				perceptron.pesos.put(perceptron.INSULINA, Double.valueOf(diabetesPerceptronGui.txtPesoInsulina.getText()));
				perceptron.pesos.put(perceptron.MASA_CORPORAL, Double.valueOf(diabetesPerceptronGui.txtPesoMasaCorporal.getText()));
				perceptron.pesos.put(perceptron.FUNCION, Double.valueOf(diabetesPerceptronGui.txtPesoFuncion.getText()));
				perceptron.pesos.put(perceptron.EDAD, Double.valueOf(diabetesPerceptronGui.txtPesoEdad.getText()));
				
				
				perceptron.entrenar(getData(diabetesPerceptronGui.tablaSetAprendizaje.getModel()));
				
				
				diabetesPerceptronGui.lblNoEntrenada.setVisible(false);
				diabetesPerceptronGui.visibleSalida(true);
				
				//Valores finales
				DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
				simbolos.setDecimalSeparator('.');
				DecimalFormat formato = new DecimalFormat("#####.##",simbolos);
				
				diabetesPerceptronGui.lblPesoEmbarazoValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.EMBARAZOS))+"</b></html>");
				diabetesPerceptronGui.lblPesoConcentracionGlucosaValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.CONCENTRACION_GLUCOSA))+"</b></html>");
				diabetesPerceptronGui.lblPesoPresionArterialValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.PRESION_ARTERIAL))+"</b></html>");
				diabetesPerceptronGui.lblPesoGrosorTricepsValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.GROSOR_TRICEPS))+"</b></html>");
				diabetesPerceptronGui.lblPesoInsulinaValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.INSULINA))+"</b></html>");
				diabetesPerceptronGui.lblPesoMasaCorporalValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.MASA_CORPORAL))+"</b></html>");
				diabetesPerceptronGui.lblPesoFuncionValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.FUNCION))+"</b></html>");
				diabetesPerceptronGui.lblPesoEdadValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.EDAD))+"</b></html>");

				
				diabetesPerceptronGui.lblUmbralValor.setText("<html><b>"+formato.format(perceptron.getUmbralFinal())+"</b></html>");
				diabetesPerceptronGui.lblEpocasValor.setText("<html><b>"+formato.format(perceptron.getNumeroEpocasFinal())+"</b></html>");


			}
		});
		
		diabetesPerceptronGui.btnClasificar.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	diabetesPerceptronGui.ventanaClasificaciones();
		    
		    }
		});
		
		diabetesPerceptronGui.btnClasificarPrueba.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	String[][] clasificados = perceptron.clasificar(getData(diabetesPerceptronGui.tablaSetPruebas.getModel()));
				DefaultTableModel resultModel =  (DefaultTableModel) diabetesPerceptronGui.tablaSetPruebas.getModel();
				
				DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
				simbolos.setDecimalSeparator('.');
				DecimalFormat formato = new DecimalFormat("#####.##",simbolos);
				double efectividad  = 0;
				double porcentaje = 0;
				
				//Obtener las respuestas correctas
				String [] respuestas = diabetesPerceptronGui.obtenerRespuestasCorrectas(clasificados.length);
				
				for(int j=0; j<clasificados.length; j++){
					resultModel.setValueAt(clasificados[j][8], j, 8);
					
					if(clasificados[j][8].equals(respuestas[j]))
						efectividad++;
				}
				
				porcentaje = efectividad/clasificados.length;
				diabetesPerceptronGui.lblEfectividad.setText("Porcentaje de efectividad: " + formato.format(porcentaje*100) + "%");

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
