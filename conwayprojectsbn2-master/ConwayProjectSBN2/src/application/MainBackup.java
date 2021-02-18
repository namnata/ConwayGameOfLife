package application;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainBackup extends Application {

	private GridPane grid;

	// ArrayList<Cell> circles = new ArrayList<>();

	boolean flag = false;
	//these are the dimenions of the grid and will later need to be infinite
	private int ROWS = 300;
	private int COLS = 300;
	
	
	private int[][] thegrid;
	Grid g = new Grid(4, 5);

	private Color defaultColor;

	private static final int GRIDSIZE = 300;
	private static final int CELLSIZE = 10;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		BorderPane border = new BorderPane();

		HBox hbox = addHBox();

		border.setTop(hbox);

		this.grid = addGridPane();
		border.setCenter(grid);

		Scene scene = new Scene(border, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

		draw();

	}

	private void draw() {

		//g.printGrid();

		// System.out.println("DRAW");
		// TODO Auto-generated method stub
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				Rectangle rect = new Rectangle(r, c, CELLSIZE, CELLSIZE);
				this.grid.add(rect, r, c);
			}
		}

		Grid g = new Grid(4, 5);

		//g.printGrid();

		System.out.println("===========================");

		g.getGrid()[0][0].setAlive(!g.getGrid()[0][0].isAlive());
		System.out.println("===========================");

	}

	public MainBackup() {
		// TODO Auto-generated constructor stub

	}

//	

	private void start(Button btnStart) {
		this.flag = !this.flag;
		if (flag)
			btnStart.setText("Stop Moving");
		else
			btnStart.setText("Start Moving");
	}

	public HBox addHBox() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: #336699;");

		// Stage btnStart = new Button("Start");
		Button btnStart = new Button("Start");
		btnStart.setPrefSize(100, 20);
		btnStart.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> start(btnStart));

		Button btnNext = new Button("Next");
		btnNext.setPrefSize(100, 20);
		btnNext.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> Next());

		Button btnReset = new Button("Reset");
		btnReset.setPrefSize(100, 20);
		btnReset.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> reset());

		hbox.getChildren().addAll(btnStart, btnNext, btnReset);
		return hbox;
	}
	public void Next() {
		
	}

	private Object reset() {
		// TODO Auto-generated method stub

		return null;
	}

	public GridPane addGridPane() {
		GridPane grid = new GridPane();
		grid.setStyle("-fx-background-color: #999999;");
		grid.setHgap(1);
		grid.setVgap(1);
		grid.setPadding(new Insets(0, 0, 0, 0));

		return grid;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}


//	//Grid g = new Grid(4, 5);
//
////    int[][] next = new int[ROWS][COLS];
//    int[][] next = new int[g.getRows()][g.getColumns()];
//
//    for (int i = 0; i < g.getRows(); i++) {
//        for (int j = 0; j < g.getColumns(); j++) {
//            int neighbors = countAlive(i, j);
//
//            if (neighbors == 3) {
//                next[i][j] = 1;
//            }
//            else if (neighbors < 2 || neighbors > 3) {
//                next[i][j] = 0;
//            }
//            else {
////                next[i][j] = thegrid[i][j];
//                next[i][j] = thegrid[i][j];
//
//            }
//        }
//    }
//    thegrid = next;
////	g.getGrid()[0][0].setAlive(!g.getGrid()[0][0].isAlive());
////	System.out.println("===========================");
//    draw();
//}
//
//
//private int countAlive(int i, int j) {
//    int sum = 0;
//    //need to add logic to count neigbours
//    
//    
//    
//	return sum;
//    
//    
//}