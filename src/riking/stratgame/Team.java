package riking.stratgame;

public class Team {
	public int id;
	public String name;
	public char ident;
	public Team(int id, String name)
	{
		this(id,name,name.charAt(0));
	}
	public Team(int id, String name, char ident)
	{
		this.id = id;
		this.name = name;
		this.ident = ident;
	}
	public boolean isOpposedTo(Team other)
	{
		return this.id != other.id; // temp definition
	}
	public boolean isAlliedTo(Team other)
	{
		return this.id == other.id; // temp definition
	}
}
