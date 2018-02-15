<<<<<<< HEAD
package lab4.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import lab4.data.GameGrid;

/**
 * A panel providing a graphical view of the game board
 */

public class GamePanel extends JPanel implements Observer{

	private final int UNIT_SIZE = 20;
	private GameGrid grid;
	
	/**
	 * The constructor
	 * 
	 * @param grid The grid that is to be displayed
	 */
	public GamePanel(GameGrid grid){
		this.grid = grid;
		grid.addObserver(this);
		Dimension d = new Dimension(grid.getSize()*UNIT_SIZE+1, grid.getSize()*UNIT_SIZE+1);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
		this.setBackground(Color.WHITE);
	}

	/**
	 * Returns a grid position given pixel coordinates
	 * of the panel
	 * 
	 * @param x the x coordinates
	 * @param y the y coordinates
	 * @return an integer array containing the [x, y] grid position
	 */
	public int[] getGridPosition(int x, int y){}
	
	public void update(Observable arg0, Object arg1) {
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
	}
	
}
=======
package lab4.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import lab4.data.GameGrid;

/**
 * A panel providing a graphical view of the game board
 */

public class GamePanel extends JPanel implements Observer {

	private final int UNIT_SIZE = 20;
	private GameGrid grid;

	/**
	 * The constructor
	 * 
	 * @param grid
	 *            The grid that is to be displayed
	 */
	public GamePanel(GameGrid grid) {
		this.grid = grid;
		grid.addObserver(this);
		Dimension d = new Dimension(grid.getSize() * UNIT_SIZE + 1, grid.getSize() * UNIT_SIZE + 1);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
		this.setBackground(Color.WHITE);
	}

	/**
	 * Returns a grid position given pixel coordinates of the panel
	 * 
	 * @param x
	 *            the x coordinates
	 * @param y
	 *            the y coordinates
	 * @return an integer array containing the [x, y] grid position
	 */
	public int[] getGridPosition(int x, int y) throws IndexOutOfBoundsException{
		if(x>grid.getSize()*UNIT_SIZE||y>grid.getSize()*UNIT_SIZE) {
			throw new IndexOutOfBoundsException("X or Y value to big");
		}
		return new int[] { x / UNIT_SIZE, y / UNIT_SIZE };
	}

	public void update(Observable arg0, Object arg1) {
		this.repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Color prevcolor=g.getColor();
		for (int i = 0, size = grid.getSize(); i < size; i++) {
			g.setColor(Color.BLACK);
			if(i==0) {
				g.drawLine(size * UNIT_SIZE, 0, size * UNIT_SIZE, size * UNIT_SIZE);
				g.drawLine(0, size * UNIT_SIZE, size * UNIT_SIZE, size * UNIT_SIZE);
			}
			g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, size * UNIT_SIZE);
			g.drawLine(0, i * UNIT_SIZE, size * UNIT_SIZE, i * UNIT_SIZE);
			for (int j = 0; j < size; j++) {
				switch (grid.getLocation(j, i)) {
				case (1): //If me is here
					g.setColor(Color.BLUE);
					g.drawLine((j) * UNIT_SIZE + 1, (i) * UNIT_SIZE + 1, (j + 1) * UNIT_SIZE - 1,
							(i + 1) * UNIT_SIZE - 1);
					g.drawLine((j) * UNIT_SIZE + 1, (i + 1) * UNIT_SIZE + 1, (j + 1) * UNIT_SIZE - 1,
							(i) * UNIT_SIZE - 1);
					break;
				
				case(2): //If other is here
					g.setColor(Color.RED);
					g.drawOval((j) * UNIT_SIZE + 1, (i) * UNIT_SIZE + 1, (j + 1) * UNIT_SIZE - 1,
							(i + 1) * UNIT_SIZE - 1);
					break;
				default: //If Empty is here
					break;
				}	
			}
		}
	}

}
>>>>>>> branch 'master' of https://github.com/chopan-7/lab4.git
