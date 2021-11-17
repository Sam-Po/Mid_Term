package snakeGame;

import java.util.ArrayList;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SnakePane extends Pane {
	
	protected Snake snek = new Snake();
	private Rectangle food = new Rectangle(20, 20);
	Timeline line;
	int diff = 1;
	int level = 1;
	int count = 0;
	boolean finished = false;
	ArrayList<Rectangle> obsta = new ArrayList<>();
	
	public SnakePane(int diff) {
		this.diff = diff;
		this.level = 1;
		init();
	}
	
	public SnakePane(int diff, int level) {
		this.diff = diff;
		this.level = level;
		levelSetter(this.level);
		init();
	}
	
	private void init() {
		
		//Text Score
		Text scoreText = new Text("Score: " + snek.sqList.size());
		scoreText.setFont(new Font("Lucida Handwriting Italic", 20));
		scoreText.setX(5);
		scoreText.setY(20);
		getChildren().add(scoreText);
		
		//Text level
		Text levelText = new Text("Level " + this.level);
		levelText.setFont(new Font("Lucida Handwriting Italic", 60));
		levelText.setX(180);
		levelText.setY(290);
		getChildren().add(levelText);
		
		//Level text animation handler
		EventHandler<ActionEvent> animEvent = e -> {
			if (count > 200) {
				if (levelText.getFont().getSize() > 20)
					levelText.setFont(new Font("Lucida Handwriting Italic", levelText.getFont().getSize() - 0.12));
				if (levelText.getX() < 520)
					levelText.setX(levelText.getX() + 1);
				if (levelText.getY() > 23)
					levelText.setY(levelText.getY() - 1);
			}
			count++;
		};
		
		//Level animation
		Timeline anim = new Timeline(new KeyFrame(Duration.millis(5), animEvent));
		anim.setCycleCount(Timeline.INDEFINITE);
		anim.play();
		
		this.food.setX(getRandomInt(1, 29) * 20);
		this.food.setY(getRandomInt(1, 29) * 20);
		this.food.setFill(Color.RED);
		
		getChildren().add(food);
		
		for (int i = 0; i < snek.getLength(); i++) {
			getChildren().add(snek.sqList.get(i));
		}
		
		//Main handler for timeline animation
		EventHandler<ActionEvent> moveEvent = e -> {
			if (!finished) {
				//Change color after score x
				if (snek.sqList.size() == 5) {
					snek.reColor(Color.BLUE, Color.AQUA);
				} else if (snek.sqList.size() == 10) {
					snek.reColor(Color.GREEN, Color.YELLOW);
				} else if (snek.sqList.size() == 20) {
					snek.reColor(Color.FIREBRICK, Color.BLACK);
				}
				
				//Condition for finishing level = 30
				if (snek.sqList.size() == 25) {
					finished = true;
					Text winText = new Text("Good job!");
					winText.setX(195);
					winText.setY(250);
					winText.setFont(new Font("Lucida Handwriting Italic", 40));
					getChildren().addAll(SnakeMain.btNext, winText);
				}
				
				//Condition for hitting obstacles
				for (int i = 0; i < obsta.size() && !finished; i++) {
					if (snek.sqList.get(0).getX() == this.obsta.get(i).getX() && snek.sqList.get(0).getY() == this.obsta.get(i).getY()) {
						line.stop();
						Text text = new Text("You lost!");
						text.setX(205);
						text.setY(250);
						text.setFont(new Font("Lucida Handwriting Italic", 40));
						getChildren().addAll(SnakeMain.btRestart, text);
						finished = true;
					}
				}
				
				//Condition for hitting self
				for (int i = 1; i < snek.sqList.size() && !finished; i++) {
					if (snek.sqList.get(0).getX() == snek.sqList.get(i).getX() && snek.sqList.get(0).getY() == snek.sqList.get(i).getY()) {
						line.stop();
						Text text = new Text("You lost!");
						text.setX(205);
						text.setY(250);
						text.setFont(new Font("Lucida Handwriting Italic", 40));
						getChildren().addAll(SnakeMain.btRestart, text);
						finished = true;
					}
				}
				
				//Condition for hitting walls
				if (!finished && ((snek.sqList.get(0).getX() >= 580 && snek.getDir() == 1) || (snek.sqList.get(0).getX() <= 0 && snek.getDir() == 3) || (snek.sqList.get(0).getY() <= 0 && snek.getDir() == 0) || (snek.sqList.get(0).getY() >= 580 && snek.getDir() == 2))) {
					line.stop();
					Text text = new Text("You lost!");
					text.setX(205);
					text.setY(250);
					text.setFont(new Font("Lucida Handwriting Italic", 40));
					getChildren().addAll(SnakeMain.btRestart, text);
					finished = true;
				}
				
				//Condition for having eaten food
				if (this.food.getX() == snek.sqList.get(0).getX() && this.food.getY() == snek.sqList.get(0).getY()) {
					resetFood();
					snek.add();
					scoreText.setText("Score: " + snek.sqList.size());
					getChildren().add(snek.sqList.get(snek.sqList.size() - 1));
				}
				
				//Default move
				if (!finished)
					snek.move();
			}
		};
		
		line = new Timeline(new KeyFrame(Duration.millis(diff == 1 ? 150 : (diff == 2 ? 100 : 50)), moveEvent));
		line.setCycleCount(Timeline.INDEFINITE);
	}
	
	private void resetFood() {
		this.food.setX(getRandomInt(1, 29) * 20);
		this.food.setY(getRandomInt(1, 29) * 20);
		int i = 0;
		while (i < snek.sqList.size()) {
			if (this.food.getX() == snek.sqList.get(i).getX() && this.food.getY() == snek.sqList.get(i).getY()) {
				i = 0;
				this.food.setX(getRandomInt(1, 29) * 20);
				this.food.setY(getRandomInt(1, 29) * 20);
			}
			for (int j = 0; j < obsta.size(); j++) {
				if (this.food.getX() == this.obsta.get(j).getX() && this.food.getY() == this.obsta.get(j).getY()) {
					i = 0;
					this.food.setX(getRandomInt(1, 29) * 20);
					this.food.setY(getRandomInt(1, 29) * 20);
				}
			}
			i++;
		}
	}
	
	private void levelSetter(int level) {
		if (level == 2) {
			this.obsta.clear();
			Rectangle rec = new Rectangle(100, 100, 20, 20);
			rec.setFill(Color.MAGENTA);
			rec.setStroke(Color.BLACK);
			rec.setStrokeWidth(4);
			this.obsta.add(rec);
			getChildren().add(rec);
		} else if (level == 3) {
			this.obsta.clear();
			int i = 0;
			while (i < 3) {
				Rectangle temp = new Rectangle(getRandomInt(1, 29) * 20, getRandomInt(1, 29) * 20, 20, 20);
				if ((temp.getX() == this.food.getX() && temp.getY() == temp.getY()) || (temp.getX() == snek.sqList.get(0).getX() && temp.getY() == snek.sqList.get(0).getY())) {
					continue;
				}
				temp.setFill(Color.MAGENTA);
				temp.setStroke(Color.BLACK);
				temp.setStrokeWidth(4);
				this.obsta.add(temp);
				getChildren().add(temp);
				i++;
			}
		} else if (level == 4) {
			this.obsta.clear();
			int i = 0;
			while (i < 7) {
				Rectangle temp = new Rectangle(getRandomInt(1, 29) * 20, getRandomInt(1, 29) * 20, 20, 20);
				if ((temp.getX() == this.food.getX() && temp.getY() == temp.getY()) || (temp.getX() == snek.sqList.get(0).getX() && temp.getY() == snek.sqList.get(0).getY())) {
					continue;
				}
				temp.setFill(Color.MAGENTA);
				temp.setStroke(Color.BLACK);
				temp.setStrokeWidth(4);
				this.obsta.add(temp);
				getChildren().add(temp);
				i++;
			}
		} else if (level == 5) {
			this.obsta.clear();
			//Make class for obstacles? Extends rectangle? easier for fill/color
			//Create obstacle pattern //TODO Make method for this, with position and size as input?
			Rectangle[] recAr = new Rectangle[25];
			recAr[0] = new Rectangle(300, 300, 20, 20);
			recAr[1] = new Rectangle(320, 300, 20, 20);
			recAr[2] = new Rectangle(340, 300, 20, 20);
			recAr[3] = new Rectangle(360, 300, 20, 20); //show
			recAr[4] = new Rectangle(300, 320, 20, 20);
			recAr[5] = new Rectangle(300, 340, 20, 20);
			recAr[6] = new Rectangle(300, 360, 20, 20); //show
			recAr[7] = new Rectangle(280, 300, 20, 20);
			recAr[8] = new Rectangle(260, 300, 20, 20);
			recAr[9] = new Rectangle(240, 300, 20, 20); //show
			recAr[10] = new Rectangle(300, 280, 20, 20);
			recAr[11] = new Rectangle(300, 260, 20, 20);
			recAr[12] = new Rectangle(300, 240, 20, 20); //show
			
			recAr[13] = new Rectangle(260, 320, 20, 20); //SHow
			recAr[14] = new Rectangle(280, 340, 20, 20); //SHOW
			recAr[15] = new Rectangle(280, 320, 20, 20);
			recAr[16] = new Rectangle(320, 340, 20, 20); //show
			recAr[17] = new Rectangle(340, 320, 20, 20); //show
			recAr[18] = new Rectangle(320, 320, 20, 20);
			recAr[19] = new Rectangle(340, 280, 20, 20); //show
			recAr[20] = new Rectangle(320, 260, 20, 20); //show
			recAr[21] = new Rectangle(320, 280, 20, 20);
			recAr[22] = new Rectangle(280, 260, 20, 20); //show
			recAr[23] = new Rectangle(260, 280, 20, 20); //show
			recAr[24] = new Rectangle(280, 280, 20, 20);
			
			getChildren().addAll(recAr[3], recAr[6], recAr[9], recAr[12], recAr[13], recAr[14], recAr[16], recAr[17], recAr[19], recAr[20], recAr[22], recAr[23]);
			
			for (int i = 0; i < recAr.length; i++) {
				recAr[i].setFill(Color.MAGENTA);
				recAr[i].setStroke(Color.BLACK);
				recAr[i].setStrokeWidth(4);
				this.obsta.add(recAr[i]);
			}
			
			int i = 0;
			while (i < recAr.length) {
				if (recAr[i].getX() == this.food.getX() && recAr[i].getY() == this.food.getY()) {
					resetFood();
					i = 0;
					continue;
				}
				i++;
			}
		}
	}
	
	private int getRandomInt(int min, int max) {
		  return (int)(Math.floor(Math.random() * (max - min + 1) + min));
	}
}
