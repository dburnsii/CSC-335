//Authors: Peter Hanson and Desone Burns

//The purpose of this class is to provide a main method that actually runs the Hunt the Wumpus game using objects
//created from the other classes in this package. The turn based game updates are handled by a while loop. User
//input is received through a Scanner object to determine hunter movement and the current state of the map is
//displayed out to the system in text format.

package huntTheWumpus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.io.IOException;
import java.util.Observable;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Game extends Observable
{
	
	private static Map gameMap; //ALL PUBLIC VARIABLES ARE USED ONLY FOR JUNIT TESTING (Would not appear in real game)
	private boolean pitDeath;
	private boolean wumpusDeath;
	private boolean gameOver;
	private boolean gameWon;
	private boolean buttonPressed;
	private boolean checkedForDeath;
	private boolean arrowDeath;
	private static Hunter hunter;
	private wumpusGUI gui;
	private char dir;
	private String textViewMap;
	private String roomMessageString;

	
	public static void main(String[] args) throws IOException
	{
		new Game();
	}
	
	public Game()
	{
		gameMap = new Map();
		hunter = new Hunter(gameMap.getHunterLocation());

		gameOver = false;
		gameWon = false;
		pitDeath = false;
		wumpusDeath = false;
		buttonPressed = false;
		arrowDeath = false;
		String textMap;
		//Scanner key = new Scanner(System.in);
		char movement;
		int roomMessageCode;
		textViewMap = gameMap.toString();
		
		gui = new wumpusGUI(gameMap, this);
		this.addObserver(gui);
//		// This prints the 'graphic' for the game, before it starts
//		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"); 
//		System.out.println("xx|  |XX|  |x|  |xx|  |x|  \\xx|  |x|       |xxx|        |x|  |XX|  |x|   ___|xx");
//		System.out.println("xx|  |xx|  |x|  |xx|  |x|   \\x|  |x|       |xxx|        |x|  |XX|  |x|  |xxxxxx");
//		System.out.println("xx|        |x|  |xx|  |x|    \\|  |xxx|   |xxxxxxxx|   |xxx|        |x|   __|xxx");
//		System.out.println("xx|   __   |x|  |xx|  |x|  |\\    |xxx|   |xxxxxxxx|   |xxx|   __   |x|  |xxxxxx");
//		System.out.println("xx|  |xx|  |x|        |x|  |x\\   |xxx|   |xxxxxxxx|   |xxx|  |xx|  |x|      |xx");
//		System.out.println("xx|__|xx|__|xx\\______/xx|__|xx\\__|xxx|___|xxxxxxxx|___|xxx|__|xx|__|x|______|xx");
//		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//		System.out.println("xxxx|  |x^x|  |xx|  |xx|  |xxx/\\xxxxx/\\xxx|      \\xxx|  |xx|  |xxx/     \\xxxxxx");
//		System.out.println("xxxx|  |/ \\|  |xx|  |xx|  |xx/  \\xxx/  \\xx|  |x|  |xx|  |xx|  |xx|  /x\\__|xxxxx");
//		System.out.println("xxxx|         |xx|  |xx|  |xx|   \\x/   |xx|  |x|  |xx|  |xx|  |xxx\\___ \\xxxxxxx");
//		System.out.println("xxxx|    /\\   |xx|  |xx|  |xx|    v    |xx|   ___/xxx|  |xx|  |xxxxxxx\\ \\xxxxxx");
//		System.out.println("xxxxx\\  /xx\\  /xx|  |xx|  |xx|  |\\ /|  |xx|  |xxxxxxx|  |xx|  |xx| \\xx/  |xxxxx");
//		System.out.println("xxxxxx\\/xxxx\\/xxxx\\______/xxx|  |xvx|  |xx|__|xxxxxxxx\\______/xxxx\\_____/xxxxxx");
//		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
//
//		// The description of the game, and instructions
//		String desc = "You are a brave hunter searching for the elusive wumpus in a cave containing" 
//				+ "\nbottomless pits and warped space-time properties. Slime is one square away"
//				+ "\nfrom the pits, blood is up to 2 squares away from the wumpus. Goo is the "
//				+ "\nmixture of both. You only have one arrow to shoot, if you miss you are dead."
//				+ "\n\nPress 'u' to move up, 'd' to move down, 'r' to move right, 'l' to move left, \nand 'a' to shoot your arrow. Good luck!\n\n\nPress Enter to play.\n\n";
//		
//		// Makes the characters of the
//		// description appear on the
//		// screen like an rpg, rather
//		// than just showing up
//		for (int i = 0; i < desc.length(); i++) 
//		{
//			System.out.print(desc.charAt(i));
//			wait(50); // Delay between character prints
//		}
//
//		key.nextLine(); // Forces the player to press enter to play
//		textMap = gameMap.toString(); // Prints initial map layout
//		System.out.print(textMap);

		while (!gameOver)
		{
			textViewMap = gameMap.toString();
			// If the player has fallen in a pit, clear the fog
			// and let them know
			// Since the player is "safe", tell them where they are

				roomMessageCode = gameMap.roomMessage();
				switch (roomMessageCode)
				{
				case 0:
					System.out.println("You are in an empty room.");
					roomMessageString = "You are in an empty room.";
					break;
				case 1:
					System.out.println("You are in a room filled with blood.");
					roomMessageString = "You are in a room filled with blood.";
					break;
				case 2:
					System.out.println("You are in a room filled with goop.");
					roomMessageString = "You are in a room filled with goop.";
					break;
				case 3:
					System.out.println("You are in a room filled with slime.");
					roomMessageString = "You are in a room filled with slime.";
					break;
				case 4:
					pitDeath = true;
					gameOver = true;
					break;
				case 5:
					wumpusDeath = true;
					gameOver = true;
					break;
				case 6:
					//Not used in this version
					System.out.println("You are in a room filled with bats."); 
					break;
				}

			buttonPressed = false;
			checkedForDeath = false;
			
			while(!buttonPressed && !gameOver){
				
				// if statement so that we do not check over and over and create lag
				if(!checkedForDeath)
				{
				checkDeath(); //See if player walked into a pit or a wumpus on last move
				checkedForDeath = true;
				}
				//do nothing and wait until button is pressed
				//System.out.print("."); //test purposes
			}
			System.out.println("\nYou moved!"); //for testing purposes
			
			// If the player is still alive, ask them where they want to go
			/*if (!pitDeath && !wumpusDeath) 
			{
				System.out.println("You are facing " + hunter.getOrientation());
				System.out.println("Where would you like to move?");
				movement = key.next().charAt(0);
				switch (movement)
				{
				case 'u':
					hunter.moveUp();
					gameMap.updateHunter(hunter.getPosition() , 'u');
					break;
				case 'd':
					hunter.moveDown();
					gameMap.updateHunter(hunter.getPosition(), 'd');
					break;
				case 'l':
					hunter.moveLeft();
					gameMap.updateHunter(hunter.getPosition(), 'l');
					break;
				case 'r':
					hunter.moveRight();
					gameMap.updateHunter(hunter.getPosition(), 'r');
					break;
				case 'a':
					gameMap.initialArrow(hunter.getPosition());
					while (gameMap.animateArrow(hunter.getOrientation())) //animates the arrow before resolving the result
					{
						System.out.print(gameMap.toString());
						wait(100);
					}
					gameOver = true;
					gameWon = gameMap.updateArrow(hunter.shootArrow(),
							hunter.getPosition());
					break;
				default:
					System.out
							.println("Not a valid key, try again. Pick either u,d,l,r, or a.");
				}
			}

			textMap = gameMap.toString();
			System.out.print(textMap); */
		}

		//key.close();
		
		if (pitDeath)
		{
			gameOver = true;
			gameMap.clearFog();
			textMap = gameMap.toString();
			textViewMap = gameMap.toString();
			//System.out.print(textMap);
			System.out.println("You have fallen into a bottomless pit!! Enviornmental damage too stronk!");
			roomMessageString = "You have fallen into a bottomless pit!! Enviornmental damage too stronk!";
		} 
			// If the player has run into the Wumpus,
			// clear the fog and let them know
		if (wumpusDeath) 
		{
			gameOver = true;
			gameMap.clearFog();
			textMap = gameMap.toString();
			textViewMap = gameMap.toString();
			//System.out.print(textMap);
			System.out
					.println("You come face to face with the Wumpus and barely have time to panic\n"
								+ "before it rips you to pieces!");
			roomMessageString = "You come face to face with the Wumpus and it rips you to pieces!!";
			
		}

		//Player has won, time to celebrate!!
		if (gameWon)
		{
			gameMap.clearFog();
			textMap = gameMap.toString();
			//System.out.print(textMap);
			System.out
					.println("You won! One shot, one kill, no luck, all skill.");
		}
		
		//Player took an arrow to the back. Of the knee. Tell them of their failure.
		if (!gameWon && !wumpusDeath && !pitDeath)
		{
			gameMap.clearFog();
			textMap = gameMap.toString();
			arrowDeath = true;
			System.out
					.println("You lost, you shot yourself in the back with your arrow!");
		}
	}
	
	
	private void drawMap()
	{
		
		for(int i = 0; i < Map.vertSize; i++)
			for(int j = 0; j < Map.horizSize; j++)
			{
				
				ImageIcon empty = new ImageIcon("images/EmptyRoom.png");
				
			}
	}
	
	private void checkDeath(){
		for(int i = 0; i < Map.vertSize; i++)
			for(int j = 0; j < Map.horizSize; j++)
			{
				if(gameMap.getRoom(new Point(i,j)).containsPit() && (i == hunter.getPosition().x) && (j == hunter.getPosition().y))
				{
					pitDeath = true;
					gameOver = true;
				}
				if(gameMap.getRoom(new Point(i,j)).containsWumpus() && (i == hunter.getPosition().x) && (j == hunter.getPosition().y))
				{
					wumpusDeath = true;
					gameOver = true;
				}
			}
	}
	

	public void buttonPress(char dir)
	{
		
		
		this.dir = dir;
		switch (dir)
		{
		case 'u':
			buttonPressed = true;
			hunter.moveUp();
			gameMap.updateHunter(hunter.getPosition(), 'u');
			break;
		case 'd':
			buttonPressed = true;
			hunter.moveDown();
			gameMap.updateHunter(hunter.getPosition() , 'd');
			break;
		case 'l':
			buttonPressed = true;
			hunter.moveLeft();
			gameMap.updateHunter(hunter.getPosition() , 'l');
			break;
		case 'r':
			buttonPressed = true;
			hunter.moveRight();
			gameMap.updateHunter(hunter.getPosition(), 'r');
			break;
		case 'a':
			buttonPressed = true;
			gameMap.initialArrow(hunter.getPosition());
//			while (gameMap.animateArrow(hunter.getOrientation())) //animates the arrow before resolving the result
//			{
//				//System.out.print(gameMap.toString());
//				wait(100);
//			}
			gameOver = true;
			gameWon = gameMap.updateArrow(hunter.shootArrow(),
					hunter.getPosition());
			break;
		default:
			System.out
					.println("Not a valid key, try again. Pick either u,d,l,r, or a.");
		}
		//System.out.println(hunter.getPosition().toString());
		checkDeath();
		setChanged();
		notifyObservers();
	}
	
	public char getDir()
	{
		return dir;
	}
	

	public String getTextViewMap(){
		return textViewMap;
	}
	
	public String getRoomMessageString(){
		return roomMessageString;
	}
	
	public boolean getGameOver()
	{
		return gameOver;
	}
	
	public boolean getWon()
	{
		return gameWon;
	}
	
	public boolean getWumpusDeath()
	{
		return wumpusDeath;
	}
	
	public boolean getPitDeath()
	{
		return pitDeath;
	}
	
	public boolean getArrowDeath()
	{
		return arrowDeath;
	}
	
	// A simplified delay timer, so that the
	// try/catch doesn't have to take up
	// space.
	public static void wait(int time) 
	{
		try
		{
			Thread.sleep(time);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
