package maze.logic;

import java.util.Stack;

public class MazeBuilder
{
	private char[][] lab;
	private boolean[][] visitedCells;
	private Point guideCell;
	private Stack <Point> pathHistory = new Stack <Point> ();


	//auxiliar function (odd numbers)
	public boolean isOdd(int i)
	{
		if (i % 2 == 0) 
		{
			return false;
		} 
		else 
		{
			return true;
		}
	}

	public MazeBuilder(int size)
	{
		lab = new char[size][size];

		//fill the maze with X
		for ( int i =0; i < size; i++)
		{
			for(int j=0; j < size; j++)
			{
				lab[i][j]= 'X';
			}
		}

		//space cells defined
		for ( int i =0; i < size; i++)
		{
			for(int j=0; j < size; j++)
			{
				if (isOdd(i)&& isOdd(j))
				{
					lab[i][j]=' ';
				}
			}
		}
		createVisitedCell(size);
		generateExit(size);
	}
	
	public void openMaze()
	{
		int random = (int)(Math.random() * 4);
		
		case(random)
	}
	
	public void printMaze()
	{
		for(int j=0; j < lab.length;j++)
		{
			for(int i=0; i < lab[j].length; i++)
			{
				System.out.print(lab[j][i]);
				System.out.print(' ');
			}
			System.out.print('\n');
		}
	}
	
	public void printVisited()
	{
		for(int j=0; j < visitedCells.length;j++)
		{
			for(int i=0; i < visitedCells[j].length; i++)
			{
				if(visitedCells[j][i])
					System.out.print("T ");
				else
					System.out.print("F ");
			}
			System.out.print('\n');
		}
	}
	
	public void generateExit(int size)
	{
		int random = (int)(Math.random() * (size-1));
		
		while(!isOdd(random))
		{
			random = (int)(Math.random() * (size-1));
		}
		
		lab[random][size-1]= 'S';
		
		visitedCells[(random-1) /2][(size-2) /2] = true;
		
		
	}

	public void createVisitedCell(int size)
	{
		int vcDimension = (size - 1) / 2;
		
		visitedCells = new boolean[vcDimension][vcDimension];
		
		for ( int i =0; i < vcDimension; i++)
		{
			for(int j=0; j < vcDimension; j++)
			{
				visitedCells[i][j]= true;
			}
		}
		
		

	}
	
	public boolean isOut(Point p)
	{
		if(p.getY() > visitedCells.length  || p.getY() < 0 || p.getX() > visitedCells[0].length || p.getX() < 0)
			return true;
		else
			return false;
	}
	
	public boolean openCellAround(Point p)
	{
		Point newp = conversionToCells(p);
		
		newp.setX(newp.getX() +1);
		if(!isOut(newp))
		{
			if(!(visitedCells[newp.getY()][newp.getX()]));
				return true;
		}
		
		newp.setX(newp.getX() -2);
		if(!isOut(newp))
		{
			if(!(visitedCells[newp.getY()][newp.getX()]));
				return true;
		}
		
		newp.setX(newp.getX() +1);
		newp.setY(newp.getY() +1);
		if(!isOut(newp))
		{
			if(!(visitedCells[newp.getY()][newp.getX()]));
				return true;
		}
		
		newp.setY(newp.getY() -2);
		if(!isOut(newp))
		{
			if(!(visitedCells[newp.getY()][newp.getX()]));
				return true;
		}
		
		return false;
		
	}
	
	public Point conversionToCells(Point p)
	{
		return new Point((p.getX() -1) / 2,(p.getY() -1) / 2);
	}

	public Point conversionToMaze(Point p)
	{
		return new Point((p.getX()*2) +1,(p.getY()*2) +1);
	}
}
