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
     *
     * @param candidates 候選數字陣列 (不重複)。
     * @param target     目標總和。
     * @return 所有滿足條件的組合列表。
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 為了確保組合的不重複性，可以先對 candidates 進行排序
        // 雖然題目說 candidates 是 distinct，但排序有助於後續的回溯剪枝
        Arrays.sort(candidates);
        backtrack(candidates, target, 0); // 從索引 0 開始回溯
        return result;
    }

    /**
     * 回溯函數
     *
     * @param candidates 候選數字陣列
     * @param remain     剩餘需要達成的目標值
     * @param startIndex 當前考慮的候選數字的起始索引 (為了避免重複組合)
     */
    private void backtrack(int[] candidates, int remain, int startIndex) {
        // 1. 目標達成：如果剩餘目標為 0，表示找到了一個有效的組合
        if (remain == 0) {
            // 將當前組合的拷貝加入結果列表
            result.add(new ArrayList<>(currentCombination));
            return; // 結束當前分支
        }

        // 2. 剪枝：如果剩餘目標小於 0，表示當前組合超過 target，此分支無效
        if (remain < 0) {
            return; // 回溯
        }

        // 3. 遞迴探索：遍歷候選數字
        // 從 startIndex 開始，確保組合不重複 (例如，先選 2, 再選 3；而不是先選 3, 再選 2)
        for (int i = startIndex; i < candidates.length; i++) {
            int candidate = candidates[i];

            // 提前剪枝：如果當前候選數字大於剩餘目標，
            // 由於 candidates 已經排序，後續的數字只會更大，所以可以直接跳過
            if (candidate > remain) {
                break;
            }

            // 選擇 (Choose)：將當前候選數字加入組合
            currentCombination.add(candidate);

            // 遞迴 (Explore)：繼續尋找下一個數字
            // 因為數字可以重複使用，所以下一次遞迴時， startIndex 仍然是 i
            // (也就是說，可以再次選擇 candidates[i])
            backtrack(candidates, remain - candidate, i);

            // 取消選擇 (Unchoose / Backtrack)：移除最後加入的數字，以便嘗試其他可能性
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}