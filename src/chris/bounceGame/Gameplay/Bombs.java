package chris.bounceGame.Gameplay;

import java.util.LinkedList;
import java.util.ListIterator;

import com.badlogic.androidgames.framework.Graphics;

public class Bombs {
	
	
	public Bombs(String sheetName, Protagonist pro) {
		
		sched = new FireSchedule(sheetName);
		this.pro = pro;
		timeSinceStart = 0;
		
		
		activeBombs = new LinkedList<Bomb>();
		recyclingBin = new LinkedList<Bomb>();		
	}
	
	public int update(float time) {
		
		timeSinceStart += time;
				
		createNewBombs(timeSinceStart);
		
		int numberOfHits = 0;
		ListIterator<Bomb> iterator = activeBombs.listIterator();
		while(iterator.hasNext()) {
			
			Bomb bomb = iterator.next();
			bomb.update(timeSinceStart, pro);
			
			switch(bomb.getState()) {
			case HIT_GROUND:
				numberOfHits++;
				recyclingBin.add(bomb);
				iterator.remove();
				break;
			case CLEARED_SCREEN:
				recyclingBin.add(bomb);
				iterator.remove();
				break;
			// do nothing for the other states				
			}			
			
		}
		
		return numberOfHits;		
	}
	
	public void draw(Graphics g, float timeSinceStart) {
		for(Bomb bomb: activeBombs) {
			// might be clearer to add other none-draw states, but because the
			// bombs are removes from the list when they are in said states it wont
			// make a difference functionally
			if(bomb.getState() != Bomb.BombState.WAITING)
				bomb.draw(g, timeSinceStart);
		}
	}
	
	public int percentageComplete() {
		return sched.percentageComplete(timeSinceStart);
	}
	
	private void createNewBombs(float time) {
		while(activeBombs.size() < NUM_ACTIVE_BOMBS) {
			if(sched.hasNext()) {
				if(recyclingBin.isEmpty())
					activeBombs.add(sched.getNext());
				else
					activeBombs.add(sched.getNext(recyclingBin.removeLast()));
			}			
		}		
	}
	
	private final static int NUM_ACTIVE_BOMBS = 30;
	
	private float timeSinceStart;
	
	private FireSchedule sched;
	
	private LinkedList<Bomb> activeBombs;
	
	private LinkedList<Bomb> recyclingBin;
	
	private Protagonist pro;
	
	
	

}
