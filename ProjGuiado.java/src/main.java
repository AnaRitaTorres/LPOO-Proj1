import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		labirinth lab = new labirinth();
		Character czar = new Hero(1, 1, 'H');
		
		Scanner sc = new Scanner(System.in);
		lab.getLab()[czar.getPosition()[0]][czar.getPosition()[1]] = czar.getChar();
		while (true)
		{
			lab.printLab();
			System.out.print("\nMovement(U, D, L, R): ");
			
			char c = sc.next().charAt(0);
			lab.move(c, czar);
			
			System.out.print("\n");
			
			
		}
		
		 
		
		
	}

}
