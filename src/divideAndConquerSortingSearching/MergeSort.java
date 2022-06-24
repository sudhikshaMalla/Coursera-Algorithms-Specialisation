package src.divideAndConquerSortingSearching;

public class MergeSort {

    public static void sort(int[] array, int low, int high) {
        int mid;
        if(low < high) {
            mid = (low + high) / 2;
            sort(array, low, mid);
            sort(array, mid+1, high);
            merge(array, low, high);
        }
    }

    public static void merge(int[] array, int low, int high) {

        int temp[] = new int[high-low+1];
        int mid = (low + high) / 2;
        int i,j,k;
        i = low;
        j = mid + 1;
        k = 0;

        while(i<=mid && j<=high) {
            if(array[i]<array[j]) {
                temp[k++] = array[i++];
            }
            else {
                temp[k++] = array[j++];
            }
        }

        while(i<=mid) {
            temp[k++] = array[i++];
        }

        while(j<=high) {
            temp[k++] = array[j++];
        }

        k=0;

        for(i=low; i<=high; i++) {
            array[i] = temp[k++];
        }

    }

    public static void main(String[] args) {

        int[] array = {6,5,7,8,4,3,2,9,0,1};

        sort(array, 0, array.length - 1);

        for(int i=0; i< array.length; i++) {
            System.out.print(array[i] + " ");
        }

    }
}