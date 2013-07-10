package core;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;

public class Adaline {
	

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
		this.pesos.put(EMBARAZOS, this.generarAletorio());
		this.pesos.put(CONCENTRACION_GLUCOSA, this.generarAletorio());
		this.pesos.put(PRESION_ARTERIAL, this.generarAletorio());
		this.pesos.put(GROSOR_TRICEPS, this.generarAletorio());
		this.pesos.put(INSULINA, this.generarAletorio());
		this.pesos.put(MASA_CORPORAL, this.generarAletorio());
		this.pesos.put(FUNCION, this.generarAletorio());
		this.pesos.put(EDAD, this.generarAletorio());
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
				peso = this.getPeso(EMBARAZOS);
				break;
			case 1:
				peso = this.getPeso(CONCENTRACION_GLUCOSA);
				break;
			case 2:
				peso = this.getPeso(PRESION_ARTERIAL);
				break;
			case 3:
				peso = this.getPeso(GROSOR_TRICEPS);
				break;
			case 4:
				peso = this.getPeso(INSULINA);
				break;
			case 5:
				peso = this.getPeso(MASA_CORPORAL);
				break;
			case 6:
				peso = this.getPeso(FUNCION);
				break;
			case 7:
				peso = this.getPeso(EDAD);
				break;

		}
		return peso;
	}
	
	private void setPesoLlaveEntera(int llave, double valor){
		
		switch (llave) {
			case 0:
				this.setPeso(EMBARAZOS, valor);
				break;
			case 1:
				this.setPeso(CONCENTRACION_GLUCOSA, valor);
				break;
			case 2:
				this.setPeso(PRESION_ARTERIAL, valor);
				break;
			case 3:
				this.setPeso(GROSOR_TRICEPS, valor);
				break;
			case 4:
				this.setPeso(INSULINA, valor);
				break;
			case 5:
				this.setPeso(MASA_CORPORAL, valor);
				break;
			case 6:
				this.setPeso(FUNCION, valor);
				break;
			case 7:
				this.setPeso(EDAD, valor);
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
		int y;
		double  error = 0, errorCuadraticoMedio = 0, valorActivacion = 0;
		this.numeroEpocasFinal = 0;
		
			while(!finalizado){ 
				
				
				for(int n = 0; n < entradas.length; n++){
					valorActivacion = this.obtenidaSigmoide(entradas[n]);
					y = Integer.valueOf(entradas[n][entradas[n].length-1]); //Clase esperada
					
					error = y - valorActivacion; //Delta
					errorCuadraticoMedio = Math.pow(error, 2); //Error cuadratico medio
					this.errorDeseadoFinal +=errorCuadraticoMedio; //Error acumulado
					
					/*System.out.println("Entrada[" + n + "] Y: " + y +
										", Valor de activacion (net): " + valorActivacion + 
										", Error cuadratico medio: " + errorCuadraticoMedio);
					*/
					
						this.ajustarPesos(entradas[n], error);

						
				}
				
				this.numeroEpocasFinal++;
				this.errorDeseadoFinal /= entradas.length;
				System.out.println("["+ this.numeroEpocasFinal +"]Error Esperado: " + this.errorDeseadoFinal);
				if(this.numeroEpocasFinal >= this.limiteEpocas || this.errorDeseadoFinal <= this.errorDeseado)
					finalizado = true;
				else
					this.errorDeseadoFinal = 0;
			}
		
		//Pesos finales	
		System.out.println("Peso 1: " + this.getPesoLlaveEntera(0));
		System.out.println("Peso 2: " + this.getPesoLlaveEntera(1));
		System.out.println("Peso 3: " + this.getPesoLlaveEntera(2));
		System.out.println("Peso 4: " + this.getPesoLlaveEntera(3));
		System.out.println("Peso 5: " + this.getPesoLlaveEntera(4));
		System.out.println("Peso 6: " + this.getPesoLlaveEntera(5));
		System.out.println("Peso 7: " + this.getPesoLlaveEntera(6));
		System.out.println("Peso 8: " + this.getPesoLlaveEntera(7));
	}
	
	
	private double obtenidaSigmoide(String [] patron){
		double sumatoria = 0, fy = 0;		
		
		for(int n = 0; n < patron.length-1; n++){
			sumatoria+=(Double.valueOf(patron[n]) * this.getPesoLlaveEntera(n));
		}
		
		fy = 1 / (1 + Math.exp(-1 * sumatoria));
		
		//System.out.println("Valor de activacion (x*w) en f(y): " + fy);
		
		return fy;

	}
	
	private void ajustarPesos(String [] patron, double error){
		for(int n = 0; n < patron.length-1; n++){ 
			this.setPesoLlaveEntera(n, this.getPesoLlaveEntera(n) + this.razonAprendizaje * error * this.obtenidaSigmoide(patron) * (1-this.obtenidaSigmoide(patron)) * Double.valueOf(patron[n]));
			//System.out.println(n + " " + this.getPesoLlaveEntera(n));
		}
	}
	
	public String[][] clasificar(String[][] entradasClasificar){
		
		for(int n = 0; n < entradasClasificar.length; n++){
			
			double fy = 0;		
			fy = obtenidaSigmoide(entradasClasificar[n]);
			
			if(fy >= 0.5) 
				entradasClasificar[n][8] = "1";
			else
				entradasClasificar[n][8] = "0";				
			
		}
		
		return entradasClasificar;
	}

}
