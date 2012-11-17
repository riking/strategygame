package riking.stratgame;

public class TerminalRenderer
{
	// windows terminal default size 80 wide 25 high
	// gnome terminal 80x40?
	
	// ANSI.moveCursor(h,v)
	// ANSI.cls()
	// ANSI.color(boolean bright, int color)
	// ANSI.color(boolean bright, char color)
	// ANSI.color(int color) converts color 0-15 to 0-7+bright
	// ANSI.resetStyle()
	public void paintBase()
	{
		ANSI.cls();
		for(int i=1; i<10; i++)
		{
			ANSI.moveCursor(1,2*i);
			output(" +--+--+--+--+--+--+--+--+--+");
			ANSI.moveCursor(1,2*i+1);
			output(" |  |  |  |  |  |  |  |  |  |");
		}
		ANSI.moveCursor(1,2*10);
		output(" +--+--+--+--+--+--+--+--+--+");
	}
	public void paintTeamBorder(Team t)
	{
		int tco = t.getColor();
		ANSI.moveCursor(1,1);
		ANSI.color(tco);
		output("                              ");
		ANSI.moveCursor(1,21);
		ANSI.color(tco);
		output("                              ");
		for(int i=2; i<20; i++)
		{
			ANSI.moveCursor(1,i);
			ANSI.color(tco);
			output(" ");
			ANSI.moveCursor(30,i);
			ANSI.color(tco);
			output(" ");
		}
	}
	public void paintStatus(Team t)
	{
		World world = World.getWorld();
		int tco = t.getColor();
		ANSI.moveCursor(33,1);
		ANSI.resetStyle();
		output("Money: ");
		ANSI.color(tco);
		String cash = Long.toString(t.getMoney());
		if (cash.length()>11)
		{
			/*123456789012 -> 1.23456e+11*/
			StringBuilder sb = new StringBuilder();
			sb.append(cash.charAt(0));
			sb.append('.');
			sb.append(cash.substring(1, 5));
			sb.append("e+");
			sb.append(cash.length()-1);
		}
		output(cash);
		
		ANSI.moveCursor(33,2);
		ANSI.resetStyle();
		output("Base HP: ");
		ANSI.color(tco);
		
		for (Team te : world.teams)
		{
			
		}
	}
	static void output(String s)
	{
		System.out.print(s);
	}
	static class ANSI
	{
		static final String CSI = "\u001b[";
		static void cls()
		{
			System.out.print(CSI);
			System.out.print("2J");
		}
		static void moveCursor(int hz, int vt)
		{
			
		}
		static void color(int col)
		{
			
		}
		static void resetStyle()
		{
			
		}
	}
}
/*
******************************************************************************
cccccccccccccccccccccccccccccc+--------------------+------------------------+*
c+--+--+--+--+--+--+--+--+--+c| Money: xxxxxxxxxxx |T Money & Spawnxxxx tttt|*
c|  |  |  |  |  |  |  |  |  |c| Base HP: xxxx/xxxx |T xxxxxxxxxxxxxxxxx tttt|*
c+--+--+--+--+--+--+--+--+--+c| Unit Info (Team C) |T                       |*
c|  |  |  |  |  |  |  |  |  |c|  HP: xxxxx         |T                       |*
c+--+--+--+--+--+--+--+--+--+c|  Attack: xxxxx     |T                       |*
c|  |  |  |  |  |  |  |  |  |c|  Atk Type: xxxxxxx |T                       |*
c+--+--+--+--+--+--+--+--+--+c|  Atk Clr: RGB      |T                       |*
c|  |  |  |  |  |  |  |  |  |c|  Def:              |T                       |*
c+--+--+--+--+--+--+--+--+--+c|   (R) xxx%         |T                       |*
c|  |  |  |  |  |  |  |  |  |c|   (G) xxx%         |T                       |*
c+--+--+--+--+--+--+--+--+--+c|   (B) xxx%         |T                       |*
c|  |  |  |  |  |  |  |  |  |c|  Attk Speed: xxxxx |T                       |*
c+--+--+--+--+--+--+--+--+--+c|  Move Speed: xxxxx |T                       |*                        *
c|  |  |  |  |  |  |  |  |  |c+--------------------+------------------------+*
c+--+--+--+--+--+--+--+--+--+c|                                              *
c|  |  |  |  |  |  |  |  |  |c|                                              *
c+--+--+--+--+--+--+--+--+--+c|                                              *
c|  |  |  |  |  |  |  |  |  |c|                                              *
c+--+--+--+--+--+--+--+--+--+c|                                              *
cccccccccccccccccccccccccccccc|                                              *
------------------------------+                                              *
                                                                             *
                                                                             *
*******************************************************************************
*/