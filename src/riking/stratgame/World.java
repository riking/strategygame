package riking.stratgame;

import riking.stratgame.Team;
import riking.stratgame.Tile;

public class World {
	public Tile[][] tiles;
	public int sizeX;
	public int sizeY;
	public TickTaskScheduler scheduler;
	public static World instance;
	
	public long time;
	public Team[] teams;
	
	public static World getWorld() { return instance; }
	public World setGlobal() {
		instance = this;
		return this;
	}
	
	public World(int size)
	{
		this(size,size);
	}
	public World(int sizeX, int sizeY)
	{
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		tiles = new Tile[sizeX][sizeY];
		for (int x=0; x<sizeX; x++)
		{
			for (int y=0; y<sizeY; y++)
			{
				tiles[x][y] = new Tile(x,y);
			}
		}
	}
	
	public Tile getTile(int posX, int posY)
	{
		return tiles[posX][posY];
	}
	public World replaceTile(Tile newTile, int posX, int posY)
	{
		tiles[posX][posY] = newTile;
		return this;
	}
	public World addTeam(Team t)
	{
		// Copy old array and insert
		Team[] temp = new Team[teams.length+1];
		for (int i=0; i<teams.length; i++)
		{ temp[i] = teams[i]; }
		temp[teams.length] = t; 
		return this;
	}
}
