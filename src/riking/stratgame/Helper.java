package riking.stratgame;

public class Helper {
	public static final int[][] sequence = new int[][] {
		{1,1}, {1,0}, {1,-1},
		{0,1},        {0,-1},
		{-1,1},{-1,0},{-1,-1}
	};
	public static final int[][] sequenceWithSelf = new int[][] {
		{1,1}, {1,0}, {1,-1},
		{0,1}, {0,0}, {0,-1},
		{-1,1},{-1,0},{-1,-1}
	};
}
