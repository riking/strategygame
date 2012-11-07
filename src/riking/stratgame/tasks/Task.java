package riking.stratgame.tasks;

public abstract class Task implements Runnable, Comparable<Task>
{
	public long tick;
	@Override public int compareTo(Task t)
	{ return (int)(tick - t.tick); }
	@Override public boolean equals(Object o)
	{
		if (o instanceof Task)
		{
			return this.tick == ((Task)o).tick;
		}
		else if (o instanceof Long)
		{
			return tick == ((Long)o).longValue();
		}
		return false;
	}
	/**
	 * Executes the Task.
	 */
	public abstract void run();
}