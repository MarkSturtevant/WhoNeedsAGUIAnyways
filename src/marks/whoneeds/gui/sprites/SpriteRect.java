package marks.whoneeds.gui.sprites;

import javafx.scene.paint.Color;
import marks.whoneeds.gui.CanvasUtils;

public class SpriteRect extends Sprite {

	private double x1, x2, y1, y2;
	private Color color;
	
	public SpriteRect(int depth, double x1, double y1, double x2, double y2, Color c) {
		super(depth);
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.color = c;
	}
	
	public SpriteRect(int depth, Color c, double x, double y, double w, double h) {
		super(depth);
		this.x1 = x;
		this.x2 = x + w;
		this.y1 = y;
		this.y2 = y + h;
		this.color = c;
	}

	@Override
	public boolean overlaps(double x, double y) {
		return x >= x1 && x <= x2 && y >= y1 && y <= y2;
	}

	@Override
	public void render(int frameSpeed) {
		CanvasUtils.drawRect1(x1, y1, x2, y2, color);
	}
	
	public double x1() {
		return x1;
	}
	
	public double x2() {
		return x2;
	}
	
	public double y1() {
		return y1;
	}
	
	public double y2() {
		return y2;
	}
	
	public double r() {
		return this.w / 2;
	}
	
	public double cenX() {
		return (x1 + x2) / 2;
	}
	
	public double cenY() {
		return (y1 + y2) / 2;
	}
	
	public double mass() {
		return Math.pow(this.w(), 2);
	}
	
	public double w() {
		return x2 - x1;
	}

	@Override
	public void translate(double x, double y) {
		x1 += x;
		x2 += x;
		y1 += y;
		y2 += y;
	}
	
	public void relocate(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public boolean collides(SpriteRect r) {
		return x1 < r.x1 + r.w() &&
				x1 + w() > r.x1 &&
				y1 < r.y1 + r.w() &&
				y1 + w() > r.y1;
	}
	
	public boolean isOffScreen() {
		return x1 < 0 || x2 > 1200 || y1 < 0 || y2 > 800;
	}
	
	public void changeColor(Color newColor) {
		color = newColor;
	}
	
	public static int sortHor(SpriteRect r1, SpriteRect r2) {
		if (r1.x1 != r2.x1)
			return (int) (1000 * (r2.y1 - r1.y1));
		return (int) (1000 * (r2.x1 - r1.x1));
	}
	
	public static int sortVer(SpriteRect r1, SpriteRect r2) {
		if (r1.y1 != r2.y1)
			return (int) (1000 * (r2.x1 - r1.x1));
		return (int) (1000 * (r2.y1 - r1.y1));
	}

}
