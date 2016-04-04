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
	
	/** The i. */
	private Interface i = new Interface();
	
	/** The maze. */
	private Maze maze = new Maze();
	
	/** The h. */
	private Hero h;
	
	/** The w. */
	private Weapon w;
	
	/** The game type. */
	private gameState gameType;	
	
	/** The dragons. */
	private ArrayList<Dragon> dragons = new ArrayList<Dragon>();
	
	/**
	 * Instantiates a new play.
	 *
	 * @param lab the lab
	 * @param state the state
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
	 * Gets the lab.
	 *
	 * @return the lab
	 */
	public Maze getLab()
	{
		return this.maze;
	}
	
	/**
	 * Point equals.
	 *
	 * @param p1 the p1
	 * @param p2 the p2
	 * @return true, if successful
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
	 * Check armed.
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
	 * Update game.
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
	 * @return true, if successful
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
	 * Didnt complete mission.
	 *
	 * @return true, if successful
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
	 * Game play.
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
	 * Game play gui.
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
		
//		i.printMaze(maze);
//		
//		if(h.getState() == characterState.DEAD )
//		{
//			setState(gameType.LOST);
//			System.out.print("\nYou're Dead!\n");
//			
//		}
//		
//		if(!maze.aliveDragon() && pointEquals(maze.getOut(), h.getCharacterPosition()))
//		{
//			setState(gameType.WON);
//			System.out.print("\nYou Won The Game!\n");
//		}
	}
	
	public Weapon getWeapon()
	{
		return w;
	}
	
	public Interface getInterface()
	{
		return i;
	}

}
