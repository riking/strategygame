package riking.stratgame.tasks;

import riking.stratgame.Unit;

public class TaskUnitWakeup extends TaskBasic {

	final Unit myUnit;
	public TaskUnitWakeup(Unit u, long tick)
	{
		super(tick);
		myUnit = u;
	}
	@Override
	public void run() {
		myUnit.attackReady = true;
		//TODO Gui.promptUnitAction(myUnit);
	}

}
