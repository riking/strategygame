package riking.stratgame;

public class GameStarter
{
	public void run()
	{
		World w = new World(9,9).setGlobal()
				.addTeam(new Team(1,"RED"))
				.addTeam(new Team(2,"BLU"))
				.replaceTile(new TileSpawner(1), 1, 1)
				.replaceTile(new TileSpawner(2), 8, 8)
				;
		mainloop(w);
	}
	public void mainloop(World w)
	{
		while (true)
		{
			
		}
	}
}
