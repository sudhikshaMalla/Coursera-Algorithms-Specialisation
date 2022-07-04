package src.divideAndConquerSortingSearching;

import java.util.Random;

public class RandomisedSelection {
    public static int partition(int[] array, int low, int high) {
        int pivot = selectPivot(low, high);
        swapElements(array, high, pivot);

        pivot = array[high];

        int i = (low - 1);

        for(int j = low; j <= high - 1; j++)
        {
            if (array[j] < pivot)
            {
                i++;
                swapElements(array, i, j);
            }
        }
        swapElements(array, i + 1, high);
        return (i + 1);

    }

    public static int select(int[] array, int low, int high, int index) {
        if(low<=high) {
            int pivotPosition = partition(array, low, high);
            if(pivotPosition == index) {
                return array[pivotPosition];
            }
            else if(index < pivotPosition) {
                return select(array, low, pivotPosition - 1, index);
            }
            return select(array,pivotPosition+1,high, index);
        }
        return -1;
    }

    public static int selectPivot(int start, int end) {
        Random rand = new Random();
        int diff = end - start + 1;
        int randomNumber = rand.nextInt(diff);
        return randomNumber + start;
    }

    public static void swapElements(int[] array, int i, int j) {
        if(i!=j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] array = {8, 3, 2, 4, 1, 6, 9, 5, 7};

        System.out.println("Given array : ");

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

        System.out.println();

        int ind = 6;

        System.out.println("Element at index " + ind + " is : ");

        System.out.println(select(array, 0, array.length - 1, ind));

    }
}