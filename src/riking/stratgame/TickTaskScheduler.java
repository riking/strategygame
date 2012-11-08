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
		Task next = tasks.first()
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
	private void doPriorTasks(long untilTick)
	{
		Task next = tasks.first();
		while(next.tick < untilTick)
		{
			next.run();
			assert(tasks.remove(next));
			next = tasks.first();
		}
		return next; // return for chaining purposes
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
	 * remember to put the delay as the first argument!
	 */
	public void schedule(Class<? extends Task> taskClass, Object... args)
	{
		try {
			args[0] += currentTick;
		} catch (Throwable t)
		{
			throw new IllegalArgumentException("first argument of args must be a tick delay", t);
		}
		try {
			Task newTask = taskClass.getConstructor(args).newInstance(args);
			add(newTask);
		}
		catch (SecurityException impossible)
		{ throw new RuntimeException(impossible); }
		catch (IllegalAccessException impossible)
		{ throw new RuntimeException(impossible); }
		catch (InstantiationException e)
		{ throw new RuntimeException("Attempt to construct an abstract Task"), e); }
		catch (InvocationTargetException e)
		{ throw new RuntimeException("An error occured when dynamically creating a Task"), e); }
		catch (NoSuchMethodException, IllegalArgumentException e)
		{
			throw new IllegalArgumentEx
		}
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
