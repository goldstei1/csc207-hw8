package edu.grinnell.csc207.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Sort using iterative merge sort.
 * 
 * @author Samuel A. Rebelsky
 * @author Earnest Wheeler
 * @author Daniel Goldstein
 */
public class IterativeMergeSorter<T> extends SorterBridge<T> {
    /**
     * Sort vals using iterative merge sort. See the Sorter<T> interface for
     * additional details.
     */
    @Override
    @SuppressWarnings({ "unchecked" })
    public T[] sorti(T[] vals, Comparator<T> order) {
	int size = 1;
	int left;
	int right;
	int rightEnd;
	int j; // index for the left subarray element being examined
	int k; // index for the right subarray element being examined
	int m; // index for the temporary array being sorted
	T[] compileArray = (T[]) new Object[vals.length];
	while (size < vals.length) {
	    for (int i = 0; i + size < vals.length; i += (size * 2)) {
		left = i;
		right = i + size;
		rightEnd = right + size;
		if (rightEnd > vals.length) {
		    rightEnd = vals.length;
		}
		j = left;
		k = right;
		m = 0;
		while (j < right && k < rightEnd) {
		    if (order.compare(vals[j], vals[k]) <= 0) {
			compileArray[m] = vals[j];
			j++;
			m++;
		    } else {
			compileArray[m] = vals[k];
			k++;
			m++;
		    }
		}
		// we have to put the rest of the elements at the end
		// all of those elements are in one of the subarrays
		// and are already sorted
		while (j < right) {
		    compileArray[m] = vals[j];
		    j++;
		    m++;
		}
		while (k < rightEnd) {
		    compileArray[m] = vals[k];
		    k++;
		    m++;
		}
		// finally, copy all values merged back into vals
		for (int l = left; l < rightEnd; l++) {
		    vals[l] = compileArray[l - left];
		}
	    } // Merge neighboring subarrays of size size
	      // The merged subarrays are now twice as large
	    size *= 2;
	} // while
	return vals;
    } // sorti(T[], Comparator<T>)
} // IterativeMergeSorter<T>
