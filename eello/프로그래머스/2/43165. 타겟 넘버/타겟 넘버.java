class Solution {
    
    public int solution(int[] numbers, int target) {
        return dfs(numbers, 0, target, 0);
    }
    
    private int dfs(int[] numbers, int idx, int target, int k) {
        if (idx == numbers.length) {
            return target == k ? 1 : 0;
        }
        
        int ret = dfs(numbers, idx + 1, target, k + numbers[idx]);
        return ret + dfs(numbers, idx + 1, target, k - numbers[idx]);
    }
}