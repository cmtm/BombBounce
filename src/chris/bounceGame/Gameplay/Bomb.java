package chris.bounceGame.Gameplay;

import chris.bounceGame.Constants;

import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;

public class Bomb {
	
	public enum BombState {WAITING, IN_AIR, DIPPING, FALLING, HIT_GROUND, CLEARED_SCREEN}
	
	
	
	public Bomb(float init_yVel, float init_xVel, float init_y, float init_x, float gravitationalConst, float startTime) {
		this.init_yVel = init_yVel;
		this.init_xVel = init_xVel;
		this.init_y = init_y;
		this.init_x = init_x;
		this.gravitationalConst = gravitationalConst;
		this.startTime = startTime;
		
		state = BombState.WAITING;
		
		calculateLandingTimes();
	}

	public boolean update(float timeSinceGameStart, Protagonist pro) {
		
		float timeSinceBombStart = timeSinceGameStart - startTime;
		
		switch(state) {
		case WAITING:
			if(timeSinceBombStart > startTime)
				state = BombState.IN_AIR;
		case IN_AIR:
			if(timeSinceBombStart > timeOfFirstContact) {
				if(pro.isSupported(calculateXposition(timeSinceBombStart)))
					state = BombState.DIPPING;
				else
					state = BombState.FALLING;
			}
			// might replace this with a "if(timeSinceBombStart > timeOfClearScreen)"
			// and a corresponding addition to calculateLandingTimes()
			if(calculateXposition(timeSinceBombStart) > Constants.WORLD_WIDTH)
				state = BombState.CLEARED_SCREEN;
		case DIPPING:
			if(timeSinceBombStart > timeOfLowestContact) {
				state = BombState.IN_AIR;
				// re-evaluate the ballistics parameters as the bomb has a new trajectory
				
				init_yVel = calculateYvelocity(timeOfLowestContact) * -1;
				// init_xVel stays the same
				init_y = TRAMPOLINE_HEIGHT - REQ_TRAMP_DIP;
				init_x = calculateXposition(timeOfLowestContact);		
			} else {
				if(!pro.isSupported(calculateXposition(timeSinceBombStart)))
					state = BombState.FALLING;
			}
		case FALLING:
			// might replace this with a "if(timeSinceBombStart > timeOfGroundHit)"
			// and a corresponding addition to calculateLandingTimes()
			if(calculateYposition(timeSinceBombStart) < RADIUS) {
				state = BombState.HIT_GROUND;
				return true;
			}
		// There's nothing to be done in the case of CLEARED_SCREEN or HIT_GROUND
		}
		
		return false;
	}
	
	public void draw(Graphics g, float timeSinceGameStart)  {

		float timeSinceBombStart = timeSinceGameStart - startTime;
		
		if(picture == null)
			picture = g.newPixmap("bomb.png",PixmapFormat.ARGB8888);
		
		g.drawPixmap(picture, calculateXposition(timeSinceBombStart) - RADIUS/2 , calculateYposition(timeSinceBombStart) - RADIUS/2);
	}
	

	public BombState getState() {
		return state;
	}
	
	private int calculateYposition(float timeSinceBombStart) {

		return (int) (Math.pow(timeSinceBombStart, 2) * gravitationalConst / 2   +   init_yVel * timeSinceBombStart   +   init_y);		
	}
	
	private int calculateXposition(float timeSinceBombStart) {
		return (int) (init_xVel * timeSinceBombStart   +   init_x);		
	}
	
	private float calculateYvelocity(float timeSinceBombStart) {

		return (int) (gravitationalConst * timeSinceBombStart   +   init_yVel);
	}
	
	private void calculateLandingTimes() {
		timeOfFirstContact = (float) (Math.sqrt(Math.pow(init_yVel, 2) - 2*gravitationalConst*(init_y - TRAMPOLINE_HEIGHT)) - init_yVel) / gravitationalConst;
		timeOfLowestContact = (float) (Math.sqrt(Math.pow(init_yVel, 2) - 2*gravitationalConst*(init_y - TRAMPOLINE_HEIGHT + REQ_TRAMP_DIP)) - init_yVel) / gravitationalConst;
	}
	
	private static final int REQ_TRAMP_DIP = 4;
	private static final int RADIUS = 10;
	// it might be more appropriate to put this in system constants
	private static final int TRAMPOLINE_HEIGHT = 20;
	
	private BombState state;
	private float init_yVel, init_xVel, init_y, init_x;
	private float gravitationalConst;
	private float startTime;
	private float timeOfFirstContact;
	private float timeOfLowestContact;
	
	private static Pixmap picture;
}
