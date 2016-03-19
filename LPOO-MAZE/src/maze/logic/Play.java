package maze.logic;

import maze.cli.Interface;
import maze.logic.CharacterState.characterState;
import maze.logic.GameState.gameState;


public class Play 
{
	private Interface i = new Interface();
	private Maze maze = new Maze();
	private Hero h = new Hero(1,1,'H');
	private Dragon d = new Dragon (8,1,'D');
	private Weapon w = new Weapon (1,2,'E');
	private gameState gameType;	
	
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
		return d;
	}
	
	public boolean isDragonDead()
	{
		if(d.getState() == characterState.DEAD)
		{
			return false;
		}
		
		return true;
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
		int dx = Math.abs(h.getCharacterPosition().getX() - d.getCharacterPosition().getX());
		int dy = Math.abs(h.getCharacterPosition().getY() - d.getCharacterPosition().getY());
		
		checkArmed();
		
		if(dx <= 1 && dy <= 1)
		{
			if(h.getState()== characterState.DISARMED)
			{
				h.setState(characterState.DEAD);
			}
			
			if(h.getState() == characterState.ARMED && d.getState()== characterState.ALIVE)
			{
				d.setState(characterState.DEAD);
				maze.clearCell(d.getCharacterPosition());
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
		if((int )(Math.random() * 2) == 1)
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
		maze.printCell(d.getCharacterPosition(), d.getChar());
		maze.printCell(w.getPosition(), w.getChar());
		
		gameStateHandler();
		
		i.printMaze(maze);
		
		
		
		while(gameType!=gameState.OVER)
		{
			maze.moveHandler(h);
			
			if(d.getState() == characterState.ALIVE && gameType == gameState.SLEEP)
				if(sleepMove())
				{
					d.setChar('D');
					maze.moveRandom(d);
				}
					
				else
					d.setChar('d');
					
			
			if(d.getState() == characterState.ALIVE && gameType == gameState.RANDOM)
				maze.moveRandom(d);
			
			
			maze.printCell(w.getPosition(), w.getChar());
			
			updateGame();
			maze.dragonWeapon(d, w);
			
			if(d.getState()== characterState.ALIVE)
			{
				maze.printCell(d.getCharacterPosition(), d.getChar());
			}
			
			i.printMaze(maze);
			
			if(h.getState() == characterState.DEAD )
			{
				setState(gameType.OVER);
				System.out.print("\nYou're Dead!\n");
				
			}
			
			
			if (d.getState()==characterState.DEAD && pointEquals(maze.getOut(), h.getCharacterPosition()))
			{
				setState(gameType.OVER);
				System.out.print("\nYou Won The Game!\n");
				
			}
			
		}
	}
	
}
