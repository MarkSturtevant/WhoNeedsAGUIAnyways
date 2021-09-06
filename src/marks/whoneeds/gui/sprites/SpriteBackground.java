package marks.whoneeds.gui.sprites;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import marks.whoneeds.gui.CanvasUtils;

public class SpriteBackground extends Sprite {

	private Sprite obj;
	
	public SpriteBackground(Image img) {
		super(100000);
		obj = new SpriteImage(img, 0, 0, CanvasUtils.getReferenceWidth(), CanvasUtils.getReferenceHeight(), 1000000);
	}
	
	public SpriteBackground(Color color) {
		super(100000);
		obj = new SpriteRect(1000000, 0, 0, CanvasUtils.getReferenceWidth(), CanvasUtils.getReferenceHeight(), color);
	}

	@Override
	public boolean overlaps(double x, double y) {
		return true;
	}

	@Override
	public void render(int frameSpeed) {
		obj.render(frameSpeed);
	}

	@Override
	public void translate(double x, double y) {
		System.out.println("Warning: Attempting to move a Background Sprite.  Operation cancelled");
	}

}
