package riking.stratgame;

import riking.stratgame.enums.EAtkType;
import riking.stratgame.tasks.TaskBasic;

@SuppressWarnings("unused")
public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try
		{
			GameStarter.setup();
			GameStarter.run();
			//World.getScheduler().schedule(BasicTask.class, 50L);
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
	}

}
