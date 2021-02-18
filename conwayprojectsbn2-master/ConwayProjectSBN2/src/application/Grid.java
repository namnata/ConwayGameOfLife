package application;

import org.w3c.dom.css.Rect;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Grid {

	Cell[][] grid;
	private int rows, columns;
	
/*Grid constructor uses rows and columns values form the Main to create a 2D array of object Cell
 * which takes an empty constructor and thus contains a randomised boolean value.*/
	public Grid(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;

		this.grid = new Cell[rows][columns];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cell = new Cell();
				this.grid[i][j] = cell;
				
//cell statuses can be hard coded here to use shapes for testing
			}
		}

	}
/*creates a Cell object at each coordinate of the grid and sets the colour to black or aqua
according to Cell boolean. Mouse clicker can turn dead cell alive and change its colour.
All rectangles are added to the panel at respective coordinates. 
*/
	public void printGrid(GridPane panel) {

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				Rectangle rect = new Rectangle(c, r, 6, 6);
				rect.setOnMouseClicked(e -> {
					rect.setFill(Color.AQUA);
					this.grid[(int) rect.getY()][(int) rect.getX()].setAlive(true);
				});
				if (grid[r][c].isAlive()) {
					rect.setFill(Color.AQUA);
				} else {
					rect.setFill(Color.BLACK);
				}
				panel.add(rect, c, r);
			}
		}

	}

//Cell status  and colour is updated according to the rules and cells are added to nextGrid accordingly 
	public void runGame(GridPane panel) {
		Cell[][] nextGrid = new Cell[rows][columns];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {

				int liveNeighbours = liveNeighbours(r, c);

				if (grid[r][c].isAlive() && liveNeighbours < 2) {
					nextGrid[r][c] = new Cell(false);

				} else if (grid[r][c].isAlive() && (liveNeighbours == 2 || liveNeighbours == 3)) {
					nextGrid[r][c] = new Cell(true);

				} else if (grid[r][c].isAlive() && liveNeighbours > 3) {
					nextGrid[r][c] = new Cell(false);

				} else if (!grid[r][c].isAlive() && liveNeighbours == 3) {
					nextGrid[r][c] = new Cell(true);

				} else {
					nextGrid[r][c] = grid[r][c];
				}
			}
		}
		
//old grid is set as new gris, the panel is cleared and the grid is reprinted
		
		grid = nextGrid;
		panel.getChildren().clear();
		printGrid(panel);
	}

/*this method calls the getCellStaus method for each of the eight neighbouring cells and if 
 * the boolean returned is true, increases liveCells count by 1*/
	public int liveNeighbours(int r, int c) {
		int liveCells = 0;

		if (getCellStatus(r - 1, c + 1, grid)) {
			liveCells += 1;
		}
		if (getCellStatus(r, c + 1, grid)) {
			liveCells += 1;
		}
		if (getCellStatus(r + 1, c + 1, grid)) {
			liveCells += 1;
		}
		if (getCellStatus(r + 1, c, grid)) {
			liveCells += 1;
		}
		if (getCellStatus(r + 1, c - 1, grid)) {
			liveCells += 1;
		}
		if (getCellStatus(r, c - 1, grid)) {
			liveCells += 1;
		}
		if (getCellStatus(r - 1, c - 1, grid)) {
			liveCells += 1;
		}
		if (getCellStatus(r - 1, c, grid)) {
			liveCells += 1;
		}
		return liveCells;
	}
/*this method returns a true boolean if the cell is alive and returns false if the cell is dead or
outside the rows and columns*/
	public boolean getCellStatus(int r, int c, Cell[][] grid) {
		if (r >= rows || r < 0) {
			return false;
		}
		if (c >= columns || c < 0) {
			return false;
		}

		return grid[r][c].isAlive();

	}
//setters and getter are not currently used

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public Cell[][] getGrid() {
		return grid;
	}

	public void setGrid(Cell[][] grid) {
		this.grid = grid;
	}

}
