/**
 * @author Arti Chandel <charti@seas.upenn.edu>, Brian Kendrick <btkend@seas.upenn.edu>, Brian Ho <hobr@seas.upenn.edu>
 * This class manages all the sorting algorithms which get call based on user selections in the view controller class
 */

package application;

import java.util.ArrayList;

import javafx.scene.chart.XYChart.Data;

public class SortingAlgo {

	public static ArrayList<Data<String, Number>> barsArrayList;
	public static BarGraph graph;
	private static final int animation_delayed = 15;

	/**
	 * Constructor of the SortingAlgo Class. This class accept a BarClass instance which contains barsArrayList that will be sorted herein.
	 * @param graph
	 */
	public SortingAlgo(BarGraph graph) {
		barsArrayList = BarGraph.bars;
		//		this.graph = graph;
	}


	public void quickSort (){
		final int len = barsArrayList.size();
		smallquickSort(0, len - 1);
		graph.visualizeAllBars(graph.SORTED_BAR_COLOR);
	}

	/**
	 * The quick sort algorithm picks an element as a pivot and partitions the 
	 * given array around the picked pivot. This particular sort always picks 
	 * first element as the pivot.
	 */
	public void smallquickSort(int lowIndex, int highIndex) {

		int i = lowIndex;
		int j = highIndex;
		// calculate pivot number,taking pivot as middle index number

		int pivotIndex = (int) Math.floor ((lowIndex +highIndex)/2);// middle index as pivotindex
		int pivotvalue = graph.getBarValue(pivotIndex);// middle point  as pivot

		// Divide into two ArrayList
		while (i <= j) {
			/**
			 * In each iteration, it will identify a number from left side which 
			 * is greater then the pivot value, and also it will identify a number 
			 * from right side which is less then the pivot value. Once the search 
			 * is done, then we swap both numbers.
			 */
			while (graph.getBarValue(i) < pivotvalue) {
				i++;
			}
			while (graph.getBarValue(j) > pivotvalue) {
				j--;
			}
			if (i <= j) {
				swapNumbers(i, j);
				//move index to next position on both sides
				i++;
				j--;
			}
		}
		// call quickSort() method recursively
		if (lowIndex < j)
			smallquickSort(lowIndex, j);
		if (i < highIndex)
			smallquickSort(i, highIndex);
	}


	//this method swaps the value of the 2 bars at position i and j
	private void swapNumbers(int i, int j) {
		graph.paintBarAtIndex(i, graph.ACTIVE_BAR_COLOR);
		graph.paintBarAtIndex(j, graph.ACTIVE_BAR_COLOR);
		delay();

		int temp = graph.getBarValue(i);
		graph.paintBarAtIndex(i, graph.PURPLE);
		graph.assignBarValueAtIndex(i,graph.getBarValue(j));
		graph.paintBarAtIndex(j, graph.PURPLE);
		graph.assignBarValueAtIndex (j,temp);
		delay();

	}

	/**
	 * The selection sort algorithm sorts an array by repeatedly finding 
	 * the minimum element (considering ascending order) from unsorted 
	 * part and putting it at the beginning. The algorithm maintains two 
	 * subarrays in a given array: one sorted and one unsorted. In every 
	 * iteration of selection sort, the minimum element from the unsorted 
	 * subarray is picked and moved to the sorted subarray. 
	 */
	public void selectionSort() {
		final int len = barsArrayList.size();
		for (int i = 0; i < len-1; i++)
		{
			int min = i;
			for (int j = i+1; j < len; j++) {
				if (graph.getBarValue(j) < graph.getBarValue(min))// look for the smallest value in given array
				{min = j;} 
			}//replace min with smallest value index
			swapNumbers (i,min); // sawp values at i with minimum value 

		}
		graph.visualizeAllBars(graph.SORTED_BAR_COLOR);
	}

	/**
	 * The insertionSort() method is a simple algorithm that loops through performing 
	 * 3 actions. It pulls an element from the array, compares it to the largest value,
	 * then places it right or left of that value; left if less, right if more.
	 */
	public void insertionSort() {
		final int len = barsArrayList.size();
		int c = 0;

		while(c < len) {
			int sm = c;
			for(int i = c+1; i < len; i++) {
				if(graph.getBarValue(i) < graph.getBarValue(sm)) {
					sm = i;
				}
			}
			if(c != sm) {

				graph.paintBarAtIndex(c, graph.ACTIVE_BAR_COLOR);
				graph.paintBarAtIndex(sm, graph.ACTIVE_BAR_COLOR);
				delay();

				int temp = graph.getBarValue(c); //

				graph.paintBarAtIndex(c, graph.PURPLE);

				graph.assignBarValueAtIndex(c, graph.getBarValue(sm)); //

				graph.paintBarAtIndex(sm, graph.PURPLE);
				graph.assignBarValueAtIndex(sm, temp); //
				delay();

			}
			c++;

		}

		graph.visualizeAllBars(graph.SORTED_BAR_COLOR);
	}

	int current;
	int check;

	/**
	 * The heap sort works as follows: first, prepare the list by first 
	 * turning it into a max heap. The algorithm then repeatedly swaps 
	 * the first value of the list with the last value, decreasing the 
	 * range of values considered in the heap operation by one, and 
	 * sifting the new first value into its position in the heap. This 
	 * repeats until the range of considered values is one value in length. 
	 */

	public void heapSort() {
		int len = barsArrayList.size();
		heapify(len);
		int end = len-1;
		while(end > 0) {
			current = end;
			swapNumbers(end,0);
			end--;
			siftDown(0,end);
		}
	}

	public void heapify(int n) {

		int start = iParent(n-1);
		while(start >= 0) {
			siftDown(start, n-1);
			start--;
		}
	}

	public int iParent(int i) { return ((i-1)/2); }
	public int iLeftChild(int i) { return 2*i + 1; }


	public void siftDown(int start, int end) {
		int root = start;
		while(iLeftChild(root) <= end) {
			int child = iLeftChild(root);
			int swap = root;
			check = root;
			if(graph.getBarValue(swap) < graph.getBarValue(child)) {
				swap = child;
			} if(child+1 <= end && (graph.getBarValue(swap) < graph.getBarValue(child+1))) {
				swap = child+1;
			} if(swap == root) {
				return;
			} else {
				swapNumbers(root,swap);
				check = root;
				root = swap;
			}
		}
	}

	/**
	 * The bubbleSort() method utilizes an array to move elements into order
	 * much like a bubble rising; comparing the first element to every other until
	 * it "settles" into a spot with a larger value on its right. This is looped 
	 * until all values have risen to their final location which results in 
	 * smaller values on their left and larger values on their right.
	 */
	public void bubbleSort() {
		// This starts the loop through array
		for (int k = 0; k < barsArrayList.size() - 1; k++) {
			for (int i = 0; i < barsArrayList.size() -k - 1; i++) {
				// Conditions are met and swapping of adjacent values occurs.
				if (graph.getBarValue(i) > graph.getBarValue(i + 1)) { //getBarValue(i) --> arrayList[i]
					delay();
					swapNumbers(i, i + 1);
					delay();
				}

			}
		}

		graph.visualizeAllBars(graph.SORTED_BAR_COLOR);

	}

	/**
	 * Merge sort is a sorting technique based on divide and conquer technique
	 * @param l starting point
	 * @param m ending point
	 * @param r breaking point of the array
	 */
	void merge(int l, int m, int r)
	{
		int n1 = m - l + 1;
		int n2 = r - m;

		int leftArray[] = new int [n1];
		int rightArray[] = new int [n2];

		for (int i=0; i<n1; i++) {
			leftArray[i] = graph.getBarValue(l + i);
		}
		for (int j=0; j<n2; j++) {
			rightArray[j] = graph.getBarValue(m + 1+ j);
		}
		int i = 0, j = 0;

		int k = l;
		while (i < n1 && j < n2) {
			check = k;
			if (leftArray[i] <= rightArray[j]) {
				graph.paintBarAtIndex(k, graph.ACTIVE_BAR_COLOR);
				delay();

				graph.assignBarValueAtIndex(k,leftArray[i]);
				graph.paintBarAtIndex(k, graph.PURPLE);
				delay();
				i++;
			} else {
				graph.paintBarAtIndex(k, graph.ACTIVE_BAR_COLOR);
				delay();

				graph.assignBarValueAtIndex(k,rightArray[j]);
				graph.paintBarAtIndex(k, graph.PURPLE);
				j++;
			}
			delay();
			k++;
		}

		while (i < n1) {
			graph.paintBarAtIndex(k, graph.ACTIVE_BAR_COLOR);
			delay();
			graph.assignBarValueAtIndex(k,leftArray[i]);
			graph.paintBarAtIndex(k, graph.PURPLE);
			i++;
			k++;
			delay();
		}

		while (j < n2) {
			graph.paintBarAtIndex(k, graph.ACTIVE_BAR_COLOR);
			graph.assignBarValueAtIndex(k,rightArray[j]);
			graph.paintBarAtIndex(k, graph.PURPLE);
			j++;
			k++;
			delay();
		}
	}

	public void merSortRunner() {
		mergeSort(0, graph.number_of_bars -1);
		graph.visualizeAllBars(graph.SORTED_BAR_COLOR);
	}

	/**
	 * The merge sort works as follows: first, divide the unsorted 
	 * list into n sublists, each containing one element, then 
	 * repeatedly merge sublists to produce new sorted sublists 
	 * until there is only one sublist remaining. This will be the 
	 * sorted list.
	 */
	public void mergeSort(int l, int r) {
		if (l < r) {
			int m = (l+r)/2;
			current = r;
			mergeSort(l, m);
			mergeSort(m+1, r);

			merge(l, m, r);

		}
	}
	/**
	 * This generate an "artificial delay" between animation to allow user be able to observe the underlying swapping of bars happening 
	 * on each algorithm. A delay happens between 2 values of comparing bars being swapped.
	 */
	public void delay() {
		try {
			Thread.sleep(animation_delayed);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}




}


