package gui.backpropagation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import core.BackPropagation;

public class BackPropagationController {
	
	BackPropagation bp = new BackPropagation();


	public void listenerPrincipal(final BackPropagationContainer bpGui){

		bpGui.btnAgregarSetAprendizaje.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) bpGui.tablaSetAprendizaje.getModel();
				Object nuevo[]= {"0","0", "0", "0", "0", "0", "0", "0", "0"};
				temp.addRow(nuevo);
		    }
		});

		bpGui.btnEliminarSetAprendizaje.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) bpGui.tablaSetAprendizaje.getModel();
		    	if(temp.getRowCount() > 0){
		    		temp.removeRow(temp.getRowCount()-1);
		    	}
		    }
		});

		bpGui.btnAgregarSetPrueba.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) bpGui.tablaSetPruebas.getModel();
				Object nuevo[]= {"0","0", "0", "0", "0", "0", "0", "0", "-"};
				temp.addRow(nuevo);
		    }
		});

		bpGui.btnEliminarSetPrueba.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) bpGui.tablaSetPruebas.getModel();
		    	if(temp.getRowCount() > 0){
		    		temp.removeRow(temp.getRowCount()-1);
		    	}
		    }
		});
		
		bpGui.btnEntrenar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				bpGui.visibleSalida(false);
				bpGui.lblNoEntrenada.setVisible(true);
				bpGui.lblNoEntrenada.setText("<html><b>Entrenando...</b></html>");
					
				//Establecer la arquitectura
				bp.setNumeroCapasOcultas(Integer.valueOf(bpGui.txtCapas.getText()));
				bp.setNumeroNeuronasPorCapa(Integer.valueOf(bpGui.txtNeuronas.getText()));

				
				//Establecer los parametros
				bp.setRazonAprendizaje(Double.valueOf(bpGui.txtRazonAprendizaje.getText()));
				bp.setLimiteEpocas(Integer.valueOf(bpGui.txtLimiteEpocasInicial.getText()));
				bp.setErrorDeseado(Double.valueOf(bpGui.txtErrorDeseado.getText()));

				//Inicializar los pesos segun las capas, neuronas, entradas
				bp.inicializarPesos();
				bp.salidasPorCapa = new String[bp.getNumeroCapasOcultas()][bp.getNumeroNeuronasPorCapa()];
				bp.sensibilidades = new double[bp.getNumeroCapasOcultas()][bp.getNumeroNeuronasPorCapa()];
				//Inicializar pesos para la neurona de salida (Adaline)
				bp.inicializarPesosAdaline();
				
				//Imprimir pesos iniciales
				
				for(int m = 0; m<bp.getNumeroCapasOcultas(); m++){
					System.out.println("CAPA OCULTA " + m);
					for (int i = 0; i < bp.getNumeroNeuronasPorCapa(); ++i){
						System.out.println("NEURONA " + i);
						for(int j = 0; j<=bp.getNumeroEntradas(); j++){
							
							System.out.println("Peso " + j + ": =>" + bp.getPesoLlaveEntera(m, i, j));
						}
					
					}
				}
				
				
				//Imprimir pesos Adaline
				
				System.out.println("Adaline");
				for(int j = 0; j<bp.getNumeroNeuronasPorCapa(); j++){
					System.out.println("Peso " + j + ": =>" + bp.getPesoAdaline(j));
				}
				
				bp.entrenar(getData(bpGui.tablaSetAprendizaje.getModel()));
				
				
				bpGui.lblNoEntrenada.setVisible(false);
				bpGui.visibleSalida(true);
				
				//Valores finales
				/*DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
				simbolos.setDecimalSeparator('.');
				DecimalFormat formato = new DecimalFormat("#####.##",simbolos);
				
				bpGui.lblPesoEmbarazoValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.EMBARAZOS))+"</b></html>");
				bpGui.lblPesoConcentracionGlucosaValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.CONCENTRACION_GLUCOSA))+"</b></html>");
				bpGui.lblPesoPresionArterialValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.PRESION_ARTERIAL))+"</b></html>");
				bpGui.lblPesoGrosorTricepsValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.GROSOR_TRICEPS))+"</b></html>");
				bpGui.lblPesoInsulinaValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.INSULINA))+"</b></html>");
				bpGui.lblPesoMasaCorporalValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.MASA_CORPORAL))+"</b></html>");
				bpGui.lblPesoFuncionValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.FUNCION))+"</b></html>");
				bpGui.lblPesoEdadValor.setText("<html><b>"+formato.format(perceptron.getPeso(perceptron.EDAD))+"</b></html>");

				
				bpGui.lblUmbralValor.setText("<html><b>"+formato.format(perceptron.getUmbralFinal())+"</b></html>");
				bpGui.lblEpocasValor.setText("<html><b>"+formato.format(perceptron.getNumeroEpocasFinal())+"</b></html>");
				*/

			}
		});
		
		bpGui.btnClasificar.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	bpGui.ventanaClasificaciones();
		    
		    }
		});
		
		bpGui.btnClasificarPrueba.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	String[][] clasificados = bp.clasificar(getData(bpGui.tablaSetPruebas.getModel()));
				DefaultTableModel resultModel =  (DefaultTableModel) bpGui.tablaSetPruebas.getModel();
				
				DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
				simbolos.setDecimalSeparator('.');
				DecimalFormat formato = new DecimalFormat("#####.##",simbolos);
				double efectividad  = 0;
				double porcentaje = 0;
				
				//Obtener las respuestas correctas
				String [] respuestas = bpGui.obtenerRespuestasCorrectas(clasificados.length);
				
				for(int j=0; j<clasificados.length; j++){
					resultModel.setValueAt(clasificados[j][8], j, 8);
					
					if(clasificados[j][8].equals(respuestas[j]))
						efectividad++;
				}
				
				porcentaje = efectividad/clasificados.length;
				bpGui.lblEfectividad.setText("Porcentaje de efectividad: " + formato.format(porcentaje*100) + "%");

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
