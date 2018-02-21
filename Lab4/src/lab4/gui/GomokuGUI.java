package lab4.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import lab4.client.GomokuClient;
import lab4.data.GomokuGameState;

/*
 * The GUI class
 */

public class GomokuGUI implements Observer {

	private GamePanel gameGridPanel;
	private GomokuClient client;
	private GomokuGameState gamestate;
	private JButton connectButton, newGameButton, disconnectButton;
	private JLabel messageLabel;

	/**
	 * The constructor
	 * 
	 * @param g
	 *            The game state that the GUI will visualize
	 * @param c
	 *            The client that is responsible for the communication
	 */
	public GomokuGUI(GomokuGameState g, GomokuClient c) {
		this.client = c;
		this.gamestate = g;
		client.addObserver(this);
		gamestate.addObserver(this);
		JFrame frame = new JFrame();
		connectButton = new JButton("Connect");
		newGameButton = new JButton("New Game");
		disconnectButton = new JButton("Disconnect");
		messageLabel = new JLabel("Welcome to Gomoku");
		gameGridPanel = new GamePanel(this.gamestate.getGameGrid());
		gameGridPanel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					try {
						int[] a = gameGridPanel.getGridPosition(e.getX(), e.getY());
						gamestate.move(a[0], a[1]);
					} catch (IndexOutOfBoundsException exception) {
						messageLabel.setText("Try pressing on the field.");
					}
				}
			}
		});
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				messageLabel.setText("Type in the IP to connect to.");
				new ConnectionWindow(client);
			}
		});
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gamestate.newGame();
			}
		});
		disconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gamestate.disconnect();
			}
		});

		newGameButton.setEnabled(false);
		disconnectButton.setEnabled(false);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = frame.getContentPane();
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		contentPane.add(connectButton);
		contentPane.add(newGameButton);
		contentPane.add(disconnectButton);
		contentPane.add(messageLabel);
		contentPane.add(gameGridPanel);

		// SetUp Layout
		layout.putConstraint(SpringLayout.WEST, gameGridPanel, 10, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, gameGridPanel, 10, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, connectButton, 5, SpringLayout.WEST, gameGridPanel);
		layout.putConstraint(SpringLayout.NORTH, connectButton, 5, SpringLayout.SOUTH, gameGridPanel);
		layout.putConstraint(SpringLayout.WEST, newGameButton, 5, SpringLayout.EAST, connectButton);
		layout.putConstraint(SpringLayout.NORTH, newGameButton, 5, SpringLayout.SOUTH, gameGridPanel);
		layout.putConstraint(SpringLayout.WEST, disconnectButton, 5, SpringLayout.EAST, newGameButton);
		layout.putConstraint(SpringLayout.NORTH, disconnectButton, 5, SpringLayout.SOUTH, gameGridPanel);
		layout.putConstraint(SpringLayout.NORTH, messageLabel, 5, SpringLayout.SOUTH, connectButton);
		layout.putConstraint(SpringLayout.WEST, messageLabel, 5, SpringLayout.WEST, gameGridPanel);
		layout.putConstraint(SpringLayout.EAST, contentPane, 5, SpringLayout.EAST, gameGridPanel);
		layout.putConstraint(SpringLayout.SOUTH, contentPane, 5, SpringLayout.SOUTH, messageLabel);
	
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Called Automatically from the observed object
	 */
	public void update(Observable arg0, Object arg1) {
		// Update the buttons if the connection status has changed
		if (arg0 == client) {
			if (client.getConnectionStatus() == GomokuClient.UNCONNECTED) {
				connectButton.setEnabled(true);
				newGameButton.setEnabled(false);
				disconnectButton.setEnabled(false);
			} else {
				connectButton.setEnabled(false);
				newGameButton.setEnabled(true);
				disconnectButton.setEnabled(true);
			}
		}

		// Update the status text if the gamestate has changed
		if (arg0 == gamestate) {
			messageLabel.setText(gamestate.getMessageString());
		}

	}

}