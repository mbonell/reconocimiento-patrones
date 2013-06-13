package core;

public class DistanciaMinima {
	

	public final int PESO = 0;
	public final int DIAMETRO = 1;
	public final int DISTANCIA_EUCLIDIANA_BEISBOL = 2;
	public final int DISTANCIA_EUCLIDIANA_FUTBOL = 3;
	public final int DISTANCIA_EUCLIDIANA_MINIMA = 4;
	public final int DISTANCIA_EUCLIDIANA_CLASE = 5;
	public final int DISTANCIA_MANHATTAN_BEISBOL = 6;
	public final int DISTANCIA_MANHATTAN_FUTBOL = 7;
	public final int DISTANCIA_MANHATTAN_MINIMA = 8;
	public final int DISTANCIA_MANHATTAN_CLASE = 9;
	
	
	public final String BEISBOL = "Béisbol";
	public final String FUTBOL = "Fútbol";

	private double pesoBeisbol = 142;
	private double diametroBeisbol = 7;


	private double pesoFutbol = 420;
	private double diametroFutbol = 21.5;
	
	
	public double obtenerPesoBeisbol(){
		return this.pesoBeisbol;
	}
	
	public double obtenerDiametroBesibol(){
		return this.diametroBeisbol;
	}
	
	public double obtenerPesoFutbol(){
		return this.pesoFutbol;
	}
	
	public double obtenerDiametroFutbol(){
		return this.diametroFutbol;
	}
	
	public String[][] generarClasificacion(String[][] data){
		
		String[][] resultados = new String[data.length][10];
		
		for(int i = 0; i<data.length; i++){
			
			//Copiar valores de los nuevos patrones a evaluar
			resultados[i][PESO] = data[i][PESO];
			resultados[i][DIAMETRO] = data[i][DIAMETRO];
			              
			//Distancia Euclidiana para beisbol
			double deBeisbol = this.calcularDistanciaEuclidiana(Double.parseDouble(data[i][PESO]), Double.parseDouble(data[i][DIAMETRO]), this.pesoBeisbol, this.diametroBeisbol);
			resultados[i][DISTANCIA_EUCLIDIANA_BEISBOL] = Double.toString(deBeisbol);
			
			//Distancia Euclidiana para futbol
			double deFutbol = this.calcularDistanciaEuclidiana(Double.parseDouble(data[i][PESO]), Double.parseDouble(data[i][DIAMETRO]), this.pesoFutbol, this.diametroFutbol);
			resultados[i][DISTANCIA_EUCLIDIANA_FUTBOL] = Double.toString(deFutbol);
			
			//Minimo de la Distancia Euclidiana
			double deMinimo = Math.min(deBeisbol, deFutbol);
			resultados[i][DISTANCIA_EUCLIDIANA_MINIMA] = Double.toString(deMinimo);
			
			//Establecer la clase segun la Distancia Euclidiana
			resultados[i][DISTANCIA_EUCLIDIANA_CLASE] = (deMinimo == deBeisbol) ? BEISBOL : FUTBOL;
			
			
			//Distancia Manhattan para beisbol
			double dmBeisbol = this.calcularDistanciaManhattan(Double.parseDouble(data[i][PESO]), Double.parseDouble(data[i][DIAMETRO]), this.pesoBeisbol, this.diametroBeisbol);
			resultados[i][DISTANCIA_MANHATTAN_BEISBOL] = Double.toString(dmBeisbol);
			
			//Distancia Manhattan para futbol
			double dmFutbol = this.calcularDistanciaManhattan(Double.parseDouble(data[i][PESO]), Double.parseDouble(data[i][DIAMETRO]), this.pesoFutbol, this.diametroFutbol);
			resultados[i][DISTANCIA_MANHATTAN_FUTBOL] = Double.toString(dmFutbol);
			
			//Minimo de la Distancia Euclidiana
			double dmMinimo = Math.min(dmBeisbol, dmFutbol);
			resultados[i][DISTANCIA_MANHATTAN_MINIMA] = Double.toString(dmMinimo);
			
			//Establecer la clase segun la Distancia Manhattan
			resultados[i][DISTANCIA_MANHATTAN_CLASE] = (dmMinimo == dmBeisbol) ? BEISBOL : FUTBOL;
	
		}
		
		return resultados;
	}
	
	private double calcularDistanciaEuclidiana(double x1, double x2, double y1, double y2){
		double abs1 = (x1-y1 >= 0) ? (x1-y1) : (x1-y1)*-1;
		double abs2 = (x1-y1 >= 0) ? (x1-y1) : (x1-y1)*-1;
		return Math.sqrt((abs1*abs1) + (abs2*abs2));
		
	}

	private double calcularDistanciaManhattan(double x1, double x2, double y1, double y2){
		double abs1 = (x1-y1 >= 0) ? (x1-y1) : (x1-y1)*-1;
		double abs2 = (x1-y1 >= 0) ? (x1-y1) : (x1-y1)*-1;
		return abs1 + abs2;		
	}
	
}
