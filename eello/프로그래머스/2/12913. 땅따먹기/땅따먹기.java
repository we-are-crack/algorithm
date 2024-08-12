class Solution {
    
    public int solution(int[][] land) {
        int n = land.length, m = land[0].length;
        int[][] dp = new int[n][m];
        dp[0] = land[0];
        
        for (int r = 1; r < n; r++) {
            for (int c = 0; c < m; c++) {
                for (int k = 0; k < m; k++) {
                    if (c == k) {
                        continue;
                    }
                    
                    dp[r][c] = Math.max(dp[r][c], dp[r - 1][k] + land[r][c]);
                }
            }
        }
        
        int answer = 0;
        for (int c = 0; c < m; c++) {
            answer = Math.max(answer, dp[n - 1][c]);
        }
        
        return answer;
    }
}