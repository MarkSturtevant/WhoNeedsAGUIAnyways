package marks.whoneeds.gui.resources;

import javafx.scene.image.Image;

public enum Images {

	
	GAME(new Image(Images.class.getClassLoader().getResourceAsStream("resource/game.png"))),
	END(new Image(Images.class.getClassLoader().getResourceAsStream("resource/end.png"))),
	HOWTOPLAY(new Image(Images.class.getClassLoader().getResourceAsStream("resource/howtoplay.png"))),
	TITLE(new Image(Images.class.getClassLoader().getResourceAsStream("resource/title.png")));
	
	private Image img;
	
	private Images(Image i) {
		img = i;
	}
	
	public Image get() {
		return img;
	}
	
	
	
}
