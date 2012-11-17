package riking.stratgame.tiles;

import riking.stratgame.Team;
import riking.stratgame.World;
import riking.stratgame.tasks.Task;
import riking.stratgame.tasks.TaskSpawner;

public class TileSpawner extends Tile implements Targetable {
	public Team myteam;
	public int health;
	public World world;
	
	public TileSpawner(Team t, int posx, int posy) {
		super(posx, posy);
		myteam = t;
		world = World.getWorld();
		health = 8000;
	}
	
	public void scheduleFirstRun()
	{
		myteam.setBase(this);
		TaskSpawner t = new TaskSpawner(this,0,false);
		t.postExecuteBehavior = Task.PEBehavior.DENY_POST_EXECUTE;
		world.scheduler.add(t);
	}
	@Override public boolean containsTarget(Team attacker)
	{
		return attacker.isOpposedTo(myteam);
	}
}
