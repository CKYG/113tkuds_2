import java.util.*;

public class LC28_StrStr_NoticeSearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String haystack = sc.nextLine();
        String needle = sc.nextLine();
        sc.close();

        System.out.println(strStr(haystack, needle));
    }

    private static int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        int n = haystack.length(), m = needle.length();
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m && haystack.charAt(i + j) == needle.charAt(j)) j++;
            if (j == m) return i;
        }
        return -1;
    }
}
