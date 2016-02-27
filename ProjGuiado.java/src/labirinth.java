

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
	
	public void move(char direction, Character c)
	{
		
		switch (direction)
		{
		case 'U': 
			System.out.print("U");
			if(lab[c.getPosition()[0] -1][c.getPosition()[1]] == ' ')
			{
				lab[c.getPosition()[0]][c.getPosition()[1]] = ' ';
				lab[c.getPosition()[0] -1][c.getPosition()[1]] = c.getChar();
				c.setPosition(direction);
				
				
			}
			break;
			
		case 'D': 
			System.out.print("D");
			if(lab[c.getPosition()[0] +1][c.getPosition()[1]] == ' ')
			{
				lab[c.getPosition()[0]][c.getPosition()[1]] = ' ';
				lab[c.getPosition()[0] +1][c.getPosition()[1]] = c.getChar();
				c.setPosition(direction);
				
			}
			break;
			
		case 'L': 
			System.out.print("L");
			if(lab[c.getPosition()[0]][c.getPosition()[1] -1] == ' ')
			{
				lab[c.getPosition()[0]][c.getPosition()[1]] = ' ';
				lab[c.getPosition()[0]][c.getPosition()[1] -1] = c.getChar();
				c.setPosition(direction);
				
			}
			break;
			
		case 'R': 
			System.out.print("R");
			if(lab[c.getPosition()[0]][c.getPosition()[1] +1] == ' ')
			{
				lab[c.getPosition()[0]][c.getPosition()[1]] = ' ';
				lab[c.getPosition()[0]][c.getPosition()[1] +1] = c.getChar();
				c.setPosition(direction);
				
			}
			break;
			
		}
		
		System.out.print(c.getPosition()[1]);
		System.out.print(c.getPosition()[0]);
		
	}

}
