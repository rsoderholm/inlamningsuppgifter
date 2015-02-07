package inlamningsuppgift1;

import java.util.Comparator;
import java.util.List;

public class Sorting {

	public static void quickSort(List<Movie> arr, Comparator<Movie> comp) {
		quickSort(arr, 0, arr.size() - 1, comp);
	}

	private static void quickSort(List<Movie> arr, int left, int right, Comparator<Movie> comp) {
		int pivotIndex;
		if (left < right) {
			pivotIndex = partition(arr, left, right, (left + right) / 2, comp);
			quickSort(arr, left, pivotIndex - 1, comp);
			quickSort(arr, pivotIndex + 1, right, comp);
		}
	}

	private static int partition(List<Movie> arr, int left, int right, int pivotIndex, Comparator<Movie> comp) {
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

	private static void swap(List<Movie> arr, int pos1, int pos2) {
		Movie temp = arr.get(pos1);
		arr.set(pos1, arr.get(pos2));
		arr.set(pos2, temp);		
	}
}
