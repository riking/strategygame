package riking.stratgame.tasks;

public final class TaskStub extends BasicTask
{
	public TaskStub(long tick) {
		super(tick);
	}

	@Override public void run()
	{
		throw new RuntimeException("TaskStub invoked! This is a bug!");
	}
}