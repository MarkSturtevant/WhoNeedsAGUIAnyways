package marks.whoneeds.gui.setups;

import java.util.Random;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import marks.whoneeds.game.Game;
import marks.whoneeds.gui.GUIHandler;
import marks.whoneeds.gui.resources.Images;
import marks.whoneeds.gui.resources.Music;
import marks.whoneeds.gui.sprites.SpriteBackground;
import marks.whoneeds.gui.sprites.SpriteRect;
import marks.whoneeds.gui.sprites.SpriteText;

public class SetupGame extends Setup {
	
	private int roundID;
	private SpriteText text;
	private Game gi;
	private Music activeMusic;
	
	public SetupGame() {
		this.addSprite(new SpriteBackground(Images.GAME.get()));
		this.activeMusic = Music.values()[new Random().nextInt(3)];
		this.activeMusic.play();
		roundID = 0;
		this.addSprite(text = new SpriteText(1, "Round: " + roundID + " / 10", 600, 23, 36, Color.BLACK));
		gi = new Game(this);
		this.nextRound();
	}

	@Override
	public void onClick(double mouseX, double mouseY) {
		gi.clickSelection(mouseX, mouseY);
	}

	@Override
	public void update(double mouseX, double mouseY, boolean mousePressed, int frameSpeed) {
		gi.update();
		for (SpriteRect rect : gi.getToDraw())
			rect.render(frameSpeed);
	}

	@Override
	public void onKeyPress(KeyCode key) {
		if (! gi.accepting())
			return;
		switch(key) {
			case W: gi.moveSelection(0); break;
			case A: gi.moveSelection(1); break;
			case S: gi.moveSelection(2); break;
			case D: gi.moveSelection(3); break;
			case UP: gi.launch(0); break;
			case RIGHT: gi.launch(1); break;
			case LEFT: gi.launch(3); break;
			case DOWN: gi.launch(2); break;
			default: break;
		}
	}
	
	public void call(int info) { // 0: fail; 1: next round;
		if (info == 0)
			{GUIHandler.setSetup(new SetupEndScreen(roundID - 1)); this.activeMusic.stop();}
		else if (info == 1)
			nextRound();
	}
	
	public void nextRound() {
		if (++roundID == 11)
			{GUIHandler.setSetup(new SetupEndScreen(10)); this.activeMusic.stop();}
		else gi.setupRound(roundID);
		text.setText("Round: " + roundID + " / 10");
	}

}
