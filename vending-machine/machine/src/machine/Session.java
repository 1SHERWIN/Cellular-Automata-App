package machine;
import java.util.Scanner;

public class Session {
	public Session() {
		change = 0;
		more = true;
		input = new Scanner(System.in); 
	}
	public void spend(int cost) {
		change -= cost;
	}
 	public void stop() {
		more = false;
	}
	public int change() {
		return change;
	}
	public char choice() {
		return choice;
	}
	public void deposit() {
		if ( choice == 'T' || choice == 't' ) change += 1000;
		if ( choice == 'F' || choice == 'f' ) change += 500;
		if ( choice == 'O' || choice == 'o' ) change += 100;
		if ( choice == 'Q' || choice == 'q' ) change += 25;
		if ( choice == 'D' || choice == 'd' ) change += 10;
		if ( choice == 'N' || choice == 'n' ) change += 5;
		if ( choice == 'P' || choice == 'p' ) change += 1;
	}
	public void getchange() {
		for (int i = 0; i < 50; ++i) System.out.println();
		System.out.println("Your change is " + change + "¢");
		int quarter = change / 25;
		change %= 25;
		int dime = change / 10;
		change %= 10;
		int nickel = change / 5;
		change %= 5;
		int penny = change;
		System.out.println("You recieved " + quarter + " quarters");
		System.out.println("You recieved " + dime + " dimes");
		System.out.println("You recieved " + nickel + " nickels");
		System.out.println("You recieved " + penny + " pennies");
		change = 0;
		System.out.println("Enter anything to end session");
		input.next();
	}
	
	public boolean more;
	public char choice;
	private int change;
	private Scanner input;
}
