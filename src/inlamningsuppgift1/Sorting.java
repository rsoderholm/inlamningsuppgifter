package inlamningsuppgift1;

import java.util.Comparator;

public class Sorting {

	public static void quickSort(Movie[] arr, Comparator<Movie> comp) {
		quickSort(arr, 0, arr.length - 1, comp);
	}

	private static void quickSort(Movie[] arr, int left, int right, Comparator<Movie> comp) {
		int pivotIndex;
		if (left < right) {
			pivotIndex = partition(arr, left, right, (left + right) / 2, comp);
			quickSort(arr, left, pivotIndex - 1, comp);
			quickSort(arr, pivotIndex + 1, right, comp);
		}
	}

	private static int partition(Movie[] arr, int left, int right, int pivotIndex, Comparator<Movie> comp) {
		Movie pivotValue = arr[pivotIndex];
		int storeIndex = left;
		swap(arr, pivotIndex, right);
		for (int i = left; i < right; i++) {
			if (comp.compare(arr[i], pivotValue) <0) {
				swap(arr, i, storeIndex);
				storeIndex++;
			}
		}
		swap(arr, storeIndex, right);
		return storeIndex;
	}

	private static void swap(Movie[] arr, int pos1, int pos2) {
		Movie temp = arr[pos1];
		arr[pos1] = arr[pos2];
		arr[pos2] = temp;
	}
}
