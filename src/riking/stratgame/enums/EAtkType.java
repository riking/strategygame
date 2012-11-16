package riking.stratgame.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum EAtkType {
	Annn(false,false,false),
	Arnn(true, false,false),
	Angn(false,true, false),
	Annb(false,false,true ),
	Argn(true, true ,false),
	Arnb(true, false,true ),
	Angb(false,true, true ),
	Argb(true, true, true );
	private boolean red;
	private boolean green;
	private boolean blue;
	private int count;
	
	private static final List<EAtkType> VALUES =
		    Collections.unmodifiableList(Arrays.asList(values()));
	public static final int SIZE = VALUES.size();
	
	private EAtkType(boolean r, boolean g, boolean b)
	{
		red = r;
		green = g;
		blue = b;
		count = (red?1:0) + (green?1:0) + (blue?1:0);
	}
	
	public boolean isRed() 	{	return red;	}
	public boolean isGreen() 	{	return green;	}
	public boolean isBlue() 	{	return blue;	}
	public int getColorCount()	{	return count;	}
	
	public static EAtkType get(int i)
	{
		return VALUES.get(i);
	}
	public double resolveDefenses(double[] defense)
	{
		double[] dCopy = defense.clone();
		double sum = 0d;
		if (red)	sum += dCopy[0];
		if (green)	sum += dCopy[1];
		if (blue)	sum += dCopy[2];
		return sum / count;
	}
}
