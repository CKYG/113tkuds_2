import java.util.*;

public class M05_GCD_LCM_Recursive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();

        long gcd = getGCD(a, b);
        long lcm = a / gcd * b;

        System.out.println("GCD: " + gcd);
        System.out.println("LCM: " + lcm);
    }

    private static long getGCD(long a, long b) {
        if (b == 0) return a;
        return getGCD(b, a % b);
    }
}

/*
 * Time Complexity: O(log(min(a, b)))
 * 說明: 使用歐幾里得演算法計算 GCD，每次遞迴將較小值取餘數，
 * 收斂速度快，計算 LCM 時先除後乘避免溢位。
 */
