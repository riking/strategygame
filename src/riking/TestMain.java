package riking;

import riking.stratgame.GameStarter;
import riking.stratgame.World;
import riking.stratgame.enums.EAtkType;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try
		{
			GameStarter.run();
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
	}

}
