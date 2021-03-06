//Authors: Peter Hanson and Desone Burns

//The purpose of this class is to provide a blueprint to create Map objects which contain information
//about the state of the game's map such as the position of the hunter, pits, wumpus, etc.


package huntTheWumpus;

import java.awt.Point;
import java.util.Random;

public class Map 
{
	private Room[][] map;
	private Point wumpus; //the x is the vertical variable (0-vertSize) and the y is the horizontal variable (0-horizSize)
	private Point hunter; //this is backwards from cartesian system but all the code was already in that orientation
	private Point[] pit;
	public static int horizSize = 10;
	public static int vertSize = 10;
	private Point arrow;
	
	Map()
	{
		map = new Room[vertSize][horizSize];
		Random randy = new Random();
		pit = new Point[randy.nextInt(3) + 3]; //Generates random amount of pits, from 3-5
		
		hunter = new Point(randy.nextInt(vertSize), randy.nextInt(horizSize)); //Puts hunter in random location
		wumpus = new Point(randy.nextInt(vertSize), randy.nextInt(horizSize)); //Puts wumpus in random location
		
		for(int i = 0; i < pit.length; i++) 
		
			pit[i] = new Point(randy.nextInt(vertSize), randy.nextInt(horizSize));
		
		while(inRange(wumpus, hunter, 3)) //If the generated wumpus coords are less than 2 blocks away, re-randomize them.
			wumpus = new Point(randy.nextInt(vertSize), randy.nextInt(horizSize));
		int i = 0;
		for(Point point : pit) //Makes sure all the generated pits aren't sitting on top of each other or other objects (not including blood, goop, or slime)
		{
			boolean tooClose = false;
			for(int j = 0; j < i; j++)
				if(inRange(point, pit[j], 1))
					tooClose = true;
			int endless = 0;
			while(inRange(point, hunter, 2) || inRange(point, wumpus, 1) || tooClose)
			{
				point = new Point(randy.nextInt(vertSize), randy.nextInt(horizSize));
				pit[i] = point;
				tooClose = false;
				for(int j = 0; j < i; j++)
					if(inRange(point, pit[j], 1))
						tooClose = true;
				endless++;
				if(endless == 20)
				{
					hunter = new Point(randy.nextInt(vertSize), randy.nextInt(horizSize));
					wumpus = new Point(randy.nextInt(vertSize), randy.nextInt(horizSize));
				}
			}
			
			i++;
		}
	
		i = 0;
		for(int j = 0; j < vertSize; j++) //Set all the attributes for all the "rooms." 
		{
			for(i = 0; i < horizSize; i++)
			{
				map[j][i] = new Room();
				Point temp = new Point(j, i);
				if(hunter.equals(temp))
				{
					map[j][i].enableHunter();
					map[j][i].enableExplored();
				}
				if(wumpus.equals(temp)) 
				{
					map[j][i].enableWumpus();
				}
				if(inRange(temp, wumpus, 2))
				{
					map[j][i].enableBlood();
				}
				for(Point point : pit)
				{
					if(point.equals(temp))
						map[j][i].enablePit();
					if(inRange(temp, point, 1))
						map[j][i].enableSlime();
				}
			}
		}
	}
	
	
	Map(Point hunter, Point wumpus, Point[] pit)
	{
		map = new Room[vertSize][horizSize];
		this.hunter = hunter;
		this.wumpus = wumpus;
		arrow = hunter;
		for(int j = 0; j < vertSize; j++)
		{
			for(int i = 0; i < horizSize; i++)
			{
				map[j][i] = new Room();
				Point temp = new Point(j, i);
				if(hunter.equals(temp))
				{
					map[j][i].enableHunter();
					map[j][i].enableExplored();
				}
				if(wumpus.equals(temp)) 
				{
					map[j][i].enableWumpus();
				}
				if(inRange(temp, wumpus, 2))
				{
					map[j][i].enableBlood();
				}
				for(Point point : pit)
				{
					if(point.equals(temp))
						map[j][i].enablePit();
					if(inRange(temp, point, 1))
						map[j][i].enableSlime();
				}
			}
		}
	}
	
	public String toString() //Generates the string that is the map
	{
		String output = "";
		for(int i = 0; i < 30; i++)
			System.out.println();//Simulates clearing the screen
		for(int j = 0; j < vertSize; j++)
		{
			for(int i = 0; i < horizSize; i++)
			{
				output += "[" + map[j][i].toString() + "]";
			}
			output += "\n";
		}
		return output;
	}
	
	private boolean inRange(Point reference, Point interest, int distance) //Method for seeing if two points are within a certain distance, and wraps around
	{
		if(reference.distance(interest) <= distance) //Checks if the box in inherently close enough
		
			return true;
		
		
		Point temp = new Point(reference.x, reference.y);

		if(vertSize - reference.x <= distance ) //If the point is on either extreme, it simulates it being on the opposite end of the map
			
			temp.x -= vertSize;
		
		else if(reference.x <= distance )
			
			temp.x += vertSize;
		
		if(temp.distance(interest) <= distance)
		
			return true;
		
		
		temp.x = reference.x; //Set the x coordinate back, so that y is not affected by the previous movement of x to the other side
		
		if(horizSize - reference.y <= distance )
			
			temp.y -= horizSize;
		
		else if( reference.y <= distance )
			
			temp.y += horizSize;
		
		if(temp.distance(interest) <= distance)
		
			return true;
		
		return false;	
		
	}
	
	public Point getHunterLocation()
	{
		for(int i = 0; i < vertSize; i++)
		{
			for(int j = 0; j < horizSize; j++)
			{
				if(map[i][j].containsHunter())
					return new Point(i, j);
			}
		}
		return new Point(0,0); //Will never happen, just a default for java to compile properly
	}

	public void updateHunter(Point position, char dir)
	{
		hunter = position;
		for(int i = 0; i < vertSize; i++)
		{
			for(int j = 0; j < horizSize; j++)
			{
				if(i == position.x && j == position.y)
				{
					map[i][j].enableHunter();
					map[i][j].enableExplored();
					map[i][j].setDir(dir);
				}
				else
					map[i][j].disableHunter();
			}
		}
		
	}

	public boolean updateArrow(String direction, Point hunterPosition)
	{
		if(direction == "North" || direction == "South") //If the arrow is shot north or south, its bound to hit the Wumpus, if it has the same y
			if( hunterPosition.y == wumpus.y)
				return true;
		if(direction == "East" || direction == "West")
			if( hunterPosition.x == wumpus.x)
				return true;
		map[hunterPosition.x][hunterPosition.y].enableShotHimself();
		return false;
	}

	public void clearFog()  //Marks all rooms as explored, so everything is visible
	{
		for(int i = 0; i < vertSize; i++)
		{
			for(int j = 0; j < horizSize; j++)
			{
				map[i][j].enableExplored();
			}
		}
	}

	public int roomMessage() //Returns what's in the room, with dangerous objects taking priority
	{
		if(map[hunter.x][hunter.y].containsWumpus())
			return 5;
		if(map[hunter.x][hunter.y].containsPit())
			return 4;
		if(map[hunter.x][hunter.y].containsGoop())
			return 2;
		if(map[hunter.x][hunter.y].containsBlood())
			return 1;
		if(map[hunter.x][hunter.y].containsSlime())
			return 3;
		if(map[hunter.x][hunter.y].containsBat())
			return 6;
		return 0;
	}

	
	public void initialArrow(Point hunterLocation) //Sets the arrow's initial location, which is of course wherever the hunter is
	{
		arrow = hunterLocation;
	}
	
	public boolean animateArrow(String direction) //Gives the arrow some animation, works best in windows console.
	{
		if(direction.equals("North")) //Methods are essentially the same for all directions with different direction and axes 
		{
			arrow = new Point(arrow.x - 1, arrow.y); //Move the arrow each time we call the method
			boolean wrap = false; 
			if(arrow.x < 0) //If the arrow has reached the edge, wrap it
			{
				arrow = new Point(horizSize - 1, arrow.y); 
				wrap = true;
			}
			map[arrow.x][arrow.y].enableExplored(); //Our arrow is flaming, so wherever it has traveled, we have seen
			map[arrow.x][arrow.y].enableVArrow(); //Put an arrow in the room
			if(wrap) //if the arrow has wrapped, disable the only exception
				map[0][arrow.y].disableVArrow();
			else //otherwise, disable the one behind current arrow
				map[arrow.x+1][arrow.y].disableVArrow();

			//if our arrow is in the same place as the Wumpus or the Hunter, end animation
			if((arrow.x == wumpus.x && arrow.y == wumpus.y) || arrow.x == hunter.x) 
			{
				map[arrow.x][arrow.y].disableVArrow();
				return false;
			}
			return true;
		}
		if(direction.equals("South"))
		{
			arrow = new Point(arrow.x + 1, arrow.y);
			boolean wrap = false;
			if(arrow.x >= horizSize )
			{
				arrow = new Point(0, arrow.y);
				wrap = true;
			}
			
			map[arrow.x][arrow.y].enableExplored();
			map[arrow.x][arrow.y].enableVArrow();
			
			if(wrap)
				map[horizSize - 1][arrow.y].disableVArrow();
			else
				map[arrow.x - 1][arrow.y].disableVArrow();

			if((arrow.x == wumpus.x && arrow.y == wumpus.y) || arrow.x == hunter.x)
			{
				map[arrow.x][arrow.y].disableVArrow();
				return false;
			}
			return true;
		}
		if(direction.equals("East"))
		{
			arrow = new Point(arrow.x, arrow.y + 1);
			boolean wrap = false;
			if(arrow.y >=  vertSize )
			{
				arrow = new Point(arrow.x, 0);
				wrap = true;
			}
			
			map[arrow.x][arrow.y].enableExplored();
			map[arrow.x][arrow.y].enableHArrow();
			
			if(wrap)
				map[arrow.x][vertSize - 1].disableHArrow();
			else
				map[arrow.x][arrow.y - 1].disableHArrow();

			if((arrow.x == wumpus.x && arrow.y == wumpus.y) || arrow.y == hunter.y)
			{
				map[arrow.x][arrow.y].disableHArrow();
				return false;
			}
			return true;
		}
		
		if(direction.equals("West"))
		{
			arrow = new Point(arrow.x, arrow.y - 1);
			boolean wrap = false;
			if(arrow.y < 0 )
			{
				arrow = new Point(arrow.x, vertSize - 1);
				wrap = true;
			}
		
			map[arrow.x][arrow.y].enableExplored();
			map[arrow.x][arrow.y].enableHArrow();
		
			if(wrap)
				map[arrow.x][0].disableHArrow();
			else
				map[arrow.x][arrow.y + 1].disableHArrow();

			if((arrow.x == wumpus.x && arrow.y == wumpus.y) || arrow.y == hunter.y)
			{
				map[arrow.x][arrow.y].disableHArrow();
				return false;
			}
			return true;
		}
		return false;
	}
	
	public Room getRoom(Point location)
	{
		return map[location.x][location.y];
	}
		
}