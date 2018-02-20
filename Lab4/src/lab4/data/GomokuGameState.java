/*
 * Created on 2007 feb 8
 */
package lab4.data;
import java.util.Observable;
import java.util.Observer;

import lab4.client.GomokuClient;

/**
 * Represents the state of a game
 */
public class GomokuGameState extends Observable implements Observer {

	// Game variables
	private final int DEFAULT_SIZE = 10;
	private GameGrid gameGrid;

	// Possible game states
	private final int NOT_STARTED = 0;
	private final int MY_TURN = 1;
	private final int OTHER_TURN = 2;
	private final int FINISHED = 3;
	private int currentState;

	private GomokuClient client;

	private String message;

	/**
	 * The constructor
	 * 
	 * @param gc
	 *            The client used to communicate with the other player
	 */
	public GomokuGameState(GomokuClient gc) {
		client = gc;
		client.addObserver(this);
		gc.setGameState(this);
		currentState = NOT_STARTED;
		gameGrid = new GameGrid(DEFAULT_SIZE);
	}

	/**
	 * Returns the message string
	 * 
	 * @return the message string
	 */
	public String getMessageString() {
		return message;
	}

	/**
	 * Returns the game grid
	 * 
	 * @return the game grid
	 */
	public GameGrid getGameGrid() {
		return gameGrid;
	}

	/**
	 * This player makes a move at a specified location
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 */
	public void move(int x, int y) {
		if (currentState == MY_TURN) {
			if (!this.gameGrid.move(x, y, MY_TURN)) {
				// new message if move is illegal
				message = "Illegal move. Choose another positoin.";
				setChanged();
				notifyObservers();
			}
			else {
				// moves the player to grid position if empty
				this.gameGrid.move(x, y, MY_TURN);
				client.sendMoveMessage(x, y);
				message = "It is now your opponents turn.";
				this.currentState=OTHER_TURN;
	
				// check if player has made the winning move
				if (this.gameGrid.isWinner(MY_TURN)) {
					message = "You won!";
					currentState = FINISHED;
				}
				setChanged();
				notifyObservers();
			}
		}

	}

	/**
	 * Starts a new game with the current client
	 */
	public void newGame() {
		gameGrid.clearGrid();
		this.currentState = OTHER_TURN;
		message = "A new game is started. Waiting for the other player...";
		client.sendNewGameMessage();
		setChanged();
		notifyObservers();
	}

	/**
	 * Other player has requested a new game, so the game state is changed
	 * accordingly
	 */
	public void receivedNewGame() {
		gameGrid.clearGrid();
		this.currentState = MY_TURN;
		message = "A new game is started. It's your turn.";
		setChanged();
		notifyObservers();

	}

	/**
	 * The connection to the other player is lost, so the game is interrupted
	 */
	public void otherGuyLeft() {
		gameGrid.clearGrid();
		message = "The other player left the game.";
		this.currentState = FINISHED;
		setChanged();
		notifyObservers();
	}

	/**
	 * The player disconnects from the client
	 */
	public void disconnect() {
		gameGrid.clearGrid();
		message = "You have left the game.";
		notifyObservers();
		client.disconnect();
	}

	/**
	 * The player receives a move from the other player
	 * 
	 * @param x
	 *            The x coordinate of the move
	 * @param y
	 *            The y coordinate of the move
	 */
	public void receivedMove(int x, int y) {
		if (!gameGrid.move(x, y, OTHER_TURN)) {
			message = "Illegal move. Choose another position.";
		} else {
			// moves the player to grid position if empty
			this.gameGrid.move(x, y, OTHER_TURN);
			client.sendMoveMessage(x, y);
			message = "It is now your turn.";
			currentState=MY_TURN;

			// check if player has made the winning move
			if (this.gameGrid.isWinner(OTHER_TURN)) {
				message = "You lost..";
				currentState = FINISHED;
			}
			setChanged();
			notifyObservers();
		}
	}

	public void update(Observable o, Object arg) {

		switch (client.getConnectionStatus()) {
		case GomokuClient.CLIENT:
			message = "Game started, it is your turn!";
			currentState = MY_TURN;
			break;
		case GomokuClient.SERVER:
			message = "Game started, waiting for other player...";
			currentState = OTHER_TURN;
			break;
		}
		setChanged();
		notifyObservers();

	}

}