package maze.logic;

import maze.cli.Interface;
import maze.logic.CharacterState.characterState;

public class Play 
{
	private Interface i = new Interface();
	private Maze maze = new Maze();
	private Hero h = new Hero(1,1,'H');
	private Dragon d = new Dragon (8,1,'D');
	
	public boolean isDragonDead()
	{
		if(d.getState() == characterState.DEAD)
		{
			return false;
		}
		
		return true;
	}
	
	
	
	
	public void gamePlay()
	{
		maze.printCell(h.getCharacterPosition(),h);
		maze.printCell(d.getCharacterPosition(), d);
		i.printMaze(maze);
		
		while(true)
		{
			maze.moveHandler(h);
			maze.moveRandom(d);
			i.printMaze(maze);
		}
	}
	
}
