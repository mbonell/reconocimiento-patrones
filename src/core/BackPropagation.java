package core;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
	private int limiteEpocas = 50000;
	private double errorDeseado = 0.00001;
	private double errorDeseadoFinal;
	private int numeroEpocasFinal;
	
	private int numeroCapasOcultas = 2;
	private int numeroNeuronasPorCapa = 8;
	private int numeroEntradas = 8;
	
	public HashMap<String, Double>[][] pesos;
	public List<Double> errores = new ArrayList<Double>();
	public String [][]salidasPorCapa;
	public double [][]sensibilidades;
	private double sensibilidadAdaline = 0;
	private double [] pesosAdaline;
	
	public void setSalidasPorCapa(int capa, int neurona, String valor){
		this.salidasPorCapa[capa][neurona] = valor;
	}
	
	public String getSalidasPorCapa(int capa, int neurona){
		return this.salidasPorCapa[capa][neurona];
	}
	
	public double getSensibilidades(int capa, int neurona){
		return this.sensibilidades[capa][neurona];
	}
	
	public void setSensibilidades(int capa, int neurona, double valor){
		this.sensibilidades[capa][neurona] = valor;
	}
	
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
	
	@SuppressWarnings("unchecked")
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
	
	public void inicializarPesosAdaline(){
		this.pesosAdaline =  new double [this.getNumeroNeuronasPorCapa()+1];
		
			for(int i = 0; i < this.getNumeroNeuronasPorCapa()+1; ++i){	
				this.pesosAdaline[i] = this.generarAletorio();
			}	          
	}
	
	public double getPesoAdaline(int neurona){
		return this.pesosAdaline[neurona];
	}
	
	public void setPesoAdaline(int neurona, double valor){
		this.pesosAdaline[neurona] = valor;
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
		this.errores.clear();
		
			while(!finalizado){ 
				
				
				for(int n = 0; n < entradas.length; n++){
					
					System.out.println("ENTRADA " + (n+1) + " ------------------------------------------");
					
					//Calcular todos los n (a) (salidas de cada neurona de cada capa) dada una entrada
					this.feedForward(Arrays.copyOfRange(entradas[n], 0, entradas[n].length-1));
					
					//Ejecutar el Adaline con la salida del feed y los pesos asignados
					valorActivacion = this.obtenidaAdaline(this.salidasPorCapa[this.getNumeroCapasOcultas() - 1]);
					y = Integer.valueOf(entradas[n][entradas[n].length-1]); //Clase esperada
					System.out.println("Clase esperada " + y);
					
					//Calculo del error de neurona final
					error = y - valorActivacion;
					System.out.println("Error: " + error);
					
					//Calcular sensibilidad (backward), repartir el error
					this.calcularSensibilidad(valorActivacion, error, entradas[n].length-1);
					
					//Ajuste de pesos, mando el numero de elementos por entrada y la entrada inicial
					this.ajustarPesos(entradas[n].length-1, Arrays.copyOfRange(entradas[n], 0, entradas[n].length-1));
					
					errorCuadraticoMedio = Math.pow(error, 2); //Error cuadratico
					this.errorDeseadoFinal +=errorCuadraticoMedio; //Error acumulado

				}
				
				this.numeroEpocasFinal++;
				this.errorDeseadoFinal /= entradas.length;
				
				//Guardar el error para la grafica
				this.errores.add(this.errorDeseadoFinal);
				
				System.out.println("["+ this.numeroEpocasFinal +"] Error Esperado: " + this.errorDeseadoFinal);
				
				if(this.numeroEpocasFinal >= this.limiteEpocas || this.errorDeseadoFinal <= this.errorDeseado)
					finalizado = true;
				else
					this.errorDeseadoFinal = 0;
			}
		
	}
	
	
	private double obtenidaAdaline(String [] salidasNeuronas){
		double sumatoria = 0, fn = 0;		
		
		//Agregar entrada umbral
		String umbral [] = {"-1"};
		String[] temp = new String[salidasNeuronas.length+1];
		System.arraycopy(umbral, 0, temp, 0, umbral.length);
		System.arraycopy(salidasNeuronas, 0, temp, umbral.length, salidasNeuronas.length);
		salidasNeuronas = temp;
		
		for(int i = 0; i < salidasNeuronas.length; i++){
			sumatoria+=(Double.valueOf(salidasNeuronas[i]) * this.getPesoAdaline(i));
		}
		
		fn = 1 / (1 + Math.exp(-1 * sumatoria));
				
		return fn;

	}
	
	public void ajustarPesosAdaline(String [] salidasNeuronas, double error){
		for(int i = 0; i < salidasNeuronas.length; i++){ 
			this.setPesoAdaline(i, this.getPesoAdaline(i) + this.razonAprendizaje * error * this.obtenidaAdaline(salidasNeuronas) * (1-this.obtenidaAdaline(salidasNeuronas)) * Double.valueOf(salidasNeuronas[i]));
			System.out.println(i + " " + this.getPesoAdaline(i));
		}
	}
	
	private double obtenerSalidaNeurona(String [] entradas, int capa, int neurona){
		double sumatoria = 0, salidaNeurona = 0;		

		for(int j = 0; j < entradas.length; j++){
			sumatoria+=(Double.valueOf(entradas[j]) * this.getPesoLlaveEntera(capa, neurona, j));
		}
		
		//System.out.println("N["+capa+"]["+neurona+"]: " + sumatoria);
		
		salidaNeurona = 1 / (1 + Math.exp(-1 * sumatoria));
		
		return salidaNeurona;

	}
	
	private void feedForward(String [] entradas){
		
		double valorActivacion = 0;
		String [] a = null;
		
		for(int m = 0; m < this.getNumeroCapasOcultas(); m++){
			//System.out.println("CAPA OCULTA " + (m+1) + " **************************");

			
			for (int i = 0; i < this.getNumeroNeuronasPorCapa(); ++i){
				//System.out.println("NEURONA " + (i+1));
				
				if(m == 0){
					a = entradas;
				}else{
					
					//Agregar entrada umbral
					String umbral [] = {"-1"};
					String[] temp = new String[this.salidasPorCapa[m-1].length+1];
					System.arraycopy(umbral, 0, temp, 0, umbral.length);
					System.arraycopy(this.salidasPorCapa[m-1], 0, temp, umbral.length, this.salidasPorCapa[m-1].length);
					
					a = temp;

				}
				
				valorActivacion = this.obtenerSalidaNeurona(a, m, i);
				//System.out.println("A["+m+"]["+i+"]: " + valorActivacion);
				
				//Guardar salida de la neurona (a)
				this.setSalidasPorCapa(m, i, String.valueOf(valorActivacion));
								
			}
		}
				
	}
	
	private void calcularSensibilidad(double valorActivacion, double error, int numeroEntradasIniciales){
		
		//Derivada de la funcion de activacion y(1-y)
		double derivadaAdaline = valorActivacion * (1-valorActivacion);
		this.sensibilidadAdaline = -2 * derivadaAdaline * error;
		//System.out.println("S[" + this.getNumeroCapasOcultas() + "] Adaline = " + this.sensibilidadAdaline);
		int pesosRecorrer = 0;
		
		double sensibilidad = 0;
		double sensibilidadAdelante = 0;
		
			for(int m = this.getNumeroCapasOcultas()-1; m >= 0; m--){

				for (int i = 0; i < this.getNumeroNeuronasPorCapa(); ++i){
					sensibilidad = 0;
					double derivada = Double.valueOf(this.getSalidasPorCapa(m, i)) * (1 - Double.valueOf(this.getSalidasPorCapa(m, i)));
						
					 sensibilidadAdelante = (m == this.getNumeroCapasOcultas()-1) ? this.sensibilidadAdaline : this.getSensibilidades(m+1, i);
					 pesosRecorrer = this.getNumeroNeuronasPorCapa();
							
						for(int peso = 1; peso <= pesosRecorrer; peso++){ 
							
							if(m == this.getNumeroCapasOcultas()-1){
								//Pesos Adaline
								sensibilidad += derivada * this.getPesoAdaline(peso) * sensibilidadAdelante;
							}else{
								//Pesos normales
								sensibilidad += derivada * this.getPesoLlaveEntera(m+1, i, peso) * sensibilidadAdelante;
							}
							
						}
						
					this.setSensibilidades(m, i, sensibilidad);
					//System.out.println("S["+m+"]["+i+"] = " + sensibilidad);
				}
				
			}
	}
	
	private void ajustarPesos(int numeroEntradasIniciales, String [] entradas){
		System.out.println("*** Ajuste de pesos ***");
		int entradasRecorrer = 0;
		String [] a;
		
		for(int m = 0; m < this.getNumeroCapasOcultas(); m++){
			for(int i = 0; i < this.getNumeroNeuronasPorCapa(); i++){
				
				//La capa oculta 1 (0) recibe 9 entradas (8 y 1 thres)
				if(m == 0){
					entradasRecorrer = numeroEntradasIniciales;
					a = entradas;
				}else{
					entradasRecorrer = this.getNumeroNeuronasPorCapa() + 1;
					
					//Agregar entrada umbral
					String umbral [] = {"-1"};
					String[] temp = new String[this.salidasPorCapa[m-1].length+1];
					System.arraycopy(umbral, 0, temp, 0, umbral.length);
					System.arraycopy(this.salidasPorCapa[m-1], 0, temp, umbral.length, this.salidasPorCapa[m-1].length);
					
					a = temp;
				}
				
				for(int j = 0; j < entradasRecorrer; j++){
					this.setPesoLlaveEntera(m ,i, j, this.getPesoLlaveEntera(m, i, j) + (-1 * this.razonAprendizaje) * this.getSensibilidades(m, i) * Double.valueOf(a[j]));
				}
			}
		}
		
		//Ajuste de pesos de la neurona de salida Adaline
		int m = this.getNumeroCapasOcultas();
		String[] entradaCapaAdaline = this.salidasPorCapa[m-1];
		
		//Agregar entrada umbral
		String umbral [] = {"-1"};
		String[] temp = new String[entradaCapaAdaline.length+1];
		System.arraycopy(umbral, 0, temp, 0, umbral.length);
		System.arraycopy(entradaCapaAdaline, 0, temp, umbral.length, entradaCapaAdaline.length);
		entradaCapaAdaline = temp;
		
		for(int i = 0; i < entradaCapaAdaline.length; i++){ 
			this.setPesoAdaline(i, this.getPesoAdaline(i) + (-1 * this.razonAprendizaje) * this.sensibilidadAdaline * Double.valueOf(entradaCapaAdaline[i]));
			
		}
		
		
	}
	
	public String[][] clasificar(String[][] entradasClasificar){
		System.out.println("CLASIFICACION ********************************");
		for(int n = 0; n < entradasClasificar.length; n++){
			
			double fy = 0;	
			
			String umbral [] = {"-1"};
			String[] temp = new String[Arrays.copyOfRange(entradasClasificar[n], 0, entradasClasificar[n].length-1).length+1];
			System.arraycopy(umbral, 0, temp, 0, umbral.length);
			System.arraycopy(Arrays.copyOfRange(entradasClasificar[n], 0, entradasClasificar[n].length-1), 0, temp, umbral.length, Arrays.copyOfRange(entradasClasificar[n], 0, entradasClasificar[n].length-1).length);
			
			entradasClasificar[n] = temp;
			this.feedForward(entradasClasificar[n]);
			
			fy = this.obtenidaAdaline(this.salidasPorCapa[this.getNumeroCapasOcultas() - 1]);
			
			if(fy >= 0.9) 
				entradasClasificar[n][this.getNumeroEntradas()] = "1";
			else
				entradasClasificar[n][this.getNumeroEntradas()] = "0";				
			
		}
		
		return entradasClasificar;
	}

}
