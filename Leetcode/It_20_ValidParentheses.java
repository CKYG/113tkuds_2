import java.util.*;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else {
                if (stack.isEmpty() || stack.pop() != c) return false;
            }
        }

        return stack.isEmpty();
    }
}

/*
解題思路：
1. 使用 stack 模擬括號匹配。
2. 遇到左括號就壓入對應的右括號。
3. 遇到右括號時，若 stack 為空或不匹配 → 回傳 false。
4. 最後檢查 stack 是否為空，空代表完全匹配。
5. 時間複雜度 O(n)，空間複雜度 O(n)。
*/