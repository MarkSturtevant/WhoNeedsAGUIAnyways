package marks.whoneeds.gui.sprites;

public abstract class Sprite {

	private int depth;
	
	protected double x, y, w, h;
	
	public Sprite(int depth) {
		this.depth = depth;
	}
	
	public int getDepth() {
		return this.depth;
	}
	
	public abstract boolean overlaps(double x, double y);
	public abstract void render(int frameSpeed);
	public abstract void translate(double x, double y);
	
	
}
