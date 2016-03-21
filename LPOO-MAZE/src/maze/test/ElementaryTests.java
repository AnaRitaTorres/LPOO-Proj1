package maze.test;

import org.junit.Test;

import maze.cli.Interface;
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
	private Interface i = new Interface();
	private Play p = new Play();

	//2.1
	//a.
	@Test
	public void freeCell()
	{
		p.setState(gameState.STATIC);
		m.move(movementType.RIGHT, p.getHero());
		Point pt = new Point (2,1);
		assertEquals(true,m.pointEquals(pt,p.getHero().getCharacterPosition()));
	}

	//b.
	@Test
	public void wallStop()
	{
		p.setState(gameState.STATIC);
		m.move(movementType.LEFT, p.getHero());
		Point pt = new Point (1,1);
		assertEquals(true,m.pointEquals(pt, p.getHero().getCharacterPosition()));
	}

	//c.
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

	//d.
	@Test
	public void disarmedAndKilled()
	{
		p.setState(gameState.STATIC);
		m.move(movementType.RIGHT, p.getHero());
		Point pt = new Point(2,1);
		assertEquals(true,m.pointEquals(pt, p.getHero().getCharacterPosition()));
		assertEquals(characterState.DISARMED,p.getHero().getState());

		for(int i =0; i < p.getDragon().getCharacterPosition().getX()-3;i++)
		{
			m.move(movementType.RIGHT, p.getHero());
		}
		Point it = new Point(7,1);
		p.updateGame();
		assertEquals(true,m.pointEquals(it, p.getHero().getCharacterPosition()));
		assertEquals(characterState.DEAD,p.getHero().getState());


	}

	//e.
	@Test
	public void armedAndKills()
	{
		p.setState(gameState.STATIC);
		m.move(movementType.DOWN, p.getHero());
		Point pt = new Point(1,2);
		p.updateGame();
		assertEquals(true,m.pointEquals(pt, p.getHero().getCharacterPosition()));
		assertEquals(characterState.ARMED,p.getHero().getState());

		m.move(movementType.UP, p.getHero());
		for(int i =0; i < p.getDragon().getCharacterPosition().getX()-2;i++)
		{
			m.move(movementType.RIGHT, p.getHero());
		}
		Point it = new Point(7,1);
		p.updateGame();
		assertEquals(true,m.pointEquals(it, p.getHero().getCharacterPosition()));
		assertEquals(characterState.DEAD,p.getDragon().getState());
		assertEquals(characterState.ARMED,p.getHero().getState());

	}

	//f.
	@Test
	public void movesToExit()
	{
		armedAndKills();
		m.move(movementType.RIGHT, p.getHero());

		for (int i=0; i < 4; i++)
		{
			m.move(movementType.DOWN, p.getHero());
		}
		m.move(movementType.RIGHT, p.getHero());
		p.updateGame();
		assertEquals(p.getDragon().getState(),characterState.DEAD );
		assertEquals(true,m.pointEquals(m.getOut(), p.getHero().getCharacterPosition()));
	}

	//g.
	@Test
	public void cantGetOutDisarmed()
	{

		m.printCell(p.getDragon().getCharacterPosition(), p.getDragon().getChar());

		p.setState(gameState.STATIC);
		m.move(movementType.RIGHT, p.getHero());
		m.move(movementType.RIGHT, p.getHero());
		m.move(movementType.RIGHT, p.getHero());

		for( int i =0; i < 8; i++)
		{
			m.move(movementType.DOWN, p.getHero());
		}

		m.move(movementType.RIGHT, p.getHero());
		m.move(movementType.RIGHT, p.getHero());
		m.move(movementType.RIGHT, p.getHero());
		m.move(movementType.RIGHT, p.getHero());

		m.move(movementType.UP, p.getHero());
		m.move(movementType.UP, p.getHero());
		m.move(movementType.UP, p.getHero());

		m.move(movementType.RIGHT, p.getHero());

		Point y = new Point (8,5);
		p.updateGame();

		assertEquals(true,m.pointEquals(y, p.getHero().getCharacterPosition()));
	}

	//h.
	@Test 
	public void cantGetOutArmed()
	{
		m.printCell(p.getDragon().getCharacterPosition(), p.getDragon().getChar());

		catchSword();

		m.move(movementType.UP, p.getHero());

		m.move(movementType.RIGHT, p.getHero());
		m.move(movementType.RIGHT, p.getHero());
		m.move(movementType.RIGHT, p.getHero());

		for( int i =0; i < 8; i++)
		{
			m.move(movementType.DOWN, p.getHero());
		}

		m.move(movementType.RIGHT, p.getHero());
		m.move(movementType.RIGHT, p.getHero());
		m.move(movementType.RIGHT, p.getHero());
		m.move(movementType.RIGHT, p.getHero());

		m.move(movementType.UP, p.getHero());
		m.move(movementType.UP, p.getHero());
		m.move(movementType.UP, p.getHero());

		m.move(movementType.RIGHT, p.getHero());

		Point y = new Point (8,5);
		p.updateGame();

		assertEquals(true,m.pointEquals(y, p.getHero().getCharacterPosition()));

	}

	//2.2

	@Test(timeout=1000)
	public void testRandomMode()
	{
		boolean dragonMove = true;
		p.setState(gameState.RANDOM);

		while ((p.getGameType()!=gameState.WON && p.getGameType()!=gameState.LOST )&& dragonMove)
		{
			Point o = new Point (p.getDragon().getCharacterPosition().getY(),p.getDragon().getCharacterPosition().getX());
			if(p.getHero().getState()== characterState.ARMED ||p.getHero().getState()== characterState.DISARMED)
				m.moveRandom(p.getHero());
			p.updateGame();
			if (p.getDragon().getState()==characterState.ALIVE)
				m.moveRandom(p.getDragon());
			p.updateGame();
			if(m.pointEquals(p.getDragon().getCharacterPosition(),o)&& p.getDragon().getState()==characterState.ALIVE)
				dragonMove=false;

			if(p.getHero().getState() == characterState.DEAD )
			{
				p.setState(gameState.LOST);
			}

			if (p.getDragon().getState()==characterState.DEAD && m.pointEquals(m.getOut(), p.getHero().getCharacterPosition()))
			{
				p.setState(gameState.WON);
			}
		}

		assertEquals(true,p.getGameType()==gameState.WON || p.getGameType()==gameState.LOST);
		assertEquals(true,dragonMove);
	}

	@Test(timeout=1000)
	public void testSleepMode()
	{

	}
}
