package lab4.data;

import java.util.Observable;

/**
 * Represents the 2-d game grid
 */

public class GameGrid extends Observable{
	public static final int EMPTY = 0;
	public static final int ME = 1;
	public static final int OTHER = 2;
	
	private int gridsize;
	public int[][] grid;
	
	/**
	 * Constructor
	 * 
	 * @param size The width/height of the game grid
	 */
	public GameGrid(int size){
		this.gridsize = size; // set grid size
		// create grid
		clearGrid();
	}
	
	/**
	 * Reads a location of the grid
	 * 
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @return the value of the specified location
	 */
	public int getLocation(int x, int y){
		return this.grid[y][x];
	}
	
	/**
	 * Returns the size of the grid
	 * 
	 * @return the grid size
	 */
	public int getSize(){return this.gridsize;}
	
	/**
	 * Enters a move in the game grid
	 * 
	 * @param x the x position
	 * @param y the y position
	 * @param player
	 * @return true if the insertion worked, false otherwise
	 */
	public boolean move(int x, int y, int player){
		// if gridLocation is EMPTY, insert player value and return true
		if (getLocation(x,y) == EMPTY) {
			this.grid[y][x] = player;
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * Clears the grid of pieces
	 */
	public void clearGrid(){
		this.grid = new int[getSize()][getSize()];
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				grid[j][i] = EMPTY;
			}
		}
	}
	
	/**
	 * Check if a player has 5 in row
	 * 
	 * @param player the player to check for
	 * @return true if player has 5 in row, false otherwise
	 */
	public boolean isWinner(int player){
		boolean INROW = false;
		for (int i = 0; i < this.gridsize; i++) {
			for (int j = 0; j < this.gridsize; j++) {
				if(this.grid[j][i] == player) {
					
				}
			}
		}
		return false;
	}
	
}
