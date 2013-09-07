package chris.bounceGame;

import java.util.List;

import android.graphics.Color;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;

public class GameScreen extends Screen {

    World world;
    
    public GameScreen(Game game) {
        super(game);
        world = new World();
    }

    @Override
    public void update(float deltaTime) {
    	//---- flush inputs??? --
        game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        //-----------------------
        
        world.moveProtagonist(game.getInput().getTouchX(0));     
    }
   

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        
        g.clear(Color.LTGRAY);
        drawWorld(world);               
    }
    
    private void drawWorld(World world) {
        Graphics g = game.getGraphics();        
        
        g.drawRect(0, world.WORLD_HEIGHT - world.BLOCK_HEIGHT, world.BLOCK_WIDTH, world.BLOCK_HEIGHT, Color.GREEN);
        
        g.drawPixmap(Assets.protagonist, world.protagonistPosition, world.WORLD_HEIGHT - world.PROTAGONIST_HEIGHT);
    }   
    
    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        
    }

    @Override
    public void dispose() {
        
    }
}