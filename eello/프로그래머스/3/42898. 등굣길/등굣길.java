class Solution {
    
    private static final int MOD = 1_000_000_007;
    
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n + 1][m + 1];
        for (int[] p : puddles) {
            dp[p[1]][p[0]] = -1;
        }
        
        dp[1][1] = 1;
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= m; c++) {
                if (dp[r][c] == -1) {
                    continue;
                }
                
                dp[r][c] += dp[r - 1][c] == -1 ? 0 : dp[r - 1][c];
                dp[r][c] += dp[r][c - 1] == -1 ? 0 : dp[r][c - 1];
                dp[r][c] %= MOD;
            }
        }
        
        return dp[n][m];
    }
}