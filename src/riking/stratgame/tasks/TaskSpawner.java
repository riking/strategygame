package riking.stratgame.tasks;

import java.util.Random;

import riking.RandUtils;
import riking.stratgame.TerminalRenderer;
import riking.stratgame.enums.EAtkRange;
import riking.stratgame.enums.EAtkType;
import riking.stratgame.tiles.TileSpawner;
import riking.stratgame.units.Unit;

public class TaskSpawner extends TaskBasic {
	TileSpawner tile;
	public TaskSpawner(TileSpawner tile, long time) {
		super(time);
		this.tile = tile;
	}
	public TaskSpawner(TileSpawner tile, long time, boolean inFuture) {
		super(time,inFuture);
		this.tile = tile;
	}
	@Override
	public void run() {
		tile.myteam.addMoney(250);
		tile.world.scheduler.add(new TaskSpawner(tile,1000));
		
		int menuchoice = tile.screen.displayInGameMenu(new String[] {
				"Spawn new Unit (400)",
				"Spawn Custom Unit (750+)",
				"Pass"
		}, tile.myteam);
		switch(menuchoice)
		{
		case 1:
			if (!tile.containsUnit())
			{
				Unit u = createBasicUnit();
				u.deploy(tile.posX, tile.posY);
				tile.world.scheduler.add(new TaskUnitWakeup(u,100));
			}
			break;
		case 2:
			break;
		case 3:
			return;
			// No break needed
		}
	}
	public Unit createBasicUnit()
	{
		Random r = RandUtils.getRandom();
		Unit u = new Unit(tile.myteam,tile.world)
				.setAttackRange(EAtkRange.get(RandUtils.weightedChoice(new double[] {0.6, 0.1, 0.1, 0.1})))
				.setAttackType(EAtkType.get(r.nextInt(EAtkType.SIZE)))
				.setMoveSpeed((long)RandUtils.randNorm(1000, 150))
				.setAttackSpeed((long)RandUtils.randNorm(900,200))
				.setMaxHealth((int)RandUtils.randNorm(20, 5))
				.setSight((int)RandUtils.randNorm(2.5, 0.5))
				.setDefenses(new double[] {
						RandUtils.randNorm(1, 0.25),
						RandUtils.randNorm(1, 0.25),
						RandUtils.randNorm(1, 0.25),
				});
		return u;
	}
}
