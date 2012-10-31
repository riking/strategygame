package riking.stratgame;

public class GameStarter
{
	public Team teamRed = new Team();
	public Team teamBlue = new Team();
	
	public GameStarter()
	{
		World w = new World(9).setGlobal();
	}
}
