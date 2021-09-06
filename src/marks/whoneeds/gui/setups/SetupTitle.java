package marks.whoneeds.gui.setups;

import javafx.scene.input.KeyCode;
import marks.whoneeds.gui.GUIHandler;
import marks.whoneeds.gui.resources.Images;
import marks.whoneeds.gui.sprites.Bound;
import marks.whoneeds.gui.sprites.SpriteBackground;

public class SetupTitle extends Setup {
	
	private Bound[] bounds;
	
	public SetupTitle() {
		super();
		this.addSprite(new SpriteBackground(Images.TITLE.get()));
		bounds = new Bound[] {
				new Bound(551, 542, 284, 160),
				new Bound(115, 596, 134, 70)
		};
	}

	@Override
	public void onClick(double mouseX, double mouseY) {
		if (bounds[0].overlaps(mouseX, mouseY))
			GUIHandler.setSetup(new SetupHowToPlay());
		else if (bounds[1].overlaps(mouseX, mouseY))
			GUIHandler.setSetup(new SetupGame());
	}

	@Override
	public void update(double mouseX, double mouseY, boolean mousePressed, int frameSpeed) {
	}

	@Override
	public void onKeyPress(KeyCode key) {
	}

}
