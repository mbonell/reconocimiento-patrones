package core;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;

public class Perceptron {
	

	public final String LONGITUD_SEPALO = "LONGITUD_SEPALO";
	public final String ANCHO_SEPALO = "ANCHO_SEPALO";
	public final String LONGITUD_PETALO = "LONGITUD_PETALO";
	public final String ANCHO_PETALO = "ANCHO_PETALO";
	
	private double razonAprendizaje = 0.5;
	private int limiteEpocas = 1000;
	private int numeroEpocasFinal;
	private double umbralInicial;
	private double umbralFinal;
	
	public HashMap<String, Double> pesos = new HashMap<String, Double>();
	
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
	
	public double getUmbralInicial(){
		return this.umbralInicial;
	}
	
	public void setUmbralInicial(double umbral){
		this.umbralInicial = umbral;
	}
	
	public double getUmbralFinal(){
		return this.umbralFinal;
	}
	
	public void setUmbralFinal(double umbral){
		this.umbralFinal = umbral;
	}
	
	public void inicializarPesos(){
		this.pesos.put(LONGITUD_SEPALO, this.generarAletorio());
		this.pesos.put(ANCHO_SEPALO, this.generarAletorio());
		this.pesos.put(LONGITUD_PETALO, this.generarAletorio());
		this.pesos.put(ANCHO_PETALO, this.generarAletorio());
	}
	
	public double getPeso(String llave){
		return this.pesos.get(llave);
	}
	
	public double setPeso(String llave, Double valor){
		return this.pesos.put(llave,valor);
	}
	
	private double getPesoLlaveEntera(int llave){
		double peso = 0;
		
		switch (llave) {
			case 0:
				peso = this.getPeso(LONGITUD_SEPALO);
				break;
			case 1:
				peso = this.getPeso(ANCHO_SEPALO);
				break;
			case 2:
				peso = this.getPeso(LONGITUD_PETALO);
				break;
			case 3:
				peso = this.getPeso(ANCHO_PETALO);
				break;

		}
		return peso;
	}
	
	private void setPesoLlaveEntera(int llave, double valor){
		
		switch (llave) {
			case 0:
				this.setPeso(LONGITUD_SEPALO, valor);
				break;
			case 1:
				this.setPeso(ANCHO_SEPALO, valor);
				break;
			case 2:
				this.setPeso(LONGITUD_PETALO, valor);
				break;
			case 3:
				this.setPeso(ANCHO_PETALO, valor);
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
	
	public String[][] generarClasificacion(String[][] data){
		
		String[][] resultados = new String[data.length][10];
		
		for(int i = 0; i<data.length; i++){
			
		}
		
		return resultados;
	}
	
	
	public void entrenar(String[][] entradas){
		
		boolean finalizado = false;
		int Pw,error, y;
		this.numeroEpocasFinal = 0;
		
			while(!finalizado){ 
				
				finalizado = true;
				
				for(int n = 0; n < entradas.length; n++){
					Pw = this.desicion(entradas[n]);
					y = Integer.valueOf(entradas[n][entradas[n].length-1]); //Clase esperada
					error = y - Pw;
					System.out.println("Entrada[" + n + "] Y: " + y + " Pw: " + Pw);
						if(error != 0){
							finalizado = false;
							this.ajustarPesos(entradas[n], error);
						}
				}
				
				this.numeroEpocasFinal++;
				
				if(this.numeroEpocasFinal > this.limiteEpocas)
					finalizado = true;
			}
		
		//Pesos finales	
		System.out.println("Peso 1: " + this.getPesoLlaveEntera(0));
		System.out.println("Peso 2: " + this.getPesoLlaveEntera(1));
		System.out.println("Peso 3: " + this.getPesoLlaveEntera(2));
		System.out.println("Peso 4: " + this.getPesoLlaveEntera(3));
	}
	
	
	private int desicion(String [] patron){
		double sumatoria = 0;		
		
		for(int n = 0; n < patron.length-1; n++){
			sumatoria+=(Double.valueOf(patron[n]) * this.getPesoLlaveEntera(n));
		}
		
		System.out.println("Sumatoria: " + sumatoria);
		
		if(sumatoria >= this.umbralFinal) //TODO: Comparacion contra el umbal o contra 0?, cuando se actualiza el umbral?
			return 1;
		else
			return 0;
	}
	
	private void ajustarPesos(String [] patron, int error){
		for(int n = 0; n < patron.length-1; n++){ //TODO: No deberia considerarse la clase esperada?
			this.setPesoLlaveEntera(n, this.getPesoLlaveEntera(n) + this.razonAprendizaje * error * Double.valueOf(patron[n]));
		}
	}
	
	public String[][] clasificar(String[][] entradasClasificar){
		for(int n = 0; n < entradasClasificar.length; n++){
			
			double sumatoria = 0;		
			
			for(int j = 0; j < entradasClasificar[n].length-1; j++){
				sumatoria+=(Double.valueOf(entradasClasificar[n][j]) * this.getPesoLlaveEntera(j));
			}
			
			if(sumatoria >= 0) //TODO: Si es contra 0? y el umbral?
				entradasClasificar[n][4] = "1";
			else
				entradasClasificar[n][4] = "0";				
			
		}
		
		return entradasClasificar;
	}

}
