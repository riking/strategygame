package riking.stratgame;

public class GameStarter
{
	public void run()
	{
		World w = new World(9,9).setGlobal();
		Team RED = new Team(1,"RED");
		Team BLUE= new Team(2,"BLU");
		TileSpawner redSpawn = new TileSpawner(RED, 1, 1);
		TileSpawner bluSpawn = new TileSpawner(BLU, 8, 8);
		w.addTeam(RED).addTeam(BLUE).replaceTile(redSpawn).replaceTile(bluSpawn);
		redSpawn.scheduleFirst();
		bluSpawn.scheduleFirst();
		mainloop(w);
	}
	public void mainloop(World w)
	{
		while (w.tick())
		{
		}
	}
}
