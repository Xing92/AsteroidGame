package game;

import data.Direction;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Start extends Application {

	private Direction direction = Direction.UP;

	public static void main(String[] args) {
		Game2 game = new Game2();
		game.startMe(args);
//		Game.launch(args);
	}

	public void start(Stage theStage) {
		theStage.setTitle("Canvas Example 2");

		Group root = new Group();

		theStage.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			public void handle(final MouseEvent keyEvent) {
				System.out.println(keyEvent.getSceneX());
			}
		});

		theStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
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

		Scene theScene = new Scene(root);
		theStage.setScene(theScene);

		Canvas canvas = new Canvas(800, 800);
		root.getChildren().add(canvas);

		GraphicsContext gc = canvas.getGraphicsContext2D();

		Image dupa = new Image("dupa.bmp");
		final long startNanoTime = System.nanoTime();
		new AnimationTimer() {
			long previousNanoTime = startNanoTime;
			double x = 232;
			double y = 232;
			double speed = 0.0000001;

			public void handle(long currentNanoTime) {
				double t = (currentNanoTime - startNanoTime) / 1000000000.0;
				double deltaT = currentNanoTime - previousNanoTime;
				previousNanoTime = currentNanoTime;
				double deltaX = 0;
				double deltaY = 0;
				if (direction == Direction.RIGHT) {
					deltaX = deltaT * speed * 1;
					deltaY = 0;
				} else if (direction == Direction.LEFT) {
					deltaX = deltaT * speed * (-1);
					deltaY = 0;
				} else if (direction == Direction.UP) {
					deltaY = deltaT * speed * (-1);
					deltaX = 0;
				} else if (direction == Direction.DOWN) {
					deltaY = deltaT * speed * (1);
					deltaX = 0;
				}

				x = x + deltaX;
				y = y + deltaY;
				// System.out.println(x + "," + y);

				// background image clears canva
				gc.clearRect(0, 0, 800, 800);
				gc.drawImage(dupa, x, y);
				// System.out.println(direction);
			}
		}.start();

		theStage.show();
	}

}