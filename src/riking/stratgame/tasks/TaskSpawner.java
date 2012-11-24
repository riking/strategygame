package riking.stratgame.tasks;

import java.util.Random;

import riking.RandUtils;
import riking.stratgame.enums.EAtkRange;
import riking.stratgame.enums.EAtkType;
import riking.stratgame.menus.MenuChoice;
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
		boolean redo = true;
		while(redo)
		{
			redo = false;
			int menuchoice = tile.screen.displayInGameMenu(new MenuChoice[] {
					new MenuChoice("Spawn new Unit (400)",(!tile.containsUnit()) && (tile.myteam.hasMoney(400))),
					new MenuChoice("Spawn Custom Unit (500+)",(!tile.containsUnit()) && (tile.myteam.hasMoney(500))),
					new MenuChoice("Pass"),
			}, tile.myteam, tile);
			switch(menuchoice)
			{
			case 1:
				if(tile.myteam.spendMoney(400))
				{
					Unit u = createBasicUnit();
					u.deploy(tile.posX, tile.posY);
					tile.world.scheduler.add(new TaskUnitWakeup(u,100));
				}
				else
				{
					tile.screen.displayModalInfoBox("An error has occured, you do not have enough money. Unit creation was aborted.");
				}
				break;
			case 2:
				tile.screen.displayModalInfoBox("Test.");
				break;
			case 3:
				tile.screen.displayModalInfoBox("This is a little bit long test.");
				return;
				// No break needed
			}
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
