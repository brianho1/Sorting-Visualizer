/**
 * @author Arti Chandel <charti@seas.upenn.edu>, Brian Kendrick <btkend@seas.upenn.edu>, Brian Ho <hobr@seas.upenn.edu>
 * This main class is the application loader 
 *
 */
package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


/**
 * This is the main application loader that load the program from the ViewController to the underlying Scene.
 * This is a standard implementation for JavaFx projects
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			ViewController view = new ViewController();
			Scene scene = new Scene(view,ViewController.WIDTH,ViewController.HEIGHT);
			primaryStage.setTitle("Sorting Visualizer - CIT 591 Project");
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
