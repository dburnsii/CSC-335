//Authors: Peter Hanson and Desone Burns

//The purpose of this class is to provide a main method that actually runs the Hunt the Wumpus game using objects
//created from the other classes in this package. The turn based game updates are handled by a while loop. User
//input is received through a Scanner object to determine hunter movement and the current state of the map is
//displayed out to the system in text format.

package huntTheWumpus;

import java.io.IOException;
import java.util.Scanner;

public class Game {
	
	
	public static void main(String[] args) throws IOException{
		
		Map gameMap = new Map();
		Hunter hunter = new Hunter(gameMap.getHunterLocation());
		
		
		boolean gameOver = false;
		boolean gameWon = false;
		boolean pitDeath = false;
		boolean wumpusDeath = false;
		String textMap;
		String logo;
		Scanner key = new Scanner(System.in);
		String movement;
		int roomMessageCode;
		
		logo = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n" + 
"xxxx|  |XX|  |xx|  |xx|  |xx|  \\xx|  |xx|       |xxxx|        |xx|  |XX|  |xx|   ___|xxxx\n" +
"xxxx|  |xx|  |xx|  |xx|  |xx|   \\x|  |xx|       |xxxx|        |xx|  |XX|  |xx|  |xxxxxxxx\n" + 
"xxxx|        |xx|  |xx|  |xx|    \\|  |xxxx|   |xxxxxxxxx|   |xxxx|        |xx|   __|xxxxx\n" +
"xxxx|   __   |xx|  |xx|  |xx|  |\\    |xxxx|   |xxxxxxxxx|   |xxxx|   __   |xx|  |xxxxxxxx\n" +
"xxxx|  |xx|  |xx|        |xx|  |x\\   |xxxx|   |xxxxxxxxx|   |xxxx|  |xx|  |xx|      |xxxx\n" +
"xxxx|__|xx|__|xxx\\______/xxx|__|xx\\__|xxxx|___|xxxxxxxxx|___|xxxx|__|xx|__|xx|______|xxxx\n" + 
"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n" +
"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n" +
"xxxxxxxx|  |x^x|  |xx|  |xx|  |xxx/\\xxxxx/\\xxx|      \\xxx|  |xx|  |xxx/     \\xxxxxxxxxxxx\n" +
"xxxxxxxx|  |/ \\|  |xx|  |xx|  |xx/  \\xxx/  \\xx|  |x|  |xx|  |xx|  |xx|  /x\\__|xxxxxxxxxxx\n" +
"xxxxxxxx|         |xx|  |xx|  |xx|   \\x/   |xx|  |x|  |xx|  |xx|  |xxx\\___ \\xxxxxxxxxxxxx\n" +
"xxxxxxxx|    /\\   |xx|  |xx|  |xx|    v    |xx|   ___/xxx|  |xx|  |xxxxxxx\\ \\xxxxxxxxxxxx\n" +
"xxxxxxxxx\\  /xx\\  /xx|  |xx|  |xx|  |\\ /|  |xx|  |xxxxxxx|  |xx|  |xx| \\xx/  |xxxxxxxxxxx\n" +
"xxxxxxxxxx\\/xxxx\\/xxxx\\______/xxx|  |xvx|  |xx|__|xxxxxxxx\\______/xxxx\\_____/xxxxxxxxxxxx\n" +
"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n";
		
		System.out.println(logo);
		
		String desc = "You are a brave hunter searching for the elusive wumpus in a cave containing bottomless pits" +
				"\nand warped space-time properties. Slime is one square away from the pits, blood is up to 2 "
				+ "\nsquares away from the wumpus. Goo is the mixture of both. You only have one arrow to shoot, "
				+ "\nif you miss you are dead." + 
				"\n\nPress 'u' to move up, 'd' to move down, 'r' to move right, 'l' to move left, and 'a' to \nshoot your arrow. Good luck!\n\n\nPress Enter to play.\n\n";
		for(int i = 0; i < desc.length(); i++)
		{
			System.out.print(desc.charAt(i));
			wait(50); //Delay between character prints
		}
		
		key.nextLine();
		
		while(!gameOver)
		{

			
			//this is my attempt at clearing the text in the console. In theory, it should work for the windows 7 command line at least, but does nothing in Eclipse
			//Feel free to remove.
//			String OS = System.getProperty("os.name");
//			//Check to see if returned String contains "Windows"
//			System.out.println(OS);
//			if (OS.contains("Windows") == true)
//				Runtime.getRuntime().exec("cmd /c cls");
//			else
//				Runtime.getRuntime().exec("clear");
			
			
			textMap = gameMap.toString();
			System.out.print(textMap);
			
			roomMessageCode = gameMap.roomMessage();
			
			switch(roomMessageCode)
			{
			case 0: System.out.println("You are in an empty room.");
					break;
			case 1: System.out.println("You are in a room filled with blood.");
					break;
			case 2: System.out.println("You are in a room filled with goop.");
					break;
			case 3: System.out.println("You are in a room filled with slime.");
					break;
			case 4: System.out.println("You have fallen into a bottomless pit!! Enviornmental damage too stronk!");
					pitDeath = true;
					break;
			case 5: System.out.println("You come face to face with the Wumpus and barely have time to panic\n"
					+ "before it rips you to pieces!");
					wumpusDeath = true;
					break;
			case 6: System.out.println("You are in a room filled with bats."); //Not used in this version
					break;
			}
					
			if(pitDeath || wumpusDeath)
			{
				gameOver = true;
				gameMap.clearFog();
				textMap = gameMap.toString();
				System.out.print(textMap);
				break;
			}
			
			System.out.println("You are facing " + hunter.getOrientation());
			System.out.print("Where would you like to move?");
			movement = key.next();
			
			switch(movement)
			{
			case "u": hunter.moveUp();
					  gameMap.updateHunter(hunter.getPosition());
					  break;
			case "d": hunter.moveDown();
			          gameMap.updateHunter(hunter.getPosition());
			          break;
			case "l": hunter.moveLeft();
			          gameMap.updateHunter(hunter.getPosition());
			          break;
			case "r": hunter.moveRight();
			          gameMap.updateHunter(hunter.getPosition());
			          break;
			case "a": gameMap.initialArrow(hunter.getPosition());
					  while(gameMap.animateArrow(hunter.getOrientation()))
					  {
							System.out.print(gameMap.toString());
					    	wait(100);
					  }
					  gameOver = true;  
					  gameWon = gameMap.updateArrow(hunter.shootArrow(), hunter.getPosition());  
			          break;
			default:  System.out.println("Not a valid key, try again. Pick either u,d,l,r, or a.");  
			}
		}
		
		
		key.close();
		
		if(gameWon){
			gameMap.clearFog();
			textMap = gameMap.toString();
			System.out.print(textMap);
			System.out.println("You won! One shot, one kill, no luck, all skill.");
		}
		if(!gameWon && !wumpusDeath && !pitDeath)
		{
			gameMap.clearFog();
			textMap = gameMap.toString();
			System.out.print(textMap);
			System.out.println("You lost, you shot yourself in the back with your arrow!");
		}
	}
	
	public static void wait(int time)
	{
		try 
		{
			Thread.sleep(time);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}