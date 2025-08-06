import java.util.*;

public class NumberArrayProcessor {

    public static int[] removeDuplicates(int[] arr) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : arr) set.add(num);
        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) result[i++] = num;
        return result;
    }

    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) merged[k++] = arr1[i++];
            else merged[k++] = arr2[j++];
        }
        while (i < arr1.length) merged[k++] = arr1[i++];
        while (j < arr2.length) merged[k++] = arr2[j++];
        return merged;
    }

    public static int findMostFrequent(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        int maxCount = 0, mostFreq = arr[0];
        for (int num : arr) {
            int count = freq.getOrDefault(num, 0) + 1;
            freq.put(num, count);
            if (count > maxCount) {
                maxCount = count;
                mostFreq = num;
            }
        }
        return mostFreq;
    }

    public static int[][] splitArray(int[] arr, int n) {
        int len = arr.length;
        int size = len / n;
        int remainder = len % n;
        int[][] result = new int[n][];
        int index = 0;
        for (int i = 0; i < n; i++) {
            int currentSize = size + (i < remainder ? 1 : 0);
            result[i] = new int[currentSize];
            for (int j = 0; j < currentSize; j++) {
                result[i][j] = arr[index++];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 3, 5, 7, 7, 9};
        int[] arr2 = {2, 3, 4, 4, 5, 6, 7};

        int[] noDup = removeDuplicates(arr1);
        System.out.println("移除重複元素: " + Arrays.toString(noDup));

        int[] merged = mergeSortedArrays(arr1, arr2);
        System.out.println("合併兩排序陣列: " + Arrays.toString(merged));

        int mostFreq = findMostFrequent(merged);
        System.out.println("出現頻率最高元素: " + mostFreq);

        int[][] splitted = splitArray(merged, 3);
        System.out.println("分割成三個子陣列:");
        for (int i = 0; i < splitted.length; i++) {
            System.out.println(Arrays.toString(splitted[i]));
        }
    }
}
