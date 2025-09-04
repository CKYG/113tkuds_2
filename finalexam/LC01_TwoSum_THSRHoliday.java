import java.util.*;

public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] seats = new int[n];
        for (int i = 0; i < n; i++) {
            seats[i] = sc.nextInt();
        }

        // HashMap<需要的數字, 索引>
        Map<Integer, Integer> map = new HashMap<>();
        int ans1 = -1, ans2 = -1;

        for (int i = 0; i < n; i++) {
            int x = seats[i];
            if (map.containsKey(x)) {
                // 找到配對
                ans1 = map.get(x);
                ans2 = i;
                break;
            } else {
                map.put(target - x, i);
            }
        }

        System.out.println(ans1 + " " + ans2);
    }
}
