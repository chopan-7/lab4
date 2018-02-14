package lab4.data;

import java.util.*;
import lab4.client.*;

/**
 * 
 * @author Chonratid Pangdee, Anton Johansson
 *
 */
public class GomokuGameState extends Observable implements Observer{
	// constructor
	public GomokuGameState(GomokuClient gc) {
		
	}
	
	public String getMessageString() {
		return "messageString";
	}
	
	public GameGrid getGameGrid() {
		return (GameGrid);
	}
	
	public void move(int x, int y) {
		
	}
	
	public void newgame() {
		
	}
	
	public void receivedNewGame() {
		
	}
	
	public void otherGuyLeft() {
		
	}
	
	public void disconnect() {
		
	}
	
	public void receivedMove(int x, int y) {
		
	}
	
	public void update(Observable arg0, Object arg1) {
		
	}
}
