package maze.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import maze.cli.Interface;
import maze.logic.CharacterState.characterState;
import maze.logic.Dragon;
import maze.logic.GameState.gameState;
import maze.logic.Hero;
import maze.logic.Maze;
import maze.logic.MovementType.movementType;
import maze.logic.Play;
import maze.logic.Point;
import maze.logic.Weapon;


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

		while (p.getGameType()!=gameState.WON && p.getGameType()!=gameState.LOST && dragonMove)
		{
			Point o = new Point (p.getDragon().getCharacterPosition().getX(),p.getDragon().getCharacterPosition().getY());
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

	//sleepMOde
	@Test(timeout=1000)
	public void testSleepMode()
	{
		boolean dragonMove=true;

		while (p.getGameType()!=gameState.WON && p.getGameType()!=gameState.LOST && dragonMove)
		{
			
			Point o = new Point (p.getDragon().getCharacterPosition().getX(),p.getDragon().getCharacterPosition().getY());

			if(p.getHero().getState()== characterState.ARMED ||p.getHero().getState()== characterState.DISARMED)
				m.moveRandom(p.getHero());

			p.updateGame();
			
			if (p.getDragon().getState()==characterState.ALIVE)
			{
				if (p.sleepMove())
				{
					m.moveRandom(p.getDragon());
					p.getDragon().setChar('D');
				}
				else
				{
					p.getDragon().setChar('d');
				}
			}
			p.updateGame();
			if (p.getDragon().getState()==characterState.ALIVE)
			{
			if(p.getDragon().getChar()== 'd' && !m.pointEquals(o, p.getDragon().getCharacterPosition()))
				dragonMove=false;
			
			if (p.getDragon().getChar()== 'D' &&  m.pointEquals(o, p.getDragon().getCharacterPosition()))
				dragonMove=false;
			}
			 

			if(p.getHero().getState() == characterState.DEAD )
			{
				p.setState(gameState.LOST);
			}

			if (p.getDragon().getState()==characterState.DEAD && m.pointEquals(m.getOut(), p.getHero().getCharacterPosition()))
			{
				p.setState(gameState.WON);
			}
		}
		assertEquals(true,dragonMove);
		assertEquals(true,p.getGameType()==gameState.WON || p.getGameType()==gameState.LOST);
		
		
	}

	//extra tests
	
	@Test(timeout=1000)
	public void testGets()
	{
		assertEquals(m.toString(),p.getLab().toString());
		assertEquals(true,m.pointEquals(m.getMazeHero().getCharacterPosition(),p.getLab().getMazeHero().getCharacterPosition()));
		assertEquals(true,m.pointEquals(m.getMazeWeapon().getPosition(), p.getLab().getMazeWeapon().getPosition()));
		
		assertEquals('E', p.getWeapon().getChar());
		
		ArrayList<Dragon> dragons = new ArrayList<Dragon>();
		dragons.add(new Dragon(8,1,'D'));
	}
	
	@Test(timeout=1000)
	public void testGamePlayGui()
	{
		Play p = new Play();
		char[][] maze = p.getLab().getMaze();
		maze[1][6] = 'H';
		maze[2][1] = 'E';
		maze[1][8] = 'D';
		
		Play play = new Play(maze, gameState.SLEEP);
		
		play.getLab().move(movementType.RIGHT, play.getHero());
		play.gamePlayGui();
		
		assertEquals(characterState.DEAD, play.getHero().getState());
		
	}
	
	@Test//(timeout=1000)
	public void testGamePlay()
	{
		Play p = new Play();
		
		char[][] maze = p.getLab().getMaze();
		maze[1][6] = 'H';
		maze[2][1] = 'E';
		maze[1][8] = 'D';
		
		Play play = new Play(maze, gameState.SLEEP);
		
		play.getLab().setInterface(play.getInterface());
		
		play.getInterface().setTest();
		
		play.gamePlay();
		
		assertEquals(characterState.DEAD, play.getHero().getState());
		
		play.getInterface().setDefault();
	}
	
	@Test(timeout=1000)
	public void dragWeapon()
	{
		Weapon j = new Weapon (7,1,'Y');
		p.setWeapon(j);
		m.move(movementType.LEFT, p.getDragon());
		m.dragonWeapon(p.getDragon(), j);
		assertEquals('F',m.getMaze()[1][7]);
	}
	

}
