package maze.logic;

import maze.cli.Interface;
import maze.logic.CharacterState.characterState;
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
	
	public void printCell(Point p,char c)
	{
		maze[p.getY()][p.getX()]= c;
	}
	
	public boolean isOutOfBounds(Point p)
	{
		if(p.getY() > maze.length -1  || p.getY() < 0 || p.getX() > maze[0].length -1 || p.getX() < 0)
			return true;
		else
			return false;
	}
	
	public boolean isFree(Point p)
	{
		if(maze[p.getY()][p.getX()] == 'X' || isOutOfBounds(p))
			return false;
		else return true;
	}
	
	public boolean move(movementType mt, Character c)
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
			printCell(s,c.getChar());
			return true;
		}
		else
			return false;
		
	}
	
	public void moveRandom(Character c)
	{
		
		boolean valid_move = false;
		
		while(!valid_move)
		{
			int random = (int )(Math.random() * 4);
			
			switch(random)
			{
			case 0:
				valid_move = move(movementType.UP,c);
				break;
				
			case 1:
				valid_move = move(movementType.DOWN,c);
				break;
				
			case 2:
				valid_move = move(movementType.LEFT,c);
				break;
				
			case 3:
				valid_move = move(movementType.RIGHT,c);
				break;
				
			default:
				break;
				
			}
		}
	}

	public void moveHandler(Character f)
	{
		char c = i.readMove();
		
		switch(c)
		{	
			case'U':
				move(movementType.UP,f);
				break;
				
			case'D':
				move(movementType.DOWN,f);
				break;
				
			case'L':
				move(movementType.LEFT,f);
				break;
				
			case'R':
				move(movementType.RIGHT,f);
				break;
				
			default:
				break;
					
				
		}
	}
	
}
