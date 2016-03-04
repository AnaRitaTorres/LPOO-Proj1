package maze.logic;

import maze.cli.End;

public class Dragon extends Character 
{
	private char d;
	private boolean dState = true;
	
	public Dragon(int x, int y, char c)
	{
		d = c;
		position[0] = x;
		position[1] = y;
	}
	
	public char getChar()
	{
		return d;
	}
	
	public void setState()
	{
		dState=false;
	}
	
	public boolean getState()
	{
		return dState;
	}
	
	public static enum typeDragon
	{
		SLEEP,RANDOM,STATIC
	}
	 
	private typeDragon td;
	
	public void setType(typeDragon td)
	{
		this.td=td;
	}
	
	public boolean  move(char direction, labirinth lab)
	{
		int dx = 0;
		int dy = 0;
		
		boolean ret = false;
		
		switch(direction)
		{
		case 'U':
			dy = -1;
			break;
		
		case 'D':
			dy = 1;
			break;
			
		case 'L':
			dx = -1;
			break;
			
		case 'R':
			dx = 1;
			break;
			
		default:
			break;
		}
		
		if(lab.getLab()[getPosition()[0] + dy][getPosition()[1] + dx] != 'X')
		{
			lab.getLab()[getPosition()[0]][getPosition()[1]] = ' ';
			lab.getLab()[getPosition()[0] + dy][getPosition()[1] + dx] = getChar();
			setPosition(direction);
			ret = true;
			return ret;
		}
		
		if(lab.getLab()[getPosition()[0]+ dy][getPosition()[1]+dx]=='S' && lab.labirinthClear('D')== true )
		{
			lab.getLab()[getPosition()[0]][getPosition()[1]] = ' ';
			lab.getLab()[getPosition()[0] + dy][getPosition()[1] + dx] = getChar();
			End.update(lab);
			
			
		}
		
		return ret;
	}
	
	public void randomMove(labirinth lab)
	{
		boolean stop = false;
		while(!stop)
		{
			switch(0 + (int)(Math.random() * ((3 - 0) + 1)))
			{
			case 0:
				stop = move('U',lab);
				break;
			
			case 1:
				stop = move('R', lab);
				break;
			
			case 2:
				stop = move('L', lab);
				break;
			
			case 3:
				stop = move('D', lab);
				break;
			}
		}
		
	}
	
	
	public void moveType(labirinth lab)
	{
		switch(td)
		{
		case STATIC:
			lab.getLab()[getPosition()[0]][getPosition()[1]] = getChar();
			break;
		case RANDOM:
			randomMove(lab);
			break;
		case SLEEP:
			int rand = 0 + (int)(Math.random() * ((3 - 0) + 1));
			
			if(rand > 0)
			{
				d = 'D';
				randomMove(lab);
			}
			else
			{
				 d = 'd';
				 lab.getLab()[getPosition()[0]][getPosition()[1]] = getChar();
			}
			
			break;
			
		}
			
			
	}
	
	
}

