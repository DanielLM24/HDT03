import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SortDriverTest {

	@Test
	public void testMergeSort() {
		System.out.println("Prueba Merge Sort");
		ArrayList<Integer> list = new ArrayList<Integer>();
		int item = 0;
		for(int i = 10; i >= 0; i--)
			list.add(i);
		for(int s:list)
			System.out.println(s);
		int resultado = SortDriver.mergeSort(list).get(0);
		assertEquals(item, resultado);
	}

	@Test
	public void testQuickSort() {
		System.out.println("Prueba Quick Sort");
		ArrayList<Integer> list = new ArrayList<Integer>();
		int item = 0;
		for(int i = 10; i >= 0; i--)
			list.add(i);
		for(int s:list)
			System.out.println(s);
		int resultado = SortDriver.quickSort(list).get(0);
		assertEquals(item, resultado);
	}

}
