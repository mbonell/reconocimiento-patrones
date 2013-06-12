package core;

public class DistanciaMinima {
	

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
			resultados[i][0] = data[i][0];
			resultados[i][1] = data[i][1];
			              
			//Distancia Euclidiana para beisbol
			double deBeisbol = this.calcularDistanciaEuclidiana(Double.parseDouble(data[i][0]), Double.parseDouble(data[i][1]), this.pesoBeisbol, this.diametroBeisbol);
			resultados[i][2] = Double.toString(deBeisbol);
			
			//Distancia Euclidiana para futbol
			double deFutbol = this.calcularDistanciaEuclidiana(Double.parseDouble(data[i][0]), Double.parseDouble(data[i][1]), this.pesoFutbol, this.diametroFutbol);
			resultados[i][3] = Double.toString(deFutbol);
			
			//Minimo de la Distancia Euclidiana
			double deMinimo = Math.min(deBeisbol, deFutbol);
			resultados[i][4] = Double.toString(deMinimo);
			
			//Establecer la clase segun la Distancia Euclidiana
			resultados[i][5] = (deMinimo == deBeisbol) ? "Beisbol" : "Futbol";
			
			
			//Distancia Manhattan para beisbol
			double dmBeisbol = this.calcularDistanciaManhattan(Double.parseDouble(data[i][0]), Double.parseDouble(data[i][1]), this.pesoBeisbol, this.diametroBeisbol);
			resultados[i][6] = Double.toString(dmBeisbol);
			
			//Distancia Manhattan para futbol
			double dmFutbol = this.calcularDistanciaManhattan(Double.parseDouble(data[i][0]), Double.parseDouble(data[i][1]), this.pesoFutbol, this.diametroFutbol);
			resultados[i][7] = Double.toString(dmFutbol);
			
			//Minimo de la Distancia Euclidiana
			double dmMinimo = Math.min(dmBeisbol, dmFutbol);
			resultados[i][8] = Double.toString(dmMinimo);
			
			//Establecer la clase segun la Distancia Manhattan
			resultados[i][9] = (dmMinimo == dmBeisbol) ? "Beisbol" : "Futbol";

			System.out.println(resultados[i][0]);
			System.out.println(resultados[i][1]);
			System.out.println(resultados[i][2]);
			System.out.println(resultados[i][3]);
			System.out.println(resultados[i][4]);
			System.out.println(resultados[i][5]);
			System.out.println(resultados[i][6]);
			System.out.println(resultados[i][7]);
			System.out.println(resultados[i][8]);
			System.out.println(resultados[i][9]);

	
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
