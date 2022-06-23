package src.divideAndConquerSortingSearching;

import java.util.Random;

public class QuickSort {

    public static void sort(int[] array, int low, int high) {
        int pivot, i, j, temp;
        if(low < high) {

            pivot = low;
            i = low;
            j = high;

            while(i < j) {
                while(array[i] <= array[pivot] && i<=high) {
                    ++i;
                }
                while (array[j] > array[pivot] && j>=low) {
                    --j;
                }
                if(i<j) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            temp = array[j];
            array[j] = array[pivot];
            array[pivot] = temp;
            sort(array, low, j-1);
            sort(array, j+1, high);
        }
    }

    public static void main(String[] args) {
        int[] array = {8,3,2,4,1,6,9,5,7};

        sort(array, 0, array.length-1);

        System.out.println("Array after sorting : ");

        for(int i=0; i< array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}