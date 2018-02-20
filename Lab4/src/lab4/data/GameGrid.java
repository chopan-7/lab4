package lab4.data;

import java.util.Observable;

/**
 * Represents the 2-d game grid
 */

public class GameGrid extends Observable{
	public static final int EMPTY = 0;
	public static final int ME = 1;
	public static final int OTHER = 2;
	public static final int INROW = 2;
	
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
			setChanged();
			notifyObservers();
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
		for (int y = 0; y < getSize(); y++) {
			for (int x = 0; x < getSize(); x++) {
				grid[y][x] = EMPTY;
			}
		}
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Check if player has 5 in a row in any direction.
	 * 
	 * @param x	starting position for x
	 * @param y starting position for y
	 * @param stepx	how to move in x-direction
	 * @param stepy how to move in y-direction
	 * @param count counter for inRow. Starts from 0.
	 * @param player the player to check for
	 * @return	true if player has INROW in row, false otherwise
	 */
	private boolean inRow(int x, int y, int stepx, int stepy, int count, int player) {
		if (count == INROW) {return true;}
		try {
			if (this.grid[y][x] == player) {
				return inRow(x+stepx, y+stepy, stepx, stepy, ++count, player);
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
		return false;	
	}
	
	/**
	 * Check if a player has 5 in row
	 * 
	 * @param player the player to check for
	 * @return true if player has 5 in row, false otherwise
	 */
	public boolean isWinner(int player){
		for (int y = 0; y < this.gridsize; y++) {
			for (int x = 0; x < this.gridsize; x++) {
				if(this.grid[y][x] == player) {
					for (int i = -1; i <= 1; i++) {
						if (inRow(x, y, i, 1, 0, player)) {
							return true;
						}
					}
					if (inRow(x,y, 1, 0, 0, player)) {
						return true;
					}
				}
			}
		}
		return false;
	}
}