package machine;
import java.util.Scanner;

public class Machine {
	public Machine() {
		
		// Bank is supplied $10.00 for change
		bank = 1000;

		// Machine holds 10 products
		size = 10;
		products = new Product[size];
		items = 3;
		products[1] = new Product("Cheetos", 1); 
		products[2] = new Product("Doritos", 2);
		products[3] = new Product("Coke", 2);
		input = new Scanner(System.in); 
	}
	
	public Product product(int item) {
		return products[item];
	}	
	public void deposit(int money) {
		bank += money;
	}
	public void purchase(int item) {
		if (product(item).qty() > 0) {
			product(item).remove();
			deposit(product(item).cost());
			System.out.println("You recieved " + product(item).name());
			System.out.println("Enter anything to continue ");
			input.next();
		}
		else {
			System.out.println("Select another item");
			System.out.println("Enter anything to continue ");
			input.next();
		}
		
	}
	public int empty() {
		for (int i = 0; i < 50; ++i) System.out.println();
		System.out.println("Machine earned " + (bank - 1000) + "¢ in revenue today");
		System.out.println("Bank has been emptied and resupplied $10.00 for change");
		int loot = bank;
		bank = 1000;
		return loot;
	}
	public void menu() {
		for (int i = 0; i < 50; ++i) System.out.println();
		System.out.println("Item: ");
		for (int j = 1; j <= items; j++) 
			System.out.println(j + "	" + product(j).name() + "	$" + (product(j).cost() / 100) + "	Count: " + product(j).qty());
	}
	public void selection (Session user) {
		System.out.println();
		System.out.println("Balance: " + user.change() + "¢	Insert Money:    or S(select product) or C(change return)");
		System.out.println("T($10) F($5) O($1) Q(25¢) D(10¢) N(5¢) P(1¢)");
		user.choice = input.next().charAt(0);
		
		if ( user.choice == 'C' || user.choice == 'c' ) {
			user.getchange();
			user.stop();
		}
		
		if ( user.choice == 'S' || user.choice == 's' ) {
			menu();
			System.out.println("Balance: " + user.change() + "¢	Select Item: ");
			item = input.nextInt();
			if ( item > 0 && item <= items) {
				if (user.change() >= product(item).cost()) {
					user.spend(product(item).cost());
					purchase(item);
				}
				else {
					System.out.println("Insert more coins ");
					System.out.println("Enter anything to continue ");
					input.next();
				}
			}
			else {
				System.out.println("Select another item ");
				System.out.println("Enter anything to continue ");
				input.next();
			}
			
		}
		else user.deposit();
	}
		
	private Product[] products;
	private int bank, size, items, item;
	private Scanner input;
}
