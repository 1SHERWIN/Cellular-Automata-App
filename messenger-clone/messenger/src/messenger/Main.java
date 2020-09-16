package messenger;

public class Main {
	public static void main(String[] args) {
		Messenger app = new Messenger();
		app.addClient(new Client("Rick", app));
		app.addClient(new Client("Win", app));
		app.addClient(new Client("Richard", app));	
	}
}
