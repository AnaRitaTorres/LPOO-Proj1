package maze.logic;

import java.util.ArrayList;

import maze.cli.Interface;
import maze.logic.MovementType.movementType;
import maze.logic.Point;

public class Maze 
{

	private char[][] maze;
	private Interface i= new Interface();
	private Point out;

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
		this.out= getExit();

	}

	public Maze(char[][] maze)
	{
		this.maze = maze;
		this.out= getExit();
	}

	public char[][] getMaze()
	{
		return maze;
	}

	public Point getOut()
	{
		return out;
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

	public boolean isCellFree(Point p)
	{
		if(maze[p.getY()][p.getX()] == 'X' || isOutOfBounds(p))
			return false;
		if (aliveDragon()&& maze[p.getY()][p.getX()] == 'S')
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

		if(isCellFree(s)== true)
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
			int random = (int)(Math.random() * 4);

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

	public boolean pointEquals(Point p1, Point p2)
	{
		if(p1.getX() == p2.getX() && p1.getY() == p2.getY())
			return true;
		else
			return false;
	}

	public Point getExit()
	{
		Point p = new Point (0,0);

		for(int i=0; i < maze.length; i++)
		{
			for (int j=0; j < maze[i].length; j++)
			{
				if (maze[i][j]== 'S')
				{
					p.setX(j);
					p.setY(i);
				}
			}	
		}

		return p;
	}

	public boolean aliveDragon()
	{
		boolean c = false;

		for(int i=0; i < maze.length; i++)
		{
			for (int j=0; j < maze[i].length; j++)
			{
				if (maze[i][j]== 'D' || maze[i][j]== 'd' || maze[i][j]== 'F')
					c=true;
			}	
		}

		return c;
	}

	public void dragonWeapon(Dragon d,Weapon w)
	{
		if(pointEquals(w.getPosition(),d.getCharacterPosition()))
		{
			printCell(w.getPosition(), 'F');
		}

	}

	public ArrayList<Dragon> getMazeDragons() // guarda todos os dragões da maze
	{
		ArrayList<Dragon> collect = new ArrayList<Dragon>();

		for(int i=0; i < maze.length; i++)
		{
			for (int j=0; j < maze[i].length; j++)
			{
				if (maze[i][j]== 'D')
				{
					collect.add(new Dragon(j,i,'D'));
				}
			}
		}
		
		return collect;
	}

	public Hero getMazeHero()
	{
		Hero hero = new Hero(0,0,'H');
		
		for(int i=0; i < maze.length; i++)
		{
			for (int j=0; j < maze[i].length; j++)
			{
				if (maze[i][j]== 'H')
				{
					hero = new Hero(j,i,'H');
				}
			}	
		}

		return hero;
	}

	
	public Weapon getMazeWeapon()
	{
		
		Weapon weapon = new Weapon(0,0,'E');
		
		for(int i=0; i < maze.length; i++)
		{
			for (int j=0; j < maze[i].length; j++)
			{
				if (maze[i][j]== 'E')
				{
					weapon = new Weapon(j,i,'E');
				}
			}	
		}
		
		return weapon;
	}
	
	@Override
	public String toString() 
	{
		String s ="";
		for(int j=0; j < maze.length;j++)
		{
			for(int i=0; i < maze[j].length; i++)
			{
				s+= maze[j][i];
				s+=' ';
			}
			s+='\n';
		}
		
		return s;
	}
}