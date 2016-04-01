package maze.logic;

import maze.logic.GameState.gameState;
import maze.logic.Play;
public class Main
{
	static Play p = new Play();

	private static MazeBuilder mb = new MazeBuilder(9);

	//private static MazeBuilder mb = new MazeBuilder(3511);

	
	public static void main(String[] args) 
	{	
		

//		mb.printMaze();
//		System.out.print('\n');
//		mb.printVisited();

		//printMaze();
		//System.out.print('\n');
		//mb.printVisited();
		p = new Play(mb.getMaze(), gameState.STATIC);
		p.gamePlay();

	}

}
