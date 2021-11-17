package snakeGame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SnakeMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	Font bigFont = new Font("Lucida Handwriting Italic", 50);
	Font smallFont = new Font(10);
	VBox vert;
	int mode = 0;
	SnakePane skPane;
	Scene secondScene;
	int diff = 1;
	int level = 1;
	public static Button btRestart;
	public static Button btNext;
	Font btFont = new Font("Lucida Handwriting Italic", 20);
	
	@Override
	public void start(Stage stage) throws Exception {
		Button btStart = new Button("Start");
		btStart.setFont(btFont);
		btStart.setStyle("-fx-border-color: black");
		btStart.setPrefSize(150, 50);
		Button btOptions = new Button("Options");
		btOptions.setFont(btFont);
		btOptions.setStyle("-fx-border-color: black");
		btOptions.setPrefSize(150, 50);
		Button btExit = new Button("Exit");
		btExit.setFont(btFont);
		btExit.setStyle("-fx-border-color: black");
		btExit.setPrefSize(150, 50);
		
		Text mainText = new Text("Simple  Snake");
		Text space = new Text(" ");
		Text space2 = new Text(" ");
		Text space3 = new Text(" ");
		mainText.setFont(bigFont);
		Text secText = new Text(" By Mathieu Lajoie - Computer Science Student");
		secText.setFont(smallFont);
		
		vert = new VBox(20);
		vert.setAlignment(Pos.TOP_CENTER);
		vert.getChildren().addAll(mainText, space3, btStart, btOptions, btExit);
		
		VBox vert2 = new VBox(50);
		vert2.getChildren().addAll(space, space2);
		
		BorderPane mainPane = new BorderPane();
		mainPane.setStyle("-fx-border-color: black");
		mainPane.setTop(vert2);
		mainPane.setCenter(vert);
		BorderPane.setAlignment(mainText, Pos.CENTER);
		BackgroundImage myBI= new BackgroundImage(new Image("res/snakeLast.jpg", 620, 620,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		mainPane.setBackground(new Background(myBI));
		mainPane.setBottom(secText);
		BorderPane.setAlignment(secText, Pos.BASELINE_LEFT);
		
		Scene mainScene = new Scene(mainPane, 600, 600);
		
		btStart.setOnAction(e -> {
			skPane = new SnakePane(this.diff, this.level);
			skPane.setStyle("-fx-border-color: black");
			Scene secondScene = new Scene(skPane, 600, 600);
			secondScene.setOnKeyPressed(r -> {
				if (r.getCode() == KeyCode.UP && skPane.snek.getDir() != 2) {
					skPane.snek.setDir(0);
					skPane.line.play();
				} else if (r.getCode() == KeyCode.RIGHT && skPane.snek.getDir() != 3) {
					skPane.snek.setDir(1);
					skPane.line.play();
				} else if (r.getCode() == KeyCode.DOWN && skPane.snek.getDir() != 0) {
					skPane.snek.setDir(2);
					skPane.line.play();
				} else if (r.getCode() == KeyCode.LEFT && skPane.snek.getDir() != 1) {
					skPane.snek.setDir(3);
					skPane.line.play();
				} else if (r.getCode() == KeyCode.ESCAPE) {
					btRestart.fire();
				}
			});
			stage.setScene(secondScene);
		});
		
		ImageView iv = new ImageView("res/arrow.png");
		iv.setFitHeight(15);
		iv.setFitWidth(15);
		
		btRestart = new Button("Return to menu");
		btRestart.setFont(btFont);
		btRestart.setStyle("-fx-border-color: black");
		btRestart.setPrefSize(220, 50);
		btRestart.setLayoutX(190);
		btRestart.setLayoutY(270);
		btRestart.setOnAction(e -> {
			this.level = 1;
			stage.setScene(mainScene);
		});
		
		ImageView iv2 = new ImageView("res/arrow.png");
		iv2.setFitHeight(15);
		iv2.setFitWidth(15);
		iv2.setRotate(180);
		
		//Next button settings
		btNext = new Button("Next level ");
		btNext.setGraphic(iv2);
		btNext.setContentDisplay(ContentDisplay.RIGHT);
		btNext.setFont(btFont);
		btNext.setStyle("-fx-border-color: black");
		btNext.setPrefSize(200, 50);
		btNext.setLayoutX(200);
		btNext.setLayoutY(270);
		btNext.setOnAction(e -> {
			this.level++;
			btStart.fire();
		});
		
		Button btEasy = new Button("Easy");
		btEasy.setFont(btFont);
		btEasy.setStyle("-fx-border-color: black; -fx-border-width: 4");
		btEasy.setPrefSize(150, 50);
		Button btNormal = new Button("Normal");
		btNormal.setFont(btFont);
		btNormal.setStyle("-fx-border-color: black");
		btNormal.setPrefSize(150, 50);
		Button btHard = new Button("Hard");
		btHard.setFont(btFont);
		btHard.setStyle("-fx-border-color: black");
		btHard.setPrefSize(150, 50);
		Button btBack = new Button(" Back", iv);
		btBack.setFont(btFont);
		btBack.setStyle("-fx-border-color: black");
		btBack.setPrefSize(150, 50);
		
		btOptions.setOnAction(e -> {
			vert.getChildren().clear();
			vert.getChildren().addAll(mainText, space3, btEasy, btNormal, btHard, btBack);
			btBack.setOnAction(r -> {
				vert.getChildren().clear();
				vert.getChildren().addAll(mainText, space3, btStart, btOptions, btExit);
			});
			
			btEasy.setOnAction(r -> {
				btEasy.setStyle("-fx-border-color: black; -fx-border-width: 4");
				btHard.setStyle("-fx-border-color: black");
				btNormal.setStyle("-fx-border-color: black");
				this.diff = 1;
			});
			
			btNormal.setOnAction(r -> {
				btNormal.setStyle("-fx-border-color: black; -fx-border-width: 4");
				btHard.setStyle("-fx-border-color: black");
				btEasy.setStyle("-fx-border-color: black");
				this.diff = 2;
			});
			
			btHard.setOnAction(r -> {
				btHard.setStyle("-fx-border-color: black; -fx-border-width: 4");
				btEasy.setStyle("-fx-border-color: black");
				btNormal.setStyle("-fx-border-color: black");
				this.diff = 3;
			});
		});
		
		btExit.setOnAction(e -> {
			System.exit(0);
		});
		
		stage.setScene(mainScene);
		stage.getIcons().add(new Image("res/simpleSnake.png"));
		stage.setResizable(false);
		stage.setTitle("Simple Snake");
		stage.show();
	}

}
