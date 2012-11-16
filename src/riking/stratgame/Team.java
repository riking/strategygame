package riking.stratgame;

import java.util.ArrayList;

public class Team {
	public int id;
	public String name;
	public char ident;
	
	protected long money = 0;
	public ArrayList<Team> allies;
	
	public Team(int id, String name)
	{
		this(id,name,name.charAt(0));
	}
	public Team(int id, String name, char ident)
	{
		this.id = id;
		this.name = name;
		this.ident = ident;
		allies = new ArrayList<Team>(1);
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
	
}
