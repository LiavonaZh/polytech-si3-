package ads.lab6;

/**
 * A class for the recursive merge sort algorithm.
 */
public class MergeSort {

	/**
	 * Sort the array using the recursive merge sort algorithm.
	 * This algorithm is not in place and needs an auxiliary array of
	 * the same size than the array to sort
	 * Complexity: THETA( n.log(n) ) where n is the size of the array
	 */		
	public static <AnyType extends Comparable<AnyType>> void sort(AnyType[] array) {
		AnyType[] tmp = (AnyType[]) new Comparable[array.length];
		sort(array,tmp,0,array.length - 1);
	}
	
	/**
	 * Sort the array in the range [lo, hi] using the portion [lo, hi]
	 * of the auxiliary array tmp
	 * Complexity: THETA( n.log(n) ) where n = hi - lo + 1
	 */
	private static <AnyType extends Comparable<AnyType>> void sort(AnyType[] array, AnyType[] tmp, int lo, int hi) {
		if( lo < hi ) {
			int center = (lo + hi) / 2;
			sort(array, tmp, lo, center);
			sort(array, tmp, center + 1, hi);
			merge(array, tmp, lo, center + 1, hi);
		}
	}
	
	/**
	 * Merge array[lo, mid] and array[mid+1, hi] into tmp[lo, hi]
	 * and copy back the result into array[lo, hi]
	 * Precondition: array[lo, mid] and array[mid+1, hi] are sorted
	 * Complexity: THETA( n ) where n = hi - lo + 1
	 */
	private static <AnyType extends Comparable<AnyType>> void merge(AnyType[] array, AnyType[] tmp, int lo, int mid, int hi) {
/*		int leftEnd = mid - 1;
		int k = lo;
		int num = hi - lo + 1;

		while(lo <= leftEnd && mid <= hi)
			if(array[lo].compareTo(array[mid]) <= 0)
				tmp[k++] = array[lo++];
			else
				tmp[k++] = array[mid++];

		while(lo <= leftEnd)    // Copy rest of first half
			tmp[k++] = array[lo++];

		while(mid <= hi)  // Copy rest of right half
			tmp[k++] = array[mid++];

		// faut mettre dans fonction transfer qui est en bas
		// Copy tmp back
		for(int i = 0; i < num; i++, hi--)
			array[hi] = tmp[hi];*/

// marche pas
		int a1 = lo, a2 = mid+1;
		for (int i = lo; i <= hi; i++) {
			if (a2 > hi || ( a1 <= mid && array[a1].compareTo(array[a2]) < 0))
				tmp[i] = array[a1++];
			else
				tmp[i] = array[a2++];
			}
		transfer(tmp, array, lo, hi);
		}
	
	/**
	 * Copy the elements from tmp[lo, hi] into array[lo, hi]
	 * Complexity: THETA( n ) where n = hi - lo + 1
	 */
	private static <AnyType> void transfer(AnyType[] tmp, AnyType[] array, int lo, int hi) {
		for(int i = lo; i <= hi; i++)
			array[i] = tmp[i];
	}
}
