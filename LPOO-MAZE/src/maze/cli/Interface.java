package maze.cli;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import maze.logic.Maze;
import java.io.InputStream;
import java.util.Scanner;


public class Interface 
{
	Scanner sc = new Scanner(System.in);
	private boolean test = false;
	
	public void setTest()
	{
		test = true;
	}
	
	public void setDefault()
	{
		test = false;
	}
	
	public void printMaze(Maze maze)
	{
		for(int j=0; j < maze.getMaze().length;j++)
		{
			for(int i=0; i < maze.getMaze()[j].length; i++)
			{
				System.out.print(maze.getMaze()[j][i]);
				System.out.print(' ');
			}
			System.out.print('\n');
		}
	}
	
	public char readMove()
	{
		if(test == false)
		{
			System.out.print("\nDirection ( Up(U), Down (D), Left(L), Right(R)): ");
			
			char s = sc.next().charAt(0);
			
			return s;
		}
		else
			return 'R';
		
		
	}
	
	public char readGameState()
	{
		if(test == false)
		{
			System.out.print("\nGame Mode? ( STATIC(T), SLEEP(S), RANDOM(R)): ");
			char s = sc.next().charAt(0);
			return s;
		}
		else
			return 'S';
	}
}

	