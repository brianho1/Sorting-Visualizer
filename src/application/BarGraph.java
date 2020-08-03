/**
 * @author Arti Chandel <charti@seas.upenn.edu>, Brian Kendrick <btkend@seas.upenn.edu>, Brian Ho <hobr@seas.upenn.edu>
 * This class constructs a BarGraph which is an extension of JavaFx BarChart
 *  The extension includes randomizing the underlying data, drawing a single bar or all bars at once
 */


package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Platform;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class BarGraph extends BarChart<String, Number> {
	
	public static ArrayList<Data<String, Number>> bars;
	public static int number_of_bars = 30;
	public int maximum_height_of_bars = 30;
	public String
	GREEN   = "-fx-bar-fill: #56d15e",
	PURPLE   = "-fx-bar-fill: #d156a2",
	ACTIVE_BAR_COLOR   = "-fx-bar-fill: #fbff7a",
	SORTED_BAR_COLOR   = "-fx-bar-fill: #e86e27";

	/**
	 * BarGraph is an extension of JavaFx class BarChart with added capacity to randomize barData and desired visualization effects
	 * @param xAxis
	 * @param yAxis
	 */
    public BarGraph(Axis xAxis, Axis yAxis) {

        super(xAxis, yAxis);
        
		this.getYAxis().setTickLabelsVisible(false);
		this.getXAxis().setTickLabelsVisible(false);
		this.setLegendVisible(false);
		this.getXAxis().setOpacity(0);
		this.getYAxis().setOpacity(0);
		this.setHorizontalGridLinesVisible(false);
		this.setVerticalGridLinesVisible(false);
		this.setBarGap(0.1);
		this.setAnimated(false);
		
		bars = new ArrayList<Data<String, Number>>();
		final Series<String, Number> data = new Series<>();
		this.getData().add(data);
		for (int i = 0; i < BarGraph.number_of_bars; i++) {
			bars.add(new Data<>(Integer.toString(i+1), 1)); //assign all bars value of 1 at initiation, will randomize later
			data.getData().add(bars.get(i));
			paintBarAtIndex(i, GREEN);
		}


    }
    
    /** 
     * randomizing all bars of a graph
     */
	public void randomize() {
		Random random = new Random();
		//System.out.println(number_of_bars);
		for (int i = 0; i < BarGraph.number_of_bars; i++) {
			assignBarValueAtIndex(i, random.nextInt(maximum_height_of_bars) + 1);
		}
		visualizeAllBars(GREEN);
	}

	/**
	 * assigning a bar a new height at index 
	 * @param index
	 * @param value
	 */
	public void assignBarValueAtIndex(int index, int value) {
		bars.get(index).setYValue(value);
	}

	/**
	 * return the height of a bar at index
	 * @param index
	 * @return
	 */
	public int getBarValue(int index) {
		return (int) bars.get(index).getYValue();
	}
	
	/**
	 * apply style to bar at index
	 * @param index
	 * @param style
	 */
	public void paintBarAtIndex(int index, final String style) {
		Platform.runLater(() -> {
			bars.get(index).getNode().setStyle(style);
		});
	}
	
	/**
	 * draw all bar using style
	 * @param style
	 */
	public void visualizeAllBars(final String style) {
		Platform.runLater(() -> {
			for (int i = 0; i < BarGraph.number_of_bars; i++) {
				paintBarAtIndex(i, style);
			}
		});
	}



}
