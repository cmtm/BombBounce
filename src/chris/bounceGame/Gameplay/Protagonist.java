package chris.bounceGame.Gameplay;

import chris.bounceGame.Constants;

import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;

public class Protagonist {
	
	public Protagonist(int startingPosition) {
		xPosition = startingPosition;
	}
	
	public void update(float time, int xTouch) {
		if (xTouch > Constants.WORLD_WIDTH)    //this should never occur
			xPosition = Constants.WORLD_WIDTH;
		else if (xTouch < LEFT_BOUND )
			xPosition = LEFT_BOUND;
		else
			xPosition = xTouch;		
	}
	
	public boolean isSupported(int x) {
		if(xPosition - PROTAGONIST_WIDTH/2 < x  &&  x < xPosition + PROTAGONIST_WIDTH/2 )
			return true;
		else
			return false;
	}
	
	public void draw(Graphics g) {
		// this is an awkward and wasteful way of creating the Pixmap.
		// I might end up passing graphics as an parameter to constructor
		if(picture == null)
			picture = g.newPixmap("protagonistPicAlpha.png",PixmapFormat.ARGB8888);
		
		g.drawPixmap(picture, xPosition - PROTAGONIST_WIDTH/2, PROTAGONIST_HEIGHT + HEIGHT_OFF_GROUND);
		
	}
	
	private static final int PROTAGONIST_WIDTH = 66;
    private static final int PROTAGONIST_HEIGHT = 84;
    private static final int HEIGHT_OFF_GROUND = 0;
    public static final int LEFT_BOUND = 0;
	
	private int xPosition;
	private Pixmap picture;
}
