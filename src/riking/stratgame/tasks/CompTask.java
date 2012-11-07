package riking.stratgame.tasks;

import java.util.Comparator;

/**
 * Class to sort Tasks.
 * @author kane
 *
 */
public class CompTask implements Comparator<Task> {

	@Override
	public int compare(Task o1, Task o2) {
		long res = o1.tick - o2.tick;
		if (res < Integer.MAX_VALUE && res > Integer.MIN_VALUE)
			return (int)res;
		else if (res > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		else
			return Integer.MIN_VALUE;
	}

}
