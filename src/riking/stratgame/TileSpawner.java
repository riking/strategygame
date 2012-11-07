package riking.stratgame;

import riking.stratgame.tasks.TaskSpawner;

public class TileSpawner extends Tile {
	public Team myteam;
	public int health;
	
	public TileSpawner(Team t, int posx, int posy) {
		super(posx, posy);
		myteam = t;
		// TODO Auto-generated constructor stub
	}
	
	public void scheduleFirstRun()
	{
		World.getScheduler().schedule(new TaskSpawner(this),1);
	}
	@Override public boolean containsTarget(Team attacker)
	{
		return attacker.isOpposedTo(myteam);
	}
}
