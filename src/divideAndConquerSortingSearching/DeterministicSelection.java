package src.divideAndConquerSortingSearching;

public class DeterministicSelection {
    public static int partition(int[] array, int low, int high) {

        int pivot = getPivot(array, low, high);
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
            int piv_pos = partition(array, low, high);
            if(piv_pos == index) {
                return array[piv_pos];
            }
            else if(index < piv_pos) {
                return select(array, low, piv_pos - 1, index);
            }
            return select(array,piv_pos+1,high, index);
        }
        return 0;
    }

    public static int getPivot(int array[], int low, int high) {

        if(high - low + 1 < 5) {
            return low;
        }

        int medians_count = array.length/5;
        int[] medians = new int[medians_count];
        int ind = 0;

        for(int i=low; i<=high; i+=5) {
            if(i+4 <= high) {
                int[] groupOfFive = groupArray(i, 5);
                medians[ind++] = sortAndFindMedianIndex(array, groupOfFive);
            }
        }
        return sortAndFindMedianIndex(array, medians);
    }

    private static int[] groupArray(int i, int groupLength) {
        int[] groupOfFive = new int[groupLength];
        for (int j = 0; j < groupLength; j++) {
            groupOfFive[j] = i + j;
        }
        return groupOfFive;
    }

    public static int sortAndFindMedianIndex(int[] array, int[] indices) {
        int[] values = new int[indices.length];

        for(int i=0; i<indices.length; i++) {
            values[i] = array[indices[i]];
        }

        for(int i=0; i<values.length; i++) {
            bubble(indices, values, i);
        }

        return indices[values.length/2];
    }

    private static void bubble(int[] indices, int[] values, int i) {
        for(int j = 0; j< values.length- i -1; j++) {
            if(values[j] > values[j+1]) {
                swapElements(values, j , j+1);
                swapElements(indices, j, j+1);
            }
        }
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

        int ind = 7;

        System.out.println("Element at index " + ind + " is : ");

        System.out.println(select(array, 0, array.length - 1, ind));

    }
}