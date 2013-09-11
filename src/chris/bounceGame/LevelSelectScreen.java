package chris.bounceGame;

import android.R.color;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class LevelSelectScreen extends Screen {
	
	private static final int HORIZONTAL_SPACE = 30;
	private static final int VERTICAL_SPACE = 50;
	
	
	private int levelScores[];
	private Planet planet;
	
	private Button[] levels;
	private Pixmap lock;
	
	public LevelSelectScreen(Game game, Planet planet, int[] levelScores) {
		super(game);
		
		Graphics g = game.getGraphics();
		
		this.planet = planet;
		lock = g.newPixmap("lock.png", PixmapFormat.ARGB8888);
		this.levelScores = levelScores;	
		//TODO: set background in this case when they're ready
		switch (planet) {
			case MOON:      
			                break;
			case EARTH:    
			                break;
			case JUPITER: 
			                break;
			case SUN:    
			                break;
		}
			
		Pixmap buttonPic = g.newPixmap("levelButton.png", PixmapFormat.ARGB8888);
		
		
		int distanceFromLeft = (g.getWidth() - (3 * buttonPic.getWidth() + HORIZONTAL_SPACE * 2))/2;
		int distanceFromBottom = (g.getHeight() + 2 * buttonPic.getHeight() + VERTICAL_SPACE)/2;
		
		levels = new Button[6];		
		
		for (int y = 0; y < 2; y++) {
			for(int x = 0; x < 3; x++) {
				levels[y*3 + x] = new Button(distanceFromLeft + x * (buttonPic.getWidth() + HORIZONTAL_SPACE), distanceFromBottom - y * (buttonPic.getHeight() + VERTICAL_SPACE), buttonPic);
			}
		}

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
		
		g.clear(color.darker_gray);
		
		for(Button button : levels) {
			button.draw(g);
		}
		//add lock

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
		for (int i = 0; i < levels.length; i++) {
			if(levels[i].isInsideButton(x,y) && (i == 0 || levelScores[i - 1] >= Constants.POINT_THRESHOLD)) {
				game.setScreen(new NewGameScreen(game, planet, i));
			}
		}
		
	}

}
