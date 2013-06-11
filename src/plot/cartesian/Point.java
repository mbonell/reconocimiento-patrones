package plot.cartesian;

public class Point {

	private float x = 0;
	private float y = 0;
	
	public Point(float lx, float ly){
		this.x = lx;
		this.y = ly;
	}
	
	public float getX(){
		return this.x;
	}
	
	public float getY(){
		return this.y;
	}
}
