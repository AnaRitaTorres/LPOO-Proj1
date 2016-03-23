package maze.logic;

import java.util.Scanner;

public interface IMazeBuilder 
{

	char[][] buildMaze(int size)throws IllegalArgumentException
	{
		if (size % 2 != 0) 
		{
			
		Scanner sc = new Scanner(System.in);
		System.out.print("Specify the size of the maze, it must be an odd number: ");
		int s = sc.nextInt();
		
		MazeBuilder mb = new MazeBuilder(s);
		
		return mb.getMaze();
		}
	}

}
