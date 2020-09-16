package machine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SessionTest {

	@Test
	void test() {
		Session user = new Session();
		
		// view change
		assertEquals(0, user.change());
		
		user.choice = 'F';
		user.deposit();
		
		// view change after deposit
		assertEquals(500, user.change());
		
	}

}
