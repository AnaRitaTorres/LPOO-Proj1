package maze.logic;

import java.util.ArrayList;
import maze.cli.Interface;
import maze.logic.CharacterState.characterState;
import maze.logic.GameState.gameState;


public class Play 
{
	private Interface i = new Interface();
	private Maze maze = new Maze();
	private Hero h = new Hero(0,0,'H');
	private Weapon w = new Weapon (0,0,'E');
	private gameState gameType;	
	private ArrayList<Dragon> dragons = new ArrayList<Dragon>();
	
	public Play (char[][] lab, gameState state)
	{
		this.maze = new Maze(lab);
		this.h = maze.getMazeHero();
		this.w = maze.getMazeWeapon();
		this.dragons = maze.getMazeDragons();
		this.gameType = state;
		
	}
	
	public Play()
	{
		
	}
	
	public Maze getLab()
	{
		return this.maze;
	}
	
	public boolean pointEquals(Point p1, Point p2)
	{
		if(p1.getX() == p2.getX() && p1.getY() == p2.getY())
			return true;
		else
			return false;
	}
	
	public Hero getHero()
	{
		return h;
	}
	
	public Dragon getDragon()
	{
		return dragons.get(0);
	}
	
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
	
	public gameState getGameType()
	{
		return gameType;
	}
	
	public void setState(gameState g)
	{
		gameType = g;
	}
	
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
	
	public boolean sleepMove()
	{
		if((int)(Math.random() * 2) == 1)
		{
			
			return false;
		}
		else
			return true;
	}
	
	public boolean didntCompleteMission()
	{
		if (maze.aliveDragon()==true)
		{
			return false;
		}
		else return true;
	}
	
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
