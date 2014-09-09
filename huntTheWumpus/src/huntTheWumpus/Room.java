//Authors: Peter Hanson and Desone Burns

//The purpose of this class is to provide a blueprint to create Room objects which contain information
//about the state of the rooms in the game's map such as if it contains the hunter, pits, wumpus, goo, etc.



package huntTheWumpus;

public class Room 
{
	private boolean blood;
	private boolean slime;
	private boolean goop;
	private boolean wumpus;
	private boolean hunter;
	private boolean bat;
	private boolean explored;
	private boolean pit;
	private boolean vArrow;
	private boolean hArrow;
	
	Room()
	{
		blood = false;
		slime = false;
		goop = false;
		wumpus = false;
		hunter = false;
		bat = false;
		explored = false;  //CHANGE THIS TO MAKE THINGS GO (AWAY)
	}
	
	public void disableElse()
	{
		slime = false;
		goop = false;
		blood = false;
		bat = false;
	}
	
	public void enableBlood()
	{
		if(slime)
		{
			goop = true;
			slime = false;
			return;
		}
		blood = true;
	}
	
	public void enableSlime()
	{
		if(blood)
		{
			goop = true;
			blood = false;
			return;
		}
		slime = true;
	}
	
	public void enablePit()
	{
		pit = true;
		disableElse();
	}
	
	public void enableWumpus()
	{
		wumpus = true;
		disableElse();
	}
	
	public void enableExplored()
	{
		explored = true;
	}
	
	public void enableBat()
	{
		bat = true;
	}
	
	public void enableHunter()
	{
		hunter = true;
	}
	
	public void disableHunter()
	{
		hunter = false;
	}
	
	public void enableVArrow()
	{
		vArrow = true;
	}
	
	public void enableHArrow()
	{
		hArrow = true;
	}
	public void disableVArrow()
	{
		vArrow = false;
	}
	
	public void disableHArrow()
	{
		hArrow = false;
	}
	
	public String toString() //Generates the letter that represents the room
	{
		if(explored)
			if(hunter)
				return "O";
			else if(vArrow)
				return "|";
			else if(hArrow)
				return "-";
			else if(wumpus)
				return "W";
			else if(pit)
				return "P";
			else if(blood)
				return "B";
			else if(slime)
				return "S";
			else if(goop)
				return "G";
			else
				return " ";
		else
			return "X";
	}

	public boolean containsHunter() {
		if(hunter)
			return true;
		return false;
	}

	public boolean containsWumpus() {
		if(wumpus)
			return true;
		return false;
	}

	public boolean containsPit() {
		if(pit)
			return true;
		return false;
	}
	
	public boolean containsBlood() {
		if(blood)
			return true;
		return false;
	}
	
	public boolean containsSlime() {
		if(slime)
			return true;
		return false;
	}
	
	public boolean containsGoop() {
		if(goop)
			return true;
		return false;
	}

	public boolean containsBat() {
		if(bat)
			return true;
		return false;
	}
}
