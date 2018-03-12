package data;

import javafx.scene.image.Image;

public class Spaceship extends Creature {

	public Spaceship(Image image) {
		super(image);
	}

	private Direction direction = Direction.NOTSET;
	private boolean isShooting = false;
	private boolean isAccelerating = false;

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}

	public boolean isAccelerating() {
		return isAccelerating;
	}

	public void setAccelerating(boolean isAccelerating) {
		this.isAccelerating = isAccelerating;
	}

}
