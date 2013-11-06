package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Sort using selection sort.
 * 
 * @author Samuel A. Rebelsky
 * @author Earnest Wheeler
 * @author Daniel Goldstein
 */
public class SelectionSorter<T> extends SorterBridge<T> {
    /**
     * Sort vals using selection sort. See Sorter<T> for more details.
     */
    @Override
    public T[] sorti(T[] vals, Comparator<T> order) {

	// Invariants:
	// I1(i) = Utils.sorted(vals, 0, i).
	// I2(i) = order.compareTo(vals[j], vals[i]) <= 0
	//           for all 0 <= j < i
	for (int i = 0; i < vals.length; i++) {
	    Utils.swap(vals, i, indexOfSmallest(vals, order, i, vals.length));
	} // for
	return vals;
    } // sorti(T[], Comparator<T>)

    /**
     * Find the index of a smallest element in positions [lb..ub) of values.
     * 
     * @param values
     *            , the array into which we are inserting values.
     * @param order
     *            , the comparator used to determine order.
     * @param lb
     *            , the lower bound of the section to search. ub, the upper
     *            bound of the section to search.
     * 
     * @return is, the index of a smallest value
     * 
     * @pre 0 <= lb,ub <= values.length
     * @pre order can be compared to any pair of values in values.
     * @post lb <= is < ub
     * @post For all j, lb <= j < ub, order.compare(values[is], values[j]) <= 0.
     * @post values is not mutated
     */
    int indexOfSmallest(T[] vals, Comparator<T> order, int lb, int ub) {
	int is = lb;

	// Find the index of the smallest T in vals
	// Invariant: 
	// I1(i) = order.compare(vals[is], vals[k]) <= 0
	//           for all lb <= k < ub
	for (int i = lb + 1; i < ub; i++) {
	    if (order.compare(vals[is], vals[i]) > 0) {
		is = i;
	    } // if
	} // for

	return is;
    } // indexOfSmallest(T[], Comparator<T>, int, int)

} // SelectionSorter<T>
