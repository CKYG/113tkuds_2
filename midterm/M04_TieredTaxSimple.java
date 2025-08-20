import java.util.*;

public class M04_TieredTaxSimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] incomes = new int[n];
        for (int i = 0; i < n; i++) incomes[i] = sc.nextInt();

        long totalTax = 0;

        for (int income : incomes) {
            int tax = calculateTax(income);
            totalTax += tax;
            System.out.println("Tax: " + tax);
        }

        System.out.println("Average: " + (totalTax / n));
    }

    private static int calculateTax(int income) {
        int tax = 0;

        if (income > 1_000_000) {
            tax += (income - 1_000_000) * 30 / 100;
            income = 1_000_000;
        }
        if (income > 500_000) {
            tax += (income - 500_000) * 20 / 100;
            income = 500_000;
        }
        if (income > 120_000) {
            tax += (income - 120_000) * 12 / 100;
            income = 120_000;
        }
        tax += income * 5 / 100;

        return tax;
    }
}

/*
 * Time Complexity: O(n)
 * 說明: 每筆收入計算稅額時，最多經過四段稅率計算，總計 O(n)。
 */
