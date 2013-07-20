package core;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BackPropagation {
	
	public final String THRESHOLD 			  = "THRESHOLD";
	public final String EMBARAZOS 			  = "EMBARAZOS";
	public final String CONCENTRACION_GLUCOSA = "CONCENTRACION_GLUCOSA";
	public final String PRESION_ARTERIAL 	  = "PRESION_ARTERIAL";
	public final String GROSOR_TRICEPS   	  = "GROSOR_TRICEPS";
	public final String INSULINA      		  = "INSULINA";
	public final String MASA_CORPORAL 		  = "MASA_CORPORAL";
	public final String FUNCION 			  = "FUNCION";
	public final String EDAD 				  = "EDAD";
	
	private double razonAprendizaje = 0.5;
	private int limiteEpocas = 10000;
	private double errorDeseado = 0.01;
	private double errorDeseadoFinal;
	private int numeroEpocasFinal;
	
	private int numeroCapasOcultas = 2;
	private int numeroNeuronasPorCapa = 4;
	private int numeroEntradas = 8;
	
	public HashMap<String, Double>[][] pesos;
	public double [][]salidasRed;
	
	public void setNumeroCapasOcultas(int numeroCapasOcultas){
		this.numeroCapasOcultas = numeroCapasOcultas;
	}
	
	public int getNumeroCapasOcultas(){
		return this.numeroCapasOcultas;
	}
	
	public int getNumeroNeuronasPorCapa(){
		return this.numeroNeuronasPorCapa;
	}
	
	public void setNumeroNeuronasPorCapa(int numeroNeuronasPorCapa){
		this.numeroNeuronasPorCapa = numeroNeuronasPorCapa;
	}
	
	public int getNumeroEntradas(){
		return this.numeroEntradas;
	}
	
	public double getRazonAprendizaje(){
		return this.razonAprendizaje;
	}
	
	public void setRazonAprendizaje(double razonAprendizaje){
		this.razonAprendizaje = razonAprendizaje;
	}
	
	public int getLimiteEpocas(){
		return this.limiteEpocas;
	}
	
	public void setLimiteEpocas(int limiteEpocas){
		this.limiteEpocas = limiteEpocas;
	}
	
	public int getNumeroEpocasFinal(){
		return this.numeroEpocasFinal;
	}
	
	public double getErrorFinal(){
		return this.errorDeseadoFinal;
	}
	
	public void setErrorFinal(double error){
		this.errorDeseadoFinal = error;
	}
	
	public double getErrorDeseado(){
		return this.errorDeseado;
	}
	
	public void setErrorDeseado(double error){
		this.errorDeseado = error;
	}
	
	public void inicializarPesos(){
		pesos =  (HashMap<String,Double>[][]) new HashMap [this.getNumeroCapasOcultas()][this.getNumeroNeuronasPorCapa()];
		
		for (int m = 0; m < this.getNumeroCapasOcultas(); ++m){
				for(int i = 0; i < this.getNumeroNeuronasPorCapa(); ++i){
					
					HashMap<String, Double> peso = new HashMap<String, Double>();
						peso.put(THRESHOLD, this.generarAletorio());
						peso.put(EMBARAZOS, this.generarAletorio());
						peso.put(CONCENTRACION_GLUCOSA, this.generarAletorio());
						peso.put(PRESION_ARTERIAL, this.generarAletorio());
						peso.put(GROSOR_TRICEPS, this.generarAletorio());
						peso.put(INSULINA, this.generarAletorio());
						peso.put(MASA_CORPORAL, this.generarAletorio());
						peso.put(FUNCION, this.generarAletorio());
						peso.put(EDAD, this.generarAletorio());
						
					this.pesos[m][i] = peso;
				}
		}
	          
	      

	}
	
	public double getPeso(int capa, int neurona, String llave){
		return this.pesos[capa][neurona].get(llave);
	}
	
	public double setPeso(int capa, int neurona, String llave, Double valor){
		return this.pesos[capa][neurona].put(llave,valor);
	}
	
	public double getPesoLlaveEntera(int capa, int neurona, int llave){
		double peso = 0;
		
		switch (llave) {
			case 0:
				peso = this.getPeso(capa, neurona, THRESHOLD);
				break;
			case 1:
				peso = this.getPeso(capa, neurona, EMBARAZOS);
				break;
			case 2:
				peso = this.getPeso(capa, neurona, CONCENTRACION_GLUCOSA);
				break;
			case 3:
				peso = this.getPeso(capa, neurona, PRESION_ARTERIAL);
				break;
			case 4:
				peso = this.getPeso(capa, neurona, GROSOR_TRICEPS);
				break;
			case 5:
				peso = this.getPeso(capa, neurona, INSULINA);
				break;
			case 6:
				peso = this.getPeso(capa, neurona, MASA_CORPORAL);
				break;
			case 7:
				peso = this.getPeso(capa, neurona, FUNCION);
				break;
			case 8:
				peso = this.getPeso(capa, neurona, EDAD);
				break;

		}
		return peso;
	}
	
	private void setPesoLlaveEntera(int capa, int neurona, int llave, double valor){
		
		switch (llave) {
			case 0:
				this.setPeso(capa, neurona, THRESHOLD, valor);
				break;
			case 1:
				this.setPeso(capa, neurona, EMBARAZOS, valor);
				break;
			case 2:
				this.setPeso(capa, neurona, CONCENTRACION_GLUCOSA, valor);
				break;
			case 3:
				this.setPeso(capa, neurona, PRESION_ARTERIAL, valor);
				break;
			case 4:
				this.setPeso(capa, neurona, GROSOR_TRICEPS, valor);
				break;
			case 5:
				this.setPeso(capa, neurona, INSULINA, valor);
				break;
			case 6:
				this.setPeso(capa, neurona, MASA_CORPORAL, valor);
				break;
			case 7:
				this.setPeso(capa, neurona, FUNCION, valor);
				break;
			case 8:
				this.setPeso(capa, neurona, EDAD, valor);
				break;

		}
	}
	
	private double generarAletorio(){
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat formato = new DecimalFormat("#.##",simbolos);
		
		double random = Math.random();
		random = (random == 0) ? 0.1: random;
		
	    return Double.valueOf(formato.format(random));
	}
	
	
	public void entrenar(String[][] entradas){
		
		boolean finalizado = false;
		int y = 0;
		double  error = 0, errorCuadraticoMedio = 0, valorActivacion = 0;
		this.numeroEpocasFinal = 0;
		
			while(!finalizado){ 
				
				
				for(int n = 0; n < entradas.length; n++){
					
					//Calcular todos los n (salidas de cada neurona de cada capa) dada una entrada
					System.out.println("ENTRADA " + (n+1) + "-------------------------");
					this.calcularSalidaRed(entradas[n]);
					
					//TODO: Si calculo todas las n de las capas, neuronas en que momento evaluo el error?
					
					//y = Integer.valueOf(entradas[n][entradas[n].length-1]); //Clase esperada
					
					error = y - valorActivacion; //Delta
					errorCuadraticoMedio = Math.pow(error, 2); //Error cuadratico medio
					this.errorDeseadoFinal +=errorCuadraticoMedio; //Error acumulado
					
					
						//this.ajustarPesos(entradas[n], error);

						
				}
				
				this.numeroEpocasFinal++;
				this.errorDeseadoFinal /= entradas.length;
				
				System.out.println("["+ this.numeroEpocasFinal +"] Error Esperado: " + this.errorDeseadoFinal);
				
				if(this.numeroEpocasFinal >= this.limiteEpocas || this.errorDeseadoFinal <= this.errorDeseado)
					finalizado = true;
				else
					this.errorDeseadoFinal = 0;
			}
		
		//Pesos finales	
			/*
		System.out.println("Peso 1: " + this.getPesoLlaveEntera(0));
		System.out.println("Peso 2: " + this.getPesoLlaveEntera(1));
		System.out.println("Peso 3: " + this.getPesoLlaveEntera(2));
		System.out.println("Peso 4: " + this.getPesoLlaveEntera(3));
		System.out.println("Peso 5: " + this.getPesoLlaveEntera(4));
		System.out.println("Peso 6: " + this.getPesoLlaveEntera(5));
		System.out.println("Peso 7: " + this.getPesoLlaveEntera(6));
		System.out.println("Peso 8: " + this.getPesoLlaveEntera(7));*/
	}
	
	

	
	private double obtenerSalidaNeurona(String [] entradas, int capa, int neurona){
		double sumatoria = 0, salidaNeurona = 0;		
		
		for(int j = 0; j < entradas.length-1; j++){
			sumatoria+=(Double.valueOf(entradas[j]) * this.getPesoLlaveEntera(capa, neurona, j));
		}
		
		salidaNeurona = 1 / (1 + Math.exp(-1 * sumatoria));
		
		return salidaNeurona;

	}
	
	private void calcularSalidaRed(String [] entradas){
		
		double valorActivacion = 0;
		
		for(int m = 0; m < this.getNumeroCapasOcultas(); m++){
			System.out.println("CAPA " + (m+1));
			for (int i = 0; i < this.getNumeroNeuronasPorCapa(); ++i){
				System.out.println("NEURONA " + (i+1));
				
				valorActivacion = this.obtenerSalidaNeurona(entradas, m, i);
				System.out.println("N: " + valorActivacion);
				//TODO: Salida deseada, error, ajuste de pesos, valor de activacion
				
				//TODO: Pero si tengo 15 entradas entonces tendre 15 diferentes sets de N (m,i) ??
			}
		}
		
		
		
	}
	private void ajustarPesos(String [] patron, double error){
		for(int n = 0; n < patron.length-1; n++){ 
			//this.setPesoLlaveEntera(0,0, n, this.getPesoLlaveEntera(0,0, n) + this.razonAprendizaje * error * this.obtenidaSigmoide(patron) * (1-this.obtenidaSigmoide(patron)) * Double.valueOf(patron[n]));
			//System.out.println(n + " " + this.getPesoLlaveEntera(n));
		}
	}
	
	public String[][] clasificar(String[][] entradasClasificar){
		
		for(int n = 0; n < entradasClasificar.length; n++){
			
			double fy = 0;		
			//fy = obtenidaSigmoide(entradasClasificar[n]);
			
			if(fy >= 0.5) 
				entradasClasificar[n][this.getNumeroEntradas()] = "1";
			else
				entradasClasificar[n][this.getNumeroEntradas()] = "0";				
			
		}
		
		return entradasClasificar;
	}

}
