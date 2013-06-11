package core;
/**
 * @author Marcela Bonell Manjarrez
 * @version 1.0 Algoritmo Gen�tico Simple
 * �ltima modificacion: 30/06/09
 * Descripci�n: Clase que contiene los algoritmos para un AGS
 */

public class DistanciaMinima {
	
	public double x1,x2,y1,y2,pm,pc;
	public int numI,numG,numB,formula;
	public String log,fitness,seleccion,elitismo,tipo,objetivo,ruta,x1s,x2s,y1s,y2s,pms,pcs;
	
	public static double pesoBeisbol = 142;
	public static double diametroBeisbol = 7;

	public static double pesoFutbol = 420;
	public static double diametroFutbol = 21.5;
	
	public boolean validarLimites(double x1, double x2, double y1, double y2){
		if(x1>=x2 || y1>=y2){
			log="Error: x1 debe ser menor que x2 y y1 menor que y2";
			  return false;
		}
		else{ 
			this.x1=x1;
			this.x2=x2;
			this.y1=y1;
			this.y2=y2;
			return true;
		}
	}

	public int tipoArchivo(){
		
		if(seleccion.equals("50 - 50%")){
			return getColor(1);
		}else if(seleccion.equals("Rueda Ruleta")){
			return getColor(2);	
		}else{
			return getColor(3);
		}
	}
	
	private int getColor(int c){
		if(c==1){
			if(tipo.equals("M�ximo") && elitismo.equals("S�"))
				return 1;
		    else if (tipo.equals("M�ximo") && elitismo.equals("No"))
				return 4;
			else if (tipo.equals("M�nimo") && elitismo.equals("S�"))
				return 7;
			else
				return 10;
		}else if(c==2){
			if(tipo.equals("M�ximo") && elitismo.equals("S�"))
				return 2;
		    else if (tipo.equals("M�ximo") && elitismo.equals("No"))
				return 5;
			else if (tipo.equals("M�nimo") && elitismo.equals("S�"))
				return 8;
			else
				return 11;
		}else{
			if(tipo.equals("M�ximo") && elitismo.equals("S�"))
				return 3;
		    else if (tipo.equals("M�ximo") && elitismo.equals("No"))
				return 6;
			else if (tipo.equals("M�nimo") && elitismo.equals("S�"))
				return 9;
			else
				return 12;
		}
	}
	
}
