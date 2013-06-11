package gui;
import gui.distancia_minima.DistanciaMinimaContainer;
import gui.distancia_minima.DistanciaMinimaController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import core.DistanciaMinima;
import core.Torneo;


public class DesktopController {
	
	DesktopContainer desk;
	DistanciaMinima ags;
	Torneo torneo;

	
	public DesktopController(DesktopContainer d) {
		this.desk=d;	
		ags=new DistanciaMinima();
		addListeners();
	}
	

	private void addListeners(){
		
		desk.itemSalir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				desk.frame.dispose();
			}			
		});
		
		
		desk.itemDistanciaMinima.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				DistanciaMinimaContainer distanciaMinimaGui = new DistanciaMinimaContainer();
				DistanciaMinimaController distanciaMinimaController = new DistanciaMinimaController();
				
				distanciaMinimaGui.ventanaDistanciaMinima(desk.desk);
				distanciaMinimaController.listenerIframe(distanciaMinimaGui);
			}			
		});
		/*
		desk.btnGrafica.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				desk.iframeGrafica();
			}			
		});
		*/
		
		desk.itemAcerca.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				desk.iframeAcerca();
			}			
		});
		
		/*
		desk.btnEjecutar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			try{	
				if(validarEntradas()){
					if(validarIndependientes() && validarProbabilidades() && validarIndividuos() && guardar()){
						getElitismo();
						getFitness();
						getSeleccion();
						getTipo();
						String exportacion=null;
						
						
						if(exportacion == ""){	
							int color=ags.tipoArchivo();
						
							switch (color) {
							case 3:
								torneo = new Torneo(ags.x1,ags.x2,ags.y1,ags.y2,ags.pm,ags.pc,ags.elitismo,ags.tipo,ags.numI,ags.numG,ags.numB,ags.formula);
								for(int i=1; i<=ags.numG; i++){
									System.out.println(i);
									if(i==1)
									torneo.poblacionInicial(ags.numB,ags.numI);
									
									else{
									torneo.poblaciones(ags.numB,ags.numI);
									torneo.poblaciones2y3(ags.numB,ags.numI);
									}
									
									torneo.reiniciar();
									
									
								}
								break;
							case 6:
								torneo = new Torneo(ags.x1,ags.x2,ags.y1,ags.y2,ags.pm,ags.pc,ags.elitismo,ags.tipo,ags.numI,ags.numG,ags.numB,ags.formula);
								for(int i=1; i<=ags.numG; i++){
									System.out.println(i);
									if(i==1)
									torneo.poblacionInicial(ags.numB,ags.numI);

									else{
									torneo.poblaciones(ags.numB,ags.numI);
									
									if(ags.elitismo.equals("S�"))
										torneo.poblaciones2y3(ags.numB,ags.numI);
									else
										torneo.poblaciones2y3NoE(ags.numB,ags.numI);
									}

									torneo.reiniciar();
									
								}
							break;
							case 9:
								torneo = new Torneo(ags.x1,ags.x2,ags.y1,ags.y2,ags.pm,ags.pc,ags.elitismo,ags.tipo,ags.numI,ags.numG,ags.numB,ags.formula);
								for(int i=1; i<=ags.numG; i++){
									System.out.println(i);
									if(i==1)
									torneo.poblacionInicial(ags.numB,ags.numI);
									
									else{
									torneo.poblaciones(ags.numB,ags.numI);
									torneo.poblaciones2y3(ags.numB,ags.numI);
									}
									
									torneo.reiniciar();
									
								}
								break;
							case 12:
								torneo = new Torneo(ags.x1,ags.x2,ags.y1,ags.y2,ags.pm,ags.pc,ags.elitismo,ags.tipo,ags.numI,ags.numG,ags.numB,ags.formula);
								for(int i=1; i<=ags.numG; i++){
									System.out.println(i);
									if(i==1)
									torneo.poblacionInicial(ags.numB,ags.numI);

									else{
									torneo.poblaciones(ags.numB,ags.numI);
									
									if(ags.elitismo.equals("S�"))
										torneo.poblaciones2y3(ags.numB,ags.numI);
									else
										torneo.poblaciones2y3NoE(ags.numB,ags.numI);
									}

									torneo.reiniciar();
									
								}
							break;
							}	
							
							
							

						
							if(exportacion == "sd"){
								

								JOptionPane.showMessageDialog(null, "Archivo Creado");
								int a=JOptionPane.showConfirmDialog(null, "Desea abrir el archivo de excel generado?");
									if(a==0){
										if(abrir()){
										}else{
											JOptionPane.showMessageDialog(null,ags.log);
										}
									}
							}else
								JOptionPane.showMessageDialog(null, "Error al guardar el libro");
								
						}else
							JOptionPane.showMessageDialog(null, "Error al exportar");
				
					}else
						JOptionPane.showMessageDialog(null, ags.log);
				}else
					JOptionPane.showMessageDialog(null, ags.log);
				
			}catch(OutOfMemoryError x){
				JOptionPane.showMessageDialog(null, "No alcanza la memoria");
			}
		}
			
		});*
		
		/*
		desk.cbFunciones.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int funcion=desk.cbFunciones.getSelectedIndex();
					if(funcion==0)
						desk.lblFuncion.setIcon(null);
					else if(funcion==1)
						desk.lblFuncion.setIcon(new ImageIcon("imagenes/ecuacion1.png"));
					else if(funcion==2)
						desk.lblFuncion.setIcon(new ImageIcon("imagenes/ecuacion2.png"));
					else if(funcion==3)
						desk.lblFuncion.setIcon(new ImageIcon("imagenes/ecuacion3.png"));
					else
						desk.lblFuncion.setIcon(new ImageIcon("imagenes/ecuacion4.png"));
				}			
			});*/
		}
	
	/* Guardar el archivo */
	 boolean guardar() {
			int returnVal = desk.fc.showSaveDialog(desk);
         if (returnVal == JFileChooser.APPROVE_OPTION) {
             File file = desk.fc.getSelectedFile();
             ags.ruta=file.getPath()+".xls";
             File fichero = new File(ags.ruta);
             if (fichero.exists()){
            	 ags.log="Error: Ese archivo ya existe, cambie el nombre o ruta"; 
            	 return false;
             } 
            else
              return true;
         } 
    	 ags.log="Cancelado por el usuario"; 
         return false;	
    }
	 /*
		//Abrir el archivo 
	 boolean abrir() {
             File fichero = new File(ags.ruta);
             if (!fichero.exists()){
            	 ags.log="Error: Ese archivo no existe"; 
            	 return false;
             } 
            else{
				try {
					Runtime.getRuntime().exec("e.bat "+"\""+ags.ruta+"\"");
				} catch (IOException e) {
	            	 ags.log="Error: No se puede abrir el archivo"; 
	            	 return false;
				}
	              return true;
            }
    }
	
	// Validaciones de las variables independientes 
	boolean validarIndependientes(){
		double x1,x2,y1,y2;

		try{
			x1=Double.parseDouble(desk.txtX1.getText());
			x2=Double.parseDouble(desk.txtX2.getText());
			y1=Double.parseDouble(desk.txtY1.getText());
			y2=Double.parseDouble(desk.txtY2.getText());
			ags.x1s=desk.txtX1.getText();
			ags.x2s=desk.txtX2.getText();
			ags.y1s=desk.txtY1.getText();
			ags.y2s=desk.txtY2.getText();
		}catch(Exception e){
			ags.log="Error: Solo se aceptan n�meros"; 
			return false;
			}
		
		return ags.validarLimites(x1,x2,y1,y2);
	}
	
	// Validaciones de las entradas
	boolean validarEntradas(){
		if(!desk.txtX1.getText().equals("") && !desk.txtX2.getText().equals("") && !desk.txtY1.getText().equals("") && !desk.txtY2.getText().equals("") && desk.cbFunciones.getSelectedIndex()!=0 && desk.cbSeleccion.getSelectedIndex()!=0 && !desk.txtNumBitInd.getText().equals("") && !desk.txtNumGen.getText().equals("") && !desk.txtNumInd.getText().equals("") && !desk.txtPc.getText().equals("") && !desk.txtPm.getText().equals("")){
			    desk.txtX1.setBackground(Color.white);
				desk.txtX2.setBackground(Color.white);
				desk.txtY1.setBackground(Color.white);
				desk.txtY2.setBackground(Color.white);
				desk.cbFunciones.setBackground(null);
				desk.cbSeleccion.setBackground(null);
				desk.txtNumBitInd.setBackground(Color.white);
				desk.txtNumGen.setBackground(Color.white);
				desk.txtNumInd.setBackground(Color.white);
				desk.txtPc.setBackground(Color.white);
				desk.txtPm.setBackground(Color.white);
				return true;
		}
		else{
			if (desk.txtX1.getText().equals("")){
				desk.txtX1.setBackground(Color.red);
				desk.txtX1.requestFocusInWindow();
			}
			if (desk.txtX2.getText().equals("")){
				desk.txtX2.setBackground(Color.red);
				desk.txtX2.requestFocusInWindow();
			}
			if (desk.txtY1.getText().equals("")){
				desk.txtY1.setBackground(Color.red);
				desk.txtY1.requestFocusInWindow();
			}
			if (desk.txtY2.getText().equals("")){
				desk.txtY2.setBackground(Color.red);
				desk.txtY2.requestFocusInWindow();
			}
			if (desk.cbFunciones.getSelectedIndex()==0){
				desk.cbFunciones.setBackground(Color.red);
				desk.cbFunciones.requestFocusInWindow();
			}
			if (desk.cbSeleccion.getSelectedIndex()==0){
				desk.cbSeleccion.setBackground(Color.red);
				desk.cbSeleccion.requestFocusInWindow();
			}
			if (desk.txtNumBitInd.getText().equals("")){
				desk.txtNumBitInd.setBackground(Color.red);
				desk.txtNumBitInd.requestFocusInWindow();
			}
			if (desk.txtNumGen.getText().equals("")){
				desk.txtNumGen.setBackground(Color.red);
				desk.txtNumGen.requestFocusInWindow();
			}
			if (desk.txtNumInd.getText().equals("")){
				desk.txtNumInd.setBackground(Color.red);
				desk.txtNumInd.requestFocusInWindow();
			}
			if (desk.txtPc.getText().equals("")){
				desk.txtPc.setBackground(Color.red);
				desk.txtPc.requestFocusInWindow();
			}
			if (desk.txtPm.getText().equals("")){
				desk.txtPm.setBackground(Color.red);
				desk.txtPm.requestFocusInWindow();
			}
			ags.log="Error: Rellene todos los datos"; 
			return false;
		}
	}
	
	// Validaciones de las probabilidades 
	boolean validarProbabilidades(){
		double pc,pm;
		try{
			pm=Double.parseDouble(desk.txtPm.getText());
			pc=Double.parseDouble(desk.txtPc.getText());
			ags.pms=desk.txtPm.getText();
			ags.pcs=desk.txtPc.getText();
		}catch(Exception e){ags.log="Error: Solo se aceptan n�meros"; return false;}
		
		if(pm>1 || pm<0 || pc>1 || pc<0){
			ags.log="Error: La probilidad de mutaci�n y/o cruza debe ser un n�mero real en [0,1]"; 
			return false;
		}
		ags.pm=pm;
		ags.pc=pc;
		return true;
	}
	
	// Validaciones del n�mero de generaciones, n�mero de individuos y bits por variable independiente
	boolean validarIndividuos(){
		int numI,numG,numB;
		try{
			numI=Integer.parseInt(desk.txtNumInd.getText());
			numG=Integer.parseInt(desk.txtNumGen.getText());
			numB=Integer.parseInt(desk.txtNumBitInd.getText());
		}catch(Exception e){ags.log="Error: Solo se aceptan n�meros enteros"; return false;}
		
		if(numI>0 && numG>0 && numB>0){
			if(numI>=4 && numI<=1000 && numI%4==0){
				if(numG>=1 && numG<=1000){
					if(numB>=1 && numB<=32){
						ags.numB=numB;
						ags.numG=numG;
						ags.numI=numI;
						return true;
					}else {ags.log="Error: El n�mero de bits por variable independiente debe ser un n�mero entero en el intervalo [1,32]"; return false;}
				}else {ags.log="Error: El n�mero de generaciones debe ser un n�mero entero en el intervalo [1,1000]"; return false;}
			}else {ags.log="Error: El n�mero de individuos debe ser m�ltiplo de 4, contenido en el intervalo [4,1000]"; return false;}
		}else {ags.log="Error: Solo se aceptan n�meros enteros positivos mayores a 0"; return false;}
	}
	
	void getFitness(){
		if(desk.cbFunciones.getSelectedIndex()==1){
			ags.fitness="(seno(ra�z cuadrada(x^2+y^2))/ra�z cuadrada(x^2+y^2))";
			ags.formula=1;
		}
		else if(desk.cbFunciones.getSelectedIndex()==2){
			ags.fitness="(seno(x^2+y^2)/(x^2+y^2))";
			ags.formula=2;
		}
		else if(desk.cbFunciones.getSelectedIndex()==3){
			ags.fitness="(0.5-((seno(x^2+y^2))^2-0.5)/(1+0.001(x^2+y2))^2)";
			ags.formula=3;
		}
		else{
			ags.fitness="(4000-100(x^2+y^2)+(1-x^2))";
			ags.formula=4;
		}
	}
	
	void getSeleccion(){
		ags.seleccion=desk.cbSeleccion.getSelectedItem().toString();
	}
	
	void getElitismo(){
		if(desk.rbSi.isSelected())
		   ags.elitismo="S�";
		else
			ags.elitismo="No";
	}
	
	void getTipo(){
		if(desk.rbMax.isSelected()){
			ags.tipo="M�ximo";
			ags.objetivo="M�ximo";
		}
		else{
			ags.tipo="M�nimo";
			ags.objetivo="M�nimo";
		}
	}
	*/
}
