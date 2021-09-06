package marks.whoneeds.gui.sprites;

public class Bound {
	
	private double x, y, w, h, d;
	private boolean circular;
	
	public Bound(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.w = width;
		this.h = height;
		this.d = Math.sqrt(width * width + height * height);
		this.circular = false;
	}
	
	public Bound(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.d = this.w = this.h = radius * 2;
		this.h = 0;
		this.circular = true;
	}
	
	public boolean overlaps(double testX, double testY) {
		if (circular)
			return Math.sqrt(Math.pow(testX - x, 2) + Math.pow(testY - y, 2)) <= d / 2;
		return testX >= x && testX <= x + w && testY >= y && testY <= y + h;
	}
	
	public double getDiameter() {
		return d;
	}
	
	public double getCenterX() {
		if (circular)
			return x;
		return x + w / 2;
	}
	
	public double getCenterY() {
		if (circular)
			return y;
		return y + h / 2;
	}

}
