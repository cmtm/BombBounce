package chris.bounceGame;


public class World {
    static final int WORLD_WIDTH = 800;
    static final int WORLD_HEIGHT = 480;
    static final int BLOCK_WIDTH = 32;
    static final int BLOCK_HEIGHT = 220;
    static final int PROTAGONIST_WIDTH = 66;
    static final int PROTAGONIST_HEIGHT = 84;


    public boolean gameOver = false;
    public int protagonistPosition = WORLD_WIDTH / 2; //position of a left-most point of the protagonist. Initial value is middle of worlds.
    
    public void moveProtagonist(int position) { //the argument here represents the MIDDLE of the protagonist
    	if (position > WORLD_WIDTH)    //this should never occur
    		protagonistPosition = WORLD_WIDTH - (PROTAGONIST_WIDTH / 2);
    	else if (position < (BLOCK_WIDTH + PROTAGONIST_WIDTH / 2) )
    		protagonistPosition = BLOCK_WIDTH;
    	else
    		protagonistPosition = position - (PROTAGONIST_WIDTH / 2);
    }
}
