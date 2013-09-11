package chris.bounceGame;

import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Pixmap;

public class Button {
	
	private int topLeftX, topLeftY;
	private Pixmap pic;
	
	public Button(int topLeftX, int topLeftY, Pixmap buttonPic) {
		this.topLeftX = topLeftX;
		this.topLeftY = topLeftY;
		this.pic = buttonPic;
	}
	
	public void draw(Graphics g) {
		g.drawPixmap(pic, topLeftX, topLeftY);
	}
	
	//I can just return the boolean expression but made it like this for clarity.
	public boolean isInsideButton(int x, int y) {
		if(    topLeftX < x    &&    x < (topLeftX + pic.getWidth())    &&
				topLeftY > y    &&    y >    (topLeftY - pic.getHeight())    )
			return true;
		else
			return false;
	}
}
