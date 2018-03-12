package data;

import javafx.scene.image.Image;

public class Bullet extends Creature {

	private static double LIFE_SPAN_SECONDS = 3;
	private static final int NANO_SECONDS = 1000000000;
	private final double startTime;

	public Bullet(Image image) {
		super(image);
		startTime = System.nanoTime();
	}

	public boolean checkDeath() {
		double currentTime = System.nanoTime();
		double deltaTime = currentTime - startTime;
		if (deltaTime / NANO_SECONDS >= LIFE_SPAN_SECONDS) {
			return true;
		}
		return false;
	}

}
