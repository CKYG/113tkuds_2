public class AdvancedArrayRecursion {

    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int pivot = arr[right];
        int partitionIndex = partition(arr, left, right, pivot);
        quickSort(arr, left, partitionIndex - 1);
        quickSort(arr, partitionIndex + 1, right);
    }

    private static int partition(int[] arr, int left, int right, int pivot) {
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                int temp = arr[i];
                arr[i++] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i];
        arr[i] = arr[right];
        arr[right] = temp;
        return i;
    }

    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];
        int i=0,j=0,k=0;
        while(i<arr1.length && j<arr2.length){
            if(arr1[i]<=arr2[j]) merged[k++] = arr1[i++];
            else merged[k++] = arr2[j++];
        }
        while(i<arr1.length) merged[k++] = arr1[i++];
        while(j<arr2.length) merged[k++] = arr2[j++];
        return merged;
    }

    public static int kthSmallest(int[] arr, int k) {
        quickSort(arr, 0, arr.length - 1);
        return arr[k - 1];
    }

    public static boolean checkSubArraySum(int[] arr, int target, int start, int sum) {
        if (sum == target) return true;
        if (start >= arr.length || sum > target) return false;
        return checkSubArraySum(arr, target, start + 1, sum + arr[start]) 
                || checkSubArraySum(arr, target, start + 1, sum);
    }

    public static void main(String[] args) {
        int[] arr1 = {5,2,9,1,5,6};
        int[] arr2 = {2,3,7,8};

        quickSort(arr1, 0, arr1.length -1);
        System.out.println("快速排序後: " + java.util.Arrays.toString(arr1));

        int[] merged = mergeSortedArrays(arr1, arr2);
        System.out.println("合併排序陣列: " + java.util.Arrays.toString(merged));

        System.out.println("第3小元素: " + kthSmallest(arr1, 3));

        System.out.println("子陣列和是否等於15: " + checkSubArraySum(arr1, 15, 0, 0));
        System.out.println("子陣列和是否等於100: " + checkSubArraySum(arr1, 100, 0, 0));
    }
}
