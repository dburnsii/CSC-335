//Authors: Peter Hanson and Desone Burns

//The purpose of this class is to provide a blueprint to create Hunter objects which contain information
//about the state of the hunter such as his position, orientation, if he has an arrow ready, and how he should move.

package huntTheWumpus;

import java.awt.Point;

public class Hunter {

	private Point position; //The current position of the hunter
	private boolean arrow; //True if the hunter still has his arrow ready
	private String orientation; //North, South, East, or West. Matters for arrow path.
	
	public Hunter(Point initialLocation){
		position = initialLocation;
		arrow = true;
		orientation = "North";
	}
	
	public Point getPosition()
	{
		return position;
	}
	
	public String getOrientation()
	{
		return orientation;
	}
	
	public void moveUp()
	{
		position.x = (position.x - 1 + Map.vertSize) % Map.vertSize;
		orientation = "North";
	}
	
	public void moveDown()
	{
		position.x = (position.x + 1) % Map.vertSize;
		orientation = "South";
	}
	
	public void moveLeft()
	{
		position.y = (position.y - 1 + Map.horizSize) % Map.horizSize;
		orientation = "West";
	}
	
	public void moveRight()
	{
		position.y = (position.y + 1) % Map.horizSize;
		orientation = "East";
	}

	public String shootArrow()
	{
		arrow = false;
		return orientation;
		
	}

	public boolean hasArrow() {
		return arrow;
	}
	
}
