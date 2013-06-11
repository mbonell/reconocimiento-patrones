package core;
import java.util.Collections;
import java.util.Vector;
/**
 * @author Marcela Bonell Manjarrez
 * @version 1.0 Algoritmo Genético Simple
 * Última modificacion: 09/08/09
 * Descripción: Clase que contiene los algoritmos para el metodo de Torneo
 */

public class Torneo{
	
	public double x1,x2,y1,y2,pm,pc,sumaFitness,sumaFitnessMax,promedio,promedioMax,maximoOminimo,maximoOminimoMax,sumaFitnessM,promedioM,maximoM;
	public int numI,numG,numB,formula;
	public String elitismo,tipo,elite,eliteM,eliteV,eliteMax;
	double maxViejo;
	
	StringBuilder cadena=new StringBuilder();
	public Vector <String>poblacionInicial=new Vector<String>();
	public Vector <String>poblacionInicialM=new Vector<String>();
	public Vector <String>poblacionInicialMax=new Vector<String>();


	public Vector <Long>enteroX=new Vector<Long>();
	public Vector <Long>enteroY=new Vector<Long>();
	public Vector <Long>enteroXM=new Vector<Long>();
	public Vector <Long>enteroYM=new Vector<Long>();
	public Vector <Long>enteroXMax=new Vector<Long>();
	public Vector <Long>enteroYMax=new Vector<Long>();

	public Vector <Double>mutacion=new Vector<Double>();
	public Vector <String>despuesMutacion= new Vector<String>();
	public Vector <Double>realX=new Vector<Double>();
	public Vector <Double>realY=new Vector<Double>();
	public Vector <Double>realFitness=new Vector<Double>();
	public Vector <Double>realXM=new Vector<Double>();
	public Vector <Double>realYM=new Vector<Double>();
	public Vector <Double>realFitnessM=new Vector<Double>();
	public Vector <Double>realXMax=new Vector<Double>();
	public Vector <Double>realYMax=new Vector<Double>();
	public Vector <Double>realFitnessMax=new Vector<Double>();

	/* Variables 5050%*/	
	public Vector <Integer>parejaAleatoria=new Vector<Integer>();
	public Vector <String>ordenacion=new Vector<String>();
	public Vector <Integer>parejaAleatoria2=new Vector<Integer>();
	public Vector <String>ordenacion2=new Vector<String>();
	public Vector <Double>cruzaclona=new Vector<Double>();
	public Vector <String>desicion=new Vector<String>();
	public Vector <Integer>puntodecruza=new Vector<Integer>();
	public Vector <String>yacruzados=new Vector<String>();
	public Vector <String>aplicaElitismo = new Vector<String>();
	public Vector <String>padres = new Vector<String>();


	public Torneo(double x1,double x2,double y1,double y2,double pm,double pc,String elitismo,String tipo,int numI,int numG,int numB,int formula){
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
		this.pm=pm;
		this.pc=pc;
		this.elitismo=elitismo;
		this.tipo=tipo;
		this.numI=numI;
		this.numG=numG;
		this.numB=numB;
		this.formula=formula;

		
	}
	
	public void poblacionInicial(int bites,int ind){
		poblacionInicialM.clear();
		poblacionInicial.clear();
		poblacionInicialMax.clear();
		enteroX.clear();
		enteroY.clear();
		enteroXM.clear();
		enteroYM.clear();
		realXM.clear();
		realYM.clear();
		realFitnessM.clear();
		sumaFitnessM=0;
		String cadena="";
		
		for(int x=1; x<=ind; x++){
			cadena="";
					for(int i=1; i<=bites*2; i++){
						if(Math.random()>=0.5)
							cadena+="1";
						else
							cadena+="0";
				}
				
			poblacionInicialM.addElement(cadena);
			
			enteroXM.addElement(Long.parseLong(cadena.substring(0, bites),2));
			enteroYM.addElement(Long.parseLong(cadena.substring(bites, (bites*2)),2));
			
			realXM.addElement(((Math.abs(x2-x1)/(Math.pow(2,bites)-1))*enteroXM.elementAt(x-1))+x1);
			realYM.addElement(((Math.abs(y2-y1)/(Math.pow(2,bites)-1))*enteroYM.elementAt(x-1))+y1);
			
			
			if(tipo.equals("Mínimo")){
				if(formula==1)
					realFitnessM.addElement((-1)*(Math.sin(Math.toRadians(Math.sqrt(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2))))/Math.sqrt(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2))));
				else if(formula==2)
					realFitnessM.addElement((-1)*(Math.sin(Math.toRadians(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2)))/(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2))));
				else if(formula==3)
					realFitnessM.addElement((-1)*(0.5-((Math.pow(Math.sin(Math.toRadians(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2))),2)-0.5) /(Math.pow((1+(0.001*(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2)))),2)))));
				else if(formula==4)
					realFitnessM.addElement((-1)*(4000-(100*Math.pow((Math.pow(realXM.elementAt(x-1),2)-realYM.elementAt(x-1)),2))+Math.pow((1-realXM.elementAt(x-1)),2)));
			}else{
				if(formula==1)
					realFitnessM.addElement(Math.sin(Math.toRadians(Math.sqrt(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2))))/Math.sqrt(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2)));
				else if(formula==2)
					realFitnessM.addElement(Math.sin(Math.toRadians(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2)))/(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2)));
				else if(formula==3)
					realFitnessM.addElement(0.5-((Math.pow(Math.sin(Math.toRadians(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2))),2)-0.5) /(Math.pow((1+(0.001*(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2)))),2))));
				else if(formula==4)
					realFitnessM.addElement(4000-(100*Math.pow((Math.pow(realXM.elementAt(x-1),2)-realYM.elementAt(x-1)),2))+Math.pow((1-realXM.elementAt(x-1)),2));
			}
			
			
			sumaFitnessM+=realFitnessM.elementAt(x-1);
			
		}
		promedioM=sumaFitnessM/ind;
		maximoM=Collections.max(realFitnessM);
		eliteM=poblacionInicialM.elementAt(realFitnessM.indexOf(maximoM)).toString();

	}
	
	public void poblaciones(int bites,int ind){
		
		//System.out.println("pim "+poblacionInicialM.size());
		//System.out.println("rfm "+realFitnessM.size());
		
		
		eliteV=eliteM;
		maxViejo=maximoM;
		String cadena="";
		poblacionInicial.removeAllElements();
		poblacionInicial.addAll(poblacionInicialM);
		
		//System.out.println("pi "+poblacionInicial.size());
		
		poblacionInicialM.removeAllElements();
		realFitnessM.removeAllElements();
		enteroX.removeAllElements();
		enteroY.removeAllElements();
		realX.removeAllElements();
		realY.removeAllElements();
		realFitness.removeAllElements();
		
		formarParejas(ind);
		
		
		for(int x=1; x<=ind; x++){
			cadena="";
			cadena=ordenacion.elementAt(x-1);
			
			enteroX.addElement(Long.parseLong(cadena.substring(0, bites),2));
			enteroY.addElement(Long.parseLong(cadena.substring(bites, (bites*2)),2));
			
			realX.addElement(((Math.abs(x2-x1)/(Math.pow(2,bites)-1))*enteroX.elementAt(x-1))+x1);
			realY.addElement(((Math.abs(y2-y1)/(Math.pow(2,bites)-1))*enteroY.elementAt(x-1))+y1);
			
			
			if(tipo.equals("Mínimo")){
				if(formula==1)
					realFitness.addElement((-1)*(Math.sin(Math.toRadians(Math.sqrt(Math.pow(realX.elementAt(x-1),2)+Math.pow(realY.elementAt(x-1),2))))/Math.sqrt(Math.pow(realX.elementAt(x-1),2)+Math.pow(realY.elementAt(x-1),2))));
				else if(formula==2)
					realFitness.addElement((-1)*(Math.sin(Math.toRadians(Math.pow(realX.elementAt(x-1),2)+Math.pow(realY.elementAt(x-1),2)))/(Math.pow(realX.elementAt(x-1),2)+Math.pow(realY.elementAt(x-1),2))));
				else if(formula==3)
					realFitness.addElement((-1)*(0.5-((Math.pow(Math.sin(Math.toRadians(Math.pow(realX.elementAt(x-1),2)+Math.pow(realY.elementAt(x-1),2))),2)-0.5) /(Math.pow((1+(0.001*(Math.pow(realX.elementAt(x-1),2)+Math.pow(realY.elementAt(x-1),2)))),2)))));
				else if(formula==4)
					realFitness.addElement((-1)*(4000-(100*Math.pow((Math.pow(realX.elementAt(x-1),2)-realY.elementAt(x-1)),2))+Math.pow((1-realX.elementAt(x-1)),2)));
			}else{
				if(formula==1)
					realFitness.addElement(Math.sin(Math.toRadians(Math.sqrt(Math.pow(realX.elementAt(x-1),2)+Math.pow(realY.elementAt(x-1),2))))/Math.sqrt(Math.pow(realX.elementAt(x-1),2)+Math.pow(realY.elementAt(x-1),2)));
				else if(formula==2)
					realFitness.addElement(Math.sin(Math.toRadians(Math.pow(realX.elementAt(x-1),2)+Math.pow(realY.elementAt(x-1),2)))/(Math.pow(realX.elementAt(x-1),2)+Math.pow(realY.elementAt(x-1),2)));
				else if(formula==3)
					realFitness.addElement(0.5-((Math.pow(Math.sin(Math.toRadians(Math.pow(realX.elementAt(x-1),2)+Math.pow(realY.elementAt(x-1),2))),2)-0.5) /(Math.pow((1+(0.001*(Math.pow(realX.elementAt(x-1),2)+Math.pow(realY.elementAt(x-1),2)))),2))));
				else if(formula==4)
					realFitness.addElement(4000-(100*Math.pow((Math.pow(realX.elementAt(x-1),2)-realY.elementAt(x-1)),2))+Math.pow((1-realX.elementAt(x-1)),2));
			}
			

		}
		
		// Selecciono a los padres
		for(int x=1; x<=ind; x++){
			
			if(realFitness.elementAt(x-1)>realFitness.elementAt(x))
				padres.addElement(ordenacion.elementAt(x-1));
			else
				padres.addElement(ordenacion.elementAt(x));
			
				x++;
		}
		
		 parejaAleatoria2.removeAllElements();
		 ordenacion2.removeAllElements();
		 
			for(int v=1; v<=ind/2; v++){
				parejaAleatoria2.addElement(v);
			}
			
			Collections.shuffle(parejaAleatoria2);
		
		
			for(int v=0; v<ind/2; v++){
				ordenacion2.addElement("0");
			}	
				
			for(int x=0; x<ind/2; x++){
					ordenacion2.setElementAt(padres.elementAt(x), parejaAleatoria2.elementAt(x)-1);
			}	
		
			
		cruzaClonaTorneo(ind/2);
		
	}
	
	public void poblaciones2y3(int bites,int ind){
		
		String cadena="";
		poblacionInicialMax.removeAllElements();
		//System.out.println("p2y3 "+ ordenacion2.size());
		ordenacion2.addAll(despuesMutacion);
		//System.out.println("p2y3 mut "+ ordenacion2.size());

		poblacionInicialMax.addAll(ordenacion2);
		
		for(int x=1; x<=ind; x++){
			cadena="";
			cadena=poblacionInicialMax.elementAt(x-1);
			
			enteroXMax.addElement(Long.parseLong(cadena.substring(0, bites),2));
			enteroYMax.addElement(Long.parseLong(cadena.substring(bites, (bites*2)),2));
			
			realXMax.addElement(((Math.abs(x2-x1)/(Math.pow(2,bites)-1))*enteroXMax.elementAt(x-1))+x1);
			realYMax.addElement(((Math.abs(y2-y1)/(Math.pow(2,bites)-1))*enteroYMax.elementAt(x-1))+y1);
			
			
			if(tipo.equals("Mínimo")){
				if(formula==1)
					realFitnessMax.addElement((-1)*(Math.sin(Math.toRadians(Math.sqrt(Math.pow(realXMax.elementAt(x-1),2)+Math.pow(realYMax.elementAt(x-1),2))))/Math.sqrt(Math.pow(realXMax.elementAt(x-1),2)+Math.pow(realYMax.elementAt(x-1),2))));
				else if(formula==2)
					realFitnessMax.addElement((-1)*(Math.sin(Math.toRadians(Math.pow(realXMax.elementAt(x-1),2)+Math.pow(realYMax.elementAt(x-1),2)))/(Math.pow(realXMax.elementAt(x-1),2)+Math.pow(realYMax.elementAt(x-1),2))));
				else if(formula==3)
					realFitnessMax.addElement((-1)*(0.5-((Math.pow(Math.sin(Math.toRadians(Math.pow(realXMax.elementAt(x-1),2)+Math.pow(realYMax.elementAt(x-1),2))),2)-0.5) /(Math.pow((1+(0.001*(Math.pow(realXMax.elementAt(x-1),2)+Math.pow(realYMax.elementAt(x-1),2)))),2)))));
				else if(formula==4)
					realFitnessMax.addElement((-1)*(4000-(100*Math.pow((Math.pow(realXMax.elementAt(x-1),2)-realYMax.elementAt(x-1)),2))+Math.pow((1-realXMax.elementAt(x-1)),2)));
			}else{
				if(formula==1)
					realFitnessMax.addElement(Math.sin(Math.toRadians(Math.sqrt(Math.pow(realXMax.elementAt(x-1),2)+Math.pow(realYMax.elementAt(x-1),2))))/Math.sqrt(Math.pow(realXMax.elementAt(x-1),2)+Math.pow(realYMax.elementAt(x-1),2)));
				else if(formula==2)
					realFitnessMax.addElement(Math.sin(Math.toRadians(Math.pow(realXMax.elementAt(x-1),2)+Math.pow(realYMax.elementAt(x-1),2)))/(Math.pow(realXMax.elementAt(x-1),2)+Math.pow(realYMax.elementAt(x-1),2)));
				else if(formula==3)
					realFitnessMax.addElement(0.5-((Math.pow(Math.sin(Math.toRadians(Math.pow(realXMax.elementAt(x-1),2)+Math.pow(realYMax.elementAt(x-1),2))),2)-0.5) /(Math.pow((1+(0.001*(Math.pow(realXMax.elementAt(x-1),2)+Math.pow(realYMax.elementAt(x-1),2)))),2))));
				else if(formula==4)
					realFitnessMax.addElement(4000-(100*Math.pow((Math.pow(realXMax.elementAt(x-1),2)-realYMax.elementAt(x-1)),2))+Math.pow((1-realXMax.elementAt(x-1)),2));
			}
			
			sumaFitnessMax+=realFitnessMax.elementAt(x-1);

		}
		
		promedioMax=sumaFitnessMax/(ind);
		maximoOminimoMax=Collections.max(realFitnessMax);
		eliteMax=poblacionInicialMax.elementAt(realFitnessMax.indexOf(maximoOminimoMax)).toString();
			
		if(elitismo.equals("Sí")){
				if(maximoOminimoMax>maxViejo){
					aplicaElitismo.addElement("NA");
					aplicaElitismo.addElement("NA");
					poblacionInicialM.removeAllElements();
					poblacionInicialM.addAll(poblacionInicialMax);
					//System.out.println("sinelite "+poblacionInicial.size());
					enteroXM.addAll(enteroXMax);
					enteroYM.addAll(enteroYMax);
					realXM.addAll(realXMax);
					realYM.addAll(realYMax);
					realFitnessM.removeAllElements();
					realFitnessM.addAll(realFitnessMax);
					sumaFitnessM=sumaFitnessMax;
					promedioM=promedioMax;
					maximoM=maximoOminimoMax;
					eliteM=eliteMax;

				}else{
					double r=Math.random();
					int index=(int) Math.rint(1+(Math.abs(1-numI)*r));
					aplicaElitismo.addElement(index+"");
					aplicaElitismo.addElement(eliteV);
					poblacionInicialM.removeAllElements();
					poblacionInicialM.addAll(poblacionInicialMax);
					//System.out.println("sinelite2 "+poblacionInicial.size());

					poblacionInicialM.setElementAt(eliteV, index-1);
					aplicarElitismo(bites,ind);
				}
		
				
		}
		
	}
	
public void poblaciones2y3NoE(int bites,int ind){
		
		String cadena="";
		poblacionInicialM.removeAllElements();
		enteroXM.removeAllElements();
		enteroYM.removeAllElements();
		realXM.removeAllElements();
		realYM.removeAllElements();
		realFitnessM.removeAllElements();
		
		//System.out.println("p2y3 "+ ordenacion2.size());
		ordenacion2.addAll(despuesMutacion);
		//System.out.println("p2y3 mut "+ ordenacion2.size());
		
		poblacionInicialM.addAll(ordenacion2);
		
		for(int x=1; x<=ind; x++){
			cadena="";
			cadena=poblacionInicialM.elementAt(x-1);
			
			enteroXM.addElement(Long.parseLong(cadena.substring(0, bites),2));
			enteroYM.addElement(Long.parseLong(cadena.substring(bites, (bites*2)),2));
			
			realXM.addElement(((Math.abs(x2-x1)/(Math.pow(2,bites)-1))*enteroXM.elementAt(x-1))+x1);
			realYM.addElement(((Math.abs(y2-y1)/(Math.pow(2,bites)-1))*enteroYM.elementAt(x-1))+y1);
			
			
			if(tipo.equals("Mínimo")){
				if(formula==1)
					realFitnessM.addElement((-1)*(Math.sin(Math.toRadians(Math.sqrt(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2))))/Math.sqrt(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2))));
				else if(formula==2)
					realFitnessM.addElement((-1)*(Math.sin(Math.toRadians(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2)))/(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2))));
				else if(formula==3)
					realFitnessM.addElement((-1)*(0.5-((Math.pow(Math.sin(Math.toRadians(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2))),2)-0.5) /(Math.pow((1+(0.001*(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2)))),2)))));
				else if(formula==4)
					realFitnessM.addElement((-1)*(4000-(100*Math.pow((Math.pow(realXM.elementAt(x-1),2)-realYM.elementAt(x-1)),2))+Math.pow((1-realXM.elementAt(x-1)),2)));
			}else{
				if(formula==1)
					realFitnessM.addElement(Math.sin(Math.toRadians(Math.sqrt(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2))))/Math.sqrt(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2)));
				else if(formula==2)
					realFitnessM.addElement(Math.sin(Math.toRadians(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2)))/(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2)));
				else if(formula==3)
					realFitnessM.addElement(0.5-((Math.pow(Math.sin(Math.toRadians(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2))),2)-0.5) /(Math.pow((1+(0.001*(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2)))),2))));
				else if(formula==4)
					realFitnessM.addElement(4000-(100*Math.pow((Math.pow(realXM.elementAt(x-1),2)-realYM.elementAt(x-1)),2))+Math.pow((1-realXM.elementAt(x-1)),2));
			}
			
			sumaFitnessM+=realFitnessM.elementAt(x-1);

		}
		
		promedioM=sumaFitnessM/(ind);
		maximoM=Collections.max(realFitnessM);
		eliteM=poblacionInicialM.elementAt(realFitnessM.indexOf(maximoM)).toString();
		
	}

	
 private void formarParejas(int ind){
	 parejaAleatoria.removeAllElements();
	 ordenacion.removeAllElements();
	 
		for(int v=1; v<=ind; v++){
			parejaAleatoria.addElement(v);
		}
		
		Collections.shuffle(parejaAleatoria);
	
	
		for(int v=0; v<ind; v++){
			ordenacion.addElement("0");
		}	
			
		for(int x=0; x<ind; x++){
				ordenacion.setElementAt(poblacionInicial.elementAt(x), parejaAleatoria.elementAt(x)-1);
		}	
	
	//System.out.println("ord "+ ordenacion.size());
	

}
	
	
	void aplicarElitismo(int bites,int ind){

		//System.out.println("elitismo "+ poblacionInicialM.size());

		String cadena="";
		for(int x=1; x<=ind; x++){
			cadena=poblacionInicialM.elementAt(x-1);
			
			enteroXM.addElement(Long.parseLong(cadena.substring(0, bites),2));
			enteroYM.addElement(Long.parseLong(cadena.substring(bites, (bites*2)),2));
			
			realXM.addElement(((Math.abs(x2-x1)/(Math.pow(2,bites)-1))*enteroXM.elementAt(x-1))+x1);
			realYM.addElement(((Math.abs(y2-y1)/(Math.pow(2,bites)-1))*enteroYM.elementAt(x-1))+y1);
						

			if(tipo.equals("Mínimo")){
				if(formula==1)
					realFitnessM.addElement((-1)*(Math.sin(Math.toRadians(Math.sqrt(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2))))/Math.sqrt(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2))));
				else if(formula==2)
					realFitnessM.addElement((-1)*(Math.sin(Math.toRadians(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2)))/(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2))));
				else if(formula==3)
					realFitnessM.addElement((-1)*(0.5-((Math.pow(Math.sin(Math.toRadians(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2))),2)-0.5) /(Math.pow((1+(0.001*(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2)))),2)))));
				else if(formula==4)
					realFitnessM.addElement((-1)*(4000-(100*Math.pow((Math.pow(realXM.elementAt(x-1),2)-realYM.elementAt(x-1)),2))+Math.pow((1-realXM.elementAt(x-1)),2)));
			}else{
				if(formula==1)
					realFitnessM.addElement(Math.sin(Math.toRadians(Math.sqrt(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2))))/Math.sqrt(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2)));
				else if(formula==2)
					realFitnessM.addElement(Math.sin(Math.toRadians(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2)))/(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2)));
				else if(formula==3)
					realFitnessM.addElement(0.5-((Math.pow(Math.sin(Math.toRadians(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2))),2)-0.5) /(Math.pow((1+(0.001*(Math.pow(realXM.elementAt(x-1),2)+Math.pow(realYM.elementAt(x-1),2)))),2))));
				else if(formula==4)
					realFitnessM.addElement(4000-(100*Math.pow((Math.pow(realXM.elementAt(x-1),2)-realYM.elementAt(x-1)),2))+Math.pow((1-realXM.elementAt(x-1)),2));
			}
			
			sumaFitnessM+=realFitnessM.elementAt(x-1);

		}
		promedioM=sumaFitnessM/ind;
		maximoM=Collections.max(realFitnessM);		
		eliteM=poblacionInicialM.elementAt(realFitnessM.indexOf(maximoM)).toString();
	}
	
	
	void cruzaClonaTorneo(int ind){
		yacruzados.removeAllElements();
		cruzaclona.removeAllElements();
		desicion.removeAllElements();
		puntodecruza.removeAllElements();
		
		for(int x=1; x<=ind; x++){
			if(x%2==1){
				cruzaclona.addElement(Math.random());

				if(cruzaclona.elementAt(cruzaclona.size()-1)<this.pc){
					desicion.addElement("cruzar");
					double r=Math.random();
					puntodecruza.addElement((int)(1+(Math.abs(1-(numB-1))*r)));
						yacruzados.addElement(ordenacion2.elementAt(x-1).substring(0, puntodecruza.elementAt(puntodecruza.size()-1))+ordenacion2.elementAt(x).substring(puntodecruza.elementAt(puntodecruza.size()-1),numB*2));
						yacruzados.addElement(ordenacion2.elementAt(x).substring(0, puntodecruza.elementAt(puntodecruza.size()-1))+ordenacion2.elementAt(x-1).substring(puntodecruza.elementAt(puntodecruza.size()-1),numB*2));
				}else{
					desicion.addElement("clonar");
					puntodecruza.addElement(0);
					yacruzados.addElement(ordenacion2.elementAt(x-1));
					yacruzados.addElement(ordenacion2.elementAt(x));
	
				}
			}

		}
		//System.out.println("cruazados "+yacruzados.size());
		mutacionTorneo(ind);
	}
	
	void mutacionTorneo(int ind){
		despuesMutacion.removeAllElements();
		mutacion.removeAllElements();
		char arr[];
		for(int x=0; x<ind; x++){
			cadena.setLength(0);
			cadena.append(yacruzados.elementAt(x));
			arr=cadena.toString().toCharArray();
				for(int y=0; y<numB*2; y++){
					mutacion.addElement(Math.random());	
						if(mutacion.elementAt(mutacion.size()-1)<pm){
							if(arr[y]=='0')
								arr[y]='1';
							else
								arr[y]='0';
						}
				}
				cadena.setLength(0);
				cadena.append(arr);
			despuesMutacion.addElement(cadena.toString());
		}
		//System.out.println("mutacion "+despuesMutacion.size());

	}

		
	public void reiniciar(){
		enteroX.clear();
		enteroY.clear();
		realX.clear();
		realY.clear();
		realFitness.clear();
		
		enteroXM.clear();
		enteroYM.clear();
		realXM.clear();
		realYM.clear();
		
		enteroXMax.clear();
		enteroYMax.clear();
		realXMax.clear();
		realYMax.clear();
		realFitnessMax.clear();
		
		parejaAleatoria.clear();
		padres.clear();
		ordenacion.clear();
		parejaAleatoria2.clear();
		ordenacion2.clear();
		cruzaclona.clear();
		desicion.clear();
		yacruzados.clear();
		puntodecruza.clear();
		mutacion.clear();
		aplicaElitismo.clear();
		cadena.delete(0,cadena.capacity()-1);
		promedio=promedioM=promedioMax=sumaFitness=sumaFitnessM=sumaFitnessMax=0;

	}
}
