class Solution {
    public int solution(int sticker[]) {
        int n = sticker.length;
        int answer = 0;
        if (n <= 3) {
            for (int i = 0; i < n; i++) {
                answer = Math.max(answer, sticker[i]);
            }
            
            return answer;
        }
        
        int[][] dp = new int[2][n];
        dp[0][0] = sticker[0]; dp[0][1] = 0; dp[0][2] = sticker[2] + dp[0][0];
        dp[1][0] = 0; dp[1][1] = sticker[1]; dp[1][2] = sticker[2];
        
        for (int i = 3; i < n - 1; i++) {
            dp[0][i] = sticker[i] + Math.max(dp[0][i - 3], dp[0][i - 2]);
            dp[1][i] = sticker[i] + Math.max(dp[1][i - 3], dp[1][i - 2]);
            
            answer = Math.max(answer, dp[0][i]);
            answer = Math.max(answer, dp[1][i]);
        }
        
        dp[1][n - 1] = sticker[n - 1] + Math.max(dp[1][n - 4], dp[1][n - 3]);
        answer = Math.max(answer, dp[1][n - 1]);
        
        return answer;
    }
}