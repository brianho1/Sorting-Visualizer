package application;

public class MyQuickSort {
	private int array[];
    private int length;
 
    public void sort(int[] RandomArr) {
         
        if (RandomArr == null || RandomArr.length == 0) {
            return;
        }
        this.array = RandomArr;
        length = RandomArr.length;
        quickSort(0, length - 1);
    }
 
    private void quickSort(int lowIndex, int highIndex) {
         
        int i = lowIndex;
        int j = highIndex;
        // calculate pivot number,taking pivot as middle index number
        int pivot = array[lowIndex+(highIndex-lowIndex)/2];
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, it will identify a number from left side which 
             * is greater then the pivot value, and also it will identify a number 
             * from right side which is less then the pivot value. Once the search 
             * is done, then we swap both numbers.
             */
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
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
            quickSort(lowIndex, j);
        if (i < highIndex)
            quickSort(i, highIndex);
    }
 
    private void swapNumbers(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
     
 

	/*public static void main(String[] args) {
		MyQuickSort sorter = new MyQuickSort();
        int[] randomArray = {19,2,125,20,44,75,222,56,99,50,12};
        sorter.sort (randomArray);
        for(int i:randomArray){
            System.out.print(i);
            System.out.print(" ");
        }
    }*/
}
	


