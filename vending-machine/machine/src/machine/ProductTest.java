package machine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductTest {
	public Product test = new Product("Cheetos", 1); 
	public int costInCents = 100;
	
	@Test
	void testName() {
		
		// Product name
		assertEquals("Cheetos", test.name());
		
		
	}
	@Test
	void testCost() {
			
		// Product cost
		assertEquals(costInCents, test.cost());
		
	}

	@Test
	void testQty() {

		// Product quantity
		assertEquals(0, test.qty());
		
	}

}
