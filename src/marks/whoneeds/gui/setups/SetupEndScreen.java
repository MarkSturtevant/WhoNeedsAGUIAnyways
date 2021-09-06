package marks.whoneeds.gui.setups;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import marks.whoneeds.gui.GUIHandler;
import marks.whoneeds.gui.resources.Images;
import marks.whoneeds.gui.resources.Music;
import marks.whoneeds.gui.sprites.SpriteBackground;
import marks.whoneeds.gui.sprites.SpriteText;

public class SetupEndScreen extends Setup {
	
	public SetupEndScreen(int completed) {
		this.addSprite(new SpriteBackground(Images.END.get()));
		this.addSprite(new SpriteText(1, "" + completed, 830, 660, 225, Color.BROWN));
		Music.END.play();
	}

	@Override
	public void onClick(double mouseX, double mouseY) {
		GUIHandler.setSetup(new SetupTitle());
	}

	@Override
	public void update(double mouseX, double mouseY, boolean mousePressed, int frameSpeed) {}

	@Override
	public void onKeyPress(KeyCode key) {}

}
