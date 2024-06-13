class Solution {
    
    private static final long MAX = 1_000_000_000;
    
    public long solution(int n, int[] times) {
        long min = 1, max = MAX * MAX;
        long answer = 0;
        while (min <= max) {
            long minute = (min + max) / 2;
            
            if (isPossible(minute, n, times)) {
                max = minute - 1;
                answer = minute;
            } else {
                min = minute + 1;
            }
        }
        
        return answer;
    }
    
    private boolean isPossible(long minute, int n, int[] times) {
        long cnt = 0;
        for (int time : times) {
            cnt += minute / time;
        }
        return n <= cnt;
    }
}