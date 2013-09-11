package chris.bounceGame;

import android.graphics.Color;
import chris.bounceGame.Gameplay.Bombs;
import chris.bounceGame.Gameplay.Protagonist;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;

public class NewGameScreen extends Screen {

	public NewGameScreen(Game game, Planet planet, int i) {
		super(game);
		this.protagonist = new Protagonist(Constants.WORLD_WIDTH / 2);
		this.bombs = new Bombs("", protagonist);
		
		
	}
	@Override
	public void update(float deltaTime) {
		//---- flush inputs??? --
        game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        //-----------------------
        
        protagonist.update(deltaTime, game.getInput().getTouchX(0));
        
        //TODO: react to hits
        bombs.update(deltaTime);
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
        
        g.clear(Color.LTGRAY);
        
        protagonist.draw(g);
        bombs.draw(g, deltaTime);
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
	
	private Pixmap background;
	private Bombs bombs;
	private Protagonist protagonist;

}
