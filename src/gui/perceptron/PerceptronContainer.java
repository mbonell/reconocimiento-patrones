package gui.perceptron;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import core.Perceptron;

public class PerceptronContainer {
	
	Perceptron perceptron;
	
	JDesktopPane desk;
	
	JInternalFrame iframe;
	JInternalFrame ventanaResultados;

	JPanel panelSetAprendizaje;
	JPanel panelPesos;
	JPanel panelUmbral;
	JPanel panelRazonAprendizaje;
	JPanel panelLimiteEpocas;
	JPanel panelSalida;

	JLabel lblPesoLongitudSepalo;
	JLabel lblPesoAnchoSepalo;
	JLabel lblPesoLongitudPetalo;
	JLabel lblPesoAnchoPetalo;
	JLabel lblUmbralInicial;
	JLabel lblRazonAprendizaje;
	JLabel lblLimiteEpocasInicial;
	JLabel lblNoEntrenada;
	JLabel lblPesoLongitudSepaloFinal;
	JLabel lblPesoAnchoSepaloFinal;
	JLabel lblPesoLongitudPetaloFinal;
	JLabel lblPesoAnchoPetaloFinal;
	JLabel lblPesoLongitudSepaloValor;
	JLabel lblPesoAnchoSepaloValor;
	JLabel lblPesoLongitudPetaloValor;
	JLabel lblPesoAnchoPetaloValor;
	JLabel lblUmbralFinal;
	JLabel lblUmbralValor;
	JLabel lblEpocasFinal;
	JLabel lblEpocasValor;
	JLabel lblEfectividad;
	
	JTextField txtPesoLongitudSepalo;
	JTextField txtPesoAnchoSepalo;
	JTextField txtPesoLongitudPetalo;
	JTextField txtPesoAnchoPetalo;
	JTextField txtUmbralInicial;
	JTextField txtRazonAprendizaje;
	JTextField txtLimiteEpocasInicial;
	
	JTable tablaSetAprendizaje;
	JTable tablaSetPruebas;
	
	JButton btnEntrenar = new JButton("Iniciar entrenamiento");
	JButton btnClasificar = new JButton("Clasificar patrón");
	JButton btnAgregarSetAprendizaje = new JButton("+");
	JButton btnEliminarSetAprendizaje = new JButton("-");
	JButton btnAgregarSetPrueba = new JButton("+");
	JButton btnEliminarSetPrueba = new JButton("-");
	JButton btnClasificarPrueba = new JButton("Iniciar clasificación");

	
	String nombreCaracteristicas [] = new String[5];
	public final String TITULO_LONGITUD_SEPALO = "Longitud del Sépalo(cm)";
	public final String TITULO_ANCHO_SEPALO = "Ancho del Sépalo(cm)";
	public final String TITULO_LONGITUD_PETALO = "Longitud del Pétalo(cm)";
	public final String TITULO_ANCHO_PETALO = "Ancho del Pétalo(cm)";
	public final String TITULO_UMBRAL = "Valor del umbral";
	public final String TITULO_RAZON_APRENDIZAJE = "Valor razón de aprendizaje";
	public final String TITULO_LIMITE_EPOCAS = "Valor límite de épocas";
	public final String TITULO_NO_ENTRENADA = "Favor de entrenar el perceptrón";

	JScrollPane scrollPanePrueba;
	
    public void ventanaPerceptron(JDesktopPane desk){
    	
    	perceptron = new Perceptron();
    	this.desk = desk;
    	iframe = new JInternalFrame("Perceptrón - Clasificación de la flor Iris Setosa", true, true, true, true);
		iframe.setBounds(100, 10, 950, 540);
		iframe.setResizable(false);
		iframe.setMaximizable(false);
		iframe.setVisible(true);
		this.desk.add(iframe);
		iframe.setLayout(null);		
		
		nombreCaracteristicas[0] = TITULO_LONGITUD_SEPALO;
		nombreCaracteristicas[1] = TITULO_ANCHO_SEPALO;
		nombreCaracteristicas[2] = TITULO_LONGITUD_PETALO;
		nombreCaracteristicas[3] = TITULO_ANCHO_PETALO;
		nombreCaracteristicas[4] = "Clase";
				
		//Set de aprendizaje
		panelSetAprendizaje = new JPanel();
		panelSetAprendizaje.setBorder(BorderFactory.createTitledBorder("Set de aprendizaje "));
		panelSetAprendizaje.setBounds(10, 10, 620, 270);
		panelSetAprendizaje.setLayout(null);
		
		tablaSetAprendizaje = new JTable(new DefaultTableModel());
		
		DefaultTableModel model = (DefaultTableModel) tablaSetAprendizaje.getModel();
		
		for(String columna : nombreCaracteristicas ){
			model.addColumn(columna);
		}
		
		this.cargarSetAprendizaje();
		
		JScrollPane scrollPane = new JScrollPane(tablaSetAprendizaje);
		scrollPane.setBounds(15, 20, 550, 220);
		panelSetAprendizaje.add(scrollPane);
		
		btnAgregarSetAprendizaje.setBounds(580, 80, 25, 25);
		btnEliminarSetAprendizaje.setBounds(580, 130, 25, 25);
		
		panelSetAprendizaje.add(btnAgregarSetAprendizaje);
		panelSetAprendizaje.add(btnEliminarSetAprendizaje);
		
		
		//Pesos
		panelPesos = new JPanel();
		panelPesos.setBorder(BorderFactory.createTitledBorder("Pesos "));
		panelPesos.setBounds(640, 10, 270, 170);
		panelPesos.setLayout(null);
		perceptron.inicializarPesos();
		
		lblPesoLongitudSepalo = new JLabel(TITULO_LONGITUD_SEPALO);
		lblPesoLongitudSepalo.setBounds(10, 30, 170, 20);
		panelPesos.add(lblPesoLongitudSepalo);
		
		txtPesoLongitudSepalo = new JTextField();
		txtPesoLongitudSepalo.setBounds(180, 25, 70, 30);
		txtPesoLongitudSepalo.setText(Double.toString(perceptron.getPeso(perceptron.LONGITUD_SEPALO)));
		panelPesos.add(txtPesoLongitudSepalo);
		
		lblPesoAnchoSepalo = new JLabel(TITULO_ANCHO_SEPALO);
		lblPesoAnchoSepalo.setBounds(10, 60, 170, 20);
		panelPesos.add(lblPesoAnchoSepalo);
		
		txtPesoAnchoSepalo = new JTextField();
		txtPesoAnchoSepalo.setBounds(180, 55, 70, 30);
		txtPesoAnchoSepalo.setText(Double.toString(perceptron.getPeso(perceptron.ANCHO_SEPALO)));
		panelPesos.add(txtPesoAnchoSepalo);
		
		lblPesoLongitudPetalo = new JLabel(TITULO_LONGITUD_PETALO);
		lblPesoLongitudPetalo.setBounds(10, 90, 170, 20);
		panelPesos.add(lblPesoLongitudPetalo);
		
		txtPesoLongitudPetalo = new JTextField();
		txtPesoLongitudPetalo.setBounds(180, 85, 70, 30);
		txtPesoLongitudPetalo.setText(Double.toString(perceptron.getPeso(perceptron.LONGITUD_PETALO)));
		panelPesos.add(txtPesoLongitudPetalo);
		
		lblPesoAnchoPetalo = new JLabel(TITULO_ANCHO_PETALO);
		lblPesoAnchoPetalo.setBounds(10, 120, 170, 20);
		panelPesos.add(lblPesoAnchoPetalo);
		
		txtPesoAnchoPetalo = new JTextField();
		txtPesoAnchoPetalo.setBounds(180, 115, 70, 30);
		txtPesoAnchoPetalo.setText(Double.toString(perceptron.getPeso(perceptron.ANCHO_PETALO)));
		panelPesos.add(txtPesoAnchoPetalo);
		

		//Umbral
		panelUmbral = new JPanel();
		panelUmbral.setBorder(BorderFactory.createTitledBorder("Umbral "));
		panelUmbral.setBounds(640, 180, 270, 75);
		panelUmbral.setLayout(null);
		perceptron.setUmbralInicial(perceptron.getPeso("LONGITUD_SEPALO"));
		perceptron.setUmbralFinal(perceptron.getPeso("LONGITUD_SEPALO"));

		
		lblUmbralInicial = new JLabel(TITULO_UMBRAL);
		lblUmbralInicial.setBounds(10, 30, 170, 20);
		panelUmbral.add(lblUmbralInicial);
		
		txtUmbralInicial = new JTextField();
		txtUmbralInicial.setBounds(180, 25, 70, 30);
		txtUmbralInicial.setText(Double.toString(perceptron.getUmbralInicial()));
		panelUmbral.add(txtUmbralInicial);
		
		
		//Razon de aprendizaje
		panelRazonAprendizaje = new JPanel();
		panelRazonAprendizaje.setBorder(BorderFactory.createTitledBorder("Razón de aprendizaje "));
		panelRazonAprendizaje.setBounds(640, 260, 270, 75);
		panelRazonAprendizaje.setLayout(null);
		
		lblRazonAprendizaje = new JLabel(TITULO_RAZON_APRENDIZAJE);
		lblRazonAprendizaje.setBounds(10, 30, 170, 20);
		panelRazonAprendizaje.add(lblRazonAprendizaje);
		
		txtRazonAprendizaje = new JTextField();
		txtRazonAprendizaje.setBounds(180, 25, 70, 30);
		txtRazonAprendizaje.setText(Double.toString(perceptron.getRazonAprendizaje()));
		panelRazonAprendizaje.add(txtRazonAprendizaje);
		
		
		//Limite epocas
		panelLimiteEpocas = new JPanel();
		panelLimiteEpocas.setBorder(BorderFactory.createTitledBorder("Límite épocas "));
		panelLimiteEpocas.setBounds(640, 340, 270, 75);
		panelLimiteEpocas.setLayout(null);
		
		lblLimiteEpocasInicial = new JLabel(TITULO_LIMITE_EPOCAS);
		lblLimiteEpocasInicial.setBounds(10, 30, 170, 20);
		panelLimiteEpocas.add(lblLimiteEpocasInicial);
		
		txtLimiteEpocasInicial = new JTextField();
		txtLimiteEpocasInicial.setBounds(180, 25, 70, 30);
		txtLimiteEpocasInicial.setText(Integer.toString(perceptron.getLimiteEpocas()));
		panelLimiteEpocas.add(txtLimiteEpocasInicial);

		iframe.add(panelSetAprendizaje);
		iframe.add(panelPesos);
		iframe.add(panelUmbral);
		iframe.add(panelRazonAprendizaje);
		iframe.add(panelLimiteEpocas);

		//Salida
		panelSalida = new JPanel();
		panelSalida.setBorder(BorderFactory.createTitledBorder("Salida "));
		panelSalida.setBounds(10, 280, 620, 180);
		panelSalida.setLayout(null);
		
		lblNoEntrenada = new JLabel("<html><b>"+TITULO_NO_ENTRENADA+"</b></html>");
		lblNoEntrenada.setBounds(210, 80, 250, 20);
		panelSalida.add(lblNoEntrenada);
		
		//Salida pesos
		lblPesoLongitudSepaloFinal = new JLabel(TITULO_LONGITUD_SEPALO);
		lblPesoLongitudSepaloFinal.setBounds(10, 30, 170, 20);
		panelSalida.add(lblPesoLongitudSepaloFinal);
		
		lblPesoLongitudSepaloValor = new JLabel("<html><b>0.0</b></html>");
		lblPesoLongitudSepaloValor.setBounds(180, 30, 170, 20);
		panelSalida.add(lblPesoLongitudSepaloValor);
		
		
		lblPesoAnchoSepaloFinal = new JLabel(TITULO_ANCHO_SEPALO);
		lblPesoAnchoSepaloFinal.setBounds(10, 60, 170, 20);
		panelSalida.add(lblPesoAnchoSepaloFinal);
		
		lblPesoAnchoSepaloValor = new JLabel("<html><b>0.0</b></html>");
		lblPesoAnchoSepaloValor.setBounds(180, 60, 170, 20);
		panelSalida.add(lblPesoAnchoSepaloValor);
		
		
		lblPesoLongitudPetaloFinal = new JLabel(TITULO_LONGITUD_PETALO);
		lblPesoLongitudPetaloFinal.setBounds(10, 90, 170, 20);
		panelSalida.add(lblPesoLongitudPetaloFinal);
		
		lblPesoLongitudPetaloValor = new JLabel("<html><b>0.0</b></html>");
		lblPesoLongitudPetaloValor.setBounds(180, 90, 170, 20);
		panelSalida.add(lblPesoLongitudPetaloValor);
		
		
		lblPesoAnchoPetaloFinal = new JLabel(TITULO_ANCHO_PETALO);
		lblPesoAnchoPetaloFinal.setBounds(10, 120, 170, 20);
		panelSalida.add(lblPesoAnchoPetaloFinal);
		
		lblPesoAnchoPetaloValor = new JLabel("<html><b>0.0</b></html>");
		lblPesoAnchoPetaloValor.setBounds(180, 120, 170, 20);
		panelSalida.add(lblPesoAnchoPetaloValor);
		
		//Salida umbral
		lblUmbralFinal = new JLabel("Umbral");
		lblUmbralFinal.setBounds(270, 60, 170, 20);
		panelSalida.add(lblUmbralFinal);
		
		lblUmbralValor = new JLabel("<html><b>0.0</b></html>");
		lblUmbralValor.setBounds(350, 60, 170, 20);
		panelSalida.add(lblUmbralValor);
		
		//Salida epocas
		lblEpocasFinal = new JLabel("Épocas");
		lblEpocasFinal.setBounds(270, 90, 170, 20);
		panelSalida.add(lblEpocasFinal);
		
		lblEpocasValor = new JLabel("<html><b>0.0</b></html>");
		lblEpocasValor.setBounds(350, 90, 170, 20);
		panelSalida.add(lblEpocasValor);
		
		btnClasificar.setBounds(480, 310, 120, 120);
		btnEntrenar.setBounds(760, 420, 150, 40);
		
		iframe.add(btnEntrenar);
		iframe.add(btnClasificar);
		iframe.add(panelSalida);
		
		visibleSalida(false);

    }
    
    
    public void visibleSalida(boolean visible){
    	 lblPesoLongitudSepaloFinal.setVisible(visible);
    	 lblPesoAnchoSepaloFinal.setVisible(visible);
    	 lblPesoLongitudPetaloFinal.setVisible(visible);
    	 lblPesoAnchoPetaloFinal.setVisible(visible);
    	 lblPesoLongitudSepaloValor.setVisible(visible);
    	 lblPesoAnchoSepaloValor.setVisible(visible);
    	 lblPesoLongitudPetaloValor.setVisible(visible);
    	 lblPesoAnchoPetaloValor.setVisible(visible);
    	 lblUmbralFinal.setVisible(visible);
    	 lblUmbralValor.setVisible(visible);
    	 lblEpocasFinal.setVisible(visible);
    	 lblEpocasValor.setVisible(visible);
    	 btnClasificar.setVisible(visible);
    }
    
    
    public void ventanaClasificaciones(){
    	ventanaResultados = new JInternalFrame("Nuevos patrónes", true, true, true, true);
    	ventanaResultados.setBounds(100, 500, 770, 350);
    	ventanaResultados.setResizable(false);
    	ventanaResultados.setVisible(true);
    	ventanaResultados.setTitle("Clasificar");
    	this.desk.add(ventanaResultados);
		ventanaResultados.setLayout(null);

		
		tablaSetPruebas = new JTable(new DefaultTableModel());
		
		DefaultTableModel model = (DefaultTableModel) tablaSetPruebas.getModel();
		
		for(String columna : nombreCaracteristicas ){
			model.addColumn(columna);
		}
		
		this.cargarSetPrueba();
		
		scrollPanePrueba = new JScrollPane(tablaSetPruebas);
		scrollPanePrueba.setBounds(15, 20, 670, 220);
		ventanaResultados.add(scrollPanePrueba);
		
		btnAgregarSetPrueba.setBounds(700, 80, 25, 25);
		btnEliminarSetPrueba.setBounds(700, 130, 25, 25);
		btnClasificarPrueba.setBounds(540, 250, 150, 35);
		lblEfectividad = new JLabel("");
		lblEfectividad.setBounds(15, 250, 250, 35);
		
		ventanaResultados.add(btnAgregarSetPrueba);
		ventanaResultados.add(btnEliminarSetPrueba);
		ventanaResultados.add(btnClasificarPrueba);
		ventanaResultados.add(lblEfectividad);


    }
    
	private void cargarSetAprendizaje(){
		DefaultTableModel temp = (DefaultTableModel) this.tablaSetAprendizaje.getModel();
		String csvFile = "dataset/iris_aprendizaje.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
	 
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			
			while ((line = br.readLine()) != null) {
	 
				String[] patron = line.split(cvsSplitBy);
				Object nuevo[]= {patron[0], patron[1], patron[2], patron[3], patron[4]};
				temp.addRow(nuevo);
				
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error accediendo al csv");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
	}
	
	private void cargarSetPrueba(){
		DefaultTableModel temp = (DefaultTableModel) this.tablaSetPruebas.getModel();
		String csvFile = "dataset/iris_prueba.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
	 
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			
			while ((line = br.readLine()) != null) {
	 
				String[] patron = line.split(cvsSplitBy);
				Object nuevo[]= {patron[0], patron[1], patron[2], patron[3], "-"};
				temp.addRow(nuevo);
				
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error accediendo al csv prueba");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
	}
	
	
	public String[] obtenerRespuestasCorrectas(int tamaño){
		String csvFile = "dataset/iris_prueba_resultados.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		String respuestas [] = new String [tamaño];
		int contador = 0;
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			
			while ((line = br.readLine()) != null) {
	 
				String[] patron = line.split(cvsSplitBy);
				respuestas[contador] = patron[4];
				contador++;
				
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error accediendo al csv");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return respuestas;
	 
	}
}
