package data;

import game.Game2;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Creature {

	private double posX = 400;
	private double posY = 400;
	private double velocityX;
	private double velocityY;
	private double angle;

	private Image image;
	private ImageView imageView;

	private CollisionBox collision;

	public Creature(Image image) {
		this.image = image;
		this.collision = new CollisionBox(posX, posY, image.getWidth(), image.getHeight());
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public double getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public void changeAngle(double angleChange) {
		angle += angleChange;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void move(double deltaTime) {
		posX += velocityX * deltaTime;
		posY += velocityY * deltaTime;
		if (posX > Game2.WINDOW_SIZE_X) {
			posX -= Game2.WINDOW_SIZE_X;
		} else if (posX < 0) {
			posX += Game2.WINDOW_SIZE_X;
		}
		if (posY > Game2.WINDOW_SIZE_Y) {
			posY -= Game2.WINDOW_SIZE_Y;
		} else if (posY < 0) {
			posY += Game2.WINDOW_SIZE_Y;
		}

		collision.setPosXY(posX, posY);
		imageView.setX(posX - image.getWidth()/2);
		imageView.setY(posY - image.getHeight()/2);
	}

	public void accelerate(double accelerateX, double accelerateY) {
		velocityX += accelerateX;
		velocityY += accelerateY;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public boolean isCollided(Creature other){
		return collision.isCollided(other.collision);
	}
}
