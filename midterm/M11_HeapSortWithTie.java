import java.util.*;

public class M11_HeapSortWithTie {
    static class Student {
        int score, index;
        Student(int score, int index) {
            this.score = score;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Student[] arr = new Student[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Student(sc.nextInt(), i);
        }

        heapSort(arr, n);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i].score);
            if (i != n - 1) System.out.print(" ");
        }
    }

    private static void heapSort(Student[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            Student temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    private static void heapify(Student[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && compare(arr[left], arr[largest]) > 0) {
            largest = left;
        }
        if (right < n && compare(arr[right], arr[largest]) > 0) {
            largest = right;
        }

        if (largest != i) {
            Student temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }

    private static int compare(Student a, Student b) {
        if (a.score != b.score) {
            return a.score - b.score;
        }
        return b.index - a.index;
    }
}

/*
 * Time Complexity: O(n log n)
 * 說明: 建立堆 O(n)，之後每次彈出元素 O(log n)，總計 O(n log n)。
 */
