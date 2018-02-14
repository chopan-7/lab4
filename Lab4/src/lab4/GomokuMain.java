package lab4;

import java.util.*;
import java.lang.*;
import lab4.client.*;
import lab4.data.*;
import lab4.gui.*;

/**
 * 
 * @author Chonratid Pangdee, Anton Johansson
 *
 */
public class GomokuMain {
	
	// main method takes port number as argument
	public static void main(String[] args) {
		int port = 4000;	// default port is used if no argument is given to main
		try {
			if (Integer.parseInt(args[0]) !=  0) {
				port = Integer.parseInt(args[0]);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// do nothing
		}
		
		
		
		// create three objects 
		GomokuClient client = new GomokuClient(port); // takes port number as argument
		GomokuGameState gamestate = new GomokuGameState(client); // takes client as argument
		GomokuGUI gameGUI = new GomokuGUI(gamestate, client);
		
		
	}
}
