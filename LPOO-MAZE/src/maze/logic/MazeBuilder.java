package maze.logic;

import java.util.Stack;

public class MazeBuilder implements IMazeBuilder
{
	private char[][] lab;
	private boolean[][] visitedCells;
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
		randomPath();
	}

	public char[][] getMaze()
	{
		return lab;
	}
	public MazeBuilder() 
	{
		
	}

	public void randomPath()
	{
		printMaze();
		System.out.print("\n");
		printVisited();
		System.out.print("\n\n\n\n\n\n\n\n\n");
		int random = (int)(Math.random() * 4);
		Point r;

		if (!pathHistory.isEmpty())
		{
			Point guideCell = new Point (pathHistory.peek().getX(),pathHistory.peek().getY());
			
			if (openCellAround(guideCell))
			{
				switch(random)
				{
				case 0:
					guideCell.setX(guideCell.getX()+2);
					r =conversionToCells(guideCell);
					if (!isOut(r) && visitedCells[r.getY()][r.getX()] == false)
					{
						lab[guideCell.getY()][guideCell.getX()-1]=' ';
						pathHistory.push(guideCell);
						visitedCells[r.getY()][r.getX()]=true;
						randomPath();
					}
					else randomPath();

				case 1:
					guideCell.setX(guideCell.getX()-2);
					r =conversionToCells(guideCell);
					if (!isOut(r) && visitedCells[r.getY()][r.getX()] == false)
					{
						lab[guideCell.getY()][guideCell.getX()+1]=' ';
						pathHistory.push(guideCell);
						visitedCells[r.getY()][r.getX()]=true;
						randomPath();
					}
					else randomPath();

				case 2:
					guideCell.setY(guideCell.getY()-2);
					r =conversionToCells(guideCell);
					if (!isOut(r) && visitedCells[r.getY()][r.getX()] == false)
					{
						lab[guideCell.getY()+1][guideCell.getX()]=' ';
						pathHistory.push(guideCell);
						visitedCells[r.getY()][r.getX()]=true;
						randomPath();
					}
					else randomPath();

				case 3:
					guideCell.setY(guideCell.getY()+ 2);
					r =conversionToCells(guideCell);
					if (!isOut(r) && visitedCells[r.getY()][r.getX()] == false)
					{
						lab[guideCell.getY()-1][guideCell.getX()]=' ';
						pathHistory.push(guideCell);
						visitedCells[r.getY()][r.getX()]=true;
						randomPath();
					}
					else randomPath();

				}
			}
			else
			{
				pathHistory.pop();
				randomPath();
			}
		}

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

		pathHistory.push(new Point (size-2,random));
	}

	public void createVisitedCell(int size)
	{
		int vcDimension = (size - 1) / 2;

		visitedCells = new boolean[vcDimension][vcDimension];

		for ( int i =0; i < vcDimension; i++)
		{
			for(int j=0; j < vcDimension; j++)
			{
				visitedCells[i][j]= false;
			}
		}
	}

	public boolean isOut(Point p)
	{
		if(p.getY() > visitedCells.length-1  || p.getY() < 0 || p.getX() > visitedCells[0].length -1|| p.getX() < 0)
			return true;
		else
			return false;
	}

	public boolean openCellAround(Point p)
	{
		Point newp = conversionToCells(p);

		newp.setX(newp.getX()+1);
		if(!isOut(newp))
		{
			if (visitedCells[newp.getY()][newp.getX()]== false)
				return true;
		}

		newp.setX(newp.getX()-2);
		if(!isOut(newp))
		{
			if (visitedCells[newp.getY()][newp.getX()]== false)
				return true;
		}

		newp.setY(newp.getY()+1);
		newp.setX(newp.getX()+1);
		if(!isOut(newp))
		{
			if (visitedCells[newp.getY()][newp.getX()]== false)
				return true;
		}

		newp.setY(newp.getY()-2);
		if(!isOut(newp))
		{
			if (visitedCells[newp.getY()][newp.getX()]== false)
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

	@Override
	public char[][] buildMaze(int size) {
		// TODO Auto-generated method stub
		return null;
	}
}
