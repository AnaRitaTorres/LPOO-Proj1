

public class labirinth 
{

	
	private char lab[][] = {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ','X',' ','S'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}
	};
	
	public char[][] getLab()
	{
		return lab;
	}
	public void printLab()
	{
		for(int j=0; j < lab.length;j++)
		{
			for(int i=0; i < lab[j].length; i++)
			{
				System.out.print(lab[j][i]);
				System.out.print(' ');
			}
			System.out.print('\n');
		}
	}
	
	public boolean move(char direction, Character c)
	{
		boolean ret = false;
		switch (direction)
		{
		case 'U': 
			System.out.print("U");
			if(lab[c.getPosition()[0] -1][c.getPosition()[1]] == ' ')
			{
				lab[c.getPosition()[0]][c.getPosition()[1]] = ' ';
				lab[c.getPosition()[0] -1][c.getPosition()[1]] = c.getChar();
				c.setPosition(direction);
				ret = true;
				
			}
			break;
			
		case 'D': 
			System.out.print("D");
			if(lab[c.getPosition()[0] +1][c.getPosition()[1]] == ' ')
			{
				lab[c.getPosition()[0]][c.getPosition()[1]] = ' ';
				lab[c.getPosition()[0] +1][c.getPosition()[1]] = c.getChar();
				c.setPosition(direction);
				ret = true;
			}
			break;
			
		case 'L': 
			System.out.print("L");
			if(lab[c.getPosition()[0]][c.getPosition()[1] -1] == ' ')
			{
				lab[c.getPosition()[0]][c.getPosition()[1]] = ' ';
				lab[c.getPosition()[0]][c.getPosition()[1] -1] = c.getChar();
				c.setPosition(direction);
				ret = true;
			}
			break;
			
		case 'R': 
			System.out.print("R");
			if(lab[c.getPosition()[0]][c.getPosition()[1] +1] == ' ')
			{
				lab[c.getPosition()[0]][c.getPosition()[1]] = ' ';
				lab[c.getPosition()[0]][c.getPosition()[1] +1] = c.getChar();
				c.setPosition(direction);
				ret = true;
			}
			break;
			
		}
		
		System.out.print(c.getPosition()[1]);
		System.out.print(c.getPosition()[0]);
		
		return ret;
	}
	
	public void randomMove(Character c)
	{
		boolean stop = false;
		while(!stop)
		{
			switch(0 + (int)(Math.random() * ((3 - 0) + 1)))
			{
			case 0:
				stop = move('U', c);
				break;
			
			case 1:
				stop = move('R', c);
				break;
			
			case 2:
				stop = move('L', c);
				break;
			
			case 3:
				stop = move('D', c);
				break;
			}
		}
		
	}

}
