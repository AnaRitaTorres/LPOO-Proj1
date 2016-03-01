package maze.logic;

import java.util.Scanner;

public class Playing {
	
	public static void update()
	{
		labirinth lab = new labirinth();
		Character czar = new Hero(1, 1, 'H');
		Character dd = new Dragon(8, 1, 'D');
		Weapon sword = new Weapon(1, 8, 'E');
		
		Scanner sc = new Scanner(System.in);
		
		lab.getLab()[czar.getPosition()[0]][czar.getPosition()[1]] = czar.getChar();
		lab.getLab()[dd.getPosition()[0]][dd.getPosition()[1]] = dd.getChar();
		lab.getLab()[sword.getPosition()[0]][sword.getPosition()[1]] = sword.getChar();
		
		while (true)
		{
			lab.printLab();
			System.out.print("\nMovement(U, D, L, R): ");
			
			char c = sc.next().charAt(0);
			
			if(dd.getState()== true)
			{
				lab.randomMove(dd);
			}
			
			lab.move(c, czar);
			
			System.out.print("\n");
			
			if(czar.encounter(lab))
			{
				if(czar.isAlive(lab))
				{
					lab.getLab()[dd.getPosition()[0]][dd.getPosition()[1]] = ' ';
					lab.printLab();
					dd.setState();
					
				}
				else
				{
					lab.getLab()[czar.getPosition()[0]][czar.getPosition()[1]] = ' ';
					lab.printLab();
					End.update(lab);
					return;
				}
					
			}
			
			
		}
		
		 
		
		
	}

		
	}


