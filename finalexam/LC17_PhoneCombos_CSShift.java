import java.util.*;

public class LC17_PhoneCombos_CSShift {
    private static final String[] KEYPAD = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.hasNext() ? sc.next() : "";
        sc.close();

        List<String> res = letterCombinations(digits);
        for (String s : res) {
            System.out.println(s);
        }
    }

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;
        StringBuilder path = new StringBuilder();
        backtrack(digits, 0, path, result);
        return result;
    }

    private static void backtrack(String digits, int idx, StringBuilder path, List<String> result) {
        if (idx == digits.length()) {
            result.add(path.toString());
            return;
        }

        String letters = KEYPAD[digits.charAt(idx) - '0'];
        for (char c : letters.toCharArray()) {
            path.append(c);
            backtrack(digits, idx + 1, path, result);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
