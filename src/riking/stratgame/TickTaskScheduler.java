package riking.stratgame;

import java.util.TreeSet;

import riking.stratgame.tasks.CompTask;
import riking.stratgame.tasks.Task;
import riking.stratgame.tasks.TaskGroup;

public class TickTaskScheduler {
	public TreeSet<Task> tasks;
	public static long currentTick;
	public TickTaskScheduler()
	{
		tasks = new TreeSet<Task>(new CompTask());
		currentTick = -1;
	}
	public void tick(long t)
	{
		currentTick = t;
		Task next = tasks.first();
		if(next == null)
		{
			new Error("No more tasks to run!").printStackTrace();
			World.getWorld().theGameMustGoOn = false;
			return;
		}
		System.out.println("tick "+Long.toString(t)+" (next "+Long.toString(tasks.first().tick)+")");
		if (next.tick < t)
		{
			System.err.println("WARNING: executing early tasks");
			next = doPriorTasks(t); // Will return next task to run
		}
		if (next.tick > t) return;
		next.run();
		assert(tasks.remove(next));
	}
	private Task doPriorTasks(long untilTick)
	{
		Task next = tasks.first();
		while(next.tick < untilTick)
		{
			if(next.postExecuteBehavior != Task.PEBehavior.ALLOW_POST_EXECUTE)
			{
				throw new IllegalStateException("Post-executed task");
			}
			next.run();
			if (!tasks.remove(next))
				throw new AssertionError();
			next = tasks.first();
		}
		return next; // return for chaining purposes
	}
	/**
	 * Alias for add()
	 * @param newTask The task to run.
	 */
	public void schedule(Task newTask)
	{
		add(newTask);
	}
	/**
	 * Adds a task to the queue to run later. Assumes the tick is already set.
	 * @param t
	 */
	public void add(Task newTask)
	{
		if(tasks.contains(newTask)) // If a task already occupies that tick
		{
			TaskGroup tg = new TaskGroup(tasks.floor(newTask));
			tg.add(newTask);
			tasks.remove(newTask);
			tasks.add(tg);
		}
		else
		{
			tasks.add(newTask);
		}
	}
	
}
