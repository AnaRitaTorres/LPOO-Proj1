package maze.logic;

import maze.cli.Interface;
import maze.logic.CharacterState.characterState;


public class Play 
{
	private Interface i = new Interface();
	private Maze maze = new Maze();
	private Hero h = new Hero(1,1,'H');
	private Dragon d = new Dragon (8,1,'D');
	private Weapon w = new Weapon (1, 3, 'E');
	
	public boolean pointEquals(Point p1, Point p2)
	{
		if(p1.getX() == p2.getX() && p1.getY() == p2.getY())
			return true;
		else
			return false;
	}
	
	public boolean isDragonDead()
	{
		if(d.getState() == characterState.DEAD)
		{
			return false;
		}
		
		return true;
	}
	
	public void updateGame()
	{
		int dx = Math.abs(h.getCharacterPosition().getX() - d.getCharacterPosition().getX());
		int dy = Math.abs(h.getCharacterPosition().getY() - d.getCharacterPosition().getY());
		
		if(pointEquals(h.getCharacterPosition(), w.getPosition()))
		{
			h.setState(characterState.ARMED);
			h.setArmed();
			w.eraseWeapon();
			maze.printCell(w.getPosition(), 'A');
			
		}
		
		if(dx <= 1 && dy <= 1)
		{
			System.out.print("\nNigga get in here now\n");
			if(h.getState() == characterState.ALIVE)
			{
				h.setState(characterState.DEAD);
			}
			
			if(h.getState() == characterState.ARMED)
			{
				d.setState(characterState.DEAD);
			}
		}
		
	}
	
	
	public void gamePlay()
	{
		maze.printCell(h.getCharacterPosition(),h.getChar());
		maze.printCell(d.getCharacterPosition(), d.getChar());
		maze.printCell(w.getPosition(), w.getChar());
		
		
		
		
		i.printMaze(maze);
		
		
		
		boolean run = true;
		
		while(run)
		{
			maze.moveHandler(h);
			
			if(d.getState() == characterState.ALIVE)
				maze.moveRandom(d);
			
			updateGame();
			
			i.printMaze(maze);
			
			if(h.getState() == characterState.DEAD)
			{
				run = false;
				System.out.print("ded");
			}
			
		}
	}
	
}
