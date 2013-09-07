package chris.bounceGame;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;
//remove below if I get rid of sketchy game cast
import com.badlogic.androidgames.framework.impl.AndroidGame;


public class MainMenuScreen extends Screen {
	//button placement constants
	private static final int X_POS = 436;
	private static final int Y_POS = 26;
	private static final int Y_OFFSET = 75;
	private static final int SPEAKER_X = 680;
	private static final int SPEAKER_Y = 403;


	
	private Button play, options, science, cinematics, exit;
	private Button speaker;
	
	private Pixmap redRing, greenRing;
	private Pixmap background;
	
	public MainMenuScreen(Game game) {
		super(game);
		Graphics g = game.getGraphics();
		play       = new Button(X_POS, Y_POS + Y_OFFSET * 0, g.newPixmap("playArmor.png", PixmapFormat.ARGB8888));
		options	   = new Button(X_POS, Y_POS + Y_OFFSET * 1, g.newPixmap("optionsArmor.png", PixmapFormat.ARGB8888));
		science	   = new Button(X_POS, Y_POS + Y_OFFSET * 2, g.newPixmap("scienceArmor.png", PixmapFormat.ARGB8888));
		cinematics = new Button(X_POS, Y_POS + Y_OFFSET * 3, g.newPixmap("cinematicsArmor.png", PixmapFormat.ARGB8888));
		exit	   = new Button(X_POS, Y_POS + Y_OFFSET * 4, g.newPixmap("exitArmor.png", PixmapFormat.ARGB8888));
		
		speaker = new Button(SPEAKER_X, SPEAKER_Y, g.newPixmap("speaker.png", PixmapFormat.ARGB8888));
		
		redRing = g.newPixmap("redRing.png", PixmapFormat.ARGB8888);
		greenRing = g.newPixmap("greenRing.png", PixmapFormat.ARGB8888);
		background = g.newPixmap("mainMenuBackground.png", PixmapFormat.ARGB8888);
		
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
		
		play.draw(g);
		options.draw(g);
		science.draw(g);
		cinematics.draw(g);
		exit.draw(g);
		speaker.draw(g);
		
		if(game.getPersistentData().isVolumeOn)
			g.drawPixmap(greenRing, SPEAKER_X, SPEAKER_Y);
		else
			g.drawPixmap(redRing, SPEAKER_X, SPEAKER_Y);
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
		
		if(play.isInsideButton(x, y))
			game.setScreen(new PlanetSelectScreen(game));
		else if (options.isInsideButton(x, y))
			game.setScreen(new OptionsScreen(game));
		else if (science.isInsideButton(x, y))
			game.setScreen(new ScienceScreen(game));
		else if (cinematics.isInsideButton(x, y))
			game.setScreen(new CinematicsScreen(game));
		else if (exit.isInsideButton(x, y))
			((AndroidGame) game).finish();
		else if (speaker.isInsideButton(x, y))
			game.getPersistentData().isVolumeOn = !game.getPersistentData().isVolumeOn;
		
	}

}
