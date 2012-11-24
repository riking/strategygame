package riking.stratgame.tiles;

import riking.stratgame.Team;
import riking.stratgame.TerminalRenderer;
import riking.stratgame.World;
import riking.stratgame.menus.MenuChoice;
import riking.stratgame.tasks.Task;
import riking.stratgame.tasks.TaskSpawner;
import riking.stratgame.units.Targetable;

public class TileSpawner extends Tile implements Targetable {
	public Team myteam;
	public int health;
	public World world;
	public TerminalRenderer screen;
	public MenuChoice[] choices = new MenuChoice[] {
		
	};
	
	public TileSpawner(Team t, int posx, int posy) {
		super(posx, posy);
		myteam = t;
		world = World.getWorld();
		health = 8000;
		screen = world.getScreen();
	}
	
	public void scheduleFirstRun()
	{
		myteam.setBase(this);
		TaskSpawner t = new TaskSpawner(this,0,false);
		t.postExecuteBehavior = Task.PEBehavior.ALLOW_POST_EXECUTE;
		world.scheduler.add(t);
	}
	@Override public boolean containsTarget(Team attacker)
	{
		return attacker.isOpposedTo(myteam);
	}

	@Override
	public Team getTeam() {
		return myteam;
	}

	@Override
	public boolean isOpposedTo(Team other) {
		return myteam.isOpposedTo(other);
	}
}
