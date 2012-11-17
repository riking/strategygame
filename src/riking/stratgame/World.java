package riking.stratgame;

import java.util.ArrayList;

import riking.stratgame.Team;
import riking.stratgame.tiles.Tile;

public class World {
	public Tile[][] tiles;
	public int sizeX;
	public int sizeY;
	public TickTaskScheduler scheduler;
	public static World instance;
	public boolean theGameMustGoOn;
	
	public long tick = 0;
	public ArrayList<Team> teams = new ArrayList<Team>(2);
	
	public static World getWorld() { return instance; }
	public World setGlobal() {
		instance = this;
		return this;
	}
	public static TickTaskScheduler getScheduler() { return instance.scheduler; }
	
	public World(int size)
	{
		this(size,size);
	}
	public World(int sizeX, int sizeY)
	{
		scheduler = new TickTaskScheduler();
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
		theGameMustGoOn = true;
	}
	
	public Tile getTile(int posX, int posY)
	{
		return tiles[posX][posY];
	}
	public World replaceTile(Tile newTile)
	{
		tiles[newTile.posX][newTile.posY] = newTile;
		return this;
	}
	public World addTeam(Team t)
	{
		teams.add(t);
		return this;
	}
}
