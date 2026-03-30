class Solution {
    public int solution(int[] money) {
        int n = money.length;
        int[][] dp = new int[2][n];
        
        dp[0][0] = 0;
        dp[0][1] = money[1];
        
        dp[1][0] = money[0];
        dp[1][1] = money[0];
        
        for (int i = 2; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                if (i == n - 1) {
                    if (j == 0) { // 0번째 집을 훔치지 않고 시작한 경우
                        dp[j][i] = Math.max(dp[j][i - 2] + money[i], dp[j][i - 1]);
                    } else { // 0번째 집을 훔치고 시작한 경우
                        dp[j][i] = dp[j][i - 1];
                    }
                } else dp[j][i] = Math.max(dp[j][i - 2] + money[i], dp[j][i - 1]);
            }
        }
        
        return Math.max(dp[0][n - 1], dp[1][n - 1]);
    }
}