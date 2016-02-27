
public class Character 
{
	
	protected int position[] = new int [2] ;
	
	public int[] getPosition()
	{
		return position;
	}
	
	public char getChar()
	{
		return '\0';
	}
	
	public void setPosition(char c)
	{
		switch(c)
		{
		case 'U':
			position[0] = position[0] - 1;
			break;
		case 'D':
			position[0] = position[0] + 1;
			break;
		case 'L':
			position[1] = position[1] - 1;
			break;
		case 'R':
			position[1] = position[1] + 1;
			break;
		}
	}
}


