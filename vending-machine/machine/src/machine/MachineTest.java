package machine;

public class MachineTest {
	public static void main(String args[]) {
		Machine vend = new Machine();
		Session Rick = new Session();
		
		// Stock our vending machine
		vend.product(1).add(5);
		vend.product(2).add(5);
		vend.product(3).add(5);
		
		// Menu
		while (Rick.more) {
			vend.menu();
			vend.selection(Rick);
		}
		
		// Collect the bank
		vend.empty();
	}
}
