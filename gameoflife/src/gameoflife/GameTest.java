package gameoflife;

public class GameTest {
	public static void main(String args[]) {
		Game Conway = new Game();
		Conway.setupGame("startinggrid.txt");
		Conway.playGame();
	}
}
