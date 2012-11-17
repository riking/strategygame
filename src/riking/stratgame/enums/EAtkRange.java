package riking.stratgame.enums;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import riking.stratgame.iterators.IteratorLine;
import riking.stratgame.iterators.IteratorNeighbors;
import riking.stratgame.tiles.Tile;

public enum EAtkRange {
	Melee(IteratorNeighbors.class),
	Burst(IteratorNeighbors.class),
	Range(IteratorLine.class),
	Artillery(IteratorLine.class);
	
	private static final List<EAtkRange> VALUES =
		    Collections.unmodifiableList(Arrays.asList(values()));
	public static final int SIZE = VALUES.size();
	
	private Class<? extends Iterator<Tile>> classIter;
	
	private EAtkRange(Class<? extends Iterator<Tile>> cIterator)
	{
		this.classIter = cIterator;
	}

	public Iterator<Tile> iterator(Object[] args) {
		@SuppressWarnings("unchecked")
		Constructor<Iterator<Tile>>[] cArr = (Constructor<Iterator<Tile>>[]) classIter.getConstructors();
		for (Constructor<Iterator<Tile>> c : cArr)
		{
			try {
				return (Iterator<Tile>) c.newInstance(args);
			} catch (IllegalArgumentException e) {
				continue; // expected
			} catch (ExceptionInInitializerError e) {
				continue; // expected
			} catch (InvocationTargetException e) {
				continue; // expected
			} catch (InstantiationException e) {
				throw new RuntimeException("Unexpected abstract class", e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Unexpected security context",e);
			}
		}
		throw new RuntimeException("suitable iterator not found");
	}
	
	public static EAtkRange get(int i)
	{
		return VALUES.get(i);
	}
}
