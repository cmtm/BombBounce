package chris.bounceGame;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.impl.AndroidGame;

public class PlanetSelectScreen extends Screen {
	
	//TODO: Catch back-button press and return to main menu
	
	private static final int POINT_THRESHOLD = 1;
	
	private int levelScores[][];
	
	private Button moon, earth, jupiter, sun;
	private Pixmap background;

	public PlanetSelectScreen(Game game) {
		super(game);

		levelScores= game.getPersistentData().levelScores;
		
		Graphics g = game.getGraphics();
		moon       = new Button(27, 227, g.newPixmap("moonButton.png", PixmapFormat.ARGB8888));
		earth	   = new Button(157, 195, g.newPixmap("earthButton.png", PixmapFormat.ARGB8888));
		jupiter	   = new Button(306, 143, g.newPixmap("jupiterButton.png", PixmapFormat.ARGB8888));
		sun        = new Button(494, 33, g.newPixmap("sunButton.png", PixmapFormat.ARGB8888));
		
		background = g.newPixmap("planetSelectBackground.png", PixmapFormat.ARGB8888);
	}
	
	@Override
	public void update(float deltaTime) {
		//---- flush inputs??? --
		game.getInput().getKeyEvents();
		//-----------------------
	
		for(TouchEvent ev : game.getInput().getTouchEvents()) {
			if(ev.type == TouchEvent.TOUCH_UP)
				checkForButtonPress(ev.x, ev.y);
		}
	}
	
	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawPixmap(background, 0, 0);
		
		moon.draw(g);
		if(levelScores[0][levelScores[0].length - 1] >= POINT_THRESHOLD)
			earth.draw(g);
		if(levelScores[1][levelScores[1].length - 1] >= POINT_THRESHOLD)
			jupiter.draw(g);
		if(levelScores[2][levelScores[2].length - 1] >= POINT_THRESHOLD)
			sun.draw(g);

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	
	private void checkForButtonPress(int x, int y) {
		
		if(moon.isInsideButton(x, y))
			game.setScreen(new LevelSelectScreen(game, Planet.MOON));
		else if (earth.isInsideButton(x, y) && levelScores[0][levelScores[0].length - 1] >= POINT_THRESHOLD)
			game.setScreen(new LevelSelectScreen(game, Planet.EARTH));
		else if (jupiter.isInsideButton(x, y) && levelScores[1][levelScores[1].length - 1] >= POINT_THRESHOLD)
			game.setScreen(new LevelSelectScreen(game, Planet.JUPITER));
		else if (sun.isInsideButton(x, y) && levelScores[2][levelScores[2].length - 1] >= POINT_THRESHOLD)
			game.setScreen(new LevelSelectScreen(game, Planet.SUN));
		
	}

}
