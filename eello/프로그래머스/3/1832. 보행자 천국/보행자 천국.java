class Solution {
    
    private static final int MOD = 20170805;
    private static final int[][] DIR = {{1, 0}, {0, 1}}; // DOWN, RIGHT
    
    public int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[m][n][2];
        dp[0][0][0] = 1;
        
        for (int y = 0; y < m; y++) {
            for (int x = 0; x < n; x++) {
                for (int d = 0; d < DIR.length; d++) {
                    int ny = y + DIR[d][0], nx = x + DIR[d][1];
                    
                    if (m <= ny || n <= nx) {
                        continue;
                    }
                    
                    if (cityMap[y][x] == 0) {
                        dp[ny][nx][d] = (dp[y][x][0] + dp[y][x][1]) % MOD;
                    } else if (cityMap[y][x] == 2) {
                        dp[ny][nx][d] = dp[y][x][d];
                    }
                }
            }
        }
        
        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
}