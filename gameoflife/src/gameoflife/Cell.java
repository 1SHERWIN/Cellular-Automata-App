package gameoflife;

public class Cell implements Runnable {
	Cell(char[][] grid, int rowIndex, int columnIndex) {
		this.grid = grid;
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.wait = true;
	}
	public void start( ) {
		thread = new Thread(this);
		thread.start();
	}
	public void run () {
		while (true) {
			next = getNextGen(rowIndex, columnIndex);
			synchronized(this) {
				try { wait();} 
				catch (InterruptedException e) { e.printStackTrace();}	
			}
		}
	}
	public char getNextGen(int rowIndex, int columnIndex) {
		int neighborCount = getNeighborCount(rowIndex, columnIndex);
		if (neighborCount < 2) return 'O';
		else if (neighborCount == 3) return 'X';
		else if (neighborCount > 3) return 'O';
		else return grid[rowIndex][columnIndex];
	}
	
	public int getNeighborCount(int rowIndex, int columnIndex) {
		int neighborCount = 0;
		for (int i = -1; i <= 1; i++) 
            for (int j = -1; j <= 1; j++) 
                if (grid[rowIndex + i][columnIndex + j] == 'X') neighborCount++;
		if (grid[rowIndex][columnIndex] == 'X') neighborCount--;
		return neighborCount;
	}
	public void updateGrid (char[][] grid) {
		this.grid = grid;
	}

	public Thread thread;
	public char next;
	public char[][] grid;
	public int rowIndex;
	public int columnIndex;
	public boolean wait;
}
