package maze.logic;

public class Hero extends Character 
{
	private char h;
	
	public Hero(int x, int y, char c)
	{
		h = c;
		position[0] = x;
		position[1] = y;
	}
	
	public char getChar()
	{
		return h;
	}
	
	public void setArmed()
	{
		h = 'A';
	}
	
	public void setDisarmed()
	{
		h = 'H';
	}
	
	public boolean encounter(labirinth lab)
	{
		
		if(lab.getLab()[getPosition()[0] -1][getPosition()[1]] == 'D')
		{
			return  true;
		}
		if(lab.getLab()[getPosition()[0] +1][getPosition()[1]] == 'D')
		{
			return  true;
		}
		if(lab.getLab()[getPosition()[0]][getPosition()[1] -1] == 'D')
		{
			return  true;
		}
		if(lab.getLab()[getPosition()[0]][getPosition()[1] +1] == 'D')
		{
			return  true;
		}
		
		return false;
	}
	
	public boolean isAlive(labirinth lab)
	{
		if(encounter(lab) == true && h == 'H')
			return false;
		else
			return true;
	}
	
		
	/*public boolean isOut(labirinth lab, Dragon d, Hero h)
	{
		if (getOut(d)== true)
		{
			if (h.getPosition())
		}
	}*/
}
