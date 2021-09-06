package marks.whoneeds.gui;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import marks.whoneeds.gui.resources.Music;
import marks.whoneeds.gui.setups.Setup;
import marks.whoneeds.gui.setups.SetupTitle;

public class GUIHandler {

	public static double stageW; // stage width
	public static double stageH; // stage height
	
	public static double mouseX; // decimal mouse X
	public static double mouseY; // decimal mouse Y
	
	private static boolean mousePressed;
	
	public static GraphicsContext gc; // Used for painting onto the canvas
	
	private static Setup currentSetup;
	private static AnimationTimer loop;
	
	private static List<AudioClip> activeMusic, activeSE;
	private static boolean musicOn, soundEffectsOn;
	
	public static void init(double width, double height, double awidth, double aheight) {
		mouseX = 1;
		mouseY = 1;
		mousePressed = false;
		stageW = awidth;
		stageH = aheight;
		activeMusic = new ArrayList<>();
		activeSE = new ArrayList<>();
		musicOn = soundEffectsOn = true;
		CanvasUtils.setReferences(width, height);
	}
	
	protected static void initCanvasAndLoop(Stage stage) {
		Pane root = new Pane();
		// init scene
		Scene sc = new Scene(root, stageW, stageH);
		sc.setOnMousePressed(e -> {
			mousePressed = true;
		});
		sc.setOnMouseReleased(e -> {
			mousePressed = false;
			currentSetup.onClick(mouseX, mouseY);
		});
		sc.setOnMouseMoved(e -> {
			mouseX = e.getSceneX() * CanvasUtils.getReferenceWidth() / stageW;
			mouseY = e.getSceneY() * CanvasUtils.getReferenceHeight() / stageH;
		});
		sc.setOnMouseDragged(e -> {
			mouseX = e.getSceneX() * CanvasUtils.getReferenceWidth() / stageW;
			mouseY = e.getSceneY() * CanvasUtils.getReferenceHeight() / stageH;
		});
		sc.setOnKeyReleased(e -> {
			currentSetup.onKeyPress(e.getCode());
		});
		
		// if an unknown exception were to occur, this block catches it.
		//Thread.setDefaultUncaughtExceptionHandler((t, th) -> {
		//	System.out.println("Error Present: " + th.getCause());
		//});
		
		// init canvas
		ResizableCanvas canvas = new ResizableCanvas(stageW, stageH);
		gc = canvas.getGraphicsContext2D();
		sc.widthProperty().addListener((obs, oldVal, newVal) -> {
			stageW = sc.getWidth();
			canvas.setWidth(stage.getWidth());
		});
		sc.heightProperty().addListener((obs, oldVal, newVal) -> {
			stageH = sc.getHeight();
			canvas.setHeight(stage.getHeight());
		});
		// >>>>>>>>>> UNUSED CODE.
		//canvas.widthProperty().bind(sc.widthProperty());
		//canvas.heightProperty().bind(sc.heightProperty());
		// <<<<<<<<<< UNUSED CODE.
		root.getChildren().add(canvas);
		
		// setting canvasScene to open when game starts; then shows window
		currentSetup = new SetupTitle();
		stage.setScene(sc);
		stage.show();
		Music.INTRO.play();
		// loop launch
		initTimerLoop();
	}
	
	public static void setSetup(Setup csc) {
		currentSetup = csc;
	}
	
	// ends loop
	public static void stop() {
		loop.stop();
	}
	
	public static void playAudio(AudioClip ac, boolean isMusic) {
		if (isMusic) {
			if (musicOn && (! ac.isPlaying())) {
				ac.play();
				if (! activeMusic.contains(ac))
					activeMusic.add(ac);
			}
		}
		else {
			if (soundEffectsOn) {
				ac.play();
				if (! activeSE.contains(ac))
					activeSE.add(ac);
			}
		}
	}
	
	public static void stopAudio(AudioClip ac, boolean isMusic) {
		if (ac.isPlaying())
			ac.stop();
		if (isMusic) {
			if (activeMusic.contains(ac))
				activeMusic.remove(ac);
		}
		else {
			if (activeSE.contains(ac))
				activeSE.remove(ac);
		}
		
	}
	
	public static void toggleAudio(boolean isMusic) {
		if (isMusic) {
			if (musicOn)
				while (activeMusic.size() > 0)
					stopAudio(activeMusic.get(0), true);
			musicOn = !musicOn;
		} else {
			if (soundEffectsOn)
				while (activeSE.size() > 0)
					stopAudio(activeSE.get(0), false);
			soundEffectsOn = !soundEffectsOn;
		}
	}
	
	public static boolean isMusicOn() {
		return musicOn;
	}
	
	public static boolean isSEOn() {
		return soundEffectsOn;
	}
	
	private static void initTimerLoop() {
		loop = new AnimationTimer() {
			long timein = System.currentTimeMillis();
			int frameSpeed = 1; // frameSpeed is in milliseconds!
			@Override
			public void handle(long now) {
				frameSpeed = (int) (System.currentTimeMillis() - timein);
				timein = System.currentTimeMillis();
				currentSetup.onUpdate(mouseX, mouseY, mousePressed, frameSpeed);
			}
		};
		loop.start();
	}
	
	// Same as normal canvas but allows for resizeability
	private static class ResizableCanvas extends Canvas {
		
		private ResizableCanvas(double width, double height) {
			super(width, height);
		}
		
        @Override
        public boolean isResizable() {
            return true;
        }

        @Override
        public double prefWidth(double height) {
            return getWidth();
        }

        @Override
        public double prefHeight(double width) {
            return getHeight();
        }
	}
	
}
