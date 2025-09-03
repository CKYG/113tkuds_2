import java.util.*;

class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            int value = map.get(s.charAt(i));
            if (i + 1 < s.length() && value < map.get(s.charAt(i + 1))) {
                total -= value; // 減法情況
            } else {
                total += value;
            }
        }
        return total;
    }
}

/*
解題思路：
1. 建立字典對應：I=1, V=5, X=10, L=50, C=100, D=500, M=1000。
2. 從左到右掃描：
   - 若當前字元比右邊小，代表是減法情況（例如 IV=4, IX=9），則 total 減去它。
   - 否則直接加上。
3. 掃描完畢即為結果。
4. 時間複雜度 O(n)，空間複雜度 O(1)。
*/