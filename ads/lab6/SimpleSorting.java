package ads.lab6;

import org.omg.CORBA.Any;

/**
 * A class for simple sorting methods
 */
public class SimpleSorting {

	/**
	 * Sort the array in place using the selection sort algorithm
	 * complexity : n(n-1)/2
	 */
	public static <AnyType extends Comparable<AnyType>> void selection(AnyType[] array) {
		int first;
		for ( int i = 0; i < array.length-1; i++ ) {
			first = i;   //initialize to subscript of first element
			for(int j = i+1; j < array.length; j++) { //locate smallest element between positions 1 and i.
				if( array[j].compareTo(array[first]) < 0 )
					first = j;
			}
			swap(array, first, i); //swap smallest found with element in position i

		}
	}
	
	/**
	 * Sort the array in place using the insertion sort algorithm
	 * complexity : n(n-1)/2 au pire
	 * n(n-1)/4 en moyenne
	 * n-1 au mieux (si array est deja trie)
	 */
	public static <AnyType extends Comparable<AnyType>> void insertion(AnyType[] array) {
		for (int i = 1; i < array.length; i++) {
			for (int j = i; j > 0; j--) {
				if (array[j].compareTo(array[j-1]) < 0) {
					swap(array, j, j-1);
				}
				else break; // super important
			}
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
