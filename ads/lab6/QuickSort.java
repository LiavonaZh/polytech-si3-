package ads.lab6;

import org.omg.CORBA.Any;

/**
 * A class for the quicksort algorithm
 */
public class QuickSort {
	
	private static final int CUTOFF = 10;
	
	/**
	 * Sort the array in place using the quicksort algorithm
	 */
	public static <AnyType extends Comparable<AnyType>> void sort(AnyType[] array) {
		sort(array, 0, array.length-1);
	}

	/**
	 * Sort the portion array[lo,hi] in place using the quicksort algorithm
	 */	
	private static <AnyType extends Comparable<AnyType>> void sort(AnyType[] array, int lo, int hi) {
		if( lo + CUTOFF > hi )
			insertion(array, lo, hi);
		else {
			int mid = (lo+hi)/2;
			AnyType pivot = array[median(array, lo, mid, hi)];
			int partition = partition(array, lo, hi);
			sort(array, lo, partition - 1);
			sort(array, partition + 1, hi);
		}
	}

	/**
	 * Partition the portion array[lo,hi] and return the index of the pivot
	 */
	private static <AnyType extends Comparable<AnyType>> int partition(AnyType[] array, int lo, int hi) {
		int leftPtr = lo;
		int rightPtr = hi - 1;
		int pivot = median(array, lo, (lo+hi)/2, hi);

		while (true) {
			while (array[++leftPtr].compareTo(array[pivot]) < 0)
				;
			while (array[--rightPtr].compareTo(array[pivot]) > 0)
				;
			if (leftPtr >= rightPtr)
				break;
			else
				swap(array, leftPtr, rightPtr);
		}
		swap(array, leftPtr, hi - 1);
		return leftPtr;
	}

	/**
	 * Return the index of the median of { array[lo], array[mid], array[hi] }
	 */
	private static <AnyType extends Comparable<AnyType>> int median(AnyType[] array, int lo, int mid, int hi) {
		if(array[lo].compareTo(array[mid]) > 0)
			swap(array, lo, mid);
		if(array[lo].compareTo(array[hi]) > 0)
			swap(array, lo, hi);
		if(array[mid].compareTo(array[hi]) > 0)
			swap(array, mid, hi);
		swap(array, mid, hi-1);
		return hi-1;
	}
	
	/**
	 * Sort array[lo, hi] in place using the insertion sort algorithm
	 */
	private static <AnyType extends Comparable<AnyType>> void insertion(AnyType[] array, int lo, int hi) {
		for(int p = lo + 1; p <= hi; p++) {
			AnyType tmp = array[p];
			int j;
			for(j = p; j > lo && tmp.compareTo(array[j-1]) < 0; j--)
				array[j] = array[j-1];
			array[j] = tmp;
		}
	}
	
	/**
	 * Swap array[i] and array[j]
	 */
	private static <AnyType> void swap(AnyType[] array, int i, int j) {
		AnyType tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
}
