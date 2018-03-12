package game;

import java.util.ArrayList;
import java.util.List;

import data.Bullet;
import data.Creature;
import data.Direction;
import data.Spaceship;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game2 extends Application {

	private Spaceship player;
	private List<Creature> creatures = new ArrayList<>();
	private Image bulletImage;

	public static int WINDOW_SIZE_X = 800;
	public static int WINDOW_SIZE_Y = 800;

	public void startMe(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = configureScene(primaryStage);
		configureListeners(primaryStage);

		bulletImage = new Image("Bullet.bmp");
		player = new Spaceship(new Image("Spaceship.bmp"));
		player.setImageView(new ImageView(player.getImage()));
		creatures.add(player);

		root.getChildren().add(player.getImageView());

		final long startNanoTime = System.nanoTime();
		new AnimationTimer() {
			long previousNanoTime = startNanoTime;
			double speed = 0.00000001;
			double bulletSpeed = 0.000001;
			double rotationSpeed = 4;

			public void handle(long currentNanoTime) {
				double deltaT = currentNanoTime - previousNanoTime;
				previousNanoTime = currentNanoTime;
//				System.out.println("FPS:" + 1 / (deltaT / 1000000000));
				double deltaX = 0;
				double deltaY = 0;
				if (player.getDirection() == Direction.RIGHT) {
					player.changeAngle(rotationSpeed);
				} else if (player.getDirection() == Direction.LEFT) {
					player.changeAngle(-rotationSpeed);
				}
				if (player.isAccelerating()) {
					deltaY = Math.cos(player.getAngle() * 3.141592 / 180) * (speed * (-1));
					deltaX = Math.sin(player.getAngle() * 3.141592 / 180) * (speed * (1));
					player.accelerate(deltaX, deltaY);
				}
				if (player.isShooting()) {
					Creature bullet = new Bullet(bulletImage);
					bullet.setImageView(new ImageView(bullet.getImage()));
					double bulletDeltaY = Math.cos(player.getAngle() * 3.141592 / 180) * (bulletSpeed * (-1));
					double bulletDeltaX = Math.sin(player.getAngle() * 3.141592 / 180) * (bulletSpeed * (1));
					bullet.accelerate(bulletDeltaX, bulletDeltaY);
					bullet.setPosX(player.getPosX() + player.getImage().getWidth() / 2);
					bullet.setPosY(player.getPosY() + player.getImage().getHeight() / 2);
					root.getChildren().add(bullet.getImageView());
					creatures.add(bullet);
				}
				for (Creature creature : creatures) {
					creature.move(deltaT);
					creature.getImageView().setRotate(creature.getAngle());
					for (Creature other : creatures) {
						if (creature == other) {
							continue;
						}
						if (creature instanceof Spaceship && creature.isCollided(other)) {
							System.out.println("collided:" + creature.getClass());
						}
					}
					if (creature instanceof Bullet) {
						if (((Bullet) creature).checkDeath()) {
							System.out.println(root.getChildren().remove(creature.getImageView()));
//							creatures.remove(creature);
						}
					}
				}
			}
		}.start();

	}

	private Group configureScene(Stage primaryStage) {
		primaryStage.setTitle("Asteroids - PC");

		Group root = new Group();
		Scene theScene = new Scene(root, WINDOW_SIZE_X, WINDOW_SIZE_Y, Color.YELLOW);
		primaryStage.setScene(theScene);
		primaryStage.show();
		return root;
	}

	private void configureListeners(Stage primaryStage) {
		primaryStage.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			public void handle(final MouseEvent keyEvent) {
				System.out.println(keyEvent.getSceneX());
			}
		});

		primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			public void handle(final KeyEvent keyEvent) {
				System.out.println(keyEvent.getCode());
				if (keyEvent.getCode() == KeyCode.UP) {
					player.setAccelerating(true);
				}
				if (keyEvent.getCode() == KeyCode.LEFT) {
					player.setDirection(Direction.LEFT);
				}
				if (keyEvent.getCode() == KeyCode.RIGHT) {
					player.setDirection(Direction.RIGHT);
				}
				if (keyEvent.getCode() == KeyCode.SPACE) {
					player.setShooting(true);
				}
			}
		});

		primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
			public void handle(final KeyEvent keyEvent) {
				if (keyEvent.getCode() == KeyCode.SPACE) {
					player.setShooting(false);
				} else if (keyEvent.getCode() == KeyCode.UP) {
					player.setAccelerating(false);
				} else {
					player.setDirection(Direction.NOTSET);
				}
			}
		});
	}

}
