package marks.whoneeds.gui.sprites;

import javafx.scene.image.Image;
import marks.whoneeds.gui.CanvasUtils;

public class SpriteImage extends Sprite {
	
	protected Image img;
	protected double centerX;
	protected double centerY;
	protected double width;
	protected double height;

	public SpriteImage(int depth, Image img, double centerX, double centerY) {
		super(depth);
		this.img = img;
		this.centerX = centerX;
		this.centerY = centerY;
		this.width = img.getWidth();
		this.height = img.getHeight();
	}
	
	public SpriteImage(int depth, Image img, double centerX, double centerY, double width, double height) {
		super(depth);
		this.img = img;
		this.centerX = centerX;
		this.centerY = centerY;
		this.width = width;
		this.height = height;
	}
	
	public SpriteImage(Image img, double x1, double y1, double x2, double y2, int depth) {
		super(depth);
		this.img = img;
		this.centerX = (x1 + x2) / 2;
		this.centerY = (y1 + y2) / 2;
		this.width = x2 - x1;
		this.height = y2 - y1;
	}
	
	public void setImage(Image newImage) {
		this.img = newImage;
	}
	
	public void setImageAndBounds(Image newImage) {
		this.img = newImage;
		this.width = newImage.getWidth();
		this.height = newImage.getHeight();
	}

	@Override
	public void render(int frameSpeed) {
		if (img != null)
			CanvasUtils.drawImage3(img, centerX, centerY, width, height);
	}

	@Override
	public boolean overlaps(double x, double y) {
		return x >= centerX - width / 2 && x <= centerX + width / 2 && y >= centerY - height / 2 && y <= centerY + height / 2;
	}

	@Override
	public void translate(double x, double y) {
		centerX += x;
		centerY += y;
	}

}
