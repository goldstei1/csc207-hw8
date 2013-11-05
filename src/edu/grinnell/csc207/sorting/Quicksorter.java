package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import java.util.Random;

/**
 * Sort using Quicksort.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here.
 */
public class Quicksorter<T> extends SorterBridge<T> {
   /**
    * Sort vals using Quicksort.  See the Sorter<T> interface
    * for additional details.
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
           qsort(vals, order, mid+1, ub);
       } // More than one element
   } // sorti(T[], Comparator<T>, int, int)

   /**
    * Pick a random pivot and reorganize the elements in positions 
    * [lb..ub) of vals such that elements smaller than the pivot appear
    * to the left, elements bigger than the pivot appear to the right
    * of the pivot, and the pivot is in the middle.  
    *
    * @param
    *    values, an array.
    * @param
    *    order, a comparator.
    * @param
    *    lb, an integer.
    * @param
    *    ub, an integer.
    * @return
    *    mid, the index of the pivot.
    *
    * @pre
    *    order can be applied to any pair of elements in vals.
    * @pre
    *    0 <= lb < ub < values.length.
    * @post
    *    lb <= mid < ub
    * @post
    *    values[mid] == pivot, for some value pivot
    * @post
    *    For all i, lb <= i < mid, order.compare(values[i],pivot) <= 0
    *    For all i, mid < i < ub, order.compare(pivot, values[i]) < 0
    */
   int partition(T[] vals, Comparator<T> order, int lb, int ub) {
       Random r = new Random();
       int upperInsert = ub - 1;
       int pivotIndex = r.nextInt(ub - lb) + lb;
       T pivot = vals[pivotIndex];
       Utils.swap(vals, pivotIndex, lb);
       
	// Invariants:
	// I1(n): for all j, upperInsert < j < vals.length,
	//     order.compare(vals[j], vals[pivotIndex]) > 0)
	// I2(n): for all k, 0 <= k < i, order.compare(vals[k],
	//     vals[pivotIndex]) <= 0
	for (int i = lb+1; i <= upperInsert; i++) {
	   if (order.compare(pivot, vals[i]) < 0) {
	       Utils.swap(vals, i, upperInsert);
	       upperInsert--;
	       i--;
	   } 
       }
       Utils.swap(vals, upperInsert, lb);
       
       // upperInsert is mid in that it is the position of the pivot after partitioning
       return upperInsert;
   } // partition
   
   public static void main(String args[]) {
       double average = 0;
       Quicksorter<Integer> qs = new Quicksorter<Integer>();
       for (int i = 0; i < 1000; i++) {
	   
       qs.sorti(new Integer[] {3, 4, 7, 1, -1, 8, 9, 11, 3, 10, 5, -38}, StandardIntegerComparator.comparator);
       int count = Utils.getCounter();
       average += ((double) count) / 1000;
       Utils.setCounter(0);
       }
       System.out.println(average);
       System.out.flush();
   }
} // Quicksorter<T>


