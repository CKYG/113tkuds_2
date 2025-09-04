import java.util.*;

public class LC11_MaxArea_FuelHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt();
        }
        System.out.println(maxArea(heights));
    }

    public static long maxArea(int[] height) {
        int L = 0, R = height.length - 1;
        long max = 0;

        while (L < R) {
            long area = (long)(R - L) * Math.min(height[L], height[R]);
            max = Math.max(max, area);

            if (height[L] < height[R]) {
                L++;
            } else {
                R--;
            }
        }

        return max;
    }
}
