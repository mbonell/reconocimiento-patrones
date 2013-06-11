package core;

public class DistanciaMinima {
	
	
	public static double pesoBeisbol = 142;
	public static double diametroBeisbol = 7;

	public static double pesoFutbol = 420;
	public static double diametroFutbol = 21.5;
	

	
	public void generarClasificacion(String[][] data){
		for(int i = 0; i<data.length; i++){
			
			//Distancia Euclidiana para beisbol
			data[i][3] = this.calcularDistanciaEuclidiana(Double.parseDouble(data[i][0]), Double.parseDouble(data[i][1]), this.pesoBeisbol, this.diametroBeisbol);
			
		}
	}
	
	private String calcularDistanciaEuclidiana(double x1, double x2, double y1, double y2){
		double abs1 = (x1-y1 >= 0) ? (x1-y1) : (x1-y1)*-1;
		double abs2 = (x1-y1 >= 0) ? (x1-y1) : (x1-y1)*-1;


		return Double.toString(Math.sqrt((abs1 * abs1) + (abs2*abs2)));
		
	}

	
}
