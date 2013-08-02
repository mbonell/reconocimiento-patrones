package plot;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import org.math.plot.Plot2DPanel;

import plot.cartesian.Point;

public class Cartesian {

	private HashMap<Color, ArrayList<Point> > pointCollection = new HashMap<Color, ArrayList<Point> >(); 
	
	//private ArrayList<Point> points = new ArrayList<Point>();
	
	private float minX = 0;
	private float maxX = 0;
	private double minY = 0;
	private double maxY = 0;
	
	private Plot2DPanel plot = new Plot2DPanel();
	
	
	public void addPoint(Color c, float x, double y){
		this.addPoint(c, new Point(x, y) );
	}
	
	public void addPoint(Color c, Point point ){
		
		ArrayList<Point> points;
		
		if ( ! pointCollection.containsKey(c) ){
			pointCollection.put(c, new ArrayList<Point>() );
		}
		
		points = pointCollection.get(c);
		
		points.add(point);
		
		this.setBoundLimits( point );		
	}
	
	public Plot2DPanel plot(){

	    
		double[] axisx = {minX, 0, maxX};
		double[] axisy = {minY, 0, maxY};
		double[] ceroAxis = {  0, 0 , 0};
		
		plot.addLinePlot("a", Color.red, axisx, ceroAxis);
		plot.addLinePlot("b", Color.red, ceroAxis, axisy);
		
		ArrayList<Point> points;
		
		for( Color c : pointCollection.keySet() ){
			
			points = pointCollection.get(c);

			double[] xAxisValues = new double[points.size()];
			double[] yAxisValues = new double[points.size()];

			if( points.size() == 2 ){
				xAxisValues[0]   = points.get(0).getX();
				xAxisValues[1]   = points.get(0).getY();
				
				yAxisValues[0]   = points.get(1).getX();
				yAxisValues[1]   = points.get(1).getY();
			}else{
				for( int i = 0; i < points.size(); i++ ){
					xAxisValues[i]   = points.get(i).getX();
					yAxisValues[i]   = points.get(i).getY();
				}
			}
        
			plot.addScatterPlot("", c ,xAxisValues, yAxisValues);
		
		}
			
		plot.removePlotToolBar();
		
		
		plot.setFixedBounds(0, minX, maxX);
		plot.setFixedBounds(1, minY, maxY);
		
		return plot;
	}
	
	private void setBoundLimits( Point point ){
		if( point.getX() < minX  ){
			minX = point.getX();
		}
		if( point.getX() > maxX  ){
			maxX = point.getX();
		}
		if( point.getY() < minY  ){
			minY = point.getY();
		}
		if( point.getY() > maxY  ){
			maxY = point.getY();
		}
	}
}
