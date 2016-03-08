package maze.test;

import org.junit.Test;

import maze.logic.GameState.gameState;
import maze.logic.Hero;
import maze.logic.Maze;
import maze.logic.MovementType.movementType;
import maze.logic.Play;
import maze.logic.Point;
import static org.junit.Assert.assertEquals;

public class ElementaryTests 
{	
	private Maze m = new Maze();
	private Hero h = new Hero(1,1,'H');
	private Play p = new Play();
	
	@Test
	public void freeCell()
	{
		p.setState(gameState.STATIC);
		m.move(movementType.RIGHT, h);
		Point p = new Point (2,1);
		assertEquals(true,m.pointEquals(p,h.getCharacterPosition()));
	}
	
	@Test
	public void wallStop()
	{
		p.setState(gameState.STATIC);
		m.move(movementType.LEFT, h);
		Point p = new Point (1,1);
		assertEquals(true,m.pointEquals(p,h.getCharacterPosition()));
	}
	
	
	

}
