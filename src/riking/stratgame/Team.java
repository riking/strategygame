package riking.stratgame;

import java.util.ArrayList;

import riking.stratgame.tiles.TileSpawner;

public class Team {
	public int id;
	public String name;
	public char ident;
	/**
	 * Temporary variable for console printing. Will be changed to a Color vector.
	 */
	public int color;
	
	protected long money = 0;
	public ArrayList<Team> allies;
	protected TileSpawner homeBase;
	
	public Team(int id, String name, int color)
	{
		this(id,name,name.charAt(0),color);
	}
	public Team(int id, String name, char ident, int color)
	{
		this.id = id;
		this.name = name;
		this.ident = ident;
		this.color = color;
		allies = new ArrayList<Team>(1);
	}
	/**
	 * Temporary method for console printing. Will be changed to a Color vector.
	 * @return ANSI color code integer, >16 for bright
	 */
	public int getColor()
	{
		return color;
	}
	
	public boolean isOpposedTo(Team other)
	{
		return !allies.contains(other);
	}
	public boolean isAlliedTo(Team other)
	{
		return (this == other) || (allies.contains(other));
	}
	public long getMoney() { return money; }
	public long addMoney(long am) { money += am; return money; }
	public long addMoney(int am) { money += am; return money; }
	/**
	 * Tries to spend money, returns false if impossible.
	 * @param cost How much money is needed
	 * @return Whether the purchase completed.
	 */
	public boolean spendMoney(int cost)
	{
		if (money >= cost)
		{
			money -= cost;
			return true;
		}
		return false;
	}
	/**
	 * Tries to spend money, returns false if impossible.
	 * @param cost How much money is needed
	 * @return Whether the purchase completed.
	 */
	public boolean spendMoney(long cost)
	{
		if (money >= cost)
		{
			money -= cost;
			return true;
		}
		return false;
	}
	public void setBase(TileSpawner t)
	{
		this.homeBase = t;
	}
	public TileSpawner getBase()
	{
		return homeBase;
	}
}
