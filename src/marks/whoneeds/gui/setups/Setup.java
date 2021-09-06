package marks.whoneeds.gui.setups;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.KeyCode;
import marks.whoneeds.gui.CanvasUtils;
import marks.whoneeds.gui.sprites.Sprite;

public abstract class Setup {

	protected List<Sprite> sprites;
	
	public Setup() {
		sprites = new ArrayList<>();
	}
	
	public abstract void onClick(double mouseX, double mouseY);
	public abstract void update(double mouseX, double mouseY, boolean mousePressed, int frameSpeed);
	public abstract void onKeyPress(KeyCode key);
	
	private void renderSprites(int frameSpeed) {
		for (Sprite s : sprites)
			s.render(frameSpeed);
	}
	
	public void onUpdate(double mouseX, double mouseY, boolean mousePressed, int frameSpeed) {
		CanvasUtils.clear();
		this.renderSprites(frameSpeed);
		this.update(mouseX, mouseY, mousePressed, frameSpeed);
	}
	
	public void addSprite(Sprite s) {
		int size = sprites.size(), depth = s.getDepth();
		for (int i = 0; i < size; i++)
			if (depth > sprites.get(i).getDepth()) {
				sprites.add(i, s);
				return;
			}
		sprites.add(s);
	}
	
	public void removeSprite(Sprite s) {
		if (sprites.contains(s))
			sprites.remove(s);
	}
}
