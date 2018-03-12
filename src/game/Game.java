package game;

import data.Direction;
import data.Spaceship;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {

	private Direction direction;
	private Spaceship player;

	public void startMe(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Canvas canvas = configureScene(primaryStage);
		configureListeners(primaryStage);

		GraphicsContext gc = canvas.getGraphicsContext2D();

		player = new Spaceship(new Image(""));
		player.setImage(new Image("Spaceship.bmp"));
		player.setImageView(new ImageView(player.getImage()));

//		final long startNanoTime = System.nanoTime();
		new AnimationTimer() {
			long previousNanoTime = System.nanoTime();
			float speed = 0.00000001f;

			public void handle(long currentNanoTime) {
				float deltaT = currentNanoTime - previousNanoTime;
				previousNanoTime = currentNanoTime;
				float deltaX = 0;
				float deltaY = 0;
				if (direction == Direction.RIGHT) {
					deltaX = deltaT * speed * 1;
					deltaY = 0;
					player.accelerate(deltaX, deltaY);
				} else if (direction == Direction.LEFT) {
					deltaX = deltaT * speed * (-1);
					deltaY = 0;
					player.accelerate(deltaX, deltaY);
				} else if (direction == Direction.UP) {
					deltaY = deltaT * speed * (-1);
					deltaX = 0;
					player.accelerate(deltaX, deltaY);
				} else if (direction == Direction.DOWN) {
					deltaY = deltaT * speed * (1);
					deltaX = 0;
					player.accelerate(deltaX, deltaY);
				} else if (direction == Direction.NOTSET) {
					deltaY = 0;
					deltaX = 0;
					player.setAngle(player.getAngle() + 1);
				}

//				player.move(deltaT, speed);

				gc.clearRect(0, 0, 800, 800);
//				gc.save();
//				gc.rotate(player.getAngle()); // TODO: remove this
//				gc.drawImage(player.getImage(), player.getPosX(), player.getPosY());
//				gc.restore();
				
				
				
				gc.drawImage(player.getImage(), player.getPosX(), player.getPosY());
			}
		}.start();

	}

	private Canvas configureScene(Stage primaryStage) {
		primaryStage.setTitle("Asteroids - PC");

		Group root = new Group();
		Scene theScene = new Scene(root,Color.YELLOW);
		primaryStage.setScene(theScene);

		Canvas canvas = new Canvas(800, 800);
		root.getChildren().add(canvas);

		primaryStage.setScene(theScene);

		primaryStage.show();

		return canvas;
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
					direction = Direction.UP;
				} else if (keyEvent.getCode() == KeyCode.DOWN) {
					direction = Direction.DOWN;
				} else if (keyEvent.getCode() == KeyCode.LEFT) {
					direction = Direction.LEFT;
				} else if (keyEvent.getCode() == KeyCode.RIGHT) {
					direction = Direction.RIGHT;
				}
			}
		});

		primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
			public void handle(final KeyEvent keyEvent) {
				direction = Direction.NOTSET;
			}
		});
	}

}
