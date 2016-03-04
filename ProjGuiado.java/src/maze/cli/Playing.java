package maze.cli;

import java.util.Scanner;

import maze.logic.Character;
import maze.logic.Dragon;
import maze.logic.Hero;
import maze.logic.Weapon;
import maze.logic.labirinth;
import maze.logic.Dragon.typeDragon;

public class Playing {

	public static void update()
	{
	Scanner sc = new Scanner(System.in);
	
	labirinth lab = new labirinth();
	Character czar = new Hero(1, 1, 'H');
	Character dd = new Dragon(8, 1, 'D');
	Weapon sword = new Weapon(5, 2, 'E');
	
	System.out.print("\nType of Dragon(Sleep(S), Static(T),Random(R)):");
	
	char s = sc.next().charAt(0);
	
	switch (s)
	{
	case 'S':
		dd.setType(typeDragon.SLEEP);
		break;
	case 'T':
		dd.setType(typeDragon.STATIC);
		break;
	case 'R':
		dd.setType(typeDragon.RANDOM);
		break;
		
	}
	
	
	lab.getLab()[czar.getPosition()[0]][czar.getPosition()[1]] = czar.getChar();
	lab.getLab()[dd.getPosition()[0]][dd.getPosition()[1]] = dd.getChar();
	lab.getLab()[sword.getPosition()[0]][sword.getPosition()[1]] = sword.getChar();
	lab.printLab();

	while (true) 
	{

		System.out.print("\nMovement(U, D, L, R): ");

		char c = sc.next().charAt(0);

		if (dd.getState() == true) 
		{
			dd.moveType(lab);
			lab.getLab()[sword.getPosition()[0]][sword.getPosition()[1]] = sword.getChar();

			if (dd.getPosition()[0] == sword.getPosition()[0] && dd.getPosition()[1] == sword.getPosition()[1]) 
			{
				lab.getLab()[sword.getPosition()[0]][sword.getPosition()[1]] = 'F';
			}

		}

		lab.move(c, czar);

		System.out.print("\n");

		if (czar.encounter(lab))
		{
			if (czar.isAlive(lab))
			{
				lab.getLab()[dd.getPosition()[0]][dd.getPosition()[1]] = ' ';
				dd.setState();

			} 
			else 
			{
				lab.getLab()[czar.getPosition()[0]][czar.getPosition()[1]] = ' ';
				End.update(lab);
				return;
			}

		}

		
		lab.printLab();
	}
	
	
}

}

