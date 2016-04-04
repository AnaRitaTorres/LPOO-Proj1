package maze.logic;

import maze.cli.Interface;
import maze.logic.MovementType.movementType;
import maze.logic.Point;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Maze.
 */
public class Maze 
{

	/** The maze. */
	private char[][] maze;
	
	/** The i. */
	private Interface i= new Interface();
	
	/** The out. */
	private Point out;
	
	

	/**
	 * Instantiates a new maze.
	 */
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

	
	/**
	 * Instantiates a new maze.
	 *
	 * @param maze the maze
	 */
	public Maze(char[][] maze)
	{
		this.maze = maze;
		this.out= getExit();
	}

	
	/**
	 * Gets the maze.
	 *
	 * @return the maze
	 */
	public char[][] getMaze()
	{
		return maze;
	}

	
	/**
	 * Gets the out.
	 *
	 * @return the out
	 */
	public Point getOut()
	{
		return out;
	}

	
	/**
	 * Clear cell.
	 *
	 * @param p the p
	 */
	public void clearCell(Point p)
	{
		maze[p.getY()][p.getX()]=' ';
	}

	
	/**
	 * Prints the cell.
	 *
	 * @param p the p
	 * @param c the c
	 */
	public void printCell(Point p,char c)
	{
		maze[p.getY()][p.getX()]= c;
	}

	
	/**
	 * Checks if is out of bounds.
	 *
	 * @param p the p
	 * @return true, if is out of bounds
	 */
	public boolean isOutOfBounds(Point p)
	{
		if(p.getY() > maze.length -1  || p.getY() < 0 || p.getX() > maze[0].length -1 || p.getX() < 0)
			return true;
		else
			return false;
	}

	
	/**
	 * Checks if is cell free.
	 *
	 * @param p the p
	 * @return true, if is cell free
	 */
	public boolean isCellFree(Point p)
	{
		if(maze[p.getY()][p.getX()] == 'X' || isOutOfBounds(p))
			return false;
		if (aliveDragon()&& maze[p.getY()][p.getX()] == 'S')
			return false;
		else return true;
	}
	
	
	/**
	 * Move.
	 *
	 * @param mt the mt
	 * @param c the c
	 * @return true, if successful
	 */
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

	/**
	 * Move random.
	 *
	 * @param c the c
	 */
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

	/**
	 * Move handler.
	 *
	 * @param f the f
	 */
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
	 * Gets the exit.
	 *
	 * @return the exit
	 */
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

	/**
	 * Alive dragon.
	 *
	 * @return true, if successful
	 */
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

	/**
	 * Dragon weapon.
	 *
	 * @param d the d
	 * @param w the w
	 */
	public void dragonWeapon(Dragon d,Weapon w)
	{
		if(pointEquals(w.getPosition(),d.getCharacterPosition()))
		{
			printCell(w.getPosition(), 'F');
		}

	}

	/**
	 * Gets the maze dragons.
	 *
	 * @return the maze dragons
	 */
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

	/**
	 * Gets the maze hero.
	 *
	 * @return the maze hero
	 */
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

	
	/**
	 * Gets the maze weapon.
	 *
	 * @return the maze weapon
	 */
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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