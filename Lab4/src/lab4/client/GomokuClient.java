package lab4.client;

import java.util.*;

/** Hej
 * 
 * @author Chonratid Pangdee, Anton Johansson
 *
 */
public class GomokuClient extends Observable implements Runnable{
	public static final int UNCONNECTED = 0;
	public static final int SERVER = 0;
	public static final int CLIENT = 0;
	
	public void run() {
		
	}
	
	public GomokuClient(int portnr) {
		
	}
	
	public void foundConnection(Printwriter writer, BufferedReader reader,
			boolean anClient) {
		
	}
	
	public void setGameState(GameState gs) {
		
	}
	
	public boolean sendMoveMessage(int x, int y) {
		return false;
	}
	
	public boolean sendNewGameMessage() {
		return false;
	}
	
	public void disconnect() {}
	
	public int getConnectionStatus() {
		return 0;
	}
}
