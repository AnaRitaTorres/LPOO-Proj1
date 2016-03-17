package maze.test;

import org.junit.Test;

import maze.logic.CharacterState.characterState;
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
	
	private Play p = new Play();
	
	@Test
	public void freeCell()
	{
		p.setState(gameState.STATIC);
		m.move(movementType.RIGHT, p.getHero());
		Point pt = new Point (2,1);
		assertEquals(true,m.pointEquals(pt,p.getHero().getCharacterPosition()));
	}
	
	@Test
	public void wallStop()
	{
		p.setState(gameState.STATIC);
		m.move(movementType.LEFT, p.getHero());
		Point pt = new Point (1,1);
		assertEquals(true,m.pointEquals(pt, p.getHero().getCharacterPosition()));
	}
	
	@Test
	public void catchSword()
	{
		p.setState(gameState.STATIC);
		m.move(movementType.DOWN, p.getHero());
		Point pt = new Point (1, 2);
		p.updateGame();
		assertEquals(true, m.pointEquals(pt,p.getHero().getCharacterPosition()));
		
		assertEquals(characterState.ARMED, p.getHero().getState());
		
	}
	
	@Test
	public void disarmedHero()
	{
		
			
	}
	
	
	

}
