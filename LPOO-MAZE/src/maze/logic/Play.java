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
		int dx = Math.abs(h.getCharacterPosition().x - d.getCharacterPosition().x);
		int dy = Math.abs(h.getCharacterPosition().y - d.getCharacterPosition().y);
		
		if(h.getCharacterPosition() == w.getPosition())
		{
			h.setState(characterState.ARMED);
			h.setArmed('A');
			w.eraseWeapon();
		}
		
		if(dx == 1 || dy == 1)
		{
			System.out.print("next");
			if(h.getState() == characterState.ALIVE)
			{
				h.setState(characterState.DEAD);
			}
			
			if(h.getState() == characterState.ARMED)
			{
				System.out.print("kill");
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
			maze.moveRandom(d);
			
			updateGame();
			
			i.printMaze(maze);
			System.out.print(h.getCharacterPosition().x);
			System.out.print(h.getCharacterPosition().y);
			if(h.getState()== characterState.DEAD)
			{
				run = false;
				System.out.print("ded");
			}
			
		}
	}
	
}
