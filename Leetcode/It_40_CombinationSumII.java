import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    // 用來存放所有有效的組合
    List<List<Integer>> result = new ArrayList<>();
    // 用來存放當前正在構建的組合
    List<Integer> currentCombination = new ArrayList<>();

    /**
     * 找出 candidates 中所有不重複的組合，使其加總等於 target。
     * 每個數字只能在組合中使用一次。
     *
     * @param candidates 候選數字陣列 (可能包含重複數字)。
     * @param target     目標總和。
     * @return 所有滿足條件的組合列表。
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 1. 對 candidates 進行排序，以便處理重複數字和重複組合
        Arrays.sort(candidates);
        backtrack(candidates, target, 0); // 從索引 0 開始回溯
        return result;
    }

    /**
     * 回溯函數
     *
     * @param candidates   候選數字陣列 (已排序)
     * @param remain       剩餘需要達成的目標值
     * @param startIndex   當前考慮的候選數字的起始索引
     */
    private void backtrack(int[] candidates, int remain, int startIndex) {
        // 1. 目標達成：如果剩餘目標為 0，表示找到了一個有效的組合
        if (remain == 0) {
            result.add(new ArrayList<>(currentCombination));
            return; // 結束當前分支
        }

        // 2. 剪枝：如果剩餘目標小於 0，此分支無效
        if (remain < 0) {
            return; // 回溯
        }

        // 3. 遞迴探索：遍歷候選數字
        for (int i = startIndex; i < candidates.length; i++) {
            // 4. 處理重複數字：
            //    如果當前數字與前一個數字相同，並且我們不是在處理第一個數字 (i > startIndex)，
            //    則跳過當前數字，以避免生成重複組合。
            if (i > startIndex && candidates[i] == candidates[i - 1]) {
                continue; // 跳過重複的數字
            }

            int candidate = candidates[i];

            // 5. 剪枝優化：如果當前候選數字大於剩餘目標，直接跳過
            if (candidate > remain) {
                break; // 因為陣列已排序，後續數字更大
            }

            // 選擇 (Choose)：將當前候選數字加入組合
            currentCombination.add(candidate);

            // 遞迴 (Explore)：繼續尋找下一個數字
            // 因為每個數字只能使用一次，所以下一次遞迴時，startIndex 必須是 i + 1
            backtrack(candidates, remain - candidate, i + 1);

            // 取消選擇 (Unchoose / Backtrack)：移除最後加入的數字
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}