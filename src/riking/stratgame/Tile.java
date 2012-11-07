package riking.stratgame;

public class Tile {
	public int posX;
	public int posY;
	public Unit unit;
	
	public Tile(int posx, int posy)
	{
		posX = posx;
		posY = posy;
	}
	
	public void moveUnitIn(Unit u)
	{
		unit = u;
	}
	public Unit moveUnitOut()
	{
		Unit tmp = unit;
		unit = null;
		return tmp;
	}
	
	public boolean containsTarget(Team attacker)
	{
		if (unit == null) return false;
		if (attacker.isAlliedTo(unit.team)) return false;
		return true;
	}
}
