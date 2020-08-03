/**
 * @author Arti Chandel <charti@seas.upenn.edu>, Brian Kendrick <btkend@seas.upenn.edu>, Brian Ho <hobr@seas.upenn.edu>
 * This class controls all the view elements of the application including displaying bar chart, buttons, comboBoxes, etc
 */

package application;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

/**
 * 
 * ViewController is the main GUI control class which is an extension of BorderPane, a JavaFX class
 */
public class ViewController extends BorderPane {

	public static final int WIDTH = 900;
	public static final int HEIGHT = 700;
	private static final int maximum_height_of_bars = 30; //implemented as a static class since this will be shared across different instance of the same instance variable
	private FlowPane inputFlowPane;
	private BarGraph barChart;
	private SortingAlgo sorting;
	final Button runButton = new Button("Run");
	String value;
	String algoComboOptionPicked = "";

	public ArrayList<Data<String, Number>> bars;

	/**
	 * the below method construct a ViewController class each time a new instance is created
	 * the view controller class controls all the main GUI elements of the program including 
	 * buttons, comboBox, BarChart etc.
	 */
	public ViewController() {
		this.setPadding(new Insets(10));
		barChart = new BarGraph(new CategoryAxis(), new NumberAxis(0, maximum_height_of_bars, 0));
		this.setCenter(barChart);
		inputFlowPane = new FlowPane();
		inputFlowPane.setHgap(5);
		inputFlowPane.setVgap(5);
		this.setBottom(inputFlowPane);
		this.generateNewButton("Randomize", barChart::randomize);
		Platform.runLater(barChart::randomize); //generate data for the first time
		sorting = new SortingAlgo(barChart);
		inputFlowPane.getChildren().add(runButton);
		ObservableList<String> numberOfBarsoptions = FXCollections.observableArrayList("10","20","30","40","50","60");
		ObservableList<String> sortingAlgoOptions = FXCollections.observableArrayList(
						"Insertion sort",
						"Quick sort",
						"Heap sort",
						"Merge sort",
						"Bubble sort",
						"Selection sort"
						);


		final ComboBox<String> comboBoxNumberOfBars = new ComboBox<String>(numberOfBarsoptions);
		comboBoxNumberOfBars.getSelectionModel().select(2);

		//Listener for values change in comboBox - number of Bars
		comboBoxNumberOfBars.valueProperty().addListener(new ChangeListener<String>() {
			@Override public void changed(ObservableValue ov, String t, String t1) {
				value = t1;
				BarGraph.number_of_bars = Integer.parseInt(value);
				System.out.println(BarGraph.number_of_bars);
				changeComboValue();
			}    
		});
		
		//Listener for values change in comboBox - algorithm Picked
		final ComboBox<String> comboBoxSortingAlgorithm = new ComboBox<String>(sortingAlgoOptions);
		comboBoxSortingAlgorithm.valueProperty().addListener(new ChangeListener<String>() {
			@Override public void changed(ObservableValue ov, String t, String t1) {
				algoComboOptionPicked = t1;
				changeAlgorithmValue(algoComboOptionPicked);
				changeComboValue();
			}

		});

		Label  labelNumberOfBars = new Label("How many bars? :");
		Label labelAlgo = new Label("Pick an algorithm :");
		inputFlowPane.getChildren().addAll(labelAlgo, comboBoxSortingAlgorithm, labelNumberOfBars,comboBoxNumberOfBars);


	}
	
	/**
	 * this method reload values of BarChart when a new item is selected from a comboBox.
	 * This method helps register the selections to the other 2 classes i.e. BarGraph and SortingAlo
	 */
	private void reloadBarChart() {
		this.setCenter(null);
		barChart = new BarGraph(new CategoryAxis(), new NumberAxis(0, maximum_height_of_bars, 0));
		this.setCenter(barChart);
		BarGraph.bars = new ArrayList<Data<String, Number>>(); //point barsArrayList to a new set of Data 
		final Series<String, Number> data2 = new Series<>(); // generating new series to add to barchart
		barChart.getData().setAll(data2);
		for (int i = 0; i < BarGraph.number_of_bars; i++) {
			BarGraph.bars.add(new Data<>(Integer.toString(i+1), 1));
			data2.getData().add(BarGraph.bars.get(i));
			barChart.paintBarAtIndex(i, barChart.GREEN);
		}
		SortingAlgo.barsArrayList = BarGraph.bars;
		SortingAlgo.graph = barChart;
	}

	/**
	 * this method generate a new button with a label and associate the button with a method to be run
	 * @param label
	 * @param method
	 */
	private void generateNewButton(String label, Runnable method) {
		final Button newButton = new Button(label);
		newButton.setOnAction(event -> new Thread(() -> {
			Platform.runLater(() -> inputFlowPane.setDisable(true));
			method.run();
			Platform.runLater(() -> inputFlowPane.setDisable(false));
		}).start());
		inputFlowPane.getChildren().add(newButton);
	}


	/**
	 * this method helps trigger the Run button and disables other items in GUI to avoid user tampering with the program while it runs
	 * @param runButton
	 * @param method
	 */
	public void runAMethod(Button runButton,Runnable method) {
		runButton.setOnAction(event -> new Thread(() -> {
			Platform.runLater(() -> inputFlowPane.setDisable(true));
			method.run();
			Platform.runLater(() -> inputFlowPane.setDisable(false));
		}).start());
	}

	/**
	 * this method help reload the BarChart and randomize the underlying data
	 */
	private void changeComboValue() {
		reloadBarChart();
		Platform.runLater(barChart::randomize);
	}

	/**
	 * this helper methods will call the corresponding sorting algo based on the userSelection "algoPicked"
	 * @param algoPicked
	 */
	private void changeAlgorithmValue(String algoPicked) {

		if (algoPicked.equals("Insertion sort")) {
			runAMethod(runButton, sorting::insertionSort);
		}
		else if (algoPicked.equals("Quick sort")) {
			runAMethod(runButton, sorting::quickSort);

		}
		else if (algoPicked.equals("Heap sort")) {
			runAMethod(runButton, sorting::heapSort);
		}
		else if (algoPicked.equals("Merge sort")) {
			runAMethod(runButton, sorting::merSortRunner);
		}
		else if (algoPicked.equals("Bubble sort")) {
			runAMethod(runButton, sorting::bubbleSort);
		}
		else if (algoPicked.equals("Selection sort")) {
			runAMethod(runButton, sorting::selectionSort);
		}
		else {
			runAMethod(runButton, barChart::randomize);
		}

	}

}
