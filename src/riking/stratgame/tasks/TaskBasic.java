package riking.stratgame.tasks;

import riking.stratgame.TickTaskScheduler;

public abstract class TaskBasic extends Task
{
	public TaskBasic(long time, boolean inFuture)
	{
		this.tick = time;
		if(inFuture)
		{	this.tick += TickTaskScheduler.currentTick;
		}
	}
	public TaskBasic(long time)
	{
		this(time,true);
	}
}