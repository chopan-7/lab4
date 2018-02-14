package lab4.data;

import java.util.*;

/**
 * 
 * @author Chonratid Pangdee, Anton Johansson
 *
 */
public class GameGrid extends Observable {	
	public static final int EMPTY = 0;
	public static final int ME = 0;
	public static final int OTHER = 0;
	
	// Constructor
	public GameGrid(int size) {
		
	}
	
	public int getLocation(int x, int y) {
		return 0;
	}
	
	public int getSize() {
		return 0;
	}
	
	public boolean move(int x, int y, int player) {
		return false;
	}
	
	public void clearGrid() {
		
	}
	
	public boolean isWinner(int player) {
		return false;
	}
}
