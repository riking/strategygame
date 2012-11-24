package riking.stratgame;

import riking.stratgame.tiles.TileSpawner;

public class GameStarter
{
	public static void setup()
	{
		World w = new World(9,9).setGlobal();
		Team RED = new Team(1,"RED",1);
		Team BLUE= new Team(2,"BLU",4);
		TileSpawner redSpawn = new TileSpawner(RED, 1, 1);
		TileSpawner bluSpawn = new TileSpawner(BLUE, 7, 7);
		w.addTeam(RED).addTeam(BLUE).replaceTile(redSpawn).replaceTile(bluSpawn);
		redSpawn.scheduleFirstRun();
		bluSpawn.scheduleFirstRun();
	}
	public static void run()
	{
		World w = World.getWorld();
		while (w.theGameMustGoOn)
		{
			w.tick++;
			w.scheduler.tick(w.tick);
		}
	}
	
}
