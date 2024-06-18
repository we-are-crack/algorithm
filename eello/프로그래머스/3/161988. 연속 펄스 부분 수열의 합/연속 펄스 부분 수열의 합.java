class Solution {
    public long solution(int[] sequence) {
        int n = sequence.length;
        
        int pulse = 1;
        long[][] dp = new long[2][n];
        dp[0][0] = sequence[0] * pulse;
        dp[1][0] = dp[0][0] * -1;
        
        long answer = Math.max(dp[0][0], dp[1][0]);
        
        for (int i = 1; i < n; i++) {
            pulse *= -1;
            
            dp[0][i] = sequence[i] * pulse;
            dp[1][i] = dp[0][i] * -1;
            
            dp[0][i] = Math.max(dp[0][i], dp[0][i - 1] + dp[0][i]);
            dp[1][i] = Math.max(dp[1][i], dp[1][i - 1] + dp[1][i]);
            
            answer = Math.max(answer, dp[0][i]);
            answer = Math.max(answer, dp[1][i]);
        }
        
        return answer;
    }
}