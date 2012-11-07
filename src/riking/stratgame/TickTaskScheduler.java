package riking.stratgame;

import java.util.TreeSet;

import riking.stratgame.tasks.CompTask;
import riking.stratgame.tasks.Task;
import riking.stratgame.tasks.TaskGroup;
import riking.stratgame.tasks.TaskStub;

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
		System.out.println("tick "+Long.toString(t)+" (next "+Long.toString(tasks.first().tick));
		Task temp = new TaskStub(t);
		if(tasks.contains(temp))
		{
			Task toDo = tasks.ceiling(temp);
			toDo.run();
			tasks.remove(toDo);
		}
		if(tasks.isEmpty())
		{
			new Error("No more tasks to run!").printStackTrace();
			World.getWorld().theGameMustGoOn = false;
		}
	}
	/**
	 * Adds a task to run <i>delay</i> ticks later.
	 * (Note: This will set the tick value of the task. The task object should not be reused.)
	 * @param newTask The task to run.
	 * @param delay How many ticks from now the task should run.
	 */
	public void schedule(Task newTask, long delay)
	{
		newTask.tick = currentTick + delay;
		add(newTask);
	}
	/**
	 * Adds a task to the queue to run later. Assumes the tick is already set.
	 * @param t
	 */
	public void add(Task newTask)
	{
		System.out.println(tasks.contains(newTask));
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
