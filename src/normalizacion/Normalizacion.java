package normalizacion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Normalizacion {

    public double obtenerMaximo(int columna, String csvFile){
    	String line = "";
    	String cvsSplitBy = ",";
		BufferedReader br = null;
    	double maximo = 0;
    	boolean primera = true;
    	
    	try {
    		
			br = new BufferedReader(new FileReader(csvFile));

			while ((line = br.readLine()) != null) {
				String[] patron = line.split(cvsSplitBy);
				
					if(primera){
						primera = false;
						maximo = Double.valueOf(patron[columna]);
					}
				
					if(Double.valueOf(patron[columna]) > maximo){
						maximo = Double.valueOf(patron[columna]);
					}

			}

	 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error al obtener el maximo");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} 
		
    	return maximo;
    }
    
    
    public double obtenerMinimo(int columna, String csvFile){
    	String line = "";
    	String cvsSplitBy = ",";
		BufferedReader br = null;
    	double minimo = 0;
    	boolean primera = true;
    	
    	try {
    		
			br = new BufferedReader(new FileReader(csvFile));

			while ((line = br.readLine()) != null) {
				String[] patron = line.split(cvsSplitBy);
				
					if(primera){
						primera = false;
						minimo = Double.valueOf(patron[columna]);
					}
				
					if(Double.valueOf(patron[columna]) < minimo){
						minimo = Double.valueOf(patron[columna]);
					}

			}

	 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error al obtener el maximo");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} 
		
    	return minimo;
    }
    
}
