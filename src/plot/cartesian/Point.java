package plot.cartesian;

public class Point {

	private float x = 0;
	private double y = 0;
	
	public Point(float lx, double ly){
		this.x = lx;
		this.y = ly;
	}
	
	public float getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
}
