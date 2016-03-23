package maze.logic;

import maze.logic.Play;
public class Main
{
	static Play p = new Play();
	private static MazeBuilder mb = new MazeBuilder(9);
	
	public static void main(String[] args) 
	{	
		
		mb.printMaze();
		System.out.print('\n');
		mb.printVisited();
		if(mb.openCellAround(new Point(1,1)))
			System.out.print("heyy");
		//p.gamePlay();

	}

}
