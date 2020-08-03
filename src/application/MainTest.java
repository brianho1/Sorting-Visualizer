/**
 * @author Arti Chandel <charti@seas.upenn.edu>, Brian Kendrick <btkend@seas.upenn.edu>, Brian Ho <hobr@seas.upenn.edu>
 * This methods test elements of the user interface and helper classes 
 * The FxTest tool requires a couple external libraries to function properly. The Jar files are available on github to download
 * to add the jar files to the project, right click on the project > Build Path > Configure Build Path .. > Add External Jars
 * 
 * Note: Due to inherent limitation of MacOS privacy security, this JUnit test that use testFX works best with Windows
 * 
 */


package application;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.ComboBoxMatchers;
import org.testfx.matcher.control.LabeledMatchers;

import static org.junit.Assert.*;

public class MainTest extends ApplicationTest {

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewController view = new ViewController();
		Scene scene = new Scene(view, ViewController.WIDTH, ViewController.HEIGHT);
		primaryStage.setTitle("Sorting Visualizer - CIT 591 Project");
		// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
		FxToolkit.hideStage();
		release(new KeyCode[] {});
		release(new MouseButton[] {});
	}

	@Test
        public void testNormal() {
        	assertTrue(true);
    	}
	
	@Test
	public void labelNumberOfBarsReadsCorrectly() {
		FxAssert.verifyThat("How many bars? :", LabeledMatchers.hasText("How many bars? :"));
	}
  
	
	@Test
	public void runButtonCanBeClicked() {
		clickOn("Run");
		return;
	}

	@Test
	public void runButtonShowsTextRun() {
		FxAssert.verifyThat("Run", LabeledMatchers.hasText("Run"));
	}

	@Test
	public void randomizeButtonCanBeClicked() {
		clickOn(".button");
		return;
	}

	@Test
	public void randomizeButtonShowsTextRandomize() {
		FxAssert.verifyThat(".button", LabeledMatchers.hasText("Randomize"));
	}

	@Test
	public void comboBoxSortingAlgorithmContainsSixSortMethods() {
		FxAssert.verifyThat(".combo-box-base", ComboBoxMatchers.hasItems(6));
	}
	
	@Test
    	public void comboBoxContainsSortingInExactOrder() {
        	FxAssert.verifyThat(".combo-box-base", ComboBoxMatchers.containsExactlyItemsInOrder("Insertion sort", "Quick sort", "Heap sort", "Merge sort", "Bubble sort", "Selection sort"));
    	}

	@Test
	public void comboBoxSortingAlgorithmCanBeClicked() {
		clickOn(".combo-box-base");
		type(KeyCode.DOWN);
		type(KeyCode.ENTER);
		return;
	}

	@Test
	public void comboBoxSortingAlgorithmContainsInsertionSort() {
		clickOn(".combo-box-base");
		type(KeyCode.DOWN);
		type(KeyCode.ENTER);
		FxAssert.verifyThat(".combo-box-base", ComboBoxMatchers.hasSelectedItem("Insertion sort"));
	}

	@Test
	public void comboBoxSortingAlgorithmContainsQuickSort() {
		clickOn(".combo-box-base");
		type(KeyCode.DOWN);
		type(KeyCode.DOWN);
		type(KeyCode.ENTER);
		FxAssert.verifyThat(".combo-box-base", ComboBoxMatchers.hasSelectedItem("Quick sort"));
	}

	@Test
	public void comboBoxSortingAlgorithmContainsHeapSort() {
		clickOn(".combo-box-base");
		type(KeyCode.DOWN);
		type(KeyCode.DOWN);
		type(KeyCode.DOWN);
		type(KeyCode.ENTER);
		FxAssert.verifyThat(".combo-box-base", ComboBoxMatchers.hasSelectedItem("Heap sort"));
	}

	@Test
	public void comboBoxSortingAlgorithmContainsMergeSort() {
		clickOn(".combo-box-base");
		type(KeyCode.DOWN);
		type(KeyCode.DOWN);
		type(KeyCode.DOWN);
		type(KeyCode.DOWN);
		type(KeyCode.ENTER);
		FxAssert.verifyThat(".combo-box-base", ComboBoxMatchers.hasSelectedItem("Merge sort"));
	}

	@Test
	public void comboBoxSortingAlgorithmContainsBubbleSort() {
		clickOn(".combo-box-base");
		type(KeyCode.DOWN);
		type(KeyCode.DOWN);
		type(KeyCode.DOWN);
		type(KeyCode.DOWN);
		type(KeyCode.DOWN);
		type(KeyCode.ENTER);
		FxAssert.verifyThat(".combo-box-base", ComboBoxMatchers.hasSelectedItem("Bubble sort"));
	}

	@Test
	public void comboBoxSortingAlgorithmContainsSelectionSort() {
		clickOn(".combo-box-base");
		type(KeyCode.DOWN);
		type(KeyCode.DOWN);
		type(KeyCode.DOWN);
		type(KeyCode.DOWN);
		type(KeyCode.DOWN);
		type(KeyCode.DOWN);
		type(KeyCode.ENTER);
		FxAssert.verifyThat(".combo-box-base", ComboBoxMatchers.hasSelectedItem("Selection sort"));
	}
	/* This test verify number of bars tab can be clicked*/
	@Test
	public void comboBoxnumberOfBarsCanBeClicked() {
		clickOn(".combo-box-base");
		type(KeyCode.DOWN);
		type(KeyCode.ENTER);
		return;
	}
	/* This test verify total number of bars tab  options are available six*/
    @Test
	public void comboBoxnumberOfBarsoptionsContainsSixBarOptions() {
		FxAssert.verifyThat(".combo-box-base", ComboBoxMatchers.hasItems(6));
	}
}
