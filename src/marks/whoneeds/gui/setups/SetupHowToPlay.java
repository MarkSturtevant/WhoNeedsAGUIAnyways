package marks.whoneeds.gui.setups;

import javafx.scene.input.KeyCode;
import marks.whoneeds.gui.GUIHandler;
import marks.whoneeds.gui.resources.Images;
import marks.whoneeds.gui.sprites.SpriteBackground;

public class SetupHowToPlay extends Setup {
	
	public SetupHowToPlay() {
		super();
		this.addSprite(new SpriteBackground(Images.HOWTOPLAY.get()));
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
