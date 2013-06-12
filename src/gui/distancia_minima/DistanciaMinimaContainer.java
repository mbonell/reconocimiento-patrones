package gui.distancia_minima;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;



import core.DistanciaMinima;

/**
 * Clase que contiene la declaracion y configuracion de los elementos
 * gráficos para el clasificador de distancia mínima.
 * @author Marcela Bonell Manjarrez
 * @creation 09/07/13
 * @modification 09/07/13
 */
public class DistanciaMinimaContainer {
	
	JDesktopPane desk;
	
	JInternalFrame iframe;
	JInternalFrame ventanaResultados;

	JPanel panelPatronesIdeales;
	JPanel panelNuevosPatrones;

	JLabel lblClaseBeisbol;
	JLabel lblCaracteristicasBeisbol;
	JLabel lblValoresBeisbol;
	JLabel lblImagenBeisbol;
	
	JLabel lblClaseFutbol;
	JLabel lblCaracteristicasFutbol;
	JLabel lblValoresFutbol;
	JLabel lblImagenFutbol;
	
	JTable tablaNuevosPatrones;
	
	JButton btnEjecutar = new JButton("Evaluar");
	
	JButton btnAgregarPatron = new JButton("+");
	JButton btnEliminarPatron = new JButton("-");
	
	String[] nombreColumnas ={"Peso(g)", "Diámetro(cm)"};



    public void ventanaDistanciaMinima(JDesktopPane desk){
    	
    	this.desk = desk;
    	iframe = new JInternalFrame("Clasificador de pelotas deportivas", true, true, true, true);
		iframe.setBounds(100, 80, 560, 500);
		iframe.setResizable(false);
		iframe.setMaximizable(false);
		iframe.setVisible(true);
		this.desk.add(iframe);
		iframe.setLayout(null);		
		
		//Patrones ideales
		panelPatronesIdeales = new JPanel();
		panelPatronesIdeales.setBorder(BorderFactory.createTitledBorder("Patrones ideales (prototipo) "));
		panelPatronesIdeales.setBounds(10, 10, 517, 180);
		panelPatronesIdeales.setLayout(null);
	
		lblClaseBeisbol = new JLabel("<html><b>Clase Beisbol</b></html>");
		lblClaseBeisbol.setBounds(30, 30, 100, 20);
		panelPatronesIdeales.add(lblClaseBeisbol);
		
		lblCaracteristicasBeisbol = new JLabel("Peso (g) / Diámetro (cm)");
		lblCaracteristicasBeisbol.setBounds(50, 50, 200, 20);
		panelPatronesIdeales.add(lblCaracteristicasBeisbol);
		
		lblValoresBeisbol = new JLabel("[ " + DistanciaMinima.pesoBeisbol + " , " + DistanciaMinima.diametroBeisbol + " ]");
		lblValoresBeisbol.setBounds(50, 70, 200, 20);
		panelPatronesIdeales.add(lblValoresBeisbol);
		
		lblImagenBeisbol = new JLabel();
		lblImagenBeisbol.setIcon(new ImageIcon("imagenes/beisbol.png"));
		lblImagenBeisbol.setBounds(300, 40, 50, 50);
		panelPatronesIdeales.add(lblImagenBeisbol);
		
		lblClaseFutbol = new JLabel("<html><b>Clase Futbol</b></html>");
		lblClaseFutbol.setBounds(30, 100, 100, 20);
		panelPatronesIdeales.add(lblClaseFutbol);
		
		lblCaracteristicasFutbol = new JLabel("Peso (g) / Diámetro (cm)");
		lblCaracteristicasFutbol.setBounds(50, 120, 200, 20);
		panelPatronesIdeales.add(lblCaracteristicasFutbol);
		
		lblValoresFutbol = new JLabel("[ " + DistanciaMinima.pesoFutbol + " , " + DistanciaMinima.diametroFutbol + " ]");
		lblValoresFutbol.setBounds(50, 140, 200, 20);
		panelPatronesIdeales.add(lblValoresFutbol);
		
		lblImagenFutbol = new JLabel();
		lblImagenFutbol.setIcon(new ImageIcon("imagenes/futbol.png"));
		lblImagenFutbol.setBounds(290, 110, 65, 65);
		panelPatronesIdeales.add(lblImagenFutbol);
		
		iframe.add(panelPatronesIdeales);
		

		//Nuevos patrones
		panelNuevosPatrones = new JPanel();
		panelNuevosPatrones.setBorder(BorderFactory.createTitledBorder("Nuevos patrones "));
		panelNuevosPatrones.setBounds(10, 200, 517, 180);
		panelNuevosPatrones.setLayout(null);

		tablaNuevosPatrones = new JTable(new DefaultTableModel());
		
		DefaultTableModel model = (DefaultTableModel) tablaNuevosPatrones.getModel();
		
		for(String columna : nombreColumnas ){
			model.addColumn(columna);
		}
		
		JScrollPane scrollPane = new JScrollPane(tablaNuevosPatrones);
		scrollPane.setBounds(30, 30, 400, 120);
		
		btnAgregarPatron.setBounds(440, 50, 25, 25);
		btnEliminarPatron.setBounds(440, 100, 25, 25);
		
		panelNuevosPatrones.add(scrollPane);
		panelNuevosPatrones.add(btnAgregarPatron);
		panelNuevosPatrones.add(btnEliminarPatron);
		
		btnEjecutar.setBounds(270, 400, 170, 50);
		btnEjecutar.setToolTipText("Evaluar los nuevos patrones");
		btnEjecutar.setIcon(new ImageIcon("imagenes/lanzar.png"));

		iframe.add(btnEjecutar);
		
		iframe.add(panelNuevosPatrones);

    }
    
    public JInternalFrame ventanaResultados(){
    	
    	ventanaResultados = new JInternalFrame("Resultados", true, true, true, true);
    	ventanaResultados.setBounds(600, 100, 560, 500);
    	ventanaResultados.setResizable(false);
    	ventanaResultados.setMaximizable(false);
    	ventanaResultados.setVisible(true);
    	this.desk.add(ventanaResultados);
		ventanaResultados.setLayout(null);
		
		return ventanaResultados;
    }
}
