package riking.stratgame;

public class World {
	public Tile[][] tiles;
	public int sizeX;
	public int sizeY;
	public static World instance;
	
	public static World getWorld() { return instance; }
	public World setGlobal() {
		instance = this;
		return this;
	}
	
	public World(int size)
	{
		sizeX = size;
		sizeY = size;
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
	public void replaceTile(Tile newTile, int posX, int posY)
	{
		tiles[posX][posY] = newTile;
	}
}
