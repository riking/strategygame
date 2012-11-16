public class TerminalRenderer
{
	// windows terminal default size 80 wide 25 high
	// gnome terminal 80x40?
	
	// ANSI.moveCursor(h,v)
	// ANSI.cls()
	// ANSI.color(bool bright, int color)
	// ANSI.color(bool bright, char color)
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
	public void paintPrePrompt(Team t)
	{
		int tco = t.getColor();
		ANSI.moveCursor(33,1);
		ANSI.resetStyle();
		output("Money: ");
		ANSI.color(tco);
		String cash = Integer.toString(t.getMoney());
		if cash.len
		output(cashout);
		
		ANSI.moveCursor(33,2);
		ANSI.resetStyle();
		output("Base HP: ");
		ANSI.color(tco);
		
		for (int i=0; i < World.teamCount; i++)
		{
			
		}
	}
	public static class ANSI
	{
		public static void cls()
		{
			
		}
		public static void moveCursor(int hz, int vt)
		{
			
		}
		public static void color(int col)
		{
			
		}
		public static void resetStyle()
		{
			
		}
	}
}
/*
*****************************************************************************
cccccccccccccccccccccccccccccc  Money: xxxxxxxxxxx
c+--+--+--+--+--+--+--+--+--+c  Base HP: xxxx/xxxx
c|  |  |  |  |  |  |  |  |  |c  
c+--+--+--+--+--+--+--+--+--+c  Unit Info (Friendly):
c|  |  |  |  |  |  |  |  |  |c   HP: xxxxx
c+--+--+--+--+--+--+--+--+--+c   Attack: xxxxx
c|  |  |  |  |  |  |  |  |  |c   Atk Clr: RGB
c+--+--+--+--+--+--+--+--+--+c   Def: (R)xxx% (G)xxx% (B)xxx%
c|  |  |  |  |  |  |  |  |  |c   Move Speed: xxxx (T+xxxx)
c+--+--+--+--+--+--+--+--+--+c   Attk Speed: xxxx (T+xxxx)
c|  |  |  |  |  |  |  |  |  |c 
c+--+--+--+--+--+--+--+--+--+c
c|  |  |  |  |  |  |  |  |  |c
c+--+--+--+--+--+--+--+--+--+c
c|  |  |  |  |  |  |  |  |  |c
c+--+--+--+--+--+--+--+--+--+c
c|  |  |  |  |  |  |  |  |  |c
c+--+--+--+--+--+--+--+--+--+c
c|  |  |  |  |  |  |  |  |  |c
c+--+--+--+--+--+--+--+--+--+c
cccccccccccccccccccccccccccccc



*******************************************************************************
*/