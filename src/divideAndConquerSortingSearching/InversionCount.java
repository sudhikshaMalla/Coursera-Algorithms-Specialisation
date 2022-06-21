package src.divideAndConquerSortingSearching;

public class InversionCount {

    public static int countAndSort(int[] array, int low, int high) {
        int mid;
        int inversion_count = 0;
        if(low < high) {
            mid = (low + high) / 2;
            inversion_count += countAndSort(array, low, mid);
            inversion_count += countAndSort(array, mid+1, high);
            inversion_count += countAndMerge(array, low, high);
        }
        return inversion_count;
    }

    public static int countAndMerge(int[] array, int low, int high) {

        int temp[] = new int[high-low+1];
        int mid = (low + high) / 2;
        int i,j,k,count = 0;
        i = low;
        j = mid + 1;
        k = 0;

        while(i<=mid && j<=high) {
            if(array[i]<array[j]) {
                temp[k++] = array[i++];
            }
            else {
                temp[k++] = array[j++];
                count += mid - i + 1;
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

        return count;
    }

    public static void main(String[] args) {

        int[] array = {6,5,7,8,4,3,2,9,0,1};

        System.out.println("Inversion Count: " + countAndSort(array, 0, array.length - 1));

    }
}