import java.util.*;

public class M02_YouBikeNextArrival {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            String t = sc.nextLine();
            times[i] = toMinutes(t);
        }

        String query = sc.nextLine();
        int q = toMinutes(query);

        int idx = binarySearch(times, q);

        if (idx == -1) {
            System.out.println("No bike");
        } else {
            System.out.println(formatTime(times[idx]));
        }
    }

    private static int toMinutes(String t) {
        String[] parts = t.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    private static String formatTime(int m) {
        int h = m / 60;
        int min = m % 60;
        return String.format("%02d:%02d", h, min);
    }

    private static int binarySearch(int[] times, int q) {
        int left = 0, right = times.length - 1;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (times[mid] >= q) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
