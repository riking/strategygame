package riking.stratgame.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import riking.stratgame.Helper;
import riking.stratgame.Tile;
import riking.stratgame.World;

public class IteratorNeighbors implements Iterator<Tile> {
	private int state = 0;
	private int pBaseX;
	private int pBaseY;
	private World world;
	
	public IteratorNeighbors(Tile base)
	{
		pBaseX = base.posX;
		pBaseY = base.posY;
		world = World.getWorld();
	}
	public IteratorNeighbors(int posX, int posY)
	{
		pBaseX = posX;
		pBaseY = posY;
	}
	@Override
	public boolean hasNext() {
		return state < 9;
	}

	@Override
	public Tile next() {
		try
		{
			Tile res = world.getTile(
					pBaseX + Helper.sequence[state][0],
					pBaseY + Helper.sequence[state][1]);
			state++;
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
