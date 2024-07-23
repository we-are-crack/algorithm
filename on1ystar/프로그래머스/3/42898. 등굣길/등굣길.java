class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] d = new int[n + 1][m + 1];
        for(int[] puddle : puddles) {
            d[puddle[1]][puddle[0]] = -1;
        }
        d[1][0] = 1;
        for(int i = 1; i <= n; i++) { 
            for(int j = 1; j <= m; j++) { 
                if(d[i][j] != -1) {
                    int up = d[i - 1][j] == -1 ? 0 : d[i - 1][j];
                    int left = d[i][j - 1] == -1 ? 0 : d[i][j - 1];
                    d[i][j] = (up + left) % 1_000_000_007;
                }
            }
        }
        return d[n][m];
    }
}