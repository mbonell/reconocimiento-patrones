package gui.quickpropagation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.math.plot.Plot2DPanel;
import plot.Cartesian;
import core.QuickPropagation;

public class QuickPropagationController {
	
	QuickPropagation qp = new QuickPropagation();


	public void listenerPrincipal(final QuickPropagationContainer qpGui){

		qpGui.btnAgregarSetAprendizaje.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) qpGui.tablaSetAprendizaje.getModel();
				Object nuevo[]= {"0","0", "0", "0", "0", "0", "0", "0", "0", "0"};
				temp.addRow(nuevo);
		    }
		});

		qpGui.btnEliminarSetAprendizaje.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) qpGui.tablaSetAprendizaje.getModel();
		    	if(temp.getRowCount() > 0){
		    		temp.removeRow(temp.getRowCount()-1);
		    	}
		    }
		});

		qpGui.btnAgregarSetPrueba.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) qpGui.tablaSetPruebas.getModel();
				Object nuevo[]= {"0","0", "0", "0", "0", "0", "0", "0", "-"};
				temp.addRow(nuevo);
		    }
		});

		qpGui.btnEliminarSetPrueba.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) qpGui.tablaSetPruebas.getModel();
		    	if(temp.getRowCount() > 0){
		    		temp.removeRow(temp.getRowCount()-1);
		    	}
		    }
		});
		
		qpGui.btnEntrenar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				qpGui.visibleSalida(false);
				qpGui.lblNoEntrenada.setVisible(true);
				qpGui.lblNoEntrenada.setText("<html><b>Entrenando...</b></html>");
					
				//Establecer la arquitectura
				qp.setNumeroCapasOcultas(Integer.valueOf(qpGui.txtCapas.getText()));
				qp.setNumeroNeuronasPorCapa(Integer.valueOf(qpGui.txtNeuronas.getText()));

				
				//Establecer los parametros
				qp.setRazonAprendizaje(Double.valueOf(qpGui.txtRazonAprendizaje.getText()));
				qp.setLimiteEpocas(Integer.valueOf(qpGui.txtLimiteEpocasInicial.getText()));
				qp.setErrorDeseado(Double.valueOf(qpGui.txtErrorDeseado.getText()));

				//Inicializar los pesos segun las capas, neuronas, entradas
				qp.inicializarPesos();
				qp.salidasPorCapa = new String[qp.getNumeroCapasOcultas()][qp.getNumeroNeuronasPorCapa()];
				qp.sensibilidades = new double[qp.getNumeroCapasOcultas()][qp.getNumeroNeuronasPorCapa()];
				//Inicializar pesos para la neurona de salida (Adaline)
				qp.inicializarPesosAdaline();
				
				//Imprimir pesos iniciales
				
				for(int m = 0; m<qp.getNumeroCapasOcultas(); m++){
					System.out.println("CAPA OCULTA " + m);
					for (int i = 0; i < qp.getNumeroNeuronasPorCapa(); ++i){
						System.out.println("NEURONA " + i);
						for(int j = 0; j<=qp.getNumeroEntradas(); j++){
							
							System.out.println("Peso " + j + ": =>" + qp.getPesoLlaveEntera(m, i, j));
						}
					
					}
				}
				
				
				//Imprimir pesos Adaline
				
				System.out.println("Adaline");
				for(int j = 0; j<qp.getNumeroNeuronasPorCapa()+1; j++){
					System.out.println("Peso " + j + ": =>" + qp.getPesoAdaline(j));
				}
				
				qp.entrenar(getData(qpGui.tablaSetAprendizaje.getModel()));
				
				
				qpGui.lblNoEntrenada.setVisible(false);
				qpGui.lblErrorFinal.setVisible(true);
				qpGui.lblErrorValor.setVisible(true);
				qpGui.lblEpocasValor.setVisible(true);
				qpGui.lblEpocasFinal.setVisible(true);
				qpGui.btnClasificar.setVisible(true);
				qpGui.btnError.setVisible(true);

				
				//Valores finales
				qpGui.lblEpocasValor.setText("<html><b>"+qp.getNumeroEpocasFinal()+"</b></html>");
				qpGui.lblErrorValor.setText("<html><b>"+qp.getErrorFinal()+"</b></html>");

				

				
			}
		});
		
		qpGui.btnClasificar.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	qpGui.ventanaClasificaciones();
		    
		    }
		});
		
		qpGui.btnError.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
				Cartesian grafica = new Cartesian();
				
				for(int i = 0; i < qp.getNumeroEpocasFinal(); i++){

					grafica.addPoint(Color.red, (float)i+1, Double.valueOf(qp.errores.get(i)));
				}
				
		    	Plot2DPanel plot = grafica.plot();
			
				plot.setAxisLabel(0, "Época");
				plot.setAxisLabel(1, "Error");
				
				qpGui.ventanaGraficaErrores(plot);
		    
		    }
		});
		
		qpGui.btnClasificarPrueba.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	String[][] clasificados = qp.clasificar(getData(qpGui.tablaSetPruebas.getModel()));
				DefaultTableModel resultModel =  (DefaultTableModel) qpGui.tablaSetPruebas.getModel();
				
				DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
				simbolos.setDecimalSeparator('.');
				DecimalFormat formato = new DecimalFormat("#####.##",simbolos);
				double efectividad  = 0;
				double porcentaje = 0;
				
				//Obtener las respuestas correctas
				String [] respuestas = qpGui.obtenerRespuestasCorrectas(clasificados.length);
				
				for(int j=0; j<clasificados.length; j++){
					resultModel.setValueAt(clasificados[j][8], j, 8);
					
					if(clasificados[j][8].equals(respuestas[j]))
						efectividad++;
				}
				
				porcentaje = efectividad/clasificados.length;
				qpGui.lblEfectividad.setText("Porcentaje de efectividad: " + formato.format(porcentaje*100) + "%");

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
