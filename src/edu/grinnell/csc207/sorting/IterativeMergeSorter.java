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
   @SuppressWarnings({"unchecked"})
   public T[] sorti(T[] vals, Comparator<T> order) {
       // STUB
       int size = 1;
       int left;
       int right;
       int rightEnd;
       int j;
       int k;
       int m;
       T[] compileArray = (T[]) new Object[vals.length];
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
	       m = 0;
	       while(j < right && k < rightEnd) {
		   if(order.compare(vals[j], vals[k]) <= 0) {
		       compileArray[m] = vals[j];
		       j++;
		       m++;
		   }
		   else {
		       compileArray[m] = vals[k];
		       k++;
		       m++;
		   }
	       }
	       while(j < right) {
		   compileArray[m] = vals[j];
		   j++;
		   m++;
	       }
	       while(k < rightEnd) {
		   compileArray[m] = vals[k];
		   k++;
		   m++;
	       }
	       for(int l = left; l < rightEnd; l++) {
		   vals[l] = compileArray[l - left];
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
