package snakeGame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Tester extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		Pane pane = new Pane();
		
		Text text = new Text("Level 41");
		text.setX(120);
		text.setY(200);
		text.setFont(new Font(50));
		
		pane.getChildren().add(text);
		
		EventHandler<ActionEvent> moveEvent = e -> {
			if (text.getFont().getSize() > 20)
				text.setFont(new Font(text.getFont().getSize() - 0.15));
			if (text.getX() < 320)
				text.setX(text.getX() + 1);
			if (text.getY() > 15)
				text.setY(text.getY() - 1);
		};
		
		Timeline line = new Timeline(new KeyFrame(Duration.millis(7), moveEvent));
		line.setCycleCount(Timeline.INDEFINITE);
		line.play();
		
		Scene scene = new Scene(pane, 400, 400);
		
		stage.setScene(scene);
		stage.show();
	}

}
