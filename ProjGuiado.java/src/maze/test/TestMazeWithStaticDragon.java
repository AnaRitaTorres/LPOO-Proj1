package maze.test;

public class TestMazeWithStaticDragon {
	char [][] m1 = {
			{'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', ' ', 'H', 'S'},
			{'X', ' ', 'X', ' ', 'X'},
			{'X', 'E', ' ', 'D', 'X'},
			{'X', 'X', 'X', 'X', 'X'}
			};
//a.
	
	public void testMoveHeroToFreeCell()
	{
		Maze maze = new Maze (m1);
	}

}
