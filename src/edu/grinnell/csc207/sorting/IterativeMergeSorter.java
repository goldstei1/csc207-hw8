package edu.grinnell.csc207.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Sort using iterative merge sort.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here.
 */
public class IterativeMergeSorter<T> extends SorterBridge<T> {
   /**
    * Sort vals using iterative merge sort.  See the Sorter<T> interface
    * for additional details.
    */
   @Override
   public T[] sorti(T[] vals, Comparator<T> order) {
       // STUB
       int size = 1;
       int left;
       int right;
       int rightEnd;
       int j,k;
       while (size < vals.length) {
	   for(int i = 0; i + size < vals.length; i += (size*2)) {
	       left = i;
	       right = i + size;
	       rightEnd = right + size;
	       if(rightEnd > vals.length) {
		   rightEnd = vals.length;
	       }
	       j = left;
	       k = right;
	       while(j < k && k < rightEnd) {
		   if(order.compare(vals[j], vals[k]) <= 0) {
		       j++;
		   }
		   else {
		       Utils.swap(vals, j, k);
		       j++;
		   }
		       
	       }
	       if(order.compare(vals[i], vals[i+size]) > 0) {
		   Utils.swap(vals, i, i + size);
	       }
	   }
           // Merge neighboring subarrays of size size
           // FILL IN!
           // The merged subarrays are now twice as large
           size *= 2;
       } // while
       return vals;
   } // sorti(T[], Comparator<T>)
} // IterativeMergeSorter<T>
