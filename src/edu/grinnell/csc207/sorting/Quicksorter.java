package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import java.util.Random;

/**
 * Sort using Quicksort.
 * 
 * @author Samuel A. Rebelsky
 * @author Daniel Goldstein
 * @author Earnest Wheeler
 */
public class Quicksorter<T> extends SorterBridge<T> {
    /**
     * Sort vals using Quicksort. See the Sorter<T> interface for additional
     * details.
     */
    @Override
    public T[] sorti(T[] vals, Comparator<T> order) {
	qsort(vals, order, 0, vals.length);
	return vals;
    } // sorti(T[], Comparator<T>)

    /**
     * Sort the elements in positions [lb..ub) using Quicksort.
     */
    public void qsort(T[] vals, Comparator<T> order, int lb, int ub) {

	// One element arrays are sorted.
	if (lb >= ub) {
	    return;
	} else {
	    int mid = partition(vals, order, lb, ub);
	    qsort(vals, order, lb, mid);
	    qsort(vals, order, mid + 1, ub);
	} // More than one element
    } // sorti(T[], Comparator<T>, int, int)

    /**
     * Pick a random pivot and reorganize the elements in positions [lb..ub) of
     * vals such that elements smaller than the pivot appear to the left,
     * elements bigger than the pivot appear to the right of the pivot, and the
     * pivot is in the middle.
     * 
     * @param values
     *            , an array.
     * @param order
     *            , a comparator.
     * @param lb
     *            , an integer.
     * @param ub
     *            , an integer.
     * @return mid, the index of the pivot.
     * 
     * @pre order can be applied to any pair of elements in vals.
     * @pre 0 <= lb < ub < values.length.
     * @post lb <= mid < ub
     * @post values[mid] == pivot, for some value pivot
     * @post For all i, lb <= i < mid, order.compare(values[i],pivot) <= 0 For
     *       all i, mid < i < ub, order.compare(pivot, values[i]) < 0
     */
    int partition(T[] vals, Comparator<T> order, int lb, int ub) {
	Random r = new Random();
	int upperInsert = ub - 1;
	// Select an element to be the pivot at random between lb and ub
	int pivotIndex = r.nextInt(ub - lb) + lb;
	T pivot = vals[pivotIndex];

	// Move the pivot to the front of the array so we know where it is after
	// the array has been split into two parts
	Utils.swap(vals, pivotIndex, lb);

	// Invariants:
	// I1(i): for all j, upperInsert < j < vals.length,
	//     order.compare(vals[j], vals[pivotIndex]) > 0)
	// I2(i): for all k, 0 <= k < i, order.compare(vals[k],
	//     vals[pivotIndex]) <= 0
	for (int i = lb + 1; i <= upperInsert; i++) {
	    if (order.compare(pivot, vals[i]) < 0) {
		Utils.swap(vals, i, upperInsert);
		upperInsert--;
		// The loop will need to check the element that was swapped
		// to the current position so make sure i stays in the same
		// place in vals.
		i--;
	    } // if
	} // for

	// Set pivot to be the last element in the lower part of the array
	Utils.swap(vals, upperInsert, lb);

	// upperInsert is mid in that it is the position of the pivot after
	// partitioning
	return upperInsert;
    } // partition

    /**
     * A test to check the runtime of Quicksort. The runtime is theoretically
     * between O(nlog(n)) and O(n^2) depending on the array used and what is
     * selected as the pivot position. This test returns a lower number than
     * nlog(n). This is because only the calls to swap increment the counter and
     * when a value is less than the current pivot, swap is never called.
     */
    public static void main(String args[]) {
	double average = 0;
	Random r = new Random();
	Quicksorter<Integer> qs = new Quicksorter<Integer>();

	// Run with 1000 different arrays of length 100
	for (int i = 0; i < 1000; i++) {

	    // Generate a random array of length 100 with values between 0 and
	    // 100
	    Integer[] in = new Integer[100];
	    for (int j = 0; j < 100; j++) {
		in[j] = r.nextInt(100);
	    } // for

	    // Sort the array and get the number of swaps that were called
	    qs.sorti(in, StandardIntegerComparator.comparator);
	    int count = Utils.getCounter();

	    // Add the number of swaps / the total trials to the average and
	    // reset the counter
	    average += ((double) count) / 1000;
	    Utils.setCounter(0);
	} // for

	System.out.println(average);
	System.out.flush();
    } // main
} // Quicksorter<T>

