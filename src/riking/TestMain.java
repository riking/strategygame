package riking;

import riking.stratgame.GameStarter;
import riking.stratgame.TerminalRenderer;
import riking.stratgame.World;
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
			TerminalRenderer a = new TerminalRenderer();
			a.paintBase();
			a.paintTeamBorder(World.instance.teams.get(0));
			System.in.read();
			GameStarter.run();
			//World.getScheduler().schedule(BasicTask.class, 50L);
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
	}

}
