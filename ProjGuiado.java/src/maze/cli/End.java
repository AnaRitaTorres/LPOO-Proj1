package maze.cli;

import maze.logic.labirinth;

public class End {
	
	public static void update(labirinth l)
	{
		if(l.labirinthClear('D')==true)
		{
			System.out.println("You Won!!!!! \n");
		}
		else 
		{
			System.out.println("You Lost!!!!! \n");
		}
		l.printLab();
	}

}
