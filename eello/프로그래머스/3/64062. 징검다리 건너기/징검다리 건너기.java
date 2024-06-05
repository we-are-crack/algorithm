class Solution {
    
    public int solution(int[] stones, int k) {
        int right = 0;
        for (int stone : stones) {
            right = Math.max(right, stone);
        }
        
        int left = 0;
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isPossible(stones, mid, k)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
    
    private boolean isPossible(int[] stones, int n, int k) {
        int temp = 0;
        for (int stone : stones) {
            if (n - 1 < stone) {
                temp = 0;
            } else {
                temp++;
            }
            
            if (temp == k) {
                return false;
            }
        }
        
        return true;
    }
}