package riking;

import java.util.Random;

public class RandUtils
{
	private static RandUtils instance = new RandUtils();
	private Random rand = new Random();
	
	private static RandUtils getInstance() { return instance; }
	/**
	 * Helper function
	 * @return normal random number with mean 0 and standard deviation 1
	 */
	public static double randNorm(double mean, double stdev)
	{
		return getInstance().rand.nextGaussian() * stdev + mean;
	}
	/**
	 * Randomly selects one position in the weights list and returns its index.
	 * @param weights - double array of weights, need not add to 1
	 * @return selected index in weights[]
	 */
	public static int weightedChoice(double[] weights)
	{
		if (weights.length == 0) throw new IllegalArgumentException("array is empty");
		double pSum = 0;
		for(int i=0; i<weights.length; i++)
		{	pSum += weights[i];	}
		double pInd = getInstance().rand.nextDouble() * pSum;
		
		for (int i=0; i<weights.length; i++)
		{
			pInd -= weights[i];
			if (pInd < 0) return i;
		}
		// Edge case
		return weights.length - 1;
	}
	/**
	 * Randomly selects one item from the objects array with weights represented in the weights array.
	 * @param weights - double array of weights, need not add to 1
	 * @param objects - object array to choose from
	 * @return selected item from objects[] 
	 */
	public static <T> T weightedChoice(double[] weights, T[] objects)
	{
		if (weights.length != objects.length)
			throw new ArrayIndexOutOfBoundsException("weightedChoice - dimension mismatch ("+weights.length+" vs "+objects.length+")");
		return objects[weightedChoice(weights)];
	}
}
