package maze.cli;

import java.util.Scanner;
import maze.logic.Maze;

public class Interface {
	
	Maze maze = new Maze();
	
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
		System.out.print("\n Direction ( Up(U), Down (D), Left(L), Right(R)):");
		Scanner sc = new Scanner(System.in);
		char s = sc.next().charAt(0);
		return s;
	}
	
	public void play()
	{
		
		printMaze(maze);
		//Scanner sc = new Scanner(System.in);
		//System.out.print("\nType of Dragon(Sleep(S), Static(T),Random(R)):");
		//char s = sc.next().charAt(0);
		
		
		
		
	}
	

}
