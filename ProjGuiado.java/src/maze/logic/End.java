package maze.logic;


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
	}

}
