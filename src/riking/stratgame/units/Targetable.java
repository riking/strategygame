package riking.stratgame.units;

import riking.stratgame.Team;

public interface Targetable {
	public Team getTeam();
	public boolean isOpposedTo(Team other);
	
}
