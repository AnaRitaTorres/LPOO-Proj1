import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		labirinth lab = new labirinth();
		Character czar = new Hero(1, 1, 'H');
		Character dd = new Dragon(8, 1, 'D');
		
		Scanner sc = new Scanner(System.in);
		lab.getLab()[czar.getPosition()[0]][czar.getPosition()[1]] = czar.getChar();
		lab.getLab()[dd.getPosition()[0]][dd.getPosition()[1]] = dd.getChar();
		while (true)
		{
			lab.printLab();
			System.out.print("\nMovement(U, D, L, R): ");
			
			char c = sc.next().charAt(0);
			lab.move(c, czar);
			lab.randomMove(dd);
			System.out.print("\n");
			
			
		}
		
		 
		
		
	}

}
