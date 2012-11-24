package riking.stratgame;

import java.io.IOException;

import riking.stratgame.menus.MenuChoice;
import riking.stratgame.tiles.Tile;

public class TerminalRenderer
{
	// terminal default size 80 wide 24 high
	public void paintBase()
	{
		ANSI.cls();
		resetColorBlack();
		for(int i = 1; i<24; i++)
		{
			output("                                                                                ");
		}
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
	public void paintBoard()
	{
		paintBase();
		resetColorBlack();
		for (int j=0; j<World.getWorld().sizeY; j++)
		{
			for (int i=0; i<World.getWorld().sizeX; i++)
			{
				// TODO
			}
		}
	}
	public void paintTeamBorder(Team t)
	{
		int tco = t.getColor();
		ANSI.moveCursor(1,1);
		ANSI.color(7,tco);
		output("                              ");
		ANSI.moveCursor(1,21);
		output("                              ");
		for(int i=2; i<21; i++)
		{
			ANSI.moveCursor(1,i);
			output(' ');
			ANSI.moveHz(30);
			output(' ');
		}
		resetColorBlack();
	}
	public void paintStatus(Team t)
	{
		World world = World.getWorld();
		int tco = t.getColor();
		resetColorBlack();
		ANSI.moveCursor(31,1);
		output("+------------------------------------------------+");
		ANSI.moveCursor(31,2);
		output("| Money: ");
		ANSI.color(tco,0);
		ANSI.bold();
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
		resetColorBlack();
		ANSI.moveCursor(31,3);
		output("| Base HP: ");
		ANSI.bold();
		for (Team te : world.teams)
		{
			ANSI.color(te.getColor(), 0);
			output(Integer.toString(te.homeBase.health));
			output(' ');
		}
		resetColorBlack();
	}
	public void paintFutureTasks()
	{
		// TODO
	}
	public void paintMenuBase(int rows)
	{
		resetColorBlack();
		ANSI.moveCursor(31, 15);
		output("+------------------------------------------------+");
		ANSI.moveCursor(31, 16+rows);
		output("+------------------------------------------------+");
		for (int i=0; i<rows; i++)
		{
			ANSI.moveCursor(31, 16+i);
			output('|');
			ANSI.moveHz(80);
			output('|');
		}
	}
	public void doHighlight(Tile t)
	{
		int basex = t.posX*3;
		int basey = t.posY*2;
		ANSI.moveCursor(basex+2, basey+2);
		resetColorBlack();
		ANSI.rawstyle(5); // Slow Blink
		ANSI.rawstyle(7); // Inverse Colors
		output("+--+");
		ANSI.moveCursor(basex+2, basey+3);
		output("|");
		ANSI.moveHz(basex+5);
		output("|");
		ANSI.moveCursor(basex+2, basey+4);
		output("+--+");
		resetColorBlack();
	}
	static void output(String s)
	{
		System.out.print(s);
	}
	static void output(char c)
	{
		System.out.print(c);
	}
	static void output(StringBuilder sb)
	{
		System.out.print(sb);
	}
	static void resetColorBlack()
	{
		ANSI.resetColor();
		ANSI.color(7, 0);
	}
	/**
	 * Displays a menu with some choices, after repainting the game.
	 * @param choices The choices to prompt the player with.
	 * @param toPrompt The player being prompted.
	 * @return The index into {@code(choices)} that was chosen.
	 */
	public int displayInGameMenu(MenuChoice[] choices, Team toPrompt)
	{
		return displayInGameMenu(choices,toPrompt,null);
	}
	/**
	 * Displays a menu with some choices, after repainting the game.
	 * @param choices The choices to prompt the player with.
	 * @param toPrompt The player being prompted.
	 * @param highlight The tile on the board to highlight.
	 * @return The index into {@code(choices)} that was chosen.
	 */
	public int displayInGameMenu(MenuChoice[] choices, Team toPrompt, Tile highlight)
	{
		ANSI.cls();
		paintBoard();
		paintTeamBorder(toPrompt);
		paintStatus(toPrompt);
		paintFutureTasks();
		paintMenuBase(choices.length);
		if (highlight != null) doHighlight(highlight);
		resetColorBlack();
		for(int i=0; i<choices.length; i++)
		{
			ANSI.moveCursor(32, 16+i);
			if (choices[i].isValid())
			{
				ANSI.bold();
				output(Integer.toString(i+1));
				resetColorBlack();
				output(' ');
			}
			else
			{
				output("X ");
			}
			output(choices[i].getName());
		}
		while(true)
		{
			try
			{
				ANSI.moveCursor(1, 23);
				int ch = Integer.parseInt(java.awt.event.KeyEvent.getKeyText(System.in.read()));
				ch = (ch == 0) ? (10) : (ch);
				if (ch <= choices.length)
				{
					if (choices[ch-1].isValid())
					{
						// flush input
						for (;System.in.available()!=0;System.in.read()) {}
						return ch;
					}
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
				System.exit(250);
			}
			catch (NumberFormatException e)
			{
				// Ignore
			}
		}
	}
	public void displayModalInfoBox(String text)
	{
		StringBuilder border = new StringBuilder("+");
		StringBuilder prompt = new StringBuilder("|Press ENTER...");
		// TODO: Find a better way to do this
		int len = text.length();
		len = (len < 14) ? 14 : len;
		for (int herp=0; herp<len; herp++) border.append('=');
		for (int derp=14; derp<len; derp++) prompt.append(' ');
		border.append('+');
		prompt.append('|');
		ANSI.moveCursor(8, 3);
		ANSI.rawcode('K'); // Clear Line
		output(border);
		ANSI.moveCursor(8, 6);
		ANSI.rawcode('K'); // Clear Line
		output(border);
		ANSI.moveCursor(8, 4);
		ANSI.rawcode('K'); // Clear Line
		output("|");
		output(text);
		ANSI.moveHz(9+len);
		output("|");
		ANSI.moveCursor(8, 5);
		ANSI.rawcode('K'); // Clear Line
		output(prompt);
		
		try
		{
			System.in.read();
			for (;System.in.available()!=0;System.in.read()) {}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	static class ANSI
	{
		static final String CSI = "\u001b[";
		static void cls()
		{
			rawcode(2,'J');
			rawcode('H');
		}
		static void moveCursor(int hz, int vt)
		{
			rawcode(vt,hz,'H');
		}
		static void moveHz(int col)
		{
			rawcode(col,'G');
		}
		static void color(int fore, int back)
		{
			rawstyle(30+fore); // Foreground Color code
			rawstyle(40+back); // Background Color code
		}
		static void fgColor(int col)
		{
			rawstyle(30+col); // Foreground Color code
		}
		static void bgColor(int col)
		{
			rawstyle(40+col); // Background Color code
		}
		static void bold()
		{
			rawstyle(1); // Bold
		}
		static void resetColor() {
			rawstyle(0); // Style Reset
		}
		static void rawstyle(int style)
		{
			rawcode(style,'m');
		}
		static void rawcode(char code)
		{
			System.out.print(CSI);
			System.out.print(code);
		}
		static void rawcode(int data, char code)
		{
			System.out.print(CSI);
			System.out.print(Integer.toString(data));
			System.out.print(code);
		}
		static void rawcode(int data1, int data2, char code)
		{
			System.out.print(CSI);
			System.out.print(data1);
			System.out.print(';');
			System.out.print(data2);
			System.out.print(code);
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
