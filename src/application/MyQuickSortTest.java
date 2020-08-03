package application;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyQuickSortTest {

	@Test
	void testSort() {
		MyQuickSort QS = new MyQuickSort();
        int[] randomArray = {19,2,20,42,56,50,12};
        int[] expectedArray = {2, 12, 19, 20, 42, 50, 56};
        QS.sort(randomArray);
        
        assertEquals(expectedArray[0], 2);
        assertEquals(expectedArray[1], 12);
        assertEquals(expectedArray[2], 19);
        assertEquals(expectedArray[3], 20);
        assertEquals(expectedArray[4], 42);
        assertEquals(expectedArray[5], 50);
        assertEquals(expectedArray[6], 56);
	}
	
	
	
}

