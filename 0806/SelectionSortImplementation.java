import java.util.Arrays;

public class SelectionSortImplementation {

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        int totalComparisons = 0;
        int totalSwaps = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                totalComparisons++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
                totalSwaps++;
            }
            System.out.println("第 " + (i + 1) + " 輪排序結果: " + Arrays.toString(arr));
        }

        System.out.println("總比較次數: " + totalComparisons);
        System.out.println("總交換次數: " + totalSwaps);
    }

    public static void main(String[] args) {
        int[] array = {64, 25, 12, 22, 11};
        System.out.println("原始陣列: " + Arrays.toString(array));
        selectionSort(array);
        System.out.println("排序後陣列: " + Arrays.toString(array));
    }
}
