package maze.cli;

import java.util.Scanner;
import maze.logic.Maze;

public class Interface 
{
	Scanner sc = new Scanner(System.in);
	
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
		System.out.print("\nDirection ( Up(U), Down (D), Left(L), Right(R)): ");
		
		char s = sc.next().charAt(0);
		//sc.close();
		return s;
		
	}
	
	public char readGameState()
	{
		System.out.print("\nGame Mode? ( STATIC(T), SLEEP(S), RANDOM(R)): ");
		char s = sc.next().charAt(0);
		return s;
	}
}

	