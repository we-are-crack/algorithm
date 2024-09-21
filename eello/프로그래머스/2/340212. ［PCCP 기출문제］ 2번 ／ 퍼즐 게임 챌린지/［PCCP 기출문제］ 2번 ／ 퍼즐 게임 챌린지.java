class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int min = 1, max = 100_001;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (solve(diffs, times, limit, mid)) {
                answer = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        
        return answer;
    }
    
    private boolean solve(int[] diffs, int[] times, long limit, int level) {
        int n = diffs.length;
        limit -= times[0];
        
        for (int i = 1; i < n; i++) {
            int diff = diffs[i];
            int time = times[i];
            
            if (diff <= level) {
                limit -= time;
            } else {
                limit -= (diff - level) * (time + times[i - 1]) + time;
            }
            
            if (limit < 0) {
                return false;
            }
        }
        
        return true;
    }
}