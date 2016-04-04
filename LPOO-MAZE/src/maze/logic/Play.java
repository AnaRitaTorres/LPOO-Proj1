package maze.logic;

import java.util.ArrayList;
import maze.cli.Interface;
import maze.logic.CharacterState.characterState;
import maze.logic.GameState.gameState;


// TODO: Auto-generated Javadoc
/**
 * The Class Play.
 */
public class Play 
{
	
	/** The interface. */
	private Interface i = new Interface();
	
	/** The maze. */
	private Maze maze = new Maze();
	
	/** The hero. */
	private Hero h;
	
	/** The weapon. */
	private Weapon w;
	
	/** The game type. */
	private gameState gameType;	
	
	/** The dragons. */
	private ArrayList<Dragon> dragons = new ArrayList<Dragon>();
	
	/**
	 * Instantiates a new play.
	 *
	 * @param lab the labyrinth
	 * @param state the state of the game
	 */
	public Play (char[][] lab, gameState state)
	{
		this.maze = new Maze(lab);
		this.h = maze.getMazeHero();
		this.w = maze.getMazeWeapon();
		this.dragons = maze.getMazeDragons();
		this.gameType = state;
		
	}
	
	/**
	 * Instantiates a new play.
	 */
	public Play()
	{
		h = new Hero(1, 1, 'H');
		w = new Weapon(1, 2, 'E');
		dragons.add( new Dragon(8, 1, 'D'));
		
	}
	
	/**
	 * Gets the labyrinth.
	 *
	 * @return the labyrinth
	 */
	public Maze getLab()
	{
		return this.maze;
	}
	
	/**
	 * Tests if points are equal.
	 *
	 * @param p1 the point
	 * @param p2 the point
	 * @return true, if successful, otherwise false
	 */
	public boolean pointEquals(Point p1, Point p2)
	{
		if(p1.getX() == p2.getX() && p1.getY() == p2.getY())
			return true;
		else
			return false;
	}
	
	/**
	 * Gets the hero.
	 *
	 * @return the hero
	 */
	public Hero getHero()
	{
		return h;
	}
	
	/**
	 * Gets the dragon.
	 *
	 * @return the dragon
	 */
	public Dragon getDragon()
	{
		return dragons.get(0);
	}
	
	/**
	 * Checks if the hero is armed or disarmed and verifies if the state of the hero needs to be changed.
	 */
	public void checkArmed()
	{
		if (h.getState()== characterState.ARMED)
			return;
		if(pointEquals(h.getCharacterPosition(), w.getPosition()))
		{
			h.setState(characterState.ARMED);
			h.setArmed();
			w.eraseWeapon();
			maze.printCell(w.getPosition(), 'A');
			
		}
		else
		{
			h.setState(characterState.DISARMED);
			h.setDisarmed();
		}
	}
	
	/**
	 * Updates the states of the dragon and the hero.
	 */
	public void updateGame()
	{
		checkArmed();
		for(int i = 0; i < dragons.size(); i++)
		{
			int dx = Math.abs(h.getCharacterPosition().getX() - dragons.get(i).getCharacterPosition().getX());
			int dy = Math.abs(h.getCharacterPosition().getY() - dragons.get(i).getCharacterPosition().getY());
			
			
			
			if(dx <= 1 && dy <= 1)
			{
				if(h.getState()== characterState.DISARMED)
				{
					h.setState(characterState.DEAD);
				}
				
				if(h.getState() == characterState.ARMED && dragons.get(i).getState()== characterState.ALIVE)
				{
					dragons.get(i).setState(characterState.DEAD);
					maze.clearCell(dragons.get(i).getCharacterPosition());
				}
			}
		}
		
	}
	
	/**
	 * Gets the game type.
	 *
	 * @return the game type
	 */
	public gameState getGameType()
	{
		return gameType;
	}
	
	/**
	 * Sets the state.
	 *
	 * @param g the new state
	 */
	public void setState(gameState g)
	{
		gameType = g;
	}
	
	/**
	 * Game state handler.
	 */
	public void gameStateHandler()
	{
		char c = i.readGameState();
		switch(c)
		{
		case 'T':
			gameType = gameState.STATIC;
			break;
			
		case 'S':
			gameType = gameState.SLEEP;
			break;
			
		case 'R':
			gameType = gameState.RANDOM;
			break;
			
		default:
			gameStateHandler();
		}
	}
	
	/**
	 * Sleep move.
	 *
	 * @return true, if successful,otherwise false
	 */
	public boolean sleepMove()
	{
		if((int)(Math.random() * 2) == 1)
		{
			
			return false;
		}
		else
			return true;
	}
	
	/**
	 * Didn't complete mission, the hero can't get out of the maze if he hasn't killed the dragons.
	 *
	 * @return true, if successful, otherwise false
	 */
	public boolean didntCompleteMission()
	{
		if (maze.aliveDragon()==true)
		{
			return false;
		}
		else return true;
	}
	
	/**
	 * Logic of the game.
	 */
	public void gamePlay()
	{
		maze.printCell(h.getCharacterPosition(),h.getChar());
		
		for(int i = 0; i < dragons.size(); i++)
		{
			maze.printCell(dragons.get(i).getCharacterPosition(), dragons.get(i).getChar());
		}
		
		maze.printCell(w.getPosition(), w.getChar());
		
		gameStateHandler();
		
		i.printMaze(maze);
		
		
		
		while(gameType!=gameState.WON && gameType!=gameState.LOST)
		{
			maze.moveHandler(h);
			
			for(int i = 0; i < dragons.size(); i++)
			{
				if(dragons.get(i).getState() == characterState.ALIVE && gameType == gameState.SLEEP)
					if(sleepMove())
					{
						dragons.get(i).setChar('D');
						maze.moveRandom(dragons.get(i));
					}
						
					else
						dragons.get(i).setChar('d');
			}
			
			for(int i = 0; i < dragons.size(); i++)
				if(dragons.get(i).getState() == characterState.ALIVE && gameType == gameState.RANDOM)
					maze.moveRandom(dragons.get(i));
			
			
			maze.printCell(w.getPosition(), w.getChar());
			
			updateGame();
			
			for(int i = 0; i < dragons.size(); i++)
				maze.dragonWeapon(dragons.get(i), w);
			
			for(int i = 0; i < dragons.size(); i++)
				if(dragons.get(i).getState()== characterState.ALIVE)
				{
					maze.printCell(dragons.get(i).getCharacterPosition(), dragons.get(i).getChar());
				}
			
			i.printMaze(maze);
			
			if(h.getState() == characterState.DEAD )
			{
				setState(gameType.LOST);
				System.out.print("\nYou're Dead!\n");
				
			}
			
			if(!maze.aliveDragon() && pointEquals(maze.getOut(), h.getCharacterPosition()))
			{
				setState(gameType.WON);
				System.out.print("\nYou Won The Game!\n");
			}
			
		}
	}
	
	/**
	 * Sets the weapon.
	 *
	 * @param r the new weapon
	 */
	public void setWeapon(Weapon r)
	{
		this.w=r;
	}
	
	/**
	 * Game logic concerning the gui.
	 */
	public void gamePlayGui()
	{
		for(int i = 0; i < dragons.size(); i++)
		{
			if(dragons.get(i).getState() == characterState.ALIVE && gameType == gameState.SLEEP)
				if(sleepMove())
				{
					dragons.get(i).setChar('D');
					maze.moveRandom(dragons.get(i));
				}
					
				else
					dragons.get(i).setChar('d');
		}
		
		for(int i = 0; i < dragons.size(); i++)
			if(dragons.get(i).getState() == characterState.ALIVE && gameType == gameState.RANDOM)
				maze.moveRandom(dragons.get(i));
		
		updateGame();
		
		maze.printCell(w.getPosition(), w.getChar());
		maze.printCell(h.getCharacterPosition(), h.getChar());
		
		for(int i = 0; i < dragons.size(); i++)
			if(dragons.get(i).getState()== characterState.ALIVE)
			{
				maze.printCell(dragons.get(i).getCharacterPosition(), dragons.get(i).getChar());
			}
		
	
		
		for(int i = 0; i < dragons.size(); i++)
			maze.dragonWeapon(dragons.get(i), w);
		
	}
	
	/**
	 * Gets the weapon.
	 *
	 * @return the weapon
	 */
	public Weapon getWeapon()
	{
		return w;
	}
	
	/**
	 * Gets the interface.
	 *
	 * @return the interface
	 */
	public Interface getInterface()
	{
		return i;
	}

}
