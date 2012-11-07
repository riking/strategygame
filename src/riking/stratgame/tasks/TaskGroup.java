package riking.stratgame.tasks;

import java.util.ArrayList;

/**
 * 
 * @author kane
 *
 */
public class TaskGroup extends Task
{
	ArrayList<Task> tasks;
	public TaskGroup(Task origin)
	{
		this.tick = origin.tick;
		tasks = new ArrayList<Task>(3);
		tasks.add(origin);
	}
	/**
	 * Creates a new TaskGroup with the tick defined by the first argument.
	 */
	public TaskGroup(Task a, Task b)
	{
		this.tick = a.tick;
		tasks = new ArrayList<Task>(3);
		tasks.add(a);
		tasks.add(b);
	}
	/**
	 * Adds the task to this TaskGroup, ignoring tick value.
	 */
	public TaskGroup add(Task t)
	{
		tasks.add(t);
		return this;
	}
	@Override public void run()
	{
		for (Task t : tasks)
		{
			t.run();
		}
	}
}