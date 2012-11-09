package riking.stratgame;

import riking.stratgame.tasks.Task;
import riking.stratgame.tasks.TaskSpawner;

public class TileSpawner extends Tile {
	public Team myteam;
	public int health;
	public World world;
	
	public TileSpawner(Team t, int posx, int posy) {
		super(posx, posy);
		myteam = t;
		world = World.getWorld();
	}
	
	public void scheduleFirstRun()
	{
		TaskSpawner t = new TaskSpawner(this,0,false);
		t.postExecuteBehavior = Task.PEBehavior.DENY_POST_EXECUTE;
		world.scheduler.add(t);
	}
	@Override public boolean containsTarget(Team attacker)
	{
		return attacker.isOpposedTo(myteam);
	}
}
