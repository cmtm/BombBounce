package chris.bounceGame.Gameplay;

public class FireSchedule {

	public FireSchedule(String sheetName) {
		// TODO Auto-generated constructor stub
		i = 1;
	}

	public Bomb getNext(Bomb oldBomb) {
		// TODO Auto-generated method stub
		i++;
		return new Bomb(i* 10, i * 10, 20, 0, -20, i);
	}

	public Bomb getNext() {
		// TODO Auto-generated method stub
		i++;
		return new Bomb(i* 10, i * 10, 20, 0, -20, i);
	}

	public boolean hasNext() {
		// TODO Auto-generated method stub
			return true;
	}

	public int percentageComplete(float timeSinceStart) {
		// TODO Auto-generated method stub
		return i;
	}
	
	// variable for first testing physics.
	// should be removed when this class is actually completed
	private int i;

}
