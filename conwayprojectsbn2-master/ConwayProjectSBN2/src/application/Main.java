package application;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	private GridPane grid;
	boolean flag = false;

	Grid g = new Grid(50, 50);

	public void start(Stage primaryStage) throws Exception {

		BorderPane border = new BorderPane();

		HBox hbox = addHBox();

		border.setTop(hbox);

		this.grid = addGridPane();
		border.setCenter(grid);

		Scene scene = new Scene(border, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

		g.printGrid(this.grid);
		animate();
	}


	public HBox addHBox() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: #336699;");

		Button btnStart = new Button("Start");
		btnStart.setPrefSize(100, 20);
		btnStart.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> start(btnStart));

		Button btnNext = new Button("Next");
		btnNext.setPrefSize(100, 20);
		btnNext.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> Next());

		hbox.getChildren().addAll(btnStart, btnNext);
		return hbox;
	}

	public GridPane addGridPane() {
		GridPane grid = new GridPane();
		grid.setStyle("-fx-background-color: #999999;");
		grid.setHgap(1);
		grid.setVgap(1);
		grid.setPadding(new Insets(0, 0, 0, 0));

		return grid;
	}

	private void start(Button btnStart) {
		this.flag = !this.flag;
		if (flag)
			btnStart.setText("Stop Moving");
		else
			btnStart.setText("Start Moving");
	}

	private void animate() {
		try {

			Transition transition = new Transition() { // creates transition
				{
					//cycle duration appears not to affect the speed of the animation...
					//the speed seems to increase as the animation progresses? 
					setCycleDuration(Duration.millis(300));
				}

				@Override
				protected void interpolate(double frac) {
					if (flag == false) // flag for start button pressed to change text
						return;

					g.runGame(grid);

				}

			};
			transition.setCycleCount(Animation.INDEFINITE);
			transition.setAutoReverse(true);
			transition.play();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Next() {
		g.runGame(grid);

	}

	public static void main(String[] args) {
		launch(args);

	}
}
