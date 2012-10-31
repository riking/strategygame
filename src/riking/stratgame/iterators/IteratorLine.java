package riking.stratgame.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import riking.stratgame.Helper;
import riking.stratgame.Tile;
import riking.stratgame.World;

public class IteratorLine implements Iterator<Tile> {
	private int state = 0;
	private int pBaseX;
	private int pBaseY;
	private int[] direction;
	private World world;
	
	public IteratorLine(Tile base, int dir)
	{
		pBaseX = base.posX;
		pBaseY = base.posY;
		world = World.getWorld();
		this.direction = Helper.sequence[dir];
	}
	public IteratorLine(Tile base, int[] dir)
	{
		if (dir.length != 2)
			throw new IllegalArgumentException(
					"direction vector of wrong length");
		pBaseX = base.posX;
		pBaseY = base.posY;
		world = World.getWorld();
		this.direction = dir;
	}
	public IteratorLine(int posX, int posY, int dir)
	{
		pBaseX = posX;
		pBaseY = posY;
		this.direction = Helper.sequence[dir];
	}
	public IteratorLine(int posX, int posY, int[] dir)
	{
		if (dir.length != 2)
			throw new IllegalArgumentException(
					"direction vector of wrong length");
		pBaseX = posX;
		pBaseY = posY;
		this.direction = dir;
	}
	@Override
	public boolean hasNext() {
		return (pBaseX + direction[0]*(state+1) > world.sizeX)
				|| (pBaseY + direction[1]*(state+1) > world.sizeY);
	}

	@Override
	public Tile next() {
		state++;
		try
		{
			Tile res = world.getTile(
					pBaseX + direction[0]*state,
					pBaseY + direction[1]*state);
			return res;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchElementException();
		}
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
