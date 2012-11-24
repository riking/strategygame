
package riking.stratgame;

import java.io.IOException;

import riking.stratgame.tasks.Task;

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
	/**
	 * Displays a menu with some choices, after repainting the game.
	 * @param choices The choices to prompt the player with.
	 * @param toPrompt The player being prompted.
	 * @return The index into {@code(choices)} that was chosen.
	 */
	public int displayInGameMenu(String[] choices, Team toPrompt)
	{
		ANSI.cls();
		paintBase();
		paintTeamBorder(toPrompt);
		paintStatus(toPrompt);
		paintFutureTasks();
		paintMenuBase(choices.length);
		try
		{
			return System.in.read();
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	public void paintBase()
	{
		ANSI.cls();
		ANSI.color(7,0);
		/*for(int i = 1; i<24; i++)
		{
			output("                                                                                ");
		}*/
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
		ANSI.color(7,0);
		int tco = t.getColor();
		ANSI.moveCursor(1,1);
		ANSI.color(7,tco);
		output("                              ");
		ANSI.moveCursor(1,21);
		output("                              ");
		for(int i=2; i<21; i++)
		{
			ANSI.moveCursor(1,i);
			output(" ");
			ANSI.moveCursor(30,i);
			ANSI.color(7,tco);
			output(" ");
		}
	}
	public void paintStatus(Team t)
	{
		World world = World.getWorld();
		int tco = t.getColor();
		ANSI.color(7,0);
		ANSI.moveCursor(31,1);
		output("+------------------------------------------------+");
		ANSI.moveCursor(31,2);
		output("| Money: ");
		ANSI.color(tco,0);
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
		
		ANSI.moveCursor(31,3);
		output("| Base HP: ");
		for (Team te : world.teams)
		{
			ANSI.color(te.getColor(), 0);
			output(Integer.toString(te.homeBase.health));
			output(" ");
		}
	}
	public void paintFutureTasks()
	{
		
	}
	public void paintMenuBase(int rows)
	{
		ANSI.color(7,0);
		ANSI.moveCursor(31, 15);
		output("+--------------------+------------------------+");
		ANSI.moveCursor(31, 16+rows);
		output("+---------------------------------------------+");
		if (rows > 7)
		{
			for (int i=8; i<=rows; i++)
			{
				ANSI.moveCursor(31, 15+i);
				output("|");
			}
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
			System.out.print(CSI);
			System.out.print(";H");
		}
		static void moveCursor(int hz, int vt)
		{
			System.out.print(CSI);
			System.out.print(vt);
			System.out.print(';');
			System.out.print(hz);
			System.out.print('H');
		}
		static void color(int fore, int back)
		{
			rawstyle(30+fore);
			rawstyle(40+back);
		}
		static void fgColor(int col)
		{
			rawstyle(30+col);
		}
		static void bgColor(int col)
		{
			rawstyle(40+col);
		}
		static void rawstyle(int style)
		{
			System.out.print(CSI);
			System.out.print(Integer.toString(style));
			System.out.print('m');
		}
		static void resetColor() {	rawstyle(0); }
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
