package inlamningsuppgift1;

import java.util.ArrayList;
import java.util.Comparator;

public class Sorting {

	public static void quickSort(ArrayList<Movie> arr, Comparator<Movie> comp) {
		quickSort(arr, 0, arr.size() - 1, comp);
	}
	
	public static void bubbleSort(ArrayList<Movie> array, Comparator<Movie> comp) {
		for( int i=0; i < array.size() - 1; i++ ) {
            for( int j = array.size() - 1; j > i; j-- ) {
                if( comp.compare( array.get(j), array.get(j - 1 )) < 0 )
                    swap( array, j, j - 1 );
            }
        }
		
	}

	private static void quickSort(ArrayList<Movie> arr, int left, int right, Comparator<Movie> comp) {
		int pivotIndex;
		if (left < right) {
			pivotIndex = partition(arr, left, right, (left + right) / 2, comp);
			quickSort(arr, left, pivotIndex - 1, comp);
			quickSort(arr, pivotIndex + 1, right, comp);
		}
	}

	private static int partition(ArrayList<Movie> arr, int left, int right, int pivotIndex, Comparator<Movie> comp) {
		Movie pivotValue = arr.get(pivotIndex);
		int storeIndex = left;
		swap(arr, pivotIndex, right);
		for (int i = left; i < right; i++) {
			if (comp.compare(arr.get(i), pivotValue) <0) {
				swap(arr, i, storeIndex);
				storeIndex++;
			}
		}
		swap(arr, storeIndex, right);
		return storeIndex;
	}

	private static void swap(ArrayList<Movie> arr, int pos1, int pos2) {
		Movie temp = arr.get(pos1);
		arr.set(pos1, arr.get(pos2));
		arr.set(pos2, temp);		
	}

	
}
