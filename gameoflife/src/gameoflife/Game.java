package gameoflife;

import java.io.File;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Game{
	public void setupGame(String startingGrid) {
		try { input = new Scanner(new File(startingGrid)); }
		catch(Exception e){	System.out.println("File not found."); }
		
		// Parse each input line
		for (int rowIndex = 0; rowIndex < rowCount; rowIndex++)
			grid[rowIndex] = input.nextLine().toCharArray();
		String lastLine = input.nextLine();
		generations = Integer.parseInt(lastLine);
		
		System.out.println("Total Generations: " + generations);
		displayGame();
		input.close();
	}
	
	// Multi-threaded grid with a thread for each cell
	public void playGame() {
		
		// Threads will persist across generations
		for (rowIndex = 1; rowIndex < rowCount - 1; rowIndex++)
			for (columnIndex = 1; columnIndex < rowCount - 1; columnIndex++) {
				nextGen[rowIndex][columnIndex] = new Cell(grid,rowIndex,columnIndex);
				nextGen[rowIndex][columnIndex].start();
			}
		
		// Update the grid for each generation
		while (currentGen++ < generations) {
			System.out.println("Current Generation: " + currentGen);
			for (rowIndex = 1; rowIndex < rowCount - 1; rowIndex++)
				for (columnIndex = 1; columnIndex < rowCount - 1; columnIndex++) {
					nextGen[rowIndex][columnIndex].updateGrid(grid);
					synchronized(nextGen[rowIndex][columnIndex]) {
						nextGen[rowIndex][columnIndex].notify();
					}
				}
			
			// GUI is updated each generation
			for (rowIndex = 1; rowIndex < rowCount - 1; rowIndex++)
				for (columnIndex = 1; columnIndex < rowCount - 1; columnIndex++) {
					grid[rowIndex][columnIndex] = nextGen[rowIndex][columnIndex].next;	
					if (grid[rowIndex][columnIndex] == 'X') 
						labelGrid[rowIndex][columnIndex].setBackground(Color.BLUE);
					else 
						labelGrid[rowIndex][columnIndex].setBackground(null);
				}
			
			
			try { Thread.sleep(200);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}	
	}
	
	public void displayGame() {
		frame = new JFrame("Game of Life");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setBounds(100, 100, 500, 500);
	    
	    panel = new JPanel();
	    panel.setLayout(new GridLayout(rowCount, rowCount));
	    frame.getContentPane().add(panel);
	    
	    labelGrid = new JLabel[rowCount][rowCount];
	    for (int i = 0; i < rowCount; i++){
	        for (int j = 0; j < rowCount; j++){
	        	labelGrid[i][j] = new JLabel();
	            panel.add(labelGrid[i][j]);
	        	labelGrid[i][j].setOpaque(true);
	        }
	    }
	    for (rowIndex = 1; rowIndex < rowCount - 1; rowIndex++)
			for (columnIndex = 1; columnIndex < rowCount - 1; columnIndex++) {
				if (grid[rowIndex][columnIndex] == 'X') 
					labelGrid[rowIndex][columnIndex].setBackground(Color.BLUE);
				else 
					labelGrid[rowIndex][columnIndex].setBackground(null);
			}
	    frame.setVisible(true);
	    try { Thread.sleep(1000);} 
		catch (InterruptedException e) { e.printStackTrace();}
	}

	public void printBoard() {
		System.out.printf("Generation %d:\n", currentGen);
		for (char[] row : grid) {
			for (char cell : row)
		        System.out.print(cell + " ");
			System.out.println("");	
		}
	}
	public int rowCount = 20;
	public int rowIndex;
	public int columnIndex;
	public int generations = 0;				
	public int currentGen = 0;			
	public char[][] grid = new char[rowCount][rowCount];
	public Cell[][] nextGen = new Cell[rowCount][rowCount];
	public Scanner input;	
	public JFrame frame; 
	public JPanel panel;
	public JLabel[][] labelGrid;
}
