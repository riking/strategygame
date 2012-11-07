package riking.stratgame;

import riking.stratgame.enums.EAtkRange;
import riking.stratgame.enums.EAtkType;

public class Unit {
	public Team team;
	public EAtkType eAttackType;
	public EAtkRange eAttackRange;
	public double[] ratDefenses;
	public int sqSight;
	public long tSpeed;
	public long tAttackSpeed;
	public int health;
	
	public int posX;
	public int posY;
	public boolean attackReady;
	
	public Unit() {
		
	}
	public Tile getCurrentTile()
	{
		return World.getWorld().getTile(posX, posY);
	}
	
}
