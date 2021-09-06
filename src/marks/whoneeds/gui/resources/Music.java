package marks.whoneeds.gui.resources;

import javafx.scene.media.AudioClip;
import marks.whoneeds.gui.GUIHandler;

public enum Music {

	MUSIC1("/resource/music1.wav", true),
	MUSIC2("/resource/music2.wav", true),
	MUSIC3("/resource/music3.wav", true),
	INTRO("/resource/sting.wav", false),
	END("/resource/sting2.wav", false);
	
	private AudioClip song;
	
	Music(String url, boolean loops) {
		song = new AudioClip(Music.class.getResource(url).toExternalForm());
		if (loops)
			song.setCycleCount(AudioClip.INDEFINITE);
	}
	
	public void play() {
		GUIHandler.playAudio(song, true);
	}
	
	public void stop() {
		GUIHandler.stopAudio(song, true);
	}
	
	/** Set volume
	 * 
	 * @param newVolume 0.0: mute, 1.0: full blast
	 */
	public void setVolume(double newVolume) {
		song.setVolume(newVolume);
		this.stop();
		this.play();
	}
	
}
