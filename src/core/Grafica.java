package core;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class Grafica {

 
	private static final long serialVersionUID = 1L;

    public Grafica(int generaciones, Vector<Double>grafica,String tipo) {


        final XYDataset dataset = createDataset(generaciones,grafica,tipo);
        final JFreeChart chart = createChart(dataset);
        
        try {
            ChartUtilities.saveChartAsPNG(new File("imagen.png"), chart, 600,350);
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null, "Error al crear la imagen");
        }
        

    }
    

    private XYDataset createDataset(int gen,Vector<Double>grafica,String tipo) {
        
        final XYSeries series1 = new XYSeries("Promedio (fitness)");
        final XYSeries series2 = new XYSeries(tipo+" (fitness)");
        
        int x=1;
        
        for(int i=1; i<=gen; i++){
        	series1.add(i, grafica.elementAt(x));
        	x++;
        	series2.add(i, grafica.elementAt(x));
        	x+=2;
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
                
        return dataset;
        
    }
    

    private JFreeChart createChart(final XYDataset dataset) {
        
        final JFreeChart chart = ChartFactory.createXYLineChart(
            null,      					
            "Generaciones",                     
            null,                      
            dataset,                  
            PlotOrientation.VERTICAL,
            true,                     
            true,                     
            false                     
        );

        chart.setBackgroundPaint(Color.white);
        
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(1, true);
        plot.setRenderer(renderer);

        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
                
        return chart;
        
    }


}

   