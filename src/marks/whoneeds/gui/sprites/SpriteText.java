package marks.whoneeds.gui.sprites;

import javafx.scene.paint.Color;
import marks.whoneeds.gui.CanvasUtils;

public class SpriteText extends Sprite {
	
	private String text;
	private Color color;
	private double cenX, cenY, h;

	public SpriteText(int depth, String text, double cenX, double cenY, double h, Color color) {
		super(depth);
		this.cenX = cenX;
		this.cenY = cenY;
		this.color = color;
		this.text = text;
		this.h = h;
	}
	
	public void setText(String newText) {
		this.text = newText;
	}

	@Override
	public boolean overlaps(double x, double y) {
		return false;
	}

	@Override
	public void render(int frameSpeed) {
		CanvasUtils.drawTextCentered(cenX, cenY, h, text, color);
	}

	@Override
	public void translate(double x, double y) {
		this.cenX += x;
		this.cenY += y;
	}

}
