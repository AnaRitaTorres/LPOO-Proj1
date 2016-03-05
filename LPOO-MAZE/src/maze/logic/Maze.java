package maze.logic;

import maze.cli.Interface;
import maze.logic.MovementType.movementType;

public class Maze 
{
	
	private char[][] maze;
	private Interface i= new Interface();
	
	public Maze()
	{ 		
		char lab[][] = 
			{
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ','X',' ','S'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}};
	
	maze = lab;
		
	}
	
	public Maze(char[][] maze)
	{
		this.maze = maze;
	}
	
	public char[][] getMaze()
	{
		return maze;
	}
	
	public void clearCell(Point p)
	{
		maze[p.getY()][p.getX()]=' ';
	}
	
	public void printCell(Point p,Character c)
	{
		maze[p.getY()][p.getX()]= c.getChar();
	}
	
	public boolean isFree(Point p)
	{
		if(maze[p.getY()][p.getX()]=='X')
			return false;
		else return true;
	}
	
	public void move(movementType mt, Character c)
	{
		Point s = new Point (0,0) ;
		
		switch(mt)
		{
		case UP:
			s.setY(c.getCharacterPosition().getY()-1);
			s.setX(c.getCharacterPosition().getX());
			break;
		
		case DOWN:
			s.setY(c.getCharacterPosition().getY()+1);
			s.setX(c.getCharacterPosition().getX());
			break;
		
		case LEFT:
			s.setY(c.getCharacterPosition().getY());
			s.setX(c.getCharacterPosition().getX()-1);
			break;
			
		case RIGHT:
			s.setY(c.getCharacterPosition().getY());
			s.setX(c.getCharacterPosition().getX()+1);
			break;
			
		default:
			s.setY(c.getCharacterPosition().getY());
			s.setX(c.getCharacterPosition().getX());
			break;
		}
		
		if(isFree(s)== true)
		{
			clearCell(c.getCharacterPosition());
			c.setCharacterPosition(s);
			printCell(s,c);
		}
		
	}

	public void moveHandler(Character f)
	{
		char c = i.readMove();
		
		switch(c)
		{	
			case'U':
				move(movementType.UP,f);
			case'D':
				move(movementType.DOWN,f);
			case'L':
				move(movementType.LEFT,f);
			case'R':
				move(movementType.RIGHT,f);
		}
	}
}
