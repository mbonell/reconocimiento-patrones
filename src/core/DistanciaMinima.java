package core;

public class DistanciaMinima {
	
	
	public static double pesoBeisbol = 142;
	public static double diametroBeisbol = 7;

	public static double pesoFutbol = 420;
	public static double diametroFutbol = 21.5;
	

	@SuppressWarnings("static-access")
	public void generarClasificacion(String[][] data){
		for(int i = 0; i<data.length; i++){
			
			//Distancia Euclidiana para beisbol
			double deBeisbol = this.calcularDistanciaEuclidiana(Double.parseDouble(data[i][0]), Double.parseDouble(data[i][1]), this.pesoBeisbol, this.diametroBeisbol);
			data[i][2] = Double.toString(deBeisbol);
			
			//Distancia Euclidiana para futbol
			double deFutbol = this.calcularDistanciaEuclidiana(Double.parseDouble(data[i][0]), Double.parseDouble(data[i][1]), this.pesoFutbol, this.diametroFutbol);
			data[i][3] = Double.toString(deFutbol);
			
			//Minimo de la Distancia Euclidiana
			double deMinimo = Math.min(deBeisbol, deFutbol);
			data[i][4] = Double.toString(deMinimo);
			
			//Establecer la clase segun la Distancia Euclidiana
			data[i][5] = (deMinimo == deBeisbol) ? "Beisbol" : "Futbol";
			
			
			//Distancia Manhattan para beisbol
			double dmBeisbol = this.calcularDistanciaManhattan(Double.parseDouble(data[i][0]), Double.parseDouble(data[i][1]), this.pesoBeisbol, this.diametroBeisbol);
			data[i][6] = Double.toString(dmBeisbol);
			
			//Distancia Manhattan para futbol
			double dmFutbol = this.calcularDistanciaManhattan(Double.parseDouble(data[i][0]), Double.parseDouble(data[i][1]), this.pesoFutbol, this.diametroFutbol);
			data[i][7] = Double.toString(dmFutbol);
			
			//Minimo de la Distancia Euclidiana
			double dmMinimo = Math.min(dmBeisbol, dmFutbol);
			data[i][8] = Double.toString(dmMinimo);
			
			//Establecer la clase segun la Distancia Manhattan
			data[i][9] = (dmMinimo == dmBeisbol) ? "Beisbol" : "Futbol";

			System.out.println(data[i][2]);
			System.out.println(data[i][3]);
			System.out.println(data[i][4]);
			System.out.println(data[i][5]);
			System.out.println(data[i][6]);
			System.out.println(data[i][7]);
			System.out.println(data[i][8]);
			System.out.println(data[i][9]);

	
		}
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
