package messenger;
import java.util.*;

public class Messenger {
	public Messenger () {
		users = new ArrayList<Client>();
	}
	public void addClient(Client user) {
		users.add(user);
	}	
	public void publish (String message) {
		users.forEach((user) -> user.send(message));
	}
	public ArrayList<Client> users;
}
