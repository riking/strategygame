package riking.stratgame.units;

import riking.stratgame.Team;
import riking.stratgame.World;
import riking.stratgame.enums.EAtkRange;
import riking.stratgame.enums.EAtkType;
import riking.stratgame.tiles.Tile;

public class Unit implements Targetable {
	public Team team;
	public World world;
	public EAtkType eAttackType;
	public EAtkRange eAttackRange;
	public double[] defenses;
	public int sqSight = 2;
	public long tSpeed;
	public long tAttackSpeed;
	public int health;
	public int maxHealth;
	public int attackStrength = 5;
	
	public int posX;
	public int posY;
	public boolean attackReady;

	public Unit(Team team, World world)
	{
		this.team = team;
		this.world = world;
	}
	@Override
	public Team getTeam()
	{
		return team;
	}
	@Override
	public boolean isOpposedTo(Team t)
	{
		return team.isOpposedTo(t);
	}
	public Tile getCurrentTile()
	{
		return World.getWorld().getTile(posX, posY);
	}
	/**
	 * Places the unit on the map.
	 * @return the unit itself, for chaining
	 */
	public Unit deploy(int posX,int posY)
	{
		world.getTile(posX, posY).moveUnitIn(this);
		return this;
	}
	public Unit setAttackType(EAtkType type) { eAttackType = type; return this; }
	public Unit setAttackRange(EAtkRange range) { eAttackRange = range; return this; }
	public Unit setRedDefense(double defense) { defenses[0] = defense; return this; }
	public Unit setGreenDefense(double defense) { defenses[1] = defense; return this; }
	public Unit setBlueDefense(double defense) { defenses[2] = defense; return this; }
	public Unit setDefenses(double[] defense) { defenses = defense; return this; }
	public Unit setSight(int sight) { sqSight = sight; return this; }
	public Unit setMoveSpeed(long speed) { tSpeed = speed; return this; }
	public Unit setAttackSpeed(long speed) { tAttackSpeed = speed; return this; }
	public Unit setMaxHealth(int hp) { maxHealth = hp; health = hp; return this; }
}
