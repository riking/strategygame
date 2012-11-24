package riking.stratgame.menus;

public class MenuChoice {
	String name = "";
	boolean isValid = true;
	public MenuChoice(String name)
	{
		this.name = name;
	}
	public MenuChoice(String name, boolean valid)
	{
		this.name = name;
		this.isValid = valid;
	}
	public String getName()
	{
		return name;
	}
	public boolean isValid()
	{
		return isValid;
	}
}
