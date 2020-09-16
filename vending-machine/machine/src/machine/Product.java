package machine;

public class Product {
	public Product(String n, int c) {
		name = n;
		cost = c * 100;
		qty = 0;
	}

	public void add(int count) { 
		qty += count; 
	}
	public void remove() {
		qty--;
	}
	public String name() {
		return name;
	}
	public int cost() {
		return cost;
	}
	public int qty() {
		return qty;
	}
		
	private int cost, qty;
	private String name;
}
