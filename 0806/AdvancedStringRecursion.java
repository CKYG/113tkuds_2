import java.util.HashSet;
import java.util.Set;

public class AdvancedStringRecursion {

    public static void permutations(String str, String prefix, Set<String> results) {
        if (str.length() == 0) {
            results.add(prefix);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            permutations(str.substring(0,i) + str.substring(i+1), prefix + str.charAt(i), results);
        }
    }

    public static boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean firstMatch = (!text.isEmpty() && (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return (isMatch(text, pattern.substring(2)) ||
                    (firstMatch && isMatch(text.substring(1), pattern)));
        } else {
            return firstMatch && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    public static String removeDuplicates(String str) {
        if (str.length() < 2) return str;
        char first = str.charAt(0);
        String rem = removeDuplicates(str.substring(1));
        if (rem.indexOf(first) == -1) return first + rem;
        else return rem;
    }

    public static Set<String> substrings(String str) {
        Set<String> subs = new HashSet<>();
        substringsHelper(str, 0, "", subs);
        return subs;
    }

    private static void substringsHelper(String str, int index, String current, Set<String> subs) {
        if (index == str.length()) {
            if (!current.isEmpty()) subs.add(current);
            return;
        }
        substringsHelper(str, index + 1, current + str.charAt(index), subs);
        substringsHelper(str, index + 1, current, subs);
    }

    public static void main(String[] args) {
        Set<String> perms = new HashSet<>();
        permutations("abc", "", perms);
        System.out.println("所有排列組合: " + perms);

        System.out.println("字串匹配 (a*b): " + isMatch("aaab", "a*b"));
        System.out.println("移除重複字元: " + removeDuplicates("banana"));
        System.out.println("所有子字串組合: " + substrings("abc"));
    }
}
