package riking.stratgame.tasks;

import riking.stratgame.TickTaskScheduler;
import riking.stratgame.TileSpawner;
import riking.stratgame.World;

public class TaskSpawner extends BasicTask {
	TileSpawner myTile;
	public TaskSpawner(TileSpawner tile) {
		super(TickTaskScheduler.currentTick+1);
		myTile = tile;
	}

	@Override
	public void run() {
		//TODO Get user input
		//TODO Do stuff
		World.getScheduler().schedule(new TaskSpawner(myTile), 1000);
	}

}
