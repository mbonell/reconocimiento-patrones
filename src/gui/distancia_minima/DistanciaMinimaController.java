package gui.distancia_minima;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.math.plot.Plot2DPanel;

import plot.Cartesian;

import core.DistanciaMinima;


public class DistanciaMinimaController {
	

	public void listenerDistanciaMinima(final DistanciaMinimaContainer distanciaMinimaGui){

		distanciaMinimaGui.btnAgregarPatron.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel temp = (DefaultTableModel) distanciaMinimaGui.tablaNuevosPatrones.getModel();
				Object nuevo[]= {"0","0"};
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
				
				
				DefaultTableModel resultModel = new DefaultTableModel(); 
				
				resultModel.addColumn("Peso");
				resultModel.addColumn("Diametro");
				resultModel.addColumn("Euclidiana Béisbol");
				resultModel.addColumn("Euclidiana Fútbol");
				resultModel.addColumn("Euclidiana Mínima");
				resultModel.addColumn("Euclidiana Clase");
				resultModel.addColumn("Manhattan Béisbol");
				resultModel.addColumn("Manhattan Fútbol");
				resultModel.addColumn("Manhattan Mínima");
				resultModel.addColumn("Manhattan Clase");

				
				Cartesian grafica = new Cartesian();
				
				HashMap<String, Color> colors = new HashMap<String, Color>();
				colors.put( distanciaMinima.BEISBOL , Color.green);
				colors.put( distanciaMinima.FUTBOL , Color.blue);
				
				grafica.addPoint( Color.yellow , (float)distanciaMinima.obtenerPesoBeisbol(), (float)distanciaMinima.obtenerDiametroBesibol() ); 
				grafica.addPoint( Color.pink , (float)distanciaMinima.obtenerPesoFutbol(), (float)distanciaMinima.obtenerDiametroFutbol() );
				
				
				for(String[] row : data){
					grafica.addPoint(colors.get( row[ distanciaMinima.DISTANCIA_EUCLIDIANA_CLASE ]  ) , Float.parseFloat(row[ distanciaMinima.PESO ]), Float.parseFloat(row[ distanciaMinima.DIAMETRO ]) );
					resultModel.addRow(row);
				}
				
				JInternalFrame  windowPlot = distanciaMinimaGui.ventanaResultados();
				
				Plot2DPanel plot = grafica.plot();
				
				double [] labelFutbol = { distanciaMinima.obtenerPesoFutbol(),distanciaMinima.obtenerDiametroFutbol()-1 };
				double [] labelBaseball = { distanciaMinima.obtenerPesoBeisbol(),distanciaMinima.obtenerDiametroBesibol()-1 };
				plot.addLabel( distanciaMinima.FUTBOL , Color.black,labelFutbol );
				plot.addLabel( distanciaMinima.BEISBOL , Color.black,labelBaseball );
				
				plot.setAxisLabel(0, "Peso");
				plot.setAxisLabel(1, "Diámetro");
				
				
				windowPlot.setContentPane( plot );
				
				
				JInternalFrame  windowTable = distanciaMinimaGui.ventanaResultados();
				windowTable.setBounds(100, 495, 1100, 200);
				windowTable.setResizable(true);
				windowTable.setTitle("Resultados");
				
				JTable tabla = new JTable(resultModel);
				JScrollPane scrollPane = new JScrollPane(tabla);
				scrollPane.setBounds(0, 0, 800, 200);
				
				windowTable.setContentPane( scrollPane  );
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
