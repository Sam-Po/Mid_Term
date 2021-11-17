package snakeGame;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Snake {

	private int dir;	//0 = UP, 1 = RIGHT, 2 = DOWN, 3 = LEFT
	
	private int sqPos = 400;
	private int sqSize = 20;
	private Color color;
	private Color stroke;
	protected ArrayList<Rectangle> sqList = new ArrayList<>();
	
	public Snake() {
		this.dir = -1;
		Rectangle temp = new Rectangle(sqPos, sqPos, sqSize, sqSize);
		this.color = Color.BLACK;
		this.stroke = Color.WHITE;
		temp.setFill(Color.BLACK);
		temp.setStroke(Color.WHITE);
		this.sqList.add(temp);
	}
	
	public void add() {	
		Rectangle temp = new Rectangle(20, 20);
		temp.setFill(color);
		temp.setStroke(stroke);
		//temp.setX(this.sqList.get(this.sqList.size() - 1).getX());
		//temp.setY(this.sqList.get(this.sqList.size() - 1).getY());
		this.sqList.add(temp);
		Rectangle temp2 = new Rectangle(20, 20);
		Rectangle temp3 = new Rectangle(20, 20);
		for (int i = 1; i < this.sqList.size(); i++) {
			if (i == 1) {
				temp2.setX(this.sqList.get(i).getX());
				temp2.setY(this.sqList.get(i).getY());
				this.sqList.get(i).setX(this.sqList.get(i - 1).getX());
				this.sqList.get(i).setY(this.sqList.get(i - 1).getY());
				continue;
			} else {
				temp3.setX(this.sqList.get(i).getX());
				temp3.setY(this.sqList.get(i).getY());
				this.sqList.get(i).setX(temp2.getX());
				this.sqList.get(i).setY(temp2.getY());
			}
			temp2.setX(temp3.getX());
			temp2.setY(temp3.getY());
		}
		if (dir == 0) {
			this.sqList.get(0).setY(this.sqList.get(0).getY() - 20);
		} else if (dir == 1) {
			this.sqList.get(0).setX(this.sqList.get(0).getX() + 20);
		} else if (dir == 2) {
			this.sqList.get(0).setY(this.sqList.get(0).getY() + 20);
		} else if (dir == 3) {
			this.sqList.get(0).setX(this.sqList.get(0).getX() - 20);
		}
	}

	public int getLength() {
		return this.sqList.size();
	}
	
	public void move() {
		Rectangle temp2 = new Rectangle(20, 20);
		Rectangle temp3 = new Rectangle(20, 20);
		for (int i = 1; i < this.sqList.size(); i++) {
			if (i == 1) {
				temp2.setX(this.sqList.get(i).getX());
				temp2.setY(this.sqList.get(i).getY());
				this.sqList.get(i).setX(this.sqList.get(i - 1).getX());
				this.sqList.get(i).setY(this.sqList.get(i - 1).getY());
				continue;
			} else {
				temp3.setX(this.sqList.get(i).getX());
				temp3.setY(this.sqList.get(i).getY());
				this.sqList.get(i).setX(temp2.getX());
				this.sqList.get(i).setY(temp2.getY());
			}
			temp2.setX(temp3.getX());
			temp2.setY(temp3.getY());
		}
		if (dir == 0) {
			this.sqList.get(0).setY(this.sqList.get(0).getY() - 20);
		} else if (dir == 1) {
			this.sqList.get(0).setX(this.sqList.get(0).getX() + 20);
		} else if (dir == 2) {
			this.sqList.get(0).setY(this.sqList.get(0).getY() + 20);
		} else if (dir == 3) {
			this.sqList.get(0).setX(this.sqList.get(0).getX() - 20);
		}
	}

	public void reColor(Color r, Color s) {
		this.color = r;
		this.stroke = s;
		for (int i = 0; i < this.sqList.size(); i++) {
			this.sqList.get(i).setFill(r);
			this.sqList.get(i).setStroke(s);
		}
	}
	
	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}
	
}
