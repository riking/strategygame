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
	public int hlHealth;
	
	public int posX;
	public int posY;
	//public HashMap<String,Integer> queuedActions = new HashMap<String, Integer>();
	public long tLastMovement;
	public long tNextMovement;
	public long tLastAttack;
	public long tNextAttack;
	public long tWaitTime;
	public long tNextUpdate;
	public Unit() {
		
	}
	public Tile getCurrentTile()
	{
		return World.getWorld().getTile(posX, posY);
	}
	/**
	 * note: should get some actual dynamic logic in here
	 */
	public void refreshUpdateTimes() {
		tNextAttack = tLastAttack + tAttackSpeed;
		tNextMovement = tLastMovement + tSpeed;
		if (tWaitTime != 0) {
			tNextUpdate = tWaitTime;
			return;
		}
		tNextUpdate = ( tNextAttack < tNextMovement ) ? tNextAttack : tNextMovement;
	}
}
